<?php
	require "connect.php";
	class BaiHat{
		function __construct($idBaiHat,$tenBaiHat,$hinhBaiHat,$caSi,$linkBaiHat,$luotThich){
			$this->idBaiHat=$idBaiHat;
			$this->tenBaiHat=$tenBaiHat;
			$this->hinhBaiHat=$hinhBaiHat;
			$this->caSi=$caSi;
			$this->linkBaiHat=$linkBaiHat;
			$this->luotThich=$luotThich;
		}

	}
	$query="SELECT * FROM baihat ORDER BY luotThich DESC LIMIT 5";
	$data=mysqli_query($con,$query);
	$mangBaiHat= array();

	while ($row= mysqli_fetch_assoc($data)) {
		array_push($mangBaiHat,new BaiHat($row['idBaiHat'],$row['TenBaiHat'],$row['HinhBaiHat'],$row['CaSi'],$row['LinkBaiHat'],$row['LuotThich']));
	}
	echo json_encode($mangBaiHat);
?>