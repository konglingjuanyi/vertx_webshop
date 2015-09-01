app.factory('Categories', function ($resource) {
    return $resource('api/categories/:id', {},
    {
        query: {method: 'GET', isArray: true}
    });
});