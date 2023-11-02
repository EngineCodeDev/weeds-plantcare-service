CREATE TABLE gc_data_model(
    id UUID PRIMARY KEY,
    data JSONB
    );
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE gc_data_model TO @db_user@;