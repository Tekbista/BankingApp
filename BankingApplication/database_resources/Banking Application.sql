---------------------------------------- CREATE STATEMENTS --------------------------------------------------------------

-- users table
create table users(
	user_id int generated always as identity,
	first_name VARCHAR(50) not null,
	last_name VARCHAR(50) not null,
	username VARCHAR(50) not null,
	password VARCHAR(50) not null,
	role_id int not null,
	primary key (user_id),
	constraint fk_user_role
		foreign key(role_id)
		 references roles(role_id)
);


-- Role table
create table roles(
	role_id int generated always as identity,
	role VARCHAR(50) not null,
	primary key(role_id)
);



-- Account Type table
create table account_types(
	account_type_id int generated always as identity,
	account_type VARCHAR(50) not null,
	primary key(account_type_id)
	
);


-- Bank Account table
create table accounts(
	account_id int generated always as identity,
	account_number VARCHAR(10) not null,
	balance float8 not null,
	user_id int not null,
	account_status VARCHAR(50) not null,
	account_type_id int  not null,
	primary key(account_id),
	constraint fk_account_user
		foreign key(user_id)
		 references users(user_id),
	constraint fk_account_type
		foreign key(account_type_id)
		 references account_types(account_type_id)
);


-- Transection Type table
create table transaction_types(
	transaction_type_id int generated always as identity,
	transaction_type VARCHAR(50) not null,
	primary key(transaction_type_id)
	
);


-- Transection table
create table transactions(
	transaction_id int generated always as identity,
	account_id int not null,
	account_type_id int not null,
	transaction_amount float8 not null,
	transaction_date VARCHAR(50) not null,
	transaction_status VARCHAR(50) not null,
	transaction_type_id int not null,
	primary key(transaction_id),
	constraint fk_account_transaction
		foreign key(account_id)
		 references accounts(account_id),
	constraint fk_accounttype_transaction
		foreign key(account_type_id)
		 references account_types(account_type_id),
	constraint fk_transtype_transaction
		foreign key(transaction_type_id)
		 references transaction_types(transaction_type_id)
);



------------------------- INSERT STATEMENT---------------------------------------------------------------

insert  into roles("role") values('EMPLOYEE'),  ('CUSTOMER');

insert  into account_types(account_type) values('Checking'),  ('Saving'), ('Business');

insert  into transaction_types(transaction_type) values('Withdraw'),  ('Deposite'), ('Transfer');

insert into users(first_name, last_name, username, password, role_id) Values('Lila', 'Karki', 'atsibaleel', 'karki', 1);

-------------------------- SELECT STATEMENTS------------------------------------------------------------

select * from users;
select * from roles;
select * from accounts;
select * from account_types;
select * from transactions;
select * from transaction_types;



