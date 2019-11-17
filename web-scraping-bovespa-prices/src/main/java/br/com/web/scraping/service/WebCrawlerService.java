package br.com.web.scraping.service;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.web.scraping.enums.PageForScan;
import br.com.web.scraping.enums.StockType;
import br.com.web.scraping.module.to.Stock;
import lombok.extern.slf4j.Slf4j;

/**
 * Class of Service for Web Crawler.
 * @author Thiago Teodoro Rodrigues <thiago.teodoro.rodrigues@gmail.com>
 *
 */
@Slf4j
public class WebCrawlerService {

	/**
	 * Method to get the price of a stock.
	 * @param stockName
	 * @param stockType
	 * @return
	 */
	public Stock getPrice(String stockName, StockType stockType) {	
		
		Stock result = null;
		
		if(stockType == null) {
			log.info("The type of stock merket is inválid.");
			return null;
		}
		
		if(stockType.equals(StockType.FII)) {
			
			String priceBr = getPriceForFii(stockName);
			Double priceStock = brazilianCoinToDouble(priceBr);		
			result = new Stock(stockName,stockType, priceStock, new Date(System.currentTimeMillis()));			
		}
		
		if(stockType.equals(StockType.ACTION)) {
			String priceBr = getPriceForAction(stockName);
			Double priceStock = brazilianCoinToDouble(priceBr);		
			result = new Stock(stockName,stockType, priceStock, new Date(System.currentTimeMillis()));
		}
		
		log.info("Found stock price : " + result);		
		return result;
	}
	
	/**
	 * Method to get the price of a fii
	 * @param fiiName
	 * @return
	 */
	private String getPriceForFii(String fiiName) {
		
		String result = "";	
		
		try {
			
			Document pageContent = Jsoup.connect(PageForScan.INFOMONEY.value + fiiName + "/").get();
			Element stockPrice = pageContent.getElementsByClass("value").first();
			Element price = stockPrice.getElementsByTag("p").first();
			result = price.html();			
		} catch (Exception e) {

			log.error("A Exception ocurred when the software try fetch the page for get the Data in getPriceForFii(). Exception : ", e);
		}
		
		return result;
	}
	
	/**
	 * Method to get the price of a action
	 * @param actionName
	 * @return
	 */
	private String getPriceForAction(String actionName) {
		
		String result = "";
		
		try {
			
			Document pageContent = Jsoup.connect(PageForScan.TORORADAR.value + actionName).get();
			Element checkNameAction = pageContent.getElementsByClass("empresa-nome hidden-xs").first();			
			Element nameFoundInWebPage = checkNameAction.getElementsByTag("h2").first();
			
			if(nameFoundInWebPage.html().equalsIgnoreCase(actionName)) {
								
				Element price = pageContent.getElementsByClass("variacao").first();
				result = price.html();	
			} else {
				
				result = "0";
			}									
		} catch (Exception e) {

			log.error("A Exception ocurred when the software try fetch the page for get the Data in getPriceForAction(). Exception : ", e);
		}
		
		return result;
	}
	
	/**
	 * Transforme brazilian coin to Double number.
	 * @param coin
	 */
	private Double brazilianCoinToDouble(String coin) {
		
		try {
			
			if(coin.isEmpty()) {
				return new Double(0.0);
			}
			
			String result = coin;			
			result = result.replace("R$", "");		
			result = result.replace(",", ".");			
			return new Double(result);
		} catch (Exception e) {
			
			log.error("A Exception ocurred when the software try convert brazilian coin in Double. Exception : ", e);
			return new Double(0.0);
		}
	}
	
}
