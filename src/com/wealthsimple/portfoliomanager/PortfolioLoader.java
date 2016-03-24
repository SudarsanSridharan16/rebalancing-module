package com.wealthsimple.portfoliomanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PortfolioLoader {
	private String csvFile;
	
	public PortfolioLoader(String file) {
		this.csvFile = file;
	}

	public Portfolio load() {
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		Portfolio portfolio = new Portfolio();

		try {
			br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(csvFile)));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] sec = line.split(cvsSplitBy);
				
				String ticker = (String)sec[0];
				double targetAlloc = Double.parseDouble(sec[1]);
				double currentAlloc = Double.parseDouble(sec[2]);
				int shares = Integer.parseInt(sec[3]);
				double price = Double.parseDouble(sec[4]);
				
				portfolio.addHolding(new Security(ticker, targetAlloc, currentAlloc, shares, price));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return portfolio;
	}

}