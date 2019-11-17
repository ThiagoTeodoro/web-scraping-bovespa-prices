package br.com.web.scraping;

import java.util.Scanner;

import br.com.web.scraping.enums.StockType;
import br.com.web.scraping.service.WebCrawlerService;

public class Main {

	/**
	 * Classe main para chamadas pontuais de simulação
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		WebCrawlerService webCrawlerService = new WebCrawlerService();
		System.out.println("Projeto Web Scraping");
		System.out.println("Enter 'Exit' to exit the program.");
		Scanner scanner = new Scanner(System.in); 
		
		while(true){
			
			System.out.println("Por favor digite o código do Título : ");
			String nameStock = scanner.nextLine();
			System.out.println("Esse título é um FII ou uma ACAO? : ");
			String typeStock = scanner.nextLine();
			
			if(nameStock.equalsIgnoreCase("Exit") || typeStock.equalsIgnoreCase("Exit")) {
				break;
			}
			
			webCrawlerService.getPrice(nameStock, StockType.resolver(typeStock));					
		}
		
	}

}
