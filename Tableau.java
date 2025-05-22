import java.util.Random;
import java.util.Arrays;

// Jason DA VEIGA L2S4
class Tableau{
    int tab[];
    Tableau(int tabl[]){
        tab=tabl;
    }
     
//O(n^3)  
int[] somme_sous_tableV1(int lg) {
    int max =  tab[0];
  //  int max = tab[(int)(Math. floor(Math. random()*(lg)))];
    //ss ensemble à renvoyer
   // int tmax = tab[0];
    int[] tabM = {tab[0]};
    for (int i = 0; i < lg; i++){
        for (int j = i; j < lg; j++) {
            int tmax = 0;
            // tableau temporaire qui permet de calculer tout les sous-ensemble
            int[] tempTab = new int[j - i + 1];
            tmax = 0; // reset de tmax
            // sous séquences et leurs sommes
            for (int k = i; k <= j; k++) {
                tempTab[k - i] = tab[k];
                tmax += tab[k];
            }
            if (tmax > max) {
                max = tmax;
                tabM = tempTab;
            }
        }
    }
    return tabM;
}

//O(n^2)
// S0,0 
// S0,1 = S0,0 + T[1]tempTab = null;
// S0,2 = S0,1 + T[2]
// S0,3 = S0,2 + T[3]
int[] somme_sous_tableV2(int lg) {
    //int max = tab[(int)(Math. floor(Math. random()*(lg-1)))];
    int max = tab[0];
    int[] tabM = {tab[0]};

    for (int i = 0; i < lg; i++) {
        int tmax = 0;
        for (int j = i; j < lg; j++) {
            tmax += tab[j];
            if (tmax > max) {
                max = tmax;
                //Array.copyOfRange permet de copier une partie spécifique du tableau O(m)
                /* la plage  recopier varie en fonction de la taille du tableau original, alors la complexité dépend
                  de la manière dont la taille de la plage est déterminée. (alors O(m))*/
                tabM = (int[])Arrays.copyOfRange(tab, i, j + 1);
            }
        }
    }

    return tabM;
}

// O( n*log2(N) ) 
//On découpe au milieu, puis on compare a droite et a gauche
int[] somme_sous_tableV3(int debut, int fin) {
    // Cas de base : un seul élément dans le tableau
    if (debut == fin) {
        return new int[]{tab[debut]};
    }
    int milieu = debut + (fin - debut) / 2;
    //appels récursive
    int[] Gauche = somme_sous_tableV3(debut,milieu);
    int[] Droite = somme_sous_tableV3(milieu + 1, fin);
    int[] ssTabMilieu = ssTabMilieuMethod(debut,milieu,fin);
        int sommeGauche = 0;
        for (int val : Gauche) {
            sommeGauche += val;
        }

        int sommeDroite = 0;
        for (int val : Droite) {
            sommeDroite += val;
        }

        int sommeMilieu = 0;
        for (int val : ssTabMilieu) {
            sommeMilieu += val;
        }

    // Somme maximale du milieu à la droite
    if (sommeMilieu >= sommeGauche && sommeMilieu >= sommeDroite) {
        return ssTabMilieu;
    } else if (sommeGauche >= sommeDroite) {
        return Gauche;
    } else {
        return Droite;
    }
}

    private int[] ssTabMilieuMethod(int debut, int milieu, int fin) {
        // Partie gauche
        int sommeGauche = tab[milieu];
        int somme = 0;
        int gaucheIndex = milieu;

        for (int i = milieu; i >= debut; i--) {
            somme += tab[i];
            if (somme > sommeGauche) {
                sommeGauche = somme;
                gaucheIndex = i;
            }
        }
        // Partie droite
        int sommeDroite = tab[milieu+1];
        somme = 0;
        int droiteIndex = milieu + 1;

        for (int j = milieu + 1; j <= fin; j++) {
            somme += tab[j];
            if (somme > sommeDroite) {
                sommeDroite = somme;
                droiteIndex = j;
            }
        }
        return Arrays.copyOfRange(tab, gaucheIndex, droiteIndex + 1);
    }

//Affichage de tableau
void afficherTableau(int[] tableau) {
    if (tableau == null) {
        System.out.println("[]");  // Affiche un tableau vide si null
        return;
    }
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
    int lg = myTableau.length;
    int max;

    Tableau tableau = new Tableau(myTableau);
    int tabMax[];

    System.out.println("Le tableau:");
    tableau.afficherTableau(myTableau);
    
    System.out.println("Méthode 1:");
   //Méthode 1 sur tableau
    tabMax = tableau.somme_sous_tableV1(lg);
    tableau.afficherTableau(tabMax);
    tabMax=null;
    
     System.out.println("Méthode 2:");
   //Méthode 2 sur tableau
    tabMax = tableau.somme_sous_tableV2(lg);
    tableau.afficherTableau(tabMax);
     tabMax=null;

    System.out.println("Méthode 3:");
    //Méthode 3 sur tableau
    tabMax = tableau.somme_sous_tableV3(0,lg-1);
    tableau.afficherTableau(tabMax);
    tabMax=null;


    Random random = new Random();
    int myTableau2[] = new int[10];
    Tableau tableau2 = new Tableau(myTableau2);

    for (int i = 0; i < myTableau2.length; i++) {
        myTableau2[i] = -30 + (int) (Math.random() * (30 - (-30)));
    }

    int lg2 = myTableau2.length;

    System.out.println("Le tableau: (Méthode 1) ");
    tableau2.afficherTableau(myTableau2);

    //Méthode 1 sur tableau a valeur aléatoire
    tabMax = tableau2.somme_sous_tableV1(lg2);
    tableau2.afficherTableau(tabMax);
    tabMax=null;

    //Méthode 2 sur tableau a valeur aléatoire
     tabMax = tableau2.somme_sous_tableV2(lg2);
     tableau2.afficherTableau(tabMax);
     tabMax=null;

    //Méthode 3 sur tableau a valeur aléatoire
    tabMax = tableau2.somme_sous_tableV3(0, lg2-1);
    tableau2.afficherTableau(tabMax);

}

}
