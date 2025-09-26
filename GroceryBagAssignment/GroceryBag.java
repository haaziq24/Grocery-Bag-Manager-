
/**
 * GroceryBag class that manages grocery items using a bag data structure.
 * This class provides operations for adding, removing, displaying, and 
 * checking out grocery items.
 * 
 * @author [Haaziq Abbasi]
 */
public class GroceryBag<T> {
    private BagInterface<GroceryItem> bag;
    private static final double TAX_RATE = 0.06; // theres a 6% sales tax
    
    /**
     * Constructor that creates a GroceryBag with the specified bag implementation
     * @param bagImplementation the bag implementation to use for storing items
     */
    public GroceryBag(BagInterface<GroceryItem> bagImplementation) {
        this.bag = bagImplementation;
    }
    
    /**
     * Adds a single grocery item to the bag
     * @param item the grocery item to add
     * @return true if item was successfully added, false otherwise
     */
    public boolean addItem(GroceryItem item) {
        return bag.add(item);
    }
    
    /**
     * Adds multiple copies of the same grocery item to the bag
     * @param item the grocery item to add
     * @param quantity the number of copies to add
     * @return true if all items were successfully added, false otherwise
     */
    public boolean addMultipleItems(GroceryItem item, int quantity) {
        boolean success = true;
        for (int i = 0; i < quantity; i++) {
            // Create a new instance for each copy to avoid reference issues
            GroceryItem copy = new GroceryItem(item.getName(), item.getCategory(), item.getPrice());
            if (!bag.add(copy)) {
                success = false;
            }
        }
        return success;
    }
    
    /**
     * Removes an unspecified (random) item from the bag
     * @return the removed item, or null if bag is empty
     */
    public GroceryItem removeRandomItem() {
        return bag.remove();
    }
    
    /**
     * Removes a specific item by name from the bag
     * @param itemName the name of the item to remove
     * @return the removed item, or null if item not found
     */
    public GroceryItem removeSpecificItem(String itemName) {
    BagInterface<GroceryItem> temp = new ResizableArrayBag<GroceryItem>();
    GroceryItem removed = null;

    while (!bag.isEmpty()) {
        GroceryItem current = bag.remove();
        if (removed == null && current.getName().equalsIgnoreCase(itemName)) {
            removed = current;   // save the item we removed
            // don't put it back into temp
        } else {
            temp.add(current);   // put everything else back
        }
    }

    // restore bag contents
    while (!temp.isEmpty()) {
        bag.add(temp.remove());
    }

    return removed; // will be null if not found
}
    
    /**
     * Displays all items in the bag with their details and total count
     */
    public void displayAllItems() {
        System.out.println("\n=== GROCERY BAG CONTENTS ===");
        
        if (bag.isEmpty()) {
            System.out.println("The bag is empty.");
            return;
        }
        
        Object[] items = bag.toArray();
        int itemCount = 1;
        
        for (Object obj : items) {
            if (obj != null && obj instanceof GroceryItem) {
                GroceryItem item = (GroceryItem) obj;
                System.out.printf("%d. %s - Category: %s - Price: $%.2f%n", 
                    itemCount++, item.getName(), item.getCategory(), item.getPrice());
            }
        }
        
        System.out.println("Total number of items in bag: " + bag.getCurrentSize());
        System.out.println("===============================\n");
    }
    
    /**
     * Simulates checkout process and prints a detailed receipt
     */
    public void checkout() {
        System.out.println("\n========== RECEIPT ==========");
        
        if (bag.isEmpty()) {
            System.out.println("No items to checkout.");
            System.out.println("============================\n");
            return;
        }
        
        Object[] items = bag.toArray();
        double subtotal = 0.0;
        int itemCount = 1;
        
        // Display each item with price
        System.out.println("Items purchased:");
        for (Object obj : items) {
            if (obj != null && obj instanceof GroceryItem) {
                GroceryItem item = (GroceryItem) obj;
                System.out.printf("%d. %-15s $%.2f%n", 
                    itemCount++, item.getName(), item.getPrice());
                subtotal += item.getPrice();
            }
        }
        
        // Calculate tax and total
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;
        
        // Print totals
        System.out.println("----------------------------");
        System.out.printf("Subtotal:        $%.2f%n", subtotal);
        System.out.printf("Sales Tax (6%%):  $%.2f%n", tax);
        System.out.printf("TOTAL:           $%.2f%n", total);
        System.out.println("============================");
        System.out.println("Thank you for shopping!");
        System.out.println("============================\n");
    }
    
    /**
     * Returns the current number of items in the bag
     * @return the number of items currently in the bag
     */
    public int getCurrentSize() {
        return bag.getCurrentSize();
    }
    
    /**
     * Checks if the bag is empty
     * @return true if bag is empty, false otherwise
     */
    public boolean isEmpty() {
        return bag.isEmpty();
    }
    
    /**
     * Clears all items from the bag
     */
    public void clear() {
        bag.clear();
    }
}
