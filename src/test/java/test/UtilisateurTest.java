package test;
import fr.yak.holy.modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {
    Utilisateur unUtilisateur;

    @BeforeEach
    void init() {
        Habilitation uneHabilitation = new Habilitation("ma01", 1, "master", true, false, true, false);
        // Note: Le MDP initial doit respecter les règles pour que le scénario soit réaliste
        unUtilisateur = new Utilisateur("U001", "Durand", "Louis", "lodurand", "Coe8@MatH279", uneHabilitation);
    }

    @Test
    void verifModifierMdp() {
        // [cite: 78] Question A3.1 : Test de la méthode modifierMdp

        // Cas 1 : Échec - Mot de passe trop simple
        assertFalse(unUtilisateur.modifierMdp("toto"), "Le MDP simple ne devrait pas être accepté");

        // Cas 2 : Échec - Mot de passe déjà utilisé (simulation)
        // On force un changement valide d'abord pour remplir l'historique
        String mdpValide1 = "Azerty1234!!"; // Supposons qu'il valide les règles (attention au regex du sujet)
        // Le sujet demande 4 chiffres. "Azerty1234!!" -> 1 maj, 5 min, 4 chiffres, 2 char spec. OK.
        assertTrue(unUtilisateur.modifierMdp(mdpValide1));

        // On essaie de remettre l'ancien (le constructeur initial)
        assertFalse(unUtilisateur.modifierMdp("Coe8@MatH279"), "L'ancien MDP ne devrait pas être accepté");

        // Cas 3 : Succès
        String mdpValide2 = "Wagon5678..@";
        assertTrue(unUtilisateur.modifierMdp(mdpValide2), "Ce MDP respecte toutes les règles");
    }
}
