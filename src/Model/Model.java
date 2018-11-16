package Model;

import java.util.ArrayList;

public class Model {   // classe dans laquel on initie toutes les carte du joueur.
    ArrayList<Rencontre>listRecontre = new ArrayList<Rencontre>();
    ArrayList<Repas>listRepas  = new ArrayList<>();
    public Model(){
        initPartie();
    }

    public void initPartie(){ //Creation de toutes les differentes cartes du jeu
        initRencontre();
        initRepas();
        initSouvenir();
    }

    public void initRencontre(){ //Init de toutes les rencontre

    }

    public void initRepas(){

    }

    public void initSouvenir(){
        for(int i=0 ; i<3 ;i++){
            listRepas.add(new Repas("Misoshiru",1,6));
            listRepas.add(new Repas("Nigirineshi",1,6));
            listRepas.add(new Repas("Dango",1,6));
        }
        for (int i=0 ; i<2 ;i++){
            listRepas.add(new Repas("Yakitori",2,6));
            listRepas.add(new Repas("Soba",2,6));
            listRepas.add(new Repas("Sushi",2,6));
            listRepas.add(new Repas("Tofu",2,6));
            listRepas.add(new Repas("Tempura",2,6));
        }
        listRepas.add(new Repas("Unagi",3,6));
        listRepas.add(new Repas("Donburi",3,6));
        listRepas.add(new Repas("Udon",3,6));
        listRepas.add(new Repas("Fugu",3,6));
        listRepas.add(new Repas("TaiMeshi",3,6));
        listRepas.add(new Repas("Sashini",3,6));
    }

}
