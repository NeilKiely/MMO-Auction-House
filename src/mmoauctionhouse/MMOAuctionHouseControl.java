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
        Player currentPlayer = retrieveMatchingPlayer(currentLogIn.getUsername());
        
        while (keepLooping) {
            String selectedMenuOption = MenuControl.selectOption();

            switch (selectedMenuOption) {
                case "Adventure":
                    selectAdventure(currentPlayer);
                    break;
                case "Buy":
                    selectBuy();
                    break;
                case "Sell":
                    selectSell(currentPlayer);
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
    
    private Player retrieveMatchingPlayer(String username) {
        Inventory inventory = retrieveInventory(username);
        String[] playerInfo = ReadWriteControl.readPlayerInfo(username);
        Player player;
        
        // Depending on the player tier, create appropriate classes
        switch (playerInfo[1]) {
            case "Bronze":
                player = (Player) new Bronze(playerInfo[1], Double.parseDouble(playerInfo[2]), Integer.parseInt(playerInfo[3]), inventory, username);
                break;
            case "Silver":
                player = (Player) new Silver(playerInfo[1], Double.parseDouble(playerInfo[2]), Integer.parseInt(playerInfo[3]), inventory, username);
                break;
            case "Gold":
                player = (Player) new Gold(playerInfo[1], Double.parseDouble(playerInfo[2]), Integer.parseInt(playerInfo[3]), inventory, username);
                break;
            default:
                player = (Player) new Bronze("ERROR_USERNAME", 90.0, 0, inventory, username);
        }
        
        System.out.println(player.toString());
        
        return player;
    }
    
    private Inventory retrieveInventory(String username) {
        String[] itemsArr = ReadWriteControl.readPlayerInventory(username);
        Inventory inv = new Inventory(itemsArr);
        
        return inv;
    }
    
    private void selectAdventure(Player currentPlayer) {
        System.out.println("Adventure Time!");
        System.out.println("Come on, grab your friends!");
        System.out.println("We'll go to very distant lands!");
		Adventure adventure = new Adventure(currentPlayer);
		boolean keepLooping = true;
		while (keepLooping) {
            String selectedMenuOption = MenuControl.selectDifficulty();

            switch (selectedMenuOption) {
                case "Bronze":
                    adventure.calculateBronzeRisk();
                    break;
                 case "Silver":
                     if (currentPlayer.getTier().equals("Silver") || currentPlayer.getTier().equals("Gold"))   
                        adventure.calculateBronzeRisk();
                     else
                         System.out.println("You are a " + currentPlayer.getTier() + " Tier. You need to be silver or higher to access this adventure.");
                    break;
                case "Gold":
                    if (currentPlayer.getTier().equals("Gold"))
                        adventure.calculateBronzeRisk();
                    else
                        System.out.println("You are a " + currentPlayer.getTier() + " Tier. You need to be gold.");
                    break;
                case "Exit Adventure":
                    System.out.println("Your adventure has ended!");
                    keepLooping = false;
                    break;
                default:
                    System.out.println("Uh oh! Something went wrong");
                    break;
            }
        }
		
    }
    
    private void selectBuy() {
        System.out.println("Buy selected");
    }
    
    private void selectSell(Player currentPlayer) {
        System.out.println("Sell selected");
        
        SellControl sell = new SellControl(currentPlayer);
        sell.listItems();
        
    }
}
