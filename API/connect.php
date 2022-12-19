<?php

$server = "localhost";
$username = "id18849715_gwaterbkj";
$password = "Gwaterpro.1234";
$database = "id18849715_groundwaterbkj";

$con = new mysqli($server,$username,$password,$database);
if($con->connect_error){
    die("Connection failed: ". $con->connect_error);
}

?>