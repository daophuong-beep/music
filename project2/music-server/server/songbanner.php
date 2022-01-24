<?php
	require "connect.php";
	$query = "SELECT quangcao.idQuangCao, quangcao.HinhAnh, quangcao.NoiDung ,quangcao.idBaiHat , baihat.TenBaiHat ,baihat.HinhBaiHat FROM baihat INNER JOIN quangcao ON quangcao.IdBaiHat=baihat.idBaiHat || baihat.idBaiHat=quangcao.IdBaiHat";
	$data = mysqli_query($con,$query);

	class Quangcao{
		function __construct($idquangcao,$hinhanh,$noidung,$idbaihat,$tenbaihat,$hinhbaihat){
			$this->idquangcao=$idquangcao;
			$this->hinhanh=$hinhanh;
			$this->noidung=$noidung;
			$this->idbaihat=$idbaihat;
			// $this->$tenbaihat=$tenbaihat;
			$this->tenbaihat=$tenbaihat;
			$this->hinhbaihat=$hinhbaihat;
		}
	}
	$mang= array();
	while($row=mysqli_fetch_assoc($data)){
		array_push($mang, new Quangcao($row['idQuangCao'],$row['HinhAnh'],$row['NoiDung'],$row['idBaiHat'],$row['TenBaiHat'],$row['HinhBaiHat']));

	}
	echo json_encode($mang);
?>