package com.wealthsimple.portfoliomanager;

public class Trade {
	
	// Postive to long, negative to short
	private int numberOfShares;
	private double tradePrice;
	private Security security;
	
	public Trade(int number_of_shares, double trade_price, Security security) {
		this.numberOfShares = number_of_shares;
		this.tradePrice = trade_price;
		this.security = security;
	}
	
	//Return total trade value
	public double getTransactionValue() {
		return tradePrice * numberOfShares;
	}
	
	// Function returns the assets market value after this trade has been executed
	public double getNewMarketValue() {
		return security.getMarketValue() + this.getTransactionValue();
	}
	
	// Returns a string to provide an action to execute
	public String getAction() {
		if(numberOfShares > 0) {
			return "buy "+numberOfShares+" shares of "+security.getTickerSymbol();
		} else {
			return "sell "+ (-numberOfShares)+" shares of "+security.getTickerSymbol();
		}
	}
	
	// Accessor methods
	
	public int getNumberOfShares() { return numberOfShares; }
	public double getTradePrice(){ return tradePrice; }
	public Security getSecurity() { return security; }
	
	public void setNumberOfShares(int number_of_shares) { this.numberOfShares = number_of_shares; }
	public void setTradePrice(double tradePrice) { this.tradePrice = tradePrice; }
	public void setSecurity(Security security) { this.security = security; }
}
