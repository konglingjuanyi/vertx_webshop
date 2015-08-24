app.factory('Users', function($resource){
    return $resource('api/users/:id', {}, {
        save: { method: 'POST' }
    });
});