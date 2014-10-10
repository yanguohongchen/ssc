package com.sea.ssc;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sea.ssc.service.Ssc;

public class CountMain
{

	
	public static void main(String[] args) throws InterruptedException
	{
		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Ssc ssc = applicationContext.getBean(Ssc.class);
		
		while(true)
		{
//			long start = System.currentTimeMillis();
			ssc.queryStageCountInfo(1);
			ssc.queryStageCountInfo(2);
//			long end = System.currentTimeMillis();
			Thread.sleep(1000*60*1);
		}
		
		
	
	}
	
}
