<?php include ('header.php');

if(isset($_POST['feed_age'])){
    $myObj = new \stdClass();
    $myObj->id = $_SESSION['user_id'];
    $myObj->feedage = $_POST['feed_age_num'];
    $data = json_encode($myObj);
    $response = $obj->sendPutRequest('/user/update-feed-age', $data);
    if(isset($response->id)){
        echo '<script type="text/javascript">',
        'alert("Feed Age Updated Sucessfully");',
        '</script>'
        ;
    }else{
        echo '<script type="text/javascript">',
        'alert("Something went wrong please try again!!");',
        '</script>'
        ;
    }

}elseif (isset($_POST['update_period'])){
    $myObj = new \stdClass();
    $myObj->id = $_SESSION['user_id'];
    $myObj->updateperiod = $_POST['update_period_num'];
    $data = json_encode($myObj);
    $response = $obj->sendPutRequest('/user/update-updateperiod', $data);
    if(isset($response->id)){
        echo '<script type="text/javascript">',
        'alert("Update Period Updated Sucessfully");',
        '</script>'
        ;
    }else{
        echo '<script type="text/javascript">',
        'alert("Something went wrong please try again!!");',
        '</script>'
        ;
    }
}

if(isset($_SESSION['user_name'])) {
    $results = $obj->sendGetRequest("/user/?id=" . $_SESSION['user_id']);
}
?>
<div class="container">
<!--<h2>Welcome To RSS Fetcher </h2>-->

    <div class="row">
        <?php
        if(isset($_SESSION['user_name'])){
        ?>
            <div class="col-lg-4">
                <h3>User Details</h3>
                <table>
                    <img src="images/userIcon.png" style="height: 150px; width: 150px;">
                    <tr>
                        <td>Name: </td> <td><?php echo $_SESSION['user_name'] ?></td>
                    </tr>
                    <tr><td>Email: </td> <td><?php echo $_SESSION['user_email'] ?></td></tr>
                </table>
            </div>
        <div class="col-lg-4">
            <br><br>
            <form method="post" action="">
                <div class="form-group">
                    <label for="name">Feed Age</label><span style="color: red">*</span>
                    <input type="number" class="form-control" name="feed_age_num" id="name" value="<?php echo $results->feedage; ?>" placeholder="Enter Name" required>
                </div>
                <button id="add" type="submit" value="Update" name="feed_age" class="btn btn-primary">Update</button>
            </form>
        </div>
            <br><br>
            <div class="col-lg-4">
                <form method="post" action="">
                    <div class="form-group">
                        <label for="name">Feed Update Period</label><span style="color: red">*</span>
                        <input type="number" class="form-control" name="update_period_num" id="name" value="<?php echo $results->updateperiod; ?>" placeholder="Enter Name" required>
                    </div>
                    <button id="add" type="submit" value="Update" name="update_period" class="btn btn-primary">Update</button>
                </form>
            </div>
            <?php
        }
        ?>
    </div>
</div>

<?php include ('footer.php');?>
