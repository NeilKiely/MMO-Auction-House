package Control;

import FileReadWrite.TDFile;
import FileReadWrite.CSFile;
import FileReadWrite.ReadWriteControl;
import FileReadWrite.IFileFactory;
import FileReadWrite.SCFile;
import Player.PlayerMapper;

import CreditCard.CreditCardMapper;

/**
 *
 * @author Vilius
 */
public class MMOAuctionHouse {
    public static void main(String[] args) {
        IFileFactory.addIFile("SCFile", new SCFile());
        IFileFactory.addIFile("CSFile", new CSFile());
        IFileFactory.addIFile("TDFile", new TDFile());
        PlayerMapper.setIFile(IFileFactory.getIFile("SCFile"));
        ReadWriteControl.setIFile(IFileFactory.getIFile("SCFile"));
        CreditCardMapper.setIFile(IFileFactory.getIFile("SCFile"));
        MMOAuctionHouseControl simulation = MMOAuctionHouseControl.getInstance();
    }
    
}
