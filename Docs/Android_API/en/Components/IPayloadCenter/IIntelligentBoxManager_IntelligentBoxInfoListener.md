**Navigation:** [IPayloadCenter](IPayloadCenter.md) > [IIntelligentBoxManager](IIntelligentBoxManager.md) > [IntelligentBoxInfoListener](IIntelligentBoxManager_IntelligentBoxInfoListener.md)

---

# class IntelligentBoxInfoListener

|  |
| --- |
| ``` interface IntelligentBoxInfoListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.aibox |

##### Description:

Listener of intelligent box basic information. `Supported since MSDK 5.15.0`

##### Class Members:

method

[onBoxInfoUpdate](#iintelligentboxmanager_intelligentboxinfolistener_onboxinfoupdate_inline)

###### method onBoxInfoUpdate

|  |
| --- |
| ``` void onBoxInfoUpdate(IntelligentBoxInfo info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.aibox |

##### Description:

When the intelligent basic information changes, this method will be called back. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [IntelligentBoxInfo](../../Components/IPayloadCenter/IIntelligentBoxManager_IntelligentBoxInfo.md#iintelligentboxmanager_intelligentboxinfo) info | *Intelligent box basic information.* |

method

[onBoxAppInfoUpdate](#iintelligentboxmanager_intelligentboxinfolistener_onboxappinfoupdate_inline)

###### method onBoxAppInfoUpdate

|  |
| --- |
| ``` void onBoxAppInfoUpdate(List<IntelligentBoxAppInfo> infos) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.aibox |

##### Description:

When the intelligent box app basic information changes, this method will be called back. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| List<[IntelligentBoxAppInfo](../../Components/IPayloadCenter/IIntelligentBoxManager_IntelligentBoxAppInfo.md#iintelligentboxmanager_intelligentboxappinfo)> infos | *Intelligent box app basic information.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found