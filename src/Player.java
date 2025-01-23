import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Player{

    private String pseudo;
    private int score;

    // Constructeur pour initialiser un joueur
    public Player(String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }

    // Function pour recuperer les scores du fichier Score Game.txt



    public static void displayAllScores(String filePath) {
        try {
            // Ouvrir le fichier pour lecture
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            System.out.println("Contenu du fichier Scores-Games.txt :");
            // Lire chaque ligne du fichier
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Afficher chaque ligne dans le terminal
            }

            // Fermer le lecteur
            reader.close();
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
        }
    }



    public static String[] loadFileAsArray(String filePath) {
        try {
            // Lire toutes les lignes du fichier dans une liste
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line); // Ajouter chaque ligne à la liste
            }

            reader.close();

            // Convertir la liste en tableau et la retourner
            return lines.toArray(new String[0]);
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
            return new String[0]; // Retourne un tableau vide en cas d'erreur
        }
    }





    public void updateScore(int points) {
        this.score += points;
    }


    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {

        // Place the player at the specified row and column
        board[row][col] = playerSymbol; // Place the player in the cell
    }



    // Fuction pour demander Combien de personne vont jouer
    // stocker dans la variable numberOfPlayer

    public static int requestPartyNumber() {
        Scanner scanner = new Scanner(System.in);
        byte numberOfPlayers;

        System.out.println("Please enter the number of players you would like to play (2 - 4):");
    // Boucle pour dire qu'il faut entrer un ciffre entre 2 et 4 inclus

        while (true) {
            try {
                numberOfPlayers = Byte.parseByte(scanner.nextLine());
                if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
                    return numberOfPlayers;
                } else {
                    System.out.println("Please enter a valid number between 2 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    // Fonction pour crée un Joueur en Objet
    public static Player[] createPlayers(int numberOfPlayers) {
        Scanner scanner = new Scanner(System.in);

        // Initialisation du tableau qui va contenir les joureurs (OBJ) en fonction du nombre de joueurs
        Player[] players = new Player[numberOfPlayers];


        // boucle qui va demander les Pseudo des Joueurs en fonction du nombre avec les securités
        for (int i = 0; i < numberOfPlayers; i++) {
            String pseudo;
            boolean validPseudo;

            do {
                validPseudo = true;
                System.out.println("Enter Player " + (i + 1) + " name (2-10 characters):");
                pseudo = scanner.nextLine();

                if (pseudo.length() < 2 || pseudo.length() > 10 || (i > 0 && pseudo.equals(players[0].getPseudo()))) {
                    System.out.println("The name must be between 2 and 10 characters and different from Player 1's name. Please try again.");
                    validPseudo = false;
                }

            } while (!validPseudo);

            // push du pseudo renseigner dans le tableau players  ( Player[] )
            players[i] = new Player(pseudo);
            System.out.println("Player " + (i + 1) + " is now " + pseudo + ".");
        }


        return players;
    }


}
