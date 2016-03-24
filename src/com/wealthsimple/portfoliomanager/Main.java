package com.wealthsimple.portfoliomanager;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		// Load in portfolio from headerless csv stored under src
		PortfolioLoader loader = new PortfolioLoader("portfolio.csv");
		Portfolio portfolio = loader.load();
		
		// Rebalancing		
		System.out.println("Trades to Rebalance");
		
		// Print out trades to be executed
		List<Trade> trades = portfolio.rebalance();
		for(int i = 0; i<trades.size(); i++) {
			System.out.println(trades.get(i).getAction());
		}
		System.out.println("");
		
		// Apply trades to the portfolio
		portfolio.applyTrades(trades);
		
		// Print out rebalanced portfolio
		System.out.println("Future Portfolio");
		portfolio.printPortfolio();
		System.out.println("Total: "+portfolio.getMarketValue());
		System.out.println("\n");
		
		
	}

}
