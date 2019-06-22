<?php
include ('header.php');
if(!isset($_SESSION['user_id'] )){
    echo "Please Login to continue";die;
}

if(isset($_POST['submit'])){

    $myObj = new \stdClass();
    $myObj->id = $_POST['id'];
    $myObj->name = $_POST['name'];
    $myObj->link = $_POST['rss_link'];
    $data = json_encode($myObj);
    $response = $obj->sendPutRequest('/web-feed-provider/update-only-provider', $data);
    echo '<script type="text/javascript">',
    'alert("Web feed Provider Updated Sucessfully");',
    '</script>'
    ;
}
$result = $obj->sendGetRequest('//web-feed-provider/'.$_GET['id']);

if($_SESSION['user_id']!=$result->userid){
    echo "Access Denied!!";die;
}
?>
<?php if(!empty($result)){?>
    <div class="container">
        <h2><?php echo $result->name;?></h2>
        <div class="row">
            <div class="col-lg-6">
            <form method="post" action="">
                <div class="form-group">
                    <label for="name">Name</label><span style="color: red">*</span>
                    <input type="text" class="form-control" name="name" id="name" value="<?php echo $result->name ?>" placeholder="Enter Name" required>
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
