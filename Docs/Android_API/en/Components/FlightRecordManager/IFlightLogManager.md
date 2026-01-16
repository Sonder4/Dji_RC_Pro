# class IFlightLogManager

|  |
| --- |
| ``` interface IFlightLogManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage flight logs. User is able to get the file path of flight logs and flight controller logs through this class. The saved path of flight log is defined by MSDK and save on mobile device. The saved path of flight controller log is defined by the aircraft firmware and save on mobile device. MSDK will not write to these logs. They are provided for MSDK app developers and users. The users can use these logs in DJI user center when they want to use them for after-sales services. Only MSDK app developers and users are able to access them. The logs will override themselves after running of time. Thus, MSDK app developers and users can copy them out for backups periodicly.

##### Class Members:

method

[getFlightLogPath](#iflightlogmanager_getflightlogpath_inline)

###### method getFlightLogPath

|  |
| --- |
| ``` String getFlightLogPath() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the local directory of flight record.

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

Get the local directory of flight control compact logs.

##### Return:

|  |  |
| --- | --- |
| String | *Return the local directory of flight control compact logs.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found