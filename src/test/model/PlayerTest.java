package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    private Player player6;

    @BeforeEach
    void runBefore() {
        player = new Player("Bob", "Lee", 10, "sg", 18);
        player2 = new Player("Joe", "Bo", 15, "pg", 20);
        player3 = new Player("Alice", "Ed", 20, "sf", 21);
        player4 = new Player("Greg", "Son", 30, "c", 30);
        player5 = new Player("Vi", "White", 35, "pg", 31);
        player6 = new Player("Quinn", "Wood", 55, "c", 32);
    }

    @Test
    void testConstructor() {
        assertEquals("Bob", player.getFirstName());
        assertEquals("Lee", player.getLastName());
        assertEquals(10, player.getNumber());
        assertEquals("sg", player.getPosition());
        assertEquals(18, player.getAge());
    }

    @Test
    void testPlayerStatus18() {
        assertEquals("Prospect", player.playerStatus());
    }

    @Test
    void testPlayerStatus20() {
        assertEquals("Prospect", player2.playerStatus());
    }

    @Test
    void testPlayerStatus21() {
        assertEquals("Intermediate", player3.playerStatus());
    }

    @Test
    void testPlayerStatus30() {
        assertEquals("Intermediate", player4.playerStatus());
    }

    @Test
    void testPlayerStatus31() {
        assertEquals("Veteran", player5.playerStatus());
    }

    @Test
    void testPlayerStatus32() {
        assertEquals("Veteran", player6.playerStatus());
    }

    @Test
    void testToJson() {
        JSONObject nextPlayer = player.toJson();
        assertEquals("Bob",nextPlayer.getString("firstName"));
        assertEquals("Lee",nextPlayer.getString("lastName"));
        assertEquals(10,nextPlayer.getInt("number"));
        assertEquals("sg",nextPlayer.getString("position"));
        assertEquals(18,nextPlayer.getInt("age"));
    }
}
