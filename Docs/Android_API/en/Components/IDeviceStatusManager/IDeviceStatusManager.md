# class IDeviceStatusManager

|  |
| --- |
| ``` interface IDeviceStatusManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage device status. Users can get and monitor device status. For example, Aircraft disconnected, Sensor Error, Low Voltage Warning, etc. `Supported since MSDK 5.0.0`

##### Class Members:

method

[addDJIDeviceStatusChangeListener](#idevicestatusmanager_adddjidevicestatuschangelistener_inline)

###### method addDJIDeviceStatusChangeListener

|  |
| --- |
| ``` void addDJIDeviceStatusChangeListener(DJIDeviceStatusChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of the device status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [DJIDeviceStatusChangeListener](../../Components/IDeviceStatusManager/IDeviceStatusManager_DJIDeviceStatusChangeListener.md#idevicestatusmanager_djidevicestatuschangelistener) listener | *Listener of the device status.* |

method

[removeDJIDeviceStatusChangeListener](#idevicestatusmanager_removedjidevicestatuschangelistener_inline)

###### method removeDJIDeviceStatusChangeListener

|  |
| --- |
| ``` void removeDJIDeviceStatusChangeListener(DJIDeviceStatusChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of the device status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [DJIDeviceStatusChangeListener](../../Components/IDeviceStatusManager/IDeviceStatusManager_DJIDeviceStatusChangeListener.md#idevicestatusmanager_djidevicestatuschangelistener) listener | *Listener of the device status.* |

method

[clearAllListeners](#idevicestatusmanager_clearalllisteners_inline)

###### method clearAllListeners

|  |
| --- |
| ``` void clearAllListeners() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of the device status. `Supported since MSDK 5.0.0`

method

[getCurrentDJIDeviceStatus](#idevicestatusmanager_getcurrentdjidevicestatus_inline)

###### method getCurrentDJIDeviceStatus

|  |
| --- |
| ``` DJIDeviceStatus getCurrentDJIDeviceStatus() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the current device status. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [DJIDeviceStatus](../../Components/IDeviceStatusManager/IDeviceStatusManager.md#idevicestatusmanager_djidevicestatus) | *Return the current device status.* |

##### Related:

enum

[DJIDeviceStatus](#idevicestatusmanager_djidevicestatus_inline)

###### enum DJIDeviceStatus

|  |
| --- |
| ``` enum DJIDeviceStatus implements IDJIDeviceStatus ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.diagnostic |

##### Description:

Device status class. It is used to get the device status. `Supported since MSDK 5.0.0`

##### Class Members:

method

[statusCode](#idevicestatusmanager_djidevicestatus_statuscode_inline)

###### method statusCode

|  |
| --- |
| ``` @Override  String statusCode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.diagnostic |

##### Description:

Get the device status code. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| String | *Return the device status code.* |

method

[description](#idevicestatusmanager_djidevicestatus_description_inline)

###### method description

|  |
| --- |
| ``` @Override  String description() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.diagnostic |

##### Description:

Get the device status description. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| String | *Return the device status description.* |

method

[warningLevel](#idevicestatusmanager_djidevicestatus_warninglevel_inline)

###### method warningLevel

|  |
| --- |
| ``` WarningLevel warningLevel() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.diagnostic |

##### Description:

Get the device status warning level. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [WarningLevel](../../Components/IDeviceStatusManager/IDeviceStatusManager.md#idevicestatusmanager_warninglevel) | *Return the device status warning level.* |

enum

[WarningLevel](#idevicestatusmanager_warninglevel_inline)

###### enum WarningLevel

|  |
| --- |
| ``` enum WarningLevel ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.diagnostic |

##### Description:

Waring level class. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| NORMAL | Normal. |
| NOTICE | Notice. |
| CAUTION | Caution. |
| WARNING | Warning. |
| SERIOUS\_WARNING | Serious warning. |

##### Class Members:

class

[DJIDeviceStatusChangeListener](../../Components/IDeviceStatusManager/IDeviceStatusManager_DJIDeviceStatusChangeListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found