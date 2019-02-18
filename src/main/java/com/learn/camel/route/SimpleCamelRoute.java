package com.learn.camel.route;


import org.apache.camel.builder.RouteBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SimpleCamelRoute extends RouteBuilder {
	
	
	@Autowired
	Environment env;
	
	@Override
	public void configure() throws Exception{
		
		
		
		from("{{startroute}}")
			.log("Timer invoked and bodys"+env.getProperty("message"))
			.pollEnrich("{{fromRoute}}")
			.to("{{toRoute}}");
	}
	

}
