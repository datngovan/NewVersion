
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

public class Themenu {
    public static void main(String[] args) {
        while (true){



            boolean isLeadinformationAdded = false;
            int numberOflines = 0;
            try{
                Scanner fileReading = new Scanner(new File("lead.csv"));

                while (fileReading.hasNext()){
                    String eachLine = fileReading.nextLine();
                    numberOflines += 1;
                }

                if (numberOflines >1){
                    isLeadinformationAdded = true;
                }

                fileReading.close();

            }
            catch (FileNotFoundException fileNotfoundException){

            }

            Scanner functionInput1 = new Scanner(System.in);
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
            System.out.print("Type lead for managing lead information, interaction for managing interaction information, report for checking the reports or exit for stopping");

            String readingFunctioninput1 = functionInput1.nextLine();
            String readingFunctioninput1Sanitized = readingFunctioninput1.toLowerCase().trim();

            if (readingFunctioninput1Sanitized.equals("lead")){


                while (true){

                    String leadTitle = "stringCode,name,birthdate,gender,phone,email,address";
                    String interactionTitle = "codeString,date,stringCodeofLead,mean,status";
                    int lengthOfarray = 0;
                    boolean isThereaList = false;
                    try{
                        Scanner fileReading = new Scanner(new File("lead.csv"));
                        while (fileReading.hasNext()){
                            String eachLine = fileReading.nextLine();
                            lengthOfarray = lengthOfarray + 1;
                        }
                        if(lengthOfarray>1){
                            isThereaList = true;
                        }
                        fileReading.close();

                    }
                    catch (FileNotFoundException fileNotfoundException){
                        lengthOfarray = lengthOfarray + 1;
                    }
                    Lead[] listOfleads = new Lead[lengthOfarray - 1];
                    int index = 0;

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
                                    listOfleads[index] = lead;
                                }
                                else {
                                    Lead lead = new Lead(theArray[0],theArray[1],date,false,theArray[4],theArray[5],theArray[6]);
                                    listOfleads[index] = lead;
                                }

                                index += 1;
                            }

                        }
                        fileReading.close();


                    }catch (Exception exception){

                    }


                    int lengthOfarray1 = 0;
                    boolean isThereaList1 = false;
                    try{
                        Scanner fileReading = new Scanner(new File("interaction.csv"));
                        while (fileReading.hasNext()){
                            String eachLine = fileReading.nextLine();
                            lengthOfarray1 = lengthOfarray1 + 1;
                        }
                        if(lengthOfarray1>1){
                            isThereaList1 = true;
                        }
                        fileReading.close();

                    }
                    catch (FileNotFoundException fileNotfoundException){
                        lengthOfarray1 = lengthOfarray1 + 1;
                    }
                    Interaction[] listOfinteraction = new Interaction[lengthOfarray1 - 1];
                    int index2 = 0;

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
                                for (Lead lead: listOfleads){
                                    if (lead.getCodeString().equals(theArray[2])){
                                        Interaction interaction = new Interaction(theArray[0],date,lead,theArray[3],theArray[4]);
                                        listOfinteraction[index2] = interaction;
                                    }
                                }

