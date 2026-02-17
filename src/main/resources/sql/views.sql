use haetssal_jangteo;

# 상품 이미지를 조회하는 view
create view vw_file_item as
select
    f.id,
    f.file_type,
    f.file_name,
    f.file_origin_name,
    f.file_saved_path,
    f.file_size,
    f.created_datetime,
    fi.item_id,
    fi.file_item_type
from tbl_file_item fi
join tbl_file f on fi.id = f.id;

# 유저 프로필을 조회하는 view
create view vw_file_user as
select
    f.id,
    f.file_type,
    f.file_name,
    f.file_origin_name,
    f.file_saved_path,
    f.file_size,
    f.created_datetime,
    fu.user_id
from tbl_file_user fu
join tbl_file f on fu.id = f.id;

# 가게 프로필을 조회하는 view
create view vw_file_store as
select
    f.id,
    f.file_type,
    f.file_name,
    f.file_origin_name,
    f.file_saved_path,
    f.file_size,
    f.created_datetime,
    fs.store_id
from tbl_file_store fs
join tbl_file f on fs.id = f.id;

# 상품의 세부 정보를 조회하는 뷰
create view vw_item_detail as
select
    i.id,
    i.item_name,
    i.item_price,
    i.item_stock,
    i.item_delivery_fee,
    i.item_content,
    i.item_view_count,

    c.id as itemCategoryId,
    c.category_name as itemCategoryName,
    sc.id as itemSubCategoryId,
    sc.category_name as itemSubCategoryName,

    s.id as storeId,
    s.store_name,
    s.store_score,

    u.user_latest_login as ownerLatestLogin,

    f.file_saved_path as storeProfilePath,
    f.file_name as storeProfileName,
    f.file_origin_name as storeProfileOriginName,

    (select count(*)
    from tbl_item i2
    where i2.item_store_id = s.id
    and i2.item_state = 'active') as storeItemCount
from tbl_item i
inner join tbl_category c on i.item_category_id = c.id
left join tbl_sub_category sc on i.item_subcategory_id = sc.id
inner join tbl_store s on i.item_store_id = s.id
inner join tbl_user u on s.store_owner_id = u.id
left join tbl_file_store fs on fs.store_id = s.id
left join tbl_file f on fs.id = f.id;

drop view vw_item_detail;
