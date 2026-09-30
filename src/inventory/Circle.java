package inventory;

/** Circle with radius, color, and area helpers. */
public class Circle {
    private double radius;
    private String color;

    public Circle(double radius, String color) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius cannot be negative");
        }
        this.radius = radius;
        this.color = color == null ? "" : color.trim();
    }

    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public void setRadius(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius cannot be negative");
        }
        this.radius = radius;
    }

    public void setColor(String color) {
        this.color = color == null ? "" : color.trim();
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double circumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle{color='%s', radius=%.2f, area=%.2f}", color, radius, area());
    }
}
