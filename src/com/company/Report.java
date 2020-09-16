package com.company;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Report implements interfacesOfReport {
    String leadTitle = "stringCode,name,birthDate,gender,phone,email,address";
    String interactionTitle = "codeString,date,stringCodeOfLead,mean,status";
    String[] arrayOfMonthsTranslation = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    @Override
    public void viewLeads() {
        long count0_10 = 0;
        long count10_20 = 0;
        long count20_60 = 0;
        long count60 = 0;
        LocalDate now = LocalDate.now();
        try {
            Scanner fileReading = new Scanner(new File("lead.csv"));
            while (fileReading.hasNext()) {
                String eachLine = fileReading.nextLine();
                if (eachLine.equals(leadTitle)) {

                } else {
                    String dob = eachLine.split(",")[2];
                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = getFormat.parse(dob);
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH) + 1;
                    int day = c.get(Calendar.DATE);
                    LocalDate birthDate = LocalDate.of(year, month, day);
                    Period diff = Period.between(birthDate, now);
                    int age = diff.getYears();
                    System.out.println(age);
                    if (age >= 0 && age < 10) {
                        count0_10 += 1;
                    } else if (age >= 10 && age < 20) {
                        count10_20 += 1;
                    } else if (age >= 20 && age < 60) {
                        count20_60 += 1;
                    } else {
                        count60 += 1;
                    }

                }

            }
            fileReading.close();
            System.out.print("input" + ":" + " " + "no input required");
            System.out.print('\n');
            System.out.printf("%20s", "0-10  (years old)");
            System.out.printf("%20s", "10-20  (years old)");
            System.out.printf("%20s", "20-60  (years old)");
            System.out.printf("%20s%n", ">60  (years old)");
            System.out.printf("%20s", count0_10);
            System.out.printf("%20s", count10_20);
            System.out.printf("%20s", count20_60);
            System.out.printf("%20s%n", count60);
        } catch (Exception exception) {

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
                System.out.print('\n');
                System.out.println("Input the start date: ");
                System.out.print("For example: 2000-07-05");
                System.out.print('\n');
                System.out.print("(Please follow the pattern yyyy-MM-dd)" + " ");
                String readStartedDateInput = sc.nextLine();
                Date startDate = new Date();
                if(Integer.parseInt(readStartedDateInput.split("-")[2]) <= 31 && Integer.parseInt(readStartedDateInput.split("-")[1]) <= 12){
                    startDate = getFormat.parse(readStartedDateInput);
                }else{
                    throw new Exception("startDate input is invalid\n");
                }
                System.out.println("Input the end date: ");
                System.out.print("For example: 2000-07-08");
                System.out.print('\n');
                System.out.print("(Please follow the pattern yyyy-MM-dd)" + " ");
                String readEndedDateInput = sc.nextLine();
                Date endDate = new Date();
                if(Integer.parseInt(readEndedDateInput.split("-")[2]) <= 31 && Integer.parseInt(readEndedDateInput.split("-")[1]) <= 12){
                    endDate = getFormat.parse(readEndedDateInput);
                }else{
                    throw new Exception("endDate input is invalid\n");
                }
                Date now = new Date();
                if(startDate.compareTo(endDate)<=0 && endDate.compareTo(now)<=0){
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
                    throw new Exception("Date input is invalid");
                }
                Calendar calendarStartdate = Calendar.getInstance();
                calendarStartdate.setTime(startDate);

                Calendar calendarEnddate = Calendar.getInstance();
                calendarEnddate.setTime(endDate);

                System.out.print("input" + ":" + " " + calendarStartdate.get(Calendar.DATE) + " " + arrayOfMonthsTranslation[calendarStartdate.get(Calendar.MONTH)] + " " + calendarStartdate.get(Calendar.YEAR) + " " + "-" + " " + calendarEnddate.get(Calendar.DATE) + " " + arrayOfMonthsTranslation[calendarEnddate.get(Calendar.MONTH)] + " " + calendarEnddate.get(Calendar.YEAR));
                System.out.print('\n');
                System.out.printf("%20s","Positive");
                System.out.printf("%20s","Neutral");
                System.out.printf("%20s%n","Negative");
                System.out.printf("%20s",countPositive);
                System.out.printf("%20s",countNeutral);
                System.out.printf("%20s%n",countNegative);


            }
            catch (ArrayIndexOutOfBoundsException arrayIndexoutOfboundsException){
                System.out.print("Date input is invalid");
                continue;
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
            try{
                Scanner fileReading = new Scanner(new File("interaction.csv"));
                System.out.print('\n');
                System.out.println("Input the start date: ");
                System.out.print("For example: 2000-07-05");
                System.out.print('\n');
                System.out.print("(Please follow the pattern yyyy-MM-dd)" + " ");
                String readStartedDateInput = sc.nextLine();
                Date startDate = new Date();
                if(Integer.parseInt(readStartedDateInput.split("-")[2]) <= 31 && Integer.parseInt(readStartedDateInput.split("-")[1]) <= 12){
                    startDate = getFormat.parse(readStartedDateInput);
                }else{
                    throw new Exception("startDate input is invalid \n");
                }
                System.out.println("Input the end date: ");
                System.out.print("For example: 2000-07-08");
                System.out.print('\n');
                System.out.print("(Please follow the pattern yyyy-MM-dd)" + " ");
                String readEndedDateInput = sc.nextLine();
                Date endDate = new Date();
                if(Integer.parseInt(readEndedDateInput.split("-")[2]) <= 31 && Integer.parseInt(readEndedDateInput.split("-")[1]) <= 12){
                    endDate = getFormat.parse(readEndedDateInput);
                }else{
                    throw new Exception("endDate input is invalid \n");
                }
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

                    System.out.print("input" + ":" + " " + calendarStartdate.get(Calendar.DATE) + " " + arrayOfMonthsTranslation[calendarStartdate.get(Calendar.MONTH)] + " " + calendarStartdate.get(Calendar.YEAR) + " " + "-" + " " + calendarEnddate.get(Calendar.DATE) + " " + arrayOfMonthsTranslation[calendarEnddate.get(Calendar.MONTH)] + " " + calendarEnddate.get(Calendar.YEAR));


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
                    throw new Exception("Date input is invalid");

                }
            }
            catch (ArrayIndexOutOfBoundsException arrayIndexoutOfboundsException){
                System.out.print("Date input is invalid");
                continue;
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
