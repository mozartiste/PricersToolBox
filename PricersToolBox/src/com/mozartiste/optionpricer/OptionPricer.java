package com.mozartiste.optionpricer;

import com.mozartiste.optionpricer.ENUMS.ExerciseType;
import com.mozartiste.optionpricer.ENUMS.OptionType;

public class OptionPricer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Exemple : Européan Call
		// Sport 100
		// Strike 90
		// interest rate 0.05
		// volatility 20 %
		// maturity 3 months : 0,25
		// number of simulations 1000
		
		InputsOptions inputs = new InputsOptions(100, 90, .20, 0.05, 500, 0.25, OptionType.CALL, ExerciseType.EUROPEAN);
		IPricer pricer = new OptionBinomialPricer(inputs);
		double a = pricer.GetValue();
		System.out.println(a);

	}

}
