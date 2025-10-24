package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a player having first name, last name, jersey number, position and age.
public class Player implements Writable {

    private String firstName; // player first name
    private String lastName; // player last name
    private int number; // player jersey number
    private String position; // player position
    private int age; // player age

    /*
    REQUIRES: first name, last name, number, position and age have a non-zero length
              age is at least 18
              first name, last name, position must be String
              number, age must be int
    EFFECTS: set first name, last name, jersey number, position and age of a player

     */
    public Player(String firstName, String lastName, int number, String position, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.position = position;
        this.age = age;
        EventLog.getInstance().logEvent(new Event("Player is created"));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    // REQUIRES: age must be at least 18
    // EFFECTS: check if player is a prospect(17 < age < 21), intermediate(20 < age < 30) or veteran(age > 30)
    public String playerStatus() {
        if (age < 21) {
            return "Prospect";
        } else if (age > 30) {
            return "Veteran";
        } else {
            return "Intermediate";
        }
    }

    @Override
    // EFFECT convert to Json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("number", number);
        json.put("position", position);
        json.put("age", age);
        return json;
    }
}
