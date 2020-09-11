
package com.company;



import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Lead implements interfacesOfLead {

    private String codeString;
    private String name;
    private Date birthdate;
    private boolean gender;
    private String phone;
    private String email;
    private String address;

    public Lead(String codeStringinput, String nameInput, Date birthdateInput, boolean genderInput, String phoneInput, String emailInput, String addressInput){
        this.codeString = codeStringinput;
        this.name = nameInput;
        this.birthdate = birthdateInput;
        this.gender = genderInput;
        this.phone = phoneInput;
        this.email = emailInput;
        this.address = addressInput;

    }
    private Lead(){

    }


    public void setCodeString(String codeString) {
        this.codeString = codeString;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCodeString() {
        return codeString;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public boolean getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }




    public void fillList() throws Exception {

        String leadTitle = "stringCode,name,birthdate,gender,phone,email,address";


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.birthdate);

        try{
            Scanner fileReading = new Scanner(new File("lead.csv"));
            fileReading.close();

            try{
                FileWriter writing = new FileWriter("lead.csv",true);
                writing.write(this.codeString);
                writing.write(',');
                writing.write(this.name);
                writing.write(',');
                writing.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                writing.write(',');
                writing.write(String.valueOf(this.gender));
                writing.write(',');
                writing.write(this.phone);
                writing.write(',');
                writing.write(this.email);
                writing.write(',');
                writing.write(this.address);
                writing.write('\n');
                writing.close();
            }
            catch(IOException ioException){
                throw new Exception();
            }

        }
        catch (FileNotFoundException fileNotfoundException){
            try{
                FileWriter fileWriter = new FileWriter("lead.csv");
                fileWriter.write(leadTitle);
                fileWriter.write('\n');
                fileWriter.write(this.codeString);
                fileWriter.write(',');
                fileWriter.write(this.name);
                fileWriter.write(',');
                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                fileWriter.write(',');
                fileWriter.write(String.valueOf(this.gender));
                fileWriter.write(',');
                fileWriter.write(this.phone);
                fileWriter.write(',');
                fileWriter.write(this.email);
                fileWriter.write(',');
                fileWriter.write(this.address);
                fileWriter.write('\n');
                fileWriter.close();
            }
            catch (IOException ioException){
                throw new Exception();
            }
        }
    }


    @Override
    public void view(Lead[] leads) {
        for (Lead lead: leads){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lead.getBirthdate());
            System.out.print(lead.getCodeString());
            System.out.print(',');
            System.out.print(lead.getName());
            System.out.print(',');
            System.out.print(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
            System.out.print(',');
            System.out.print(lead.getGender());
            System.out.print(',');
            System.out.print(lead.getPhone());
            System.out.print(',');
            System.out.print(lead.getEmail());
            System.out.print(',');
            System.out.print(lead.getAddress());
            System.out.print('\n');
        }
    }
    public static void viewLead(Lead[] leads){
        Lead lead = new Lead();
        lead.view(leads);
    }

    @Override
    public void delete(Lead[] leads, String codeString) {
        String leadTitle = "stringCode,name,birthdate,gender,phone,email,address";
        try {
            FileWriter fileWriter = new FileWriter("lead.csv");
            fileWriter.write(leadTitle);
            fileWriter.write('\n');
            for (Lead lead: leads){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(lead.getBirthdate());
                if(lead.getCodeString().equals(codeString)){

                }
                else {
                    fileWriter.write(lead.getCodeString());
                    fileWriter.write(',');
                    fileWriter.write(lead.getName());
                    fileWriter.write(',');
                    fileWriter.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                    fileWriter.write(',');
                    fileWriter.write(String.valueOf(lead.getGender()));
                    fileWriter.write(',');
                    fileWriter.write(lead.getPhone());
                    fileWriter.write(',');
                    fileWriter.write(lead.getEmail());
                    fileWriter.write(',');
                    fileWriter.write(lead.getAddress());
                    fileWriter.write('\n');
                }
            }
            fileWriter.close();
        }
        catch (Exception exception){

        }
    }


    public static void deleteLead(Lead[] leads, String codeString){
        Lead lead = new Lead();
        lead.delete(leads, codeString);
    }

    @Override
    public void write(Lead[] leads) {
        String leadTitle = "stringCode,name,birthdate,gender,phone,email,address";
        try {
            FileWriter fileWriter = new FileWriter("lead.csv");
            fileWriter.write(leadTitle);
            fileWriter.write('\n');
            for (Lead lead: leads){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(lead.getBirthdate());
                fileWriter.write(lead.getCodeString());
                fileWriter.write(',');
                fileWriter.write(lead.getName());
                fileWriter.write(',');
                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                fileWriter.write(',');
                fileWriter.write(String.valueOf(lead.getGender()));
                fileWriter.write(',');
                fileWriter.write(lead.getPhone());
                fileWriter.write(',');
                fileWriter.write(lead.getEmail());
                fileWriter.write(',');
                fileWriter.write(lead.getAddress());
                fileWriter.write('\n');
            }
            fileWriter.close();
        }
        catch (IOException ioException){

        }
    }

    public static void writeLead(Lead[] leads){
        Lead lead = new Lead();
        lead.write(leads);
    }

}