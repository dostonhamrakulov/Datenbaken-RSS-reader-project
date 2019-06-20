<?php
include ('header.php');
if(isset($_POST['submit'])){
    echo "<pre>";
    print_r($_POST);
    echo "</pre>";
    die;
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
