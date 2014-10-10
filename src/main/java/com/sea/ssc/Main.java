package com.sea.ssc;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sea.ssc.service.Ssc;

public class Main
{

	public static void main(String[] args) throws IOException, InterruptedException
	{
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Ssc ssc = applicationContext.getBean(Ssc.class);

		while (true)
		{

			try
			{
				ssc.catchInfoAndSaveToDB("http://gaopinc.com/KaiJiang/JxSsc.html", "jx");
				ssc.catchInfoAndSaveToDB("http://gaopinc.com/KaiJiang/ssc.html", "cq");
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
			
			//暂停
			Thread.sleep(1000*60*1);
			
		}

	}

}
