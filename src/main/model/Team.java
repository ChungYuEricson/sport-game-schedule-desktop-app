package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

//Represents a team having a team name, a boolean deciding if the team won, and a list of players
public class Team implements Writable {

    private String teamName; // team name
    private boolean winner; // did team win?
    private List<Player> team; // list of players


    /*
        REQUIRES: team name has a non-zero length
                  teamName must be String
        EFFECTS: set team name of team and create an empty list for list of players , default for winner is false
        */
    public Team(String teamName) {
        this.teamName = teamName;
        winner = false;
        team = new ArrayList<Player>();
        EventLog.getInstance().logEvent(new Event("Team is created"));
    }

    /*
    MODIFIES: this
    EFFECTS: add a player to team
    */
    public void addPlayer(Player player) {
        team.add(player);
        EventLog.getInstance().logEvent(new Event("Player added"));
    }

    /*
    REQUIRES: at least has one player
    MODIFIES: this
    EFFECTS: remove a player from team
    */
    public void removePlayer(Player player) {
        team.remove(player);
        EventLog.getInstance().logEvent(new Event("Player is removed"));
    }

    /*
    MODIFIES: this
    EFFECTS: update team as winner
    */
    public void updateWinner() {
        this.winner = true;
        EventLog.getInstance().logEvent(new Event("Winner is updated"));
    }

    /*
    MODIFIES: this
    EFFECTS: update team as loser
    */
    public void updateLoser() {
        this.winner = false;
        EventLog.getInstance().logEvent(new Event("Loser is updated"));
    }


    public String getTeamName() {
        return teamName;
    }

    public boolean getWinner() {
        return winner;
    }

    public List<Player> getTeam() {
        return team;
    }

    /*
    REQUIRES: at least has one player
    EFFECTS: return a list of players based on selected position
    */
    public List<Player> getTeamByPosition(String position) {
        List<Player> teamByPosition = new ArrayList<Player>();

        for (Player player : team) {
            if (player.getPosition().equals(position)) {
                teamByPosition.add(player);
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewing players by position"));
        return teamByPosition;
    }


    /*
    REQUIRES: at least has one player
    EFFECTS: return a list of players based on how young players are (<= selected age)
    */
    public List<Player> getTeamByAge(int age) {
        List<Player> teamByAge = new ArrayList<Player>();

        for (Player player : team) {
            if (player.getAge() <= age) {
                teamByAge.add(player);
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewing players by age"));
        return teamByAge;
    }

    //MODIFIES: this
    //EFFECTS set winner
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    // EFFECT convert to Json
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("teamName", teamName);
        json.put("winner", winner);
        json.put("team", teamsToJsonArray());

        return json;
    }

    public JSONArray teamsToJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Player player : team) {
            jsonArray.put(player.toJson());
        }
        return jsonArray;
    }
}
