<?php
include ('header.php');
$result = $obj->sendGetRequest('//feeds/'.$_GET['id']);
$provider = $obj->sendGetRequest('//web-feed-provider/'.$result->providerid);

?>

<?php if(!empty($result)){?>
    <div class="container">
        <div class="row">
            <h2><?php echo $result->title;?></h2>
        </div>
        <div class="row">

            <img src="<?php echo $result->image; ?>" alt="Image Here" height="250px" width="500px">
        </div>
        <div class="row">
            <table>
                <tr>
                    <td>Published Date :   </td>
                    <td width="100px;">  <?php echo $result->published_date; ?></td>
                    <td>Imported Date : </td>
                    <td  width="100px;"><?php echo $result->imported_date; ?></td>
                    <td>Web Feed Provider : </td>
                    <td  width="100px;"><a href="web_feed_providers_detail.php?id=<?php echo $result->providerid; ?>"><?php echo $provider->name; ?></a></td>
                    <td>Link to feed : </td>
                    <td><a href="<?php echo $result->link; ?>" target="_blank"><?php echo $result->link; ?></a></td>
                </tr>
            </table>
        </div>
        <div class="row">
            <p>
                <?php echo $result->description; ?>
            </p>
        </div>
    </div>
<?php }else{
    echo"No Result Found! Please check the Id";
} ?>

<?php include('footer.php');?>
