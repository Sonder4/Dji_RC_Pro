**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md) > [IFlyToMissionManager](IIntelligentFlightManager_IFlyToMissionManager.md)

---

# class IFlyToMissionManager

|  |
| --- |
| ``` interface IFlyToMissionManager extends IMissionManager<FlyToTarget, FlyToParam, FlyToInfo, FlyToCapability> ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |
| *Inherits From:* | `IMissionManager` |

##### Description:

Fly to target point task management class. `Supported since MSDK 5.14.0`

##### Class Members:

method

[addMissionCapabilityListener](#iintelligentflightmanager_flytomissionmanager_addmissioncapabilitylistener_inline)

###### method addMissionCapabilityListener

|  |
| --- |
| ``` @Override  void addMissionCapabilityListener(IMissionCapabilityListener<FlyToCapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Added the capability listener for the task flying to the target point function, which is used to obtain the capability sets of different aircraft. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[FlyToCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToCapability.md#iintelligentflightmanager_flytomissionmanager_flytocapability)> listener | *Listener for the fly to target point function capability.* |

method

[removeMissionCapabilityListener](#iintelligentflightmanager_flytomissionmanager_removemissioncapabilitylistener_inline)

###### method removeMissionCapabilityListener

|  |
| --- |
| ``` @Override  void removeMissionCapabilityListener(IMissionCapabilityListener<FlyToCapability> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Remove the listener for the fly to target point function capability. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionCapabilityListener<[FlyToCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToCapability.md#iintelligentflightmanager_flytomissionmanager_flytocapability)> listener | *Listener for the fly to target point function capability.* |

method

[addMissionInfoListener](#iintelligentflightmanager_flytomissionmanager_addmissioninfolistener_inline)

###### method addMissionInfoListener

|  |
| --- |
| ``` @Override  void addMissionInfoListener(IMissionInfoListener<FlyToInfo, FlyToTarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Added the listener for the fly to target point function information. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<FlyToInfo, [FlyToTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToTarget.md#iintelligentflightmanager_flytomissionmanager_flytotarget)> listener | *Listener for the fly to target point function information.* |

method

[removeMissionInfoListener](#iintelligentflightmanager_flytomissionmanager_removemissioninfolistener_inline)

###### method removeMissionInfoListener

|  |
| --- |
| ``` @Override  void removeMissionInfoListener(IMissionInfoListener<FlyToInfo, FlyToTarget> listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Remove the listener for the fly to target point function information. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| IMissionInfoListener<FlyToInfo, [FlyToTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToTarget.md#iintelligentflightmanager_flytomissionmanager_flytotarget)> listener | *Listener for the fly to target point function information.* |

method

[startMission](#iintelligentflightmanager_flytomissionmanager_startmission_inline)

###### method startMission

|  |
| --- |
| ``` @Override  void startMission(FlyToTarget target, FlyToParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Start task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [FlyToTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToTarget.md#iintelligentflightmanager_flytomissionmanager_flytotarget) target | *Target of Fly-To function.* |
| [FlyToParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToParam.md#iintelligentflightmanager_flytomissionmanager_flytoparam) param | *Parameter of Fly-To function.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[stopMission](#iintelligentflightmanager_flytomissionmanager_stopmission_inline)

###### method stopMission

|  |
| --- |
| ``` @Override  void stopMission(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Stop task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionTarget](#iintelligentflightmanager_flytomissionmanager_updatemissiontarget_inline)

###### method updateMissionTarget

|  |
| --- |
| ``` @Override  void updateMissionTarget(FlyToTarget target, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Update the target of Fly-To task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [FlyToTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToTarget.md#iintelligentflightmanager_flytomissionmanager_flytotarget) target | *Target of fly-to function.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[updateMissionParam](#iintelligentflightmanager_flytomissionmanager_updatemissionparam_inline)

###### method updateMissionParam

|  |
| --- |
| ``` @Override  void updateMissionParam(FlyToParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Update the parameter of Fly-To task. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [FlyToParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToParam.md#iintelligentflightmanager_flytomissionmanager_flytoparam) param | *Parameter of Fly-To function.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

##### Related:

class

[FlyToCapability](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToCapability.md)

class

[FlyToInfo](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToInfo.md)

class

[FlyToParam](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToParam.md)

class

[FlyToTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_FlyToMissionManager_FlyToTarget.md)

enum

[FlyToMode](#value_flightcontroller_enum_flytomode_inline)

###### enum FlyToMode

|  |
| --- |
| ``` enum FlyToMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Mode of Fly-To task。 `Supported since MSDK 5.14.0`

##### Enum Members:

|  |  |
| --- | --- |
| SMART\_HEIGHT | Optimal Altitude Mode。 |
| SET\_HEIGHT | Set Altitude Mode. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found