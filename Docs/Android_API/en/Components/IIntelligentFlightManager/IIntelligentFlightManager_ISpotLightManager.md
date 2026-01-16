**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md) > [ISpotLightManager](IIntelligentFlightManager_ISpotLightManager.md)

---

# class ISpotLightManager

|  |
| --- |
| ``` interface ISpotLightManager extends IMissionManager<SpotLightTarget, SpotLightParam, SpotLightInfo, SpotLightCapability> ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |
| *Inherits From:* | `IMissionManager` |

##### Description:

SpotLight task management class. `Supported since MSDK 5.14.0`

##### Class Members:

method

[addMissionCapabilityListener](#iintelligentflightmanager_spotlightmanager_addmissioncapabilitylistener_inline)

###### method addMissionCapabilityListener

|  |
| --- |
| ``` @Override  void addMissionCapabilityListener(IMissionCapabilityListener<SpotLightCapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Add the spotlight function capability listener to obtain the capability of different aircraft. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[SpotLightCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightCapability.md#iintelligentflightmanager_spotlightmanager_spotlightcapability)> listener | *Listener of the spotlight capability.* |

method

[removeMissionCapabilityListener](#iintelligentflightmanager_spotlightmanager_removemissioncapabilitylistener_inline)

###### method removeMissionCapabilityListener

|  |
| --- |
| ``` @Override  void removeMissionCapabilityListener(IMissionCapabilityListener<SpotLightCapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Remove the listener of the spotlight capability。 `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[SpotLightCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightCapability.md#iintelligentflightmanager_spotlightmanager_spotlightcapability)> listener | *Listener of the spotlight function capability。* |

method

[addMissionInfoListener](#iintelligentflightmanager_spotlightmanager_addmissioninfolistener_inline)

###### method addMissionInfoListener

|  |
| --- |
| ``` @Override  void addMissionInfoListener(IMissionInfoListener<SpotLightInfo, SpotLightTarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Added the listener of the spotlight information. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<SpotLightInfo, [SpotLightTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightTarget.md#iintelligentflightmanager_spotlightmanager_spotlighttarget)> listener | *Listener of the spotlight information.* |

method

[removeMissionInfoListener](#iintelligentflightmanager_spotlightmanager_removemissioninfolistener_inline)

###### method removeMissionInfoListener

|  |
| --- |
| ``` @Override  void removeMissionInfoListener(IMissionInfoListener<SpotLightInfo, SpotLightTarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Remove the listener of the spotlight information. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<SpotLightInfo, [SpotLightTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightTarget.md#iintelligentflightmanager_spotlightmanager_spotlighttarget)> listener | *Listener of the spotlight information.* |

method

[startMission](#iintelligentflightmanager_spotlightmanager_startmission_inline)

###### method startMission

|  |
| --- |
| ``` @Override  void startMission(SpotLightTarget target, SpotLightParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Start task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [SpotLightTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightTarget.md#iintelligentflightmanager_spotlightmanager_spotlighttarget) target | *Target of spotlight function* |
| SpotLightParam param | *Parameter of spotlight function* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[stopMission](#iintelligentflightmanager_spotlightmanager_stopmission_inline)

###### method stopMission

|  |
| --- |
| ``` @Override  void stopMission(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Stop task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionTarget](#iintelligentflightmanager_spotlightmanager_updatemissiontarget_inline)

###### method updateMissionTarget

|  |
| --- |
| ``` @Override  void updateMissionTarget(SpotLightTarget target, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Update the target of spotlight function. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [SpotLightTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightTarget.md#iintelligentflightmanager_spotlightmanager_spotlighttarget) target | *Target of spotlight function* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionParam](#iintelligentflightmanager_spotlightmanager_updatemissionparam_inline)

###### method updateMissionParam

|  |
| --- |
| ``` @Override  void updateMissionParam(SpotLightParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Update the parameter of spotlight function. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| SpotLightParam param | *Parameter of spotlight function* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[enterSpotLightMode](#iintelligentflightmanager_spotlightmanager_enterspotlightmode_inline)

###### method enterSpotLightMode

|  |
| --- |
| ``` @Override  void enterSpotLightMode(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Enter spot light mode. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[exitSpotLightMode](#iintelligentflightmanager_spotlightmanager_exitspotlightmode_inline)

###### method exitSpotLightMode

|  |
| --- |
| ``` @Override  void exitSpotLightMode(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.spotlight |

##### Description:

Exit spot light mode. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

##### Related:

class

[SpotLightCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightCapability.md)

class

[SpotLightTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_SpotLightManager_SpotLightTarget.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found