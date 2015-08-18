app.factory('Orders', function ($resource) {
    return $resource('api/orders/:id', {},
    {
        save: {method: 'POST'}
    });
});