CREATE DATABASE weeds_db;
CREATE USER weeds_admin WITH PASSWORD 'weeds_pass123';
ALTER USER weeds_admin CREATEROLE;
GRANT CONNECT ON DATABASE weeds_db TO weeds_admin;
GRANT CREATE ON DATABASE weeds_db TO weeds_admin;
CREATE USER weeds_user WITH PASSWORD 'weeds_pass123';
GRANT CONNECT ON DATABASE weeds_db TO weeds_user;