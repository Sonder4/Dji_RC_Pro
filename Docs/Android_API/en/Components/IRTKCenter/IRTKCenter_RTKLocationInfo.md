**Navigation:** [IRTKCenter](IRTKCenter.md) > [RTKLocationInfo](IRTKCenter_RTKLocationInfo.md)

---

# class RTKLocationInfo

|  |
| --- |
| ``` class RTKLocationInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

The information of RTK location. It includes the location data of an RTK module, the heading of an aircraft. It also includes the fusion data of an aircraft's heading and location based on RTK readings and flight controller readings. `Supported since MSDK 5.0.0`

##### Class Members:

#### Members

method

[getRtkLocation](#irtkcenter_rtklocationinfo_getrtklocation_inline)

###### method getRtkLocation

|  |
| --- |
| ``` RTKLocation getRtkLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

Get the RTK location information. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [RTKLocation](../../Components/IRTKCenter/Value_RtkMobileStation_Struct_RTKLocation.md#value_rtkmobilestation_struct_rtklocation) | *Return the RTK location information.* |

method

[getRtkHeading](#irtkcenter_rtklocationinfo_getrtkheading_inline)

###### method getRtkHeading

|  |
| --- |
| ``` RTKHeading getRtkHeading() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

Get the RTK heading. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| RTKHeading | *Return the RTK heading.* |

method

[getRealHeading](#irtkcenter_rtklocationinfo_getrealheading_inline)

###### method getRealHeading

|  |
| --- |
| ``` Double getRealHeading() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

Get the fusion heading of flight controller readings and RTK module readings. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| Double | *Return the fusion heading of flight controller readings and RTK module readings.* |

method

[getReal3DLocation](#irtkcenter_rtklocationinfo_getreal3dlocation_inline)

###### method getReal3DLocation

|  |
| --- |
| ``` LocationCoordinate3D getReal3DLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

Get the fusion location of flight controller readings and RTK module readings. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) | *Return the fusion location of flight controller readings and RTK module readings.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found