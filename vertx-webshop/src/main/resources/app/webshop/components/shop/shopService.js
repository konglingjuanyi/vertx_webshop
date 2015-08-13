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