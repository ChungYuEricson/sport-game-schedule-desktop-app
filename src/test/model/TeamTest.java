package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team team1;
    private Player player;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    private Player player6;

    @BeforeEach
    void runBefore() {
        team1 = new Team("Huskies");
        player = new Player("Bob", "Lee", 10, "sg", 18);
        player2 = new Player("Joe", "Bo", 15, "pg", 20);
        player3 = new Player("Alice", "Ed", 20, "sf", 21);
        player4 = new Player("Greg", "Son", 30, "c", 30);
        player5 = new Player("Vi", "White", 35, "pg", 31);
        player6 = new Player("Quinn", "Wood", 55, "c", 32);
    }

    @Test
    void testConstructor() {
        assertEquals("Huskies", team1.getTeamName());
        assertFalse(team1.getWinner());
        assertTrue(team1.getTeam().isEmpty());
    }

    @Test
    void testAddPlayer() {
        assertTrue(team1.getTeam().isEmpty());
        team1.addPlayer(player);
        assertFalse(team1.getTeam().isEmpty());
        assertEquals(1, team1.getTeam().size());
        assertEquals(player, team1.getTeam().get(0));
    }

    @Test
    void testRemovePlayer() {
        assertTrue(team1.getTeam().isEmpty());
        team1.addPlayer(player);
        team1.addPlayer(player2);
        team1.addPlayer(player3);
        assertEquals(player2, team1.getTeam().get(1));
        team1.removePlayer(player2);
        assertEquals(2, team1.getTeam().size());
        assertEquals(player3, team1.getTeam().get(1));
    }

    @Test
    void testUpdateWinner() {
        assertFalse(team1.getWinner());
        team1.updateWinner();
        assertTrue(team1.getWinner());
    }

    @Test
    void testUpdateLoser() {
        assertFalse(team1.getWinner());
        team1.updateWinner();
        assertTrue(team1.getWinner());
        team1.updateLoser();
        assertFalse(team1.getWinner());
    }

    @Test
    void testGetTeamByPositionOnePlayer() {
        List<Player> testList = new ArrayList<Player>();
        testList.add(player);

        List<Player> testList2 = new ArrayList<Player>();

        team1.addPlayer(player);
        assertEquals(testList, team1.getTeamByPosition("sg"));

        assertEquals(testList2, team1.getTeamByPosition("pg"));
    }

    @Test
    void testGetTeamByPositionMultiplePlayers() {
        List<Player> testList = new ArrayList<Player>();
        testList.add(player4);
        testList.add(player6);

        List<Player> testList2 = new ArrayList<Player>();

        team1.addPlayer(player);
        team1.addPlayer(player4);
        team1.addPlayer(player6);

        assertEquals(testList, team1.getTeamByPosition("c"));
        assertEquals(testList2, team1.getTeamByPosition("sf"));
    }

    @Test
    void testGetTeamByAgeOnePlayer() {
        List<Player> testList = new ArrayList<Player>();
        testList.add(player5);

        List<Player> testList2 = new ArrayList<Player>();

        team1.addPlayer(player5);
        assertEquals(testList, team1.getTeamByAge(31));
        assertEquals(testList, team1.getTeamByAge(32));

        assertEquals(testList2, team1.getTeamByAge(30));
    }

    @Test
    void testGetTeamByAgeMultiplePlayer() {
        List<Player> testList = new ArrayList<Player>();
        testList.add(player4);
        testList.add(player5);
        testList.add(player6);

        List<Player> testList2 = new ArrayList<Player>();
        testList2.add(player4);
        testList2.add(player5);

        team1.addPlayer(player4);
        team1.addPlayer(player5);
        team1.addPlayer(player6);

        assertEquals(testList, team1.getTeamByAge(32));
        assertEquals(testList, team1.getTeamByAge(33));

        assertEquals(testList2, team1.getTeamByAge(31));
    }

    @Test
    void testToJson() {
        team1.addPlayer(player);
        team1.addPlayer(player2);

        JSONObject nextTeam = team1.toJson();
        assertEquals("Huskies",nextTeam.getString("teamName"));
        assertFalse(nextTeam.getBoolean("winner"));
        JSONArray playersArray = nextTeam.getJSONArray("team");

        assertEquals(2, playersArray.length());

        JSONObject player1JSON = playersArray.getJSONObject(0);
        assertEquals("Bob",player1JSON.getString("firstName"));
        assertEquals("Lee",player1JSON.getString("lastName"));
        assertEquals(10,player1JSON.getInt("number"));
        assertEquals("sg",player1JSON.getString("position"));
        assertEquals(18,player1JSON.getInt("age"));

        JSONObject player2JSON = playersArray.getJSONObject(1);
        assertEquals("Joe",player2JSON.getString("firstName"));
        assertEquals("Bo",player2JSON.getString("lastName"));
        assertEquals(15,player2JSON.getInt("number"));
        assertEquals("pg",player2JSON.getString("position"));
        assertEquals(20,player2JSON.getInt("age"));
    }
}
