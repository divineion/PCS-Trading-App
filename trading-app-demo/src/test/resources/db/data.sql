-- data for table role
INSERT INTO role (id, name) 
VALUES (1, 'USER');

INSERT INTO role (id, name) 
VALUES (2, 'ADMIN');

-- data for table app_user
INSERT INTO app_user (fullname, username, password, role) 
VALUES ('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 2);

INSERT INTO app_user(fullname, username, password, role) 
VALUES ('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 1);

-- INSERTIONS TOUTES TABLES