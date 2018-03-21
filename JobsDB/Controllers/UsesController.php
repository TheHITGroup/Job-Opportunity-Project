<?php

class UsesController
{
    function insertUses($uses, $pdo)
    {
        $dbc = new DbController();
        $dbc->insertUsesTuple($uses, $pdo);
    }
    
    function jobFrameworkAndLanguageHandler($numJobs, $zip, $pdo)
    {
        $jobId = 1;
        for($i = 0; $i < $numJobs; $i++) {
            $this->insertNewJob($jobId, $zip, $pdo);
            
            $this->createLanguageAndFrameworkForJob($jobId, $pdo);
            
            $jobId++;
        }    
    }
    
    private function insertNewJob($jobId, $zip, $pdo) {
        $jc = new JobController();
        
        $job = new Job($zip, $jobId);
        $jc->insertJob($job, $pdo);
    }
    
    private function createLanguageAndFrameworkForJob($jobId, $pdo) {
        $tc = new TechnologyController();
        
        $language = $tc->getRandomLanguage();
        
        $this->insertNewUsesRelationForJobAndLanguage($jobId, $language, $pdo);
        //SQL does not have frameworks associated with it
        if($language != "SQL") {
            $this->insertNewUsesRelationForJobAndFramework($jobId, $pdo, $language);
        }
    }
    
    private function insertNewUsesRelationForJobAndLanguage($jobId, $language, $pdo) {
        $usesTuple = $this->getUsesTupleForLanguage($language, $jobId, $pdo);
        $this->insertUses($usesTuple, $pdo);
    }
    
    private function getUsesTupleForLanguage($language, $jobId, $pdo) {
        $tc = new TechnologyController();
        
        $techId = $tc->queryTechnologyId($language, $pdo);
        $usesTuple = new Uses($techId, $jobId);
        $usesTuple->language = $language;
        
        return $usesTuple;
    }
    
    private function insertNewUsesRelationForJobAndFramework($jobId, $pdo, $language) {
        $tc = new TechnologyController();
        
        $framework = $tc->getRandomFrameworkForLanguage($language);
        $usesTuple = $this->getUsesTupleForFramework($framework, $jobId, $pdo);
        $this->insertUses($usesTuple, $pdo);
    }
    
    private function getUsesTupleForFramework($framework, $jobId, $pdo) {
        $tc = new TechnologyController();
        
        $techId = $tc->queryTechnologyId($framework, $pdo);
        $usesTuple = new Uses($techId, $jobId);
        
        return $usesTuple;
    }
}

