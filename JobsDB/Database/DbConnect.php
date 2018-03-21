<?php

class DbConnect
{
    private $host = "localhost";
    private $username = "root";
<<<<<<< HEAD
    private $password = "";
=======
    private $password = "***REMOVED***";
>>>>>>> 3bf8d47b4653eb1627eb809b35236c20059b9817
    private $database = "jobOps";
    
    public function getDbConnection()
    {
        try {
            $pdo = new PDO("mysql:host=$this->host;dbname=$this->database", $this->username, $this->password);
        } catch (PDOException $e) {
            print "Error!: " . $e->getMessage();
            die();
        }
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $pdo->setAttribute(PDO::MYSQL_ATTR_LOCAL_INFILE, true);
        
        return $pdo;
    }
}

