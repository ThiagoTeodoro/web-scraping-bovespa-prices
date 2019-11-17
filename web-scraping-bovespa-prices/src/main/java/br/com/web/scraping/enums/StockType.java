package br.com.web.scraping.enums;

/**
 * Enum to a group Types of Stock Market for Brazil Bovespa.
 * @author Thiago Teodoro Rodrigues <thiago.teodoro.rodrigues@gmail.com>
 *
 */
public enum StockType {
	
	FII("FII"),
	ACTION("ACAO");
	
	public String value;
	
	/**
	 * Constructor default.
	 * @param value
	 */
	StockType(String value) {
		this.value = value;
	}
	
	/**
	 * Resolver for Stock
	 * @param nameStock
	 */
	public static StockType resolver(String nameStock) {
		
		StockType result = null;
		
		if(nameStock.equalsIgnoreCase(StockType.FII.value)) {
			result = StockType.FII;
		}
		
		if(nameStock.equalsIgnoreCase(StockType.ACTION.value)) {
			result = StockType.ACTION;
		}
		
		return result;
	}

}
