package com.date.app.service;

/**
 * Date service.
 */
public interface DateService {

  /**
     * add number of days to the date.
     *
     * @param date the date
     * @param days the days to add
  */
  String addDate(String date, int days);

}
