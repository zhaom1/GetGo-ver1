<?php
session_start();
$_SESSION['message'] = "");

class pull_course {
	private connection;


	function __construct() {
		require once ''; //add the path you store db_connect.php here
		$db = new DB_Connect();
		$this->connection = $db->connect();
	}


	function pull_data() {
		$query = "SELECT course_name from course";
		$result = msqli_query($this->connection, query) or die(mysqli_error($this->connection));
		$userinfo = mysqli_fetch_assoc($result);

	return $userinfo;
