package com.vazirna.dimagibot.commands;

import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import picocli.CommandLine.Command;

@Command(
	  name = "lorem-ipsum"
	)
public class GenerateLoremIpsum implements Runnable {
	
	public void run() {
       
		System.out.println("Contacting 3rd party to generate text ...");
		
		try (CloseableHttpClient client = HttpClients.createDefault()) {
	
			   HttpGet request = new HttpGet("https://montanaflynn-lorem-text-generator.p.rapidapi.com/paragraph?count=1&length=3");
			   request.addHeader("x-rapidapi-key", "ecef80588fmsh62566eecb86bc02p1c04f1jsnaca7e87ea6e0");
			   
	
			   CloseableHttpResponse response = client.execute(request);
			   
			   System.out.println(response.getEntity().getContent());
			   
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    }
}


