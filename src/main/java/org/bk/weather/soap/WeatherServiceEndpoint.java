package org.bk.weather.soap;

import de.codecentric.namespace.weatherservice.WeatherException;
import de.codecentric.namespace.weatherservice.WeatherService;
import de.codecentric.namespace.weatherservice.general.ForecastRequest;
import de.codecentric.namespace.weatherservice.general.ForecastReturn;
import de.codecentric.namespace.weatherservice.general.WeatherInformationReturn;
import de.codecentric.namespace.weatherservice.general.WeatherReturn;

public class WeatherServiceEndpoint implements WeatherService {

    @Override
    public ForecastReturn getCityForecastByZIP(ForecastRequest forecastRequest)
            throws WeatherException {
        ForecastReturn forecastReturn =  new ForecastReturn();
        forecastReturn.setCity("Bothell");
        forecastReturn.setState("WA");
        forecastReturn.setResponseText("Sunny");
        forecastReturn.setSuccess(true);
        return forecastReturn;
    }
    
    @Override
    public WeatherInformationReturn getWeatherInformation(String zip)
            throws WeatherException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WeatherReturn getCityWeatherByZIP(ForecastRequest forecastRequest)
            throws WeatherException {
        // TODO Auto-generated method stub
        return null;
    }

}