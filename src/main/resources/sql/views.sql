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