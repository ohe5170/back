use haetssal_jangteo;

select * from tbl_market;

select * from tbl_store;

select * from tbl_category;

select * from tbl_sub_category;

select * from tbl_item;

select * from tbl_item_option;

select * from tbl_file;

select * from tbl_file_item;

select * from tbl_file_user;

delete from tbl_file
where file_type = 'image';

select * from tbl_user;

select * from tbl_auth;

select * from tbl_seller;

# 확인용 유저 샘플
insert into tbl_user (id, user_email, user_phone, user_name, user_intro)
values (1, 'example@example.com', '01012345678', '홍길동', '설명1');


select id from tbl_store;

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
values (1, '과일');

# insert into tbl_market (id, market_name, market_region, market_location, market_state)
# values (1, '가락시장', '서울', '가락동', 'active');

