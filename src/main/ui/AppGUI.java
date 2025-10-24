package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// referenced: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/ui/AlarmControllerUI.java
// for JPanel
// referenced: phase 2 for jsonWriter and jsonReader

//Effect: Constructor sets up button panel, create an empty list to save the teams.

public class AppGUI extends JFrame {
    private JPanel currentPanel;
    private List<Team> teams;
    private static final String JSON_STORE = "./data/savedapp.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public AppGUI() {
        setTitle("Schedule App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        teams = new ArrayList<>();

        jsonWriter = new JsonWriter(JSON_STORE);
        showLoadTeamsPanel();
        addWindowListener(new WindowEventClass());
    }

    // referenced: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter/blob/main/src/main/gui/IntersectionGUI.java
    // for actionListener and actionPerformed
    // Effect: set up panel and buttons for load team and add team

    private void showLoadTeamsPanel() {
        currentPanel = new JPanel();
        currentPanel.setLayout(new GridLayout(2, 1));

        JButton loadTeamsButton = new JButton("Load Teams");
        loadTeamsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                loadTeams();
            }
        });


        JButton addTeamsButton = new JButton("Add Teams");
        addTeamsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddTeamsPanel();
            }
        });

        currentPanel.add(loadTeamsButton);
        currentPanel.add(addTeamsButton);

        setContentPane(currentPanel);

    }

    //referenced: https://www.tabnine.com/code/java/methods/javax.swing.JOptionPane/showMessageDialog
    // for showMessageDialog
    //referenced phase 2 for the try catch logic
    // Effect: jsonReader to load team

    private void loadTeams() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            teams = jsonReader.read(); // Load teams
            JOptionPane.showMessageDialog(
                    AppGUI.this, "Teams loaded successfully!");
            showManageTeamsPanel(); // After successful loading, show the manage teams panel
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    AppGUI.this, "Error: Unable to load teams");
        }
    }

