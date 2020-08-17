create table if not exists userEntity
(
    id          char(32)    not null,
    user_name   varchar(20) not null comment '用户名',
    nick_name   varchar(20) not null comment '用户昵称',
    user_pswd   varchar(50) not null comment '用户密码',
    create_date datetime    not null comment '创建日期',
    create_by   char(32)    not null comment '创建人',
    update_date datetime    not null comment '更新日期',
    update_by   char(32)    not null comment '更新人',
    constraint user_id_uindex
        unique (id)
)
    comment '用户表';

alter table userEntity
    add primary key (id);
