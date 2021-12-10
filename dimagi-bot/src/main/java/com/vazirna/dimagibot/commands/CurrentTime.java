package com.vazirna.dimagibot.commands;

import java.util.Calendar;

import picocli.CommandLine.Command;

@Command(
  name = "time"
)
public class CurrentTime implements Runnable {
    
    public void run() {
        System.out.println("Current Time is:"+Calendar.getInstance().getTimeInMillis());
    }

}
