<?php
define("PORT", 8000);
class myFunctions

{
//    CONST $port = 8000;

    function sendGetRequest($url) {

//    url = "http://localhost:8000/feeds/all";
//        $port =8000;
        $new_url = 'http://localhost:'.PORT.$url;

        $client = curl_init($new_url);
        curl_setopt($client,CURLOPT_RETURNTRANSFER,true);
        $response = curl_exec($client);

        // print_r($response);
        $results = json_decode($response);
        return $results;
    }
    function sendPostRequest($url, $data){
        $new_url = 'http://localhost:'.PORT.$url;
        $ch = curl_init( $new_url );
                # Setup request to send json via POST.
        $payload = $data;
        curl_setopt( $ch, CURLOPT_POSTFIELDS, $payload );
        curl_setopt( $ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
            #Return response instead of printing.
        curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
            # Send request.
        $result = curl_exec($ch);
        curl_close($ch);
        return $result;
    }

    function sendDeleteRequest($id){
//        $id = 13;
        $new_url = 'http://localhost:'.PORT.'/web-feed-provider/'.$id;
//        echo $new_url;die;
//        $url = $this->__url.$new_url;
        $ch = curl_init($new_url);
        curl_setopt($ch, CURLOPT_URL, $new_url);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
        $result = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);
//        header("Location:".$redirect_url."");
//        die();

        return $result;
    }

}
?>