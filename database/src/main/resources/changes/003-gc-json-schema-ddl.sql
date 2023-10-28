CREATE TABLE gc_json_schema(
    id UUID PRIMARY KEY,
    data JSONB
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE gc_json_schema TO @db_user@;