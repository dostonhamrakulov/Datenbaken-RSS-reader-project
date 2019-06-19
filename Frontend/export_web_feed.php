<?php
include ('header.php');
echo "<pre>";
$ids = implode(',', $_POST['web_id']);
$results = $obj->sendGetRequest("/feeds/export?ids=".$ids );
echo "<pre>";
print_r($results);
echo "</pre>";
?>
<a id="rss_create"  target="_blank" href="export_web_feed.php"> </a>


<script>
    $('#rss_create').click();
</script>
<?php
include ('footer.php');
?>
