<?php
include ('header.php');
$result = $obj->sendGetRequest('//web-feed-provider/'.$_GET['id']);

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
                <td>last Updated Date : </td><td> <?php echo $result->updateddate; ?></td>
            </tr>
            <tr>
                <td>Number of Feeds : </td><td> <?php echo $result->numfeeds; ?></td>
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
