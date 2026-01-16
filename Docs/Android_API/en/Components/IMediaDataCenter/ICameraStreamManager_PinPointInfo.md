**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [ICameraStreamManager](ICameraStreamManager.md) > [PinPointInfo](ICameraStreamManager_PinPointInfo.md)

---

# class PinPointInfo

|  |
| --- |
| ``` class PinPointInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera.view |

##### Description:

Pin point information, include the pin points location in the liveview and callback result. `Supported since MSDK 5.9.0`

##### Class Members:

method

[getPinPoints](#icamerastreammanager_pinpointinfo_getpinpoints_inline)

###### method getPinPoints

|  |
| --- |
| ``` List<PinPoint> getPinPoints() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera.view |

##### Description:

Gets the pin points location. If the `KeyThermalDisplayMode` mokde is `ThermalPIPPosition`:`SIDE_BY_SIDE`, it will return two pin points.

##### Return:

|  |  |
| --- | --- |
| List<[PinPoint](../../Components/IMediaDataCenter/ICameraStreamManager_PinPoint.md#icamerastreammanager_pinpoint)> | *Returns the pin points location.* |

method

[getResult](#icamerastreammanager_pinpointinfo_getresult_inline)

###### method getResult

|  |
| --- |
| ``` Result getResult() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera.view |

##### Description:

Gets the result of pin point callback.

##### Return:

|  |  |
| --- | --- |
| [Result](../../Components/IMediaDataCenter/ICameraStreamManager.md#icamerastreammanager_pinpointresult) | *Returns the result of pin point callback.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found