public class GroceryItem {
    // =====================
    // Instance Variables
    // =====================
    private String name;
    private String category;
    private double price;

    // =====================
    // Constructors
    // =====================

    /** 
     * Default constructor
     * Sets name and category to empty strings, price to 0.0 
     */
    public GroceryItem() {
        this.name = "";
        this.category = "";
        this.price = 0.0;
    }

    /**
     * Full constructor
     * @param name the name of the grocery item
     * @param category the category of the grocery item
     * @param price the price of the grocery item
     */
    public GroceryItem(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * Copy constructor
     * @param other another GroceryItem to copy
     */
    public GroceryItem(GroceryItem other) {
        this.name = other.name;
        this.category = other.category;
        this.price = other.price;
    }

    // =====================
    // Accessors (Getters)
    // =====================
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    // =====================
    // Mutators (Setters)
    // =====================
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // =====================
    // Other Methods
    // =====================

    /**
     * Returns a string representation of the GroceryItem
     */
    @Override
    public String toString() {
        return name + " (" + category + ") - $" + String.format("%.2f", price);
    }

    /**
     * Compares two GroceryItem objects for equality.
     * Items are equal if their name, category, and price match.
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // same memory reference
        }
        if (!(obj instanceof GroceryItem)) {
            return false; // not the same type
        }
        GroceryItem other = (GroceryItem) obj;
        return this.name.equalsIgnoreCase(other.name)
            && this.category.equalsIgnoreCase(other.category)
            && this.price == other.price;
    }
}
