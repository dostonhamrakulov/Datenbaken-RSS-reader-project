<?php
define("HOST","http://localhost:");
define("PORT", 8000);
class myFunctions

{
//    CONST $port = 8000;

    function sendGetRequest($url) {

//    url = "http://localhost:8000/feeds/all";
        $new_url = HOST.PORT.$url;
        $client = curl_init($new_url);
        curl_setopt($client,CURLOPT_RETURNTRANSFER,true);
        $response = curl_exec($client);

        $results = json_decode($response);
        return $results;
    }
    function sendPostRequest($url, $data){
        $new_url = HOST.PORT.$url;
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
        $new_url = HOST.PORT.'/web-feed-provider/'.$id;
        $ch = curl_init($new_url);
        curl_setopt($ch, CURLOPT_URL, $new_url);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
        $result = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);

        return $result;
    }

    function sendPutRequest($url, $data){
        $new_url = HOST.PORT.$url;
        $ch = curl_init();

        curl_setopt($ch, CURLOPT_URL, $new_url);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Content-Length: ' . strlen($data)));
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
        curl_setopt($ch, CURLOPT_POSTFIELDS,$data);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $response  = curl_exec($ch);
        curl_close($ch);

        $results = json_decode($response);
        return $results;
    }

}
?>