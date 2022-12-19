<?php

require "connect.php";

$Swine = filter_input(INPUT_POST,'Swine');


$sql = "SELECT * FROM `tbl_infor` WHERE `Swine` = '$Swine' AND `Date` = CURDATE()";
    
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
    
array_push($result,array(
            
        'Swine' =>$row[0],
        'House_number' =>$row[1],
        'Name'=>$row[2]
        
        ));


}

 
echo json_encode(array("result"=>$result,JSON_UNESCAPED_UNICODE));
 
mysqli_close($con);

?>

