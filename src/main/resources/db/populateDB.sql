INSERT INTO users (name, email, password) VALUES
                                                 ('User', 'user@yandex.ru', 'password'),
                                                 ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
                                              ('ROLE_USER', 100000),
                                              ('ROLE_ADMIN', 100001);

insert into meals (description, calories, date_time, user_id) values
                                                                     ('Завтрак', 500,'2018-10-22 10:23:34', 100000);
insert into meals (description, calories, date_time, user_id) values
                                                                     ('Обед', 503, '2018-10-22 14:24:34', 100000);
insert into meals (description, calories, date_time, user_id) values
                                                                     ('Ужин', 545, '2018-10-22 19:25:34', 100000);
insert into meals (description, calories, date_time, user_id) values
                                                                     ('Завтрак', 500, '2018-10-23 10:23:34', 100001);
insert into meals (description, calories, date_time, user_id) values
                                                                     ('Обед', 1003, '2018-10-23 14:24:34', 100001);
insert into meals (description, calories, date_time, user_id) values
                                                                     ('Ужин', 545, '2018-10-23 19:25:34', 100001);