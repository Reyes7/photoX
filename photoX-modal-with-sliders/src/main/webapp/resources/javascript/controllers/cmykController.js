var photoApp = angular.module('imgApp');

photoApp.controller('postCmyk', function ($scope, $http){
    var cyanSlider, magentaSlider, yellowSlider, blackSlider;
    var showCyan, showMagenta, showYellow, showBlack;
    
    $scope.cmyk = {"cyan": 0.0000, "magenta": 0.0000, "yellow": 0.0000, "black": 0.0000};

    $scope.initCmyk = function() {
        cyanSlider = document.getElementById('cyan-slider');
        magentaSlider = document.getElementById('magenta-slider');
        yellowSlider = document.getElementById('yellow-slider');
        blackSlider = document.getElementById('black-slider');
        
        showCyan = document.getElementById('showCyan');
        showMagenta = document.getElementById('showMagenta');
        showYellow = document.getElementById('showYellow');
        showBlack = document.getElementById('showBlack');

        noUiSlider.create(cyanSlider, {
            start: 0.0000,
            step: 0.0001,
            range: {
                'min': -1.0000,
                'max': 1.0000
            }
        });

        noUiSlider.create(magentaSlider, {
            start: 0.0000,
            step: 0.0001,
            range: {
                'min': -1.0000,
                'max': 1.0000
            }
        });

        noUiSlider.create(yellowSlider, {
            start: 0.0000,
            step: 0.0001,
            range: {
                'min': -1.0000,
                'max': 1.0000
            }
        });

        noUiSlider.create(blackSlider, {
            start: 0.0000,
            step: 0.0001,
            range: {
                'min': -1.0000,
                'max': 1.0000
            }
        });

        cyanSlider.noUiSlider.on('update', function( values, handle ) {
            $scope.cmyk.cyan = parseFloat(values[handle]);
            showCyan.value = values[handle];
        });

        magentaSlider.noUiSlider.on('update', function( values, handle ) {
            $scope.cmyk.magenta = parseFloat(values[handle]);
            showMagenta.value = values[handle];
        });

        yellowSlider.noUiSlider.on('update', function( values, handle ) {
            $scope.cmyk.yellow = parseFloat(values[handle]);
            showYellow.value = values[handle];
        });

        blackSlider.noUiSlider.on('update', function( values, handle ) {
            $scope.cmyk.black = parseFloat(values[handle]);
            showBlack.value = values[handle];
        });
    };

    $scope.sendCmyk = function (){
        $http.post("/alg/cmyk",$scope.cmyk).
        success(function(data, status, headers, config){
            console.log("success upload cmyk data");
        }).error(function(data, status, headers, config){
            console.log("error upload cmyk data");
        });
    };

    $scope.resetCmykValues = function () {
        cyanSlider.noUiSlider.set(0);
        magentaSlider.noUiSlider.set(0);
        yellowSlider.noUiSlider.set(0);
        blackSlider.noUiSlider.set(0);

        showCyan.value = 0.00;
        showMagenta.value = 0.00;
        showYellow.value = 0.00;
        showBlack.value = 0.00;
    };
});