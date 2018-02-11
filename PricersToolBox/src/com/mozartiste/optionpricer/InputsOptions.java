package com.mozartiste.optionpricer;

public class InputsOptions implements IModelInputs{



	private double spot;
	private double strike;
	private double vol;
	private double r;
	private int noSteps;
	private double expiry;
	private OptionType type;
    private ExerciseType exercise;
    
	//constructor
	public InputsOptions(double spot, double strike, double vol, double r, int noSteps, double expiry,
			OptionType type, ExerciseType exercise) {
		super();
		this.spot = spot;
		this.strike = strike;
		this.vol = vol;
		this.r = r;
		this.noSteps = noSteps;
		this.expiry = expiry;
		this.type = type;
		this.exercise = exercise;
	}
	
	//getters and setters
	public double getSpot() {
		return spot;
	}
	public void setAssset(double assset) {
		this.spot = assset;
	}
	public double getStrike() {
		return strike;
	}
	public void setStrike(double strike) {
		this.strike = strike;
	}
	public double getVol() {
		return vol;
	}
	public void setVol(double vol) {
		this.vol = vol;
	}
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	public int getNoSteps() {
		return noSteps;
	}
	public void setNoSteps(int noSteps) {
		this.noSteps = noSteps;
	}
	public double getExpiry() {
		return expiry;
	}
	public void setExpiry(double expiry) {
		this.expiry = expiry;
	}

	public OptionType getType() {
		return type;
	}

	public void setType(OptionType type) {
		this.type = type;
	}

	public ExerciseType getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseType exercise) {
		this.exercise = exercise;
	}

}
