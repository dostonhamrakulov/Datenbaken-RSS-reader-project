<?php include ('header.php');?>

<div class="signup">
    <div class="login-form">
        <form action="/examples/actions/confirmation.php" method="post">
            <div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
            <h4 class="modal-title">Create a New Account</h4>
            <div class="form-group">
                <input type="email" class="form-control" placeholder="Email" required="required">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Full Name" required="required">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" required="required">
            </div>
            <!--            <div class="form-group small clearfix">-->
            <!--                <label class="checkbox-inline"><input type="checkbox"> Remember me</label>-->
            <!--                <a href="#" class="forgot-link">Forgot Password?</a>-->
            <!--            </div>-->
            <input type="submit" class="btn btn-primary btn-block btn-lg" value="Login">
        </form>
        <div class="text-center small">Already have an account? <a href="login.php">Log In</a></div>
    </div>
</div>
<?php include ('footer.php');?>