// referenced: https://www.tabnine.com/code/java/methods/javax.swing.JPanel/removeAll
// for removeALL() to clear previous JPanel components
// referenced: https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/layout/grid.html
// for grid setup
// referenced: https://www.geeksforgeeks.org/java-swing-jtextfield/
// for text field
// referenced: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter/blob/main/src/main/gui/IntersectionGUI.java
// for JLabel

    //Effects: remove existing components, set up new panel and buttons for adding new teams and typing team names

    private void showAddTeamsPanel() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 1));

        JTextField team1Field = new JTextField();
        JTextField team2Field = new JTextField();
        JButton nextStepButton = new JButton("Next Step");

        nextStepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teams.add(new Team(team1Field.getText()));
                teams.add(new Team(team2Field.getText()));
                showManageTeamsPanel();
            }
        });

        currentPanel.add(new JLabel("Enter Team 1 name:"));
        currentPanel.add(team1Field);
        currentPanel.add(new JLabel("Enter Team 2 name:"));
        currentPanel.add(team2Field);
        currentPanel.add(nextStepButton);

        setContentPane(currentPanel);
    }

    //Effect: setup buttons
    private void setUpTeamsPanel() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(4, 1));
    }

    // Effect: remove existing components, set up panel and buttons for selecting team to manage and save teams
    private void createManageTeamsPanel() {
        JButton manageTeam1Button = new JButton("Manage Team 1");
        manageTeam1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTeamManagementPanel(teams.get(0));
            }
        });

        JButton manageTeam2Button = new JButton("Manage Team 2");
        manageTeam2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTeamManagementPanel(teams.get(1));
            }
        });

        JButton saveTeamsButton = new JButton("Save Teams");
        saveTeamsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTeams();
            }
        });

        currentPanel.add(manageTeam1Button);
        currentPanel.add(manageTeam2Button);
        currentPanel.add(saveTeamsButton);

        setContentPane(currentPanel);
    }

    //effect: show panel
    private void showManageTeamsPanel() {
        setUpTeamsPanel();
        createManageTeamsPanel();
    }

    //Effect: remove existing components, set up panel and buttons for add player, see all players,
    // remove player, see players by position, see players by age and return to select team to manage.

    private void setupTeamManagementPanel(Team team) {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(7, 1));
    }

    private void createAddPlayerButton(Team team) {
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddPlayerPanel(team);
            }
        });
        currentPanel.add(addPlayerButton);
    }

    private void createSeeAllPlayerButton(Team team) {
        JButton seeAllPlayersButton = new JButton("See All Players");
        seeAllPlayersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllPlayersPanel(team);
            }
        });
        currentPanel.add(seeAllPlayersButton);
    }

    private void createRemovePlayerButton(Team team) {
        JButton removePlayerButton = new JButton("Remove Player"); // New button for removing player
        removePlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removePlayer(team);
            }
        });
        currentPanel.add(removePlayerButton);
    }

    private void createSeePlayersByPositionButton(Team team) {
        JButton seePlayersByPositionButton = new JButton(
                "See Players by Position"); // New button for seeing players by position
        seePlayersByPositionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPlayersByPosition(team);
            }
        });
        currentPanel.add(seePlayersByPositionButton);
    }

    private void createSeePlayersByAgeButton(Team team) {
        JButton seePlayersByAgeButton = new JButton("See Players by Age"); // New button for seeing players by age
        seePlayersByAgeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPlayersByAge(team);
            }
        });
        currentPanel.add(seePlayersByAgeButton);
    }

    private void createReturnToStep3Button() {
        JButton returnToStep3Button = new JButton("Return to Select Team to Manage");
        returnToStep3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showManageTeamsPanel();
            }
        });
        currentPanel.add(returnToStep3Button);
    }


    private void showTeamManagementPanel(Team team) {
        setupTeamManagementPanel(team);
        createAddPlayerButton(team);
        createSeeAllPlayerButton(team);
        createRemovePlayerButton(team);
        createSeePlayersByPositionButton(team);
        createSeePlayersByAgeButton(team);
        createReturnToStep3Button();

        setContentPane(currentPanel);
    }

    // Effect: if no input, nothing happens, if input same as existing player, remove the player, catch error
    // from invalid input
    private void removePlayer(Team team) {
        String jerseyNumberString = JOptionPane.showInputDialog("Enter the jersey number of the player to remove:");
        if (jerseyNumberString == null || jerseyNumberString.isEmpty()) {
            return;
        }
        try {
            int jerseyNumber = Integer.parseInt(jerseyNumberString);
            Player playerToRemove = null;
            for (Player player : team.getTeam()) {
                if (player.getNumber() == jerseyNumber) {
                    playerToRemove = player;
                    break;
                }
            }
            if (playerToRemove != null) {
                team.removePlayer(playerToRemove);
                JOptionPane.showMessageDialog(this, "Player removed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Player with jersey number " + jerseyNumber + " not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid jersey number.");
        }
    }

    //effect: set up add player panel
    private void setUpAddPlayerPanel() {
        currentPanel.removeAll();
        currentPanel.setLayout(new GridLayout(3, 2));
    }

    //effect: create JTextField and add JLabel
    private void createFieldsAddPlayerPanel(Team team) {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField numberField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField ageField = new JTextField();

        currentPanel.add(new JLabel("First Name:"));
        currentPanel.add(firstNameField);
        currentPanel.add(new JLabel("Last Name:"));
        currentPanel.add(lastNameField);
        currentPanel.add(new JLabel("Number:"));
        currentPanel.add(numberField);
        currentPanel.add(new JLabel("Position:"));
        currentPanel.add(positionField);
        currentPanel.add(new JLabel("Age:"));
        currentPanel.add(ageField);

        createConfirmButton(team, firstNameField, lastNameField, numberField, positionField, ageField);
    }

    //effect: create confirm button
    private void createConfirmButton(Team team, JTextField firstNameField, JTextField
            lastNameField, JTextField numberField, JTextField
                                             positionField, JTextField ageField) {

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int number = Integer.parseInt(numberField.getText());
                String position = positionField.getText();
                int age = Integer.parseInt(ageField.getText());

                Player player = new Player(firstName, lastName, number, position, age);
                team.addPlayer(player);
                JOptionPane.showMessageDialog(null, "Player added successfully!");
                showTeamManagementPanel(team);
            }
        });
        currentPanel.add(confirmButton);
    }


    // Effect: set up panel and buttons and JTextField to add players. Confirm button needs to perform action to
    // add players according to input.
    private void showAddPlayerPanel(Team team) {
        setUpAddPlayerPanel();
        createFieldsAddPlayerPanel(team);
        setContentPane(currentPanel);
    }


    // Effect: return if empty, otherwise call getTeamByAge, if nothing returns then show message no players found,
    // if found then return a  list of player names, catch error for invalid input
    private void viewPlayersByAge(Team team) {
        String ageString = JOptionPane.showInputDialog("Enter the maximum age to view players:");
        if (ageString == null || ageString.isEmpty()) {
            return;
        }
        try {
            int age = Integer.parseInt(ageString);
            List<Player> players = team.getTeamByAge(age);
            if (players.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No players found for at least " + age + " years old.");
                return;
            }

            String playersInfo = "Players in " + team.getTeamName() + " aged at most " + age + ":\n";
            for (Player player : players) {
                playersInfo += player.getFirstName() + " " + player.getLastName() + "\n";
            }
            JOptionPane.showMessageDialog(this, playersInfo);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid age.");
        }
    }

    // Effect: return if empty, otherwise call getTeamByPosition, if nothing returns then show message no players found,
    // if found then return a  list of player names, catch error for invalid input
    private void viewPlayersByPosition(Team team) {
        String position = JOptionPane.showInputDialog("Enter the position to view players:");
        if (position == null || position.isEmpty()) {
            return; // User canceled or entered empty string
        }

        List<Player> players = team.getTeamByPosition(position);
        if (players.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No players found for position " + position + ".");
            return; // Exit the method if no players are found
        }

        String playersInfo = "Players in " + team.getTeamName() + " for position " + position + ":\n";
        for (Player player : players) {
            playersInfo += player.getFirstName() + " " + player.getLastName() + "\n";
        }
        JOptionPane.showMessageDialog(this, playersInfo);
    }

    // referenced : https://www.geeksforgeeks.org/java-jscrollpane/
