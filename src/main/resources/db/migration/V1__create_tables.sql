create table if not exists public.categories
(
    category_id   serial       not null,
    category_name varchar(255) not null,
    slug          varchar      not null,
    constraint categories_pk
        primary key (category_id)
);

create table if not exists public.order_status
(
    order_status_id serial       not null,
    status_name     varchar(255) not null,
    constraint order_status_pk
        primary key (order_status_id)
);

create table if not exists public.brands
(
    brand_id   serial       not null,
    brand_name varchar(255) not null,
    constraint brands_pk
        primary key (brand_id)
);

create table if not exists public.roles
(
    role_id   serial       not null,
    role_name varchar(255) not null,
    constraint users_role_pk
        primary key (role_id)
);

create table if not exists public.users
(
    user_id      bigserial    not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    email        varchar(255) not null,
    address      varchar(255) not null,
    phone_number varchar(15),
    city         varchar(255) not null,
    created_at   timestamp    not null,
    updated_at   timestamp,
    password     varchar      not null,
    last_login   timestamp,
    role_id      integer      not null,
    active       boolean      not null,
    constraint clients_pk
        primary key (user_id),
    constraint users_roles_role_id_fk
        foreign key (role_id) references public.roles
);

create table if not exists public.products
(
    product_id   serial         not null,
    product_name varchar(255)   not null,
    slug         varchar        not null,
    quantity     integer        not null,
    price        numeric(10, 2) not null,
    description  varchar(2048)  not null,
    thumbnail    varchar(255)   not null,
    category_id  integer        not null,
    brand_id     integer        not null,
    updated_by   integer,
    created_at   timestamp      not null,
    updated_at   timestamp,
    ean_code     varchar(255)   not null,
    constraint products_pk
        primary key (product_id),
    constraint products_categories_category_id_fk
        foreign key (category_id) references public.categories,
    constraint products_brands_brand_id_fk
        foreign key (brand_id) references public.brands,
    constraint products_users_user_id_fk
        foreign key (updated_by) references public.users
);

create table if not exists public.orders
(
    order_id        bigserial not null,
    order_note      varchar(512),
    client_id       integer   not null,
    order_status_id integer   not null,
    employee_id     integer   not null,
    created_at      timestamp not null,
    updated_at      timestamp,
    constraint orders_pk
        primary key (order_id),
    constraint orders_order_status_order_status_id_fk
        foreign key (order_status_id) references public.order_status,
    constraint orders_users_user_id_fk
        foreign key (client_id) references public.users,
    constraint orders_users_user_id_fk_2
        foreign key (employee_id) references public.users
);

create table if not exists public.product_order
(
    id         bigserial      not null,
    price      numeric(10, 2) not null,
    quantity   integer        not null,
    product_id integer        not null,
    order_id   integer        not null,
    constraint product_order_pk
        primary key (id),
    constraint product_order_products_product_id_fk
        foreign key (product_id) references public.products,
    constraint product_order_orders_order_id_fk
        foreign key (order_id) references public.orders
);