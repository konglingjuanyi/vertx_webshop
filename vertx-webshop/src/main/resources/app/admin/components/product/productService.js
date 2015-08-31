app.factory('Products', function ($resource) {
    return $resource('api/products/:id', {},
    {
        query: {method: 'GET', isArray: true},
        delete: { method: 'DELETE', params: { id:'@id' } },
        get: {method: 'GET'}
    });
});