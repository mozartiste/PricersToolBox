package com.mozartiste.interestrates;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.mozartiste.utils.DateUtil;

public class InterestRateCurve implements InterestRate{
	
	public InterestRateCurve(Date refDate, InterpolationMethod interpolationMethod,
			ExtrapolationMethod extrapolationMethod) {
		super();
		this.refDate = refDate;
		this.interpolationMethod = interpolationMethod;
		this.extrapolationMethod = extrapolationMethod;
	}

	private TreeMap<Double, Double> ratecurve = new TreeMap<>();
	private Date refDate =new Date();//date reference for the interest rate curve
	private InterpolationMethod	interpolationMethod	= InterpolationMethod.LINEAR;
	private ExtrapolationMethod	extrapolationMethod = ExtrapolationMethod.CONSTANT;

	public enum InterpolationMethod {
		LINEAR,
		CUBIC_SPLINE,
	}
	
	public enum ExtrapolationMethod {
		//  using linear interpolation of the previous interval
		DEFAULT,
		CONSTANT,
		LINEAR
	}
	
	@Override
	public Double GetRate(Date d){
		Double nbDays= DateUtil.nbDays(refDate, d);
		Double rval;
		// TODO Add spline interpolation
		if (interpolationMethod.equals(InterpolationMethod.LINEAR)) rval = LinearInterpolation(nbDays);
		else rval =.0;
		
		return rval;
	}
	
	@Override
	public Double GetRate(Double d){
		double rval;
		// TODO Add spline interpolation
		if (interpolationMethod.equals(InterpolationMethod.LINEAR)) rval = LinearInterpolation(d);
		else rval =0;
		return rval;
	}
	
	public void Add(Double d, Double rate){
		 ratecurve.put(d, rate) ;
	}
	
	public void Add(Date d, Double rate){
		Double nbDays= DateUtil.nbDays(refDate, d);
		 ratecurve.put(nbDays, rate) ;
	}
	
	public double LinearInterpolation(Double x) {
		Map.Entry<Double,Double> e1, e2;
		double x1, x2;
		double y1, y2;
		
		if (Double.isNaN(x))
			return Double.NaN;
		
		e1 = ratecurve.floorEntry(x);
		
		if (e1 == null) {
			// x smaller than any value in the TreeMap
			e1 = ratecurve.firstEntry();
			if (e1 == null) {
				return Double.NaN;
			}
			e2 = ratecurve.higherEntry(e1.getKey());
			if (e2 == null) {
				// only one value in the TreeMap
				return e1.getValue();
			}
		} else {
			
			e2 = ratecurve.higherEntry(e1.getKey());
			if (e2 == null) {
				// x larger than any value in the set
				e2 = e1;
				e1 = ratecurve.lowerEntry(e2.getKey());
				if (e1 == null) {
					// only one value in the set
					return e2.getValue();
				}
			}
		}
		
		x1 = e1.getKey();
		x2 = e2.getKey();
		y1 = e1.getValue();
		y2 = e2.getValue();
		
		return (x - x1)/(x2-x1) * (y2-y1) + y1;
	}
	
}