package main;


import fr.yak.holy.modele.Habilitation;
import fr.yak.holy.modele.Utilisateur;
import fr.yak.holy.view.AppliHoly;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Pour le TP, on propose de choisir un profil pour tester les droits
        Object[] options = {"Stagiaire (Niveau 1)", "Comptable (Niveau 5)", "Directrice (Niveau 10)"};
        int choix = JOptionPane.showOptionDialog(null,
                "Avec quel utilisateur voulez-vous lancer Holy ?",
                "Simulation de Connexion",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        Utilisateur userConnecte = null;

        // Création de fausses données selon le choix
        switch (choix) {
            case 0: // Stagiaire
                Habilitation h1 = new Habilitation("STAG", 1, "Stagiaire", true, false, false, false);
                userConnecte = new Utilisateur("U1", "Petit", "Jean", "jpetit", "mdp", h1);
                break;
            case 1: // Comptable
                Habilitation h2 = new Habilitation("COMPTA", 5, "Comptable", true, true, true, false);
                userConnecte = new Utilisateur("U2", "Compte", "Marie", "mcompte", "mdp", h2);
                break;
            case 2: // Directrice
                Habilitation h3 = new Habilitation("DIR", 10, "Directrice", true, true, true, true);
                userConnecte = new Utilisateur("U3", "Lenvy", "Jeanne", "admin", "mdp", h3);
                break;
            default:
                System.exit(0);
        }

        // Lancement de l'interface graphique
        // (Nécessaire pour le Thread Swing)
        final Utilisateur finalUser = userConnecte;
        SwingUtilities.invokeLater(() -> {
            AppliHoly frame = new AppliHoly(finalUser);
            frame.setVisible(true);
        });
    }
}
