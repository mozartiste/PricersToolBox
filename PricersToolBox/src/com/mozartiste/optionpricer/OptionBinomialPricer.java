package com.mozartiste.optionpricer;

import java.text.DecimalFormat;

public class OptionBinomialPricer implements IPricer{
	//constructors
	public OptionBinomialPricer(InputsOptions inputs) {
		super();
		this.setInputs(inputs);
		this.stockPrice = new Double[inputs.getNoSteps() + 1][inputs.getNoSteps() + 1];
		this.payOff = new Double[inputs.getNoSteps() + 1][inputs.getNoSteps() + 1];
		
	}

	// Attributs
	private InputsOptions inputs;
	private Double[][] stockPrice;
    private Double[][] payOff;
    
	//Methods
    public double getRate() {
    		return inputs.getR();
    }
    
	@Override
	public double GetValue() {
		// TODO Auto-generated method stub
		double spot = inputs.getSpot();
		double strike = inputs.getStrike();
		double vol = inputs.getVol();
		int noSteps=inputs.getNoSteps();
		double expiry=inputs.getExpiry();
	    OptionType type=inputs.getType();
	    ExerciseType exercise=inputs.getExercise();
		
	    double timestep = expiry/noSteps;
	    double DF = Math.exp(-getRate()*timestep);
	    double temp1 = Math.exp((getRate() + vol * vol)*timestep);
	    double temp2 = 0.5 * (DF + temp1);
	    double up = temp2 + Math.sqrt(temp2*temp2 - 1);
	    double down = 1/ up;
	    double probaUp = (Math.exp(getRate() * timestep) - down)/(up -down)  ;
	    double probaDown = 1 - probaUp;

	    //stock price tree
		stockPrice[0][0]=spot;
	    for (int n = 1; n <= noSteps; n ++) {
            for (int j = n; j > 0; j--){
            	stockPrice[j][n] = up * stockPrice[j-1][n-1];
            }
        		stockPrice[0][n] = down * stockPrice[0][n-1];
        }
	    
	   //last column set payoffs
		for (int j = 0; j <= noSteps; j++) {
				if(type.equals(OptionType.CALL)) {
					payOff[j][noSteps] = Math.max(this.stockPrice[j][noSteps] - strike, .0);
				}else {
					payOff[j][noSteps] = Math.max(strike - this.stockPrice[j][noSteps], .0);
				}
		}

		
	    //payoff tree in backwardation
	    
	    for (int i = noSteps ; i >= 1; i--) {
            for (int j = 0; j <= i-1; j++) {
	            	if(exercise.equals(ExerciseType.AMERICAN)) { 
	             //American	
	            		if(type.equals(OptionType.CALL)) {
	            				payOff[j][i-1] = Math.max(DF * (probaUp * payOff[j+1][i] + probaDown * payOff[j][i]) ,
	            				Math.max(this.stockPrice[j][i-1] - strike, .0));
	            			
	            		}else 	payOff[j][i-1] = Math.max(DF * (probaUp * payOff[j+1][i] + probaDown * payOff[j][i]) ,
	            				Math.max(strike - this.stockPrice[j][i-1] , .0));
	            	}else { 
	            			//European put and call option
	            				payOff[j][i-1] = DF * (probaUp * payOff[j+1][i] + probaDown * payOff[j][i]);
	            }
            }
        }
	    
	    long rounded = Math.round(payOff[0][0]*10000);
	    return rounded/10000.0;
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

	public InputsOptions getInputs() {
		return inputs;
	}

	public void setInputs(InputsOptions inputs) {
		this.inputs = inputs;
	}

}
