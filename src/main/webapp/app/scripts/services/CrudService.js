/*
 * Generic CRUD resource REST service
 */
FirstApp.factory('CrudService', ['$resource', 'settings', function($resource, settings) {
  return function(type) {
    return $resource('/rest/' + type + '/:id', {}, {
      import: {
        method: 'POST',
        url: "/rest/" + type + "/import"
      },
      paged: {
        method: 'GET',
        url: "/rest/" + type + "/paged"
      }
    });
  };
}]);
