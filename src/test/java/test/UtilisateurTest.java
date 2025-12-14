package test;
import fr.yak.holy.modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {
    Utilisateur unUtilisateur;

    @BeforeEach   // initialisation avant chaque test
    void init() {
        // 1. Initialisation avec un MDP conforme (4 chiffres minimum selon les règles strictes)
        Habilitation uneHabilitation = new Habilitation("ma01", 1, "master", true, false, true, false);
        unUtilisateur = new Utilisateur("U001", "Durand", "Louis", "lodurand", "M1ue@uiT455n", uneHabilitation);

        // 2. Premier changement : "Lae99_Mat00!" (Conforme : 4 chiffres 9900)
        // -> "M1ue@uiT455n" part dans les archives.
        // -> "Lae99_Mat00!" devient l'actuel.
        unUtilisateur.modifierMdp("Lae99_Mat00!");

        // 3. Deuxième changement : "NouveauPass1234!" (Conforme : 4 chiffres 1234)
        // -> "Lae99_Mat00!" part ENFIN dans les archives.
        // -> "NouveauPass1234!" devient l'actuel.
        unUtilisateur.modifierMdp("NouveauPass1234!");
    }

    @Test   // Test fourni dans le sujet initial
    void verifHabilitation() {
        assertTrue(unUtilisateur.getHabilitation().peutConsulter(), "Erreur sur le droit de lecture");
        assertFalse(unUtilisateur.getHabilitation().peutAjouter(), "Erreur sur le droit d'ajout");
        assertTrue(unUtilisateur.getHabilitation().peutModifier(), "Erreur sur le droit de modification");
        assertFalse(unUtilisateur.getHabilitation().peutSupprimer(), "Erreur sur le droit de suppression");
    }

    // --- CORRECTION Q A3.1 : Test Unitaire demandé ---
    @Test
    void verifModifierMdp() {
        // Cas 1 : Échec - Mot de passe trop court ou simple (pas assez de chiffres/maj/min)
        assertFalse(unUtilisateur.modifierMdp("toto"), "Doit refuser un MDP faible");

        // Cas 2 : Échec - Mot de passe déjà utilisé
        // Grâce au double changement dans le init(), "Lae99_Mat00!" est bien dans l'historique
        assertFalse(unUtilisateur.modifierMdp("Lae99_Mat00!"), "Doit refuser un ancien MDP");

        // Cas 3 : Succès - Mot de passe valide et jamais utilisé
        // 12 chars, 1 Maj (N), 3 min (ouv), 4 chiffres (2023), 1 spé (.)
        assertTrue(unUtilisateur.modifierMdp("Nouveau2023."), "Doit accepter un MDP conforme");
    }
}
