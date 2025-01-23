import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game {

    // Codes de couleur ANSI pour afficher les couleurs dans la console
    public static final String RESET = "\u001B[0m";
    public static final String[] COLORS = { "\u001B[32m", "\u001B[34m", "\u001B[33m", "\u001B[35m" }; // Couleurs pour les joueurs

    public static void main(Player... players) {
        // Crée un plateau de jeu avec 10 lignes et 11 colonnes
        char[][] board = Board.CreateBoard(10, 11);
        short ScorePlayer2 = 0;
        short ScorePlayer1 = 0;

        // Positions fixes des joueurs (Joueur 1 sur E4, Joueur 2 en dessous, Joueur 3 à côté de Joueur 1, Joueur 4 à côté de Joueur 3)
        int[][] fixedPositions = {
                {3, 4}, // Joueur 1
                {4, 4}, // Joueur 2
                {3, 5}, // Joueur 3
                {4, 5}  // Joueur 4
        };

        // Placer les joueurs sur le plateau à leurs positions respectives
        int[][] playerPositions = new int[players.length][2];
        Random random = new Random();
        for (int i = 0; i < players.length; i++) {
            playerPositions[i][0] = fixedPositions[i][0];
            playerPositions[i][1] = fixedPositions[i][1];
            Player.placePlayer(board, (char) ('1' + i), fixedPositions[i][0], fixedPositions[i][1]);
        }

        // Afficher le plateau après avoir placé les joueurs
        Board.showBoard(board);

        // Choisir aléatoirement le premier joueur
        Random random = new Random();
        int currentPlayerIndex = random.nextInt(players.length); // Sélection aléatoire
        System.out.println("Le joueur qui commence est : " + COLORS[currentPlayerIndex] + players[currentPlayerIndex].getPseudo() + RESET);

        boolean[] isEliminated = new boolean[players.length]; // Tableau pour savoir quels joueurs sont éliminés

        // Boucle du jeu (tour par tour)
        while (true) {

            // Compte combien de joueurs ne sont pas éliminés
            int remainingPlayers = 0;

            // Parcours du tableau qui garde une trace des joueurs éliminés
            for (int i = 0; i < isEliminated.length; i++) {
                // Si le joueur n'est pas éliminé (c'est-à-dire que isEliminated[i] est false)
                if (!isEliminated[i]) {
                    remainingPlayers++;  // On augmente le compteur des joueurs restants
                }
            }


            // Si un seul joueur reste, il gagne
            if (remainingPlayers == 1) {
                for (int i = 0; i < players.length; i++) {
                    if (!isEliminated[i]) {
                        System.out.println(COLORS[i] + players[i].getPseudo() + RESET + " est le gagnant !");
                        return; // Fin du jeu
                    }
                }
            }

            // Si le joueur est éliminé, on passe son tour
            if (isEliminated[currentPlayerIndex]) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
                continue;
            }

            Player currentPlayer = players[currentPlayerIndex];
            System.out.println(COLORS[currentPlayerIndex] + currentPlayer.getPseudo() + RESET + "'s turn");

            int currentRow = playerPositions[currentPlayerIndex][0];
            int currentCol = playerPositions[currentPlayerIndex][1];

            // Vérifie si le joueur est bloqué
            if (Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
                System.out.println(COLORS[currentPlayerIndex % COLORS.length] + currentPlayer.getPseudo() + RESET + " is blocked and has lost!");
                System.out.println("Remaining players:");
                currentPlayer.updateScore(-2);
                System.out.println(currentPlayer.getPseudo() + "new score: " + currentPlayer.getScore());

                // Announce remaining players
                for (int i = 0; i < players.length; i++) {
                    if (i != currentPlayerIndex && !Cell.checkIfPlayerLost(board, playerPositions[i][0], playerPositions[i][1])) {
                        players[i].updateScore(5);
                        System.out.println(players[i].getPseudo() + " new score: " + players[i].getScore());

                    }
                }

                // Envoie dans le fichier txt
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Scores.txt", true))) {
                    // Write the current player's score
                    writer.write(currentPlayer.getPseudo() + " score: " + currentPlayer.getScore());
                    writer.newLine();

                    // Write the scores of other players
                    for (int i = 0; i < players.length; i++) {
                        if (i != currentPlayerIndex) {
                            writer.write(players[i].getPseudo() + " score: " + players[i].getScore());
                            writer.newLine();
                        }
                    }
                    System.out.println("Scores successfully written to Scores Games.txt");
                } catch (IOException e) {
                    System.err.println("An error occurred while writing scores to the file: " + e.getMessage());
                }

                //System.out.println(players[0].getPseudo() + players[0]);
                break; // End the game
                System.out.println(COLORS[currentPlayerIndex] + currentPlayer.getPseudo() + RESET + " est bloqué et a perdu!");
                isEliminated[currentPlayerIndex] = true; // Marque le joueur comme éliminé
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length; // Passe au joueur suivant
                continue;
            }

            // Effectuer le mouvement du joueur
            boolean validMove = false;

            // Continue asking for a valid move until it's made
            while (!validMove) {
                // Ask the player to make a move and get the new position
                int[] newPosition = Cell.MovePlayer(board, (char) ('1' + currentPlayerIndex), currentRow, currentCol);

                // If the move is valid, update the player's position on the board
                if (newPosition != null) {
                    playerPositions[currentPlayerIndex][0] = newPosition[0];
                    playerPositions[currentPlayerIndex][1] = newPosition[1];
                    validMove = true;

                    // Display the updated board
                    Board.showBoard(board);
                } else {
                    // Si le mouvement est invalide, on redemande à ce joueur de jouer
                    Board.showBoard(board);
                    System.out.println(COLORS[currentPlayerIndex] + currentPlayer.getPseudo() + RESET + "'s turn");
                }
            }

            // Passer au joueur suivant
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

        }
    }
}
