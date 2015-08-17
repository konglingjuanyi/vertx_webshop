db = connect("localhost:27017/DEFAULT_DB");
db.product.remove({});
db.category.remove({});
db.product.insert({ name: "Purina One", price: 2.80, description: "Purina One is the number one food for puppies!", category: {name: "Food"}});
db.product.insert({ name: "Pedigree Dentastix", price: 4.50, description: "Let your dog have the smile it deserves.", category: {name: "Food"}});
db.product.insert({ name: "Turbo 2000", price: 3.80, description: "Groom your pet in to perfection.", category: {name: "Grooming"}});

db.category.insert( {name: "Food"} );
db.category.insert( {name: "Grooming"} );