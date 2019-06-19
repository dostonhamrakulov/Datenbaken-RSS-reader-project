<?php include ('header.php');
if(!isset($_SESSION['user_id'] )){
    echo "Please Login to continue";die;
}
?>
<div class="container">
    <div class="web_feed">
        <h2>
            All Web Feeds
        </h2>
        <div class="row">
            <button type="submit" id="export_rss" value="submit" name="submit" class="btn btn-primary pull-right">Export</button>
            <br>
            <br>
            <?php
                $results = $obj->sendGetRequest("/feeds/all");
                $providers = $obj->sendGetRequest("/web-feed-provider/feed-providers-of-user/".$_SESSION['user_id'] );
                $provider_id=[];
                $provider_id_name =[];
                foreach ($providers as $provider){
                    $provider_id[] = $provider->id;
                    $provider_id_name[$provider->name] = $provider->id;
                }
                echo "<table class='table table-striped' id=\"example\" class=\"display\" cellspacing=\"0\" width=\"100%\">";
                ?>
                        <thead>
                            <tr>
                                <th> </th>
                                <th>Title</th>
                                <th>Link</th>
                                <th>Description</th>
                                <th>Published Date</th>
                                <th>Imported Date</th>
                                <th width="150px;">Provider Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
<!--                        <form action="export_web_feed.php" method="post">-->

                            <?php
                            $results = array_reverse($results);
                                foreach ($results as $result){
                                    if (in_array($result->providerid, $provider_id)){
                                    ?>
                            <tr>
                                <td><input type="checkbox" name="web_id[]" value="<?php echo $result->id;?>"></td>
                                <td><?php echo $result->title ;?></td>
                                <td><a href="<?php echo $result->link; ?>" target="_blank">Read Full</a> </td>
                                <td><?php echo $result->description; ?></td>
                                <td><?php echo $result->publisheddate; ?></td>
                                <td><?php echo $result->importeddate; ?></td>
                                <td width="150px;"><?php echo array_search($result->providerid,$provider_id_name);?></td>
                                <td>
                                    <a href="edit/url"><span class="glyphicon glyphicon-edit"></a>
                                    <a href="delete_web_feed.php?id=<?php echo $result->id; ?>"></span> <span class="glyphicon glyphicon-remove-sign"></span></a>
                                    <a href="web_feed_detail.php?id=<?php echo $result->id; ?>"></span> <span class="glyphicon glyphicon-eye-open"></span></a>
                                </td>
                            </tr>
                                <?php
                                    }}
//                   echo"</form>";
                echo "</tbody>";
                echo "</table>";
            ?>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        function cbDropdown(column) {
            if(column[0].innerHTML=='Provider Name'){
                return $('<ul>', {
                    'class': 'cb-dropdown'
                }).appendTo($('<div>', {
                    'class': 'cb-dropdown-wrap'
                }).appendTo(column));
            }else{
                return $('<ul style="display: none;">', {
                    'class': 'cb-dropdown'
                }).appendTo($('<div style="display: none;">', {
                    'class': 'cb-dropdown-wrap'
                }).appendTo(column));
            }

        }

        var table  = $('#example').DataTable({
            initComplete: function() {
                this.api().columns().every(function() {
                    var column = this;;
                    var ddmenu = cbDropdown($(column.header()))
                        .on('change', ':checkbox', function() {
                            var active;
                            var vals = $(':checked', ddmenu).map(function(index, element) {
                                active = true;
                                return $.fn.dataTable.util.escapeRegex($(element).val());
                            }).toArray().join('|');

                            column
                                .search(vals.length > 0 ? '^(' + vals + ')$' : '', true, false)
                                .draw();

                            // Highlight the current item if selected.
                            if (this.checked) {
                                $(this).closest('li').addClass('active');
                            } else {
                                $(this).closest('li').removeClass('active');
                            }

                            // Highlight the current filter if selected.
                            var active2 = ddmenu.parent().is('.active');
                            if (active && !active2) {
                                ddmenu.parent().addClass('active');
                            } else if (!active && active2) {
                                ddmenu.parent().removeClass('active');
                            }
                        });
                    column.data().unique().sort().each(function(d, j) {
                        var // wrapped
                            $label = $('<label>'),
                            $text = $('<span>', {
                                text: d
                            }),
                            $cb = $('<input>', {
                                type: 'checkbox',
                                value: d
                            });
                        $text.appendTo($label);
                        $cb.appendTo($label);

                        ddmenu.append($('<li>').append($label));
                    });
                });
            }
        });

        $('#export_rss').on('click', function(e){
            e.preventDefault();
            var data = table.$('input[type="checkbox"]').serializeArray();

            $.ajax({
                type: "POST",
                url: 'export_web_feed.php',
                data: data
            }).done(function(response){
                console.log('Response', response);
            });
        });

    });


</script>
<?php include ('footer.php');?>
