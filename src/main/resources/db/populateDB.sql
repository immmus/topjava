DELETE FROM user_roles;
DELETE FROM users;
delete from meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

insert into meals (id, description, calories, date_time, user_id) values
                                                                        (1, 'Завтрак', 500,'2018-10-22 13:23:34', 100000);
insert into meals (id, description, calories, date_time, user_id) values
                                                                        (2, 'Обед', 503, '2018-10-22 13:24:34', 100000);
insert into meals (id, description, calories, date_time, user_id) values
                                                                        (3, 'ужин', 545, '2018-10-22 13:25:34', 100000);
insert into meals (id, description, calories, date_time, user_id) values
                                                                        (4, 'Завтрак', 500, '2018-10-23 13:23:34', 100001);
insert into meals (id, description, calories, date_time, user_id) values
                                                                        (5, 'Обед', 1003, '2018-10-23 13:24:34', 100001);
insert into meals (id, description, calories, date_time, user_id) values
                                                                        (6, 'ужин', 545, '2018-10-23 13:25:34', 100001);