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
                    String newMonthYear = dates[i].toString().split(" ")[1] + dates[i].toString().split(" ")[5];
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
