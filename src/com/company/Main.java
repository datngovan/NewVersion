package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String leadTitle = "stringCode,name,birthDate,gender,phone,email,address";
        String interactionTitle = "codeString,date,stringCodeOfLead,mean,status";
        checkFile checker = new checkFile();
        Scanner sc = new Scanner(System.in);
        while(true){
            boolean leadInformationIsAdded = checker.getBoolean("lead.csv");
            System.out.print('\n');
            System.out.print("Welcome to CRM");
            System.out.print('\n');
            System.out.print("What would you like to do?");
            System.out.print('\n');
            System.out.print("- Manage lead information");
            System.out.print('\n');
            System.out.print("- Manage interaction information");
            System.out.print('\n');
            System.out.print("- Check the three reports taken from lead and interaction information");
            System.out.print('\n');
            System.out.print("Type lead for function 1, interaction for function 2, report for function 3 or exit for stopping");
            System.out.print('\n');

            String readMainMenuInput = sc.nextLine();
            String readMainMenuInputSanitized = readMainMenuInput.toLowerCase().trim();

            /** Begin of the lead Function */

            if(readMainMenuInputSanitized.equals("lead")){
                while(true){
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

                    System.out.print('\n');
                    System.out.print("Welcome to CRM");
                    System.out.print('\n');
                    System.out.print("What would you like to do?");
                    System.out.print('\n');
                    System.out.print("- View leads in detail");
                    System.out.print('\n');
                    System.out.print("- Add lead detail");
                    System.out.print('\n');
                    System.out.print("- Delete lead detail");
                    System.out.print('\n');
                    System.out.print("- Update lead detail");
                    System.out.print("\n");
                    System.out.print("Type view for function 1, add for function 2, delete for function 3, update for function 4 and exit for stopping");
                    System.out.print('\n');

                    String readLeadInput = sc.nextLine();
                    String readLeadInputSanitized = readLeadInput.toLowerCase().trim();

                    /** Begin of the view lead function */

                    if(readLeadInputSanitized.equals("view")){
                        if(leadFileExist){
                            Lead.viewLead(listOfLeads);
                        }else{
                            System.out.println("The file is empty or not found.");
                            continue;
                        }
                    }

                    /** Begin of the add lead function */

                    else if(readLeadInputSanitized.equals("add")){
                        while(true){
                            try{


                                String codeString = "lead"+'_';
                                int codeInteger = 0;
                                try{
                                    Scanner fileReading = new Scanner(new File("lead.csv"));
                                    while (fileReading.hasNext()){
                                        String eachLine = fileReading.nextLine();
                                        if(leadTitle.equals(eachLine)){

                                        }
                                        else{
                                            codeInteger = Integer.parseInt(eachLine.split(",")[0].split("_")[1]);
                                        }
                                    }

                                    fileReading.close();

                                }
                                catch (FileNotFoundException fileNotFoundexception){

                                }
                                codeInteger = codeInteger + 1;
                                for (int i = 0; i < 3 - String.valueOf(codeInteger).length();++i){
                                    codeString = codeString + '0';
                                }

                                codeString = codeString + codeInteger;


                                System.out.print('\n');
                                System.out.print('\n');
                                System.out.print("codeString is" + " " + codeString);



                                Scanner input = new Scanner(System.in);



                                System.out.print('\n');
                                System.out.print("Input your name" + " ");
                                System.out.print('\n');
                                System.out.print("For example: anna");
                                System.out.print('\n');
                                System.out.print("(Numbers or special characters are invalid)" + " ");
                                String leadName = input.nextLine();

                                for (char eachCharacter: leadName.toLowerCase().trim().toCharArray()){
                                    if ( (eachCharacter<97 && eachCharacter!=' ') ||( eachCharacter>97+26 && eachCharacter != ' ' )){
                                        throw new IllegalArgumentException("Name input is invalid");
                                    }
                                }


                                System.out.print("Input your birthDate" + " ");
                                System.out.print('\n');
                                System.out.print("For example: 2000-07-05");
                                System.out.print('\n');
                                System.out.print("(Please follow the pattern yyyy-MM-dd)" + " ");
                                String leadBirthDate = input.nextLine();
                                SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = getFormat.parse(leadBirthDate);
                                if(Integer.parseInt(leadBirthDate.split("-")[2]) >31){
                                    throw new IllegalArgumentException("Day input is invalid");
                                }
                                else {
                                    if (Integer.parseInt(leadBirthDate.split("-")[1]) > 12){
                                        throw new IllegalArgumentException("Month input is invalid");
                                    }
                                    else {
                                        if(date.compareTo(new Date()) >0){
                                            throw new IllegalArgumentException("BirthDate input is in valid");
                                        }
                                    }
                                }



                                System.out.print("Input your phone" + " ");
                                System.out.print('\n');
                                System.out.print("For example: 0946576795");
                                System.out.print('\n');
                                System.out.print("(Only 10 numbers are allowed)" + " ");
                                String leadPhone = input.nextLine();

                                Pattern patternOfPhone = Pattern.compile("[0-9]{10}");
                                Matcher phoneMatcher = patternOfPhone.matcher(leadPhone);
                                if (!phoneMatcher.matches()){



                                    throw new IllegalArgumentException("Phone input is invalid");
                                }



                                System.out.print("Input your email" + " ");
                                System.out.print('\n');
                                System.out.print("For example: anna@gmail.com or anna@rmit.edu.vn");
                                System.out.print('\n');
                                System.out.print("(Please follow the pattern)" + " ");
                                String leadEmail = input.nextLine();

                                Pattern patternOfEmail=  Pattern.compile("([a-zA-Z0-9]*[@][a-zA-Z]*[.][a-zA-Z]*)|([a-zA-Z0-9]*[@][a-zA-Z]*[.][a-zA-Z]*[.][a-zA-Z]*)");
                                Matcher emailMatcher = patternOfEmail.matcher(leadEmail);
                                if (!emailMatcher.matches()){


                                    throw new IllegalArgumentException("Email input is invalid");
                                }


                                System.out.print("Input your address" + " ");
                                System.out.print('\n');
                                System.out.print("For example: 702 Nguyen Van Linh");
                                System.out.print('\n');
                                System.out.print("(Please follow the pattern)" + " ");
                                String leadAddress = input.nextLine();
                                Pattern patternOfAddress = Pattern.compile("[a-zA-Z0-9/ ]*");
                                Matcher addressMatcher = patternOfAddress.matcher(leadAddress);
                                if (!addressMatcher.matches()){

                                    throw new IllegalArgumentException("Address input is invalid");
                                }



                                System.out.print("Input your gender" + " ");
                                System.out.print('\n');
                                System.out.print("For example: true");
                                System.out.print('\n');
                                System.out.print("(True for female and false for male)" + " ");
                                boolean leadGender = input.nextBoolean();
                                Lead lead = new Lead(codeString, leadName, date,leadGender,leadPhone, leadEmail, leadAddress);
                                lead.fillList();

                            }
                            catch (ParseException parseException){
                                System.out.print("birthdate input is invalid");
                                continue;
                            }
                            catch (Exception exception){
                                System.out.print(exception.getMessage());
                                continue;
                            }
                            System.out.print("Successful");
                            break;
                        }
                    }

                    /** Begin of the delete lead function */

                    else if(readLeadInputSanitized.equals("delete")){

                        if (leadFileExist){
                            while (true){
                                System.out.print('\n');
                                System.out.print("codeString" + " ");
                                System.out.print('\n');
                                System.out.print("For example: lead_001");
                                System.out.print('\n');
                                System.out.print("(Please follow the pattern lead_nnn)" + " ");
                                Scanner codeStringinput = new Scanner(System.in);
                                String readingCodestringInput = codeStringinput.nextLine();
                                boolean isCodestringAdded = false;
                                for (Lead lead: listOfLeads){
                                    if (lead.getCodeString().equals(readingCodestringInput.toLowerCase().trim())){
                                        isCodestringAdded = true;
                                    }
                                }
                                if (isCodestringAdded){
                                    if(interactionListExist){
                                        boolean isEquals = false;
                                        for(Interaction interaction: listOfInteraction){
                                            if(readingCodestringInput.toLowerCase().trim().equals(interaction.getLead().getCodeString())){
                                                isEquals = true;
                                            }
                                        }
                                        if(isEquals){
                                            while (true){
                                                System.out.println("This lead has been involved with 1 or more interactions");
                                                System.out.println("Yes");
                                                System.out.println("No");
                                                System.out.println("Type yes to delete the lead and its relative interactions or no to stop this process.");
                                                String inputDelete = sc.nextLine();
                                                String inputDeleteSanitized = inputDelete.toLowerCase().trim();
                                                if(inputDeleteSanitized.equals("yes")){
                                                    Lead.deleteLead(listOfLeads,readingCodestringInput);
                                                    Interaction.writeDeleteByLeadId(listOfInteraction,readingCodestringInput);
                                                    System.out.println("Successful");
                                                    break;
                                                }else if(inputDeleteSanitized.equals("no")){
                                                    System.out.println("Successful");
                                                    break;
                                                }else{
                                                    System.out.print("invalid input");
                                                    continue;
                                                }

                                            }
                                            break;
                                        }else{
                                            Lead.deleteLead(listOfLeads, readingCodestringInput);
                                            System.out.print("Successful");
                                            break;
                                        }
                                    }else{
                                        Lead.deleteLead(listOfLeads, readingCodestringInput);
                                        System.out.print("Successful");
                                        break;
                                    }
                                }
                                System.out.print("codeString is not in the file or has not added");

                            }
                        }
                        else{
                            System.out.print("the list needs to be filled");
                            continue;
                        }
                    }

                    /** Begin of the update lead function */

                    else if (readLeadInputSanitized.equals("update")){

                        if(leadFileExist){
                            while(true){
                                System.out.print('\n');
                                System.out.print("codeString" + " ");
                                System.out.print('\n');
                                System.out.print("For example: lead_001");
                                System.out.print('\n');
                                System.out.print("(Please follow the pattern lead_nnn)" + " ");
                                Scanner codeStringinput = new Scanner(System.in);
                                String readingCodestringInput = codeStringinput.nextLine();
                                boolean isCodestringAdded = false;
                                int secondIndex = 0;
                                int count = 0;
                                for (Lead lead: listOfLeads){
                                    if (lead.getCodeString().equals(readingCodestringInput)){
                                        System.out.print("Accepted");
                                        isCodestringAdded = true;
                                        secondIndex = count;

                                    }
                                    count += 1;

                                }

                                if (isCodestringAdded){

                                    while (true){
                                        System.out.print('\n');
                                        System.out.print("information section" + " ");
                                        System.out.print('\n');
                                        System.out.print("(name, birthdate, gender, phone, email and address)" + " ");
                                        Scanner informationSectioninput = new Scanner(System.in);
                                        String readingInformationsectionInput = informationSectioninput.nextLine();
                                        Scanner newUpdateinput = new Scanner(System.in);
                                        String readingInformationsectionInputsanitized = readingInformationsectionInput.toLowerCase().trim();
                                        if(readingInformationsectionInputsanitized.equals("name")){
                                            while (true){
                                                System.out.print('\n');
                                                System.out.print("new update" + " ");
                                                System.out.print('\n');
                                                System.out.print("For example: anna");
                                                System.out.print('\n');
                                                System.out.print("(Numbers or special characters are invalid)" + " ");
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                try {

                                                    for (char eachCharacter: readingNewupdateInputsanitized.toCharArray()){
                                                        if ( (eachCharacter<97 && eachCharacter!=' ') ||( eachCharacter>97+26 && eachCharacter != ' ' )){
                                                            throw new IllegalArgumentException("Name input is invalid");
                                                        }
                                                    }

                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    System.out.print(illegalArgumentexception.getMessage());
                                                    continue;
                                                }
                                                listOfLeads[secondIndex].setName(readingNewupdateInput);
                                                break;
                                            }
                                        }
                                        else if(readingInformationsectionInputsanitized.equals("birthdate")){
                                            while (true){
                                                System.out.print('\n');
                                                System.out.print("new update" + " ");
                                                System.out.print('\n');
                                                System.out.print("For example: 2000-07-05");
                                                System.out.print('\n');
                                                System.out.print("(Please follow the pattern)" + " ");
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                Date date = new Date();
                                                try {
                                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                    date = getFormat.parse(readingNewupdateInput);
                                                    if(Integer.parseInt(readingNewupdateInput.split("-")[2]) >31){
                                                        throw new IllegalArgumentException("day input is invalid");
                                                    }
                                                    else {
                                                        if (Integer.parseInt(readingNewupdateInput.split("-")[1]) > 12){
                                                            throw new IllegalArgumentException("month input is invalid");
                                                        }
                                                        else {
                                                            if(date.compareTo(new Date()) >0){
                                                                throw new IllegalArgumentException("birthdate input is invalid");
                                                            }
                                                        }
                                                    }
                                                }
                                                catch (ParseException parseException){
                                                    System.out.print("birthdate input is invalid");
                                                    continue;
                                                }
                                                catch (Exception exception){
                                                    System.out.print(exception.getMessage());
                                                    continue;
                                                }
                                                listOfLeads[secondIndex].setBirthdate(date);
                                                break;
                                            }

                                        }

                                        else if(readingInformationsectionInputsanitized.equals("gender")){
                                            while (true){

                                                Scanner newUpdateinputBoolean = new Scanner(System.in);


                                                try {
                                                    System.out.print('\n');
                                                    System.out.print("new update" + " ");
                                                    System.out.print('\n');
                                                    System.out.print("For example: true");
                                                    System.out.print('\n');
                                                    System.out.print("(True for female and false for male)" + " ");
                                                    boolean readingNewupdateInputboolean = newUpdateinputBoolean.nextBoolean();
                                                    listOfLeads[secondIndex].setGender(readingNewupdateInputboolean);
                                                }
                                                catch (Exception exception){
                                                    System.out.print(exception.getMessage());
                                                    continue;
                                                }

                                                break;
                                            }

                                        }
                                        else if(readingInformationsectionInputsanitized.equals("phone")){
                                            while (true){
                                                System.out.print('\n');
                                                System.out.print("new update" + " ");
                                                System.out.print('\n');
                                                System.out.print("For example: 0946576795");
                                                System.out.print('\n');
                                                System.out.print("(Only 10 numbers are allowed)" + " ");
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                try {
                                                    Pattern patternOfphone = Pattern.compile("[0-9]{10}");
                                                    Matcher phoneMatcher = patternOfphone.matcher(readingNewupdateInput);
                                                    if (!phoneMatcher.matches()){
                                                        throw new IllegalArgumentException("phone input is invalid");
                                                    }
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    System.out.print(illegalArgumentexception.getMessage());
                                                    continue;
                                                }
                                                listOfLeads[secondIndex].setPhone(readingNewupdateInput);
                                                break;
                                            }
                                        }
                                        else if(readingInformationsectionInputsanitized.equals("email")){
                                            while (true){
                                                System.out.print('\n');
                                                System.out.print("new update" + " ");
                                                System.out.print('\n');
                                                System.out.print("For example: anna@gmail.com or anna@rmit.edu.vn");
                                                System.out.print('\n');
                                                System.out.print("(Please follow the pattern)" + " ");
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                try {
                                                    Pattern patternOfEmail=  Pattern.compile("([a-zA-Z0-9]*[@][a-zA-Z]*[.][a-zA-Z]*)|([a-zA-Z0-9]*[@][a-zA-Z]*[.][a-zA-Z]*[.][a-zA-Z]*)");
                                                    Matcher emailMatcher = patternOfEmail.matcher(readingNewupdateInput);
                                                    if (!emailMatcher.matches()){


                                                        throw new IllegalArgumentException("email input is invalid");
                                                    }
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    System.out.print(illegalArgumentexception.getMessage());
                                                    continue;
                                                }
                                                listOfLeads[secondIndex].setEmail(readingNewupdateInput);
                                                break;
                                            }
                                        }

                                        else if(readingInformationsectionInputsanitized.equals("address")){
                                            while (true){
                                                System.out.print('\n');
                                                System.out.print("new update" + " ");
                                                System.out.print('\n');
                                                System.out.print("For example: 702 Nguyen Van Linh");
                                                System.out.print('\n');
                                                System.out.print("(Please follow the pattern)" + " ");
                                                String readingNewupdateInput = newUpdateinput.nextLine();

                                                try {
                                                    Pattern patternOfaddress = Pattern.compile("[a-zA-Z0-9 ]+");
                                                    Matcher addressMatcher = patternOfaddress.matcher(readingNewupdateInput);
                                                    if(!addressMatcher.matches()){

                                                        throw new IllegalArgumentException("address input is invalid");
                                                    }
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    System.out.print(illegalArgumentexception.getMessage());
                                                    continue;
                                                }
                                                listOfLeads[secondIndex].setAddress(readingNewupdateInput);
                                                break;
                                            }
                                        }

                                        else {
                                            System.out.print("section input is not in the system");
                                            continue;
                                        }



                                        break;
                                    }

                                    Lead.writeLead(listOfLeads);
                                    System.out.print("Successful");

                                    break;
                                }


                                System.out.print("codeString is not in the file or has not added");
                            }
                        }
                        else {
                            System.out.print("the list needs to be filled");
                            continue;
                        }
                    }
                    else if(readLeadInputSanitized.equals("exit")){
                        break;
                    }
                    else {
                        System.out.print("invalid input");
                        continue;
                    }
                }
            }

            /** Begin of the interaction Function */

            else if(readMainMenuInputSanitized.equals("interaction")){
                if(leadInformationIsAdded){
                    while(true){
                        int lengthOfLeadArray = checker.getLength("lead.csv");
                        boolean leadFileExist = checker.getBoolean("lead.csv");
                        Lead[] listOfLeads = new Lead[lengthOfLeadArray - 1];
                        int leadIndex = 0;
                        try{
                            Scanner fileReading = new Scanner(new File("lead.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                if(eachLine.equals(leadTitle)){

                                }
                                else {

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
                        int lengthOfInteractionArray = checker.getLength("interaction.csv");
                        boolean interactionFileExist = checker.getBoolean("interaction.csv");
                        Interaction[] listOfInteractions = new Interaction[lengthOfInteractionArray - 1];
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
                                            listOfInteractions[interactionIndex] = interaction;
                                        }
                                    }

                                    interactionIndex += 1;

                                }


                            }
                            fileReading.close();
                        }catch (Exception exception){

                        }

                        System.out.print('\n');
                        System.out.print("Welcome to CRM");
                        System.out.print('\n');
                        System.out.print("What would you like to do?");
                        System.out.print('\n');
                        System.out.print("- View interaction in detail");
                        System.out.print('\n');
                        System.out.print("- Add interaction detail");
                        System.out.print('\n');
                        System.out.print("- Delete interaction detail");
                        System.out.print('\n');
                        System.out.print("- Update interaction detail");
                        System.out.print("\n");
                        System.out.print("Type view for function 1, add for function 2, delete for function 3, update for function 4 and exit for stopping");
                        System.out.print('\n');

                        String readInteractionInput = sc.nextLine();
                        String readInteractionInputSanitized = readInteractionInput.toLowerCase().trim();

                        /** Begin of the view interaction function */

                        if(readInteractionInputSanitized.equals("view")){
                            if(interactionFileExist){
                                Interaction.viewInteraction(listOfInteractions);
                            }else{
                                System.out.println("The file is empty or not found.");
                            }
                        }

                        /** Begin of the add interaction function */

                        else if(readInteractionInputSanitized.equals("add")){
                            while (true){
                                try{


                                    String codeString = "inter"+'_';
                                    int codeInteger = 0;
                                    try{
                                        Scanner fileReading = new Scanner(new File("interaction.csv"));
                                        while (fileReading.hasNext()){
                                            String eachLine = fileReading.nextLine();
                                            if(eachLine.equals(interactionTitle)){

                                            }
                                            else {
                                                codeInteger = Integer.parseInt(eachLine.split(",")[0].split("_")[1]);

                                            }

                                        }

                                        fileReading.close();

                                    }
                                    catch (FileNotFoundException fileNotFoundexception){

                                    }
                                    codeInteger = codeInteger + 1;
                                    for (int i = 0; i < 3 - String.valueOf(codeInteger).length();++i){
                                        codeString = codeString + '0';
                                    }

                                    codeString = codeString + codeInteger;



                                    System.out.print('\n');
                                    System.out.print('\n');

                                    System.out.print("codeString is " + " " + codeString);



                                    Scanner input = new Scanner(System.in);

                                    System.out.print('\n');
                                    System.out.print("input the date" + " ");
                                    System.out.print('\n');
                                    System.out.print("For example: 2000-07-05");
                                    System.out.print('\n');
                                    System.out.print("(Please follow the pattern yyyy-MM-dd)" + " ");
                                    String interactionDate = input.nextLine();
                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = getFormat.parse(interactionDate);
                                    if(Integer.parseInt(interactionDate.split("-")[2]) >31){
                                        throw new IllegalArgumentException("date input is invalid");
                                    }
                                    else {
                                        if (Integer.parseInt(interactionDate.split("-")[1]) > 12){
                                            throw new IllegalArgumentException("month input is invalid");
                                        }
                                        else {
                                            if(date.compareTo(new Date()) >0){
                                                throw new IllegalArgumentException("interactionDate input is invalid");
                                            }
                                        }
                                    }




                                    System.out.print("input codeString of the lead" + " ");
                                    System.out.print('\n');
                                    System.out.print("For example: lead_001");
                                    System.out.print('\n');
                                    System.out.print("(Please follow the pattern lead_nnn)" + " ");
                                    String interactionLead = input.nextLine();
                                    boolean isStringcodeOfleadNotadded = true;
                                    try{
                                        Scanner fileReading = new Scanner(new File("lead.csv"));

                                        while (fileReading.hasNext()){
                                            String eachLine = fileReading.nextLine();
                                            if(eachLine.equals(leadTitle)){

                                            }
                                            else{
                                                if (eachLine.split(",")[0].equals(interactionLead)){
                                                    isStringcodeOfleadNotadded = false;
                                                    break;
                                                }
                                                else {
                                                    isStringcodeOfleadNotadded = true;
                                                }

                                            }

                                        }
                                        fileReading.close();
                                        if (isStringcodeOfleadNotadded){
                                            throw new IllegalArgumentException("stringCodeofLead input is invalid");
                                        }


                                    }
                                    catch (FileNotFoundException fileNotfoundException){
                                        break;
                                    }

                                    System.out.print("input the mean" + " ");
                                    System.out.print('\n');
                                    System.out.print("For example: face-to-face");
                                    System.out.print('\n');
                                    System.out.print("(face to face, message, socialmedia or facebook)" + " ");
                                    String interactionMean = input.nextLine();
                                    String interactionMeansanitized = interactionMean.trim().toLowerCase();
                                    if(!(interactionMeansanitized.equals("ftf")||interactionMeansanitized.equals("facetoface")||interactionMeansanitized.equals("face-to-face")||interactionMeansanitized.equals("message")||interactionMeansanitized.equals("socialmedia")||interactionMeansanitized.equals("facebook"))){
                                        throw new IllegalArgumentException("interactionMean input is invalid");
                                    }




                                    System.out.print("input the status");
                                    System.out.print('\n');
                                    System.out.print("For example: neutral");
                                    System.out.print('\n');
                                    System.out.print("(neutral, negative or positive)" + " ");
                                    String interactionStatus = input.nextLine();
                                    if (interactionStatus.trim().toLowerCase().equals("neutral")||interactionStatus.trim().toLowerCase().equals("negative")||interactionStatus.trim().toLowerCase().equals("positive")){

                                    }
                                    else {
                                        throw new IllegalArgumentException("interactionStatus input is invalid");
                                    }

                                    for (Lead lead: listOfLeads){
                                        if(lead.getCodeString().equals(interactionLead)){
                                            Interaction interaction = new Interaction(codeString, date, lead, interactionMean, interactionStatus);
                                            interaction.fillList();
                                        }
                                    }



                                }
                                catch (Exception exception){
                                    System.out.print(exception.getMessage());
                                    continue;
                                }
                                System.out.print("Successful");
                                break;
                            }
                        }

                        /** Begin of the delete interaction function */

                        else if(readInteractionInputSanitized.equals("delete")){

                            if (interactionFileExist){
                                while (true){
                                    System.out.print('\n');
                                    System.out.print("codeString" + " ");
                                    System.out.print('\n');
                                    System.out.print("For example: inter_001");
                                    System.out.print('\n');
                                    System.out.print("(Please follow the pattern inter_nnn)" + " ");
                                    Scanner codeStringinput = new Scanner(System.in);
                                    String readingCodestringInput = codeStringinput.nextLine();
                                    boolean isCodestringAdded = false;
                                    for (Interaction interaction: listOfInteractions){
                                        if (interaction.getStringCode().equals(readingCodestringInput.toLowerCase().trim())){
                                            isCodestringAdded = true;
                                        }
                                    }
                                    if (isCodestringAdded){
                                        Interaction.deleteInteraction(listOfInteractions, readingCodestringInput);

                                        System.out.print("Successful");
                                        break;

                                    }
                                    System.out.print("codeString is not in the file or has not added");
                                }
                            }
                            else {
                                System.out.print("the list needs to be filled");
                                continue;
                            }
                        }

                        /** Begin of the update interaction function */

                        else if (readInteractionInputSanitized.equals("update")){

                            if(interactionFileExist){
                                while(true){
                                    System.out.print('\n');
                                    System.out.print("codeString" + " ");
                                    System.out.print('\n');
                                    System.out.print("For example: inter_001");
                                    System.out.print('\n');
                                    System.out.print("(Please follow the pattern inter_nnn)" + " ");
                                    Scanner codeStringinput = new Scanner(System.in);
                                    String readingCodestringInput = codeStringinput.nextLine();
                                    boolean isCodestringAdded = false;
                                    int secondIndex = 0;
                                    int count = 0;
                                    for (Interaction interaction: listOfInteractions){
                                        if (interaction.getStringCode().equals(readingCodestringInput)){
                                            System.out.print("Accepted");
                                            isCodestringAdded = true;
                                            secondIndex = count;
                                        }
                                        count += 1;

                                    }

                                    if (isCodestringAdded){

                                        while (true){
                                            System.out.print('\n');
                                            System.out.print("information section" + " ");
                                            System.out.print('\n');
                                            System.out.print("(date, lead, mean and status)" + " ");

                                            Scanner informationSectioninput = new Scanner(System.in);
                                            String readingInformationsectionInput = informationSectioninput.nextLine();
                                            Scanner newUpdateinput = new Scanner(System.in);
                                            String readingInformationsectionInputsanitized = readingInformationsectionInput.toLowerCase().trim();
                                            if (readingInformationsectionInputsanitized.equals("date")){
                                                while (true){

                                                    System.out.print('\n');
                                                    System.out.print("new update" + " ");
                                                    System.out.print('\n');
                                                    System.out.print("For example: 2000-07-05");
                                                    System.out.print('\n');
                                                    System.out.print("(Please follow the pattern yyyy-MM-dd)" + " ");
                                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                                    Date date = new Date();
                                                    try {
                                                        SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                        date = getFormat.parse(readingNewupdateInput);
                                                        if(Integer.parseInt(readingNewupdateInput.split("-")[2]) >31){
                                                            throw new IllegalArgumentException("day input is invalid");
                                                        }
                                                        else {
                                                            if (Integer.parseInt(readingNewupdateInput.split("-")[1]) > 12){
                                                                throw new IllegalArgumentException("month input is invalid");
                                                            }
                                                            else {
                                                                if(date.compareTo(new Date()) >0){
                                                                    throw new IllegalArgumentException("interactionDate input is invalid");
                                                                }
                                                            }
                                                        }
                                                    }
                                                    catch (ParseException parseException){
                                                        System.out.print("interactionDate input is invalid");
                                                        continue;
                                                    }
                                                    catch (Exception exception){
                                                        System.out.print(exception.getMessage());
                                                        continue;
                                                    }
                                                    listOfInteractions[secondIndex].setDate(date);
                                                    break;
                                                }
                                            }
                                            else if(readingInformationsectionInputsanitized.equals("lead")){
                                                while (true){
                                                    System.out.print('\n');
                                                    System.out.print("new update" + " ");
                                                    System.out.print('\n');
                                                    System.out.print("For example: lead_001");
                                                    System.out.print('\n');
                                                    System.out.print("(Please follow the pattern lead_nnn)" + " ");
                                                    String readingNewupdateInput = newUpdateinput.nextLine();

                                                    try {

                                                        boolean isStringcodeOfleadNotadded = true;
                                                        try{
                                                            Scanner fileReading = new Scanner(new File("lead.csv"));

                                                            while (fileReading.hasNext()){
                                                                String eachLine = fileReading.nextLine();
                                                                if (eachLine.equals(leadTitle)) {

                                                                }
                                                                else {
                                                                    if (eachLine.split(",")[0].equals(readingNewupdateInput)){
                                                                        isStringcodeOfleadNotadded = false;
                                                                        break;
                                                                    }
                                                                    else {
                                                                        isStringcodeOfleadNotadded = true;
                                                                    }

                                                                }

                                                            }
                                                            fileReading.close();
                                                            if (isStringcodeOfleadNotadded){
                                                                throw new IllegalArgumentException("stringCode input is invalid");
                                                            }


                                                        }
                                                        catch (FileNotFoundException fileNotfoundException){
                                                            break;
                                                        }

                                                    }
                                                    catch (IllegalArgumentException illegalArgumentexception){
                                                        System.out.print(illegalArgumentexception.getMessage());
                                                        continue;
                                                    }
                                                    for (Lead lead: listOfLeads){
                                                        if (lead.getCodeString().equals(readingNewupdateInput)){
                                                            listOfInteractions[secondIndex].setLead(lead);
                                                        }
                                                    }

                                                    break;
                                                }
                                            }
                                            else if(readingInformationsectionInputsanitized.equals("mean")){
                                                while (true){
                                                    System.out.print('\n');
                                                    System.out.print("new update" + " ");
                                                    System.out.print('\n');
                                                    System.out.print("For example: face-to-face");
                                                    System.out.print('\n');
                                                    System.out.print("(face to face, message, socialmedia or facebook)" + " ");
                                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                                    String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                    try {
                                                        if(!(readingNewupdateInputsanitized.equals("ftf")||readingNewupdateInputsanitized.equals("facetoface")||readingNewupdateInputsanitized.equals("face-to-face")||readingNewupdateInputsanitized.equals("message")||readingNewupdateInputsanitized.equals("socialmedia")||readingNewupdateInputsanitized.equals("facebook"))){
                                                            throw new IllegalArgumentException("interactionMean input is invalid");
                                                        }
                                                    }
                                                    catch (IllegalArgumentException illegalArgumentexception){
                                                        System.out.print(illegalArgumentexception.getMessage());
                                                        continue;
                                                    }
                                                    listOfInteractions[secondIndex].setMean(readingNewupdateInput);
                                                    break;
                                                }
                                            }
                                            else if(readingInformationsectionInputsanitized.equals("status")){
                                                while (true){
                                                    System.out.print('\n');
                                                    System.out.print("new update" + " ");
                                                    System.out.print('\n');
                                                    System.out.print("For example: neutral");
                                                    System.out.print('\n');
                                                    System.out.print("(neutral, negative or positive)" + " ");
                                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                                    String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                    try {
                                                        if(!(readingNewupdateInputsanitized.equals("neutral")||readingNewupdateInputsanitized.equals("negative")||readingNewupdateInputsanitized.equals("positive"))){
                                                            throw new IllegalArgumentException("interactionStatus input is invalid");
                                                        }
                                                    }
                                                    catch (IllegalArgumentException illegalArgumentexception){
                                                        System.out.print(illegalArgumentexception.getMessage());
                                                        continue;
                                                    }
                                                    listOfInteractions[secondIndex].setStatus(readingNewupdateInput);
                                                    break;
                                                }
                                            }
                                            else {
                                                System.out.print("section input is not in the system");
                                                continue;
                                            }
                                            break;
                                        }

                                        Interaction.writeInteraction(listOfInteractions);
                                        System.out.print("Successful");
                                        break;
                                    }
                                    System.out.print("codeString is not in the file or has not added");
                                }
                            }
                            else {
                                System.out.print("the list needs to be filled");
                                continue;
                            }
                        }
                        else if(readInteractionInputSanitized.equals("exit")){
                            break;
                        }
                        else {
                            System.out.print("invalid input");
                            continue;
                        }
                    }
                } else {
                    System.out.print("the lead list needs to be filled");
                    continue;
                }
            }

            /** Begin of the report Function */

            else if(readMainMenuInputSanitized.equals("report")){
                if(leadInformationIsAdded){
                    while (true){
                        boolean interactionInformationIsAdded = checker.getBoolean("interaction.csv");
                        Scanner functionInput = new Scanner(System.in);
                        System.out.print('\n');
                        System.out.print("Welcome to CRM");
                        System.out.print('\n');
                        System.out.print("- View the report of the lead number by age");
                        System.out.print('\n');
                        System.out.print("- View the report of the interaction number of different status by date");
                        System.out.print('\n');
                        System.out.print("- View the report of the interaction number by date ");
                        System.out.print('\n');
                        System.out.print("Type view age for function 1, view status for function 2, view interaction for function 3 or exit for stopping");
                        System.out.print('\n');
                        String readReportInput = functionInput.nextLine();
                        String readReportInputSanitized = readReportInput.trim().toLowerCase();

                        /** Begin of the print leads function
                         * This function print out the numbers of leads in different age ranges */

                        if(readReportInputSanitized.equals("view age")){
                            Report.printViewLeads();
                        }
                        /** Begin of the print status function
                         * This function print out the numbers of potential status(positive, neutral or negative) within a time range */

                        else if(readReportInputSanitized.equals("view status")){
                            if(interactionInformationIsAdded){
                                Report.printViewStatus();
                            }
                            else {
                                System.out.print("the interaction list needs to be added");
                                continue;
                            }
                        }

                        /** Begin of the print interaction function
                         * This function print out the number of interaction in each months within the time range */

                        else if(readReportInputSanitized.equals("view interaction")){
                            if(interactionInformationIsAdded){
                                Report.printViewInteractions();
                            }
                            else{
                                System.out.print("the interaction list needs to be added");
                                continue;
                            }
                        }
                        else if(readReportInputSanitized.equals("exit")){
                            break;
                        }
                        else {
                            System.out.print("invalid input");
                            continue;
                        }
                    }
                }
                else{
                    System.out.print("the lead list needs to be filled");
                    continue;
                }
            }
            else if(readMainMenuInputSanitized.equals("exit")) {
                break;
            }
            else {
                System.out.print("invalid input");
                continue;
            }
        }
    }
}
