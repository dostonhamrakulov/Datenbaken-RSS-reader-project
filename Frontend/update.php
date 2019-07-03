<?php
include ('header.php');
//print_r($_GET['id']) ;
//$idqw = isset($_GET['id']);
//echo $idqw;

$update = $obj->stringSendGetRequest('/user/update-button?id='.$_SESSION['user_id'],'');

if($update=="True"){
    header("Location: web_feed_providers.php?update=success");

}else{
    header("Location: web_feed_providers.php?update=unsuccess");

}

include ('footer.php');
?>