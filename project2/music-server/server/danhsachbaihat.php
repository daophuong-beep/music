<?php
	require "connect.php";

	// tạo lớp bài hát để trả đối tượng bài hát về
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



// đây là nhận dữ liệu và trả danh sách bài hát theo quảng cáo
	// nhận dữ liệu (idquangcao) 
	if(isset($_POST['idQuangCao'])){
		$idQuangCao=$_POST['idQuangCao'];
		$query="SELECT * FROM quangcao WHERE idQuangCao='$idQuangCao'";
		$dataquangcao = mysqli_query($con,$query);
		$row=mysqli_fetch_assoc($dataquangcao);
		$idBaiHat= $row['IdBaiHat']; 	
		$queryGetBaiHat="SELECT * FROM baihat WHERE idBaiHat='$idBaiHat'";
	}	
// đây là nhận dữ liệu theo id playlist và trả danh sách bài hát 
	// nhận dữ liệu idplaylist
	if(isset($_POST['idPlayList'])){
		$idPlayList=$_POST['idPlayList'];
		$queryGetBaiHat="SELECT * FROM BaiHat WHERE FIND_IN_SET('$idPlayList',idPlayList)";
		
	}
// đây là nhận dữ liệu theo id theloai và trả danh sách bài hát
	// nhận dữ liệu (idtheloai)
	if(isset($_POST['idTheLoai'])){
		$idTheLoai=$_POST['idTheLoai'];
		$queryGetBaiHat="SELECT * FROM BaiHat WHERE FIND_IN_SET('$idTheLoai',idTheLoai)";
		
	}
// đây là nhận dữ liệu theo id album và trả danh sách bài hát 
	// nhận dữ liệu (idalbum)
	if(isset($_POST['idAlbum'])){
		$idAlbum=$_POST['idAlbum'];
		$queryGetBaiHat="SELECT * FROM BaiHat WHERE FIND_IN_SET('$idAlbum',idAlbum)";
		
	}

	// lấy dữ liệu danh sách bài hát trả về cho client
	$dataBaiHat = mysqli_query($con,$queryGetBaiHat);
	$mangBaiHat=array();
	while ($rowBaiHat=mysqli_fetch_assoc($dataBaiHat)) {
		array_push($mangBaiHat, new BaiHat($rowBaiHat['idBaiHat'],$rowBaiHat['TenBaiHat'],$rowBaiHat['HinhBaiHat'],$rowBaiHat['CaSi'],$rowBaiHat['LinkBaiHat'],$rowBaiHat['LuotThich']));
	}
	echo json_encode($mangBaiHat);


 ?>