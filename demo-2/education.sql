drop database if exists education;
create database education;
use education;

create table type(
   id int(11) not null primary key auto_increment,
   name varchar(20) not null unique key
);

insert into type(name) values('教学老师');
insert into type(name) values('教务老师');

create table collage(
   id int(11) not null primary key auto_increment,
   name varchar(20) not null unique key
);
insert into collage(name) values('计算机学院');
insert into collage(name) values('外国语学院');

create table major(
   id int(11) not null primary key auto_increment,
   name varchar(20) not null unique key,
   cid int(11) not null,
   FOREIGN key(cid) REFERENCES collage(id)
);
insert into major(name,cid) values('计算结科学与技术',1);
insert into major(name,cid) values('软件工程',1);
insert into major(name,cid) values('计物联网',1);
insert into major(name,cid) values('信息管理与信息系统',1);

create table teacher(
   id int(11) not null primary key,
   name varchar(20) not null,
   sex varchar(4) not null,
   address varchar(50) not null,
   phone varchar(11) not null,
   password varchar(20) not null,
   cid int(11) not null,
   tid int(11) not null,
   FOREIGN key(cid) REFERENCES collage(id),
   FOREIGN key(tid) REFERENCES type(id)
);

create table classes(
   id int(11) not null primary key,
   name varchar(20) not null unique key,
   mid int(11) not null,
   FOREIGN key(mid) REFERENCES major(id)
);
insert into classess values(20181171,'18信管01',4);

create table student(
   id int(11) not null primary key,
   name varchar(20) not null,
   sex varchar(4) not null,
   address varchar(50) not null,
   phone varchar(11) not null,
   password varchar(20) not null,
   cid int(11) not null,
   FOREIGN key(cid) REFERENCES classes(id)
);
-- 教学楼
create table building(
	id int(11) not null primary key,
    name varchar(20) not null unique key,
);
insert into building values(1,'教学楼');
insert into building values(2,'计算机实验楼');
insert into building values(3,'生物实验楼');

-- 教室
create table room(
    id int(11) not null primary key,
	num int(11) not null,
	bid int(11) not NULL
	FOREIGN key(bid) REFERENCES building(id);
);
insert into room values(1101,240,1);
insert into room values(2101,80,2);

create table subject(
	id int(11) not null primary key,
    name varchar(20) not null unique key
)

create table teachplan(
    id int(11) not null primary key auto_increment,
	teachyear varchar(20) not null,
	teachtime varchar(20) not null,
	sid int(11) not null,
	tid int(11) not null,
	cid int(11) not null,
	rid int(11) not null,
	weeks int(11) not null,
	lession int(11) not null
)
insert into teachorder(teachyear,teachtime,sid,tid,cid,rid weeks,lession) values('18/19学年','第一学期',1,20171702,20181171,1101,16,4)

create table teachorder(
  id int(11) not null primary key auto_increment,
  tid int(11) not null,
  weekday varchar(20) not null,
  timeorder varchar(20) not null,
  FOREIGN key(tid) REFERENCES teachplan(id);
)
insert into teachorder values(1,'星期一','7:50-9:30');
insert into teachorder values(1,'星期三','14:00-15:40');

-- 成绩信息   教学评价    考试安排
