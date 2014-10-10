package com.sea.ssc;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sea.ssc.service.Ssc;

public class CountMain
{

	
	public static void main(String[] args)
	{

		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Ssc ssc = applicationContext.getBean(Ssc.class);
		ssc.queryStageCountInfo();
	
	}
	
}
