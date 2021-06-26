<?php

$csrf = new csrf();

// vygenerovanie token_id a token_value
$token_id = $csrf->get_token_id();
$token_value = $csrf->get_token($token_id);

// vygenerovanie nahodnych nazvov pre polia formularu
$form_names = $csrf->form_names(array('user', 'password'), false);


if(isset($_POST[$form_names['user']], $_POST[$form_names['password']])) {
	// kontrola ci su token_id a token_value platne
	if($csrf->check_valid('post')) {
		// ziskanie premennych formularu
		$user = $_POST[$form_names['user']];
		$password = $_POST[$form_names['password']];

		if(@$_POST['logIN']){
    			if(verify_login($user, $password)) {
        			header('LOCATION: index.php');
    			}else{
        			$error = "Wrong name or password!! Pls try it again!!";
    			}
		}

	}
	// vygenerovanie novych nahodnych hodnot pre polia formularu
	$form_names = $csrf->form_names(array('user', 'password'), true);
}

function verify_login($userName, $password){
    global $db;
    if($sql = $db->prepare("SELECT id,name,password FROM admins WHERE name=? AND password=? LIMIT 1")){

       $hashPassword =  hash("sha512",$password);

        $sql->bind_param("ss", $userName, $hashPassword);
	$sql->execute();
	$sql->bind_result($userId, $name, $pass);
	$sql->fetch();
	$sql->close();
    }

    $db->close();

    if(!empty($userId)){
        $_SESSION['id']  = $userId;
        $_SESSION['name'] = $name;
        $_SESSION['session_id'] = session_id();
        return true;
    } else{
        return false;
    }
}
?>
<?if(!isLogin()){?>
<div style="width:20%;">
    <?=@$error?>
    <form method="post" name="login">
        <input type="hidden" name="<?=$token_id; ?>" value="<?=$token_value; ?>" />
	<label>Meno</label>
        <input name="<?=$form_names['user'];?>" value="" type="text" placeholder="LamaCoder" autofocus />
        <label>Heslo</label>
        <input name="<?=$form_names['password'];?>" value="" type="password" placeholder="********" />
        <br />
        <button class="button" name="logIN" value="1">Prihlasiť</button>
    </form>
</div>
<?}else{?>
    <div style="width:20%;">
        <?=@$error?>
        <a href="./?page=logout.php"><button class="button">Odhlásiť sa</button></a>
    </div>
<?}?>
