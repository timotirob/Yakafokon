<?php
session_start();
require 'db.php';

if (isset($_GET['action']) && $_GET['action'] == 'modifMdp') {

    // ⚠️ MISSION C1 : Vérification CSRF manquante ici
    // if ( ... token check ... ) {

        $newPwd = $_POST['pwd'];
        $idCli = $_SESSION['user_id'];

        // Mise à jour SQL réelle
        $stmt = $pdo->prepare("UPDATE Client SET mdp = ? WHERE idCli = ?");
        $stmt->execute([$newPwd, $idCli]);

        echo "<link rel='stylesheet' href='style.css'>";
        echo "<div class='container'>";
        echo "<h1 class='success'>Succès !</h1>";
        echo "<p>Votre mot de passe a été mis à jour dans la base.</p>";
        echo "<a href='index.php'>Retour à l'accueil</a>";
        echo "</div>";

    // } else {
    //    die("Erreur CSRF !");
    // }
}
?>