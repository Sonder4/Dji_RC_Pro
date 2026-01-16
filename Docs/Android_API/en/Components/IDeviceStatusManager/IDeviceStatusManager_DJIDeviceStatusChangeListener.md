# class DJIDeviceStatusChangeListener

|  |
| --- |
| ``` interface DJIDeviceStatusChangeListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.diagnostic |

##### Description:

Listener of the device status. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onDeviceStatusUpdate](#idevicestatusmanager_djidevicestatuschangelistener_ondevicestatusupdate_inline)

###### method onDeviceStatusUpdate

|  |
| --- |
| ``` void onDeviceStatusUpdate(DJIDeviceStatus from, DJIDeviceStatus to) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.diagnostic |

##### Description:

The listener will be triggered when the device status has changed. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [DJIDeviceStatus](../../Components/IDeviceStatusManager/IDeviceStatusManager.md#idevicestatusmanager_djidevicestatus) from | *The previous state of device.* |
| [DJIDeviceStatus](../../Components/IDeviceStatusManager/IDeviceStatusManager.md#idevicestatusmanager_djidevicestatus) to | *The current state of device.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found