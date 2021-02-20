DROP SCHEMA IF EXISTS copernicus_leads;
CREATE SCHEMA copernicus_leads;
USE copernicus_leads;

CREATE TABLE leads
(
    lead_id           int AUTO_INCREMENT NOT NULL,
    lead_name         varchar(255),
    lead_phone        varchar(255),
    lead_email        varchar(255),
    lead_company_name varchar(255),
    lead_sales_rep_id int                NOT NULL,
    PRIMARY KEY (lead_id)
);

INSERT INTO leads
VALUES (DEFAULT, 'Mario Draghi', '+39026058734', 'mdraghi@italia.it', 'GovernoItaliano', 1),
       (DEFAULT, 'Andrea Pirlo', '+390766683234', 'apirlo@juventus.it', 'Juventus', 23),
       (DEFAULT, 'Bil Gates', '+155583235', 'bgates@outlook.com', 'Microsoft', 1);