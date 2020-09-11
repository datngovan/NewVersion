package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testFunction {
    public static void main(String[] args) {
        String leadTitle = "stringCode,name,birthDate,gender,phone,email,address";
        String interactionTitle = "codeString,date,stringCodeOfLead,mean,status";
        checkFile checker = new checkFile();

        boolean leadFileExist = checker.getBoolean("lead.csv");
        int lengthOfLeadArray = checker.getLength("lead.csv");

        Lead[] listOfLeads = new Lead[lengthOfLeadArray - 1];

        int leadIndex = 0;

        try{
            Scanner fileReading = new Scanner(new File("lead.csv"));
            while (fileReading.hasNext()){
                String eachLine = fileReading.nextLine();
                if(eachLine.equals(leadTitle)){

                }else{
                    String[] theArray = eachLine.split(",");
                    SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = simpleDateformat.parse(theArray[2]);
                    if (theArray[3].equals("true")){
                        Lead lead = new Lead(theArray[0],theArray[1],date,true,theArray[4],theArray[5],theArray[6]);
                        listOfLeads[leadIndex] = lead;
                    }
                    else {
                        Lead lead = new Lead(theArray[0],theArray[1],date,false,theArray[4],theArray[5],theArray[6]);
                        listOfLeads[leadIndex] = lead;
                    }

                    leadIndex += 1;
                }
            }
            fileReading.close();
        }catch (Exception exception){

        }

        boolean interactionListExist = checker.getBoolean("interaction.csv");
        int lengthOfInteractionArray = checker.getLength("interaction.csv");
        Interaction[] listOfInteraction = new Interaction[lengthOfInteractionArray - 1];
        System.out.println(lengthOfInteractionArray);
        int interactionIndex = 0;
        try{
            Scanner fileReading = new Scanner(new File("interaction.csv"));
            while (fileReading.hasNext()){
                String eachLine = fileReading.nextLine();
                if(eachLine.equals(interactionTitle)){

                }
                else{
                    String[] theArray = eachLine.split(",");
                    SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = simpleDateformat.parse(theArray[1]);
                    for (Lead lead: listOfLeads){
                        if (lead.getCodeString().equals(theArray[2])){
                            Interaction interaction = new Interaction(theArray[0],date,lead,theArray[3],theArray[4]);
                            listOfInteraction[interactionIndex] = interaction;
                        }
                    }

                    interactionIndex += 1;
                }

            }
            fileReading.close();
        }catch (Exception exception){

        }
        String testCase = "lead_001";
        try{
            for(Interaction interaction: listOfInteraction){
                System.out.println(interaction.getStringCode());
            }
        }catch (Exception e){

        }
    }
}
