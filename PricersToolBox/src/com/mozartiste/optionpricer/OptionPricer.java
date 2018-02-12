package com.mozartiste.optionpricer;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.event.PrintJobAttributeListener;

import com.mozartiste.interestrates.CSVParser;
import com.mozartiste.interestrates.IParser;
import com.mozartiste.interestrates.InterestRateCurve;
import com.mozartiste.interestrates.InterestRateCurve.ExtrapolationMethod;
import com.mozartiste.interestrates.InterestRateCurve.InterpolationMethod;

public class OptionPricer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM");
		Date curDate;
		curDate = df.parse("2010-01-01");
		InterestRateCurve myInterestCurve = new InterestRateCurve(curDate,InterpolationMethod.LINEAR,ExtrapolationMethod.CONSTANT);
		IParser<InterestRateCurve> parser = new CSVParser(myInterestCurve);
		InterestRateCurve interestRate=parser.parse("/Users/mehdi/git/PricersToolBox/PricersToolBox/AAA.csv");
		Double rate=interestRate.GetRate(12);
		int a =2;
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
