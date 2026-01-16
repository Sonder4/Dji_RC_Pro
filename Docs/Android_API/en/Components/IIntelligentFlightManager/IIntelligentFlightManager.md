**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md)

---

# class IIntelligentFlightManager

|  |
| --- |
| ``` interface IIntelligentFlightManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage intelligent flight, which supports advanced intelligent functions such as auto sensing, spotlight, POI, Track and flying to the target point. `Supported since MSDK 5.14.0`

##### Class Members:

method

[removeIntelligentFlightInfoListener](#iintelligentflightmanager_removeintelligentflightinfolistener_inline)

###### method removeIntelligentFlightInfoListener

|  |
| --- |
| ``` void removeIntelligentFlightInfoListener(IntelligentFlightInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Removes the intelligent flight function information listener. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [IntelligentFlightInfoListener](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IntelligentFlightInfoListener.md#iintelligentflightmanager_intelligentflightinfolistener) listener | *Intelligent Flight function information listener.* |

method

[addAutoSensingInfoListener](#iintelligentflightmanager_addautosensinginfolistener_inline)

###### method addAutoSensingInfoListener

|  |
| --- |
| ``` void addAutoSensingInfoListener(AutoSensingInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Adds an auto sensing function information listener. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [AutoSensingInfoListener](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_AutoSensingInfoListener.md#iintelligentflightmanager_autosensinginfolistener) listener | *Auto sensing function information listener.* |

method

[removeAutoSensingInfoListener](#iintelligentflightmanager_removeautosensinginfolistener_inline)

###### method removeAutoSensingInfoListener

|  |
| --- |
| ``` void removeAutoSensingInfoListener(AutoSensingInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Removes the auto sensing function information listener. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [AutoSensingInfoListener](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_AutoSensingInfoListener.md#iintelligentflightmanager_autosensinginfolistener) listener | *Auto sensing function information listener.* |

method

[startAutoSensing](#iintelligentflightmanager_startautosensing_inline)

###### method startAutoSensing

|  |
| --- |
| ``` void startAutoSensing(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Starts the auto sensing function. Once activated, the information about the aircraft’s auto sensing can be accessed through `addAutoSensingInfoListener`. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Callback for returning the execution result.* |

method

[stopAutoSensing](#iintelligentflightmanager_stopautosensing_inline)

###### method stopAutoSensing

|  |
| --- |
| ``` void stopAutoSensing(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stops the auto sensing function. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Callback for returning the execution result.* |

method

[selectIntelligentModel](#iintelligentflightmanager_selectintelligentmodel_inline)

###### method selectIntelligentModel

|  |
| --- |
| ``` void selectIntelligentModel(int modelId, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Select intelligent model function. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int modelId | *the id of model on aricraft* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Callback for returning the execution result.* |

method

[getSmartTrackMissionManager](#iintelligentflightmanager_getsmarttrackmissionmanager_inline)

###### method getSmartTrackMissionManager

|  |
| --- |
| ``` ISmartTrackMissionManager getSmartTrackMissionManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Retrieves the SmartTrackMissionManager instance for the intelligent flight function, which provides functionalities to turn on and off the track function, as well as parameter settings. `Supported since MSDK 5.14.0`

##### Return:

|  |  |
| --- | --- |
| [ISmartTrackMissionManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_ISmartTrackMissionManager.md#iintelligentflightmanager_ismarttrackmissionmanager) | *Returns the `SmartTrackMissionManager` instance for the intelligent flight function.* |

method

[getSpotLightManager](#iintelligentflightmanager_getspotlightmanager_inline)

###### method getSpotLightManager

|  |
| --- |
| ``` ISpotLightManager getSpotLightManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Retrieves the SpotLightManager instance for the intelligent flight function, which provides functionalities to turn on and off the SpotLight function, as well as parameter settings. `Supported since MSDK 5.14.0`

##### Return:

|  |  |
| --- | --- |
| [ISpotLightManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_ISpotLightManager.md#iintelligentflightmanager_ispotlightmanager) | *Returns the SpotLightManager instance for the intelligent flight function.* |

method

[getPOIMissionManager](#iintelligentflightmanager_getpoimissionmanager_inline)

###### method getPOIMissionManager

|  |
| --- |
| ``` IPOIMissionManager getPOIMissionManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Retrieves the POIMissionManager instance for the intelligent flight function, which provides functionalities to turn on and off the POI function, as well as parameter settings. `Supported since MSDK 5.14.0`

##### Return:

|  |  |
| --- | --- |
| [IPOIMissionManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IPOIMissionManager.md#iintelligentflightmanager_ipoimissionmanager) | *Returns the POIMissionManager instance for the intelligent flight function.* |

method

[getFlyToMissionManager](#iintelligentflightmanager_getflytomissionmanager_inline)

###### method getFlyToMissionManager

|  |
| --- |
| ``` IFlyToMissionManager getFlyToMissionManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Retrieves the FlyToMissionManager instance for the intelligent flight function, which provides functionalities to turn on and off the fly to target point function, as well as parameter settings. `Supported since MSDK 5.14.0`

##### Return:

|  |  |
| --- | --- |
| [IFlyToMissionManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IFlyToMissionManager.md#iintelligentflightmanager_iflytomissionmanager) | *Returns the FlyToMissionManager instance for the intelligent flight function.* |

##### Related:

class

[IntelligentFlightInfoListener](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IntelligentFlightInfoListener.md)

class

[AutoSensingInfoListener](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_AutoSensingInfoListener.md)

class

[IntelligentFlightInfo](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IntelligentFlightInfo.md)

enum

[MissionType](#iintelligentflightmanager_missiontype_inline)

###### enum MissionType

|  |
| --- |
| ``` @Keep  enum MissionType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

Intelligent Flight function types. `Supported since MSDK 5.14.0`

##### Enum Members:

|  |  |
| --- | --- |
| POI | POI |
| SPOTLIGHT | SpotLight |
| TRACK | Track |
| FLY\_TO | Fly to the target point |

##### Class Members:

enum

[TargetType](#iintelligentflightmanager_targettype_inline)

###### enum TargetType

|  |
| --- |
| ``` @Keep  enum TargetType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

Target types for the intelligent flight function. `Supported since MSDK 5.14.0`

##### Enum Members:

|  |  |
| --- | --- |
| INDEX | Index type |
| LOCATION | Location type |
| RECT | Rectangle area type |

##### Class Members:

class

[AutoSensingInfo](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_AutoSensingInfo.md)

class

[AutoSensingTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_AutoSensingTarget.md)

enum

[AutoSensingTargetType](#iintelligentflightmanager_autosensingtargettype_inline)

###### enum AutoSensingTargetType

|  |
| --- |
| ``` @Keep  enum AutoSensingTargetType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

Target types for auto sensing. `Supported since MSDK 5.14.0`

##### Enum Members:

|  |  |
| --- | --- |
| PERSON | Person |
| CAR | Car |
| BOAT | Boat |
| CUSTOM | Custom |

##### Class Members:

class

[IntelligentModel](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IntelligentModel.md)

class

[ISmartTrackMissionManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_ISmartTrackMissionManager.md)

class

[ISpotLightManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_ISpotLightManager.md)

class

[IPOIMissionManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IPOIMissionManager.md)

class

[IFlyToMissionManager](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IFlyToMissionManager.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found