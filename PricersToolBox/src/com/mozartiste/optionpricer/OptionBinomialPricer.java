package com.mozartiste.optionpricer;

import com.mozartiste.interestrates.InterestRate;
import com.mozartiste.optionpricer.ENUMS.ExerciseType;
import com.mozartiste.optionpricer.ENUMS.OptionType;

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
    
    //greecs
    private double delta;
    private double gamma;
    private double theta;
    private double vega;
    private double rho;
    
	//Methods
    public InterestRate getRate() {
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
	    InterestRate interestRate=inputs.getR();
		
	    double timestep = expiry/noSteps;
	    double DF = Math.exp(-interestRate.GetRate(1.)*timestep);
	    double temp1 = Math.exp((interestRate.GetRate(1.) + vol * vol)*timestep);
	    double temp2 = 0.5 * (DF + temp1);
	    double up = temp2 + Math.sqrt(temp2*temp2 - 1);
	    double down = 1/ up;
	    double probaUp = (Math.exp(interestRate.GetRate(1.) * timestep) - down)/(up -down)  ;
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
	    
	    double deltaUp = (payOff[0][2]-payOff[1][2])/(stockPrice[0][2]-stockPrice[1][2]);
	    double deltaDown = (payOff[1][2]-payOff[2][2])/(stockPrice[1][2]-stockPrice[2][2]);
	    delta = (deltaUp + deltaDown) /2;
	    gamma = (deltaUp-deltaDown)/((stockPrice[0][2]-stockPrice[2][2])/2);
	    theta = (payOff[1][2]-payOff[0][0])/(365*2*timestep);//time in days
	    
	    long rounded = Math.round(payOff[0][0]*10000);
	    return rounded/10000.0;
	}

	@Override
	public double GetDelta() {
		GetValue();
		return Math.round(delta*10000)/10000.0;
	}

	@Override
	public double GetGamma() {
		GetValue();
		return Math.round(gamma*10000)/10000.0;
	}

	@Override
	public double GetVega() {
		inputs.setVol(inputs.getVol()+0.01);
		double valoUp=GetValue();
		inputs.setVol(inputs.getVol()-0.02);
		double valoDown=GetValue();
		
		inputs.setVol(inputs.getVol()+0.01);
		vega = (valoUp - valoDown)/0.02/100;
		return Math.round(vega*10000)/10000.0;
	}

	@Override
	public double GetTheta() {
		GetValue();
		return Math.round(theta*10000)/10000.0;
	}

	@Override
	public double GetRho() {
		// r + 0.01
		inputs.getR().translate(0.01);
		double valoUp=GetValue();
		// r - 0.01
		inputs.getR().translate(-0.02);
		double valoDown=GetValue();
		//initial value
		inputs.getR().translate(0.01);
		rho = (valoUp - valoDown)/0.02/100;//convert in Bp
		return Math.round(rho*10000)/10000.0;
	}
	
	public InputsOptions getInputs() {
		return inputs;
	}

	public void setInputs(InputsOptions inputs) {
		this.inputs = inputs;
	}



}
