package fr.yak.holy.modele;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utilisateur {
    private String code, nom, prenom, idConnexion, motDePasse;
    private Habilitation sonHabilitation;
    private ArrayList<MotDePasse> lesAnciensMdp = new ArrayList<MotDePasse>();

    public Utilisateur(String code, String nom, String prenom, String idConnexion, String motDePasse, Habilitation habil) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.idConnexion = idConnexion;
        this.motDePasse = motDePasse;
        this.sonHabilitation = habil;
    }

    // --- CODE FOURNI DANS LE DOCUMENT A3 (Avec les défauts à corriger) ---

    /**
     *
     *
     * @param motDePasseARechercher lemot de passe que l'on cherche dans la liste des anciens
     * @return boolean true si le mot de passe est trouvé
     */

    // NOTE POUR L'ELEVE : Cette méthode ne respecte pas les conventions (Question A1.1)
    public boolean existeAncienMdp(String motDePasseARechercher) {
        boolean existe = false;
        int i = 0;
        while (i < this.lesAnciensMdp.size() && existe == false) {
            if (this.lesAnciensMdp.get(i).getValMdp().equals(motDePasseARechercher)) {
                existe = true;
            } else {
                i = i + 1;
            }
        }
        return existe;
    }

    public Habilitation getHabilitation() { return sonHabilitation; }

    /**
     * Vérifie qu'un mot de passe est suffisamment complexe
     * @param mdp Le mot de passe à vérifier
     * @return vrai si le mot de passe respecte les règles de complexité
     */
    private boolean verifierMdp(String mdp) {
        // Code fourni dans le sujet (Doc A3)
        boolean verif = false;
        int nb1 = 0; // Majuscules
        int nb2 = 0; // Minuscules
        int nb3 = 0; // Chiffres
        int nb4 = 0; // Spéciaux

        for (int i = 0; i < mdp.length(); i = i + 1) {
            char c = mdp.charAt(i);
            if (Character.isUpperCase(c)) { nb1 = nb1 + 1; }
            else if (Character.isLowerCase(c)) { nb2 = nb2 + 1; }
            else if (Character.isDigit(c)) { nb3 = nb3 + 1; }
            else if (c >= 33 && c <= 46 || c == 64) {
                nb4 = nb4 + 1;
            }
        }
        if (mdp.length() >= 12 && nb1 >= 1 && nb2 >= 3 && nb3 >= 4 && nb4 >= 1) {
            verif = true;
        }
        return verif;
    }

    /** @return le niveau de l’habilitation de l’utilisateur */
    public int getNiveauHabilitation() {
        /* A COMPLÉTER (Question A3.3) */
        return this.sonHabilitation.getNiveau() ;
    }


    public boolean modifierMdp(String valMdp) {
        /* A COMPLÉTER (Question A2.2) */

        if (!verifierMdp(valMdp))
            return false ;

        MotDePasse motDePasseAArchiver = new MotDePasse(this.motDePasse,LocalDate.now()) ;

        if (existeAncienMdp(valMdp))
            return false ;

        this.lesAnciensMdp.add(motDePasseAArchiver) ;
        this.motDePasse = valMdp ;
        return true ;

    }

    // Getters nécessaires pour le fonctionnement global (non fournis dans l'extrait mais implicites)
    public String getMotDePasse() { return motDePasse; }

    public String getNom() {
        return this.nom ;
    }

    public String getPrenom() {
        return this.prenom ;
    }
}
