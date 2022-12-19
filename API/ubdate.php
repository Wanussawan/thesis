<?php
require "connect.php";

$Water_new = filter_input(INPUT_POST, "Water_new");
$House_number = filter_input(INPUT_POST, "House_number");
$Formuser =  filter_input(INPUT_POST, "Formuser");

date_default_timezone_set('Asia/Bangkok');
$date = date('Y-m-d');



$mysqli = "UPDATE `tbl_infor` SET `Water_new` = '".$Water_new."' , `Date` = '$date' , `Status` = 'ยังไม่จ่าย',  `Formuser` = '".$Formuser."' WHERE `House_number`='".$House_number."' ";

$result = mysqli_query($con,$mysqli);

if($data = mysqli_fetch_array($result)){
    echo '1';
}


mysqli_close($con);

?>