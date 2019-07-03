<?php
include ('header.php');
if(isset($_POST['submit'])){

    if(!isset($_SESSION['user_id'] )){
        echo "Please Login to continue";die;
    }

    if(isset($_POST['submit'])){

        $myObj = new \stdClass();
        $myObj->id = $_POST['id'];
        $myObj->title = $_POST['name'];
        $myObj->link = $_POST['rss_link'];
        $data = json_encode($myObj);
        $response = $obj->sendPutRequest('/feeds/update-feed-by-user', $data);
        header("Location: web_feed.php?edit=success");
    }
}
$result = $obj->sendGetRequest('//feeds/'.$_GET['id']);
//$result = $obj->sendGetRequest('//web-feed-provider/'.$_GET['id']);

?>
<?php if(!empty($result)){?>
    <div class="container">
        <h2><?php echo $result->title;?></h2>
        <div class="row">
            <div class="col-lg-6">
                <form method="post" action="">
                    <div class="form-group">
                        <label for="name">Name</label><span style="color: red">*</span>
                        <input type="text" class="form-control" name="name" id="name" value="<?php echo $result->title ?>" placeholder="Enter Name" required>
                    </div>
                    <input type="hidden" name="id" value="<?php echo$_GET['id']; ?>">
                    <div class="form-group">
                        <label for="rssLink">Link</label><span style="color: red">*</span>
                        <input type="text" class="form-control" id="rssLink" name="rss_link" value="<?php echo $result->link ?>" placeholder="Enter RSS Link" required>
                    </div>
                    <button id="add" type="submit" value="Update" name="submit" class="btn btn-primary">Update</button>
                </form>
            </div>
        </div>
        <div class="row">


        </div>
    </div>
<?php }else{
    echo"No Result Found! Please check the Id";
} ?>

<?php include('footer.php');?>
