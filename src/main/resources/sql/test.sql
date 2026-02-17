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

select * from tbl_file_store;

delete from tbl_file
where file_type = 'image';

select * from tbl_user;

select * from tbl_auth;

select * from tbl_file_item
where item_id = 9;


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

insert into tbl_item (item_store_id, item_category_id, item_subcategory_id, item_name, item_stock, item_price, item_content)
value (4, 100, 101, 'test', '10', '10000', '상품 소개');

insert into tbl_category (id, category_name)
values (1, '과일');

select id from tbl_category;

insert into tbl_category (id, category_name)
values (1, '과일');

# insert into tbl_market (id, market_name, market_region, market_location, market_state)
# values (1, '가락시장', '서울', '가락동', 'active');

select
    i.id,
    i.item_name,
    i.item_price,
    i.item_stock,
    i.item_delivery_fee,
    i.item_content,
    i.item_view_count,

    c.category_name as itemCategoryName,
    sc.category_name as itemSubCategoryName,

    s.store_name,
    s.store_score,

    u.user_latest_login as ownerLatestLogin,

    f.file_saved_path as storeProfilePath,
    f.file_name as storeProfileName,
    f.file_origin_name as storeProfileOriginName
from tbl_item i
         inner join tbl_category c on i.item_category_id = c.id
         left join tbl_sub_category sc on i.item_subcategory_id = sc.id
         inner join tbl_store s on i.item_store_id = s.id
         inner join tbl_user u on s.store_owner_id = u.id
         left join tbl_file_store fs on fs.store_id = s.id
         left join tbl_file f on fs.id = f.id
where i.id = 7;