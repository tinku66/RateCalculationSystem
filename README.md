# RateCalculationSystem
Rate Calculation System - CSV File Read and Calculate &amp; Sort Data


### Sample code for calulating results:

```markdown
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
```

