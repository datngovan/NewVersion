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
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testFunction {

    public static void main(String[] args) {
        String interactionTitle = "codeString,date,stringCodeOfLead,mean,status";
        String leadTitle = "stringCode,name,birthDate,gender,phone,email,address";
        long count0_10 = 0;
        long count10_20 = 0;
        long count20_60 = 0;
        long count60 = 0;
        LocalDate now = LocalDate.now();
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
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH) + 1;
                    int day = c.get(Calendar.DATE);
                    LocalDate birthDate = LocalDate.of(year,month,day);
                    Period diff = Period.between(birthDate,now);
                    int age = diff.getYears();
                    System.out.println(age);
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
}
