# Sport Games schedule

### Goal: 
The project aims to allow anyone to create a quick schedule for sports team. I am interested because I have not seen a lot of program dedicated to provide a convenient way to create a schedule for sport games.

### User Stories:

#### Phase 0 & 1:
During Phase 0 & 1, the project wants to create a manager to hold information of a match between 2 teams.

- As a user, I want to be able to add players to team
- As a user, I want to be able to remove players from team
- As a user, I want to be able to add info of players as I create them.
- As a user, I want to be able to view info of each player.
- As a user, I want to be able to view who is on the team.
- As a user, I want to be able to view by sorting info of players in team. i.e. quick view by player position/ age
- As a user, I want to be able to update which team won.
- As a user, I want to be able to save my progress (if I so choose)
- As a user, I want to be able to be able to load my progress (if I so choose)

### User flow
Home&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Default Load</br>
<img width="250" alt="home" src="https://github.com/user-attachments/assets/bdd02677-9d13-4370-a922-68838ccc3afc" />
<img width="250" alt="default-load" src="https://github.com/user-attachments/assets/88aae600-0f1d-4f29-a60c-48347d22c205" /></br>
Manage Team &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;Manage Player</br>
<img width="250" alt="manage-team" src="https://github.com/user-attachments/assets/b34b61b1-3ef8-4fa1-90d4-7c9196c1bc46" />
<img width="250" alt="manage-player" src="https://github.com/user-attachments/assets/2887db8e-25be-43e1-9d27-a99c7a68dd3f" /></br>
Add Player &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;Manage Result Add Player</br>
<img width="250"  alt="add-player" src="https://github.com/user-attachments/assets/923ab083-2b3e-42d5-85c6-58fbe6fbc21f" />
<img width="250" alt="added-player" src="https://github.com/user-attachments/assets/b94cb1da-11fe-4eef-8e98-fce9179392be" /></br>
Manage See All Player &emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;Remove Player</br>
<img width="250" alt="see-all-players" src="https://github.com/user-attachments/assets/fa0e1766-616a-4db7-8868-dc047e0a115f" />
<img width="250" alt="remove-player" src="https://github.com/user-attachments/assets/8a8bf75b-d7c2-4dbd-85ea-02ee204d503c" /></br>
Result Remove Player &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;View By Position</br>
<img width="250" alt="removed-player" src="https://github.com/user-attachments/assets/303475e3-37ba-486d-a1c7-21cb1b94756c" />
<img width="250" alt="view-by-position" src="https://github.com/user-attachments/assets/2c0f789e-876d-49b8-b829-3c7657d16983" /></br>
View By Position Result &emsp;&emsp;&emsp;&emsp;&emsp;View By Age</br>
<img width="250" alt="viewed-by-position" src="https://github.com/user-attachments/assets/cc3f9568-6d75-4a88-a752-ffdffa342014" />
<img width="250" alt="view-by-age" src="https://github.com/user-attachments/assets/84ecfb2b-a00f-4ba3-b13c-b5a34a93001e" /></br>
View By Age Result</br>
<img width="250" alt="viewed-by-age" src="https://github.com/user-attachments/assets/c750a472-f0fb-494a-8203-3c80e070901e" />

### Instructions for Grader

- Please select Load Teams, there is already an existing json file, so it is easier to test functionality.
  - You can reload the state of my application by Load Teams
- Select either manage team1 or team2
- Select See All Players to see all players
  - You can locate my visual component, it is the icon in front of each player for better visualization, actual jpg file is in data file
-  Select See Players by position or age to see all players according to filter, it will return a list of player names
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by Add Player
- You can remove player by Remove Player
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by Return to Select Team to Manage, select the other team and Add Player
- You can save the state of my application by Save Teams


### Phase 4: Task 2
Sun Apr 07 23:22:49 PDT 2024
Teams are loaded
Sun Apr 07 23:22:49 PDT 2024
Team is created
Sun Apr 07 23:22:49 PDT 2024
Player is created
Sun Apr 07 23:22:49 PDT 2024
Player added
Sun Apr 07 23:22:49 PDT 2024
Player is created
Sun Apr 07 23:22:49 PDT 2024
Player added
Sun Apr 07 23:22:49 PDT 2024
Player is created
Sun Apr 07 23:22:49 PDT 2024
Player added
Sun Apr 07 23:22:49 PDT 2024
Player is created
Sun Apr 07 23:22:49 PDT 2024
Player added
Sun Apr 07 23:22:49 PDT 2024
Team is created
Sun Apr 07 23:22:49 PDT 2024
Player is created
Sun Apr 07 23:22:49 PDT 2024
Player added
Sun Apr 07 23:22:49 PDT 2024
Player is created
Sun Apr 07 23:22:49 PDT 2024
Player added
Sun Apr 07 23:23:07 PDT 2024
Player is created
Sun Apr 07 23:23:07 PDT 2024
Player added
Sun Apr 07 23:23:13 PDT 2024
Viewing players by position
Sun Apr 07 23:23:17 PDT 2024
Viewing players by age
Sun Apr 07 23:23:22 PDT 2024
Player is removed
Sun Apr 07 23:23:28 PDT 2024
Teams are saved

Process finished with exit code 0


### Phase 4: Task 3

AppGUI is handling how many teams could be made, we could create an extra class, and have a list of teams instead.

This way the code would be easier to understand and the diagram would make more sense.
