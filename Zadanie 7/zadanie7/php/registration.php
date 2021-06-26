<!DOCTYPE html>
<html lang="sk">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--jQuery Datatables CSS-->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" href="../css/registration.css">

    <title>Registrácia</title>
</head>
<body>
<div class="login-form">
    <form enctype="multipart/form-data" action="confirmation.php" method="post">
        <div class="avatar">
            <i class="fa fa-user-plus fa-4x"></i>
        </div>

        <h2 class="text-center">Create Account</h2>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
            </div>
            <input type="text" class="form-control" name="firstname" placeholder="First Name" required="required">
        </div>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-user-o"></i> </span>
            </div>
            <input type="text" class="form-control" name="lastname" placeholder="Last Name" required="required">
        </div>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-user-circle-o"></i> </span>
            </div>
            <input type="text" id="usname" class="form-control" name="username" placeholder="Username" required="required">
            <div class='invalid-feedback'>
                Username already exists
            </div>
            <?php
            //validacia ci username uz existuje v databaze
            if(isset($_GET["valid"])) {
                echo "
                    <script>
                        var validation = document.getElementById('usname');
                        validation.classList.add('is-invalid');
                    </script>
                 ";
            }
            ?>
        </div>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
            </div>
            <input name="email" class="form-control" placeholder="Email address" type="email">
        </div>
        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
            </div>
            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
        </div>
        <div class="form-group">
            <button type="submit"  name="submitRegistration" class="btn btn-primary btn-lg btn-block">Register and Sign in</button>
        </div>
        <p class="text-center small"> Have an account? <a id="register" href="../index.php"> Sign In!</a></p>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>