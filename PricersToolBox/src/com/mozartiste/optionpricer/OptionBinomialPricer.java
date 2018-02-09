package com.mozartiste.optionpricer;


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
		double assset = inputs.getAssset();
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
	    double up = temp2 * Math.sqrt(temp2*temp2 - 1);
	    double down = 1/ up;
	    double probaUp = (Math.exp(getRate() * timestep) - down)/(up -down)  ;
	    double probaDown = 1 - probaUp;

	    //stock price tree
	    for (int j = 0; j <= noSteps; j ++) {
            for (int i = 0; i <= j; i++){
            	stockPrice[i][j] = assset * Math.pow(up, j-i) * Math.pow(down, i);
            }
        }
	    
	    //payoff tree in backwardation
	    for (int i = 0; i <= noSteps; i++) {
            if(type.equals(OptionType.CALL)) {
            	payOff[i][noSteps] = Math.max(this.stockPrice[i][noSteps] - strike, 0.0);
            }
            else payOff[i][noSteps] = Math.max(strike - this.stockPrice[i][noSteps], 0.0);
        }
	    
	    for (int j = noSteps - 1; j >= 0; j--) {
            for (int i = 0; i <= j; i++) {
	            	if(exercise.equals(ExerciseType.AMERICAN)) { 
	             //American	
	            		if(type.equals(OptionType.CALL)) {
	            			payOff[i][j] = Math.max(   DF * (probaUp * payOff[i][j + 1] + probaDown * payOff[i + 1][j + 1]) ,Math.max(this.stockPrice[i][noSteps] - strike, 0.0));
	            		}else payOff[i][j] = Math.max(   DF * (probaUp * payOff[i][j + 1] + probaDown * payOff[i + 1][j + 1]) ,Math.max(strike - this.stockPrice[i][noSteps], 0.0));
	            	}else { 
	             //European
	                payOff[i][j] = DF * (probaUp * payOff[i][j + 1] + probaDown * payOff[i + 1][j + 1]);
	            }
            }
        }
	    
		 return payOff[0][0];
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
