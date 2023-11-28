-- 1. Authority 기본 세팅
insert into authority (authority_name, enabled, created_date, updated_date) values ('ROLE_ADMIN', true, dateadd('day', -365, current_date), CURRENT_TIMESTAMP);
insert into authority (authority_name, enabled, created_date, updated_date) values ('ROLE_GENERAL', true, dateadd('day', -365, current_date), CURRENT_TIMESTAMP);
insert into authority (authority_name, enabled, created_date, updated_date) values ('ROLE_ADULT', true, dateadd('day', -365, current_date), CURRENT_TIMESTAMP);
insert into authority (authority_name, enabled, created_date, updated_date) values ('ROLE_COMPANY', true, dateadd('day', -365, current_date), CURRENT_TIMESTAMP);

-- 2. 사용자 및 사용자 권한 세팅
insert into users (user_name, user_nickname, user_email, password, gender, address, birth_year, birth_month, birth_day, enabled, created_date, updated_date) values ('김바덕', '김바덕', 'kimbaduck@baduck.com', '$2a$10$Rlby6NOo2JD.u.brWmuuy.Kp9NIQGYtzQ6lIFyH6nWvoEJC5hZ0.m', 'M', '서울특별시 서초구 서초대로 123-12', 2000, 9, 10, true, dateadd('day', -364, current_date), CURRENT_TIMESTAMP);
insert into user_authority (user_id, auth_id, enabled, created_date, updated_date) values (1, 1, true, dateadd('day', -364, current_date), CURRENT_TIMESTAMP);
insert into user_authority (user_id, auth_id, enabled, created_date, updated_date) values (1, 2, true, dateadd('day', -364, current_date), CURRENT_TIMESTAMP);
insert into user_authority (user_id, auth_id, enabled, created_date, updated_date) values (1, 3, true, dateadd('day', -364, current_date), CURRENT_TIMESTAMP);