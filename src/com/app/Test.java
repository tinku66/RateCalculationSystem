/**
 * 
 */
package com.app;

/**
 * @author Tinku & Lavanya
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

import com.app.dto.*;

public class Test {

	public static Double inputAmount = 0.00;

	public static void main(String[] args) {



		String csvFile = "";
		Boolean validAmount;
		try {
			csvFile = args[0];
			inputAmount = Double.valueOf(args[1]);
			//inputAmount Validation

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
		List<MarketData> marketDataList = new ArrayList<MarketData>();
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
					mkDataObj.setInterestRate(data[1]);
					mkDataObj.setAmount(Double.parseDouble(data[2]));
					marketDataList.add(mkDataObj);	
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}


		//OutPut Values
		//RequestedAmount
		//MonthlyRepaymentAmount
		//TotalRepaymentAmount
		//Rate:


		for(MarketData mkd : marketDataList) {

			if(inputAmount<mkd.getAmount()){
				
				calculate(inputAmount,Double.parseDouble(mkd.getInterestRate()),Integer.parseInt("12"),Integer.parseInt("3"));
			}
		}


	}

	public static void calculate(Double p, Double r, int n, int t) {
		double amount = p * Math.pow(1 + (r / n), n * t);
		double interest = amount - p;
		double monthlyRepayment = amount/36;
		double rateInterest = r*100;
		System.out.println("Requested Amount :" + inputAmount);
		System.out.println("Rate : " + rateInterest);
		System.out.println("Monthly Repayment " + monthlyRepayment);
		System.out.println("Total Repayment : " + amount);
	}



}
