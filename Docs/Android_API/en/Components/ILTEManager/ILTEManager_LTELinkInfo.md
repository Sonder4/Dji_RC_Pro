**Navigation:** [ILTEManager](ILTEManager.md) > [LTELinkInfo](ILTEManager_LTELinkInfo.md)

---

# class LTELinkInfo

|  |
| --- |
| ``` @Keep  class LTELinkInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

LTE link information, including link type and link quality level. `Supported since MSDK 5.2.0`

##### Class Members:

method

[getLTELinkType](#iltemanager_ltelinkinfo_getltelinktype_inline)

###### method getLTELinkType

|  |
| --- |
| ``` LTELinkType getLTELinkType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Getss the LTE link type.

##### Return:

|  |  |
| --- | --- |
| [LTELinkType](../../Components/ILTEManager/ILTEManager.md#iltemanager_ltelinktype) | *Returns the LTE link type.* |

method

[getLinkQualityLevel](#iltemanager_ltelinkinfo_getlinkqualitylevel_inline)

###### method getLinkQualityLevel

|  |
| --- |
| ``` WlmLinkQualityLevelInfo getLinkQualityLevel() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets the LTE link quality level.

##### Return:

|  |  |
| --- | --- |
| [WlmLinkQualityLevelInfo](../../Components/IKeyManager/Value_Airlink_Struct_WlmLinkQualityMsg.md#value_airlink_struct_wlmlinkqualitymsg) | *Returns the LTE link quality level.* |

method

[getAircraftPrivatizationServerInfo](#iltemanager_ltelinkinfo_getaircraftprivatizationserverinfo_inline)

###### method getAircraftPrivatizationServerInfo

|  |
| --- |
| ``` LTEPrivatizationServerInfo getAircraftPrivatizationServerInfo() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets the address of the aircraft LTE privatization server that has been set. If the set IP address is empty, you can call `setLTEAircraftPrivatizationServerInfo` to set it.

##### Return:

|  |  |
| --- | --- |
| [LTEPrivatizationServerInfo](../../Components/ILTEManager/ILTEManager_LTEPrivatizationServerInfo.md#iltemanager_lteprivatizationserverinfo) | *Returns the address of the aircraft LTE privatization server that has been set.* |

method

[getRemoteControllerPrivatizationServerInfo](#iltemanager_ltelinkinfo_getremotecontrollerprivatizationserverinfo_inline)

###### method getRemoteControllerPrivatizationServerInfo

|  |
| --- |
| ``` LTEPrivatizationServerInfo getRemoteControllerPrivatizationServerInfo() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets the address of the remote controller LTE privatization server that has been set. If the set IP address is empty, you can call `setLTERemoteControllerPrivatizationServerInfo` to set it.

##### Return:

|  |  |
| --- | --- |
| [LTEPrivatizationServerInfo](../../Components/ILTEManager/ILTEManager_LTEPrivatizationServerInfo.md#iltemanager_lteprivatizationserverinfo) | *Returns the address of the remote controller LTE privatization server that has been set.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found