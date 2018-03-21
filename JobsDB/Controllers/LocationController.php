<?php

class LocationController
{
    function populateLocationTable($pdo)
    {
        $dbc = new \DbController();
        $dbc->populateLocationTableWithFile($pdo);
    }
}

