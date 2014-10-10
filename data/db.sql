
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
	cpType varchar(255) comment "彩票类型",
	grouptype int comment "组合类型",
	times int comment "次数"
)comment "次数统计表";
