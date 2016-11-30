photoApp.directive('modaldraggable', function ($document) {
    return function (scope, element) {
        element= angular.element(document.getElementsByClassName("modal-dialog"));
        $(element).draggable({
            containment: "parent"
        });
    };
});