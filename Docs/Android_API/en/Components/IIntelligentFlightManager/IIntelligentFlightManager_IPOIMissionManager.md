**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md) > [IPOIMissionManager](IIntelligentFlightManager_IPOIMissionManager.md)

---

# class IPOIMissionManager

|  |
| --- |
| ``` interface IPOIMissionManager extends IMissionManager<POITarget, POIParam, POIInfo, POICapability> ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |
| *Inherits From:* | `IMissionManager` |

##### Description:

POI task management class. `Supported since MSDK 5.14.0`

##### Class Members:

method

[addMissionCapabilityListener](#iintelligentflightmanager_poimissionmanager_addmissioncapabilitylistener_inline)

###### method addMissionCapabilityListener

|  |
| --- |
| ``` @Override  void addMissionCapabilityListener(IMissionCapabilityListener<POICapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

Add POI function capability listener to obtain capability sets of different aircraft. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[POICapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POICapability.md#iintelligentflightmanager_poimissionmanager_poicapability)> listener | *Listener of POI function capability.* |

method

[removeMissionCapabilityListener](#iintelligentflightmanager_poimissionmanager_removemissioncapabilitylistener_inline)

###### method removeMissionCapabilityListener

|  |
| --- |
| ``` @Override  void removeMissionCapabilityListener(IMissionCapabilityListener<POICapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

移除Listener of POI function capability. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[POICapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POICapability.md#iintelligentflightmanager_poimissionmanager_poicapability)> listener | *Listener of POI function capability.* |

method

[addMissionInfoListener](#iintelligentflightmanager_poimissionmanager_addmissioninfolistener_inline)

###### method addMissionInfoListener

|  |
| --- |
| ``` @Override  void addMissionInfoListener(IMissionInfoListener<POIInfo, POITarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

Added the listener of POI function information. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<POIInfo, [POITarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POITarget.md#iintelligentflightmanager_poimissionmanager_poitarget)> listener | *Listener of POI function information.* |

method

[removeMissionInfoListener](#iintelligentflightmanager_poimissionmanager_removemissioninfolistener_inline)

###### method removeMissionInfoListener

|  |
| --- |
| ``` @Override  void removeMissionInfoListener(IMissionInfoListener<POIInfo, POITarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

Remove the listener of POI function information. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<POIInfo, [POITarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POITarget.md#iintelligentflightmanager_poimissionmanager_poitarget)> listener | *Listener of POI function information.* |

method

[startMission](#iintelligentflightmanager_poimissionmanager_startmission_inline)

###### method startMission

|  |
| --- |
| ``` @Override  void startMission(POITarget target, POIParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

Start task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [POITarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POITarget.md#iintelligentflightmanager_poimissionmanager_poitarget) target | *Target of POI function* |
| [POIParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POIParam.md#iintelligentflightmanager_poimissionmanager_poiparam) param | *Parameter of POI function* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[stopMission](#iintelligentflightmanager_poimissionmanager_stopmission_inline)

###### method stopMission

|  |
| --- |
| ``` @Override  void stopMission(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

Stop task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionTarget](#iintelligentflightmanager_poimissionmanager_updatemissiontarget_inline)

###### method updateMissionTarget

|  |
| --- |
| ``` @Override  void updateMissionTarget(POITarget target, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

Update the target of POI function. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [POITarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POITarget.md#iintelligentflightmanager_poimissionmanager_poitarget) target | *Target of POI function.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionParam](#iintelligentflightmanager_poimissionmanager_updatemissionparam_inline)

###### method updateMissionParam

|  |
| --- |
| ``` @Override  void updateMissionParam(POIParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.poi |

##### Description:

Update the parameter of POI function. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [POIParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POIParam.md#iintelligentflightmanager_poimissionmanager_poiparam) param | *Parameter of POI function.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

##### Related:

class

[POICapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POICapability.md)

class

[POIInfo](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POIInfo.md)

class

[POIParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POIParam.md)

class

[POITarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_POIMissionManager_POITarget.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found