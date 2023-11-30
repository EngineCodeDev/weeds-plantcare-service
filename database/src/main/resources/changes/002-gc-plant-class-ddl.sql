CREATE TABLE gc_plant_class(
    id UUID PRIMARY KEY,
    data JSONB NOT NULL
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE gc_plant_class TO @db_user@;

CREATE TABLE gc_plant_class_view(
    id UUID PRIMARY KEY,
    data JSONB NOT NULL,
    species TEXT GENERATED ALWAYS AS (get_entry_value_by_key(data, 'species')) STORED UNIQUE NOT NULL,
    genus TEXT GENERATED ALWAYS AS (get_entry_value_by_key(data, 'genus')) STORED NOT NULL
    );
CREATE UNIQUE INDEX idx_species ON gc_plant_class_view USING btree (species);
CREATE INDEX idx_genus ON gc_plant_class_view USING btree (genus);
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE gc_plant_class_view TO @db_user@;