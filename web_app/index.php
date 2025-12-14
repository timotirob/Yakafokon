<?php
session_start();
require 'db.php';

$error = "";

// Traitement du formulaire de connexion
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $pseudo = $_POST['pseudo'];
    $mdp = $_POST['mdp'];

    // Requête simplifiée pour le TP (Vérifie juste l'existence)
    // Idéalement, il faudrait password_verify, mais restons simples pour commencer
    $stmt = $pdo->prepare("SELECT * FROM Client WHERE pseudo = ? AND mdp = ?");
    $stmt->execute([$pseudo, $mdp]);
    $user = $stmt->fetch();

    if ($user) {
        $_SESSION['user_id'] = $user['idCli'];
        $_SESSION['pseudo'] = $user['pseudo'];
        // Redirection vers la modification de MDP
        header("Location: modifMdp.php");
        exit;
    } else {
        $error = "Identifiants incorrects ! (Essayez un client de la BDD)";
    }
}
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion EchapBox</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>🔐 EchapBox</h1>
        <p>Connexion Espace Client</p>
        <?php if($error) echo "<p class='error'>$error</p>"; ?>

        <form method="POST">
            <input type="text" name="pseudo" placeholder="Votre Pseudo" required value="RichardP">
            <input type="password" name="mdp" placeholder="Votre Mot de Passe" required value="password123">
            <button type="submit">Se connecter</button>
        </form>
        <br>
        <small>Astuce TP : Créez un client dans la BDD si besoin !</small>
    </div>
</body>
</html>