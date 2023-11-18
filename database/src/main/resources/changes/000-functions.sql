CREATE OR REPLACE FUNCTION get_entry_value_by_key(data JSONB, entry_key VARCHAR)
    RETURNS VARCHAR AS
$function$
DECLARE
    entry_value VARCHAR;
BEGIN
    SELECT entry ->> 'value'
    INTO entry_value
    FROM jsonb_array_elements(data -> 'entries') entry
    WHERE entry ->> 'key' = entry_key
    LIMIT 1;

    RETURN entry_value;
END;
$function$
    LANGUAGE plpgsql IMMUTABLE;