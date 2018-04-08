(function () {
    console.log("jobList");
    angular.module('hitModule').factory('jobListFactory',
            ['$resource', function ($resource) {
                    return function (userId) {
                        var restUrl = "ws/otherRequirements/UJOList";
                        return $resource(restUrl, {}, {
                            get: {
                                method: 'GET',
                                params: {'userId': userId
                                },
                                isArray: true
                            }

                        });
                    }
                }]);

     //techListFactory
     //stateListFactory
     //cityListFactory
     
     
    
        


    angular.module('hitModule').controller('employerController', function ($scope, $rootScope, $location, $http, jobListFactory,techListFactory,stateListFactory,cityListFactory) {
   
       $scope.techs = techListFactory.get(
                function (data) {
                }, function (error) {
            console.log("Error while getting REST data");
      });
      console.log($scope.techs ); 
        
        
        //list of states
        $scope.states = stateListFactory.get(
                function (data) {
                    console.log("success states!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });
        console.log($scope.states);
        
        //cities
       $scope.getCities = function() {
           $scope.cities = cityListFactory($scope.state).get();
           //console.log($scope.cities);
       }
       
       
       

        $scope.jobList = jobListFactory($rootScope.currentUser.id).get(
                function (data) {
                    $scope.jobListSize = data.length;
                }, function (error) {
            console.log("Error while getting REST data");
        });
        
        console.log($scope.jobList);
        var user = {
                    id: $rootScope.currentUser.id
                }

        $scope.deleteAccount = function () {
            if (confirm('Are you sure you want to delete your user?')) {
                console.log("delete");
                $http({
                    method: 'POST',
                    data: user, // object
                    url: 'ws/user/deleteUser'
                })
                        .then(
                                function (response) {
                                    console.log("POST success");
                                    $location.path("/userdeleted");

                                });

            } else {
                console.log("cancel");
            }
        };
    });
})();