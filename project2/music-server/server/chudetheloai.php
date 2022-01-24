<?php
	require "connect.php";
	$querytheloai="SELECT DISTINCT * FROM theloai ORDER BY rand(".date("Ymd").") LIMIT 4";
	$querychude="SELECT DISTINCT * FROM chude ORDER BY rand(".date("Ymd").") LIMIT 4";

	$datatheloai = mysqli_query($con,$querytheloai);
	$datachude = mysqli_query($con,$querychude);
	// if($datachude&&$datatheloai){
	// 	echo "thành công";
	// }else{
	// 	echo "lỗi";
	// }
	class TheLoai{
		function __construct($idTheLoai,$idChuDe,$tenTheLoai,$hinhTheLoai){
			$this->idTheLoai=$idTheLoai;
			$this->idChuDe=$idChuDe;
			$this->tenTheLoai=$tenTheLoai;
			$this->hinhTheLoai=$hinhTheLoai;
		}
	}
	class ChuDe{
		function __construct($idChuDe,$tenChuDe,$hinhChuDe){
			$this->idChuDe=$idChuDe;
			$this->tenChuDe=$tenChuDe;
			$this->hinhChuDe=$hinhChuDe;
		}
	}

	$arraytheloai= array();
	$arraychude= array();

	while($row= mysqli_fetch_assoc($datatheloai)){
		array_push($arraytheloai, new TheLoai($row['idTheLoai'],$row['idChuDe'],$row['TenTheLoai'],$row['HinhTheLoai']));
	}
	

	while($row= mysqli_fetch_assoc($datachude)){
		array_push($arraychude, new ChuDe($row['idChuDe'],$row['TenChuDe'],$row['HinhChuDe']));
	}
	$arraytheloaichude=array('theloai'=>$arraytheloai,'chude'=>$arraychude);

	echo json_encode($arraytheloaichude);
?>	