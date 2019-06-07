<?php
include ('header.php');
//print_r($_GET['id']) ;
//$idqw = isset($_GET['id']);
//echo $idqw;
//$obj = new myFunctions();
//$delete = $obj->sendDeleteRequest($_GET['id']);
$result = $obj->sendGetRequest('//web-feed-provider/'.$_GET['id']);
//if(!empty($result)){
//    echo "<pre>";
//    print_r($result);
//    echo "</pre>";
//    die;
//}else{
//    echo"No Result Found! Please check the Id";
//}
?>
<?php if(!empty($result)){?>
<div class="container">
    <h2><?php echo $result->name;?></h2>
    <div class="row">
        <table>
            <tr>
                <td>Link : </td><td> <?php echo $result->link; ?></td>
            </tr>
            <tr>
                <td>last Updated Date : </td><td> <?php echo $result->updated_date; ?></td>
            </tr>
            <tr>
                <td>Number of Feeds : </td><td> <?php echo $result->num_feeds; ?></td>
            </tr>
            <tr>
                <td>Was there error while fetching data  : </td>
                <td> <?php if($result->error==0){
                        echo"Yes";
                    } else{
                        echo "No";
                    }?></td>
            </tr>
        </table>
    </div>
    <div class="row">


    </div>
</div>
<?php }else{
    echo"No Result Found! Please check the Id";
} ?>

<?php include('footer.php');?>
