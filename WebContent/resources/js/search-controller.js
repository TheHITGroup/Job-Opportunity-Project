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
            
     angular.module('hitModule').factory('cityListFactory',
            ['$resource', function ($resource) {
                return function(state) {
                    var restUrl = "ws/location/citylist";
                    return $resource(restUrl, {}, {
                        get: {
                            method: 'GET',
                            params: {'state': state },
                            isArray: true
                        }

                    });
                }
                }]);
            
      angular.module('hitModule').factory('searchOneFactory',
        ['$resource', function ($resource) {
        return function (techOne, techTwo, city, state) {
        var restUrl = "ws/complexQueries/twoTechsByCityState";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'techOne': techOne,
                                'techTwo' : techTwo,
                                'city' : city,
                                'state': state},
                         isArray: false
                }

                });
                }
        }]);        
            


    angular.module('hitModule').controller('searchController', function ($scope, $location, $http, techListFactory, stateListFactory,cityListFactory,searchOneFactory) {


      //  $scope.tech1="";
       // $scope.tech2="";
        //list of techs
        $scope.techs = techListFactory.get(
                function (data) {
                    console.log("success techs!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });
        console.log($scope.techs);
        

        //list of states
        $scope.states = stateListFactory.get(
                function (data) {
                    console.log("success states!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });
       console.log($scope.states);
       
       

       $scope.getCities = function() {
           console.log("click");
           $scope.cities = cityListFactory($scope.state).get();
           console.log($scope.cities);
       }
       
       
       $scope.resultOne = "";
        
        $scope.searchOne = function() {
             $scope.resultOne = searchOneFactory($scope.techOne, $scope.techTwo, $scope.city, $scope.state).get();
        }
        
 
    });
})();