<?php
session_start();
include_once(__DIR__.DIRECTORY_SEPARATOR.'../include/csrf.class.php');
	$submitted = False;
        $csrf = new csrf();

        // vygenerovanie token_id a token_value
        $token_id = $csrf->get_token_id();
        $token_value = $csrf->get_token($token_id);

        // vygenerovanie nahodnych nazvov pre polia formularu
        $form_names = $csrf->form_names(array('myName', 'email', 'message'), false);

        if(isset($_POST[$form_names['myName']], $_POST[$form_names['email']], $_POST[$form_names['message']])) {
                // kontrola ci su token_id a token_value platne
                if($csrf->check_valid('post')) {
                        // ziskanie premennych formularu
                        $name = $_POST[$form_names['myName']];
                        $email = $_POST[$form_names['email']];
                        $message = $_POST[$form_names['message']];
                        $submitted = True;
                }

                // vygenerovanie novych nahodnych hodnot pre polia formularu
                 $form_names = $csrf->form_names(array('myName', 'email', 'message'), true);
        }

if (!$submitted)    /* display the contact form */ {
    ?>
    <form  method="post" name="send">
   	 <input type="hidden" name="<?=$token_id; ?>" value="<?=$token_value; ?>">
    Your name:<br>
    	<input name="<?=$form_names['myName'];?>" type="text" value="" size="30"/><br>
    Your email:<br>
    	<input name="<?=$form_names['email'];?>" type="text" value="" size="30"/><br>
    Your message:<br>
    	<textarea name="<?=$form_names['message'];?>" rows="7" cols="30"></textarea><br>
    	<input type="submit" name="Send" value="Send email"/>
    </form>
    <?php

} else {
    if (($name=="")||($email=="")||($message=="")){
        echo "All fields are required, please fill <a href=\"\">the form</a> again.";
     }else{
         $from="From: $name<$email>\r\nReturn-path: $email";
         $subject="Message sent using your contact form";
         mail("youremail@yoursite.com", $subject, $message, $from);
         echo "Email sent!";
     }
}
?>
