use haetssal_jangteo;

select * from tbl_market;

select * from tbl_store;

select * from tbl_category;

select * from tbl_sub_category;

select * from tbl_item;

select * from tbl_item_option;

select * from tbl_file;

select * from tbl_review;

select * from tbl_file_review;

select * from tbl_file_item;

select * from tbl_file_user;

select * from vw_file_user;

delete from tbl_file
where file_type = 'image';

select * from tbl_user;

select * from tbl_auth;

select * from tbl_seller;

select * from tbl_delivery;

select * from tbl_payment;

INSERT INTO tbl_payment (id, user_id, item_id, payment_state)
VALUES
    (1, 1, 1, 'complete'),
    (2, 1, 2, 'complete'),
    (3, 1, 3, 'complete');

# 확인용 유저 샘플
insert into tbl_user (id, user_email, user_phone, user_name, user_intro)
values (4,'example6@example.com', '01066666666', '홍길동8', '설명6');


select id from tbl_store;

select count(*) from tbl_item
where item_store_id = 8;

# insert into tbl_market (
#     market_region, market_name, market_location)
# values ('서울','가락시장','송파구');

insert into tbl_store (
    store_owner_id, store_market_id, store_name, store_intro, store_address)
values ((select max(id) from tbl_user),
           (select max(id) from tbl_market),'테스트상점','소개','주소');


select id from tbl_store;

insert into tbl_item (item_store_id, item_category_id, item_name, item_stock, item_price, item_content)
values ((select max(id) from tbl_store),1,'사과박스',10,15000,'테스트');

insert into tbl_category (id, category_name)
values (1, '과일');

select id from tbl_category;

insert into tbl_category (id, category_name)
values (400, '반찬.장류'),
       (500,'가공식품'),
    (600, '건강식품'),
    (700, '생활용품'),
    (800, '주방용품'),
    (900, '가전');

select
    i.id,
    i.item_store_id,
    i.item_category_id,
    i.item_subcategory_id,
    i.item_name,
    i.item_price,
    i.item_content,
    i.created_datetime,

    c.category_name as itemCategoryName,
    sc.category_name as itemSubCategoryName
from tbl_item i
left join tbl_category c on i.item_category_id = c.id
left join tbl_sub_category sc on i.item_subcategory_id = sc.id
where i.item_store_id = 2;
