**Navigation:** [IFlightLogManager](IFlightLogManager.md)

---

# class IFlightLogManager

|  |
| --- |
| ``` interface IFlightLogManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage flight logs. User is able to get the file path of flight logs and flight controller compact logs through this class. The flight log is defined by MSDK and saved on mobile device. The flight control compact logs is defined by the aircraft firmware and save on mobile device. MSDK will not write to these logs. They are provided for MSDK App developers and users. The users can use these logs in DJI user center when they want to use them for after-sales services. Only MSDK app developers and users are able to access them. The logs will override themselves after running of time. Thus, MSDK app developers and users can copy them out for backups periodicly. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getFlightRecordPath](#iflightlogmanager_getflightrecordpath_inline)

###### method getFlightRecordPath

|  |
| --- |
| ``` String getFlightRecordPath() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the local directory of flight record. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| String | *Return the local directory of flight record.* |

method

[getFlyClogPath](#iflightlogmanager_getflyclogpath_inline)

###### method getFlyClogPath

|  |
| --- |
| ``` String getFlyClogPath() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the local directory of flight Controller compact logs. **Note： If the remote controller is replaced or the app is reinstalled during use, the aircraft needs to be reboot to generate the flight Controller compact logs.** `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| String | *Return the local directory of flight Controller compact logs.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found