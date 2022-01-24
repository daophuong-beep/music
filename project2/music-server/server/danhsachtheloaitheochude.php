<?php
	require "connect.php";

	class TheLoai{
		function __construct($idTheLoai,$idChuDe,$tenTheLoai,$hinhTheLoai){
			$this->idTheLoai=$idTheLoai;
			$this->idChuDe=$idChuDe;
			$this->tenTheLoai=$tenTheLoai;
			$this->hinhTheLoai=$hinhTheLoai;
		}
	}
	if(isset($_POST['idChuDe'])){
		$idChuDe=$_POST['idChuDe'];
		$query="SELECT * FROM theloai where idChuDe='$idChuDe'";
		$data=mysqli_query($con,$query);
		$mangTheLoai=array();
		while($row=mysqli_fetch_assoc($data)){
			array_push($mangTheLoai, new TheLoai($row['idTheLoai'],$row['idChuDe'],$row['TenTheLoai'],$row['HinhTheLoai']));
		}
		echo json_encode($mangTheLoai);
	}
?>