CREATE TABLE gc_plant_class(
    id UUID PRIMARY KEY,
    data JSONB
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE gc_plant_class TO @db_user@;

CREATE TABLE gc_plant_class_view(
    id UUID PRIMARY KEY,
    data JSONB
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE gc_plant_class_view TO @db_user@;