<?php

require "connect.php";

$House_number = filter_input(INPUT_POST,'House_number');


$sql = "SELECT * FROM `tbl_infor` WHERE `House_number` = '$House_number'";
    
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
    
array_push($result,array('Name'=>$row[2]));


}

 
echo json_encode(array("result"=>$result,JSON_UNESCAPED_UNICODE));
 
mysqli_close($con);

?>

