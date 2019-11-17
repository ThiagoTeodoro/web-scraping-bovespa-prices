package br.com.web.scraping.enums;

/**
 * Enum to register the websitea to scraping.
 * @author Thiago Teodoro Rodrigues <thiago.teodoro.rodrigues@gmail.com>
 *
 */
public enum PageForScan {
	
	INFOMONEY("https://www.infomoney.com.br/cotacoes/fundos-imobiliarios-"),
	TORORADAR("https://app.tororadar.com.br/empresa/");
	
	public String value;
	
	/**
	 * Constructor default.
	 * @param value
	 */
	private PageForScan(String value) {
		this.value = value;
	}
}
