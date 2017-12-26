/**
 * 
 */
/**
 * @author Tamilarasu
 *
 */

package com.app.dto;

public class MarketData{

	public String lenderName;
	public Double interestRate;
	public double amount;

	public MarketData() {
		super();
	}

	public MarketData(String lenderName, Double interestRate,double amount) {

		this.lenderName = lenderName;
		this.interestRate = interestRate;
		this.amount = amount;
	}

	public String getLenderName() {
		return lenderName;
	}
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}