                                index2 += 1;
                            }

                        }
                        fileReading.close();
                    }catch (Exception exception){

                    }






















                    Scanner functionInput = new Scanner(System.in);
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
                    System.out.print("Type view for viewing, add for adding, delete for deleting, update for updating and exit for stopping");

                    String readingInput = functionInput.nextLine();
                    String readingInputsanitized = readingInput.toLowerCase().trim();
                    if (readingInputsanitized.equals("view")){
                        if (isThereaList){
                            Lead.viewLead(listOfleads);
                        }
                        else {
                            System.out.print("the file is empty or not found");
                            continue;
                        }
                    }
                    else if(readingInputsanitized.equals("add")){
                        while (true){
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
                                System.out.print("input your name" + " ");
                                String leadName = input.nextLine();

                                for (char eachCharacter: leadName.toLowerCase().trim().toCharArray()){
                                    if ( (eachCharacter<97 && eachCharacter!=' ') ||( eachCharacter>97+26 && eachCharacter != ' ' )){
                                        throw new IllegalArgumentException("name input is invalid");
                                    }
                                }


                                System.out.print("input your birthdate" + " ");
                                String leadBirthdate = input.nextLine();
                                SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = getFormat.parse(leadBirthdate);
                                if(Integer.parseInt(leadBirthdate.split("-")[2]) >31){
                                    throw new IllegalArgumentException("day input is invalid");
                                }
                                else {
                                    if (Integer.parseInt(leadBirthdate.split("-")[1]) > 12){
                                        throw new IllegalArgumentException("month input is invalid");
                                    }
                                    else {
                                        if(date.compareTo(new Date()) >0){
                                            throw new IllegalArgumentException("birthdate input is in valid");
                                        }
                                    }
                                }



                                System.out.print("input your phone" + " ");
                                String leadPhone = input.nextLine();

                                Pattern patternOfphone = Pattern.compile("[0-9 ]+");
                                Matcher phoneMatcher = patternOfphone.matcher(leadPhone);
                                if (!phoneMatcher.matches()){



                                    throw new IllegalArgumentException("phone input is invalid");
                                }



                                System.out.print("input your email" + " ");
                                String leadEmail = input.nextLine();

                                Pattern patternOfEmail=  Pattern.compile("([a-zA-Z0-9]*[@][a-zA-Z]*[.][a-zA-Z]*)|([a-zA-Z0-9]*[@][a-zA-Z]*[.][a-zA-Z]*[.][a-zA-Z]*)");
                                Matcher emailMatcher = patternOfEmail.matcher(leadEmail);
                                if (!emailMatcher.matches()){


                                    throw new IllegalArgumentException("email input is invalid");
                                }


                                System.out.print("input your address" + " ");
                                String leadAddress = input.nextLine();
                                Pattern patternOfAddress = Pattern.compile("[a-zA-Z0-9/ ]*");
                                Matcher addressMatcher = patternOfAddress.matcher(leadAddress);
                                if (!addressMatcher.matches()){

                                    throw new IllegalArgumentException("address input is invalid");
                                }



                                System.out.print("input your gender" + " ");
                                boolean leadGender = input.nextBoolean();




//                        System.out.print("input codeString of the lead");
//                        String interactionLead = input.nextLine();
//                        boolean isStringcodeOfleadNotadded = true;
//                        try{
//                            Scanner fileReading = new Scanner(new File("lead.csv"));
//
//                            while (fileReading.hasNext()){
//                                String eachLine = fileReading.nextLine();
//                                if (eachLine.split(",")[0].equals(interactionLead)){
//                                    isStringcodeOfleadNotadded = false;
//                                }
//                                else {
//                                    isStringcodeOfleadNotadded = true;
//                                }
//                            }
//                            fileReading.close();
//                            if (isStringcodeOfleadNotadded){
//                                throw new IllegalArgumentException();
//                            }
//
//
//                        }
//                        catch (FileNotFoundException fileNotfoundException){
//                            break;
//                        }



                                Lead lead = new Lead(codeString, leadName, date,leadGender,leadPhone, leadEmail, leadAddress);
                                lead.fillList();

                            }
                            catch (Exception exception){
                                System.out.print(exception.getMessage());
                                continue;
                            }
                            System.out.print("Successful");
                            break;
                        }
                    }
                    else if(readingInputsanitized.equals("delete")){

                        if (isThereaList){
                            while (true){
                                System.out.print('\n');
                                System.out.print("codeString" + " ");
                                Scanner codeStringinput = new Scanner(System.in);
                                String readingCodestringInput = codeStringinput.nextLine();
                                boolean isCodestringAdded = false;
                                for (Lead lead: listOfleads){
                                    if (lead.getCodeString().equals(readingCodestringInput.toLowerCase().trim())){
                                        isCodestringAdded = true;
                                    }
                                }
                                if (isCodestringAdded){
                                    Lead.deleteLead(listOfleads, readingCodestringInput);

                                    if(isThereaList1){
                                        try {
                                            FileWriter fileWriter = new FileWriter("interaction.csv");
                                            fileWriter.write(interactionTitle);
                                            fileWriter.write('\n');
                                            for (Interaction interaction: listOfinteraction){
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.setTime(interaction.getDate());
                                                if(interaction.getLead().getCodeString().equals(readingCodestringInput)){

                                                }
                                                else {
                                                    fileWriter.write(interaction.getStringCode());
                                                    fileWriter.write(',');
                                                    fileWriter.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                                                    fileWriter.write(',');
                                                    fileWriter.write(interaction.getLead().getCodeString());
                                                    fileWriter.write(',');
                                                    fileWriter.write(interaction.getMean());
                                                    fileWriter.write(',');
                                                    fileWriter.write(interaction.getStatus());
                                                    fileWriter.write('\n');
                                                }
                                                System.out.print("testing");

                                            }
                                            fileWriter.close();



                                        }
                                        catch (IOException ioException){

                                        }
                                    }
                                    System.out.print("Successful");
                                    break;
                                }
                                System.out.print("codeString is not in the file or has not added");

                            }
                        }
                        else{
                            System.out.print("the list needs to be filled");
                            continue;
                        }
                    }
                    else if (readingInputsanitized.equals("update")){

                        if(isThereaList){
                            while(true){
                                System.out.print('\n');
                                System.out.print("codeString" + " ");
                                Scanner codeStringinput = new Scanner(System.in);
                                String readingCodestringInput = codeStringinput.nextLine();
                                boolean isCodestringAdded = false;
                                int secondIndex = 0;
                                int count = 0;
                                for (Lead lead: listOfleads){
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
                                        Scanner informationSectioninput = new Scanner(System.in);
                                        String readingInformationsectionInput = informationSectioninput.nextLine();
                                        Scanner newUpdateinput = new Scanner(System.in);
                                        String readingInformationsectionInputsanitized = readingInformationsectionInput.toLowerCase().trim();
                                        if(readingInformationsectionInputsanitized.equals("name")){
                                            while (true){
                                                System.out.print("new update" + " ");
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                String readingNewupdateInputsanitized = readingInputsanitized.toLowerCase().trim();
                                                try {

                                                    Pattern patternOfname = Pattern.compile("[a-z ]+");
                                                    Matcher nameMatcher = patternOfname.matcher(readingNewupdateInputsanitized);
                                                    if (nameMatcher.matches()){

                                                    }
                                                    else {
                                                        throw new IllegalArgumentException("name input is invalid");
                                                    }

                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    System.out.print(illegalArgumentexception.getMessage());
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setName(readingNewupdateInput);
                                                break;
                                            }
                                        }
                                        else if(readingInformationsectionInputsanitized.equals("birthdate")){
                                            while (true){
                                                System.out.print("new update" + " ");
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
                                                catch (Exception exception){
                                                    System.out.print(exception.getMessage());
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setBirthdate(date);
                                                break;
                                            }

                                        }

                                        else if(readingInformationsectionInputsanitized.equals("gender")){
                                            while (true){


                                                try {
                                                    System.out.print("new update" + " ");
                                                    boolean readingNewupdateInput = newUpdateinput.nextBoolean();
                                                    listOfleads[secondIndex].setGender(readingNewupdateInput);
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    System.out.print(illegalArgumentexception.getMessage());
                                                    continue;
                                                }

                                                break;
                                            }

                                        }
                                        else if(readingInformationsectionInputsanitized.equals("phone")){
                                            while (true){
                                                System.out.print("new update" + " ");
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                try {
                                                    Pattern patternOfphone = Pattern.compile("[0-9 ]+");
                                                    Matcher phoneMatcher = patternOfphone.matcher(readingNewupdateInput);
                                                    if (!phoneMatcher.matches()){
                                                        throw new IllegalArgumentException("phone input is invalid");
                                                    }
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    System.out.print(illegalArgumentexception.getMessage());
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setPhone(readingNewupdateInput);
                                                break;
                                            }
                                        }
                                        else if(readingInformationsectionInputsanitized.equals("email")){
                                            while (true){
                                                System.out.print("new update" + " ");
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
                                                listOfleads[secondIndex].setEmail(readingNewupdateInput);
                                                break;
                                            }
                                        }

                                        else if(readingInformationsectionInputsanitized.equals("address")){
                                            while (true){
                                                System.out.print("new update" + " ");
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
                                                listOfleads[secondIndex].setAddress(readingNewupdateInput);
                                                break;
                                            }
                                        }

                                        else {
                                            System.out.print("section input is not in the system");
                                            continue;
                                        }



                                        break;
                                    }

                                    Lead.writeLead(listOfleads);

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


                    else if(readingInputsanitized.equals("exit")){
                        break;
                    }
                    else {
                        continue;
                    }







                }
            }
            else if(readingFunctioninput1Sanitized.equals("interaction")){
                if(isLeadinformationAdded){



                    while (true){


                        String leadTitle = "stringCode,name,birthdate,gender,phone,email,address";
                        String interactionTitle = "codeString,date,stringCodeofLead,mean,status";
                        int lengthOfarray1 = 0;
                        boolean isThereaList1 = false;
                        try{
                            Scanner fileReading = new Scanner(new File("lead.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                lengthOfarray1 = lengthOfarray1 + 1;
                            }
                            if(lengthOfarray1>1){
                                isThereaList1 = true;
                            }
                            fileReading.close();

                        }
                        catch (FileNotFoundException fileNotfoundException){
                            lengthOfarray1 = lengthOfarray1 + 1;
                        }
                        Lead[] listOfleads = new Lead[lengthOfarray1 - 1];
                        int index1 = 0;

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
                                        listOfleads[index1] = lead;
                                    }
                                    else {
                                        Lead lead = new Lead(theArray[0],theArray[1],date,false,theArray[4],theArray[5],theArray[6]);
                                        listOfleads[index1] = lead;
                                    }

                                    index1 += 1;

                                }

                            }
                            fileReading.close();


                        }catch (Exception exception){

                        }







                        int lengthOfarray = 0;
                        boolean isThereaList = false;
                        try{
                            Scanner fileReading = new Scanner(new File("interaction.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                lengthOfarray = lengthOfarray + 1;
                            }
                            if(lengthOfarray>1){
                                isThereaList = true;
                            }
                            fileReading.close();

                        }
                        catch (FileNotFoundException fileNotfoundException){
                            lengthOfarray = lengthOfarray + 1;
                        }
                        Interaction[] listOfinteraction = new Interaction[lengthOfarray - 1];
                        int index = 0;

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
                                    for (Lead lead: listOfleads){
                                        if (lead.getCodeString().equals(theArray[2])){
                                            Interaction interaction = new Interaction(theArray[0],date,lead,theArray[3],theArray[4]);
                                            listOfinteraction[index] = interaction;
                                        }
                                    }

                                    index += 1;

                                }


                            }
                            fileReading.close();
                        }catch (Exception exception){

                        }



                        Scanner functionInput = new Scanner(System.in);
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
                        System.out.print("Type view for viewing, add for adding, delete for deleting, update for updating and exit for stopping");

                        String readingInput = functionInput.nextLine();
                        String readingInputsanitized = readingInput.toLowerCase().trim();
                        if (readingInputsanitized.equals("view")){
                            if (isThereaList){
                                Interaction.viewInteraction(listOfinteraction);
                            }
                            else {
                                System.out.print("the file is empty or not founded");
                                continue;
                            }
                        }
                        else if(readingInputsanitized.equals("add")){
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
                                    System.out.print("face to face, message, socialmedia or facebook" + " ");
                                    String interactionMean = input.nextLine();
                                    String interactionMeansanitized = interactionMean.trim().toLowerCase();
                                    if(!(interactionMeansanitized.equals("ftf")||interactionMeansanitized.equals("facetoface")||interactionMeansanitized.equals("face-to-face")||interactionMeansanitized.equals("message")||interactionMeansanitized.equals("socialmedia")||interactionMeansanitized.equals("facebook"))){
                                        throw new IllegalArgumentException("interactionMean input is invalid");
                                    }




                                    System.out.print("input the status");
                                    System.out.print('\n');
                                    System.out.print("neutral, negative or positive" + " ");
                                    String interactionStatus = input.nextLine();
                                    if (interactionStatus.trim().toLowerCase().equals("neutral")||interactionStatus.trim().toLowerCase().equals("negative")||interactionStatus.trim().toLowerCase().equals("positive")){

                                    }
                                    else {
                                        throw new IllegalArgumentException("interactionStatus input is invalid");
                                    }

                                    for (Lead lead: listOfleads){
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
                        else if(readingInputsanitized.equals("delete")){

                            if (isThereaList){
                                while (true){
                                    System.out.print('\n');
                                    System.out.print("codeString" + " ");
                                    Scanner codeStringinput = new Scanner(System.in);
                                    String readingCodestringInput = codeStringinput.nextLine();
                                    boolean isCodestringAdded = false;
                                    for (Interaction interaction: listOfinteraction){
                                        if (interaction.getStringCode().equals(readingCodestringInput.toLowerCase().trim())){
                                            isCodestringAdded = true;
                                        }
                                    }
                                    if (isCodestringAdded){
                                        Interaction.deleteInteraction(listOfinteraction, readingCodestringInput);

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
                        else if (readingInputsanitized.equals("update")){

                            if(isThereaList){
                                while(true){
                                    System.out.print('\n');
                                    System.out.print("codeString" + " ");
                                    Scanner codeStringinput = new Scanner(System.in);
                                    String readingCodestringInput = codeStringinput.nextLine();
                                    boolean isCodestringAdded = false;
                                    int secondIndex = 0;
                                    int count = 0;
                                    for (Interaction interaction: listOfinteraction){
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
                                            Scanner informationSectioninput = new Scanner(System.in);
                                            String readingInformationsectionInput = informationSectioninput.nextLine();
                                            Scanner newUpdateinput = new Scanner(System.in);
                                            String readingInformationsectionInputsanitized = readingInformationsectionInput.toLowerCase().trim();
                                            if (readingInformationsectionInputsanitized.equals("date")){
                                                while (true){

                                                    System.out.print("new update" + " ");
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
                                                    catch (Exception exception){
                                                        System.out.print(exception.getMessage());
                                                        continue;
                                                    }

                                                    listOfinteraction[secondIndex].setDate(date);
                                                    break;
                                                }

                                            }
                                            else if(readingInformationsectionInputsanitized.equals("lead")){
                                                while (true){

                                                    System.out.print("new update" + " ");
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
                                                    for (Lead lead: listOfleads){
                                                        if (lead.getCodeString().equals(readingNewupdateInput)){
                                                            listOfinteraction[secondIndex].setLead(lead);
                                                        }
                                                    }

                                                    break;
                                                }
                                            }
                                            else if(readingInformationsectionInputsanitized.equals("mean")){
                                                while (true){

                                                    System.out.print("new update" + " ");
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
                                                    listOfinteraction[secondIndex].setMean(readingNewupdateInput);
                                                    break;
                                                }
                                            }
                                            else if(readingInformationsectionInputsanitized.equals("status")){
                                                while (true){

                                                    System.out.print("new update" + " ");
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
                                                    listOfinteraction[secondIndex].setStatus(readingNewupdateInput);
                                                    break;
                                                }
                                            }
                                            else {
                                                continue;
                                            }



                                            break;
                                        }

                                        Interaction.writeInteraction(listOfinteraction);

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


                        else if(readingInputsanitized.equals("exit")){
                            break;
                        }
                        else {
                            continue;
                        }







                    }



                }
                else {
                    System.out.print("the lead list needs to be filled");
                    continue;
                }
            }

            else if(readingFunctioninput1Sanitized.equals("report")){
                if(isLeadinformationAdded){
                    while(true){

                        String leadTitle = "stringCode,name,birthdate,gender,phone,email,address";
                        String interactionTitle = "codeString,date,stringCodeofLead,mean,status";
                        boolean isInteractioninformationAdded = false;
                        int countLines = 0;
                        try {
                            Scanner fileReading = new Scanner(new File("interaction.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                countLines += 1;
                            }
                            fileReading.close();
                            if(countLines>1){
                                isInteractioninformationAdded = true;
                            }

                        }
                        catch (FileNotFoundException fileNotfoundException){

                        }

                        Scanner functionInput = new Scanner(System.in);
                        System.out.print("Welcome to CRM");
                        System.out.print('\n');
                        System.out.print("- View the report of the lead number by age");
                        System.out.print('\n');
                        System.out.print("- View the report of the interaction number of different status by date");
                        System.out.print('\n');
                        System.out.print("- View the report of the interaction number by date ");
                        System.out.print('\n');
                        System.out.print("Type view lead for viewing the lead number, view status for viewing the interaction number of different status, view interaction for viewing the interaction number or exit for stopping");
                        String readingFunctioninput = functionInput.nextLine();
                        String readingFunctioninputSanitized = readingFunctioninput.trim().toLowerCase();

                        if(readingFunctioninputSanitized.equals("view lead")){
                            long count0_10 = 0;
                            long count10_20 = 0;
                            long count20_60 = 0;
                            long count60 = 0;
                            Date currentTime = new Date();
                            long currentSeconds = currentTime.getTime()/1000;
                            try{
                                Scanner fileReading = new Scanner(new File("lead.csv"));
                                while(fileReading.hasNext()){
                                    String eachLine = fileReading.nextLine();
                                    if(eachLine.equals(leadTitle)){

                                    }
                                    else {
                                        String dob = eachLine.split(",")[2];
                                        SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = getFormat.parse(dob);
                                        long age = (currentSeconds-(date.getTime()/1000))/(24*60*60*365);
                                        if(age>=0 && age<10){
                                            count0_10 +=1;
                                        }else if(age>=10 && age <20){
                                            count10_20+=1;
                                        }else if(age>=20 && age <60){
                                            count20_60+=1;
                                        }else{
                                            count60+=1;
                                        }

                                    }

                                }
                                fileReading.close();
                                System.out.printf("%20s","0-10  (years old)");
                                System.out.printf("%20s","10-20  (years old)");
                                System.out.printf("%20s","20-60  (years old)");
                                System.out.printf("%20s%n",">60  (years old)");
                                System.out.printf("%20s",count0_10);
                                System.out.printf("%20s",count10_20);
                                System.out.printf("%20s",count20_60);
                                System.out.printf("%20s%n",count60);
                            }catch (Exception exception) {

                            }

                        }

                        else if(readingFunctioninputSanitized.equals("view status")){
                            if(isInteractioninformationAdded){
                                while (true){
                                    long countPositive = 0;
                                    long countNeutral = 0;
                                    long countNegative = 0;
                                    String[] theArrayofMonthtranslation = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


                                    try{
                                        Scanner fileReading = new Scanner(new File("interaction.csv"));
                                        Scanner startDateinput = new Scanner(System.in);
                                        System.out.printf("Input the start date: %n");
                                        String readingStartdateInput = startDateinput.nextLine();
                                        Date startDate = new Date();
                                        if(Integer.parseInt(readingStartdateInput.split("-")[2]) <= 31 && Integer.parseInt(readingStartdateInput.split("-")[1]) <= 12){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            startDate = getFormat.parse(readingStartdateInput);
                                        }
                                        else if(Integer.parseInt(readingStartdateInput.split("-")[0])<=12 && Integer.parseInt(readingStartdateInput.split("-")[1])<=31){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                            startDate = getFormat.parse(readingStartdateInput);
                                        }
                                        else {
                                            throw new Exception("startDate input is invalid");
                                        }

                                        Scanner endDateinput = new Scanner(System.in);
                                        System.out.printf("Input the end date: %n");
                                        String readingEnddateInput = endDateinput.nextLine();
                                        Date endDate = new Date();
                                        if(Integer.parseInt(readingEnddateInput.split("-")[2]) <= 31 && Integer.parseInt(readingEnddateInput.split("-")[1]) <= 12){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            endDate = getFormat.parse(readingEnddateInput);
                                        }
                                        else if(Integer.parseInt(readingEnddateInput.split("-")[0])<=12 && Integer.parseInt(readingEnddateInput.split("-")[1])<=31){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                            endDate = getFormat.parse(readingEnddateInput);
                                        }
                                        else {
                                            throw new Exception("endDate input is invalid");
                                        }

                                        if(startDate.compareTo(endDate)<=0){
                                            while (fileReading.hasNext()){
                                                String eachLine = fileReading.nextLine();
                                                if (eachLine.equals(interactionTitle)) {

                                                }
                                                else {
                                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                    Date DateOfInteraction = getFormat.parse(eachLine.split(",")[1]);
                                                    String potential = eachLine.split(",")[4];
                                                    if(DateOfInteraction.compareTo(startDate) >=0 && DateOfInteraction.compareTo(endDate)<=0){
                                                        if(potential.equals("neutral")){
                                                            countNeutral +=1;
                                                        }else if(potential.equals("negative")){
                                                            countNegative+=1;
                                                        }else{
                                                            countPositive +=1;
                                                        }
                                                    }

                                                }

                                            }
                                            fileReading.close();
                                        }
                                        else{
                                            fileReading.close();
                                            throw new Exception();
                                        }
                                        Calendar calendarStartdate = Calendar.getInstance();
                                        calendarStartdate.setTime(startDate);

                                        Calendar calendarEnddate = Calendar.getInstance();
                                        calendarEnddate.setTime(endDate);

                                        System.out.print("input" + " " + ":" + " " + theArrayofMonthtranslation[calendarStartdate.get(Calendar.MONTH)] + " " + calendarStartdate.get(Calendar.YEAR) + " " + "-" + " " + theArrayofMonthtranslation[calendarEnddate.get(Calendar.MONTH)] + " " + calendarEnddate.get(Calendar.YEAR));
                                        System.out.print('\n');
                                        System.out.printf("%20s","Positive");
                                        System.out.printf("%20s","Neutral");
                                        System.out.printf("%20s%n","Negative");
                                        System.out.printf("%20s",countPositive);
                                        System.out.printf("%20s",countNeutral);
                                        System.out.printf("%20s%n",countNegative);


                                    }
                                    catch (Exception exception){
                                        System.out.print(exception.getMessage());
                                        continue;
                                    }
                                    break;




                                }
                            }
                            else {
                                System.out.print("the interaction list needs to be added");
                                continue;
                            }
                        }

                        else if(readingFunctioninputSanitized.equals("view interaction")){
                            if(isInteractioninformationAdded){

                                while (true){
                                    String[] theArrayofMonthtranslation = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

                                    try{
                                        Scanner fileReading = new Scanner(new File("interaction.csv"));
                                        Scanner startDateinput = new Scanner(System.in);
                                        System.out.println("INPUT START DATE: ");
                                        String readingStartdateInput = startDateinput.nextLine();
                                        Date startDate = new Date();
                                        if(Integer.parseInt(readingStartdateInput.split("-")[2]) <= 31 && Integer.parseInt(readingStartdateInput.split("-")[1]) <= 12){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            startDate = getFormat.parse(readingStartdateInput);
                                        }
                                        else if(Integer.parseInt(readingStartdateInput.split("-")[0])<=12 && Integer.parseInt(readingStartdateInput.split("-")[1])<=31){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                            startDate = getFormat.parse(readingStartdateInput);
                                        }
                                        else {
                                            throw new Exception("startDate input is invalid");
                                        }

                                        Scanner endDateinput = new Scanner(System.in);
                                        System.out.println("INPUT END DATE: ");
                                        String readingEnddateInput = endDateinput.nextLine();
                                        Date endDate = new Date();
                                        Date now = new Date();

                                        if(Integer.parseInt(readingEnddateInput.split("-")[2]) <= 31 && Integer.parseInt(readingEnddateInput.split("-")[1]) <= 12){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            endDate = getFormat.parse(readingEnddateInput);
                                        }
                                        else if(Integer.parseInt(readingEnddateInput.split("-")[0])<=12 && Integer.parseInt(readingEnddateInput.split("-")[1])<=31){
                                            SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                            endDate = getFormat.parse(readingEnddateInput);
                                        }
                                        else {
                                            throw new Exception("endDate input is invalid");
                                        }


                                        if(startDate.compareTo(endDate)<0 && endDate.compareTo(now) <= 0){

                                            String storedString = "";
                                            long countForcheck = 0;
                                            while(fileReading.hasNext()){
                                                String eachLine = fileReading.nextLine();
                                                if(eachLine.equals(interactionTitle)){

                                                }
                                                else {

                                                    String dateOfInteractionString = eachLine.split(",")[1];
                                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                    Date dateOfInteraction = getFormat.parse(dateOfInteractionString);
                                                    if(dateOfInteraction.compareTo(startDate)>=0 && dateOfInteraction.compareTo(endDate)<=0){
                                                        storedString = storedString + dateOfInteractionString +",";
                                                        countForcheck += 1;
                                                    }

                                                }

                                            }
                                            if(!(countForcheck > 0)){
                                                break;
                                            }
                                            Date[] dates = new Date[storedString.split(",").length];
                                            for(int i = 0; i <storedString.split(",").length; ++i ){
                                                try{
                                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                    Date date = getFormat.parse(storedString.split(",")[i]);
                                                    dates[i] = date;
                                                }catch (ParseException parseException){

                                                }
                                            }
                                            Arrays.sort(dates);
                                            for (int i = 0; i< dates.length; ++i){
                                                System.out.print(dates[i]);
                                                System.out.print('\n');
                                            }

                                            String[] NewStoredString = new String[dates.length];
                                            for (int i = 0; i<dates.length; ++i){
                                                String NewMonthYear = dates[i].toString().split(" ")[1] + " " + dates[i].toString().split(" ")[5];
                                                System.out.println(NewMonthYear);
                                                NewStoredString[i] = NewMonthYear;
                                            }
                                            int countLength = 1;
                                            for(int i =0; i< NewStoredString.length-1; ++i){
                                                if(!NewStoredString[i].equals(NewStoredString[i+1])){
                                                    countLength += 1;
                                                }
                                            }
                                            System.out.println("Length :"+ countLength);
                                            String[] titles = new String[countLength];
                                            int[] countInteractions = new int[countLength];
                                            int index = 0;
                                            titles[0] = NewStoredString[0];
                                            for(int i = 0; i < NewStoredString.length-1; ++i){
                                                if(!NewStoredString[i].equals(NewStoredString[i+1])){
                                                    index+=1;
                                                    titles[index] = NewStoredString[i+1];
                                                }
                                            }
                                            for(int i = 0; i < countLength; ++i){
                                                int count = 0;
                                                for(int j = 0; j < NewStoredString.length; ++j){
                                                    if(titles[i].equals(NewStoredString[j])){
                                                        count +=1;
                                                    }
                                                }
                                                countInteractions[i] = count;
                                            }

                                            Calendar calendarStartdate = Calendar.getInstance();
                                            calendarStartdate.setTime(startDate);

                                            Calendar calendarEnddate = Calendar.getInstance();
                                            calendarEnddate.setTime(endDate);

                                            System.out.print("input" + " " + ":" + " " + theArrayofMonthtranslation[calendarStartdate.get(Calendar.MONTH)] + " " + calendarStartdate.get(Calendar.YEAR) + " " + "-" + " " + theArrayofMonthtranslation[calendarEnddate.get(Calendar.MONTH)] + " " + calendarEnddate.get(Calendar.YEAR));


                                            System.out.print('\n');

//
//            System.out.println(titles[0] + counts[0]);
//            System.out.println(titles[1]+ counts[1]);
//            System.out.println(titles[2]+counts[2]);
//            System.out.println(titles[3]+counts[3]);
                                            for(int i = 0; i < titles.length; ++i ){
                                                System.out.printf("%20s", titles[i]);
                                            }
                                            System.out.printf("%n");
                                            for(int i = 0; i < countInteractions.length; ++i){
                                                System.out.printf("%20s",countInteractions[i]);
                                            }
                                            System.out.printf("%n");
                                            fileReading.close();
                                        }
                                        else{
                                            fileReading.close();
                                            throw new Exception();

                                        }



                                    }
                                    catch (Exception exception){
                                        System.out.print(exception.getMessage());
                                        continue;
                                    }
                                    break;




                                }

                            }
                            else {
                                System.out.print("the interaction list needs to be added");
                                continue;
                            }


                        }


                        else if(readingFunctioninputSanitized.equals("exit")){
                            break;
                        }
                        else {
                            continue;
                        }




                    }
                }
                else{
                    System.out.print("the lead list needs to be filled");
                    continue;
                }
            }

            else if(readingFunctioninput1Sanitized.equals("exit")){
                break;
            }
            else {
                continue;
            }

        }
    }
}