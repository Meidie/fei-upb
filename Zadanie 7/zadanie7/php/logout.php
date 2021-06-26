<?php

	session_start();
	
    if(isset($_SESSION['login_access'])){
        unset($_SESSION['login_access']);
    }
    else if(isset($_SESSION['register_access'])){
        unset($_SESSION['register_access']);
    }
    unset($_SESSION['name']);
    unset($_SESSION['surname']);
    unset($_SESSION['username']);

    session_destroy();
    header('Location: ../index.php');

    exit();

?>