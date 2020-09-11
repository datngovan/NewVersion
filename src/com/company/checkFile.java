package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class checkFile {
    public boolean getBoolean(String pathName){
        int numberOfLines = 0;
        boolean check = false;
        try{
            Scanner fileReading = new Scanner(new File("lead.csv"));

            while (fileReading.hasNext()){
                String eachLine = fileReading.nextLine();
                numberOfLines += 1;
            }

            if (numberOfLines >1){
                check = true;
            }

            fileReading.close();

        }
        catch (FileNotFoundException fileNotfoundException){

        }
        return check;
    }
    public int getLength(String pathName){
        int numberOfLines = 0;
        boolean check = false;
        try{
            Scanner fileReading = new Scanner(new File("lead.csv"));

            while (fileReading.hasNext()){
                String eachLine = fileReading.nextLine();
                numberOfLines += 1;
            }

            if (numberOfLines >1){
                check = true;
            }

            fileReading.close();

        }
        catch (FileNotFoundException fileNotfoundException){
            numberOfLines+=1;
        }
        return numberOfLines;
    }
}
