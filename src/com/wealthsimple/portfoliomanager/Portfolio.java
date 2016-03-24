package com.wealthsimple.portfoliomanager;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
	
	private List<Security> holdings = new ArrayList<Security>();
	private double cash = 0;
	
	public void addHolding(Security security) {
		holdings.add(security);
	}
	
	public double getMarketValue() {
		double marketValue = cash;
		
		for(int i = 0; i < holdings.size(); i++){
			marketValue += holdings.get(i).getMarketValue();
		}
		
		return marketValue;
	}
	
	public void printPortfolio() {
		
		System.out.format("%10s%16s%10s%10s%10s%10s\n", "Ticker", "# of Shares", "Price", "Total", "Target", "Diff");
		
		for(int i = 0; i < holdings.size(); i++){
			Security sec = holdings.get(i);
			double targetValue = sec.getTargetAllocation()*this.getMarketValue();
			System.out.format("%10s%16d%10.2f%10.2f%10.2f%10.2f\n", sec.getTickerSymbol(), sec.getSharesOwned(), sec.getSharePrice(), sec.getMarketValue(), targetValue, sec.getMarketValue()-targetValue);
		}
		
		System.out.format("%10s%36.2f%10.2f%10.2f\n", "Cash", cash, 0.0, cash-0);
		
	}
	
	// Function to apply a list of trades to the portfolio
	public void applyTrades(List<Trade> trades) {
		for(int i = 0; i < trades.size(); i++) {
			Trade trade = trades.get(i);
			int index = holdings.indexOf(trade.getSecurity());
			cash -= trade.getTransactionValue();
			if(index >= 0){
				// Already in portfolio
				holdings.get(index).applyTrade(trade);
			} else {
				// Not in Portfolio
			}
		}
	}
	
	// Function to rebalance portfolio using a floor round
	// Trades are not applied right away
	// Function returns a list of trades to be executed
	public List<Trade> rebalance() {
		
		List<Trade> trades = new ArrayList<Trade>();
		double total = 0;
		
		// Determine the number of shares to trade
		for(int i = 0; i < holdings.size(); i++){
			Security sec = holdings.get(i);
			
			double targetValue = sec.getTargetAllocation()*this.getMarketValue();
			double diff = targetValue - sec.getMarketValue();
			double targetTrade = diff/sec.getSharePrice();
			int roundTrade = (int) Math.floor(targetTrade);
			
			// Add trade to list
			Trade newTrade = new Trade(roundTrade, sec.getSharePrice(), sec);
			trades.add(newTrade);
			total += newTrade.getNewMarketValue();

		}
		
		// Allocate excess_cash if it helps to get closer to target
		double excess_cash = this.getMarketValue() - total;
		for(int i = 0; i < trades.size(); i++){
			Trade newTrade = trades.get(i);
			double newAlloc = newTrade.getNewMarketValue()/this.getMarketValue();
			double additionalTradeAlloc = (newTrade.getNewMarketValue()+newTrade.getTradePrice())/this.getMarketValue();
			double newDiff = Math.abs(newTrade.getSecurity().getTargetAllocation() - newAlloc);
			double additionalDiff = Math.abs(newTrade.getSecurity().getTargetAllocation() - additionalTradeAlloc);
			if(additionalDiff < newDiff && newTrade.getTradePrice() <=excess_cash){
				excess_cash -= newTrade.getTradePrice();
				newTrade.setNumberOfShares(newTrade.getNumberOfShares()+1);
			}
		}

		return trades;
	}
	
	// Function to rebalance portfolio using a round
	// This will only work if the portfolio has extra cash to cover a spill over
	// Trades are not applied right away
	// Function returns a list of trades to be executed
	public List<Trade> rebalanceRound() {
		
		List<Trade> trades = new ArrayList<Trade>();
		
		for(int i = 0; i < holdings.size(); i++){
			Security sec = holdings.get(i);
			
			double targetValue = sec.getTargetAllocation()*this.getMarketValue();
			double diff = targetValue - sec.getMarketValue();
			double targetTrade = diff/sec.getSharePrice();
			int roundTrade = (int) Math.round(targetTrade);
			
			if(roundTrade != 0) {
				Trade newTrade = new Trade(roundTrade, sec.getSharePrice(), sec);
				trades.add(newTrade);
			}
			
		}

		return trades;
	}
	
	
	
	
	// Accessor Methods
	public List<Security> getHoldings() { return holdings; }

	public void setHoldings(List<Security> holdings) { this.holdings = holdings; }

	public double getCash() { return cash; }

	public void setCash(double cash) { this.cash = cash; }

	 
}
