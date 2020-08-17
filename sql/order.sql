create table c_order
(
    id           char(32)         not null,
    order_no     varchar(50)      not null comment '订单号',
    order_status char default '0' not null comment '0 创建 1 完成 2 删除',
    finish_date  datetime         null comment '完成日期',
    delete_date  datetime         null comment '删除日期',
    create_date  datetime         not null,
    create_by    char(32)         not null,
    update_date  datetime         not null,
    update_by    char(32)         not null,
    amount       double           not null,
    constraint order_id_uindex
        unique (id)
)
    comment '订单';
alter table c_order
    add primary key (id);

create table c_order_detail
(
    id          char(32)    not null,
    order_id    char(32)    null comment '订单id',
    good_id     char(32)    not null comment '商品ID',
    good_name   varchar(50) not null comment '商品名称',
    price       double      not null comment '商品价格',
    quantity    int         not null comment '商品数量',
    amount      double      null comment '总金额',
    create_date datetime    not null,
    create_by   char(32)    not null,
    update_date datetime    not null,
    update_by   char(32)    null,
    constraint order_detail_id_uindex
        unique (id),
    constraint c_order_detail_c_order_id_fk
        foreign key (order_id) references c_order (id)
            on update cascade on delete cascade
)
    comment '订单明细';
alter table c_order_detail
    add primary key (id);
