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
SELECT * FROM (SELECT 25, '2025-06-30 10:00:00', 1.0, 0.5, NOW()) AS tmp
WHERE NOT EXISTS (
	SELECT 1 FROM  curve_point WHERE curve_id = 1 AND term = 1.0
)
LIMIT 1;

INSERT INTO curve_point(curve_id, as_of_date, term, curve_value, creation_date)
SELECT * FROM (SELECT 54, '2025-06-30 10:00:00', 1.0, 0.5, NOW()) AS tmp
WHERE NOT EXISTS (
	SELECT 1 FROM  curve_point WHERE curve_id = 2 AND term = 1.0
)
LIMIT 1;

-- Data for table rating
INSERT INTO rating (moodys_rating, sand_p_rating, fitch_rating, order_number)
SELECT * FROM (
    SELECT 'Baa1' AS moodys_rating, 'BBB+' AS sand_p_rating, 'BBB+' AS fitch_rating, 1 AS order_number
) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM rating WHERE order_number = 1)
LIMIT 1;

INSERT INTO rating (moodys_rating, sand_p_rating, fitch_rating, order_number)
SELECT * FROM (
    SELECT 'Ba2' AS moodys_rating, 'BB' AS sand_p_rating, 'BB' AS fitch_rating, 2 AS order_number
) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM rating WHERE order_number = 2)
LIMIT 1;

-- data for table trading_app
INSERT INTO trading_rule (name, description, json, template, sql_str, sql_part)
SELECT * FROM (
    SELECT
        'Stop Loss Rule',
        'Triggers when the price drops below a defined threshold.',
        '{"threshold": 5, "currency": "USD"}',
        'IF price < :threshold THEN SELL',
        'SELECT * FROM trades WHERE price < 5',
        'price < 5'
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM trading_rule WHERE name = 'Stop Loss Rule'
) LIMIT 1;

INSERT INTO trading_rule (name, description, json, template, sql_str, sql_part)
SELECT * FROM (
    SELECT
        'Volume Spike Rule',
        'Alerts when trading volume exceeds the daily average by 200%.',
        '{"volume_multiplier": 2}',
        'IF volume > daily_avg * :volume_multiplier THEN ALERT',
        'SELECT * FROM trades WHERE volume > (SELECT AVG(volume) * 2 FROM trades)',
        'volume > daily_avg * 2'
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM trading_rule WHERE name = 'Volume Spike Rule'
) LIMIT 1;

-- data for table trade
INSERT INTO trade (account, type, buy_quantity)
SELECT 'ACC001', 'BUY', 100
WHERE NOT EXISTS (
    SELECT 1 FROM trade WHERE account = 'ACC001' AND type = 'BUY' AND buy_quantity = 100
);

INSERT INTO trade (account, type, buy_quantity)
SELECT 'ACC002', 'SELL', 50
WHERE NOT EXISTS (
    SELECT 1 FROM trade WHERE account = 'ACC002' AND type = 'SELL' AND buy_quantity = 50
);

