package org.dspace.content;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dspace.app.util.FileUtils;
import org.dspace.content.vo.ProductivityReportVO;
import org.dspace.core.Context;
import org.dspace.storage.rdbms.DatabaseManager;
import org.dspace.storage.rdbms.TableRow;
import org.dspace.storage.rdbms.TableRowIterator;



/**
 * Entidade responsável por efetuar operações relacionadas a recuperação de informações para apresentação no Relatório de produtividade
 * @author Márcio Ribeiro Gurgel do Amaral (marcio.rga@gmail.com)
 *
 */
public class ProductivityReportManager
{
    private static Logger log = Logger.getLogger(ProductivityReportManager.class);

	private static final String QUERY_COLUMN_YEAR = "year";
	private static final String QUERY_COLUMN_MONTH = "month";
	private static final String QUERY_COLUMN_NAME = "name";
	private static final String QUERY_COLUMN_ACTION = "action";
	private static final String QUERY_COLUMN_COUNT = "actionCount";
	private static final String PRODUCTIVITY_SQL_LOCATION = "datasource/queries/productivity.sql";
	private static final String PRODUCTIVITY_SQL_LOCATION_FILTER_NAME = "datasource/queries/productivity.name.sql";
	private static final String PRODUCTIVITY_SQL_LOCATION_FILTER_PERIOD = "datasource/queries/productivity.period.sql";

	/**
	 * Entry-point para tratamento de solicitação de busca de informações de produtividade de equipe.
	 * <br>Responsável também, por tratar instâncias de {@link TableRow} e converter parte das linhas em colunas, simulando a função
	 * <i>crosstab</i> do banco de dados
	 * @param context Contexto, utilizado para obtenção de fonte de dados
	 * @param initDate Data inicial (pode ser nula)
	 * @param endDate Data final (pode ser nula)
	 * @param name Nome do membro (submissor/avaliador etc..)
	 * @return Lista com os resultados recuperados da base de dados
	 */
	public List<ProductivityReportVO> generateReport(Context context, Date initDate, Date endDate, String name) {
		
		
		log.info(MessageFormat.format("Relatório de produtividade solicitado. Data inicial: {0}, Data final: {1}, Nome: {2}.",  initDate, endDate, name));
		
		List<ProductivityReportVO> linearResult = new ArrayList<ProductivityReportVO>();
		List<TableRow> queryResult = doQuery(context, initDate, endDate, name);
		
		if(queryResult != null) {
				
			Map<String, Map<String, ProductivityReportVO>> conversionMap = new HashMap<String, Map<String,ProductivityReportVO>>();
			
			for(TableRow currentResult : queryResult) {

				String currentName = currentResult.getStringColumn(QUERY_COLUMN_NAME);
				String monthAndYear = currentResult.getStringColumn(QUERY_COLUMN_MONTH) + "/" + currentResult.getStringColumn(QUERY_COLUMN_YEAR);
				String action = currentResult.getStringColumn(QUERY_COLUMN_ACTION);
				Long count = currentResult.getLongColumn(QUERY_COLUMN_COUNT);
				
				if(!conversionMap.containsKey(currentName)) {
					
					conversionMap.put(currentName, new HashMap<String, ProductivityReportVO>());
					
				}
				
				if(!conversionMap.get(currentName).containsKey(monthAndYear)) {
					
					ProductivityReportVO productivityVO = new ProductivityReportVO();
					productivityVO.setYearAndMonth(monthAndYear);
					productivityVO.setName(currentName);
					conversionMap.get(currentName).put(monthAndYear, productivityVO);
					linearResult.add(productivityVO);
					
				}
				
				conversionMap.get(currentName).get(monthAndYear).addTotal(ProductivityReportVO.ActionType.findByValue(action), count);
				
			}
			
			
		}
		
		/** Dado o retorno do banco de dados, se faz necessária ordenação via camada java  **/
		Collections.sort(linearResult, new Comparator<ProductivityReportVO>() {

			@Override
			public int compare(ProductivityReportVO o1, ProductivityReportVO o2) {
				// TODO Auto-generated method stub
				int compareTo = o1.getName().compareTo(o2.getName());
				if(compareTo == 0) {
					if(o1.getYearAndMonth() != null && o2.getYearAndMonth() != null) {
						try {
							Date initialDate = new SimpleDateFormat("MM/yyyy").parse(o1.getYearAndMonth());
							Date endDate = new SimpleDateFormat("MM/yyyy").parse(o2.getYearAndMonth());
							
							compareTo = endDate.compareTo(initialDate);
							
						} catch (ParseException e) {
							log.error("Ocorreu um erro ao converter datas do relatório de produtividade", e);
						}
					}
				}
				return compareTo;
			}
		});
		
		return linearResult;
	}

