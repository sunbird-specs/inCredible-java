package org.incredible.pojos.ob.valuator;

import org.incredible.pojos.ob.exeptions.InvalidDateFormatException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IssuedDateValuator implements IEvaluator {

    private static List<SimpleDateFormat>
            dateFormats = new ArrayList<SimpleDateFormat>() {
        {
            add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            add(new SimpleDateFormat("yyyy-MM-dd"));
        }
    };

    @Override
    public String evaluates(Object inputVal) throws InvalidDateFormatException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Calendar cal = Calendar.getInstance();
        Date date = convertToDate((String) inputVal);
        cal.setTime(date);
        return simpleDateFormat.format(cal.getTime());

    }


    public Date convertToDate(String input) throws InvalidDateFormatException {
        Date date = null;
        if (null == input) {
            throw new InvalidDateFormatException("issued date cannot be null");
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
            }
            if (date != null)
                break;
        }
        if (date == null) {
            throw new InvalidDateFormatException("issued date is not in valid format");

        } else return date;
    }

}
