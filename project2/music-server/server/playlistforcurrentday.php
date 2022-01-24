<?php
	require "connect.php";
	$query="SELECT DISTINCT * FROM playlist ORDER BY rand(".date("Ymd").") LIMIT 3";
	
	/**
	 * 
	 */
	class PlaylistToday{
		
		function __construct($idplaylist,$ten,$hinh,$icon)
		{
			$this->IdPlayList=$idplaylist;
			$this->Ten=$ten;
			$this->HinhPlayList=$hinh;
			$this->Icon=$icon;
		}

	}

	$arrayPlayList= array();
	$data = mysqli_query($con,$query);
	while($row= mysqli_fetch_assoc($data)){
		array_push($arrayPlayList, new PlaylistToday($row['idPlayList'],$row['Ten'],$row['HinhNen'],$row['Hinhicon']));
	}
	echo json_encode($arrayPlayList);
?>