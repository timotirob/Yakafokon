<?php
session_start();
require 'db.php';

if (isset($_GET['action']) && $_GET['action'] == 'modifMdp') {

    // --- CORRECTION MISSION C1 (Vérification) ---
    // 1. On vérifie que le token existe en session
    // 2. On vérifie qu'il est envoyé en POST
    // 3. On vérifie qu'ils sont IDENTIQUES
    if (!empty($_SESSION['token']) AND !empty($_POST['token'])
        AND $_SESSION['token'] === $_POST['token']) { // "===" pour comparaison stricte

        // Le code de mise à jour SQL (déjà fourni dans le kit précédent)
        $newPwd = $_POST['pwd'];
        $idCli = $_SESSION['user_id'];

        $stmt = $pdo->prepare("UPDATE Client SET mdp = ? WHERE idCli = ?");
        $stmt->execute([$newPwd, $idCli]);

        echo "<h1>Succès !</h1><p>Mot de passe modifié en toute sécurité.</p>";

    } else {
        // Échec CSRF
        http_response_code(403); // Code HTTP Interdit
        echo "<h1 style='color:red'>ERREUR DE SÉCURITÉ (CSRF)</h1>";
        echo "<p>La requête ne provient pas du formulaire officiel.</p>";
    }
}
?>