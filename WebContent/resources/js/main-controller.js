(function () {
    angular.module('hitModule').controller('mainController', function ($rootScope) {
        $rootScope.currentUser = {
            id: 0,
            username:""
        }
    });
})();