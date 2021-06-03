alter table product
	add status varchar(40);

create table product_image(
	product_id bigint not null constraint product_image_product_id references product,
	image_id bigint
);
