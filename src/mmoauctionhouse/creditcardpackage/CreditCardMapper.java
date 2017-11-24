/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse.creditcardpackage;

import Control.IFile;
import CreditCard.CreditCard;
import java.io.File;
import java.util.HashSet;


/**
 *
 * @author neil-
 */
public class CreditCardMapper {
    private static IFile ifile = null;
    
    public static CreditCard getCreditCard(String cardNo)
    {
        File cardFile = new File("resources/creditCards.txt");
        String[] cardInfoArr = new String[0];
        boolean foundCard = false;
        CreditCard card = null;
        
        if (CreditCardMapper.ifile != null)
        {
            ifile.get(cardFile.getAbsolutePath());
            String [][] cardDetails = ifile.read();

            for (int i =0;  i< cardDetails.length; i++)
            {
                if (cardDetails[i][1].equals(cardNo))
                {
                    foundCard = true;
                    cardInfoArr = new String[cardDetails[i].length];
                    cardInfoArr = cardDetails[i];
                }
            }
            String firstName = cardInfoArr[2];
            String lastName = cardInfoArr[3];
            String expDate = cardInfoArr[4];
            String csvNo = cardInfoArr[5];
            card = new CreditCard(firstName, lastName, cardNo, expDate, csvNo);
            boolean canUse = false;
            if (cardInfoArr[6].equalsIgnoreCase("true"))
                canUse = true;
            card.setCanUse(canUse);
            
            return card;
        }
        return null;
    }
    
    public static CreditCard[] findAllPlayersCards(String username)
    {
        File cardFile = new File("resources/creditCards.txt");
        CreditCard [] cards = new CreditCard[5];
        
        if (CreditCardMapper.ifile != null)
        {
            ifile.get(cardFile.getAbsolutePath());
            String [][] cardDetails = ifile.read();

            int count = 0;
            if (cardDetails == null)
                return null;
            for (int i =0;  i< cardDetails.length; i++)
            {
                if (cardDetails[i][0].equals(username))
                {
                    CreditCard card = CreditCardMapper.getCreditCard(cardDetails[i][1]);
                    cards[count] = card;
                    count++;
                }
            }
            return cards;
        }
        return null;
    }
    
    public static void addCreditCard(CreditCard card, String username)
    {
        String [][] writeString = new String[1][7];
        String firstName = card.getFirstName();
        String lastName = card.getLastName();
        String cardNo = card.getCardNo();
        String expDate = card.getExpDate();
        String csv = card.getCsvNo();
        String canUse = "" + card.isCanUse();
        writeString[0][0] = username;
        writeString[0][1] = cardNo;
        writeString[0][2] = firstName;
        writeString[0][3] = lastName;
        writeString[0][4] = expDate;
        writeString[0][5] = csv;
        writeString[0][6] = canUse;
        File cardFile = new File("resources/creditCards.txt");
        ifile.put(cardFile.getAbsolutePath());
        ifile.write(writeString);
    }
    
    public static void updateCreditCard(CreditCard card, String username)
    {
        deleteCreditCard(card);
        addCreditCard(card, username);
    }
    
    public static void deleteCreditCard(CreditCard card)
    {
        File cardFile = new File("resources/creditCards.txt");
        String [][] cardDetails = null;
        String [][] writeArray = new String[0][0];
        if (CreditCardMapper.ifile != null)
        {
            ifile.get(cardFile.getAbsolutePath());
            cardDetails = ifile.read();

            int index = 0;
            boolean foundCard = false;
            for (int i =0;  i< cardDetails.length; i++)
            {
                if(cardDetails[i] != null)
                    if (cardDetails[i][1].equals(card.getCardNo()))
                    {
                        index = i;
                        foundCard = true;
                    }
            }
            if (foundCard)
            {
                writeArray = new String[cardDetails.length -1][cardDetails[0].length];
                int countNumRows = 0;
                for (int i = 0; i < cardDetails.length; i++)
                {
                    if (i != index)
                    {
                        writeArray[countNumRows] = cardDetails[i];
                        countNumRows++;
                    }
                }
            }
            ifile.append(false);
            ifile.put(cardFile.getAbsolutePath());
            ifile.write(writeArray);
            ifile.append(true);
        }
    }
    
    public static void setIFile(IFile ifile)
    {
        CreditCardMapper.ifile = ifile;
    }
}
