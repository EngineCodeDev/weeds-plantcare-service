package dev.enginecode.weeds.plantcareservice.plantcatalog

import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification

abstract class CreatePlantClassEndpointValidationTestData extends IntestSpecification {

    protected static final def CREATE_PAYLOAD_EMPTY_GROUPS = """
        {
          "groups": [],
          "entries": [
            {"key": "name", "value": "Oak", "type": "string", "info": "Very popular tree"},
            {"key": "species", "value": "Quercus robur", "type": "string", "info": ""},
            {"key": "genus", "value": "Quercus", "type": "string", "info": ""}
          ]
        }
      """

    protected static final def CREATE_PAYLOAD_NULL_GROUPS = """
        {
          "entries": [
            {"key": "name", "value": "Oak", "type": "string", "info": "Very popular tree"},
            {"key": "species", "value": "Quercus robur", "type": "string", "info": ""},
            {"key": "genus", "value": "Quercus", "type": "string", "info": ""}
          ]
        }
      """

    protected static final def CREATE_PAYLOAD_EMPTY_ENTRIES = """
        {
          "groups": ["trees"],
          "entries": []
        }
      """

    protected static final def CREATE_PAYLOAD_NULL_ENTRIES = """
        {
          "groups": ["trees"]
        }
      """

}

