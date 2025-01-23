import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game {

    // Codes de couleur ANSI pour afficher les couleurs dans la console
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";  // Couleur verte pour le joueur 1
    public static final String BLUE = "\u001B[34m";   // Couleur bleue pour le joueur 2
    public static final String YELLOW = "\u001B[33m";  // Couleur jaune pour le joueur 3
    public static final String PURPLE = "\u001B[35m";  // Couleur violette pour le joueur 4

    public static void main(Player... players) {
        // Crée un plateau de jeu avec 10 lignes et 11 colonnes
        char[][] board = Board.CreateBoard(10, 11);

        // Placer les joueurs aux coordonnées fixes sur le plateau
        if (players.length > 0) Player.placePlayer(board, '1', 4, 4); // Joueur 1 en E4
        if (players.length > 1) Player.placePlayer(board, '2', 5, 4); // Joueur 2 en E5
        if (players.length > 2) Player.placePlayer(board, '3', 4, (5)); // Joueur 3 en F4
        if (players.length > 3) Player.placePlayer(board, '4', 5, 5); // Joueur 4 en F5


        // Afficher le plateau après avoir placé les joueurs
        Board.showBoard(board);

        // Choisir aléatoirement le premier joueur
        Random random = new Random();
        int currentPlayerIndex = random.nextInt(players.length); // Sélection aléatoire

        boolean[] isEliminated = new boolean[players.length]; // Tableau pour savoir quels joueurs sont éliminés

        // Boucle du jeu (tour par tour)
        while (true) {

            // Compte combien de joueurs ne sont pas éliminés
            int remainingPlayers = 0;
            for (int i = 0; i < isEliminated.length; i++) {
                if (!isEliminated[i]) {
                    remainingPlayers++;
                }
            }

            // Si un seul joueur reste, il gagne
            if (remainingPlayers == 1) {
                for (int i = 0; i < players.length; i++) {
                    if (!isEliminated[i]) {
                        System.out.println(players[i].getPseudo() + RESET + " est le gagnant !");
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

            // Déterminer la couleur du joueur
            String currentColor = "";
            switch (currentPlayerIndex) {
                case 0: currentColor = GREEN; break;
                case 1: currentColor = BLUE; break;
                case 2: currentColor = YELLOW; break;
                case 3: currentColor = PURPLE; break;
            }

            // Afficher le tour du joueur avec sa couleur
            System.out.println(currentColor + currentPlayer.getPseudo() + RESET + "'s turn");

            // Récupérer la position actuelle à partir des coordonnées manuelles
            int currentRow = (currentPlayerIndex == 0 || currentPlayerIndex == 2) ? 3 : 4;
            int currentCol = (currentPlayerIndex == 0 || currentPlayerIndex == 1) ? 4 : 5;

            // Vérifie si le joueur est bloqué
            if (Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
                System.out.println(currentColor + currentPlayer.getPseudo() + RESET + " is blocked and has lost!");
                currentPlayer.updateScore(-2);
                System.out.println(currentPlayer.getPseudo() + " new score: " + currentPlayer.getScore());

                // Annonce des joueurs restants
                for (int i = 0; i < players.length; i++) {
                    if (i != currentPlayerIndex && !Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
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

                break; // End the game
            }

            // Effectuer le mouvement du joueur
            boolean validMove = false;
            while (!validMove) {
                int[] newPosition = Cell.MovePlayer(board, (char) ('1' + currentPlayerIndex), currentRow, currentCol);

                // Si le mouvement est valide, mettre à jour la position du joueur sur le plateau
                if (newPosition != null) {
                    currentRow = newPosition[0];
                    currentCol = newPosition[1];
                    validMove = true;

                    // Afficher le plateau mis à jour
                    Board.showBoard(board);
                } else {
                    // Si le mouvement est invalide, redemander à ce joueur de jouer
                    Board.showBoard(board);
                    System.out.println(currentColor + currentPlayer.getPseudo() + RESET + "'s turn");
                }
            }

            // Passer au joueur suivant
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }
}
