package com.date.app.controller;

import com.date.app.constants.ApiConstants;
import com.date.app.exception.ValidationException;
import com.date.app.service.DateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to handle date.
 */
@RestController
@RequestMapping("/v1/api/date")
@Slf4j
public class DateController {

	@Autowired
	private DateService dateService;

	/**
	 * Return new date.
	 *
	 * @param date the date
	 * @param days the days to add
	 * @return new date with added days.
	 */
	@ApiOperation(value = "Return new date with added days",
			      response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200") })
	@GetMapping("/add")
	public ResponseEntity<String> addDate(
			@RequestParam(required = true) String date,
			@RequestParam(required = true) int days) {
		if (days < 0) {
			log.debug(ApiConstants.INVALID_DAYS+": addDate days:{} : ", days);
			throw new ValidationException(ApiConstants.INVALID_DAYS);
		} else if (date.isBlank()) {
			log.debug(ApiConstants.BLANK_DATE_FIELD + ": addDate date:{} : ", date);
			throw new ValidationException(ApiConstants.BLANK_DATE_FIELD);
		} else {
			log.info("DateController : addDate date:{} ", date);
			return ResponseEntity.ok(ApiConstants.NEW_DT_MSG + dateService
					             .addDate(date, days));
		}
	}

}
