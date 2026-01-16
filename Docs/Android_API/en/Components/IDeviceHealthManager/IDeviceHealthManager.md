**Navigation:** [IDeviceHealthManager](IDeviceHealthManager.md)

---

# class IDeviceHealthManager

|  |
| --- |
| ``` interface IDeviceHealthManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage device health. Users can get and monitor the health information of each module. For example, Camera overheated, Flight control system error, Battery Maintenance Required, etc. `Supported since MSDK 5.0.0`

##### Class Members:

method

[addDJIDeviceHealthInfoChangeListener](#idevicehealthmanager_adddjidevicehealthinfochangelistener_inline)

###### method addDJIDeviceHealthInfoChangeListener

|  |
| --- |
| ``` void addDJIDeviceHealthInfoChangeListener(DJIDeviceHealthInfoChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of the device helth information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [DJIDeviceHealthInfoChangeListener](../../Components/IDeviceHealthManager/IDeviceHealthManager_DJIDeviceHealthInfoChangeListener.md#idevicehealthmanager_djidevicehealthinfochangelistener) listener | *Listener of the device health information.* |

method

[removeDJIDeviceHealthInfoChangeListener](#idevicehealthmanager_removedjidevicehealthinfochangelistener_inline)

###### method removeDJIDeviceHealthInfoChangeListener

|  |
| --- |
| ``` void removeDJIDeviceHealthInfoChangeListener(DJIDeviceHealthInfoChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of the device helth information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [DJIDeviceHealthInfoChangeListener](../../Components/IDeviceHealthManager/IDeviceHealthManager_DJIDeviceHealthInfoChangeListener.md#idevicehealthmanager_djidevicehealthinfochangelistener) listener | *Listener of the device health information.* |

method

[clearAllListeners](#idevicehealthmanager_clearalllisteners_inline)

###### method clearAllListeners

|  |
| --- |
| ``` void clearAllListeners() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of device health information. `Supported since MSDK 5.0.0`

method

[getCurrentDJIDeviceHealthInfos](#idevicehealthmanager_getcurrentdjidevicehealthinfos_inline)

###### method getCurrentDJIDeviceHealthInfos

|  |
| --- |
| ``` List<DJIDeviceHealthInfo> getCurrentDJIDeviceHealthInfos() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the currnet device health information. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| List<[DJIDeviceHealthInfo](../../Components/IDeviceHealthManager/IDeviceHealthManager_DJIDeviceHealthInfo.md#idevicehealthmanager_djidevicehealthinfo)> | *Return the currnet device health information.* |

##### Related:

class

[DJIDeviceHealthInfo](../../Components/IDeviceHealthManager/IDeviceHealthManager_DJIDeviceHealthInfo.md)

class

[DJIDeviceHealthInfoChangeListener](../../Components/IDeviceHealthManager/IDeviceHealthManager_DJIDeviceHealthInfoChangeListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found