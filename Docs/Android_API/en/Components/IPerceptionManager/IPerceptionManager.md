**Navigation:** [IPerceptionManager](IPerceptionManager.md)

---

# class IPerceptionManager

|  |
| --- |
| ``` interface IPerceptionManager extends IVisualManager, IPerceptionCommon ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |
| *Inherits From:* | `IVisualManager, IPerceptionCommon` |

##### Description:

This class is perception management class, which is used to turn on and turn off the obstacle avoidance switch, set the type of obstacle avoidance, set warning distance, braking distance and other functions. At the same time, you can call `getRadarManager` to get the information and data of the millimeter-wave radar accessories (if installed). `Supported since MSDK 5.0.0`

##### Class Members:

method

[setObstacleAvoidanceType](#iperceptionmanager_setobstacleavoidancetype_inline)

###### method setObstacleAvoidanceType

|  |
| --- |
| ``` void setObstacleAvoidanceType(ObstacleAvoidanceType type, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Set obstacle avoidance type. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ObstacleAvoidanceType](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_obstacleavoidancetype) type | *Type of the obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getObstacleAvoidanceType](#iperceptionmanager_getobstacleavoidancetype_inline)

###### method getObstacleAvoidanceType

|  |
| --- |
| ``` void getObstacleAvoidanceType( CommonCallbacks.CompletionCallbackWithParam<ObstacleAvoidanceType> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Get the set obstacle avoidance type. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[ObstacleAvoidanceType](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_obstacleavoidancetype)> callback | *Return the callback of execution result.* |

method

[setOverallObstacleAvoidanceEnabled](#iperceptionmanager_setoverallobstacleavoidanceenabled_inline)

###### method setOverallObstacleAvoidanceEnabled

|  |
| --- |
| ``` void setOverallObstacleAvoidanceEnabled(boolean isEnabled,         CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Turn on or turn off the the main switch of obstacle avoidance. MSDK will automatically turn on or turn off all the sub switch of obstacle avoidance at the same time. **Note: This interface has been deprecated since MSDK 5.1.0. Please call `setObstacleAvoidanceType` to set the obstacle avoidance type to `BRAKE` or `CLOSE` to turn on or turn off the obstacle avoidance switch. For mmWave radar accessories, you need to call `setObstacleAvoidanceEnabled` to turn on or turn off the obstacle avoidance switch.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true` means turn on the main switch of obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getOverallObstacleAvoidanceEnabled](#iperceptionmanager_getoverallobstacleavoidanceenabled_inline)

###### method getOverallObstacleAvoidanceEnabled

|  |
| --- |
| ``` void getOverallObstacleAvoidanceEnabled( CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Return whether to turn on the main switch of obstacle avoidance or not. **Note: This interface has been deprecated since MSDK 5.1.0. Please call `getObstacleAvoidanceType` to get whether the obstacle avoidance switch is enabled. For millimeter wave radar accessories, you need to call `getObstacleAvoidanceEnabled` to get whether the obstacle avoidance switch is enabled.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the callback of execution result.* |

method

[setObstacleAvoidanceEnabled](#iperceptionmanager_setobstacleavoidanceenabled_inline)

###### method setObstacleAvoidanceEnabled

|  |
| --- |
| ``` void setObstacleAvoidanceEnabled(boolean isEnabled, @NonNull         PerceptionDirection direction, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Turn on or turn off the sub switch of obstacle avoidance. **Note: For aircraft that do not support `BRAKE`, such as Mini 3 and Mini 3 Pro, the obstacle avoidance sub switch cannot be turned on or off.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true` means that turn on the sub switch of obstacle avoidance.* |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of the sub switch of obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getObstacleAvoidanceEnabled](#iperceptionmanager_getobstacleavoidanceenabled_inline)

###### method getObstacleAvoidanceEnabled

|  |
| --- |
| ``` void getObstacleAvoidanceEnabled(@NonNull PerceptionDirection direction, @NonNull CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Return whether to turn on the sub switch of obstacle avoidance. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of the sub switch of obstacle avoidance.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the callback of execution result.* |

method

[setObstacleAvoidanceWarningDistance](#iperceptionmanager_setobstacleavoidancewarningdistance_inline)

###### method setObstacleAvoidanceWarningDistance

|  |
| --- |
| ``` void setObstacleAvoidanceWarningDistance(double distance, @NonNull PerceptionDirection direction,                                           CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Set distance of obstacle avoidance. Unit: meter.- Take the M30 Series as an example: (For other Aircraft, please refer to the range of DJI Pilot 2) - Horizontal warning distance range: [1.1, 33.0]. - Upward warning distance range: [1.1, 33.0]. - Downward warning distance range: [0.6, 33.0]. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| double distance | *Warning distance of obstacle avoidance.* |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getObstacleAvoidanceWarningDistance](#iperceptionmanager_getobstacleavoidancewarningdistance_inline)

###### method getObstacleAvoidanceWarningDistance

|  |
| --- |
| ``` void getObstacleAvoidanceWarningDistance(@NonNull PerceptionDirection direction,                                           CommonCallbacks.CompletionCallbackWithParam<Double> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Get the warning distance of obstacle avoidance. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Double> callback | *Return the callback of execution result.* |

method

[setObstacleAvoidanceBrakingDistance](#iperceptionmanager_setobstacleavoidancebrakingdistance_inline)

###### method setObstacleAvoidanceBrakingDistance

|  |
| --- |
| ``` void setObstacleAvoidanceBrakingDistance(double distance, @NonNull PerceptionDirection direction,                                           CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Set the braking distance of obstacle avoidance. Unit:meter.- Take the M30 Series as an example: (For other Aircraft, please refer to the range of DJI Pilot 2) - Horizontal braking distance range:[1.0, 10.0]. - Upward braking distance range:[1.0, 10.0]. - Downward braking distance range:[0.5, 3.0]. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| double distance | *Braking distance of obstacle avoidance.* |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getObstacleAvoidanceBrakingDistance](#iperceptionmanager_getobstacleavoidancebrakingdistance_inline)

###### method getObstacleAvoidanceBrakingDistance

|  |
| --- |
| ``` void getObstacleAvoidanceBrakingDistance(@NonNull PerceptionDirection direction,                                           CommonCallbacks.CompletionCallbackWithParam<Double> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Get obstacle braking distance. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PerceptionDirection](../../Components/IPerceptionManager/IPerceptionManager.md#iperceptionmanager_perceptiondirection) direction | *Direction of obstacle avoidance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Double> callback | *Return the callback of execution result.* |

method

[setVisionPositioningEnabled](#iperceptionmanager_setvisionpositioningenabled_inline)

###### method setVisionPositioningEnabled

|  |
| --- |
| ``` void setVisionPositioningEnabled(boolean isEnabled, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Open or close the vision positioning. Vision positioning system help aircraft hover and support landing protection when GPS signal is weak. Disable downward vision system will disable landing protection. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true` means vision positioning is opened.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getVisionPositioningEnabled](#iperceptionmanager_getvisionpositioningenabled_inline)

###### method getVisionPositioningEnabled

|  |
| --- |
| ``` void getVisionPositioningEnabled(@NonNull CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Return whether the vision positioning is opened. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the callback of execution result.* |

method

[setPrecisionLandingEnabled](#iperceptionmanager_setprecisionlandingenabled_inline)

###### method setPrecisionLandingEnabled

|  |
| --- |
| ``` void setPrecisionLandingEnabled(boolean isEnabled, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Open or close the precise landing. When the aircraft takes off vertically to a height of at least 7 m, it will automatically collect information for the area around the takeoff point. When the aircraft finishes collecting information, precise landing will be available during RTH. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true` means that precise landing is opened.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getPrecisionLandingEnabled](#iperceptionmanager_getprecisionlandingenabled_inline)

###### method getPrecisionLandingEnabled

|  |
| --- |
| ``` void getPrecisionLandingEnabled(@NonNull CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Return whether the precise landing is opened. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the callback of execution result.* |

method

[addPerceptionInformationListener](#iperceptionmanager_addperceptioninformationlistener_inline)

###### method addPerceptionInformationListener

|  |
| --- |
| ``` void addPerceptionInformationListener(@NonNull PerceptionInformationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Set the listener of perception information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PerceptionInformationListener](../../Components/IPerceptionManager/IPerceptionManager_PerceptionInformationListener.md#iperceptionmanager_perceptioninformationlistener) listener | *Listener of perception information.* |

method

[removePerceptionInformationListener](#iperceptionmanager_removeperceptioninformationlistener_inline)

###### method removePerceptionInformationListener

|  |
| --- |
| ``` void removePerceptionInformationListener(@NonNull PerceptionInformationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Remove the listener of perception information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PerceptionInformationListener](../../Components/IPerceptionManager/IPerceptionManager_PerceptionInformationListener.md#iperceptionmanager_perceptioninformationlistener) listener | *Listener of perception information.* |

method

[clearAllPerceptionInformationListener](#iperceptionmanager_clearallperceptioninformationlistener_inline)

###### method clearAllPerceptionInformationListener

|  |
| --- |
| ``` void clearAllPerceptionInformationListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Remove all the listeners of perception information. `Supported since MSDK 5.0.0`

method

[addObstacleDataListener](#iperceptionmanager_addobstacledatalistener_inline)

###### method addObstacleDataListener

|  |
| --- |
| ``` void addObstacleDataListener(ObstacleDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Sets the listener of obstacle data to obtain obstacle ranging data. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ObstacleDataListener](../../Components/IPerceptionManager/IPerceptionManager_ObstacleDataListener.md#iperceptionmanager_obstacledatalistener) listener | *The listener of obstacle data.* |

method

[removeObstacleDataListener](#iperceptionmanager_removeobstacledatalistener_inline)

###### method removeObstacleDataListener

|  |
| --- |
| ``` void removeObstacleDataListener(ObstacleDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Remove the listener of obstacle data. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ObstacleDataListener](../../Components/IPerceptionManager/IPerceptionManager_ObstacleDataListener.md#iperceptionmanager_obstacledatalistener) listener | *The listener of obstacle data.* |

method

[clearAllObstacleDataListener](#iperceptionmanager_clearallobstacledatalistener_inline)

###### method clearAllObstacleDataListener

|  |
| --- |
| ``` void clearAllObstacleDataListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception |

##### Description:

Remove all the listener of obstacle data. `Supported since MSDK 5.1.0`

method

[getRadarManager](#iperceptionmanager_getradarmanager_inline)

###### method getRadarManager

|  |
| --- |
| ``` IRadarManager getRadarManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get an instance of RadarRTKManager. This instance is only available with the mmWave radar accessory installed. `Supported since MSDK 5.1.0`

##### Return:

|  |  |
| --- | --- |
| [IRadarManager](../../Components/IPerceptionManager/IRadarManager.md#iradarmanager) | *Return an instance of RadarManager.* |

##### Related:

enum

[PerceptionDirection](#iperceptionmanager_perceptiondirection_inline)

###### enum PerceptionDirection

|  |
| --- |
| ``` enum PerceptionDirection ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception.data |

##### Description:

Direction of obstacle avoidance. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| UPWARD | Upward. |
| DOWNWARD | Downward. |
| HORIZONTAL | Horizontal. Four directions of forward, backward, left and right. |

##### Class Members:

enum

[ObstacleAvoidanceType](#iperceptionmanager_obstacleavoidancetype_inline)

###### enum ObstacleAvoidanceType

|  |
| --- |
| ``` enum ObstacleAvoidanceType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception.data |

##### Description:

Type of obstacle avoidance. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| BRAKE | Beaking. The aircraft will hover automatically when obstacles are detected. |
| BYPASS | Bypass. The aircraft will choose automatically to bypass the obstacles or to hover when obstacles are detected. |
| CLOSE | Close. horizontal, upward and downward obstacle avoidance are unavailable. Aircraft will not automatically brake and bypass when obstacles are detected. |

##### Class Members:

class

[PerceptionInfo](../../Components/IPerceptionManager/IPerceptionManager_PerceptionInfo.md)

class

[PerceptionInformationListener](../../Components/IPerceptionManager/IPerceptionManager_PerceptionInformationListener.md)

class

[ObstacleData](../../Components/IPerceptionManager/IPerceptionManager_ObstacleData.md)

class

[ObstacleDataListener](../../Components/IPerceptionManager/IPerceptionManager_ObstacleDataListener.md)

class

[IRadarManager](../../Components/IPerceptionManager/IRadarManager.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found