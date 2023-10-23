INSERT INTO PUBLIC.TB_ROLE (ID, NAME) VALUES ('e3dfcefd-0d0c-4eb6-9afd-d7bcbf8ad3f1', 'DUCK_ADM');

INSERT INTO PUBLIC.TB_PROFILE (ID, DTCREATION, NAME) VALUES ('757beccf-7bfd-4a15-a01f-1c294590e0c8', '2021-05-08 00:00:00.000000', 'Administrador');
INSERT INTO PUBLIC.TB_PROFILE_TB_ROLE (PROFILE_ID, ROLE_ID) VALUES ('757beccf-7bfd-4a15-a01f-1c294590e0c8', 'e3dfcefd-0d0c-4eb6-9afd-d7bcbf8ad3f1');

INSERT INTO PUBLIC.TB_PROFILE (ID, DTCREATION, NAME) VALUES ('59556d37-7e90-44aa-9eeb-72d955031dde', '2021-06-03 00:00:00.000000', 'Usuário');

INSERT INTO PUBLIC.TB_USER (ID, DTCREATION, FULLNAME, EMAIL, PASSWORD, PROFILE_ID) VALUES ('21cb3573-715f-49c6-b1b9-de6c6b54e8b0', '2021-05-08 00:00:01.000000', 'Tester Admin', 'testeradmin@mail.com', '$2a$12$2efhB7V/SSnAZm3brj.qaOCdNnqbWzV37YsqSF5gqxUtxMSgIQb8G', '757beccf-7bfd-4a15-a01f-1c294590e0c8');
INSERT INTO PUBLIC.TB_USER (ID, DTCREATION, FULLNAME, EMAIL, PASSWORD, PROFILE_ID) VALUES ('6776e717-a737-4b9a-8016-0aaee8d18967', '2021-05-08 00:00:01.000000', 'Tester Orea', 'testerusuario@mail.com', '$2a$12$2efhB7V/SSnAZm3brj.qaOCdNnqbWzV37YsqSF5gqxUtxMSgIQb8G', '59556d37-7e90-44aa-9eeb-72d955031dde');

INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESCRICAO, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'A Fazer', '2021-05-08 00:00:01.000000', 1);
INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESCRICAO, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'Fazendo', '2021-05-08 00:00:01.000000', 2);
INSERT INTO PUBLIC.TB_ESTADO_DEMANDA (ID, DESCRICAO, DTCRIACAO, ORDEM) VALUES (nextval('estado_demanda_seq'), 'Pronto', '2021-05-08 00:00:01.000000', 3);

INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Portainer', 'https://rasp.local:10001/', 'https://icon.casaos.io/main/all/portainer-ce.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Nginx Proxy Manager', 'http://rasp.local:10002/login', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/NginxProxyManager/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'PI Hole', 'http://rasp.local:10003/admin/login.php', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/Pihole/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Bitwarden', 'https://bitwarden.yanpgabriel.com/', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/Vaultwarden/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Home Assistant', 'https://home.yanpgabriel.com', 'https://brands.home-assistant.io/homeassistant/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Jellyfin', 'http://jellyfin.yanpgabriel.com/', 'https://cdn.jsdelivr.net/gh/IceWhaleTech/CasaOS-AppStore@main/Apps/Jellyfin/icon.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Jenkins', 'https://jenkins.yanpgabriel.dev/', 'https://a.slack-edge.com/80588/img/services/jenkins-ci_512.png');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-02-06 22:46:00.000000', 'Discord Bot', 'http://rasp.local/', 'https://images-eds-ssl.xboxlive.com/image?url=Q_rwcVSTCIytJ0KOzcjWTYl.n38D8jlKWXJx7NRJmQKBAEDCgtTAQ0JS02UoaiwRCHTTX1RAopljdoYpOaNfVf5nBNvbwGfyR5n4DAs0DsOwxSO9puiT_GgKqinHT8HsW8VYeiiuU1IG3jY69EhnsQ--&format=source');
INSERT INTO PUBLIC.TB_APP (ID, DTCREATION, NAME, LINK, ICON) values (nextval('app_seq'), '2023-09-10 15:15:30.000000', 'SonarQube', 'http://rasp.local:9000/', 'https://seeklogo.com/images/S/sonarqube-logo-AF25541AAF-seeklogo.com.png');
