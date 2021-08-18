-- keep lines order
delete from link_subcategory_characteristic  where subcategory_id > 10000;
delete from characteristic  where id > 10000;
delete from sub_product_category  where id > 10000;
delete from product_in_cart  where id > 10000;
delete from product_in_order where product_id > 10000;
delete from cart  where id > 10000;
delete from product where id > 10000;
delete from user_role where user_id > 10000;
delete from user_order where user_id > 10000;
delete from usr  where id > 10000;
delete from product_category  where id > 10000;
