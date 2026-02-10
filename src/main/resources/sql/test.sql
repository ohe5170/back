use haetssal_jangteo;

select * from tbl_market;

select * from tbl_store;

select * from tbl_category;

select * from tbl_sub_category;

select * from tbl_item;

select * from tbl_item_option;

select * from tbl_file;

delete from tbl_file
where file_type = 'image';

select * from tbl_file_item;

select * from tbl_user;

# 확인용 유저 샘플
insert into tbl_user (id, user_email, user_phone, user_name, user_intro)
values (1, 'example@example.com', '01012345678', '홍길동', '설명1');