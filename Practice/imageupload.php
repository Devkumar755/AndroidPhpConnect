<?php
require("connection.php");
if($con)
{
	$title = $_POST['title'];
	$image = $_POST['image'];
	$path = "uploads/$title.jpg";
	$serverpath = "http://192.168.8.100/Practice/$path";
	$sql = "Insert into imginfo(title,url) values('$title','$serverpath')";
	if(mysqli_query($con,$sql))
	{
		file_put_contents($path,base64_decode($image));
		echo json_encode(array("response"=>"image uploaded successfulyy"));
	}else{
		echo json_encode(array("response"=>"image upladed failed"));
	}
	mysqli_close($con);
}else
{
	
}

?>