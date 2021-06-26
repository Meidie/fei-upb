<?php
session_start();

//Klasicky login
if (isset($_POST['submitLogin'])){

	$userName = $_POST['username'];
	$password = $_POST['password'];

	$_SESSION['login_access'] =  "logged in";
	$_SESSION['username'] = $userName;

	header('Location: main.php');

}
//Registracia
else if (isset($_POST['submitRegistration'])){

	$firstName = $_POST['firstname'];
	$lastName = $_POST['lastname'];
	$userName = $_POST['username'];
	$email = $_POST['email'];
	$password = $_POST['password'];

	if(isset($_SESSION['access_token'])){
		unset($_SESSION['access_token']);
		$client->revokeToken();
	}
	else if(isset($_SESSION['login_access'])){
		unset($_SESSION['login_access']);
	}
	else if(isset($_SESSION['LDAP_access'])){
		unset($_SESSION['LDAP_access']);
	}
	else if(isset($_SESSION['register_access'])){
		unset($_SESSION['register_access']);
	}

	$_SESSION['register_access'] =  "logged in";
	$_SESSION['username'] = $userName;

	header('Location: main.php');
}
?>
