package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Team> teams = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/Readeremptyapp.json");
        try {
            List<Team> teams = reader.read();
            assertEquals(2, teams.size());

            Team teamA = teams.get(0);
            assertEquals("a", teamA.getTeamName());
            assertFalse(teamA.getWinner());

            Team teamB = teams.get(1);
            assertEquals("b", teamB.getTeamName());
            assertFalse(teamA.getWinner());

            assertTrue(teamA.getTeam().isEmpty());
            assertTrue(teamB.getTeam().isEmpty());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFilledWorkRoom() {
        JsonReader reader = new JsonReader("./data/Readerfilledapp.json");
        try {
            List<Team> teams = reader.read();
            assertEquals(2, teams.size());

            Team teamA = teams.get(0);
            assertEquals("a", teamA.getTeamName());
            assertTrue(teamA.getWinner());

            List<Player> playersTeamA = teamA.getTeam();
            assertEquals(2, playersTeamA.size());

            checkPlayer("bob", "white", 10, "pg", 20, playersTeamA.get(0));
            checkPlayer("alice", "chan", 80, "pg", 45, playersTeamA.get(1));

            Team teamB = teams.get(1);
            assertEquals("b", teamB.getTeamName());
            assertFalse(teamB.getWinner());

            List<Player> playersTeamB = teamB.getTeam();
            assertEquals(1, playersTeamB.size());

            checkPlayer("cat", "hope", 33, "sf", 26, playersTeamB.get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}