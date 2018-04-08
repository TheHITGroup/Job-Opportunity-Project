(function () {
    /*pattern 1*/
    angular.module('hitModule').factory('searchPatternOneFactory',
        ['$resource', function ($resource) {
        return function (zip) {
        var restUrl = "ws/patternQueries/langInZip";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'zip': zip},
                         isArray: false
                }

                });
                }
        }]);
    
    angular.module('hitModule').factory('searchPatternTwoFactory',
        ['$resource', function ($resource) {
        return function (zip) {
        var restUrl = "ws/patternQueries/fwInZip";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'zip': zip},
                         isArray: false
                }

                });
                }
        }]);
    
    
    angular.module('hitModule').controller('patternQueriesController', function ($scope, $location, $http,techListFactory,searchPatternOneFactory,searchPatternTwoFactory) {
        /*list of techs*/
        $scope.techs = techListFactory.get(
                function (data) {
                    console.log("success techs!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });
        
        /*result pattern 1*/
        $scope.resultPatternOne ="";
        $scope.searchPatternOne= function() {
           $scope.resultPatternOne = searchPatternOneFactory($scope.zipCode).get();
        }
        
        /*result pattern 2*/
        $scope.resultPatternTwo ="";
        $scope.searchPatternTwo= function() {
           $scope.resultPatternTwo = searchPatternTwoFactory($scope.zipCode2).get();
        }
        
        
        
    });
})();