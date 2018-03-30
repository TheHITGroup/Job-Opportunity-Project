(function () {
    angular.module('hitModule').factory('techListFactory',
            ['$resource', function ($resource) {
                    var restUrl = "ws/tech/list";
                    return $resource(restUrl, {}, {
                        get: {
                            method: 'GET',
                            params: {},
                            isArray: true
                        }

                    });

                }]);

    angular.module('hitModule').factory('stateListFactory',
            ['$resource', function ($resource) {
                    var restUrl = "ws/location/statelist";
                    return $resource(restUrl, {}, {
                        get: {
                            method: 'GET',
                            params: {},
                            isArray: true
                        }

                    });

                }]);


    angular.module('hitModule').controller('searchController', function ($scope, $location, $http, techListFactory,stateListFactory) {


    //    $scope.state = "NJ"; //default


        $scope.techs = techListFactory.get(
                function (data) {
                    console.log("success!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });

        $scope.states = stateListFactory.get(
                function (data) {
                    console.log("success!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });

        
     


    });
})();