**Navigation:** [IRTKCenter](IRTKCenter.md) > [RTKSystemState](RTKCenter_RTKSystemState.md)

---

# class RTKSystemState

|  |
| --- |
| ``` class RTKSystemState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

RTK system state. `Supported since MSDK 5.0.0`

##### Class Members:

#### Members

method

[getRTKConnected](#rtkcenter_rtksystemstate_getrtkconnected_inline)

###### method getRTKConnected

|  |
| --- |
| ``` boolean getRTKConnected() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

Get the connection status of the RTK module on the aircraft between the flight system. The default value is `true`, it will only become `false` when the RTK module on the aircraft has a circuit fault. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true`means the RTK moudle on the aircraft is connected with the flight system.* |

method

[getRTKHealthy](#rtkcenter_rtksystemstate_getrtkhealthy_inline)

###### method getRTKHealthy

|  |
| --- |
| ``` boolean getRTKHealthy() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

Get the working status of the RTK module on the aircraft. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true`means the RTK module is working normally on the aircraft.* |

method

[getSatelliteInfo](#rtkcenter_rtksystemstate_getsatelliteinfo_inline)

###### method getSatelliteInfo

|  |
| --- |
| ``` RTKSatelliteInfo getSatelliteInfo() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk |

##### Description:

Get the satellite information of the RTK module on the aircraft. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [RTKSatelliteInfo](../../Components/IRTKCenter/Value_RtkMobileStation_Struct_RTKSatelliteInfo.md#value_rtkmobilestation_struct_rtksatelliteinfo) | *Return the satellite information of the RTK module on the aircraft.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found