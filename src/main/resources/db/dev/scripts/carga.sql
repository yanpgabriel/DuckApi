INSERT INTO PUBLIC.TB_ROLE (ID, NAME) VALUES (1, 'USER_CREATE');
INSERT INTO PUBLIC.TB_ROLE (ID, NAME) VALUES (2, 'USER_LIST');

INSERT INTO PUBLIC.TB_PROFILE (ID, DTCREATION, NAME) VALUES (1, '2021-05-08 00:00:00.000000', 'Administrador');
INSERT INTO PUBLIC.TB_PROFILE_TB_ROLE (PROFILE_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO PUBLIC.TB_PROFILE_TB_ROLE (PROFILE_ID, ROLE_ID) VALUES (1, 2);

INSERT INTO PUBLIC.TB_PROFILE (ID, DTCREATION, NAME) VALUES (2, '2021-06-03 00:00:00.000000', 'Usuário');
INSERT INTO PUBLIC.TB_PROFILE_TB_ROLE (PROFILE_ID, ROLE_ID) VALUES (2, 2);

INSERT INTO PUBLIC.TB_USER (ID, DTCREATION, FULLNAME, EMAIL, PASSWORD, KEYCLOACKID, PROFILE_ID) 
    VALUES (0, '2021-05-08 00:00:01.000000', 'Tester', 'tester@mail.com', '$2a$12$2efhB7V/SSnAZm3brj.qaOCdNnqbWzV37YsqSF5gqxUtxMSgIQb8G', '00000000-0000-0000-0000-000000000000', 1);
-- password -> teste

INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESC, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'A Fazer', '2021-05-08 00:00:01.000000', 1);
INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESC, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'Fazendo', '2021-05-08 00:00:01.000000', 2);
INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESC, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'Pronto', '2021-05-08 00:00:01.000000', 3);
