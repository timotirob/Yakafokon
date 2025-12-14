<?php
// Configuration simple pour Laragon (root/vide)
$host = 'localhost';
$dbname = 'Yak_Voyages';
$username = 'root';
$password = ''; // Sur Laragon par défaut c'est vide

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Erreur de connexion BDD : " . $e->getMessage());
}
?>