
drop database ssc_db; 
create database ssc_db character set utf8 default character set utf8 collate utf8_general_ci default collate utf8_general_ci;

use ssc_db;

create table t_cq_stage
(
    id varchar(255) comment "期号",
    stagetime bigint comment "时间",
    stagenumber varchar(255) comment '开奖号码',
    constraint t_jx_stage_pk primary key (id)
) comment '重庆时时彩期';


create table t_jx_stage
(
    id varchar(255) comment "期号",
    stagetime bigint comment "时间",
    stagenumber varchar(255) comment '开奖号码',
    constraint t_jx_stage_pk primary key (id)
) comment '江西时时彩期';


create table t_current
(
	id int comment "id",
	stageId varchar(255) comment "期号",
	stageTime bigint comment "时间",
	bb int comment "大大",
	bs int comment "大小",
	sb int comment "小大",
	ss int comment "小小",
	constraint t_current_pk primary key (id)
)comment "当前表";



create table t_history
(
	id int comment "id",
	bb int comment "大大",
	bs int comment "大小",
	sb int comment "小大",
	ss int comment "小小",
	constraint t_history_pk primary key (id)
)comment "历史表";



create table t_count_time
(
	cpType int comment "彩票类型",
	grouptype int comment "组合类型",
	times int comment "次数"
)comment "次数统计表";

drop table t_cp_courrent;
create table t_cp_courrent
(
	id bigint auto_increment,
	userid bigint comment "购买者编号",
	money bigint comment "购买金额",
	buyTime bigint comment "购买时间",
	stageNum varchar(255) comment "期号",
	cpType int comment "彩票类型",
	groupType int comment "组合类型",
	content varchar(255) comment "内容",
	constraint t_cp_courrent_pk primary key (id)
)comment "彩票当前表";


drop table t_cp_history;
create table t_cp_history
(
	id bigint auto_increment,
	userid bigint comment "购买者编号",
	money bigint comment "购买金额",
	buyTime bigint comment "购买时间",
	stageNum varchar(255) comment "期号",
	cpType int comment "彩票类型",
	groupType int comment "组合类型",
	content varchar(255) comment "内容",
	constraint t_cp_history_pk primary key (id)
)comment "彩票历史表";


create table t_user
(
	id bigint comment "id",
	username varchar(255) comment "用户名",
	money bigint comment "金额",
	constraint t_user_pk primary key (id)
)comment "用户表";




