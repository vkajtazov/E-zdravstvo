FirstApp.controller('BookingController',
		[
				'$scope',
				'$rootScope',
				'BookingService',
				function($scope, $rootScope, BookingService) {

					$scope.dt = new Date();

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

					console.log($scope.dt);
					$scope.search = function() {
						BookingService.find($.param({
							byDate : $scope.dt
						}), function(result) {
							$scope.entity = {};
							$scope.entities = result;
							$scope.entity = $scope.entities[0];
							console.log($scope.entity.id);
						});
					}

					$scope.book = function() {
						BookingService.book($.param({
							date : $scope.dt,
							time_id : $scope.entity.id
						}));
					}

				} ]);
