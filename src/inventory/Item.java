package inventory;

/** Inventory item with a name and weight. */
public class Item {
    private String name;
    private double weight;

    public Item(String name, double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("weight cannot be negative");
        }
        this.name = name == null ? "" : name.trim();
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name.trim();
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("weight cannot be negative");
        }
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + " (" + weight + ")";
    }
}
