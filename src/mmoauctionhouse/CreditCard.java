/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 *
 * @author Neil & Chris
 */
public class CreditCard {
    private String firstName;
    private String lastName;
    private String cardNo;
    private String expDate;
    private String csvNo;
    
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
   
    /*
    authors Neil & Chris
    Hasn't been tested yet.
    */
    public static boolean validateCreditCardInformation(String cardNo, String csvNo, String expDate)
    {
        Pattern visa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
        Pattern mastercard = Pattern.compile("^5[1-5][0-9]{14}$");
        Pattern date = Pattern.compile("[0-9]{2}/[0-9]{2}");
        
        if (visa.matcher(cardNo).matches() || mastercard.matcher(cardNo).matches())
        {
            //System.out.println("Correct CardNo!");
            if (csvNo.length() == 3)
            {
               //System.out.println("Correct CSV!");
                if (date.matcher(expDate).matches())
                {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    
                    //System.out.print("Card Added!");
                    return true;
                }
            }
        }
        return false;
    }
}
