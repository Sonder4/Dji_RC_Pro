**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [AirSenseAirplaneState](Value_FlightController_Struct_AirSenseAirplaneState.md)

---

# class AirSenseAirplaneState

|  |
| --- |
| ``` class AirSenseAirplaneState implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The AirSense state of an airplane.

##### Class Members:

#### Members

method

[getCode](#value_flightcontroller_struct_airsenseairplanestate_getcode_inline)

###### method getCode

|  |
| --- |
| ``` String getCode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the unique code (ICAO) of the airplane.

##### Return:

|  |  |
| --- | --- |
| String | *Returns The unique code (ICAO) of the airplane.* |

method

[getWarningLevel](#value_flightcontroller_struct_airsenseairplanestate_getwarninglevel_inline)

###### method getWarningLevel

|  |
| --- |
| ``` AirSenseWarningLevel getWarningLevel() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the warning level determined by DJI AirSense system.

##### Return:

|  |  |
| --- | --- |
| [AirSenseWarningLevel](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_airsensewarninglevel) | *Returns the warning level determined by DJI AirSense system.* |

method

[getRelativeDirection](#value_flightcontroller_struct_airsenseairplanestate_getrelativedirection_inline)

###### method getRelativeDirection

|  |
| --- |
| ``` AirSenseDirection getRelativeDirection() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the direction of the airplane relative to the DJI aircraft.

##### Return:

|  |  |
| --- | --- |
| [AirSenseDirection](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_airsensedirection) | *Returns the direction of the airplane relative to the DJI aircraft.* |

method

[getHeading](#value_flightcontroller_struct_airsenseairplanestate_getheading_inline)

###### method getHeading

|  |
| --- |
| ``` Double getHeading() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the heading of the airplane.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the heading of the airplane.* |

method

[getDistance](#value_flightcontroller_struct_airsenseairplanestate_getdistance_inline)

###### method getDistance

|  |
| --- |
| ``` Integer getDistance() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the distance between the airplane and DJI aircraft in meters.

##### Return:

|  |  |
| --- | --- |
| Integer | *Returns the distance between the airplane and DJI aircraft in meters.* |

method

[getLatitude](#value_flightcontroller_struct_airsenseairplanestate_getlatitude_inline)

###### method getLatitude

|  |
| --- |
| ``` Double getLatitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the latitude of the airplane's position.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the latitude of the airplane's position.* |

method

[getLongitude](#value_flightcontroller_struct_airsenseairplanestate_getlongitude_inline)

###### method getLongitude

|  |
| --- |
| ``` Double getLongitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the longitude of the airplane's position.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the longitude of the airplane's position.* |

method

[getAbsoluteAltitude](#value_flightcontroller_struct_airsenseairplanestate_getabsolutealtitude_inline)

###### method getAbsoluteAltitude

|  |
| --- |
| ``` Double getAbsoluteAltitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the altitude of the airplane, unit: meter.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the altitude of the airplane.* |

method

[getAltitude](#value_flightcontroller_struct_airsenseairplanestate_getaltitude_inline)

###### method getAltitude

|  |
| --- |
| ``` Double getAltitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the relative altitude between the airplane and the DJI aircraft, unit: meter.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the relative altitude between the airplane and the DJI aircraft* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found