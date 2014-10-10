package com.sea.ssc;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sea.ssc.service.Person;

public class PerMain
{
	public static void main(String[] args)
	{
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person person = applicationContext.getBean(Person.class);
		person.virtualPersonBuy();
//		person.bugCp(1, 2, 1, 1,"141011-062", "2");
	}
	
	
	
}
