package at.edu.c02.ledcontroller;

public class LedStatus {
    public boolean on;
    public int id;
    public String color;
    public String groupName;

    @Override
    public String toString() {
        return "LED " + id + " is currently " + (on ? "on" : "off") + ". Color: " + color + ".";
    }
}
