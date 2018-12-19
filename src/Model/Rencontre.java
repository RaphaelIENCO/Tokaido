package Model;
public  class Rencontre extends Cartes {

    private String description;

    /**
     * Les joueurs font des "rencontres"
     **/

    public Rencontre(String nom,String description){
        super(nom);
        this.description=description;
    }

    public String getDescription(){
        return this.description;
    }
}
