<?php 

include_once("connect.php");

$query = "SELECT `Status`,`Swine`,`Name` FROM `tbl_infor` WHERE `Status`='ยังไม่จ่าย' ORDER BY `Swine` ASC";

$result = mysqli_query($con,$query);
$number_of_rows = mysqli_num_rows($result);

$response = array();

if($number_of_rows > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        $response[] = $row;
    }
}

header('Content-Type: application/json');
echo json_encode(array("Information"=>$response));
mysqli_close($con);

?>