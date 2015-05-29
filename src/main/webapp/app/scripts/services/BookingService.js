FirstApp.factory('BookingService', function($resource) {

	return $resource('/rest/bookings/:action', {}, {
		find : {
			method : 'POST',
			isArray : true,
			params : {
				'action' : 'find'
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}
	});
});