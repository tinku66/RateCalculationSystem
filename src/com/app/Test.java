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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.app.dto.*;

public class Test {

	public static void main(String[] args) {

		String csvFile = "";
		String inputAmount = "";
		try {
			csvFile = args[0];
			inputAmount = args[1]; 
		} catch (Exception e) {
			System.out.println("Please provide the file Name and the amount");
			System.exit(0);
		}

		String line = "";
		String csvSplitBy = ",";

		String lender = "";
		String rate = "";
		String amountAvailable="";
		
		List<MarketData> marketDataList = new ArrayList<MarketData>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			int count = 1;
			while ((line = br.readLine()) != null) {
				
				String[] data = line.split(csvSplitBy);

				MarketData mkDataObj = new MarketData();
				mkDataObj.setLenderName(data[0]);
				mkDataObj.setInterestRate(data[1]);
				mkDataObj.setAmount(Double.parseDouble(data[2]));
				marketDataList.add(mkDataObj);

				lender = data[0];
				rate = data[1];
				amountAvailable = data[2];
				System.out.println("Result " + data[1]);
				
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
			if(mkd.lenderName!=null || !(mkd.equals(""))) {
				
			}
			
		}
		
		
	}
}
