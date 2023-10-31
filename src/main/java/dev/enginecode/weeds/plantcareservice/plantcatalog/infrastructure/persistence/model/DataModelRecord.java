package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.persistence.model;

import dev.enginecode.eccommons.infrastructure.json.model.TableAnnotatedRecord;
import dev.enginecode.eccommons.infrastructure.json.model.TableName;
import dev.enginecode.eccommons.structures.model.Entry;
import dev.enginecode.eccommons.structures.model.DataModel;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.UUID;

@TableName("gc_data_model")
public record DataModelRecord(
        UUID id,
        LinkedHashMap<String, DataModel.EntrySettings> entrySettings,
        LinkedHashMap<String, LinkedHashSet<String>> groupContents,
        LinkedHashMap<String, LinkedHashSet<Entry<?>>> enumOptions
) implements TableAnnotatedRecord<UUID> {}
