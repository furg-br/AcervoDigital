package org.dspace.app.webui.theme;

/**
 * Registra possibilidades de temas para o acervo
 * @author Márcio Ribeiro Gurgel do Amaral
 *
 */
public enum Theme 
{
	RI("themes/ri/theme.css"),
	BDTCCS("themes/bdtccs/theme.css"),
	SABERCOM("themes/sabercom/theme.css"),
	DEFAULT("bootstrap/bootstrap-theme.min.css");
	
	private String css;

	private Theme(String css) {
		this.css = css;
	}
	
	public String getCss() {
		return css;
	}
	
}
