package test;
import fr.yak.holy.modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {
    Utilisateur unUtilisateur;

    @BeforeEach   // initialisation avant chaque test
    void init() {
        // Données issues du Document A4 du sujet
        Habilitation uneHabilitation = new Habilitation("ma01", 1, "master", true, false, true, false);
        unUtilisateur = new Utilisateur("U001", "Durand", "Louis", "lodurand", "Coe8@MatH279", uneHabilitation);

        // Simulation d'historique (Mots de passe conformes à 4 chiffres, donc valides)
        unUtilisateur.modifierMdp("Lae99_Mat00!");
        unUtilisateur.modifierMdp("M1ue@uiT455n");
    }

    @Test   // définit une méthode de test unitaire
    void verifHabilitation() {
        // Test fourni dans le Document A4
        assertTrue(unUtilisateur.getHabilitation().peutConsulter(), "Erreur sur le droit de lecture");
        assertFalse(unUtilisateur.getHabilitation().peutAjouter(), "Erreur sur le droit d'ajout");
        assertTrue(unUtilisateur.getHabilitation().peutModifier(), "Erreur sur le droit de modification");
        assertFalse(unUtilisateur.getHabilitation().peutSupprimer(), "Erreur sur le droit de suppression");
    }

    /* A COMPLÉTER SUR VOTRE COPIE (Question A3.1) */
    // AJOUTER ici la méthode @Test verifModifierMdp()

    @Test
    void verifModifierMdp() {
        assertFalse(unUtilisateur.modifierMdp("simple"), "Mot de passe trop simple");
        assertFalse(unUtilisateur.modifierMdp("Lae99_Mat00!"), "Mot de passe existe déjà");
        assertTrue(unUtilisateur.modifierMdp("Lae99@@123_Mat00!"), "Mot de passe pas bon");
    }
}
