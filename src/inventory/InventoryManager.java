package inventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/** File-backed inventory with add / remove / view operations. */
public class InventoryManager {
    private final List<Item> items = new ArrayList<>();
    private final String filePath;
    private final Scanner scanner;

    public InventoryManager(String filePath, Scanner scanner) {
        this.filePath = filePath;
        this.scanner = scanner;
    }

    public void runMenu() {
        load();
        boolean running = true;
        while (running) {
            System.out.println("\n--- Inventory ---");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. View items");
            System.out.println("4. Save & exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addItem();
                    break;
                case "2":
                    removeItem();
                    break;
                case "3":
                    viewItems();
                    break;
                case "4":
                    save();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addItem() {
        System.out.print("Item name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        System.out.print("Item weight: ");
        try {
            double weight = Double.parseDouble(scanner.nextLine().trim());
            items.add(new Item(name, weight));
            System.out.println("Added.");
        } catch (NumberFormatException e) {
            System.out.println("Weight must be a number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeItem() {
        System.out.print("Name to remove: ");
        String name = scanner.nextLine().trim();
        boolean removed = false;
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equalsIgnoreCase(name)) {
                it.remove();
                removed = true;
                break;
            }
        }
        System.out.println(removed ? "Removed." : "Not found.");
    }

    private void viewItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        double total = 0;
        System.out.println("\nItems:");
        for (Item item : items) {
            System.out.println("  - " + item);
            total += item.getWeight();
        }
        System.out.printf("Total weight: %.2f (%d items)%n", total, items.size());
    }

    private void load() {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    items.add(new Item(parts[0], Double.parseDouble(parts[1].trim())));
                }
            }
        } catch (Exception e) {
            System.out.println("Could not load inventory: " + e.getMessage());
        }
    }

    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Item item : items) {
                writer.println(item.getName() + "," + item.getWeight());
            }
            System.out.println("Saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Could not save inventory: " + e.getMessage());
        }
    }

    /** Reverse the lines of a text file into *_reversed.*. */
    public static void reverseFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Could not read " + filename + ": " + e.getMessage());
            return;
        }

        java.util.Collections.reverse(lines);
        String outName = filename.contains(".")
                ? filename.replaceFirst("(\\.[^.]+)$", "_reversed$1")
                : filename + "_reversed";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outName))) {
            for (String reversed : lines) {
                writer.write(reversed);
                writer.newLine();
            }
            System.out.println("Created: " + outName);
        } catch (IOException e) {
            System.out.println("Could not write reversed file: " + e.getMessage());
        }
    }
}
