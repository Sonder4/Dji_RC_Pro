**Navigation:** [IPerceptionManager](IPerceptionManager.md) > [IRadarManager](IRadarManager.md)

---

# class IRadarManager

|  |
| --- |
| ``` interface IRadarManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class to manager radar, which is used to turn on and off the radar obstacle avoidance switch and obtain obstacle ranging data. `Supported since MSDK 5.1.0`

##### Class Members:

method

[setObstacleAvoidanceEnabled](#iradarmanager_setobstacleavoidanceenabled_inline)

###### method setObstacleAvoidanceEnabled

|  |
| --- |
| ``` void setObstacleAvoidanceEnabled(boolean isEnabled, @NonNull         PerceptionDirection direction, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Turn on or turn off the sub switch of radar obstacle avoidance. Only when the main switch `setOverallObstacleAvoidanceEnabled` is enabled, the sub switch can be enabled. Millimeter-wave radar does not support downward obstacle avoidance. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true` means that turn on the sub switch of radar obstacle avoidance.* |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of the sub switch of radar obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getObstacleAvoidanceEnabled](#iradarmanager_getobstacleavoidanceenabled_inline)

###### method getObstacleAvoidanceEnabled

|  |
| --- |
| ``` void getObstacleAvoidanceEnabled(@NonNull PerceptionDirection direction, @NonNull CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Return whether to turn on the sub switch of radar obstacle avoidance. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of the sub switch of radar obstacle avoidance.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the callback of execution result.* |

method

[addRadarInformationListener](#iradarmanager_addradarinformationlistener_inline)

###### method addRadarInformationListener

|  |
| --- |
| ``` void addRadarInformationListener(RadarInformationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the listener of radar information. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RadarInformationListener](../../Components/IPerceptionManager/IRadarManager_RadarInformationListener.md#iradarmanager_radarinformationlistener) listener | *Listener of radar information.* |

method

[removeRadarInformationListener](#iradarmanager_removeradarinformationlistener_inline)

###### method removeRadarInformationListener

|  |
| --- |
| ``` void removeRadarInformationListener(RadarInformationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of radar information. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RadarInformationListener](../../Components/IPerceptionManager/IRadarManager_RadarInformationListener.md#iradarmanager_radarinformationlistener) listener | *Listener of radar information.* |

method

[clearAllRadarInformationListener](#iradarmanager_clearallradarinformationlistener_inline)

###### method clearAllRadarInformationListener

|  |
| --- |
| ``` void clearAllRadarInformationListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of radar information. `Supported since MSDK 5.1.0`

##### Related:

class

[RadarInformation](../../Components/IPerceptionManager/IRadarManager_RadarInformation.md)

class

[RadarInformationListener](../../Components/IPerceptionManager/IRadarManager_RadarInformationListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found