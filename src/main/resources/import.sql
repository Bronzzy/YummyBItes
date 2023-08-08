-----INSERT DATA INTO RESTAURANT-----
INSERT INTO restaurant (address,closing_hour,"name",open_hour,phone) values ('4089 Charing Cross Drive','22:00:00','YummyBites','11:00:00','341-724-5075');



-----INSERT DATA INTO EMPLOYEE-----
INSERT INTO employee (address,email,first_name,last_name,phone,status,restaurant) VALUES('77210 Transport Court','bjowitt@friendfeed.com','Berny','Jowitt','673-264-9952','STATUS_ACTIVE',1);
INSERT INTO employee (address,email,first_name,last_name,phone,status,restaurant) VALUES('75 Sundown Pass','mjudron@aol.com','Myrilla','Judron','332-671-9443','STATUS_ACTIVE',1);
INSERT INTO employee (address,email,first_name,last_name,phone,status,restaurant) VALUES('415 Columbus Plac','pstores@gmpg.org','Paco','Stores','790-839-5818','STATUS_ACTIVE',1);
INSERT INTO employee (address,email,first_name,last_name,phone,status,restaurant) VALUES('3 Spenser Pass','ggavagan@paypal.com','Guillermo','Gavagan','731-564-3099','STATUS_ACTIVE',1);
INSERT INTO employee (address,email,first_name,last_name,phone,status,restaurant) VALUES('43183 Loeprich Terrace','ffairs@ft.com','Francene','Fairs','174-405-4165','STATUS_ACTIVE',1);


-----INSERT DATA INTO USER-----
INSERT INTO users (active,"password",username) VALUES(true,'$2a$10$.vs0WjuKzW4kSFmfoHi5oOi5IEhzEf63LzSr3VC4o.q1JjbeaWdjK','hoabinh0911@gmail.com');
INSERT INTO users (active,"password",username) VALUES(true,'$2a$10$twXJRklbn6YLQTT4hnvtXuB5kBjkZXRcc5321EMRL79/hNx3HGQeC','bjowitt@friendfeed.com');
INSERT INTO users (active,"password",username) VALUES(true,'$2a$10$5FVyXing8hQJd5ujehfIVOj.fMXk0DyHW/dsc0Nmhwe2M/6vpnEGy','mjudron@aol.com');
INSERT INTO users (active,"password",username) VALUES(true,'$2a$10$UBE6xsfeKBmo9eS.cwALPerfbIYmHNJtv96Hik2/q2bJRIY5fhLFO','pstores@gmpg.org');
INSERT INTO users (active,"password",username) VALUES(true,'$2a$10$KS8vk0cHQocN/DJLbAX6Jur46d1lH4bAE715Dl2vgAd.0jyB7KgyW','ggavagan@paypal.com');
INSERT INTO users (active,"password",username) VALUES(true,'$2a$10$6Yn6mO4kQvgA1qtLJvjc7uD4itJPike26roL9jj0z/cH/62SNB5yC','ffairs@ft.com');



-----INSERT DATA INTO USER ROLE ASSIGMENTS-----
INSERT INTO user_role_assignment (assigned_date,"role",modified_date,user_id) VALUES('2023-08-08 17:12:33.970','ROLE_OWNER','2023-08-08 17:12:33.970',1);
INSERT INTO user_role_assignment (assigned_date,"role",modified_date,user_id) VALUES('2023-08-08 17:13:45.880','ROLE_WAITER','2023-08-08 17:13:45.880',2);
INSERT INTO user_role_assignment (assigned_date,"role",modified_date,user_id) VALUES('2023-08-08 17:13:58.121','ROLE_WAITER','2023-08-08 17:13:58.121',3);
INSERT INTO user_role_assignment (assigned_date,"role",modified_date,user_id) VALUES('2023-08-08 17:14:17.055','ROLE_WAITER','2023-08-08 17:14:17.055',4);
INSERT INTO user_role_assignment (assigned_date,"role",modified_date,user_id) VALUES('2023-08-08 17:14:54.759','ROLE_COOK','2023-08-08 17:14:54.759',5);
INSERT INTO user_role_assignment (assigned_date,"role",modified_date,user_id) VALUES('2023-08-08 17:15:08.771','ROLE_COOK','2023-08-08 17:15:08.771',6);



