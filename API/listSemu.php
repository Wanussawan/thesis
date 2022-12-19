<?php 

include_once("connect.php");


$query = "SELECT `Status`,`Swine`,`Name` FROM `tbl_infor` WHERE `Status`='จ่ายแล้ว' ORDER BY `Swine` ASC";

$result = mysqli_query($con,$query);
$number_of_rows = mysqli_num_rows($result);

$response = array();

if($number_of_rows > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        $response[] = $row;
    }
}


echo json_encode(array("Information"=>$response));
mysqli_close($con);

?>