package test;
import fr.yak.holy.modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {
    Utilisateur unUtilisateur;

    @BeforeEach
    void init() {
        // Mot de passe initial conforme : 1 Maj, 5 min, 4 chiffres, 2 spé (M1ue@uiT455n -> length 12)
        Habilitation uneHabilitation = new Habilitation("ma01", 1, "master", true, false, true, false);
        unUtilisateur = new Utilisateur("U001", "Durand", "Louis", "lodurand", "M1ue@uiT455n", uneHabilitation);

        // On remplit un peu l'historique pour les tests
        unUtilisateur.modifierMdp("Lae99_Mat00!"); // Ancien 1

        // 3. On change encore une fois !
        // "NouveauPass123!" devient l'actuel, "Lae99_Mat00!" part dans les archives
        unUtilisateur.modifierMdp("NouveauPass1234!");
    }

    // --- CORRECTION Q A3.1 : Test Unitaire ---
    @Test
    void verifModifierMdp() {
        // Cas 1 : Échec - Mot de passe trop court ou simple
        assertFalse(unUtilisateur.modifierMdp("toto"), "Doit refuser un MDP faible");

        // Cas 2 : Échec - Mot de passe déjà utilisé (celui mis dans le @BeforeEach)
        assertFalse(unUtilisateur.modifierMdp("Lae99_Mat00!"), "Doit refuser un ancien MDP");

        // Cas 3 : Succès - Mot de passe valide et jamais utilisé
        // 12 chars, 1 Maj (N), 3 min (ouv), 4 chiffres (2023), 1 spé (.)
        assertTrue(unUtilisateur.modifierMdp("Nouveau2023."), "Doit accepter un MDP conforme");
    }
}
