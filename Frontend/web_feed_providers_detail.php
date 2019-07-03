<?php
include ('header.php');
$result = $obj->sendGetRequest('//web-feed-provider/'.$_GET['id']);

?>
<?php if(!empty($result)){
   ?>
<div class="container">

    <div class="row">
    </div>
    <div class="row">

        <div class="col-md-8">
            <div class="box" style="text-align: left">
                <!--                    <div class="box-icon">-->
                <!--                        <span class="fa fa-4x fa-html5"></span>-->
                <!--                    </div>-->
                <div class="info">

                </div>
                <div class="info">
                    <h2><?php echo $result->name;?></h2>
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
                    <a href="<?php echo $result->link; ?>" class="btn" target="_blank">Click!</a>

                </div>
            </div>
        </div>
        <!--            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">-->
        <!--                -->
        <!--            </div>-->
        <div class="col-md-4 scrollable" >
            <div class="box">
                <!--                    <div class="box-icon">-->
                <!--                        <span class="fa fa-4x fa-html5"></span>-->
                <!--                    </div>-->
                <div class="info">

                </div>
                <div class="info">
                    <h4 class="text-center">Latest Web feeds</h4>
                    <?php
                    $results = $obj->sendGetRequest("/web-feed-provider/feed-providers-of-user/".$_SESSION['user_id'] );
                    $results = array_reverse($results);
                    $results =  array_slice($results, 0, 11);;

                    $provider_id=[$_GET['id']];
                    $provider_id_name =[];
//                    foreach ($providers as $provider){
//                        $provider_id[] = $provider->id;
//                        $provider_id_name[$provider->name] = $provider->id;
//                    }
                    foreach ($results as $result){
                                ?>
                                <p class="latest-feed">
                                    <a href="web_feed_providers_detail.php?id=<?php echo $result->id; ?>"><?php echo  $obj->custom_echo($result->name , 50);?></a>
                                </p>
                                <?php
                        }

                    ?>

                </div>
            </div>
        </div>

    </div>
</div>
<?php }else{
    echo"No Result Found! Please check the Id";
} ?>

<?php include('footer.php');?>
