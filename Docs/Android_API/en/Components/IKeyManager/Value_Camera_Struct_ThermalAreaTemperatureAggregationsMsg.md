**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [ThermalAreaMetersureTemperature](Value_Camera_Struct_ThermalAreaTemperatureAggregationsMsg.md)

---

# class ThermalAreaMetersureTemperature

|  |
| --- |
| ``` class ThermalAreaMetersureTemperature implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Information of regional temperature measurement, including the average temperature, minimum temperature and maximum temperature of the measuring area.

##### Class Members:

#### Members

method

[getAverageAreaTemperature](#value_camera_struct_thermalareatemperatureaggregationsmsg_getaverageareatemperature_inline)

###### method getAverageAreaTemperature

|  |
| --- |
| ``` Double getAverageAreaTemperature() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the average temperature measured in the current area.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the average temperature measured in the current area.* |

method

[getMinAreaTemperature](#value_camera_struct_thermalareatemperatureaggregationsmsg_getminareatemperature_inline)

###### method getMinAreaTemperature

|  |
| --- |
| ``` Double getMinAreaTemperature() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the minimum temperature measured in the current area.

##### Return:

|  |  |
| --- | --- |
| Double | *Return the minimum temperature measured in the current area.* |

method

[getMinTemperaturePoint](#value_camera_struct_thermalareatemperatureaggregationsmsg_getmintemperaturepoint_inline)

###### method getMinTemperaturePoint

|  |
| --- |
| ``` DoublePoint2D getMinTemperaturePoint() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the position of the minimum temperature measured in the current area.

##### Return:

|  |  |
| --- | --- |
| [DoublePoint2D](../../Components/IKeyManager/Value_Common_Struct_DoublePoint2D.md#value_common_struct_doublepoint2d) | *Return the position of the minimum temperature measured in the current area.* |

method

[getMaxAreaTemperature](#value_camera_struct_thermalareatemperatureaggregationsmsg_getmaxareatemperature_inline)

###### method getMaxAreaTemperature

|  |
| --- |
| ``` Double getMaxAreaTemperature() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the maximum temperature measured in the current area.

##### Return:

|  |  |
| --- | --- |
| Double | *Return the maximum temperature measured in the current area.* |

method

[getMaxTemperaturePoint](#value_camera_struct_thermalareatemperatureaggregationsmsg_getmaxtemperaturepoint_inline)

###### method getMaxTemperaturePoint

|  |
| --- |
| ``` DoublePoint2D getMaxTemperaturePoint() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the position of the maximum temperature measured in the current area.

##### Return:

|  |  |
| --- | --- |
| [DoublePoint2D](../../Components/IKeyManager/Value_Common_Struct_DoublePoint2D.md#value_common_struct_doublepoint2d) | *Return the position of the maximum temperature measured in the current area.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found