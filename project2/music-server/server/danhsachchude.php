<?php
	require "connect.php";
	$query="SELECT * FROM chude";
	$data= mysqli_query($con,$query);
	class ChuDe{
		function __construct($idChuDe,$tenChuDe,$hinhChuDe){
			$this->idChuDe=$idChuDe;
			$this->tenChuDe=$tenChuDe;
			$this->hinhChuDe=$hinhChuDe;
		}
	}
	$arraychude= array();
	while($row= mysqli_fetch_assoc($data)){
		array_push($arraychude, new ChuDe($row['idChuDe'],$row['TenChuDe'],$row['HinhChuDe']));
	}
	echo json_encode($arraychude);
?>