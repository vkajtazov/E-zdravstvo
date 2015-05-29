FirstApp.controller('BookingController',
		[
				'$scope',
				'$rootScope',
				'BookingService',
				function($scope, $rootScope, BookingService) {

					$scope.dt = new Date();
					$scope.dt.setDate($scope.dt.getDate() + 1)

					$scope.format = 'yyyy-MM-dd';

					$scope.open = function($event) {
						$event.preventDefault();
						$event.stopPropagation();

						$scope.opened = true;
					};

					// Disable weekend selection
					$scope.disabled = function(date, mode) {
						return (mode === 'day' && (date.getDay() === 0 || date
								.getDay() === 6));
					};

					// Disable before tomorrow selection
					$scope.toggleMin = function() {
						$scope.minDate = $scope.minDate ? null : new Date();
						$scope.minDate.setDate($scope.minDate.getDate() + 1);
					};
					$scope.toggleMin();

					console.log($scope.dt.getFullYear()+'-0'+$scope.dt.getMonth()+'-'+$scope.dt.getDate());
					$scope.search = function() {
						BookingService.find($.param({
							byDate : $scope.dt.toISOString()
						}));
					}

				} ]);
