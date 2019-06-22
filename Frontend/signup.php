<?php include ('header.php');

if(isset($_POST['submit'])){



    $myObj = new \stdClass();
    $myObj->name = $_POST['name'];
    $myObj->email = $_POST['email'];
    $myObj->password = $_POST['password'];
    $data = json_encode($myObj);
    $url ="/user/add-user";
    $result = $obj->sendPostRequest($url,$data);
    if($result=='Created'){
        echo "User Creation success! Please Log in to proceed";
    }else{
        echo"Sorry! Something error Occured Please try again!!";
    }
}

?>

<div class="signup">
    <div class="login-form">
        <form action="" method="post">
            <div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
            <h4 class="modal-title">Create a New Account</h4>
            <div class="form-group">
                <input type="email" name="email" class="form-control" placeholder="Email" required="required">
            </div>
            <div class="form-group">
                <input type="text" name="name" class="form-control" placeholder="Full Name" required="required">
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="Password" required="required">
            </div>
            <!--            <div class="form-group small clearfix">-->
            <!--                <label class="checkbox-inline"><input type="checkbox"> Remember me</label>-->
            <!--                <a href="#" class="forgot-link">Forgot Password?</a>-->
            <!--            </div>-->
            <input type="submit" class="btn btn-primary btn-block btn-lg" name="submit" value="Login">
        </form>
        <div class="text-center small">Already have an account? <a href="login.php">Sign Up</a></div>
    </div>
</div>
<?php include ('footer.php');?>