-----INSERT DATA INTO SUPPLIER-----
INSERT INTO supplier (address,email,"name",phone) VALUES('0983 Kings Road','deliyahu0@foxnews.com','Mante Group','286-338-0165');
INSERT INTO supplier (address,email,"name",phone) VALUES('4013 Delladonna Avenue','gchanner2@myspace.com','Heidenreich, Kunde and Quitzon','311-954-0433');
INSERT INTO supplier (address,email,"name",phone) VALUES('41 Killdeer Road','rjakubovicz3@linkedin.com','Dach, Wuckert and Cronin','474-851-1834');
INSERT INTO supplier (address,email,"name",phone) VALUES('28 Welch Place','dbreitler4@tiny.cc','Jenkins LLC','345-394-9966');
INSERT INTO supplier (address,email,"name",phone) VALUES('80 Mcbride Hill','gjoist1@artisteer.com','Reichel Inc','744-784-1225');



-----INSERT DATA INTO INGREDIENT-----
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Potato',146.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Cheese',226.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Salmon',431.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Shrimp',155.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Pasta',371.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Beef',470.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Chicken',342.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Lamb',456.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Veal',145.0,1);
INSERT INTO ingredient ("name",quantity,restaurant) VALUES('Onions',56.0,1);



-----INSERT DATA INTO DINING TABLE-----
INSERT INTO dining_table ("number",occupied) VALUES(1,false);
INSERT INTO dining_table ("number",occupied) VALUES(2,false);
INSERT INTO dining_table ("number",occupied) VALUES(3,false);
INSERT INTO dining_table ("number",occupied) VALUES(4,false);
INSERT INTO dining_table ("number",occupied) VALUES(5,false);
INSERT INTO dining_table ("number",occupied) VALUES(6,false);
INSERT INTO dining_table ("number",occupied) VALUES(7,false);
INSERT INTO dining_table ("number",occupied) VALUES(8,false);
INSERT INTO dining_table ("number",occupied) VALUES(9,false);
INSERT INTO dining_table ("number",occupied) VALUES(10,false);



-----INSERT DATA INTO MENU ITEM-----
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Creamy layers of sliced potatoes and melted cheese, baked to perfection.','DISH_TYPE_MAIN_DISH','Cheesy Potato Casserole',200000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Al dente linguine pasta tossed with sautéed salmon, shrimp, and onions in a garlic butter sauce.','DISH_TYPE_MAIN_DISH','Salmon and Shrimp Linguine',300000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Tender beef strips stir-fried with a colorful medley of vegetables and served over fluffy rice.','DISH_TYPE_MAIN_DISH','Beef and Vegetable Stir-Fry',120000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Marinated chicken and potato cubes threaded onto skewers, grilled and served with a tangy dipping sauce.','DISH_TYPE_MAIN_DISH','Chicken and Potato Skewers',220000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Slow-cooked lamb stew infused with rich flavors of caramelized onions, tomatoes, and aromatic spices.','DISH_TYPE_MAIN_DISH','Lamb and Onion Stew',350000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Classic pasta carbonara featuring bacon, sautéed onions, and a creamy egg-based sauce.','DISH_TYPE_MAIN_DISH','Creamy Pasta Carbonara',150000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Tender veal cutlets sautéed with mushrooms and onions in a Marsala wine sauce, served with mashed potatoes.','DISH_TYPE_MAIN_DISH','Veal Marsala with Mushrooms',420000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Crispy shrimp and potato chunks nestled in warm tortillas, topped with a zesty coleslaw and salsa.','DISH_TYPE_MAIN_DISH','Shrimp and Potato Tacos',270000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Grilled tortillas stuffed with seasoned chicken, melted cheese, and sautéed onions, served with sour cream and guacamole.','DISH_TYPE_MAIN_DISH','Chicken and Cheese Quesadillas',170000.0,1);
INSERT INTO menu_item (description,dish_type,"name",price,restaurant_id) VALUES('Wholesome frittata loaded with caramelized onions and sliced potatoes, perfect for breakfast or brunch.','DISH_TYPE_MAIN_DISH','Onion and Potato Frittata',150000.0,1);
