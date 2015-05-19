FirstApp.controller('AccountController', [ '$scope', '$rootScope',
		function($scope, $rootScope) {

			$scope.IsUndefined = function() {
				return $rootScope.user.role == "null";
			}

			$scope.IsPatient = function() {
				return $rootScope.user.role == "ROLE_PATIENT";
			}
			
			$scope.IsDoctor = function(){
				return $rootScope.user.role == "ROLE_DOCTOR";
			}

		} ]);
