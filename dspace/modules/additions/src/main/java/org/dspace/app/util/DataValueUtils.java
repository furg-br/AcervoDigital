package org.dspace.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * Armazena funções utilitárias para tratamento de valores oriundos de varíaveis.
 * @author Márcio Ribeiro Gurgel do Amaral
 *
 */
public class DataValueUtils {
	
	private static Logger logger = Logger.getLogger(DataValueUtils.class);
	
	/**
	 * Verifica se dada string posusi valor válido
	 * @param value Valor base para verificação
	 * @return Indicativo de valor válido
	 */
	public static boolean hasValue(String value) {
		return value != null && !value.equals("");
	}

	
	/**
	 * Efetua <i>parse</i> de data no formato <pre>dd/MM/yyyy</pre> para {@link Date}
	 * @param dateAsString Data em formato string
	 * @return Data convertida, em caso de sucesso. <b>null</b> em caso de erro na conversão
	 */
	public static Date parseToDate(String dateAsString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return dateFormat.parse(dateAsString);
		} catch (ParseException e) {
			logger.error("Ocorreu um erro ao converter a data do relatório", e);
		}
		return null;
	}	

}
