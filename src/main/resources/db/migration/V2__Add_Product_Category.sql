insert into product_category (created, updated, name, thumbnail)
values (now(),now(),'компьютеры','ComputerIcon'),
       (now(),now(),'бытовая техника','TvIcon'),
       (now(),now(),'мужская одежда','AccessibilityIcon;'),
       (now(),now(),'женская одежда','PregnantWomanIcon'),
       (now(),now(),'детская одежда','ChildCareIcon'),
       (now(),now(),'декор','LocalFloristIcon'),
       (now(),now(),'мебель','DeckIcon'),
       (now(),now(),'хозяйственные товары','EmojiFoodBeverageIcon'),
       (now(),now(),'продовольственные товары','FastfoodIcon');

insert into sub_product_category(created, updated, name, product_category_id)
values (now(), now(), 'ноутбуки', 1),
       (now(), now(), 'мониторы', 1),
       (now(), now(), 'офисные', 1),
       (now(), now(), 'микроволновки', 2),
       (now(), now(), 'телевизоры', 2),
       (now(), now(), 'пиджаки', 3),
       (now(), now(), 'брюки', 3),
       (now(), now(), 'платья', 4),
       (now(), now(), 'сарафаны', 4),
       (now(), now(), 'комбинезоны', 5),
       (now(), now(), 'чепчики', 5),
       (now(), now(), 'картины', 6),
       (now(), now(), 'вазы', 6),
       (now(), now(), 'шкафы', 7),
       (now(), now(), 'столы', 7),
       (now(), now(), 'верёвки', 8),
       (now(), now(), 'мыло', 8),
       (now(), now(), 'хлеб', 9),
       (now(), now(), 'сыр', 9);

insert into characteristic (created, updated, name)
values (now(),now(),'оперативная память'),
       (now(),now(),'процессор'),
       (now(),now(),'размер'),
       (now(),now(),'энергопотребление'),
       (now(),now(),'цвет'),
       (now(),now(),'длина'),
       (now(),now(),'ширина'),
       (now(),now(),'высота'),
       (now(),now(),'материал'),
       (now(),now(),'вес'),
       (now(),now(),'срок годности'),
       (now(),now(),'энергетическая ценность');

insert into link_subcategory_characteristic (subcategory_id, characteristic_id)
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6),
       (4, 7),
       (4, 8),
       (5, 9),
       (5, 11),
       (6, 11),
       (6, 12),
       (7, 3),
       (7, 4),
       (8, 5),
       (8, 6),
       (9, 7),
       (9, 8),
       (10, 9),
       (10, 11),
       (11, 10),
       (11, 12),
       (12, 1),
       (12, 2),
       (13, 3),
       (13, 4),
       (14, 5),
       (14, 6),
       (15, 7),
       (15, 8),
       (16, 9),
       (16, 10),
       (17, 11),
       (17, 1),
       (18, 2),
       (18, 3),
       (19, 4),
       (19, 5);

