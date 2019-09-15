INSERT INTO user_roles (description, role) VALUES ('default user role', 'ROLE_USER');

INSERT INTO users_roles (`user_id`, `roles_id`) VALUES ('1', '1');

INSERT INTO users (email, password, username) VALUES ('user@gmail.com',
'{bcrypt}$2a$10$RNwFbL1GYDiPj3YOvYXwKueJsWwK5O1/UrFxX33DMKI0mcnRMEBlm', 'user');

INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Jedzenie', '1');
INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Rozrywka', '1');
INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Transport', '1');
INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Rachunki', '1');
INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Usługi', '1');
INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Ubrania', '1');
INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Zdrowie', '1');
INSERT INTO expense_categories (`name`, `user_id`) VALUES ('Higiena', '1');

INSERT INTO expenses (`time`, `title`, `value`, `category_id`, `user_id`) VALUES ('2019-09-15 11:20:01', 'Bułka', '1.50', '1', '1');
INSERT INTO expenses (`time`, `title`, `value`, `category_id`, `user_id`) VALUES ('2019-09-15 12:20:01', 'Hot-Dog', '2.00', '1', '1');
INSERT INTO expenses (`time`, `title`, `value`, `category_id`, `user_id`) VALUES ('2019-09-15 19:00:00', 'Kino', '20.00', '2', '1');





