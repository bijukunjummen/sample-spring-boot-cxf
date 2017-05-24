package org.bk.config;

import de.codecentric.namespace.weatherservice.WeatherService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.bk.calc.endpoint.CalculatorEndpoint;
import org.bk.weather.soap.WeatherServiceEndpoint;
import org.example.ICalculator;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebConfiguration {

	@Bean
	public ServletRegistrationBean cxfServet() {
		return new ServletRegistrationBean(new CXFServlet(), "/soap-api/*");
	}

	@Bean(name= Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public WeatherService weatherService() {
		return new WeatherServiceEndpoint();
	}

	@Bean
	public ICalculator calculatorService() {
		return new CalculatorEndpoint();
	}

	@Bean
	public Endpoint weatherEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), weatherService());
		endpoint.publish("/weather");
		endpoint.setWsdlLocation("Weather1.0.wsdl");
		return endpoint;
	}

	@Bean
	public Endpoint calculatorEndpoitn() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), calculatorService());
		endpoint.publish("/calculator");
		endpoint.setWsdlLocation("Calculator.wsdl");
		return endpoint;
	}

}
