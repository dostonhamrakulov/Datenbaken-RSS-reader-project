<?php
include ('header.php');
$result = $obj->sendGetRequest('//feeds/'.$_GET['id']);
$provider = $obj->sendGetRequest('//web-feed-provider/'.$result->providerid);
$providers = $obj->sendGetRequest("/web-feed-provider/feed-providers-of-user/".$_SESSION['user_id'] );

?>

<?php if(!empty($result)){
//    echo "<pre>";
//    print_r($result);
//    echo "</pre>";
//    die;
    ?>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="box">
                    <!--                    <div class="box-icon">-->
                    <!--                        <span class="fa fa-4x fa-html5"></span>-->
                    <!--                    </div>-->
                    <div class="info">

                    </div>
                    <div class="info">
                        <h4 class="text-center"><?php echo $result->title;?></h4>
                        <p><?php echo $result->description; ?></p>
                        <p>Imported Date : <?php echo $result->importeddate; ?> </p>
                        <p>Published Date : <?php echo $result->publisheddate; ?> </p>
                        <p>Web Feed Provider :  <a href="web_feed_providers_detail.php?id=<?php echo $result->providerid; ?>"><?php echo $provider->name; ?></a> </p>
                        <a href="<?php echo $result->link; ?>" class="btn" target="_blank">Link</a>

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
                        $results = $obj->sendGetRequest("/feeds/all");
                        $results =  array_slice($results, 0, 11);;

                        $provider_id=[];
                        $provider_id_name =[];
                        foreach ($providers as $provider){
                            $provider_id[] = $provider->id;
                            $provider_id_name[$provider->name] = $provider->id;
                        }
                        foreach ($results as $result){
                        if (in_array($result->providerid, $provider_id)){
                        if($result->deleted=='False'){
                            ?>
                            <p class="latest-feed">
                                <a href="web_feed_detail.php?id=<?php echo $result->id; ?>"><?php echo  $obj->custom_echo($result->title, 50);?></a>
                            </p>
                        <?php
                        }
                        }
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
