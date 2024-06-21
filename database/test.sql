SELECT * FROM jblog.user;

insert into user values("h4123i","shin",password("1234"),curdate());

select * from blog a, category b, post c where a.id=b.id and b.no = c.category_no;

-- blog --
alter table blog change log logo varchar(200);
insert into blog values("hi","spring 이야기", "이미지경로");
SELECT * FROM blog;
update blog set title="안녕하세요", logo="" where id="hi";
select * from blog where id = "hi";

-- category --
insert into category values(null,"4","1",current_date(),"hi");
select * from category where id = "hi";
desc category;
select * from category;

-- post --
select * from post;
select * from post where category_no=1 and no=2;
desc post;
alter table post modify no int not null auto_increment;
insert into post values(null,"타이틀4","글4",curdate(),1);
