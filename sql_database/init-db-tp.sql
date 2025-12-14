-- Initialisation de la base (Squelette fourni)
CREATE DATABASE IF NOT EXISTS Yak_Voyages;
USE Yak_Voyages;

-- Table Devis (nécessaire pour le trigger)
CREATE TABLE Devis_Voyage (
                              idDevis INT PRIMARY KEY,
                              intituleVoyage VARCHAR(100),
                              nbJours INT,
                              nbParticipants INT -- Implicite pour le calcul
);

-- Table Contrat (Cible du trigger)
CREATE TABLE Contrat_Voyage (
                                idContrat INT PRIMARY KEY AUTO_INCREMENT,
                                montantAPayer DECIMAL(10,2),
                                acompte DECIMAL(10,2),
                                nbParticipants INT,
                                idDevis INT,
                                FOREIGN KEY (idDevis) REFERENCES Devis_Voyage(idDevis)
);

-- Données pour tester
INSERT INTO Devis_Voyage (idDevis, intituleVoyage, nbJours, nbParticipants) VALUES
(1, 'Safari Kenya', 10, 2),
(2, 'Weekend BZH', 2, 2);

-- ============================================================
-- TRIGGER A COMPLÉTER (Basé sur Document B6)
-- ============================================================
DELIMITER |
CREATE TRIGGER before_insert_contrat_voyage
    BEFORE INSERT ON Contrat_Voyage
    FOR EACH ROW
BEGIN
    -- Récupération de la durée du devis
    SET @nb_jours = (SELECT nbJours FROM Devis_Voyage WHERE idDevis = NEW.idDevis);

    -- Règle 1 : Durée minimale (Fournie dans le sujet)
    IF @nb_jours < 3 THEN
        SIGNAL SQLSTATE '10001'
        SET MESSAGE_TEXT = 'Le devis validé par le contrat est d''une durée inférieure à la durée minimale autorisée';
END IF;

-- --------- A COMPLÉTER (Question B2.3) ----------
-- Ajouter ici la vérification du montant minimal (75€/j/pers)

END |
DELIMITER ;