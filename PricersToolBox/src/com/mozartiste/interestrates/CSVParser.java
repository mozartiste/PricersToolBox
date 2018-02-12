package com.mozartiste.interestrates;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVParser implements IParser<InterestRateCurve>{
	private InterestRateCurve RateCurve ;
	private String DateFormat;

	public CSVParser(InterestRateCurve rateCurve, String dateFormat) {
		super();
		RateCurve = rateCurve;
		DateFormat = dateFormat;
	}

	public InterestRateCurve parse(String inputFile, String cvsSplitBy, String DateFormat) {
        BufferedReader br = null;
        String line = "";

        try {

            br = new BufferedReader(new FileReader(inputFile));
            while ((line = br.readLine()) != null) {            		
                String[] row_i = line.split(cvsSplitBy);
                SimpleDateFormat df = new SimpleDateFormat(DateFormat);
                Date d = df.parse(row_i[0]);
                double value = Double.parseDouble(row_i[1]);
                RateCurve.Add(d, value);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		} finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return RateCurve;
	}

	@Override
	public InterestRateCurve parse(String inputFile) {
		// default separator as comma
		InterestRateCurve rval = this.parse(inputFile,",", this.DateFormat) ;
		return rval;
	}

}
