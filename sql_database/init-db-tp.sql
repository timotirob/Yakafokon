-- Initialisation de la base (Squelette fourni)
CREATE DATABASE IF NOT EXISTS Yak_Voyages;
USE Yak_Voyages;

-- Table Devis (nécessaire pour le trigger)
DROP TABLE IF EXISTS Devis_Voyage;
CREATE TABLE Devis_Voyage (
                              idDevis INT PRIMARY KEY,
                              intituleVoyage VARCHAR(100),
                              nbJours INT,
                              nbParticipants INT -- Implicite pour le calcul
);

-- Table Contrat (Cible du trigger)
DROP TABLE IF EXISTS Contrat_Voyage;
CREATE TABLE Contrat_Voyage (
                                idContrat INT PRIMARY KEY AUTO_INCREMENT,
                                montantAPayer DECIMAL(10,2),
                                acompte DECIMAL(10,2),
                                nbParticipants INT,
                                idDevis INT,
                                FOREIGN KEY (idDevis) REFERENCES Devis_Voyage(idDevis)
);

-- Safari Kenya (ID 1) : 10 jours, 2 pers.
-- Seuil min : 10 * 2 * 75 = 1500€. On met 2000€.

DROP TABLE IF EXISTS Client;
CREATE TABLE Client (
                        idCli INT AUTO_INCREMENT PRIMARY KEY,
                        civilite VARCHAR(10),
                        nom VARCHAR(50),
                        prenom VARCHAR(50),
                        pseudo VARCHAR(50) UNIQUE,  -- Important pour le Login
                        mdp VARCHAR(255),           -- Important pour le Login
                        adresse VARCHAR(100),
                        codePostal VARCHAR(10),
                        ville VARCHAR(50),
                        pays VARCHAR(50),
                        email VARCHAR(100),
                        tel VARCHAR(20)
    -- Note : accordPubli n'est pas là, c'est l'objet de la question B1.2
);

INSERT INTO Client (nom, prenom, pseudo, mdp, email, ville) VALUES
                                                                ('Perrin', 'Richard', 'RichardP', 'password123', 'r.perrin@gmail.com', 'Rennes'),
                                                                ('Lenvy', 'Jeanne', 'MmeLenvy', 'admin1234', 'j.lenvy@yak.com', 'Brest'),
                                                                ('G', 'Allan', 'AllanG', 'azerty', 'allan.g@hotmail.fr', 'Nantes');

-- Données pour tester
INSERT INTO Devis_Voyage (idDevis, intituleVoyage, nbJours, nbParticipants) VALUES
                                                                                (1, 'Safari Kenya', 10, 2),
                                                                                (2, 'Weekend BZH', 2, 2),
                                                                                (3, 'Expédition Islande', 12, 4), -- Valide (Long et plusieurs pers)
                                                                                (4, 'Escapade Rome', 4, 1),       -- Valide (Court mais > 2 jours)
                                                                                (5, 'Survie Alpes', 1, 2),        -- Invalide (Trop court : < 3 jours)
                                                                                (6, 'Croisière Luxe', 7, 2);      -- Valide

-- ============================================================
-- 2. INSERTION DES DONNÉES DE TEST (Pour que le login marche)
-- ============================================================


INSERT INTO Contrat_Voyage (montantAPayer, acompte, nbParticipants, idDevis)
VALUES (2000.00, 500.00, 2, 1);

-- Expédition Islande (ID 3) : 12 jours, 4 pers.
-- Seuil min : 12 * 4 * 75 = 3600€. On met 4000€.
INSERT INTO Contrat_Voyage (montantAPayer, acompte, nbParticipants, idDevis)
VALUES (4000.00, 1000.00, 4, 3);

-- Weekend BZH (ID 2) ou Survie Alpes (ID 5) font moins de 3 jours.
INSERT INTO Contrat_Voyage (montantAPayer, acompte, nbParticipants, idDevis)
VALUES (500.00, 100.00, 2, 2);

-- Croisière Luxe (ID 6) : 7 jours, 2 pers.
-- Seuil attendu : 7 * 2 * 75 = 1050€. On tente de tricher avec 800€.
INSERT INTO Contrat_Voyage (montantAPayer, acompte, nbParticipants, idDevis)
VALUES (800.00, 200.00, 2, 6);

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