var photoApp = angular.module('imgApp',['ui.bootstrap','ui.bootstrap.tabs']);

photoApp.controller('imageController', function($scope, $http, $uibModal){
    
    $scope.openSlidersWindow = function () {
        $uibModal.open({
            templateUrl: 'resources/modals/sliders.html',
            backdrop: false,
            controller: 'slidersController',
            controllerAs: this,
            resolve: {}
        });
    };
});