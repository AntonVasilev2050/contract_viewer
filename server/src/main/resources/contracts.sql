CREATE TABLE contracts
(
    contract_id   serial PRIMARY KEY,
    contract_date date not null,
    contract_number int,
    contract_update date
);
INSERT INTO contracts
VALUES (1, '2000-02-17', 111111, '2022-01-17'),
       (2, '2000-02-17', 111112, '2000-01-17'),
       (3, '2000-11-20', 333333, '2015-10-01'),
       (4, '2000-12-03', 987654, '2022-01-17');