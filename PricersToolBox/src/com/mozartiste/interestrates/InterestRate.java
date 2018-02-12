package com.mozartiste.interestrates;

import java.util.Date;

public interface InterestRate {
	public Double GetRate(Double d);
	public Double GetRate(Date mydate);

}
