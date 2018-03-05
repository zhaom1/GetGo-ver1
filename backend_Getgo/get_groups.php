<?php
// Used to display message that user has been created.
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
class Get_All_Groups
{
    private $connection;

    // the construction is to initiate the connection in Create_User class
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
    function get_groups()
    {

/*
 * Following code will list all the products
 */

    // array for JSON response
        $response = array();
        $query = "SELECT * from groups"; 
    // get all products from products table
        $result = mysqli_query($this->connection, $query) or die(mysqli_error($this->connection));

// check for empty result
        if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // products node
            $response= array();
    
        while ($row = mysqli_fetch_array($result)) {
        // temp user array
            $group = array();
            
            $group["course_id"] = $row["course_id"];
            $group["institution_id"] = $row["institution_id"];
            $group["group"] = $row["group"];

        // push single product into final response array
            array_push($response, $group);
        }
    // success
        //$response["success"] = 1;

    // echoing JSON response
        echo json_encode($response);
        } else {
    // no products found
        $response["success"] = 0;
        $response["message"] = "No condtions found";

    // echo no users JSON
    echo json_encode($response);
}
}
}
$condtions = new Get_All_Groups();
$condtions->get_groups();
?>