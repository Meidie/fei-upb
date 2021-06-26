<?php
session_start();
ob_start();
include_once('include/db.php');
include_once('include/csrf.class.php');

function isLogin() {
    if(@$_SESSION['id'] > 0){
        return true;
    }else{
        return false;
    }
}
?>
<!DOCTYPE HTML>
<html>
	<head>
		<title>VulnerableBlog9000</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
			<link rel="stylesheet" href="css/style-wide.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<!--
		Note: Set the body element's class to "left-sidebar" to position the sidebar on the left.
		Set it to "right-sidebar" to, you guessed it, position it on the right.
	-->
	<body class="left-sidebar">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Content -->
					<div id="content">
						<div class="inner">
                            <?php
                           	 @$pages=$_GET["page"];
                        	 if(!isLogin()) {
					 $pages='login.php';
			 	}

				$allowedPages = array('search.php','logout.php', 'login.php', 'kontakt.php', 'home.php');

				 if (!isset($pages) || empty($pages)){
                                	require("content/home.php");
                            	} elseif (in_array($pages, $allowedPages) && file_exists("content/$pages")) {
                                	require("content/$pages");
                           	} else{
					require ("content/error_page.php");
				}
                            ?>
						</div>
					</div>

				<!-- Sidebar -->
					<div id="sidebar">

						<!-- Logo -->
							<h1 id="logo"><a href="#">LameBlog</a></h1>

						<!-- Nav -->
							<nav id="nav">
								<ul>
									<li class="current"><a href="./?page=">Články</a></li>
                                    <li><a href="./?page=kontakt.php">Kontakt</a></li>
                                    <?php
                                    if(isLogin()){
                                        echo '<li><a href="./?page=logout.php">Odhlásiť sa</a></li>';
                                    }else{
                                        echo '<li><a href="./?page=login.php">Login</a></li>';
                                    }
                                    ?>
								</ul>
							</nav>

						<!-- Search -->
							<section class="box search">
								<form method="post" action="index.php?page=search.php">
									<input type="text" class="text" name="search" placeholder="Search" />
								</form>
							</section>

						<!-- Copyright -->
							<ul id="copyright">
							</ul>
					</div>
			</div>
	</body>
</html>
