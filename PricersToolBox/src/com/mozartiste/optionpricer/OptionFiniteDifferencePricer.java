package com.mozartiste.optionpricer;

public class OptionFiniteDifferencePricer implements IPricer{
	public IModelInputs Inputs;
	double timeToMaturity;
	double spot;
	double strike;
	double drift;
	double vol;
	double r;
	
	@Override
	public double GetValue() {
		int noAssetSteps = 100;
		double[] oldValue = new double[noAssetSteps];
		double[] newValue = new double[noAssetSteps];
		double[] delta = new double[noAssetSteps];
		double[] gamma = new double[noAssetSteps];
		double[] asset = new double[noAssetSteps];
		double assetstep = 2 * strike / noAssetSteps;
		int nearestGridPoint = (int) (spot / assetstep);
		double dummy = (spot - nearestGridPoint * assetstep) / assetstep;
		double timestep = assetstep * assetstep
		/ (vol * 4 * strike * strike);
		int noTimeSteps = (int) (timeToMaturity / timestep) + 1;
		timestep = timeToMaturity / noTimeSteps;
		
		for (int i = 0; i < noAssetSteps; i++) {
			asset[i] = i * assetstep;
			oldValue[i] = Math.max(asset[i] - strike, 0);
		}
		
		for (int j = 1; j < noTimeSteps; j++) {
			for (int i = 1; i < noAssetSteps - 1; i++) {
					delta[i] = (oldValue[i + 1] - oldValue[i - 1])
							/ (2 * assetstep);
					gamma[i] = (oldValue[i + 1] - 2 * oldValue[i] + oldValue[i - 1])
							/ (assetstep * assetstep);
					newValue[i] = oldValue[i]
							+ timestep
							* (0.5 * vol * asset[i] * asset[i] * gamma[i] + r
									* asset[i] * delta[i] - r * oldValue[i]);
			}
			newValue[0] = 0;
			newValue[noAssetSteps - 1] = 2 * newValue[noAssetSteps - 2]
					- newValue[noAssetSteps - 3];
			for (int i = 0; i < noAssetSteps; i++) {
				oldValue[i] = newValue[i];
			}
		}
		double value = (1 - dummy) * oldValue[nearestGridPoint] + dummy
		* oldValue[nearestGridPoint + 1];
		return value;
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

}
