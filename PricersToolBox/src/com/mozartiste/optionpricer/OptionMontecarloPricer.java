package com.mozartiste.optionpricer;

import java.util.Random;

import com.mozartiste.interestrates.InterestRate;
import com.mozartiste.optionpricer.ENUMS.ExerciseType;
import com.mozartiste.optionpricer.ENUMS.OptionType;

public class OptionMontecarloPricer implements IPricer {
	public InputsOptions inputs;
	
	public OptionMontecarloPricer(InputsOptions myinputs) {
		super();
		this.inputs = myinputs;
	}


	@Override
	public double GetDelta() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double GetGamma() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double GetVega() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double GetTheta() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//using the log-normal random walk
	@Override
	public double GetValue() {
		Random rand = new Random();
		OptionType type=inputs.getType();
	    ExerciseType exercise=inputs.getExercise();
	    
		InterestRate interestRate=inputs.getR();
		double spot = inputs.getSpot();
		double strike = inputs.getStrike();
		double vol = inputs.getVol();
		int noSteps=inputs.getNoSteps();
		double expiry=inputs.getExpiry();
	    double r = interestRate.GetRate(.05);
	    
		int numTimeSteps = 100;
		double timestep = expiry / numTimeSteps;
		double sigmaValues = 0;
		
		for (int j = 0; j < noSteps; j++) {
			double asset = spot;
			for (int i = 0; i < numTimeSteps; i++) {
				asset = asset * Math.exp((r - 0.5 * vol) * timestep + Math.sqrt(vol * timestep) * rand.nextGaussian());
			}
			sigmaValues += Math.max(asset - strike, 0);
		}
		double value = sigmaValues / noSteps * Math.exp(-r * expiry);
		
		return value;				
	}

}
