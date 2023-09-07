CREATE DATABASE weeds_db;
CREATE USER weeds_admin WITH PASSWORD 'weeds_pass123';
GRANT CONNECT ON DATABASE weeds_db TO weeds_admin;
-- change database to weeds_db
GRANT USAGE, CREATE ON SCHEMA public TO weeds_admin;

CREATE USER weeds_user WITH PASSWORD 'weeds_pass123';
GRANT CONNECT ON DATABASE weeds_db TO weeds_user;
-- change database to weeds_db
GRANT USAGE ON SCHEMA public TO weeds_user;