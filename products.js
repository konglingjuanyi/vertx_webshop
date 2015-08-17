db = connect("localhost:27017/DEFAULT_DB");
db.product.remove({});
db.category.remove({});
db.product.insert({ name: "Purina One", price: 2.80, description: "Purina One is the number one food for puppies!", category: {name: "Food"}});
db.product.insert({ name: "Pedigree Dentastix", price: 4.50, description: "Let your dog have the smile it deserves.", category: {name: "Food"}});
db.product.insert({ name: "Nom-nom bars", price: 2.99, description: "NOM NOM NOM NOM.", category: {name: "Food"}});
db.product.insert({ name: "Turbo 2000", price: 3.80, description: "Groom your pet in to perfection.", category: {name: "Grooming"}});
db.product.insert({ name: "Joy-a-matic", price: 14.99, description: "Joy comes automatically.", category: {name: "Toys"}});
db.product.insert({ name: "Dog-a-tron", price: 19.99, description: "It's a dog... which says tron.", category: {name: "Toys"}});

db.category.insert( {name: "Food"} );
db.category.insert( {name: "Grooming"} );
db.category.insert( {name: "Toys"} );