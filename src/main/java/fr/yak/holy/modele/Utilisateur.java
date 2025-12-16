package fr.yak.holy.modele;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utilisateur {
    private String code, nom, prenom, idConnexion, motDePasse;
    private Habilitation sonHabilitation;
    private ArrayList<MotDePasse> lesAnciensMdp = new ArrayList<>();

    public Utilisateur(String code, String nom, String prenom, String idConnexion, String motDePasse, Habilitation habil) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.idConnexion = idConnexion;
        this.motDePasse = motDePasse;
        this.sonHabilitation = habil;
    }

    // --- CORRECTION Q A1.1 : Renommage (Verbe au début + camelCase) ---
    public boolean existeAncienMdp(String motDePasseRecherche) {
        // Parcours de la liste des anciens mots de passe
        for (MotDePasse mdp : lesAnciensMdp) {
            if (mdp.getValMdp().equals(motDePasseRecherche)) {
                return true;
            }
        }
        return false;
    }

    public Habilitation getHabilitation() { return sonHabilitation; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }

    /**
     * Vérifie qu'un mot de passe est suffisamment complexe
     * @param mdp Le mot de passe à vérifier
     * @return vrai si le mot de passe respecte les règles de complexité
     */
    private boolean verifierMdp(String mdp) {
        boolean verif = false;
        int nb1 = 0; // Majuscules
        int nb2 = 0; // Minuscules
        int nb3 = 0; // Chiffres
        int nb4 = 0; // Spéciaux

        for (int i = 0; i < mdp.length(); i++) {
            char c = mdp.charAt(i);
            if (Character.isUpperCase(c)) nb1++;
            else if (Character.isLowerCase(c)) nb2++;
            else if (Character.isDigit(c)) nb3++;
            else if ((c >= 33 && c <= 46) || c == 64) nb4++;
        }
        // Règle du sujet : 12 car, 1 Maj, 3 min, 4 chiffres, 1 spécial
        if (mdp.length() >= 12 && nb1 >= 1 && nb2 >= 3 && nb3 >= 4 && nb4 >= 1) {
            verif = true;
        }
        return verif;
    }

    // --- CORRECTION Q A3.3a : Implémentation du getter ---
    public int getNiveauHabilitation() {
        return this.sonHabilitation.getNiveau();
    }

    // --- CORRECTION Q A2.3 : Préparation de la requête SQL ---
    /**
     * Retourne la requête SQL de mise à jour (pour validation pédagogique)
     * @return La requête SQL préparée
     */
    public String genererRequeteUpdate() {
        // L'élève doit trouver la bonne syntaxe SQL
        return "UPDATE Client SET mdp = ? WHERE idCli = ?";
    }

    /**
     * Tente de modifier le mot de passe de l'utilisateur.
     * Vérifie la complexité et l'unicité par rapport à l'historique.
     * @param nouveauMdp Le nouveau mot de passe souhaité
     * @return true si la modification a réussi, false sinon
     * --- CORRECTION Q A1.2 : Javadoc ---
     */
    // --- CORRECTION Q A2.2 : Implémentation complète ---
    public boolean modifierMdp(String nouveauMdp) {
        // 1. Vérifier la complexité
        if (!verifierMdp(nouveauMdp)) {
            return false;
        }

        // 2. Vérifier si déjà utilisé
        if (existeAncienMdp(nouveauMdp)) {
            return false;
        }

        // 3. Archiver l'ancien mot de passe
        MotDePasse mdpArchive = new MotDePasse(this.motDePasse, LocalDate.now());
        this.lesAnciensMdp.add(mdpArchive);

        // 4. Mettre à jour
        this.motDePasse = nouveauMdp;
        return true;
    }
}
