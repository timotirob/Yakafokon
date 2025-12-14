package fr.yak.holy.modele;

public class Habilitation {
    private String code, role;
    private int niveau;
    private boolean consultation, ajout, modification, suppression;

    public Habilitation(String code, int niveau, String role, boolean consultation, boolean ajout, boolean modification, boolean suppression) {
        this.code = code;
        this.niveau = niveau;
        this.role = role;
        this.consultation = consultation;
        this.ajout = ajout;
        this.modification = modification;
        this.suppression = suppression;
    }

    // Getters nécessaires pour le TP
    public int getNiveau() { return niveau; }
    public boolean peutConsulter() { return consultation; }
    public boolean peutAjouter() { return ajout; }
    public boolean peutModifier() { return modification; }
    public boolean peutSupprimer() { return suppression; }
}
