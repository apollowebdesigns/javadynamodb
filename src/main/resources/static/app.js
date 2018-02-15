angular
    .module('app', []);

angular
    .module('app')
    .controller('AppController', AppController);

AppController.$inject = ['$http'];

function AppController($http) {
    let vm = this;

    vm.test = 'hello';

    let data = {
        "beaches": ["Budleigh"]
    };

    $http
        .post('/putitem', data)
        .then(data => console.log("success: " + data))
        .catch(err => console.log("err: " + err));
}