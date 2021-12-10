package com.vazirna.dimagibot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import picocli.CommandLine.Command;

@Command(
  name = "history",
  description = "Lists the commands history" 
)
public class CommandHistory implements Runnable {
    
    public void run() {
    	try {
    		File historyFile = new File("history.txt");
    		Scanner historyReader = new Scanner(historyFile);
    		while (historyReader.hasNextLine()) {
    			String line = historyReader.nextLine();
    			System.out.println(line);
    		}
    		historyReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
    	
    }

}
