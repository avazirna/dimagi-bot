package com.vazirna.dimagibot.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
  name = "dimagibot",
  description = "Bot that offers very usefull information to Dimagi employees",
  subcommands = {
	CommandHistory.class,
	CurrentTime.class,
	GenerateLoremIpsum.class
  }
)
public class DimagiBotCommand implements Runnable {
	public static List<String> executedCommands = new ArrayList<String>();
	
    public static void main(String[] args) throws IOException {
        CommandLine.run(new DimagiBotCommand(), args);
        
        registerHistory(args);
    }
    
    // This method will ensure that after every execution, the command gets saved in a text file
    private static void registerHistory(String[] args) throws IOException {
    	String command = "";
    	for (int i = 0; i < args.length; i++) {
    		command+=args[i]+" ";
    	}
    	
    	try {
	    	FileWriter commandsFile = new FileWriter("history.txt", true);
	    	commandsFile.write(command+"\n");
	    	commandsFile.close();
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}


    }

    public void run() {
        System.out.println("Welcome to my world, how can I assist you!");
    }
}

