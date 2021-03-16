alter table product_order drop column price;
alter table orders add grand_total numeric(10,2) not null;
