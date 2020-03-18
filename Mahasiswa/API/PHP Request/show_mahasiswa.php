<?php

include("config.php");

$sql = "SELECT * FROM mahasiswa";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array(
    	'id' => $row['id'],
	    'nama_mahasiswa' => $row['nama_mahasiswa'],
	    'nomer_mahasiswa' => $row['nomer_mahasiswa'],
	    'alamat_mahasiswa' => $row['alamat_mahasiswa'],
	));
}
echo json_encode(array("result" => $result));
?>