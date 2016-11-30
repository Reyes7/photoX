var photoApp = angular.module('imgApp');

photoApp.controller('postHsl', function ($scope, $http){
    var hueSlider, saturationSlider, lightnessSlider;
    var showHue, showSaturation, showLightness;

    $scope.hsl = {"hue": 0, "saturation": 0, "lightness": 0};

    $scope.initHsl = function() {
        hueSlider = document.getElementById('hue-slider');
        saturationSlider = document.getElementById('saturation-slider');
        lightnessSlider = document.getElementById('lightness-slider');

        showHue = document.getElementById('showHue');
        showSaturation = document.getElementById('showSaturation');
        showLightness = document.getElementById('showLightness');

        noUiSlider.create(hueSlider, {
            start: 0,
            step: 1,
            range: {
                'min': -180,
                'max': 180
            }
        });

        noUiSlider.create(saturationSlider, {
            start: 0.0000,
            step: 0.0001,
            range: {
                'min': -1.0000,
                'max': 1.0000
            }
        });

        noUiSlider.create(lightnessSlider, {
            start: 0.0000,
            step: 0.0001,
            range: {
                'min': -1.0000,
                'max': 1.0000
            }
        });

        hueSlider.noUiSlider.on('update', function( values, handle ) {
            $scope.hsl.hue = values[handle];
            showHue.value = values[handle];
        });

        saturationSlider.noUiSlider.on('update', function( values, handle ) {
            $scope.hsl.saturation = values[handle];
            showSaturation.value = values[handle];
        });

        lightnessSlider.noUiSlider.on('update', function( values, handle ) {
            $scope.hsl.lightness = values[handle];
            showLightness.value = values[handle];
        });
    };

    $scope.sendHsl = function (){
        $http.post("/alg/hsl",$scope.hsl).
        success(function(data, status, headers, config){
            console.log("success upload hsl data");
        }).error(function(data, status, headers, config){
            console.log("error upload hsl data");
        });
    };

    $scope.resetHslValues = function () {
        hueSlider.noUiSlider.set(0);
        saturationSlider.noUiSlider.set(0);
        lightnessSlider.noUiSlider.set(0);

        showHue.value = 0;
        showSaturation.value = 0;
        showLightness.value = 0;
    };
});