use haetssal_jangteo;

select * from tbl_market;

select * from tbl_store;

select * from tbl_user;

# 확인용 유저 샘플
insert into tbl_user (id, user_email, user_phone, user_name, user_intro)
values (1, 'example@example.com', '01012345678', '홍길동', '설명1');