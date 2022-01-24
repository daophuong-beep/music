<?php
	require "connect.php";
	$query="SELECT * FROM album";
	$dataalbum = mysqli_query($con,$query);
	class Album{
		function __construct($idAlbum,$tenalbum,$tencasialbum,$hinhalbum){
			$this->idAlbum=$idAlbum;
			$this->tenAlbum=$tenalbum;
			$this->tenCaSiAlbum=$tencasialbum;
			$this->hinhAlbum=$hinhalbum;
		}
	}

	$arrayAlbum = array();
	while ($row = mysqli_fetch_assoc($dataalbum)) {
		array_push($arrayAlbum, new Album($row['idAlbum'],$row['TenAlbum'],$row['TenCaSiAlbum'],$row['HinhAlbum']));
	}
	echo json_encode($arrayAlbum);
?>