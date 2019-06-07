<?php
include ('header.php');
//print_r($_GET['id']) ;
//$idqw = isset($_GET['id']);
//echo $idqw;
$obj = new myFunctions();
$delete = $obj->sendDeleteRequest($_GET['id']);
if($delete){
    header("Location: web_feed_providers.php?delete=success");
}
include ('footer.php');
?>