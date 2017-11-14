package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public class MMOAuctionHouse {
    public static void main(String[] args) {
        IFileFactory.addIFile("SCFile", new SCFile());
        MMOAuctionHouseControl simulation = new MMOAuctionHouseControl();
        /*IFileFactory iFileFactory = new IFileFactory();
        iFileFactory.addIFile("CSFile", new CSFile());
        iFileFactory.addIFile("SCFile", new SCFile());
        iFileFactory.addIFile("TDFile", new TDFile());
        IFile newIFile = iFileFactory.getIFile("CSFile");
        IFile newIFile1 = iFileFactory.getIFile("SCFile");
        IFile newIFile2 = iFileFactory.getIFile("TDFile");
        newIFile.put("workingCS.txt");
        newIFile1.put("workingSC.txt");
        newIFile2.put("workingTD.txt");
        
        String print[][] = new String[1][2];
        print[0][0] = "Working";
        print[0][1] = "And";
        newIFile.write(print);
        newIFile1.write(print);
        newIFile2.write(print);
*/
    }
    
}
