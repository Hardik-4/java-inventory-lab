package inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Lab demo: circle comparison + file-backed inventory + text file reverse.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================");
        System.out.println("  Java OOP Inventory / Circles");
        System.out.println("=================================");
        System.out.println("1) Circle demo");
        System.out.println("2) Inventory manager");
        System.out.println("3) Reverse input.txt");
        System.out.println("4) Run all then exit");
        System.out.print("Choose: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                circleDemo(scanner);
                break;
            case "2":
                new InventoryManager("inventory.txt", scanner).runMenu();
                break;
            case "3":
                InventoryManager.reverseFile("input.txt");
                break;
            case "4":
            default:
                circleDemo(scanner);
                new InventoryManager("inventory.txt", scanner).runMenu();
                InventoryManager.reverseFile("input.txt");
                break;
        }
    }

    private static void circleDemo(Scanner scanner) {
        List<Circle> circles = new ArrayList<>();
        System.out.println("\nEnter 4 circles:");
        for (int i = 1; i <= 4; i++) {
            System.out.print("Circle " + i + " radius: ");
            double radius = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Circle " + i + " color: ");
            String color = scanner.nextLine().trim();
            circles.add(new Circle(radius, color));
        }

        Circle largest = circles.get(0);
        System.out.println("\nAll circles:");
        for (Circle circle : circles) {
            System.out.println("  " + circle + ", circumference="
                    + String.format("%.2f", circle.circumference()));
            if (circle.area() > largest.area()) {
                largest = circle;
            }
        }
        System.out.println("Largest by area: " + largest);
    }
}
