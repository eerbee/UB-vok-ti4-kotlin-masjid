<?php 

	include ("config.php");

	$isi_marquee = $_POST['isi_marquee'];

	$sql = "INSERT INTO marquee (id_marquee, isi_marquee) VALUES ('', '$isi_marquee')";
	$query = mysqli_query($db, $sql);

	if($query)
	{
		
	}
	else
	{
		die("Gagal menyimpan...");
	}

?>