package persistence;

import model.Event;
import model.EventLog;
import model.Team;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

// Referenced JsonSerializationDemo
// Represents a reader that reads list of teams from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of teams from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Team> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Teams are loaded"));
        return parseTeams(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses List of team from JSON object and returns it
    private List<Team> parseTeams(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        List<Team> teams = new ArrayList<>();

        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            teams.add(parseTeam(nextTeam));
        }
        return teams;
    }

    // MODIFIES:team
    // EFFECTS: parse teams from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String teamName = jsonObject.getString("teamName");
        boolean winner = jsonObject.getBoolean("winner");
        JSONArray playersArray = jsonObject.getJSONArray("players");
        Team team = new Team(teamName);
        team.setWinner(winner);
        addPlayers(team, jsonObject);
        return team;
    }

    // MODIFIES: team
    // EFFECTS: parses thingy from JSON object and adds them to team
    private void addPlayers(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(team, nextPlayer);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses thingy from JSON object and adds it to team
    private void addPlayer(Team team, JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        int number = jsonObject.getInt("number");
        String position = jsonObject.getString("position");
        int age = jsonObject.getInt("age");

        Player player = new Player(firstName, lastName, number, position, age);
        team.addPlayer(player);
    }
}
