<?php include ('header.php');
if(isset($_POST['submit'])){

    $myObj = new \stdClass();
    $myObj->email = $_POST['email'];
    $myObj->password = $_POST['password'];
    $data = json_encode($myObj);
    $url ="/user/get-user";
    $result = $obj->sendPostRequest($url,$data);
    $result = json_decode($result);
    if(!empty($result)){
        $_SESSION['user_email'] = $result->email;
        $_SESSION['user_id'] = $result->id;
        $_SESSION['user_name'] = $result->name;
        $_SESSION['user_feedage'] = $result->feedage;
        $_SESSION['user_updateperiod'] = $result->updateperiod;
        header("Location: index.php");

    }else{
        echo "Password or Username Mismatch";
    }

}

?>
<div class="login">

    <div class="login-form">
        <form action="" method="post">
            <div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
            <h4 class="modal-title">Login to Your Account</h4>
            <div class="form-group">
                <input type="text" class="form-control" name="email" placeholder="Email" required="required">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="Password" required="required">
            </div>
<!--            <div class="form-group small clearfix">-->
<!--                <label class="checkbox-inline"><input type="checkbox"> Remember me</label>-->
<!--                <a href="#" class="forgot-link">Forgot Password?</a>-->
<!--            </div>-->
            <input type="submit" name="submit" class="btn btn-primary btn-block btn-lg" value="Login">
        </form>
        <div class="text-center small">Don't have an account? <a href="signup.php">Sign up</a></div>
    </div>
</div>
<?php include ('footer.php');?>
