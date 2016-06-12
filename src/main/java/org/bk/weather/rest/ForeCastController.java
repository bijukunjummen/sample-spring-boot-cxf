package org.bk.weather.rest;

import de.codecentric.namespace.weatherservice.general.ForecastReturn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForeCastController {

	@RequestMapping("/forecast")
	public ForecastReturn getForeCast() {
		ForecastReturn forecastReturn =  new ForecastReturn();
		forecastReturn.setCity("Bothell");
		forecastReturn.setState("WA");
		forecastReturn.setResponseText("Sunny");
		forecastReturn.setSuccess(true);
		return forecastReturn;
	}
}
