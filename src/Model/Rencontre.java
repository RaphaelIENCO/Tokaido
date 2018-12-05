package Model;
public  class Rencontre extends Cartes {

    protected String description;

    public Rencontre(String nom,String description){
        super(nom);
        this.description=description;
    }

    public String getDescription(){
        return this.description;
    }
}
