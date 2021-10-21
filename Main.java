import edu.gatech.cs6310.DeliveryService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Grocery Express Delivery Service!");
        DeliveryService simulator = new DeliveryService();
        simulator.commandLoop();
    }
}
//make_store,whole_foods,34000
//make_store,publix,35000
//make_store,kroger,33000

//display_stores