import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.Arrays;

public class SaveScores {

    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("caca.txt");

        String[] ScoresPlayer = {
                "Player1",
                "Plater2"
        };


        writer.write(ScoresPlayer[0]);





    }


}
