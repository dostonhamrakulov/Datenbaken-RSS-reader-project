<?php
include ('header.php');
//print_r($_GET['id']) ;
//$idqw = isset($_GET['id']);
//echo $idqw;
$delete = $obj->sendPutRequest('/feeds/delete-feed?id='.$_GET['id'],'');

    header("Location: web_feed.php?delete=success");

    include ('footer.php');
?>