-- auto-generated definition
create table room
(
    room_id       bigint      not null comment '房间id'
        primary key,
    home_id       bigint      null comment '家庭id',
    name          varchar(30) null comment '房间名称',
    display_order int         null comment '房间排序',
    create_time   datetime    null,
    update_time   datetime    null
)
    comment '房间表' collate = utf8mb4_unicode_ci;

create index home_id
    on room (home_id);



-- auto-generated definition
create table user_device
(
    id          bigint       not null comment 'deviceid或者groupid'
        primary key,
    type        int          not null comment '类型,设备或者分组',
    entity_type int          not null,
    name        varchar(50)  null comment '名称',
    icon        varchar(200) null comment '图标',
    icon_type   tinyint      null,
    room_id     bigint       null,
    home_id     bigint       null,
    room_order  int          null comment '设备在房间的排序',
    home_order  int          null,
    common      bit          null,
    create_time datetime     null,
    update_time datetime     null
)
    comment '用户设备' collate = utf8mb4_unicode_ci;

create index home_id
    on user_device (home_id);

