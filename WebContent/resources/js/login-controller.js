(function() {
	angular.module('hitModule').controller('loginController', function($scope,$location,$http) {	
		$scope.userName = "";
		$scope.password="";

		$scope.checkUser = function() {
			user = {
					userName: $scope.userName,
					password: $scope.password,		
			}
			$http({
				method : 'POST',
				data : user, // object
				url : 'ws/user/checklogin'
			}).then(function(response) {

				console.log(response.data);
				if(response.data!=0) { 

					$location.path( "/employer" );
				} 
				else {
					alert("Incorrect User/Password");
					$scope.userName = "";
					$scope.password="";
				}

			});




		};
	});
})();