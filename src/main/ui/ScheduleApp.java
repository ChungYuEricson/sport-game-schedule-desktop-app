package ui;

import model.Team;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// Referenced JsonSerializationDemo
// Referenced TellerApp
public class ScheduleApp {
    private List<Team> teams;
    private Scanner scanner;
    private static final String JSON_STORE = "./data/savedapp.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    /*
    EFFECTS: create a new array list for teams
             create a new scanner for input
             create a new JsonReader
             create a new JsonWriter
             initialize Teams
             run the Schedule application
     */
    public ScheduleApp() throws FileNotFoundException {
        teams = new ArrayList<>();
        scanner = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        initializeTeams();
        runSchedule();
    }

    /*
    EFFECTS: create 2 team, asking user for team name
             and add team to teams or load existing teams
    */
    private void initializeTeams() {
        for (int i = 1; i <= 2; i++) {
            System.out.println("Enter 1 to  load existing teams, or name for team " + i + ":");
            String teamName = scanner.nextLine();
            if (teamName.equals("1")) {
                loadProgress();
                break;
            } else {
                Team team = new Team(teamName);
                teams.add(team);
            }
        }
    }

    /*
    Referenced TellerApp
    EFFECTS: Display menu of options to user
             Asking user to choose from 2 teams to manage
             Save progress if press s
             Exit the app if press q
    */
    private void runSchedule() {
        while (true) {
            System.out.println("Which team would you like to manage?");
            System.out.println("Press 1 for " + teams.get(0).getTeamName());
            System.out.println("Press 2 for " + teams.get(1).getTeamName());
            System.out.println("Press s to save progress");
            System.out.println("Press q to quit");

            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            } else if (choice.equals("1") || choice.equals("2")) {
                int teamIndex = Integer.parseInt(choice) - 1;
                Team selectedTeam = teams.get(teamIndex);
                manageTeam(selectedTeam);
            } else if (choice.equalsIgnoreCase("s")) {
                saveProgress();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Exiting Schedule Manager...");
    }

    /*
    Referenced TellerApp
    EFFECTS: Display menu of options to user
             Asking user to choose to add player by pressing 1
             Remove player by pressing 2
             View players by pressing 3
             Update if the team won by pressing 4
             Exit from managing current team if press q
             Other input return invalid
    */
    private void manageTeam(Team team) {
        while (true) {
            System.out.println("Managing " + team.getTeamName());
            System.out.println("Press 1 to add a player");
            System.out.println("Press 2 to remove a player");
            System.out.println("Press 3 to view players");
            System.out.println("Press 4 to update winner");
            System.out.println("Press q to quit managing " + team.getTeamName());

            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            } else if (choice.equals("1")) {
                addPlayer(team);
            } else if (choice.equals("2")) {
                removePlayer(team);
            } else if (choice.equals("3")) {
                viewPlayers(team);
            } else if (choice.equals("4")) {
                updateWinningStatus(team);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /*
    EFFECTS: Update team to become the winning team, and update other team as losing team
    */
    private void updateWinningStatus(Team team) {
        team.updateWinner();
        System.out.println(team.getTeamName() + " won.");

        for (Team otherteam : teams) {
            if (otherteam != team) {
                otherteam.updateLoser();
                System.out.println(otherteam.getTeamName() + " lost.");
            }
        }
    }

    /*
    REQUIRES: Jersey number must be unique
    EFFECTS: Add player to current team and specify player info
    */
    private void addPlayer(Team team) {
        System.out.println("Enter player first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter player last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter player number:");
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter player position:");
        String position = scanner.nextLine();
        System.out.println("Enter player age:");
        int age = scanner.nextInt();
        scanner.nextLine();

        Player player = new Player(firstName, lastName, number, position, age);

        team.addPlayer(player);
        System.out.println(lastName + " added to " + team.getTeamName());
    }

    /*
    EFFECTS: remove player from current team by jersey number
    */
    private void removePlayer(Team team) {
        if (team.getTeam().isEmpty()) {
            System.out.println("Invalid amount of players. Please add players.");
            return;
        }
        System.out.println("Remove player by jersey number:");
        int numRemove = scanner.nextInt();
        scanner.nextLine();

        List<Player> players = team.getTeam();
        for (Player player : players) {
            if (player.getNumber() == numRemove) {
                team.removePlayer(player);
                System.out.println(player.getLastName() + " is removed.");
                break;
            }
        }
    }

    /*
    EFFECTS: Display options to view players,
             View all players by pressing 1
             View by sorting by pressing 2
             Exit viewing players if press q
    */
    private void viewPlayers(Team team) {
        if (team.getTeam().isEmpty()) {
            System.out.println("Invalid amount of players. Please add players.");
            return;
        }

        while (true) {
            System.out.println("Viewing " + team.getTeamName());
            System.out.println("Press 1 to view all players");
            System.out.println("Press 2 to view by sorting");
            System.out.println("Press q to quit viewing");

            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            } else if (choice.equals("1")) {
                viewAllPlayers(team);
            } else if (choice.equals("2")) {
                viewPlayersBySort(team);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /*
    EFFECTS: Get current list of players in current team
             Iterate through each player and print their info
    */
    private void viewAllPlayers(Team team) {
        List<Player> players = team.getTeam();
        System.out.println(("All players in " + team.getTeamName() + ":"));

        for (Player player : players) {
            System.out.println("Name:" + player.getFirstName() + " " + player.getLastName());
            System.out.println("Number:" + player.getNumber());
            System.out.println("Position:" + player.getPosition());
            System.out.println("Age:" + player.getAge());
            System.out.println("Status:" + player.playerStatus());
            System.out.println("-----------------------------------");
        }
    }

    /*
    EFFECTS: Display options of sorting,
             Sort by same position by pressing 1
             Sort by younger than age of by pressing 2
             Exit view by sorting if press q
    */
    private void viewPlayersBySort(Team team) {
        while (true) {
            System.out.println("Viewing " + team.getTeamName() + " by Sort");
            System.out.println("Press 1 to view players with same position");
            System.out.println("Press 2 to view players younger than:");
            System.out.println("Press q to quit viewing");

            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            } else if (choice.equals("1")) {
                viewPlayersByPosition(team);
            } else if (choice.equals("2")) {
                viewPlayersByAge(team);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /*
    EFFECTS: Get current list of players in current team with same position
             Iterate through each player and print their info
             print no players found if no players found
    */
    private void viewPlayersByPosition(Team team) {
        System.out.println(("Which position?"));
        String position = scanner.nextLine();

        List<Player> players = team.getTeamByPosition(position);

        if (players.isEmpty()) {
            System.out.println("No players found for " + position + ".");
        } else {
            System.out.println(("All players in " + team.getTeamName() + ":"));
            for (Player player : players) {
                System.out.println("Name:" + player.getFirstName() + " " + player.getLastName());
                System.out.println("Number:" + player.getNumber());
                System.out.println("Age:" + player.getAge());
                System.out.println("Status:" + player.playerStatus());
                System.out.println("-----------------------------------");
            }
        }
    }

    /*
    EFFECTS: Get current list of players in current team younger or same age as selected age
         Iterate through each player and print their info
         print no players found if no players found
    */
    private void viewPlayersByAge(Team team) {
        System.out.println(("At least how young?"));
        int age = scanner.nextInt();
        scanner.nextLine();

        List<Player> players = team.getTeamByAge(age);

        if (players.isEmpty()) {
            System.out.println("No players found for at least " + age + " years old young.");
        } else {
            System.out.println(("All players in " + team.getTeamName() + ":"));
            for (Player player : players) {
                System.out.println("Name:" + player.getFirstName() + " " + player.getLastName());
                System.out.println("Number:" + player.getNumber());
                System.out.println("Position:" + player.getPosition());
                System.out.println("Status:" + player.playerStatus());
                System.out.println("-----------------------------------");
            }
        }
    }

    //EFFECTS: save progress
    private void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(teams);
            jsonWriter.close();
            System.out.println("Team saved to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }



    //EFFECTS: load team
    private void loadProgress() {
        try {
            teams = jsonReader.read();
            System.out.println("Team loaded from file: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Error: Unable to load team");
        }
    }
}
