import java.util.Arrays;
import java.util.Random;

//Jason DA VEIGA L2S4
class TableauBonus {
    int tab[];

    TableauBonus(int tabl[]) {
        tab = tabl;
    }

    //
    int[] sous_tableau_Kadane(int lg) {
    //maxA correspond à la somme de la séquence actuelle (lors de la boucle)
        int maxA = 0;
    //maxB sera la somme maximale globlale, elle sera la plus grande si le programme est correct
        int maxB = 0;
        int debut = 0;
        int fin = 0;
    //valeur de début temporaire, qui remplacera debut si maxB est plus petit que maxA
    //En gros le potentiel indice de début 
        int debut_temp = 0;

        for (int i = 0; i < lg; i++) {
            maxA= maxA+ tab[i];
            if (tab[i] > maxA) {
                maxA = tab[i];
                debut_temp = i;
            }
            if (maxB < maxA) {
                maxB = maxA;
                debut = debut_temp;
                fin = i;
            }
        }
       //Copy of range recopie une plage spécifique du tableau, aucun effet sur la compléxité
       // expliquer sur Tableau.java
        return Arrays.copyOfRange(tab, debut, fin + 1);
    }
    
void afficherTableau(int[] tableau) {
    System.out.print("[");
    for (int i = 0; i < tableau.length; i++) {
        System.out.print(tableau[i]);
        if (i < tableau.length - 1) {
            System.out.print(", ");
        }
    }
    System.out.println("]");
}

    public static void main(String[] args) {
        int myTableau[] = {20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15};
        TableauBonus tableau = new TableauBonus(myTableau);
        int lg = myTableau.length;
        int[] sstabMax = null;
        
        System.out.println("Le tableau:");
        tableau.afficherTableau(myTableau);
        System.out.println("Sous ensemble max:");
         sstabMax = tableau.sous_tableau_Kadane(lg);
        tableau.afficherTableau(sstabMax);
        sstabMax=null;
        
        
        Random random = new Random();
        int myTableau2[] = new int[6];
        TableauBonus tableau2 = new TableauBonus(myTableau2);
    
        for (int i = 0; i < myTableau2.length; i++) {
        myTableau2[i] = -30 + (int) (Math.random() * (30 - (-30)));
        }
    
        int lg2 = myTableau2.length;
    
        System.out.println("Le tableau (valeurs rand):");
        tableau2.afficherTableau(myTableau2);
         System.out.println("Sous ensemble max:");
        sstabMax = tableau2.sous_tableau_Kadane(lg2);
        tableau2.afficherTableau(sstabMax);
        
    }


}

