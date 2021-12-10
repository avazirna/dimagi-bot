package com.vazirna.dimagibot.commands;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;

import com.vazirna.dimagibot.model.RemoteTime;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
  name = "time",
  description = "Returns the time of the local enviornment and from remote locations"
)
public class CurrentTime implements Runnable {
	@Option(names = {"-C", "--c"})
    private String city; 
    
    public void run() {
    	
    	Scanner sc = new Scanner(System.in);
    	if (city !=null) {
    		int areaOption = -1;
    		String area = "";
    		do {
    			System.out.println("In which area is the city located?");
    			System.out.println("1 - Africa\n2 - Europe\n3 - Asia");
    			areaOption = sc.nextInt();
    			
    			switch (areaOption) {
					case 1:
						area = "Africa";
						break;
					case 2:
						area = "Europe";
						break;
					case 3:
						area = "Asia";
						break;
					default:
						System.out.println("Invalid option, try again!");
						break;
				}
    		}while(areaOption <1 || areaOption >3);
    		
    		System.out.println("Contacting 3rd party, please bear with me ...");
    		String remoteTime = getTimeOfRemoteCity(area, city);
    		if (remoteTime != null)
    			System.out.println(city+" time is:"+remoteTime.toString());
    		else
    			System.out.println("Invalid city, try again!");
    		
    	}
    	else
    		System.out.println("Current system Time is:"+Calendar.getInstance().getTimeInMillis());
    }

	private String getTimeOfRemoteCity(String area, String city) {
		ObjectMapper mapper = new ObjectMapper();
		
		try (CloseableHttpClient client = HttpClients.createDefault()) {
	
			   HttpGet request = new HttpGet("http://worldtimeapi.org/api/timezone/"+area+"/"+city);
			   RemoteTime response = client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), RemoteTime.class));
			   
			   return response.getDatetime();
			   
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		return null;
	}

}
