<?php include ('header.php');

if(!isset($_SESSION['user_id'] )){
    echo "Please Login to continue";die;
}
if(isset($_GET['delete'])=='success'){
    echo '<script type="text/javascript">',
    'alert("Web Feed Provider Deleted Sucessfully");
    window.location = \'web_feed_providers.php\';
    ',
    '</script>'
    ;
}elseif(isset($_GET['edit'])=='success'){
    echo '<script type="text/javascript">',
    'alert("Web Feed Provider Edited Sucessfully");
    window.location = \'web_feed_providers.php\';
    ',
    '</script>'
    ;
}
if(isset($_POST['submit'])) {
    $feed_provider_name = $_POST['name'];
    $feed_provider_link = $_POST['rss_link'];
    $myObj = new \stdClass();
    $myObj->name = $feed_provider_name;
    $myObj->link = $feed_provider_link;
    $myObj->userid = $_SESSION['user_id'];
    $data = json_encode($myObj);
    $url ="//web-feed-provider/add" ;
    $result = $obj->sendPostRequest($url,$data);
    echo '<script type="text/javascript">',
    'alert("Web Feed Provider Added Sucessfully");',
    '</script>'
    ;
}
?>
<div class="container">
    <div class="web_feed_providers">

        <h2>
            All Web Feed Providers
        </h2>
        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#addRss" aria-expanded="false" aria-controls="collapseExample">Add Web Feed Provider</button>
        <button type="button" class="btn btn-success pull-right" >Update</button>
        <div class="row">
            <div id="addRss" class="collapse">
                <div class="col-lg-6">
                    <form method="post" action="">
                        <div class="form-group">
                            <label for="name">Name</label><span style="color: red">*</span>
                            <input type="text" class="form-control" name="name" id="name" placeholder="Enter Name" required>
                        </div>
                        <div class="form-group">
                            <label for="rssLink">Link</label><span style="color: red">*</span>
                            <input type="text" class="form-control" id="rssLink" name="rss_link" placeholder="Enter RSS Link" required>
                        </div>
                        <button id="add" type="submit" value="submit" name="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
        <br>
        <div class="row">

            <?php
            $results = $obj->sendGetRequest("/web-feed-provider/feed-providers-of-user/".$_SESSION['user_id'] );
            $results = array_reverse($results);
            echo "<table class='table table-striped' style='border: 1px solid'>";
            ?>
            <thead>
            <tr>
                <th>Name</th>
                <th>Link</th>
                <th>Updated Date</th>
                <th>Number Of Feeds</th>
                <th>Error</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <?php
            foreach ($results as $result){ ?>

                <tr>
                    <td><?php echo $result->name ;?></td>
                    <td><a href="<?php echo $result->link; ?>" target="_blank"><?php echo $result->link; ?></a> </td>
                    <td><?php echo $result->updateddate; ?></td>
                    <td><?php echo $obj->sendGetRequest("/feeds/num-of-feeds-of-provider?providerid=".$result->id ) ?></td>
                    <td><?php
                        $myObj = new \stdClass();
                        $myObj->providerid =  $result->id;
                        $data = json_encode($myObj);
                        echo $obj->sendPostRequest('/feeds/number-of-errors',$data); ?></td>
                    <td>
                        <a href="edit_web_feed_providers.php?id=<?php echo $result->id; ?>"><span class="glyphicon glyphicon-edit"></a>
                        <a href="delete_web_feed_providers.php?id=<?php echo $result->id; ?>"></span> <span class="glyphicon glyphicon-remove-sign"></span></a>
                        <a href="web_feed_providers_detail.php?id=<?php echo $result->id; ?>"></span> <span class="glyphicon glyphicon-eye-open"></span></a>
                    </td>
                </tr>
                <?php
            }
            echo "</tbody>";
            echo "</table>";
            ?>
        </div>
    </div>
</div>

<!--<script>-->
<!--    $("#add").click(function(){-->
<!--        var name = $('#name').val();-->
<!--        var rssLink = $('#rssLink').val();-->
<!--        if(name=='' || rssLink==''){-->
<!--            alert('Please fill all the fields');-->
<!--        }else{-->
<!--            $.ajax({-->
<!--                type: "POST",-->
<!--                url: 'http://localhost:8000//web-feed-provider/add',-->
<!--                contentType:"application/json;",crossDomain: true,-->
<!--                "headers": {-->
<!--                    "accept": "application/json",-->
<!--                    "Access-Control-Allow-Origin":"*"-->
<!--                }-->
<!--                data: {-->
<!--                    "name" : name,-->
<!--                    "link" : rssLink-->
<!--                },-->
<!--                success: function(){-->
<!--                    $('#success-add').show();-->
<!--                }-->
<!--            })-->
<!---->
<!--        }-->
<!--    });-->
<!--</script>-->
<?php include ('footer.php');?>
