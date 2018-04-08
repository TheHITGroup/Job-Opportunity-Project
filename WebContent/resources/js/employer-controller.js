(function () {
    
    angular.module('hitModule').factory('jobList',
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
    
    
    
    angular.module('hitModule').controller('employerController', function ($scope,$rootScope, $location, $http) {
        
       //  console.log($rootScope.currentUser.id);
    });
})();