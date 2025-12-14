package fr.yak.holy.view;


import fr.yak.holy.modele.Utilisateur;
import fr.yak.holy.modele.ElementMenu ;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppliHoly extends JFrame {

    private Utilisateur leUtilConnecte;
    private ArrayList<ElementMenu> lesElementsMenu = new ArrayList<>();

    // Composants graphiques
    private JMenuBar menuBar;
    private JMenu menuGeneral;
    private JLabel lblBienvenue;

    public AppliHoly(Utilisateur unUtil) {
        this.leUtilConnecte = unUtil;

        // --- 1. Configuration de la Fenêtre ---
        this.setTitle("Holy - Gestion Agence (Utilisateur: " + unUtil.getNom() + ")");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrer
        this.setLayout(new BorderLayout());

        // --- 2. Initialisation des Données (Simulation BDD) ---
        // On crée des menus avec des niveaux de sécurité croissants
        lesElementsMenu.add(new ElementMenu("Accueil", 0));         // Tout le monde
        lesElementsMenu.add(new ElementMenu("Vente", 1));           // Stagiaires et +
        lesElementsMenu.add(new ElementMenu("Comptabilité", 5));    // Comptables
        lesElementsMenu.add(new ElementMenu("RH", 8));              // DRH
        lesElementsMenu.add(new ElementMenu("Administration", 10)); // Directrice (Mme Lenvy)

        // --- 3. Construction de la barre de menu ---
        menuBar = new JMenuBar();
        menuGeneral = new JMenu("Menu Principal");

        // ========================================================================
        // ZONE A COMPLÉTER PAR L'ÉLÈVE (Basé sur le Sujet - Question A3.3b)
        // ========================================================================

        /* CONSIGNE : Parcourir lesElementsMenu.
           Si le niveau de l'utilisateur est suffisant, rendre le menu accessible
           et l'ajouter à l'interface graphique.
         */


        // ========================================================================

        menuBar.add(menuGeneral);
        this.setJMenuBar(menuBar);

        // --- 4. Contenu de la page ---
        // LIGNE CI DESSOUS A DECOMMENTER ENSUITE
        // lblBienvenue = new JLabel("Bienvenue " + unUtil.getPrenom() + " (Niveau " + niveauUser + ")", SwingConstants.CENTER);
        lblBienvenue.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblBienvenue, BorderLayout.CENTER);
    }
}