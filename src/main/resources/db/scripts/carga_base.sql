INSERT INTO PUBLIC.TB_ROLE (ID, NAME) VALUES (nextval('role_seq'), 'DUCK_ADM');

INSERT INTO PUBLIC.TB_PROFILE (ID, DTCREATION, NAME) VALUES (nextval('profile_seq'), '2021-05-08 00:00:00.000000', 'Administrador');
INSERT INTO PUBLIC.TB_PROFILE_TB_ROLE (PROFILE_ID, ROLE_ID) VALUES (1, 1);

INSERT INTO PUBLIC.TB_PROFILE (ID, DTCREATION, NAME) VALUES (nextval('profile_seq'), '2021-06-03 00:00:00.000000', 'Usuário');

INSERT INTO PUBLIC.TB_USER (ID, DTCREATION, FULLNAME, EMAIL, PASSWORD, PROFILE_ID) VALUES (nextval('user_seq'), '2021-05-08 00:00:01.000000', 'Tester Admin', 'testeradmin@mail.com', '$2a$12$2efhB7V/SSnAZm3brj.qaOCdNnqbWzV37YsqSF5gqxUtxMSgIQb8G', 1);
INSERT INTO PUBLIC.TB_USER (ID, DTCREATION, FULLNAME, EMAIL, PASSWORD, PROFILE_ID) VALUES (nextval('user_seq'), '2021-05-08 00:00:01.000000', 'Tester Orea', 'testerusuario@mail.com', '$2a$12$2efhB7V/SSnAZm3brj.qaOCdNnqbWzV37YsqSF5gqxUtxMSgIQb8G', 2);

INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESCRICAO, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'A Fazer', '2021-05-08 00:00:01.000000', 1);
INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESCRICAO, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'Fazendo', '2021-05-08 00:00:01.000000', 2);
INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESCRICAO, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'Pronto', '2021-05-08 00:00:01.000000', 3);

INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'CasaOS', 'http://local.yanpgabriel.com:89/#/', 'http://local.yanpgabriel.com:89/img/casa-white.2579f069.svg');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Portainer', 'https://local.yanpgabriel.com:9443/', 'https://icon.casaos.io/main/all/portainer-ce.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Nginx Proxy Manager', 'http://local.yanpgabriel.com:81/login', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/NginxProxyManager/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'PI Hole', 'http://local.yanpgabriel.com:9080/admin/login.php', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/Pihole/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Bitwarden', 'http://bitwarden.yanpgabriel.com/', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/Vaultwarden/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Home Assistant', 'http://local.yanpgabriel.com:8123/', 'https://brands.home-assistant.io/homeassistant/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Jellyfin', 'http://local.yanpgabriel.com:8096/', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/Jellyfin/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Jenkins', 'http://local.yanpgabriel.com:8008/', 'http://local.yanpgabriel.com:8008/static/6f86b4e0/images/svgs/logo.svg');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Discord Bot', 'http://local.yanpgabriel.com:8069/bot/music', '');
