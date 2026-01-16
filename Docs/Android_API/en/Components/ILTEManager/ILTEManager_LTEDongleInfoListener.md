**Navigation:** [ILTEManager](ILTEManager.md) > [LTEDongleInfoListener](ILTEManager_LTEDongleInfoListener.md)

---

# class LTEDongleInfoListener

|  |
| --- |
| ``` @Keep  interface LTEDongleInfoListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Listener for LTE dongle information. `Supported since MSDK 5.2.0`

##### Class Members:

method

[onLTEAircraftDongleInfoUpdate](#iltemanager_ltedongleinfolistener_onlteaircraftdongleinfoupdate_inline)

###### method onLTEAircraftDongleInfoUpdate

|  |
| --- |
| ``` void onLTEAircraftDongleInfoUpdate(@NonNull List<WlmDongleInfo> aircraftDongleInfos) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

When the aircraft dongle Information changes, this method will be called back. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull List<[WlmDongleInfo](../../Components/IKeyManager/Value_Airlink_Struct_WlmDongleState.md#value_airlink_struct_wlmdonglestate)> aircraftDongleInfos | *Aircraft dongle Information.* |

method

[onLTERemoteControllerDongleInfoUpdate](#iltemanager_ltedongleinfolistener_onlteremotecontrollerdongleinfoupdate_inline)

###### method onLTERemoteControllerDongleInfoUpdate

|  |
| --- |
| ``` void onLTERemoteControllerDongleInfoUpdate(@NonNull List<WlmDongleInfo> remoteControllerDongleInfos) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

When the remote controller dongle Information changes, this method will be called back. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull List<[WlmDongleInfo](../../Components/IKeyManager/Value_Airlink_Struct_WlmDongleState.md#value_airlink_struct_wlmdonglestate)> remoteControllerDongleInfos | *Remote controller dongle Information.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found