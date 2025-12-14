<?php
session_start();
// Vérification session utilisateur (si pas connecté, on vire)
if (!isset($_SESSION['user_id'])) { header("Location: index.php"); exit; }

// --- CORRECTION MISSION C1 (Génération du Token) ---
if (empty($_SESSION['token'])) {
    // Génère une chaîne aléatoire hexadécimale de 32 octets (très robuste)
    $_SESSION['token'] = bin2hex(random_bytes(32));
}
$token = $_SESSION['token'];
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

       <form method="POST" action="traitement.php?action=modifMdp">
          <div class="input-div">
            <input type="password" name="pwd" placeholder="Nouveau mot de passe" required>
          </div>
          <div class="input-div">
            <input type="password" name="confirmPwd" placeholder="Confirmer le mot de passe" required>
          </div>

          <input type="hidden" name="token" value="<?php echo $token; ?>">

          <button type="submit">Valider</button>
       </form>
   </div>
</body>
</html>