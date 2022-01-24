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
	$mangbaihat= array();
	if(isset($_POST['tukhoa'])){
		$tukhoa=$_POST['tukhoa'];
		if($tukhoa!=""){
			$query="SELECT * FROM baihat WHERE lower(TenBaiHat) LIKE '%$tukhoa%'";
			$data=mysqli_query($con,$query);
			while ($rowBaiHat=mysqli_fetch_assoc($data)) {
				array_push($mangbaihat, new BaiHat($rowBaiHat['idBaiHat'],$rowBaiHat['TenBaiHat'],$rowBaiHat['HinhBaiHat'],$rowBaiHat['CaSi'],$rowBaiHat['LinkBaiHat'],$rowBaiHat['LuotThich']));
			}
			echo json_encode($mangbaihat);
		}
		
	}else{
		echo "fail";
	}
?>