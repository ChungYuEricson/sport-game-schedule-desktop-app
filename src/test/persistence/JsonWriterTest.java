package persistence;

import model.Team;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            List<Team> teams = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/Wr\0itter344emptyapp.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
        // pass
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            List<Team> teams = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/Writteremptyapp.json");
            writer.open();
            writer.write(teams);
            writer.close();

            JsonReader reader = new JsonReader("./data/Writteremptyapp.json");
            teams = reader.read();
            assertEquals(0, teams.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultipleTeam() {
        try {
            List<Team> teams = new ArrayList<>();

            Team teamA = new Team("a");
            teamA.updateWinner();
            teamA.addPlayer(new Player("bob", "white", 10, "pg", 20));
            teamA.addPlayer(new Player("alice", "chan", 80, "pg", 45));
            teams.add(teamA);


            Team teamB = new Team("b");
            teamB.addPlayer(new Player("cat", "hope", 33, "sf", 26));
            teams.add(teamB);

            JsonWriter writer = new JsonWriter("./data/Writterfilledapp.json");
            writer.open();
            writer.write(teams);
            writer.close();

            JsonReader reader = new JsonReader("./data/Writterfilledapp.json");
            teams = reader.read();
            assertEquals(2, teams.size());

            Team readTeamA = teams.get(0);
            assertEquals("a", readTeamA.getTeamName());
            assertTrue(readTeamA.getWinner());
            assertEquals(2, readTeamA.getTeam().size());

            Team readTeamB = teams.get(1);
            assertEquals("b", readTeamB.getTeamName());
            assertFalse(readTeamB.getWinner());
            assertEquals(1, readTeamB.getTeam().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}