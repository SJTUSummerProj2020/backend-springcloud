create table c_good
(
    id          char(32)    not null,
    good_name   varchar(50) null,
    good_stock  int         null comment '库存',
    good_price  double      not null comment '价格',
    create_date datetime    not null,
    create_by   char(32)    not null,
    update_date datetime    not null,
    update_by   char(32)    not null,
    constraint good_id_uindex
        unique (id)
)
    comment '商品';
alter table c_good
    add primary key (id);
