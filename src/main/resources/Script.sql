create table users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NULL,
    phone      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL
);

create table account
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    balance  INT          NULL,
    id_users INT REFERENCES users (id)
);

create table type_transaction
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

create table transaction
(
    id                  SERIAL PRIMARY KEY,
    source_account      INT                         NULL REFERENCES account (id),
    target_account      INT                         NULL REFERENCES account (id),
    Create_date         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    id_type_transaction INT REFERENCES type_transaction (id),
    amount              INT                         NOT NULL
);

create table category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

create table id_tran_to_id_category
(
    id_transaction INT REFERENCES category (id),
    id_category    INT REFERENCES transaction (id)
);

insert into users (first_name, last_name, phone, email, password)
values ('Ivan', 'Dzhenenko', '+79855518076', 'i.dzhenenko@gmail.com', 'qwerty1234567890');
insert into users (first_name, last_name, phone, email, password)
values ('Olga', 'Dzhenenko', '+79093730888', 'olgaginger20.05@gmail.com', 'qwerty0987654321');

insert into account (name, balance, id_users)
values ('Ivan Wallet', '50000', '1');
insert into account (name, balance, id_users)
values ('Ivan Sberbank', '9000000', '1');
insert into account (name, balance, id_users)
values ('Olga Sberbank', '8000000', '2');

insert into type_transaction (name)
values ('Getting');
insert into type_transaction (name)
values ('Consumption');
insert into type_transaction (name)
values ('Money transfer');
insert into type_transaction (name)
values ('Consumption');
insert into type_transaction (name)
values ('Consumption');
insert into type_transaction (name)
values ('Consumption');


insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', NULL, '2019-07-07 3:12:12', '2', '120000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('3', '2', '2019-07-07 2:33:11', '3', '113000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values (NULL, '2', '2019-07-07 2:29:19', '1', '240000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', NULL, '2019-07-07 1:57:14', '2', '114000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', '3', '2019-07-07 3:59:59', '3', '174000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', '1', '2019-07-06 1:59:59', '2', '10000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('3', NULL, '2019-07-07 1:59:59', '2', '60000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', NULL, '2019-07-07 2:59:59', '2', '169000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', '1', '2019-07-07 22:59:59', '3', '286000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', '3', '2019-07-07 3:09:09', '3', '174000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', '3', '2019-09-04 3:09:09', '3', '333000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', '3', '2019-09-04 3:09:09', '3', '333000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', '3', '2019-09-04 3:09:09', '2', '333000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', NULL, '2019-09-04 3:09:09', '1', '93000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', NULL, '2019-09-04 3:09:09', '6', '93000');
insert into transaction (source_account, target_account, Create_date, id_type_transaction, amount)
values ('2', NULL, '2019-09-04 3:09:09', '5', '3000');


insert into category (name)
values ('Products');
insert into category (name)
values ('Transport');
insert into category (name)
values ('Gym');
insert into category (name)
values ('Rent apartment');
insert into category (name)
values ('Trip');
insert into category (name)
values ('Salary');

insert into id_tran_to_id_category (id_transaction, id_category)
values (1, 1);
insert into id_tran_to_id_category (id_transaction, id_category)
values (1, 2);
insert into id_tran_to_id_category (id_transaction, id_category)
values (3, 4);
insert into id_tran_to_id_category (id_transaction, id_category)
values (4, 5);
insert into id_tran_to_id_category (id_transaction, id_category)
values (5, 3);
insert into id_tran_to_id_category (id_transaction, id_category)
values (1, 6);

--Вывести все счета пользователя
select a.id,
       a.name,
       u.first_name,
       u.last_name
from account as a
         join users u on a.id_users = u.id
where u.first_name = 'Ivan';

--Вывести сумму всех остатков на счетах по всем пользователям
select sum(balance)
from account;
--Вывести все транзакции пользователя за вчера

select a.id,
       u.first_name,
       u.last_name,
       tt.name,
       t.Create_date,
       t.amount
from account as a
         join users u on a.id_users = u.id
         left join transaction t on a.id = t.source_account
         join type_transaction tt on t.id_type_transaction = tt.id
where u.first_name = 'Ivan'
  and date_trunc('day', t.Create_date) = date_trunc('day', current_timestamp - interval '1 day');

update users
set password = md5(password);

select c.name,
       t.amount
from account as a
         join users u on a.id_users = u.id
         left join transaction t on a.id = t.source_account
         join type_transaction tt on t.id_type_transaction = tt.id
         join category c on t.id_type_transaction = c.id
where u.first_name = 'Ivan'
  and date_trunc('day', t.Create_date) = date_trunc('day', current_timestamp - interval '1 day');