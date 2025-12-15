<?php
// Nom du fichier de log à générer
$fichierLog = 'auth.log';

// Les entrées de log exactes du sujet (Question C2.1)
// On simule une attaque par force brute sur RichardP
$lignesLog = [
    "[21:41:18] NOTICE: utilisateur AllanG : erreur de connexion",
    "[21:41:20] WARNING: utilisateur : essai de connexion avec champs vides",
    "[21:41:24] NOTICE: utilisateur RichardP : erreur de connexion",
    "[21:41:24] NOTICE: utilisateur RichardP : erreur de connexion",
    "[21:41:24] NOTICE: utilisateur RichardP : erreur de connexion",
    "[21:41:26] NOTICE: utilisateur AllanG : s’est connecté correctement",
    "[21:41:26] NOTICE: utilisateur RichardP : erreur de connexion",
    "[21:41:26] NOTICE: utilisateur RichardP : erreur de connexion", // Ajouté pour insister
    "[21:41:28] NOTICE: utilisateur RichardP : s’est connecté correctement" // Le hacker a réussi (optionnel)
];

// 1. On efface le fichier s'il existe déjà pour repartir à zéro
if (file_exists($fichierLog)) {
    unlink($fichierLog);
}

// 2. Écriture des lignes
foreach ($lignesLog as $ligne) {
    // PHP_EOL permet de gérer le saut de ligne selon l'OS (Windows/Linux)
    file_put_contents($fichierLog, $ligne . PHP_EOL, FILE_APPEND);
}

echo "<h1>✅ Fichier généré !</h1>";
echo "<p>Le fichier <strong>$fichierLog</strong> a été créé dans le dossier courant.</p>";
echo "<pre>" . file_get_contents($fichierLog) . "</pre>";
?>
