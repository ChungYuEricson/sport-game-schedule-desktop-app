package persistence;

import model.Event;
import model.EventLog;
import model.Team;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.util.List;
import java.util.ArrayList;

// Referenced JsonSerializationDemo
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of List of teams to file
    public void write(List<Team> teams) {
        JSONObject json = new JSONObject();
        json.put("teams", teamsToJsonArray(teams));
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Teams are saved"));
    }

    // EFFECTS: converts list of teams to JSON array
    public JSONArray teamsToJsonArray(List<Team> teams) {
        JSONArray jsonArray = new JSONArray();

        for (Team team : teams) {
            jsonArray.put(teamToJson(team));
        }
        return jsonArray;
    }

    // EFFECTS: converts team to JSON object
    private JSONObject teamToJson(Team team) {
        JSONObject json = new JSONObject();
        json.put("teamName", team.getTeamName());
        json.put("winner", team.getWinner());
        json.put("players", playersToJsonArray(team.getTeam()));
        return json;
    }

    // EFFECTS: converts list of players to JSON array
    private JSONArray playersToJsonArray(List<Player> players) {
        JSONArray jsonArray = new JSONArray();

        for (Player player : players) {
            jsonArray.put(playerToJson(player));
        }
        return jsonArray;
    }

    // EFFECTS: converts player to JSON object
    private JSONObject playerToJson(Player player) {
        JSONObject json = new JSONObject();
        json.put("firstName", player.getFirstName());
        json.put("lastName", player.getLastName());
        json.put("number", player.getNumber());
        json.put("position", player.getPosition());
        json.put("age", player.getAge());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
