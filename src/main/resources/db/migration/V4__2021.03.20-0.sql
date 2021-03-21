alter table orders rename column created_at to created_date;
alter table orders rename column updated_at to last_modified_date;

alter table products drop column created_at;
alter table products add created_date timestamp;
alter table products drop column updated_at;
alter table products add last_modified_date timestamp;

alter table products drop constraint products_users_user_id_fk;
alter table products drop column updated_by;

alter table products add created_by bigint;
alter table products
    add constraint products_users_user_id_fk
        foreign key (created_by) references users;
alter table products add last_modified_by bigint;
alter table products
    add constraint products_users_user_id_fk_2
        foreign key (last_modified_by) references users;

alter table users rename column created_at to created_date;
alter table users rename column updated_at to last_modified_date;
alter table users drop column last_login;