package org.dspace.app.webui.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dspace.app.util.DataValueUtils;
import org.dspace.app.webui.servlet.DSpaceServlet;
import org.dspace.app.webui.util.JSPManager;
import org.dspace.authorize.AuthorizeException;
import org.dspace.content.ProductivityReportManager;
import org.dspace.content.vo.ProductivityReportVO;
import org.dspace.core.Context;

/**
 * Servlet responsável pelo tratamento de requisições do relatório de produtividade
 * @author Márcio Ribeiro Gurgel do Amaral
 *
 */
public class ProductivityReportServlet extends DSpaceServlet {

	private static final long serialVersionUID = 1L;
	private static final String PARAMTER_INIT_DATE = "init-date";
	private static final String PARAMTER_END_DATE = "end-date";
	private static final String PARAMTER_MEMBER_NAME = "member-name";

	@Override
	protected void doDSGet(Context context, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, AuthorizeException {
		
		JSPManager.showJSP(request, response, "/dspace-admin/productivityreport.jsp");
	}

	@Override
	protected void doDSPost(Context context, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException, AuthorizeException {
		
		String initDateAsString = request.getParameter(PARAMTER_INIT_DATE);
		String endDateAsString = request.getParameter(PARAMTER_END_DATE);
		String name = request.getParameter(PARAMTER_MEMBER_NAME);
		String errorMessageKey = null;
		
		boolean hasFillingError = false;
		boolean hasValidationError = false;
		
		Date initialDate = null;
		Date endDate = null;
		
		
		boolean initialDateFilled = DataValueUtils.hasValue(initDateAsString);
		if(initialDateFilled) {
			initialDate = DataValueUtils.parseToDate(initDateAsString);
			if(initialDate == null){
				errorMessageKey = "jsp.dspace-admin.productivityreport.error.initialdate.invalid";
				hasValidationError = true;
			}
		} 
		
		if(DataValueUtils.hasValue(endDateAsString)) {
			endDate = DataValueUtils.parseToDate(endDateAsString);
			if(endDate == null){
				errorMessageKey = "jsp.dspace-admin.productivityreport.error.enddate.invalid";
				hasValidationError = true;
			}
		}else if(initialDateFilled) {
			endDate = new Date();
		}
		
		if(!hasValidationError && initialDate != null && endDate != null) {
			
			if(endDate.before(initialDate)) {
				errorMessageKey = "jsp.dspace-admin.productivityreport.error.period.invalid";
				hasValidationError = true;
			}
		}
		
		/** Caso não existam erro de preenchimento **/
		if(!hasFillingError) {
				
			/** Caso não existam erros de validação **/
			if(!hasValidationError) {
				
				ProductivityReportManager productivityReportManager = new ProductivityReportManager();
				List<ProductivityReportVO> productivityData = productivityReportManager.generateReport(context, initialDate, endDate,
						DataValueUtils.hasValue(name) ? name.replaceAll("'", "").replaceAll("%", "") : null);
				
				request.setAttribute("productivityData", productivityData);
			}
		}
		
		request.setAttribute("errorMessage", errorMessageKey);
		
		/** By-pass de variáveis inseridas no relatório **/
		request.setAttribute(PARAMTER_INIT_DATE, initDateAsString);
		request.setAttribute(PARAMTER_END_DATE, endDateAsString);
		request.setAttribute(PARAMTER_MEMBER_NAME, name);
		
		JSPManager.showJSP(request, response, "/dspace-admin/productivityreport.jsp");
	}
	
}
