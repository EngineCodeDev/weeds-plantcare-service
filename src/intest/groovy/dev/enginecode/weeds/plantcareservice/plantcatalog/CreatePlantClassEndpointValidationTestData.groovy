package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification

abstract class CreatePlantClassEndpointValidationTestData extends IntestSpecification {

    protected static final def CREATE_PAYLOAD_EMPTY = """
        {
          "groups": [],
          "entries": []
        }
      """

    protected static final def CREATE_PAYLOAD_NULL = "{}"

    protected static final def CREATE_PAYLOAD_UNKNOWN_GROUP = """
        {
          "groups": ["trees", "unknown group"],
          "entries": [
            {"key": "species", "value": "Quercus robur", "type": "string", "info": ""},
            {"key": "genus", "value": "Quercus", "type": "string", "info": ""}
          ]
        }
      """

    protected static final def CREATE_PAYLOAD_UNKNOWN_GROUPS = """
        {
          "groups": ["unknown group 1", "trees", "unknown group 2"],
          "entries": [
            {"key": "species", "value": "Quercus robur", "type": "string", "info": ""},
            {"key": "genus", "value": "Quercus", "type": "string", "info": ""}
          ]
        }
      """

    protected static final def CREATE_PAYLOAD_UNEXPECTED_CONTENTS = """
        {
          "groups": ["trees", "ground plant"],
          "entries": [
            {"key": "species", "value": "Quercus robur", "type": "string", "info": ""},
            {"key": "genus", "value": "Quercus", "type": "string", "info": ""},
            {"key": "origin", "value": ["origin_asia"], "type": "enum_key_array", "info": ""}
          ]
        }
      """

    protected static final def CREATE_PAYLOAD_WRONG_ENTRY_TYPE = """
        {
          "groups": ["trees"],
          "entries": [
            {"key": "species", "value": "Quercus robur", "type": "enum_key", "info": ""},
            {"key": "genus", "value": "Quercus", "type": "string", "info": ""}
          ]
        }
      """

}

