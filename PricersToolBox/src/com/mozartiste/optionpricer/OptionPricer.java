package com.mozartiste.optionpricer;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.event.PrintJobAttributeListener;

import com.mozartiste.interestrates.CSVParser;
import com.mozartiste.interestrates.IParser;
import com.mozartiste.interestrates.InterestFlatRate;
import com.mozartiste.interestrates.InterestRate;
import com.mozartiste.interestrates.InterestRateCurve;
import com.mozartiste.interestrates.InterestRateCurve.ExtrapolationMethod;
import com.mozartiste.interestrates.InterestRateCurve.InterpolationMethod;
import com.mozartiste.optionpricer.ENUMS.ExerciseType;
import com.mozartiste.optionpricer.ENUMS.OptionType;

public class OptionPricer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		String dateFormat="yyyy-dd-MM";
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		Date curDate;
		curDate = df.parse("2010-01-01");
		InterestRateCurve myInterestCurve = new InterestRateCurve(curDate,InterpolationMethod.LINEAR,ExtrapolationMethod.CONSTANT);
		IParser<InterestRateCurve> parser = new CSVParser(myInterestCurve,dateFormat);
		InterestRateCurve interestRate=parser.parse("/Users/mehdi/git/PricersToolBox/PricersToolBox/AAA.csv");
		Double rate=interestRate.GetRate(12.);
		
		InterestRate interestRateFlat = new InterestFlatRate(0.05);
		InputsOptions inputs = new InputsOptions(new Integer(100), new Integer(90), new Double(.20), interestRateFlat, new Integer(200), new Double(0.25), OptionType.CALL, ExerciseType.EUROPEAN);
		OptionMontecarloPricer pricerMontecarlo = new OptionMontecarloPricer(inputs);
		OptionBinomialPricer priceBinomial = new OptionBinomialPricer(inputs);
		double ValueMC = pricerMontecarlo.GetValue();
		double ValueBiomial = priceBinomial.GetValue();
		double vega=priceBinomial.GetVega();
		double rho=priceBinomial.GetRho();
		double test =11.6702;
		
		

		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
