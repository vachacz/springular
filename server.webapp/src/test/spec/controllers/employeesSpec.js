describe("controller.employees", function() {

    var $scope, _$httpBackend;
    var employeeController;

    beforeEach(module('springular.app'));
    beforeEach(inject(function($controller, $rootScope, filterFilter, $httpBackend) {
        $scope = $rootScope.$new();

        _$httpBackend = $httpBackend;
        _$httpBackend.expect('GET', '/angular-rest/employee').respond(200, [
            { login : "test1", firstName : "John", lastName : "Bush" },
            { login : "test2", firstName : "John", lastName : "Obama" },
            { login : "test3", firstName : "Chris", lastName : "Clinton" }
        ]);

        employeeController = $controller('controller.employees', {
            $scope: $scope,
            filterfilter: filterFilter
        });

        _$httpBackend.flush();
    }));

    afterEach(function() {
        _$httpBackend.verifyNoOutstandingExpectation();
        _$httpBackend.verifyNoOutstandingRequest();
    });

    it("should filter employees after criteria change", function() {
        $scope.filterCriteria.login = "test1";
        $scope.$apply(); // triggers watch

        expect($scope.employeesTotal).toBe(1);
        expect($scope.employeesFilteredList[0].lastName).toBe("Bush");

        $scope.filterCriteria.login = "test2";
        $scope.$apply(); // triggers watch

        expect($scope.employeesTotal).toBe(1);
        expect($scope.employeesFilteredList[0].lastName).toBe("Obama");
    });

    it("should sort by an attribute", function() {
        $scope.sortEmployees("lastName");
        $scope.sortEmployees("firstName");

        expect($scope.employeeOrderReverse).toBe(false);
        expect($scope.employeeOrderPredicate).toBe("firstName");
    });

    it("should sort by an attribute in a reversed order", function() {
        $scope.sortEmployees("firtName"); // sort
        $scope.sortEmployees("firtName"); // sort in reversed order

        expect($scope.employeeOrderReverse).toBe(true);
        expect($scope.employeeOrderPredicate).toBe("firtName");
    });

    it("should delete an employee", function() {
        $scope.deleteEmployee($scope.employees[0]);

        _$httpBackend.expect('DELETE', '/angular-rest/employee').respond('200');
        _$httpBackend.flush();

        expect($scope.employeesTotal).toBe(2);
        expect($scope.employees[0].login).toBe("test2"); // test1 was deleted
    });

    it("should preserve ordering after delete", function() {
        $scope.filterCriteria.firstName = "John";
        $scope.$apply(); // apply filter by John

        $scope.deleteEmployee($scope.employeesFilteredList[0]);

        _$httpBackend.expect('DELETE', '/angular-rest/employee').respond('200');
        _$httpBackend.flush();

        expect($scope.employeesTotal).toBe(1); // Chris is still out of the filter
        expect($scope.employees[0].login).toBe("test2"); // test1 was deleted
    });

    it("should sort employees", function() {
        $scope.sortEmployees("firstName");

        expect($scope.employeeOrderPredicate).toBe("firstName");
        expect($scope.employeeOrderReverse).toBe(false);

        $scope.sortEmployees("lastName");

        expect($scope.employeeOrderPredicate).toBe("lastName");
        expect($scope.employeeOrderReverse).toBe(false);
    });

    it("should revert order", function() {
        $scope.sortEmployees("firstName");
        expect($scope.employeeOrderReverse).toBe(false);

        $scope.sortEmployees("firstName");
        expect($scope.employeeOrderReverse).toBe(true);
    });

    it("should compute ordering class based on revers attribute", function() {
        // sort by first name
        $scope.sortEmployees("firstName");
        expect($scope.sortClass("firstName")).toBe('sort-false');
        expect($scope.sortClass("lastName")).toBe('');

        // sort by first name in reversed order
        $scope.sortEmployees("firstName");
        expect($scope.sortClass("firstName")).toBe('sort-true');
        expect($scope.sortClass("lastName")).toBe('');

        // sort by last name
        $scope.sortEmployees("lastName");
        expect($scope.sortClass("firstName")).toBe('');
        expect($scope.sortClass("lastName")).toBe('sort-false');

        // sort by last name in reversed order
        $scope.sortEmployees("lastName");
        expect($scope.sortClass("firstName")).toBe('');
        expect($scope.sortClass("lastName")).toBe('sort-true');
    });
});