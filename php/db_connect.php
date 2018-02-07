<?php

class DB_Connect {
    private $connection;

    public function connect() {
        // changed this in order to connect to DB on Joe's computer, need to generalize it
        require_once '..\backend_Getgo\db_config.php';
      
        $this->connection = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        return $this->connection;
    }
}
 
?>
