/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse.creditcardpackage;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import mmoauctionhouse.Observer;

/**
 *
 * @author Neil & Chris
 */
public class CreditCard implements Observer {
    private String firstName;
    private String lastName;
    private String cardNo;
    private String expDate;
    private String csvNo;
    private boolean canUse = true;
    
    public CreditCard(String firstName, String lastName, String cardNo, String expDate, String csvNo)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNo = cardNo;
        this.expDate = expDate;
        this.csvNo = csvNo;
    } 

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCsvNo() {
        return csvNo;
    }

    public void setCsvNo(String csvNo) {
        this.csvNo = csvNo;
    }
    
    public String toString()
    {
        String cardHidden = this.cardNo.substring(this.cardNo.length() - 4);
        String details = this.firstName + " " + this.lastName + ": Card ending in *" + cardHidden;
        return details;
    }
    
    public String getLastFourDigits()
    {
        return this.cardNo.substring(this.cardNo.length() - 4);
    }
   
    /*
    authors Neil & Chris
    */
    public static boolean validateCreditCardInformation(String cardNo)
        {
            Pattern visa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
            Pattern mastercard = Pattern.compile("^5[1-5][0-9]{14}$");

            if (visa.matcher(cardNo).matches() || mastercard.matcher(cardNo).matches())
            {
                //System.out.println("Correct CardNo!");
                return true;
            }
            System.out.println("Incorrect Card Numebr");
            return false;
        }


    public static boolean validateCsvNo(String csvNo)
    {
    try
    {
        int i = Integer.parseInt(csvNo);
         if (csvNo.length() == 3)
            {
                    return true;
            }
    }
    catch(Exception e)
        {
        System.out.println("Incorrect CSV format");
        return false;
        }
    System.out.println("Incorrect CSV format");
    return false;
    }

    public static boolean vaildateDate(String expDate)
    {
    Pattern date = Pattern.compile("[0-9]{2}/[0-9]{2}");

    if (date.matcher(expDate).matches())
                    {
                        Calendar cal = new GregorianCalendar();
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);
                        year = year % 2000;
                        //System.out.println(month);
                        //System.out.println(year);
                        //System.out.println("calendar created");
                        String [] dates = expDate.split("/");
                        try 
                        {
                            //System.out.println("in try loop");
                            int monthCheck = Integer.parseInt(dates[0]);
                            int yearCheck = Integer.parseInt(dates[1]);
                            //System.out.println("split expdate");
                            if (yearCheck > year)
                                return true;
                            else if (yearCheck == year && monthCheck >= month)
                                return true;
                        }
                        catch (Exception e)
                        {
                            System.out.println("Incorrect Date format");
                            return false;
                        }
                        
                        //System.out.print("Card Added!");
                    }
    System.out.println("Incorrect Date format");
    return false;
    }

    @Override
    public void update(String string) {
        if (cardNo.equals(string))
        {
            canUse = false;
        }
    }


}
