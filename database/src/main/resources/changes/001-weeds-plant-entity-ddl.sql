CREATE TABLE ps_plant_entity(
    id UUID PRIMARY KEY,
    data JSONB
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE ps_plant_entity TO @db_user@;