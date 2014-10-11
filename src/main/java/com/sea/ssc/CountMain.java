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
		
<<<<<<< HEAD
		while(true)
		{
//			long start = System.currentTimeMillis();
			ssc.queryStageCountInfo(1);
			ssc.queryStageCountInfo(2);
//			long end = System.currentTimeMillis();
			Thread.sleep(1000*60*1);
		}
=======
//		while(true)
//		{
			long start = System.currentTimeMillis();
			ssc.queryStageCountInfo(1);
			ssc.queryStageCountInfo(2);
			long end = System.currentTimeMillis();
			System.out.println(end-start);
//			Thread.sleep(1000*60*1);
//		}
>>>>>>> 9189bfa903b8bae49f7d2d85b2595ded6a784441
		
		
	
	}
	
}
