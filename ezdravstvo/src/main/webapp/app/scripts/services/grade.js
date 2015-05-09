FirstApp.factory("Grade", [function() {
  var grades = {};

  return {
    /**
     * set grade for a subject. If a grade for the subject exists, it will be
     * overridden
     */
    setGrade: function(subject, grade) {
      grades[subject] = grade;
    },
    /**
     * removes the grade for the subject, if it exists
     */
    removeGrade: function(subject) {
      delete grades[subject];
    },
    /**
     * retrieves all the grades as an array of objects.
     */
    getGradesAsArray: function() {
      var result = new Array();
      angular.forEach(grades, function(val, key) {
        result.push({
          subject: key,
          grade: val
        });
      });
      return result;
    }
  };
}]);