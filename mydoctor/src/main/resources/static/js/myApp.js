var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $window, $http) {
    $scope.patient = {};
    $scope.patient.firstName = "John";
    $scope.patient.lastName = "Doe";

    $scope.showAlert = function () {
        $window.alert("Hello");
    }

    $scope.savePatient = function(){
        $http.post("registerp", $scope.patient)
            .then(function (data) {
                $scope.patient.username = "HELLO";
                $scope.patient = data;
            },
            function (data) {
                $scope.patient  =data;
            })
    };
});