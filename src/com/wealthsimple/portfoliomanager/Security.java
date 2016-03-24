package com.wealthsimple.portfoliomanager;

public class Security {
	
	private String tickerSymbol;
	private double targetAllocation;
	private double currentAllocation;
	private int sharesOwned;
	private double sharePrice;
	
	public Security(String tickerSymbol, double targetAllocation, double currentAllocation, int sharesOwned, double sharePrice) {
		this.tickerSymbol = tickerSymbol;
		this.targetAllocation = targetAllocation;
		this.currentAllocation = currentAllocation;
		this.sharesOwned = sharesOwned;
		this.sharePrice = sharePrice;
	}
	
	public double getMarketValue(){
		return sharesOwned * sharePrice;
	}
	
	// Apply trade to a security holding
	public void applyTrade(Trade trade) {
		this.sharesOwned += trade.getNumberOfShares();
	}
	
	// Accessor Methods
	public String getTickerSymbol() { return tickerSymbol; }
	
	public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }

	public double getTargetAllocation(){ return targetAllocation; }
	
	public void setTargetAllocation(double targetAllocation) { this.targetAllocation = targetAllocation; }
	
	public double getCurrentAllocation() { return currentAllocation; }
	
	public void setCurrentAllocation(double currentAllocation) { this.currentAllocation = currentAllocation; }
	
	public int getSharesOwned() { return sharesOwned; }
	
	public void setSharesOwned(int sharesOwned) { this.sharesOwned = sharesOwned; }
	
	public double getSharePrice() { return sharePrice; }
	
	public void setSharePrice(double sharePrice) { this.sharePrice = sharePrice; }
	
}
