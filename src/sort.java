/*
    j'initialise la longueur du tableau
    je parcours le tableau:
        j'initialise la variable key avec la valeur le l'index i
        j'initalise j avec la valeur de i-1
        tant que j est superieur a 0 et que j est superieur a key
            on fait reculer j
        on insere la valeur de key a l'index de j
 */

public class sort {
    public static void ascending(int[] myArray) {
        int n = myArray.length;

        for (int i = 1; i < n; i++) {
            int key = myArray[i];
            int j = i - 1;

            while (j >= 0 && myArray[j] > key) {
                myArray[j + 1] = myArray[j];
                j--;
            }

            myArray[j + 1] = key;
        }
    }


    /*
    j'initialise la longueur du tableau
    je parcours le tableau:
        j'initialise la variable key avec la valeur le l'index i
        j'initalise j avec la valeur de i-1
        tant que j est superieur a 0 et que j est inferieur a key
            on fait reculer j
        on insere la valeur de key a l'index de j
 */
    public static void descending(int[] myArray) {
        int n = myArray.length;

        for (int i = 1; i < n; i++) {
            int key = myArray[i];
            int j = i - 1;

            while (j >= 0 && myArray[j] < key) {
                myArray[j + 1] = myArray[j];
                j--;
            }

            myArray[j + 1] = key;
        }
    }
}