	/**
	 * Gerencia o envio da query ao banco de dados.
	 * <br>Invoca os métodos {@link #resolveQueryString(Date, Date, String)} e {@link #buildReportParameters(Date, Date, String)}
	 * que são responsáveis por construir o SQL e preparar os parâmetros, respectivamente
	 * @param context Contexto, utilizado para obtenção de fonte de dados
	 * @param initDate Data inicial (pode ser nula)
	 * @param endDate Data final (pode ser nula)
	 * @param name Nome do membro (submissor/avaliador etc..)
	 * @return
	 */
	private List<TableRow> doQuery(Context context, Date initDate, Date endDate, String name) {
		try {
			
			String sqlString = resolveQueryString(initDate, endDate, name);
			
			Object[] parameters = buildReportParameters(initDate, endDate, name);
			TableRowIterator queryResult = DatabaseManager.query(context, sqlString, parameters);
			List<TableRow> restuls = new ArrayList<TableRow>();
			
			while(queryResult.hasNext()) {
				
				restuls.add(queryResult.next());
				
			}
			
			return restuls;
			
		} catch (IOException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		return null;
	}

	/**
	 * Verifica quais parâmetros foram preenchidos, em posse disso, prepra array de objetos para envio a camada
	 * de persistência.
	 * @param initDate Data inicial (pode ser nula)
	 * @param endDate Data final (pode ser nula)
	 * @param name Nome do membro (submissor/avaliador etc..)
	 * @return Array de objetos a ser enviado para o BD
	 */
	private Object[] buildReportParameters(Date initDate, Date endDate, String name) {
		
		List<Object> reportParameters = new ArrayList<Object>();
		
		if(name != null && !name.equals("")) {
			reportParameters.add("%" + name.toUpperCase() + "%");
		}
		
		if(initDate != null && endDate != null) {
			reportParameters.add(new java.sql.Date(initDate.getTime()));
			reportParameters.add(new java.sql.Date(endDate.getTime()));
		}
		
		Object[] transferArray = new Object[reportParameters.size()];
		return reportParameters.toArray(transferArray);
	}

	/**
	 * Recupera informações de arquivos, onde residem as queries via:
	 * <ul>
	 * 	<li>{@link #PRODUCTIVITY_SQL_LOCATION} (arquivo principal)</li>
	 * 	<li>{@link #PRODUCTIVITY_SQL_LOCATION_FILTER_PERIOD} (filtro por período)</li>
	 * 	<li>{@link #PRODUCTIVITY_SQL_LOCATION_FILTER_NAME} (filtro por nome)</li>
	 * </ul>
	 * @param initDate Data inicial (pode ser nula)
	 * @param endDate Data final (pode ser nula)
	 * @param name Nome do membro (submissor/avaliador etc..)
	 * @return String, preparada para envio a camada de persistência
	 * @throws IOException
	 */
	private String resolveQueryString(Date initDate, Date endDate, String name) throws IOException {
		
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(FileUtils.getAsString(PRODUCTIVITY_SQL_LOCATION));
		
		String periodFilter = "";
		if(initDate != null && endDate != null) {

			periodFilter = FileUtils.getAsString(PRODUCTIVITY_SQL_LOCATION_FILTER_PERIOD);
			
		}
		
		String nameFilter = "";
		
		if(name != null && !name.equals("")) {
			
			nameFilter = FileUtils.getAsString(PRODUCTIVITY_SQL_LOCATION_FILTER_NAME);
		}
		
		return MessageFormat.format(sqlString.toString(), nameFilter, periodFilter);
	}
	
}
