package com.sea.ssc;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sea.ssc.service.Ssc;

public class HistoryMain
{
	public static void main(String[] args)
	{

		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Ssc ssc = applicationContext.getBean(Ssc.class);

		for (int i = 1; i < 300; i++)
		{
			try
			{
				String jxbaseurl = "http://gaopinc.com/KaiJiang/JxSsc-";
				String cqbaseurl = "http://gaopinc.com/KaiJiang/Ssc-";
				ssc.catchInfoAndSaveToDB(jxbaseurl + i, "jx");
				ssc.catchInfoAndSaveToDB(cqbaseurl + i, "cq");
			} catch (ClientProtocolException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
	}

}