// to enable scrolling with JScrollPane
// referenced: https://stackoverflow.com/questions/5895829/resizing-image-in-java
// to scale image
// Effect: set up panel and labels for all players in teams, icon is added in front of each player for
// better visualization.
    private void showAllPlayersPanel(Team team) {
        JPanel panel = new JPanel(new GridLayout(team.getTeam().size() + 1, 1)); // One row for each player
        JLabel titleLabel = new JLabel("All Players in " + team.getTeamName());
        panel.add(titleLabel);
        for (Player player : team.getTeam()) {
            JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Left-aligned layout for each player
            JLabel playerLabel = new JLabel(player.getFirstName() + " " + player.getLastName());
            JLabel ageLabel = new JLabel("Age: " + player.getAge());
            JLabel numLabel = new JLabel("Jersey: " + player.getNumber());
            JLabel positionLabel = new JLabel("Position: " + player.getPosition());
            ImageIcon icon = new ImageIcon("./data/xjerseyicon.JPG");
            Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Resize the image
            JLabel iconLabel = new JLabel(new ImageIcon(scaledImage)); // Load resized icon
            playerPanel.add(iconLabel);
            playerPanel.add(numLabel);
            playerPanel.add(playerLabel);
            playerPanel.add(ageLabel);
            playerPanel.add(positionLabel);
            panel.add(playerPanel);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        JOptionPane.showMessageDialog(this, scrollPane);
    }

    // referenced: phase 2 for logic
    // Effect: jsonWriter to save teams
    private void saveTeams() {
        try {
            jsonWriter.open();
            jsonWriter.write(teams);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Teams saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }


    // Effect: run GUI
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

        }));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppGUI().setVisible(true);
            }
        });
    }
}
