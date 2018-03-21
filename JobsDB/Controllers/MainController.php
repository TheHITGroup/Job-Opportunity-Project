<?php

class MainController
{
    
    function dropTables($pdo) {
        $dbc = new DbController();
        
        $dbc->dropTable("Uses", $pdo);
        $dbc->dropTable("Job", $pdo);
        $dbc->dropTable("Technology", $pdo);
        $dbc->dropTable("Location", $pdo);
        $dbc->dropTable("User", $pdo);
    }
    
    function createDatabase($dbName, $pdo) {
        $dbc = new DbController();
        
        $dbc->createDatabase($dbName, $pdo);
    }
    
    function createTables($pdo) {
        $dbc = new DbController();
        
        $dbc->createUserTable($pdo);
        $dbc->createLocationTable($pdo);
        $dbc->createJobTable($pdo);
        $dbc->createTechnologyTable($pdo);
        $dbc->createUsesTable($pdo);
    }
    
    function populateTechnologyTable($pdo) {
        $tc = new TechnologyController();
        
        $tc->populateTechnologyTable($pdo);
    }
    
    function populateLocationTable($pdo) {        
        $lc = new LocationController();
        
        $lc->populateLocationTable($pdo);
    }
    
    function createRelations($pdo) {
        //CSV file containing zipcodes and their respective population
        $file = "zip_pop.csv";
        
        //Associative array with zipcode as index and pop as value
        $zipsAndPops = $this->getZipsAndPopsArrayFromFile($file);
        
        //file containing the zipcodes
        $zips = fopen("zips.txt", "r") or die("Unable to open file");
        
        while(($zip = fgets($zips)) !== FALSE) {
            $pop = $this->getPopForZip($zip, $zipsAndPops);
            
            $numJobs = $pop / 10000;
            $numJobs = floor($numJobs);
            
            $uc = new UsesController();
            $uc->jobFrameworkAndLanguageHandler($numJobs, $zip, $pdo);
        }
        
        fclose($zips);
    }
    
    private function getZipsAndPopsArrayFromFile($file) {
        $pop_zip = fopen("$file", "r") or die("Unable to open file");
        
        $rows = array_map('str_getcsv', file('zip_pop.csv'));
        
        foreach ($rows as $zip_pop_array) {
            $zipsAndPops[$zip_pop_array[0]] = $zip_pop_array[1];
        }
        
        return $zipsAndPops;        
    }
    
    private function getPopForZip($zip, $zipsAndPops) {
        $zipString = (string) $zip;
        $zipString = str_replace("\n", '', $zipString);
        
        //PHP gives a warning if there is no population for a zipcode
        //This handles the warning
        set_error_handler(array($this, 'warningHandler'));
        $pop = $zipsAndPops[$zipString]; 
        restore_error_handler();
        
        return $pop;
    }
    
    private function warningHandler() {
        return;
    }
}

