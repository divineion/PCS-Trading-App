-- Data for table role
INSERT INTO `role` (id, name) 
SELECT * FROM (SELECT 1, 'USER') as tmp
WHERE NOT EXISTS (SELECT 1 FROM role WHERE name = 'USER')
LIMIT 1;


INSERT INTO `role` (id, name) 
SELECT * FROM (SELECT 2, 'ADMIN') as tmp
WHERE NOT EXISTS (SELECT 1 FROM role WHERE name = 'ADMIN')
LIMIT 1;

-- Data for table app_user

INSERT INTO app_user (fullname, username, password, role)
SELECT * FROM (
	SELECT 
		"Administrator" as fullname, 
		"admin" as username, 
		"$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa" as password, 
		2 as role
	) as tmp
WHERE NOT EXISTS (
	SELECT 1 FROM app_user WHERE username = "admin"
)
LIMIT 1;

INSERT INTO app_user (fullname, username, password, role) 
SELECT * FROM (
	SELECT 
		"User" as fullname, 
		"user" as username, 
		"$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa" as password, 
		1 as role
	) AS tmp
WHERE NOT EXISTS (
	SELECT 1 FROM app_user WHERE username = "user"
)
LIMIT 1;