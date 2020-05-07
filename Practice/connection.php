<?php
$host = "localhost";
$user = "root";
$password = "";
$database = "cityguide";
$con = mysqli_connect($host,$user,$password,$database);
if(!$con)
{
		echo "failed";
}

?>