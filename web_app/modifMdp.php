<?php
session_start();
// Si pas connecté, on dégage
if (!isset($_SESSION['user_id'])) {
    header("Location: index.php");
    exit;
}

// ⚠️ ZONE A MODIFIER PAR L'ETUDIANT (Mission C1) ⚠️
// Il manque la génération du Token ici
// $token = ...
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modification Mot de Passe</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
   <div class="container">
       <h1>Changer mon mot de passe</h1>
       <p>Bonjour <strong><?= htmlspecialchars($_SESSION['pseudo']) ?></strong></p>

       <form method="POST" action="traitement.php?action=modifMdp">
          <div class="input-div">
            <input type="password" name="pwd" placeholder="Nouveau mot de passe" required>
          </div>
          <div class="input-div">
            <input type="password" name="confirmPwd" placeholder="Confirmer le mot de passe" required>
          </div>

          <button type="submit">Valider la modification</button>
       </form>
       <br>
       <a href="logout.php">Se déconnecter</a>
   </div>
</body>
</html>