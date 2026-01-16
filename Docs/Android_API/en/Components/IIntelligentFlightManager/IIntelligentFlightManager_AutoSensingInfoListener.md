**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md) > [AutoSensingInfoListener](IIntelligentFlightManager_AutoSensingInfoListener.md)

---

# class AutoSensingInfoListener

|  |
| --- |
| ``` interface AutoSensingInfoListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

Auto sensing function information listener. `Supported since MSDK 5.14.0`

##### Class Members:

method

[onAutoSensingInfoUpdate](#iintelligentflightmanager_autosensinginfolistener_onautosensinginfoupdate_inline)

###### method onAutoSensingInfoUpdate

|  |
| --- |
| ``` void onAutoSensingInfoUpdate(@NonNull AutoSensingInfo info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

This method will be called back when the auto sensing information changes. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [AutoSensingInfo](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_AutoSensingInfo.md#iintelligentflightmanager_autosensinginfo) info | *Auto sensing information* |

method

[onTrackingTargetUpdate](#iintelligentflightmanager_autosensinginfolistener_ontrackingtargetupdate_inline)

###### method onTrackingTargetUpdate

|  |
| --- |
| ``` default void onTrackingTargetUpdate(@NonNull AutoSensingTarget target) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

This method is called back when the Tracking target changes. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [AutoSensingTarget](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_AutoSensingTarget.md#iintelligentflightmanager_autosensingtarget) target | *Tracking target information* |

method

[onIntelligentModelUpdate](#iintelligentflightmanager_autosensinginfolistener_onintelligentmodelupdate_inline)

###### method onIntelligentModelUpdate

|  |
| --- |
| ``` default void onIntelligentModelUpdate(@NonNull List<IntelligentModel> models) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

This method is called back when the Intelligent model list changes. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull List<[IntelligentModel](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IntelligentModel.md#iintelligentflightmanager_intelligentmodel)> models | *Intelligent model list* |

method

[onRunningIntelligentModelUpdate](#iintelligentflightmanager_autosensinginfolistener_onrunningintelligentmodelupdate_inline)

###### method onRunningIntelligentModelUpdate

|  |
| --- |
| ``` default void onRunningIntelligentModelUpdate(int modelId) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

This method is called back when the running modelId changes. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int modelId | *The id of model on aircraft* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found