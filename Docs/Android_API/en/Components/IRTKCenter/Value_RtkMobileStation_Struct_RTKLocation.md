**Navigation:** [IRTKCenter](IRTKCenter.md) > [RTKLocation](Value_RtkMobileStation_Struct_RTKLocation.md)

---

# class RTKLocation

|  |
| --- |
| ``` class RTKLocation implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

The location of the RTK module includes RTK ground and air end information. `Supported since MSDK 5.0.0`

##### Class Members:

#### Members

method

[getPositioningSolution](#value_rtkmobilestation_struct_rtklocation_getpositioningsolution_inline)

###### method getPositioningSolution

|  |
| --- |
| ``` RTKPositioningSolution getPositioningSolution() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

Get the positioning solution of an RTK module.

##### Return:

|  |  |
| --- | --- |
| [RTKPositioningSolution](../../Components/IRTKCenter/IRTKCenter.md#value_rtkmobilestation_enum_rtkpositioningsolution) | *Return the positioning solution of an RTK module.* |

method

[getBaseStationLocation](#value_rtkmobilestation_struct_rtklocation_getbasestationlocation_inline)

###### method getBaseStationLocation

|  |
| --- |
| ``` LocationCoordinate3D getBaseStationLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

Get the location of an RTK base station.

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) | *Return the location of an RTK base station.* |

method

[getMobileStationLocation](#value_rtkmobilestation_struct_rtklocation_getmobilestationlocation_inline)

###### method getMobileStationLocation

|  |
| --- |
| ``` LocationCoordinate3D getMobileStationLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

Get the location of an RTK module on an aircraft.

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) | *Return the location of an RTK module on an aircraft.* |

method

[getStdLongitude](#value_rtkmobilestation_struct_rtklocation_getstdlongitude_inline)

###### method getStdLongitude

|  |
| --- |
| ``` Double getStdLongitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

Get the standard deviation of Longitude. Unit: meter.

##### Return:

|  |  |
| --- | --- |
| Double | *Return the standard deviation of Longitude.* |

method

[getStdLatitude](#value_rtkmobilestation_struct_rtklocation_getstdlatitude_inline)

###### method getStdLatitude

|  |
| --- |
| ``` Double getStdLatitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

Get the standard deviation of Latitude. Unit: meter.

##### Return:

|  |  |
| --- | --- |
| Double | *Return the standard deviation of Latitude.* |

method

[getStdAltitude](#value_rtkmobilestation_struct_rtklocation_getstdaltitude_inline)

###### method getStdAltitude

|  |
| --- |
| ``` Double getStdAltitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

Get the standard deviation of Altitude. Unit: meter.

##### Return:

|  |  |
| --- | --- |
| Double | *Return the standard deviation of Altitude.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found