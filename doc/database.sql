create database if not exists employees collate utf8mb4_0900_ai_ci;
use employees;

create table users (
    id varchar(20) not null primary key
) engine=InnoDb;

create table modules (
    name varchar(20) not null primary key
) engine=InnoDb;

create table user_modules (
    id varchar(36) not null primary key,
    user_id varchar(20) not null,
    module_name varchar(20) not null,
    module_order int not null,
    constraint foreign key (user_id) references users(id) on update cascade on delete cascade,
    constraint foreign key (module_name) references modules(name) on update cascade on delete cascade
) engine=InnoDb;

INSERT INTO employees.users (id) VALUES ('UserA');
INSERT INTO employees.users (id) VALUES ('UserB');
INSERT INTO employees.users (id) VALUES ('UserC');
INSERT INTO employees.users (id) VALUES ('UserD');

INSERT INTO employees.modules (name) VALUES ('PromoCard');
INSERT INTO employees.modules (name) VALUES ('CategoryCard');
INSERT INTO employees.modules (name) VALUES ('FlashSaleCard');
INSERT INTO employees.modules (name) VALUES ('HistoryCard');
INSERT INTO employees.modules (name) VALUES ('NewsCard');

INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('e037c678-7b32-49b2-8c0e-f05cf810c1e6', 1, 'PromoCard', 'UserA');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('c48558d0-b49d-4c95-8600-6c74534096af', 2, 'CategoryCard', 'UserA');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('b06a3b56-79a4-431c-aa3b-9c68593aa1bc', 3, 'FlashSaleCard', 'UserA');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('ba154e28-6b64-4de1-8863-d3cfc1652f72', 4, 'HistoryCard', 'UserA');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('c7d2bd42-52ac-435a-8753-0c4e8c79140d', 5, 'NewsCard', 'UserA');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('d5c6bd17-881f-4aa6-b713-d7f19579b6b3', 1, 'PromoCard', 'UserB');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('c8c70c0f-95bf-4d1f-b00c-d5a52a03b653', 2, 'NewsCard', 'UserB');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('2733b3fe-af6f-444c-8bdf-f0bc4c64c579', 3, 'FlashSaleCard', 'UserB');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('01f57e55-9832-4fd7-85e1-f605f7046478', 4, 'CategoryCard', 'UserB');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('c4132310-aa1b-48ca-af70-e19ddcdbc51d', 5, 'NewsCard', 'UserB');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('d2e532a7-7d6d-4612-903b-d6960ca99456', 1, 'PromoCard', 'UserC');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('89296bd1-0047-473a-8964-cb11d74d1e9d', 2, 'FlashSaleCard', 'UserC');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('c74f1f35-13c7-4b82-a201-0bf79dc4fe23', 3, 'CategoryCard', 'UserC');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('cdcb5c37-b924-4dc6-aa06-284cf0344c35', 4, 'NewsCard', 'UserC');
INSERT INTO employees.user_modules (id, module_order, module_name, user_id) VALUES ('2a92d921-6020-4cf8-bf1d-289b141b308d', 5, 'HistoryCard', 'UserC');
