(function () {

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



    angular.module('hitModule').controller('employerController', function ($scope, $rootScope, $location, $http, jobListFactory) {


       $scope.jobList = jobListFactory($rootScope.currentUser.id).get(
                function (data) {
                    $scope.jobListSize = data.length;
                }, function (error) {
            console.log("Error while getting REST data");
        });


    });
})();