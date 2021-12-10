package com.vazirna.dimagibot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	public static String currentUser;
	
    public static void main(String[] args) throws IOException {
    	currentUser = checkSession();
    	Scanner sc = new Scanner(System.in);
    	if (currentUser ==null) {
    		do {
    			System.out.println("It seems that I can't find your session, could you please tell me your name?");
    			currentUser = sc.next();
    		}while(currentUser ==null);
    		createSession(currentUser);
    		System.out.println("Thank you "+currentUser+", executing your request now!");
    	}
    	
    	
        CommandLine.run(new DimagiBotCommand(), args);
        
        registerHistory(args);
    }
    
    public static String checkSession() {
    	String user=null;
    	try {
    		File historyFile = new File("session.txt");
    		Scanner historyReader = new Scanner(historyFile);
    		
    		
    		while (historyReader.hasNextLine()) 
    			user = historyReader.nextLine();
    		
    		historyReader.close();
	    } catch (FileNotFoundException e) {
	     
	    }
    	return user;
		
	}

    private static void createSession(String user) throws IOException {
    	
    	try {
	    	FileWriter commandsFile = new FileWriter("session.txt", true);
	    	commandsFile.write(user);
	    	commandsFile.close();
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
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

