var photoApp = angular.module('imgApp');

photoApp.controller('slidersController', function ($scope){
    $scope.tabs = [
        {
            title: "CMYK",
            templateUrl: 'resources/modals/cmyk.html'
        },
        {
            title: "HSL",
            templateUrl: 'resources/modals/hsl.html'
        }
    ]
});
