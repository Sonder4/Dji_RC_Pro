**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [AirSenseSystemInformation](Value_FlightController_Struct_AirSenseSystemInformation.md)

---

# class AirSenseSystemInformation

|  |
| --- |
| ``` class AirSenseSystemInformation implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

AirSense system information.

##### Class Members:

method

[getWarningLevel](#value_flightcontroller_struct_airsensesysteminformation_getwarninglevel_inline)

###### method getWarningLevel

|  |
| --- |
| ``` AirSenseWarningLevel getWarningLevel() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the overall system warning level. This will be the worst case of all individual aircraft warning levels.

##### Return:

|  |  |
| --- | --- |
| [AirSenseWarningLevel](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_airsensewarninglevel) | *Returns the overall system warning level.* |

method

[getAirplaneStates](#value_flightcontroller_struct_airsensesysteminformation_getairplanestates_inline)

###### method getAirplaneStates

|  |
| --- |
| ``` List<AirSenseAirplaneState> getAirplaneStates() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gest the state of all airplanes detected by DJI AirSense system.

##### Return:

|  |  |
| --- | --- |
| List<[AirSenseAirplaneState](../../Components/IKeyManager/Value_FlightController_Struct_AirSenseAirplaneState.md#value_flightcontroller_struct_airsenseairplanestate)> | *Returns the state of all airplanes detected by DJI AirSense system.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found