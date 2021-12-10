package com.vazirna.dimagibot.commands;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;

import picocli.CommandLine.Command;

@Command(
	  name = "lorem-ipsum",
	  description ="Generates Lorem Ipsum text"
	)
public class GenerateLoremIpsum implements Runnable {
	
	@SuppressWarnings("unchecked")
	public void run() {
       
		System.out.println("Contacting 3rd party to generate text ...");
		ObjectMapper mapper = new ObjectMapper();
		
		try (CloseableHttpClient client = HttpClients.createDefault()) {
	
			   HttpGet request = new HttpGet("https://baconipsum.com/api/?type=meat-and-filler");
			   Object response = client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Object.class));
			   
			   System.out.println(((List<String>)response).get(0));
			   
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    }
}


