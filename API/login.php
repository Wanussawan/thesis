<?php 

$username = filter_input(INPUT_POST, "username");
$password = filter_input(INPUT_POST, "password");



 $mysqli = new mysqli("localhost","id18849715_gwaterbkj","Gwaterpro.1234","id18849715_groundwaterbkj");

 $result = mysqli_query($mysqli,"select * from user where username = '".$username."' and password = '".$password."'");

 if($data = mysqli_fetch_array($result)){
    echo '1';
}

?>