<?php 

require "connect.php";

$result = array();
$result['data'] = array();

$select = "SELECT * FROM `tbl_infor`";


$responce = mysqli_query($con,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['swine'] = $row['0'];
			$index['numhouse'] = $row['1'];
			$index['name'] = $row['2'];
			$index['status'] = $row['9'];
			$index['date'] = $row['10'];
		
			
			array_push($result['data'], $index);
		}
			
$result["success"]="1";
echo json_encode($result,JSON_UNESCAPED_UNICODE);
mysqli_close($con);

?>