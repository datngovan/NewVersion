package com.company;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Report implements interfacesOfReport {
    String leadTitle = "stringCode,name,birthDate,gender,phone,email,address";
    String interactionTitle = "codeString,date,stringCodeOfLead,mean,status";

    @Override
    public void viewLeads() {
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
    public static void printViewLeads(){
        Report report = new Report();
        report.viewLeads();
    }

    @Override
    public void viewStatus() {
        while (true){
            Scanner sc = new Scanner(System.in);
            SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
            long countPositive = 0;
            long countNeutral = 0;
            long countNegative = 0;
            String[] arrayOfMonthsTranslation = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            try{
                Scanner fileReading = new Scanner(new File("interaction.csv"));
                System.out.println("Input the start date: ");
                String readStartedDateInput = sc.nextLine();
                Date startDate = getFormat.parse(readStartedDateInput);
                System.out.println("Input the end date: ");
                String readEndedDateInput = sc.nextLine();
                Date endDate = getFormat.parse(readEndedDateInput);
                Date now = new Date();
                if(startDate.compareTo(endDate)<=0){
                    while (fileReading.hasNext()){
                        String eachLine = fileReading.nextLine();
                        if(eachLine.equals(interactionTitle)){

                        }else{
                            Date dateOfInteraction = getFormat.parse(eachLine.split(",")[1]);
                            System.out.println(dateOfInteraction);
                            String potential = eachLine.split(",")[4];
                            if(dateOfInteraction.compareTo(startDate)>=0 && dateOfInteraction.compareTo(endDate)<=0){
                                if (potential.equals("neutral")){
                                    countNeutral +=1;
                                }else if(potential.equals("negative")){
                                    countNegative+=1;
                                }else if(potential.equals("positive")){
                                    countPositive+=1;
                                }else{

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

                System.out.print("input" + " " + ":" + " " + arrayOfMonthsTranslation[calendarStartdate.get(Calendar.MONTH)] + " " + calendarStartdate.get(Calendar.YEAR) + " " + "-" + " " + arrayOfMonthsTranslation[calendarEnddate.get(Calendar.MONTH)] + " " + calendarEnddate.get(Calendar.YEAR));
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

    public static void printViewStatus(){
        Report report = new Report();
        report.viewStatus();
    }
    @Override
    public void viewInteractions() {
        while (true){
            String interactionTitle = "codeString,date,stringCodeOfLead,mean,status";
            Scanner sc = new Scanner(System.in);
            SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
            String[] arrayOfMonthsTranslation = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            try{
                Scanner fileReading = new Scanner(new File("interaction.csv"));
                System.out.println("Input the start date: ");
                String readStartedDateInput = sc.nextLine();
                Date startDate = getFormat.parse(readStartedDateInput);
                System.out.println("Input the end date: ");
                String readEndedDateInput = sc.nextLine();
                Date endDate = getFormat.parse(readEndedDateInput);
                Date now = new Date();
                if(startDate.compareTo(endDate)<=0 && endDate.compareTo(now)<=0){
                    String storedString = "";
                    long countForCheck = 0;
                    while (fileReading.hasNext()){
                        String eachLine = fileReading.nextLine();
                        if(eachLine.equals(interactionTitle)){

                        }else{
                            String dateOfInteractionString = eachLine.split(",")[1];
                            Date dateOfInteraction = getFormat.parse(dateOfInteractionString);
                            if(dateOfInteraction.compareTo(startDate)>=0 && dateOfInteraction.compareTo(endDate)<=0){
                                storedString = storedString + dateOfInteractionString +",";
                                countForCheck +=1;
                            }
                        }
                    }
                    if(!(countForCheck>0)){
                        break;
                    }
                    Date[] dates = new Date[storedString.split(",").length];
                    for(int i = 0; i<storedString.split(",").length; i++){
                        try{
                            Date date = getFormat.parse(storedString.split(",")[i]);
                            dates[i] = date;
                        }catch (ParseException parseException){

                        }
                    }
                    Arrays.sort(dates);
                    for(int i = 0; i<dates.length; ++i){
                        System.out.print(dates[i]);
                        System.out.println('\n');
                    }
                    String[] newStoredString = new String[dates.length];
                    for(int i = 0; i<dates.length; ++i){
                        String newMonthYear = dates[i].toString().split(" ")[1] +" "+ dates[i].toString().split(" ")[5];
                        System.out.println(newMonthYear);
                        newStoredString[i] = newMonthYear;
                    }
                    int countLength = 1;
                    for(int i = 0; i<newStoredString.length-1;++i){
                        if(!newStoredString[i].equals(newStoredString[i+1])){
                            countLength+=1;
                        }
                    }
                    System.out.println("length: " + countLength);
                    String[] titles = new String[countLength];
                    int[] countInteraction = new int[countLength];
                    int index = 0;
                    titles[0] = newStoredString[0];
                    for(int i = 0; i<newStoredString.length-1; ++i){
                        if(!newStoredString[i].equals(newStoredString[i+1])){
                            index +=1;
                            titles[index] = newStoredString[i+1];
                        }
                    }
                    for(int i = 0; i<countLength;++i){
                        int count = 0;
                        for(int j = 0; j<newStoredString.length;++j){
                            if(titles[i].equals(newStoredString[j])){
                                count+=1;
                            }
                        }
                        countInteraction[i] = count;
                    }
                    Calendar calendarStartdate = Calendar.getInstance();
                    calendarStartdate.setTime(startDate);

                    Calendar calendarEnddate = Calendar.getInstance();
                    calendarEnddate.setTime(endDate);

                    System.out.print("input" + " " + ":" + " " + arrayOfMonthsTranslation[calendarStartdate.get(Calendar.MONTH)] + " " + calendarStartdate.get(Calendar.YEAR) + " " + "-" + " " + arrayOfMonthsTranslation[calendarEnddate.get(Calendar.MONTH)] + " " + calendarEnddate.get(Calendar.YEAR));


                    System.out.print('\n');
                    for(int i = 0; i < titles.length; ++i ){
                        System.out.printf("%20s", titles[i]);
                    }
                    System.out.printf("%n");
                    for(int i = 0; i < countInteraction.length; ++i){
                        System.out.printf("%20s",countInteraction[i]);
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

    public static void printViewInteractions(){
        Report report = new Report();
        report.viewInteractions();
    }
}
