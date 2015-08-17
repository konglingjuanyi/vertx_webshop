/**
 * 
 */
'use strict';

app.factory('Products', function ($resource) {
    return $resource('api/products/:id', {},
    {
        query: {method: 'GET', isArray: true},
        getByCategory : { method: 'GET', isArray: true, params: { categoryName: '@categoryName'} },
        get: {method: 'GET'}
    });
});

app.factory('Categories', function ($resource) {
    return $resource('api/categories/:id', {},
    {
        query: {method: 'GET', isArray: true}
    });
});