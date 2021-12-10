package com.vazirna.dimagibot.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteTime {
	
	private String datetime;
	
    

    public RemoteTime(@JsonProperty("datetime") String datetime) {
        this.datetime = datetime;
       
    }
    
    
    public String getDatetime() {
		return datetime;
	}


	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	@Override
    public String toString() {
    	return datetime.toString();
    	
    }

}
