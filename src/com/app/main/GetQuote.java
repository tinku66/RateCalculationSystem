/**
 * 
 */
package com.app.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.app.MarketDataRateComparator.MarketDataRateComparator;
import com.app.dto.*;

public class GetQuote {

	public static final int NoOfMonths = 12;
	public static final int NoOfYears = 3;
	public static Double inputAmount = 0.00;
	public static List<MarketData> marketDataList = new ArrayList<MarketData>();
	public static List<ResultSet> resultSet = new ArrayList<ResultSet>();


	public static void main(String[] args) {

		System.out.println( "Welcome to ZOPA!" );

		String csvFile = "";

		try {
			csvFile = args[0];
			inputAmount = Double.valueOf(args[1]);

			if(inputAmount  < 1000.00 || inputAmount > 15000.00) {
				System.out.println("Please input the amount greater than 1000 and less than 15000");
				System.exit(0);
				return;
			}else {
				if (inputAmount % 100 == 0) {

				}else {
					System.out.println("Please input the amount in the multiples of 100 between 1000 to 15000");
					System.exit(0);
					return;
				}
			}

		} catch (Exception e) {
			System.out.println("Please provide the file Name and the amount");
			System.exit(0);
		}

		String line = "";
		String csvSplitBy = ",";
		int i = 0;
		List<DataHeader> dataHeaderList = new ArrayList<DataHeader>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(csvSplitBy);
				MarketData mkDataObj = new MarketData();
				DataHeader dataHeadObj =   new DataHeader();
				if(i == 0) {
					dataHeadObj.setDataHeader1(data[0]);
					dataHeadObj.setDataHeader2(data[0]);
					dataHeadObj.setDataHeader3(data[0]);
					dataHeaderList.add(dataHeadObj);
					i = i+1;
				}else {
					mkDataObj.setLenderName(data[0]);
					mkDataObj.setInterestRate(Double.parseDouble(data[1]));
					mkDataObj.setAmount(Double.parseDouble(data[2]));
					marketDataList.add(mkDataObj);	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for(MarketData mkd : marketDataList) {
			if(inputAmount<mkd.getAmount()){
				calculate(inputAmount,(double)mkd.getInterestRate(),NoOfMonths,NoOfYears);
			}
		}

		Collections.sort(resultSet, new MarketDataRateComparator());
		for(ResultSet r : resultSet) {
			DecimalFormat f = new DecimalFormat("##.00");
			System.out.println("-------------------------------------------------");
			System.out.println("Requested Amount :" + f.format(inputAmount));
			System.out.println("Rate : " + r.getRateOfInterest());
			System.out.println("Monthly Repayment " + f.format(Double.parseDouble(r.getMonthlyRepayment())));
			System.out.println("Total Repayment : " + f.format((r.getTotalAmount())));
			return;
		}
		//  if market does not have sufficient offers from lenders to satisfy the loan , then the system will throw the below error 
		if(resultSet.isEmpty()) {
			System.out.println("Currently we are not able to provide quote for the requested amount , Please reach out to our customer service for the quote");
			return;
		}

	}

	public static void calculate(Double p, Double r, int n, int t) {
		double amount = p * Math.pow(1 + (r / n), n * t);
		double monthlyRepayment = amount/36;
		double rateInterest = r*100;
		ResultSet rs = new ResultSet();
		rs.setMonthlyRepayment(String.valueOf(monthlyRepayment));
		rs.setRateOfInterest(String.valueOf(rateInterest));
		rs.setTotalAmount(amount);
		resultSet.add(rs);
	}

	public static List<MarketData> getMarketDataList() {
		return marketDataList;
	}

	public static void setMarketDataList(List<MarketData> marketDataList) {
		GetQuote.marketDataList = marketDataList;
	}


}
