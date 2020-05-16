create table mission
(
    mid       varchar(255) not null comment '任务id'
        primary key,
    title     varchar(255) null comment '任务标题',
    `desc`    text         null comment '任务描述',
    post_time datetime     null on update CURRENT_TIMESTAMP comment '任务发布时间',
    end_time  datetime     null on update CURRENT_TIMESTAMP comment '结束时间',
    state     int          null comment '任务状态【0为未接收，1为接收，2为已完成】',
    money     double       null comment '酬金',
    label1    varchar(255) null comment '标签1',
    label2    varchar(255) null comment '标签2',
    label3    varchar(255) null comment '标签3',
    comment   text         null comment '发布任务的人对任务完成情况进行评价'
);

create table user
(
    phone  varchar(255) not null comment '用户电话，也是主键'
        primary key,
    pwd    varchar(255) null comment '用户密码',
    icon   varchar(255) null comment '用户头像地址',
    motto  varchar(255) null comment '用户座右铭',
    score  double       null comment '用户完成任务获得的积分',
    credit double       null comment '用户的信用积分'
);

create table post
(
    phone varchar(255) not null,
    mid   varchar(255) not null,
    primary key (phone, mid),
    constraint mid
        foreign key (mid) references mission (mid)
            on update cascade on delete cascade,
    constraint phone
        foreign key (phone) references user (phone)
            on update cascade on delete cascade
);

create table rece
(
    phone varchar(255) not null,
    mid   varchar(255) not null,
    primary key (phone, mid),
    constraint _mid
        foreign key (mid) references mission (mid)
            on update cascade on delete cascade,
    constraint _phone
        foreign key (phone) references user (phone)
            on update cascade on delete cascade
);