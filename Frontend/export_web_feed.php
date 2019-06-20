<?php
include ('header.php');
$ids = $_GET['ids'];
$results = $obj->sendGetRequest("/feeds/export?ids=".$ids);
$mainResult = [];
foreach ($results as $result){
    $mainResult[] = (array) $result;
}

function array_to_xml( $data, &$xml_data )
{
    foreach ($data as $key => $value) {
        if (is_numeric($key)) {
            $key = 'item' . $key; //dealing with <0/>..<n/> issues
        }
        if (is_array($value)) {
            $subnode = $xml_data->addChild($key);
            array_to_xml($value, $subnode);
        } else {
            $xml_data->addChild("$key", htmlspecialchars("$value"));
        }
    }
}
$data = $mainResult;

// creating object of SimpleXMLElement
$xml_data = new SimpleXMLElement('<?xml version="1.0"?><result></result>');

// function call to convert array to xml
array_to_xml($data,$xml_data);

//saving generated xml file;
$result = $xml_data->asXML($_SESSION['user_id'].'_feed.xml');
?>
<a href="<?php echo $_SESSION['user_id']?>_feed.xml" style="display: none" id="feed_xml"></a>
<script>
    document.getElementById("feed_xml").click();
</script>
<?php
include ('footer.php');
?>
