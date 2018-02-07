<?php
session_start();
$_SESSION['message']="";

/* This class creates a new user, encrypts the password and inserts it into
 * the database.
 * Usage: $var = new Create_User()
 * Return: new connection to DB
 * NOTE: UNSURE IF THIS NEEDS TO BE ITS OWN CLASS RIGHT NOW, will talk to group memebers
 * accordingly.
 * NOTE: THE CONNECTION TO db_connect.php is hardcoded based on Joe;s filepath, need to make it
 * generalized for all users...
*/
class Get_All_Courses 
{
    private $connection;

    // the construction is to initiate the connection in Create_User class
    // Author: Liliana Quyen Tang
    function __construct()
    {
        require_once '..\backend_Getgo\db_connect.php';

        $db = new DB_Connect();
        $this->connection = $db->connect();
    }
    /* creates a user from the data entered into the form
    * Usage: $user->create_user();
    * Return: None
    */
    function get_courses()
    {

/*
 * Following code will list all the products
 */

    // array for JSON response
        $response = array();
        $query = "SELECT * from course"; 
    // get all products from products table
        $result = mysqli_query($this->connection, $query) or die(mysqli_error($this->connection));

// check for empty result
        if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // products node
            $response["courses"] = array();
    
        while ($row = mysqli_fetch_array($result)) {
        // temp user array
            $course = array();
            
            $course["course_id"] = $row["course_id"];
            $course["course_name"] = $row["course_name"];
            $course["credits"] = $row["credits"];
            $course["decription"] = $row["decription"];


        // push single product into final response array
            array_push($response["courses"], $course);
        }
    // success
        $response["success"] = 1;

    // echoing JSON response
        echo json_encode($response);
        } else {
    // no products found
        $response["success"] = 0;
        $response["message"] = "No courses found";

    // echo no users JSON
    echo json_encode($response);
}
}
}
$courses = new Get_All_Courses();
$courses->get_courses();
?>

