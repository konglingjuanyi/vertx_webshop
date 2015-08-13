/**
 * 
 */
'use strict';

app.factory('Products', function ($resource) {
    return $resource('api/products/:id', {},
    {
        query: {method: 'GET', isArray: true}
    });
});

app.factory('Categories', function ($resource) {
    return $resource('api/categories/:id', {},
    {
        query: {method: 'GET', isArray: true}
    });
});