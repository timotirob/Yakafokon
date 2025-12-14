package fr.yak.holy.modele;

public class ElementMenu {
    private String nom;
    private int niveauHabilitation;
    private boolean accessible = false;

    /**
     * @param nom Le libellé du menu
     * @param niveauHabilitation Niveau min requis pour voir ce menu
     */
    public ElementMenu(String nom, int niveauHabilitation) {
        this.nom = nom;
        this.niveauHabilitation = niveauHabilitation;
    }

    public int getNiveauHabilitation() {
        return niveauHabilitation;
    }

    public String getNom() {
        return nom;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void rendreAccessible() {
        this.accessible = true;
    }
}