package com.sea.ssc;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sea.ssc.service.Cps;

public class CpsMain
{
	
	public static void main(String[] args) throws InterruptedException
	{
		while(true){
			
			PropertyConfigurator.configure("src/main/resources/log4j.properties");
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			Cps cps = applicationContext.getBean(Cps.class);
			cps.priizes();
			Thread.sleep(1000*30*1);
		}
	}
	
}
