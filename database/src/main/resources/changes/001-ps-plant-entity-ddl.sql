CREATE TABLE ps_plant_entity(
    id UUID PRIMARY KEY,
    data JSONB NOT NULL
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE ps_plant_entity TO @db_user@;

CREATE TABLE ps_plant_entity_view(
    id UUID PRIMARY KEY,
    data JSONB NOT NULL
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE ps_plant_entity_view TO @db_user@;