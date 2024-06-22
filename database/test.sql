SELECT * FROM jblog.user;

insert into user values("h4123i","shin",password("1234"),curdate());

select * from blog a, category b, post c where a.id=b.id and b.no = c.category_no;
select b.no,b.title,b.contents,b.reg_date,b.category_no from category a, post b where a.no = b.category_no and a.id="hi" and a.no = 1 order by b.no desc;
select a.no,a.title,a.contents,a.reg_date,a.category_no from post a, category b where b.id = "hi" and b.no = a.category_no and b.no= 1 and a.no= 6;
select a.no,a.title,a.contents,a.reg_date,a.category_no from post a, category b where b.id = "hi" and b.no = a.category_no and b.no= 1 and a.no != 6 order by no desc;
select b.no,b.title,b.contents,b.reg_date,b.category_no from category a, post b where a.no = b.category_no and a.id="hi" and a.no = 5 order by b.no desc;

-- blog --
alter table blog change log logo varchar(200);
insert into blog values("hi","spring 이야기", "이미지경로");
SELECT * FROM blog;
update blog set title="안녕하세요", logo="" where id="hi";
select * from blog where id = "hi";
update blog set title="하위", logo="이미지경로" where id="123";

-- category --
insert into category values(null,"4","1",current_date(),"hi");
select * from category where id = "hi" order by no desc;
desc category;
select * from category;

-- post --
select * from post;
select * from post where category_no=4;
desc post;
alter table post modify no int not null auto_increment;
insert into post values(null,"타이틀4","글4",curdate(),5);
update post set category_no=5 where no=4;
update post set contents="스프링 캠프에서는 JVM(Java Virtual Machine) 기반 시스템의 백엔드(Back-end) 또는 서버사이드(Server-side)라고 칭하는 영역을 개발하는 애플리케이션 서버 개발에 관한 기술과 정보, 경험을 공유하는 컨퍼런스입니다.<br>
						 핵심주제로 Java와 Spring IO Platform을 다루고 있으며, 그외 Architecture나 JVM Language, Software Development Process 등 애플리케이션 서버 개발에 필요한 다양한 주제를 다루려고 노력하고 있습니다.<br>
						 우리는 같은 일을 하고, 같은 관심사를 가진 개발자들이지만 서로를 모릅니다.<br>
						 스프링 캠프라는 컨퍼런스에 찾아온 낯선 개발자들 사이에서 자신을 소개하고 이야기를 나누고 웃고 즐기며면서 어색함을 떨쳐내고 우리가 같은 분야에서 함께 일하고 있는 친구이자 동료라는 것을 인지하고 새로운 인연의 고리를 연결하고 이어갈 수 있는 순간으로 만들어가려 합니다." where no=4;