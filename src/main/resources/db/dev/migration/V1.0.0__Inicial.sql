create sequence USER_SEQ
    increment by 50;

create table TB_USER
(
    ID         BIGINT       not null,
    DTCREATION TIMESTAMP    not null,
    IDTOKEN    VARCHAR(255) not null
        constraint UK_LGCYPODRHBT920F2WBMJANLR3
            unique
);

