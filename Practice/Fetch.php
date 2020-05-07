<?php

if($_SERVER['REQUEST_METHOD'] == 'POST')
{
	require('connection.php');



	
	$qry = "SELECT * FROM userdata";
	
	
	$result = mysqli_query($con,$qry);
	$Response = array();
	while($row = mysqli_fetch_assoc($result))
	{
		array_push($Response,array('name'=>$row['username'],'email'=>$row['useremail'],'number'=>$row['usernumber'],'password'=>$row['userpassword']));
	}
	echo json_encode($Response);
	

mysqli_close($con);
}else{
	echo 'failed';
}
?>