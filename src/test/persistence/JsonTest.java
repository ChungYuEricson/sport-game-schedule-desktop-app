package persistence;

import model.Player;
import model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlayer(String firstname, String lastName, int number, String position, int age, Player player) {
        assertEquals(firstname, player.getFirstName());
        assertEquals(lastName, player.getLastName());
        assertEquals(number, player.getNumber());
        assertEquals(position, player.getPosition());
        assertEquals(age, player.getAge());
    }
}
