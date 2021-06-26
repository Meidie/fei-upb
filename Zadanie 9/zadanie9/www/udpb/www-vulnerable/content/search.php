<?php

if($sql = $db->prepare("SELECT * FROM articles WHERE title LIKE ? OR content LIKE ? ")){
	$search = "%{$_POST[search]}%";
	$sql->bind_param("ss",$search, $search);
	$sql->execute();
	$result = $sql->get_result();
	$article = $result->fetch_assoc();
}

?>

<h1> Výsledky vyhľadavania: <?=htmlspecialchars($_POST['search'])?></h1>

<div>
    <?php
    try {
      while($data = $result->fetch_assoc()){
	  echo 'Article: <a href=index.php?id='.htmlspecialchars($data["id"]).'>'.htmlspecialchars($data["title"]).'</a><br />';
    }
    } catch (Exception $e) {
      header("LOCATION: error_page.php");
     } 
    ?>
</div>
