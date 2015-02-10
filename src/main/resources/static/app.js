angular.module('launcher', ['ngResource'])
    .controller('launcherControl', function($scope, $resource){

        var resource = $resource("http://localhost:8080/nina/launcher");

        $scope.command = "";

        $scope.executeCommand = function() {
            resource.save($scope.command).$promise.then(function(data){
                $scope.executeCommand= data.executeCommand;
            });
        }
    });