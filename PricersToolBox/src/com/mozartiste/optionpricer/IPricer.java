package com.mozartiste.optionpricer;

public interface IPricer {
	//

	
	//return the Option Price
	public double GetValue();
	
	//Greecs
	public double GetDelta();
	public double GetGamma();
	public double GetVega();
	public double GetTheta();
	public double GetRho();
}
