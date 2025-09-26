/**
 * Driver class to test the GroceryBag implementation.
 * This class demonstrates all the required functionality including
 * adding items, removing items, displaying contents, and checkout.
 * 
 * @author [Haaizq Abbasi]
 */
public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Grocery Bag Driver ===\n");
        
        // Test with ArrayBag implementation (you can also test with other implementations)
        BagInterface <GroceryItem> arrayBag = new ArrayBag<>();
        GroceryBag <GroceryItem> groceryBag = new GroceryBag<>(arrayBag);
        
        // Create several GroceryItem instances (at least one per category)
        GroceryItem milk = new GroceryItem("Milk", "Dairy", 3.49);
        GroceryItem cheese = new GroceryItem("Cheese", "Dairy", 4.99);
        GroceryItem pineapple = new GroceryItem("Pineapple", "Produce", 2.99);
        GroceryItem bananas = new GroceryItem("Bananas", "Produce", 1.99);
        GroceryItem carrots = new GroceryItem("Carrots", "Produce", 1.49);
        GroceryItem chips = new GroceryItem("Chips", "Snack", 3.99);
        GroceryItem cookies = new GroceryItem("Cookies", "Snack", 4.49);
        GroceryItem bread = new GroceryItem("Bread", "Bakery", 2.79);
        
        System.out.println("1. Testing adding single items to the bag:");
        
        // Add items to the bag
        groceryBag.addItem(milk);
        groceryBag.addItem(cheese);
        groceryBag.addItem(pineapple);
        groceryBag.addItem(chips);
        groceryBag.addItem(bread);
        
        System.out.println("Added: Milk, Cheese, Pineapple, Chips, Bread");
        groceryBag.displayAllItems();
        
        System.out.println("2. Testing adding multiple copies of items:");
        
        // Add multiple copies of some items
        groceryBag.addMultipleItems(bananas, 3);
        groceryBag.addMultipleItems(cookies, 2);
        groceryBag.addMultipleItems(carrots, 2);
        
        System.out.println("Added: 3 Bananas, 2 Cookies, 2 Carrots");
        groceryBag.displayAllItems();
        
        System.out.println("3. Testing removal of unspecified (random) item:");
        
        // Remove an unspecified item
        GroceryItem removedItem = groceryBag.removeRandomItem();
        if (removedItem != null) {
            System.out.printf("Randomly removed: %s - $%.2f%n", 
                removedItem.getName(), removedItem.getPrice());
        } else {
            System.out.println("No item was removed (bag might be empty)");
        }
        groceryBag.displayAllItems();
        
        System.out.println("4. Testing removal of specific item by name:");
        
        // Remove specific items by name
        GroceryItem specificItem = groceryBag.removeSpecificItem("Chips");
        if (specificItem != null) {
            System.out.printf("Successfully removed: %s - $%.2f%n", 
                specificItem.getName(), specificItem.getPrice());
        } else {
            System.out.println("Item 'Chips' not found in bag");
        }
        
        // Try to remove an item that might not exist
        GroceryItem nonExistentItem = groceryBag.removeSpecificItem("Soda");
        if (nonExistentItem != null) {
            System.out.printf("Successfully removed: %s - $%.2f%n", 
                nonExistentItem.getName(), nonExistentItem.getPrice());
        } else {
            System.out.println("Item 'Soda' not found in bag");
        }
        
        groceryBag.displayAllItems();
        
        System.out.println("5. Testing checkout process (printing receipt):");
        
        // Simulate checkout and print receipt
        groceryBag.checkout();
        
        System.out.println("6. Testing bag after checkout (items should still be there):");
        groceryBag.displayAllItems();
        
        System.out.println("7. Testing with a different bag implementation:");
        
        // Test with LinkedBag implementation to show it works with any BagInterface
        BagInterface<GroceryItem> linkedBag = new LinkedBag<>();
        GroceryBag<GroceryItem> linkedGroceryBag = new GroceryBag<>(linkedBag);
        
        // Add some items to the linked bag implementation
        linkedGroceryBag.addItem(new GroceryItem("Yogurt", "Dairy", 1.99));
        linkedGroceryBag.addItem(new GroceryItem("Crackers", "Snack", 2.49));
        linkedGroceryBag.addMultipleItems(new GroceryItem("Orange", "Produce", 0.99), 4);
        
        System.out.println("LinkedBag implementation test:");
        linkedGroceryBag.displayAllItems();
        linkedGroceryBag.checkout();
        
        System.out.println("8. Testing edge cases:");
        
        // Test with empty bag
        BagInterface<GroceryItem> emptyBag = new ArrayBag<>();
        GroceryBag<GroceryItem> emptyGroceryBag = new GroceryBag<>(emptyBag);
        
        System.out.println("Testing with empty bag:");
        emptyGroceryBag.displayAllItems();
        emptyGroceryBag.checkout();
        
        GroceryItem removedFromEmpty = emptyGroceryBag.removeRandomItem();
        System.out.println("Attempting to remove from empty bag: " + 
            (removedFromEmpty == null ? "No item removed (as expected)" : "Unexpected result"));
        
        System.out.println("\n=== All the Tests Complete ===");
    }
}