<?php

require "connect.php";

$select = "SELECT `Swine`,`House_number`,`Name` FROM `tbl_infor` WHERE `Date` = CURDATE()";
    
$result = mysqli_query($con,$select);
$number_of_rows = mysqli_num_rows($result);

$response = array();

if($number_of_rows > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        $response[] = $row;
    }
}


echo json_encode(array("Information"=>$response,JSON_UNESCAPED_UNICODE));
mysqli_close($con);
?>

