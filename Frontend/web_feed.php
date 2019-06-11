<?php include ('header.php');?>
<div class="container">
    <div class="web_feed">
        <h2>
            All Web Feeds
        </h2>
        <div class="row">

            <?php
                $results = $obj->sendGetRequest("/feeds/all");
                echo "<table class='table table-striped'>";
                ?>
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Link</th>
                                <th>Description</th>
                                <th>Published Date</th>
                                <th>Imported Date</th>
                                <th>Provider id</th>
                                <th>Image</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                                foreach ($results as $result){ ?>

                            <tr>
                                <td><?php echo $result->title ;?></td>
                                <td><a href="<?php echo $result->link; ?>">Read Full</a> </td>
                                <td><?php echo $result->description; ?></td>
                                <td><?php echo $result->published_date; ?></td>
                                <td><?php echo $result->imported_date; ?></td>
                                <td><?php echo $result->providerid; ?></td>
                                <td><img src="<?php echo $result->image; ?>" alt="Image Here" height="50px" width="50px"> </td>
                                <td>
                                    <a href="edit/url"><span class="glyphicon glyphicon-edit"></a>
                                    <a href="delete_web_feed.php?id=<?php echo $result->id; ?>"></span> <span class="glyphicon glyphicon-remove-sign"></span></a>
                                    <a href="web_feed_detail.php?id=<?php echo $result->id; ?>"></span> <span class="glyphicon glyphicon-eye-open"></span></a>
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
<?php include ('footer.php');?>
