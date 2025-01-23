/*
    mergeSort:
        si array est possede 1 ou 0 donnée:
            on arrete la fonction
        on cherche le milieu du tableau d'origine
        tableau left= les elements du tableau d'orgine jusqu'au milier
        tableau droit= les elementes qu'il reste
        on copie les elements du tableau d'origine jusqu'a l'elements du millieu dans le tableau left
        on copie les elements du tableau d'origine du millieu jusqu'au dernier element dans le tableau right
        on trie le tableau left on rappelant la fonction mergeSort
        on trie le tableau right on rappelant la fonction mergeSort
        on appelle la fonction merge pour fuionner les tableau left et right

    merge:
        on initialise les variable qui vont parcourirs les tableau
        tant que i et j ne depassent pas la taille de leurs propre tableau
            si left[i] est plus petit que de right[j]:
                on insere left[i] dans arr et on incremente k et i
            sinon:
                on insere right[j] dans arr et on incremente k et j
        tant que i est inferieur a la taille du tableau left:
            on ajoute les element du tableau left dans le tableau arr
        tant que j est inferieur a la taille du tableau right:
            on ajoute les element du tableau right dans le tableau arr

 */

public class sort {
    public static void mergeSort(int[] myArray) {
        if (myArray.length <= 1) {
            return;
        }

        int mid = myArray.length / 2;
        int[] left = new int[mid];
        int[] right = new int[myArray.length - mid];

        System.arraycopy(myArray, 0, left, 0, mid);
        System.arraycopy(myArray, mid, right, 0, myArray.length - mid);

        mergeSort(left);
        mergeSort(right);

        merge(myArray, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
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

