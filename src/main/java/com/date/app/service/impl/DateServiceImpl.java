package com.date.app.service.impl;

import com.date.app.constants.ApiConstants;
import com.date.app.exception.ValidationException;
import com.date.app.service.DateService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Implementation for Date Service.
 */
@Service
@Slf4j
public class DateServiceImpl implements DateService {

	@Value("${api.date.format}")
	private String dateFormat;

	@Value("${api.day.millis}")
	private long dayInMillis;
	
	@Value("${api.date.regex}")
	private String dtRegex;

	/**
     * add number of days to the date.
     *
     * @param date the date
     * @param days the days to add
  */
	@Override
	public String addDate(String date, int days) {
		
		String dateError = ApiConstants.INVALID_DATE_FORMAT + dateFormat;
		
		if(!date.matches(dtRegex)) {
			throw new ValidationException(dateError);
		}

		DateFormat df = new SimpleDateFormat(dateFormat);
		df.setLenient(false);

		try {

			Date currDate = df.parse(date);
			Date newDate = new Date(currDate.getTime() + (dayInMillis * days));
			log.info("DateServiceImpl : addDate newDate :{} ", newDate);
			return df.format(newDate);

		} catch (ParseException ex) {

			log.debug("DateServiceImpl : addDate - Exception {} ", 
					   ApiConstants.INVALID_DATE);

			throw new ValidationException(ApiConstants.INVALID_DATE);
		}
	}

}
