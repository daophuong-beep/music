<?php
	require "connect.php";
	$LuotThich=$_POST['luotthich'];
	$idBaiHat=$_POST['idbaihat'];

	$query="SELECT LuotThich FROM baihat WHERE idBaiHat='$idBaiHat' ";
	$data=mysqli_query($con,$query);
	$row= mysqli_fetch_assoc($data);
	$tongluotthich=$row['LuotThich'];
	if(isset($LuotThich)){
		$tongluotthich=$tongluotthich+$LuotThich;
		$querysum="UPDATE baihat SET LuotThich='$tongluotthich' WHERE idBaiHat='$idBaiHat'";
		$datatest=mysqli_query($con,$querysum);
		if($datatest){
			echo "success";
		}
	}
?>