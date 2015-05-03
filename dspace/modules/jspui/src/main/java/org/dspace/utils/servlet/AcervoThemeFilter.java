package org.dspace.utils.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.dspace.app.webui.theme.Theme;
import org.dspace.app.webui.util.UIUtil;
import org.dspace.content.DSpaceObject;
import org.dspace.core.ConfigurationManager;
import org.dspace.core.Context;
import org.dspace.handle.HandleManager;

/**
 * Analista URLs e identifica qual o tema deve ser utilizado para cada "handle"
 * @author Márcio Ribeiro Gurgel do Amaral
 *
 */
public class AcervoThemeFilter implements Filter{

	
	private static Map<String, Theme> handleToTheme = new HashMap<String, Theme>();
	
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		handleToTheme.put(ConfigurationManager.getProperty("handle.ri"), Theme.RI);
		handleToTheme.put(ConfigurationManager.getProperty("handle.bdtccs"), Theme.BDTCCS);
		handleToTheme.put(ConfigurationManager.getProperty("handle.sabercom"), Theme.SABERCOM);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();
		if(requestURI.contains("/handle/"))
		{
			try 
			{
				Context context = UIUtil.obtainContext(httpServletRequest);
				String pathInfo = httpServletRequest.getPathInfo();
				int indexOf = pathInfo.indexOf("/", 3);
				DSpaceObject dspaceObject = HandleManager.resolveToObject(context, pathInfo.substring(1, indexOf != -1 ? indexOf : pathInfo.length()));
				
				if(dspaceObject != null)
				{
					Theme rootHandle = findRootHandle(dspaceObject);
					httpServletRequest.setAttribute("theme", rootHandle.getCss());
				}
				
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chain.doFilter(httpServletRequest, response);
	}
	
	private Theme findRootHandle(DSpaceObject dspaceObject) 
	{
		Theme rootHandleFound = handleToTheme.get(dspaceObject.getHandle());
		if(rootHandleFound != null)
		{
			/** Handle encontrado **/
			return rootHandleFound;
		}
		else
		{
			try 
			{
				return findRootHandle(dspaceObject.getParentObject());
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void destroy() 
	{
		
	}

}
