<?php
//zrusime session
$_SESSION = array();
session_destroy();
header("LOCATION: index.php");
?>
