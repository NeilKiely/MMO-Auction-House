package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public class MMOAuctionHouseControl {
    /**
    * Main control class
    * @author Vilius
    */
    public MMOAuctionHouseControl() {
        boolean keepLooping = true;
        LoginDetails currentLogIn = LoginControl.loginProcess();
        
        while (keepLooping) {
            String selectedMenuOption = MenuControl.selectOption();

            switch (selectedMenuOption) {
                case "Adventure":
                    selectAdventure();
                    break;
                case "Buy":
                    selectBuy();
                    break;
                case "Sell":
                    selectSell();
                    break;
                case "Quit":
                    System.out.println("Bye bye!");
                    keepLooping = false;
                    break;
                default:
                    System.out.println("Uh oh! Something went wrong");
                    break;
            }
        }
    }
    
    private void selectAdventure() {
        System.out.println("Adventure Time!");
        System.out.println("Come on, grab your friends!");
        System.out.println("We'll go to very distant lands!");
    }
    
    private void selectBuy() {
        System.out.println("Buy selected");
    }
    
    private void selectSell() {
        System.out.println("Sell selected");
    }
}
