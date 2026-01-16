**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md) > [IntelligentFlightInfoListener](IIntelligentFlightManager_IntelligentFlightInfoListener.md)

---

# class IntelligentFlightInfoListener

|  |
| --- |
| ``` interface IntelligentFlightInfoListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

Intelligent Flight function information listener. `Supported since MSDK 5.14.0`

##### Class Members:

method

[onIntelligentFlightInfoUpdate](#iintelligentflightmanager_intelligentflightinfolistener_onintelligentflightinfoupdate_inline)

###### method onIntelligentFlightInfoUpdate

|  |
| --- |
| ``` void onIntelligentFlightInfoUpdate(@NonNull IntelligentFlightInfo info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

This method will be called back when the intelligent flight information changes. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [IntelligentFlightInfo](../../Components/IIntelligentFlightManager/IIntelligentFlightManager_IntelligentFlightInfo.md#iintelligentflightmanager_intelligentflightinfo) info | *Intelligent flight information* |

method

[onIntelligentFlightErrorUpdate](#iintelligentflightmanager_intelligentflightinfolistener_onintelligentflighterrorupdate_inline)

###### method onIntelligentFlightErrorUpdate

|  |
| --- |
| ``` void onIntelligentFlightErrorUpdate(@NonNull IDJIError error) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent |

##### Description:

This method will be called back when an error occurs in Smart Flight. `Supported since MSDK 5.14.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *Intelligent flight error* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found