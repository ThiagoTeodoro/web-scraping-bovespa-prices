package br.com.web.scraping.module.to;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import br.com.web.scraping.enums.StockType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Transfer Object to oganized the return price for Stock's
 * @author Thiago Teodoro Rodrigues <thiago.teodoro.rodrigues@gmail.com>
 *
 */
@Data
@AllArgsConstructor
public class Stock {

	private String nameStock;	
	private StockType stockType;
	private Double priceStock;	
	private Date dateOfPrice;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy : HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		return String.format("%s;%s;%s;%s", this.nameStock, this.stockType.value, String.valueOf(priceStock), sdf.format(this.dateOfPrice));
	}
}
