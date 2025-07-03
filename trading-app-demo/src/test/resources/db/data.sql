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

-- Data for table BidList
INSERT INTO bid_list (account, type, bid_quantity, ask_quantity, bid, ask, benchmark, bid_list_date, commentary, security, status, trader, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side)
VALUES ('ACC001', 'Corporate', 150000.75, 155000.589, 99.858, 100.15, 'US Treasury 10Y', '2025-06-22 14:30:00', 'High demand expected', 'SEC-001', 'Open', 'J. Smith', 'Book-A', 'admin', '2024-06-23 09:00:00', 'jdoe', '2024-06-23 10:00:00', 'Deal-123', 'Primary', 'SL-789', 'Buy');


INSERT INTO bid_list (account, type, bid_quantity, ask_quantity, bid, ask, benchmark, bid_list_date, commentary, security, status, trader, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side)
VALUES ('ACC002', 'FX', 250000.00, 260000.00, 1.1025, 1.1035, 'EUR/USD', '2024-06-21 11:00:00', 'Volatile market conditions', 'SEC-002', 'Closed', 'M. Taylor', 'Book-B', 'user', '2024-06-19 10:00:00', 'jdoe', '2024-06-22 12:00:00', 'Deal-456', 'Spot', 'FX-123', 'Sell');

--Data dor table curve_point
INSERT INTO curve_point(curve_id, as_of_date, term, curve_value, creation_date)
VALUES (25, '2025-06-30 10:00:00', 1.0, 0.5, NOW());

INSERT INTO curve_point(curve_id, as_of_date, term, curve_value, creation_date)
VALUES (54, '2025-06-30 10:00:00', 1.0, 0.5, NOW());

-- Data for table rating
INSERT INTO rating (moodys_rating, sand_p_rating, fitch_rating, order_number)
VALUES('Baa1', 'BBB+', 'BBB+', 1);

INSERT INTO rating (moodys_rating, sand_p_rating, fitch_rating, order_number)
VALUES ('Ba2', 'BB', 'BB', 2);
