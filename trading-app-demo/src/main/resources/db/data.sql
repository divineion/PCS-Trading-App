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

-- Data for table BidList
INSERT INTO bid_list (account, type, bid_quantity, ask_quantity, bid, ask, benchmark, bid_list_date, commentary, security, status, trader, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side)
SELECT * FROM
(SELECT 'ACC001', 'Corporate', 150000.75, 155000.589, 99.858, 100.15, 'US Treasury 10Y', '2025-06-22 14:30:00', 'High demand expected', 'SEC-001', 'Open', 'J. Smith', 'Book-A', 'admin', '2024-06-23 09:00:00', 'jdoe', '2024-06-23 10:00:00', 'Deal-123', 'Primary', 'SL-789', 'Buy')
AS tmp
WHERE NOT EXISTS(
	SELECT 1 FROM bid_list WHERE deal_name = 'Deal-123'
)
LIMIT 1;


INSERT INTO bid_list (account, type, bid_quantity, ask_quantity, bid, ask, benchmark, bid_list_date, commentary, security, status, trader, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side)
SELECT * FROM
(SELECT 'ACC002', 'FX', 250000.00, 260000.00, 1.1025, 1.1035, 'EUR/USD', '2024-06-21 11:00:00', 'Volatile market conditions', 'SEC-002', 'Closed', 'M. Taylor', 'Book-B', 'user', '2024-06-19 10:00:00', 'jdoe', '2024-06-22 12:00:00', 'Deal-456', 'Spot', 'FX-123', 'Sell')
AS tmp
WHERE NOT EXISTS(
	SELECT 1 FROM bid_list WHERE deal_name = 'Deal-456'
)
LIMIT 1;

-- Data for table curve_point

INSERT INTO curve_point(curve_id, as_of_date, term, curve_value, creation_date)
SELECT * FROM (SELECT 1, '2025-06-30 10:00:00', 1.0, 0.5, NOW()) AS tmp
WHERE NOT EXISTS (
	SELECT 1 FROM  curve_point WHERE curve_id = 1 AND term = 1.0
)
LIMIT 1;

INSERT INTO curve_point(curve_id, as_of_date, term, curve_value, creation_date)
SELECT * FROM (SELECT 2, '2025-06-30 10:00:00', 1.0, 0.5, NOW()) AS tmp
WHERE NOT EXISTS (
	SELECT 1 FROM  curve_point WHERE curve_id = 2 AND term = 1.0
)
LIMIT 1;