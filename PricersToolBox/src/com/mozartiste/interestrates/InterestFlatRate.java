package com.mozartiste.interestrates;

import java.util.Date;

public class InterestFlatRate implements InterestRate{
	public InterestFlatRate(Double flatRate) {
		super();
		this.flatRate = flatRate;
	}

	Double flatRate;
	
	@Override
	public Double GetRate(Double d) {
		return flatRate;
	}

	@Override
	public Double GetRate(Date mydate) {
		return flatRate;
	}
	
	public void SetFlatRate(Double flatrate_) {
		this.flatRate = flatrate_;
	}

	@Override
	public void translate(Double offset) {
		this.flatRate = flatRate+ offset;
		
	}

}
