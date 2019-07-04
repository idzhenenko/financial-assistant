create table users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NULL,
    phone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

create table account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL ,
    balance INT NULL ,
    id_users INT REFERENCES users (id)
);

create table type_transaction (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

create table transaction (
    id SERIAL PRIMARY KEY,
    source_account INT NULL REFERENCES account(id),
    target_account INT NULL REFERENCES account(id),
    date date NOT NULL ,
    id_type_transaction INT REFERENCES type_transaction(id),
    amount INT NOT NULL
);

create table category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

create table id_tran_to_id_category (
    id_transaction INT REFERENCES category(id),
    id_category INT REFERENCES transaction(id)
);

insert into users (first_name, last_name, phone, email, password) values ('Иван','Джененко','+79855518076','i.dzhenenko@gmail.com','qwerty1234567890');
insert into users (first_name, last_name, phone, email, password) values ('Ольга','Джененко','+79093730888','olgaginger20.05@gmail.com','qwerty0987654321');

insert into account (name, balance, id_users) values ('Иван Wallet','50000','1');
insert into account (name, balance, id_users) values ('Иван Sberbank','9000000','1');
insert into account (name, balance, id_users) values ('Ольга Sberbank','8000000','2');

insert into type_transaction (name) values ('Приход');
insert into type_transaction (name) values ('Расход');
insert into type_transaction (name) values ('Перевод');

insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('2',NULL,'2019-06-30','2','10000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('3','2','2019-07-03','3','11000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values (NULL,'2','2019-07-03','1','20000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('2',NULL,'2019-07-03','2','14000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('2','3','2019-07-03','3','174000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('2','1','2019-07-02','2','1000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('3',NULL,'2019-07-03','2','6000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('2',NULL,'2019-07-03','2','16000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('2','1','2019-07-03','3','86000');
insert into transaction (source_account, target_account, date, id_type_transaction, amount) values ('2','3','2019-07-03','3','174000');


insert into category (name) values ('Продукты');
insert into category (name) values ('Транспорт');
insert into category (name) values ('Тренажерный зал');
insert into category (name) values ('Аренда квартиры');
insert into category (name) values ('На путешевствия');
insert into category (name) values ('Зарплата');

insert into id_tran_to_id_category (id_transaction, id_category) values (1,1);
insert into id_tran_to_id_category (id_transaction, id_category) values (1,2);
insert into id_tran_to_id_category (id_transaction, id_category) values (3,4);
insert into id_tran_to_id_category (id_transaction, id_category) values (4,5);
insert into id_tran_to_id_category (id_transaction, id_category) values (5,3);
insert into id_tran_to_id_category (id_transaction, id_category) values (1,6);

--Вывести все счета пользователя

select a.id,
       a.name,
       u.first_name,
       u.last_name
from account as a
    join users u on a.id_users = u.id
where u.first_name = 'Иван';

--Вывести сумму всех остатков на счетах по всем пользователям

select sum(balance) from account;

--Вывести все транзакции пользователя за вчера

select a.id,
       u.first_name,
       u.last_name,
       tt.name,
       t.date,
       t.amount
from account as a
        join users u on a.id_users = u.id
        left join transaction t on a.id = t.source_account
        join type_transaction tt on t.id_type_transaction = tt.id
where u.first_name = 'Иван' and t.date = current_date - 1;