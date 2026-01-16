**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md) > [ISmartTrackMissionManager](IIntelligentFlightManager_ISmartTrackMissionManager.md)

---

# class ISmartTrackMissionManager

|  |
| --- |
| ``` interface ISmartTrackMissionManager extends IMissionManager<SmartTrackTarget, SmartTrackParam, SmartTrackInfo, SmartTrackCapability> ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |
| *Inherits From:* | `IMissionManager` |

##### Description:

Track task management class. `Supported since MSDK 5.14.0`

##### Class Members:

method

[addMissionCapabilityListener](#iintelligentflightmanager_smarttrackmissionmanager_addmissioncapabilitylistener_inline)

###### method addMissionCapabilityListener

|  |
| --- |
| ``` @Override  void addMissionCapabilityListener(IMissionCapabilityListener<SmartTrackCapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Add the track function capability listener to obtain the capability of different aircraft. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[SmartTrackCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackCapability.md#iintelligentflightmanager_smarttrackmissionmanager_smarttrackcapability)> listener | *Listener of the track capability.* |

method

[removeMissionCapabilityListener](#iintelligentflightmanager_smarttrackmissionmanager_removemissioncapabilitylistener_inline)

###### method removeMissionCapabilityListener

|  |
| --- |
| ``` @Override  void removeMissionCapabilityListener(IMissionCapabilityListener<SmartTrackCapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Remove the listener of the track capability. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[SmartTrackCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackCapability.md#iintelligentflightmanager_smarttrackmissionmanager_smarttrackcapability)> listener | *Listener of the track capability.* |

method

[addMissionInfoListener](#iintelligentflightmanager_smarttrackmissionmanager_addmissioninfolistener_inline)

###### method addMissionInfoListener

|  |
| --- |
| ``` @Override  void addMissionInfoListener(IMissionInfoListener<SmartTrackInfo, SmartTrackTarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Added the listener of the track information。 `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<SmartTrackInfo, [SmartTrackTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackTarget.md#iintelligentflightmanager_smarttrackmissionmanager_smarttracktarget)> listener | *Listener of the track information.* |

method

[removeMissionInfoListener](#iintelligentflightmanager_smarttrackmissionmanager_removemissioninfolistener_inline)

###### method removeMissionInfoListener

|  |
| --- |
| ``` @Override  void removeMissionInfoListener(IMissionInfoListener<SmartTrackInfo, SmartTrackTarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Remove the listener of the track information。 `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<SmartTrackInfo, [SmartTrackTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackTarget.md#iintelligentflightmanager_smarttrackmissionmanager_smarttracktarget)> listener | *Listener of the track information。* |

method

[startMission](#iintelligentflightmanager_smarttrackmissionmanager_startmission_inline)

###### method startMission

|  |
| --- |
| ``` @Override  void startMission(SmartTrackTarget target, SmartTrackParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Start task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [SmartTrackTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackTarget.md#iintelligentflightmanager_smarttrackmissionmanager_smarttracktarget) target | *Target of the track funciton* |
| [SmartTrackParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackParam.md#iintelligentflightmanager_smarttrackmissionmanager_smarttrackparam) param | *Parameter of the track funciton* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[stopMission](#iintelligentflightmanager_smarttrackmissionmanager_stopmission_inline)

###### method stopMission

|  |
| --- |
| ``` @Override  void stopMission(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Stop task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionTarget](#iintelligentflightmanager_smarttrackmissionmanager_updatemissiontarget_inline)

###### method updateMissionTarget

|  |
| --- |
| ``` @Override  void updateMissionTarget(SmartTrackTarget target, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Update the target of the track function. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [SmartTrackTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackTarget.md#iintelligentflightmanager_smarttrackmissionmanager_smarttracktarget) target | *Target of the track funciton* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionParam](#iintelligentflightmanager_smarttrackmissionmanager_updatemissionparam_inline)

###### method updateMissionParam

|  |
| --- |
| ``` @Override  void updateMissionParam(SmartTrackParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.smarttrack |

##### Description:

Update the parameter of the track function. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [SmartTrackParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackParam.md#iintelligentflightmanager_smarttrackmissionmanager_smarttrackparam) param | *Parameter of the track function.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

##### Related:

class

[SmartTrackCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackCapability.md)

class

[SmartTrackInfo](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackInfo.md)

class

[SmartTrackParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackParam.md)

class

[SmartTrackTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SmartTrackMissionManager_SmartTrackTarget.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found