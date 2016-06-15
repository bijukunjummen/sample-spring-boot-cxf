package org.bk.weather;

import de.codecentric.namespace.weatherservice.WeatherException;
import de.codecentric.namespace.weatherservice.WeatherService;
import de.codecentric.namespace.weatherservice.datatypes.ProductName;
import de.codecentric.namespace.weatherservice.general.ForecastCustomer;
import de.codecentric.namespace.weatherservice.general.ForecastRequest;
import de.codecentric.namespace.weatherservice.general.ForecastReturn;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration
@WebAppConfiguration
public class WeatherIntegrationTest {
	@Autowired
	private WeatherService weatherServiceIntegrationTestClient;

	@Test
	public void getCityForecastByZIP() throws WeatherException {
		// Given
		ForecastRequest forecastRequest = generateDummyRequest();

		// When
		ForecastReturn forecastReturn = weatherServiceIntegrationTestClient.getCityForecastByZIP(forecastRequest);

		// Then
		assertNotNull(forecastReturn);
		assertEquals(true, forecastReturn.isSuccess());
		assertEquals("Bothell", forecastReturn.getCity());
	}

	public static ForecastRequest generateDummyRequest() {
		ForecastRequest forecastRequest = new ForecastRequest();
		forecastRequest.setZIP("zip");
		forecastRequest.setFlagcolor("blackblue");
		forecastRequest.setProductName(ProductName.FORECAST_BASIC);
		ForecastCustomer customer = new ForecastCustomer();
		customer.setAge(67);
		customer.setContribution(500);
		forecastRequest.setForecastCustomer(customer);
		return forecastRequest;
	}

	@Configuration
	public static class IntegrationTestConfig {

		@Value("${weather.url}")
		private String usageUrl;

		@Bean
		public WeatherService weatherServiceSystemTestClient() {
			JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
			jaxWsProxyFactory.setServiceClass(WeatherService.class);
			jaxWsProxyFactory.setAddress(usageUrl);
			return (WeatherService) jaxWsProxyFactory.create();
		}

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}
	}
}
