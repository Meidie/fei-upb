<?php
	$allowedPages = array('search.php','logout.php', 'login.php', 'kontakt.php', 'home.php');

	$file = $_GET['file'];

	if (!isset($file) || empty($file)){
		require("index.php");
	} elseif(in_array($file, $allowedPages) && file_exists("content/$file")) {
		require("content/$file");
	} else{
		require("content/error_page.php");
	}
?>
