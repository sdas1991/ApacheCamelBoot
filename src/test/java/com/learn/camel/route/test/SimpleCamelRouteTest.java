package com.learn.camel.route.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("dev")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class SimpleCamelRouteTest {
	
	@Autowired
	ProducerTemplate produce;
	
	@Autowired
	Environment env;
	
	@Test
	public void moveFile() throws InterruptedException {
		String message="type,sku#,itemdescripton,price\r\n" + 
				"ADD,100,SamsungTV,500\r\n" + 
				"ADD,200,LG,400";
		String fileName="FileText.txt";
		produce.sendBodyAndHeader(env.getProperty("fromRoute"), message, Exchange.FILE_NAME, fileName);
		Thread.sleep(3000);
		File dest=new File("data/output/"+fileName);
		assertTrue(dest.exists());
		
	}
	

}
