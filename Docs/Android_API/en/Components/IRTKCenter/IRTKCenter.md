**Navigation:** [IRTKCenter](IRTKCenter.md)

---

# class IRTKCenter

|  |
| --- |
| ``` interface IRTKCenter ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The class to manage RTK module. It is used to get an instance of chihiro network RTK, CMCC network RTK, ustom network RTK or RTK base station and provides the initialization of chihiro network RTK or custom network RTK, the connection and configuration of RTK base station. **Note: When calling `KeyMultiControlRequestPairing` pair the remote controller to `CHANNEL_B`, RTK functions will not be available.** `Supported since MSDK 5.0.0`

##### Class Members:

method

[getQXRTKManager](#irtkcenter_getqxrtkmanager_inline)

###### method getQXRTKManager

|  |
| --- |
| ``` INetworkRTKManager getQXRTKManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get an instance of chihiro NetworkRTKManager. It is used to initialize and enable chihiro network RTK service. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [INetworkRTKManager](../../Components/IRTKCenter/INetworkRTKManager.md#inetworkrtkmanager) | *Return an instance of chihiro NetworkRTKManager.* |

method

[getCMCCRTKManager](#irtkcenter_getcmccrtkmanager_inline)

###### method getCMCCRTKManager

|  |
| --- |
| ``` INetworkRTKManager getCMCCRTKManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get an instance of CMCC NetworkRTKManager. It is used to initialize and enable CMCC network RTK service. `Supported since MSDK 5.1.0`

##### Return:

|  |  |
| --- | --- |
| [INetworkRTKManager](../../Components/IRTKCenter/INetworkRTKManager.md#inetworkrtkmanager) | *Return an instance of CMCC NetworkRTKManager.* |

method

[getCustomRTKManager](#irtkcenter_getcustomrtkmanager_inline)

###### method getCustomRTKManager

|  |
| --- |
| ``` INetworkRTKManager getCustomRTKManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get an instance of custom NetworkRTKManager. It is used to initialize and enable custom network RTK service. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [INetworkRTKManager](../../Components/IRTKCenter/INetworkRTKManager.md#inetworkrtkmanager) | *Return an instance of custom NetworkRTKManager.* |

method

[getRTKStationManager](#irtkcenter_getrtkstationmanager_inline)

###### method getRTKStationManager

|  |
| --- |
| ``` IRTKStationManager getRTKStationManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get an instance of RTKStationManager. It is used to connect and configure the RTK base station. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [IRTKStationManager](../../Components/IRTKCenter/IRTKStationManager.md#irtkstationmanager) | *Return an instance of RTKStationManager.* |

method

[setAircraftRTKModuleEnabled](#irtkcenter_setaircraftrtkmoduleenabled_inline)

###### method setAircraftRTKModuleEnabled

|  |
| --- |
| ``` void setAircraftRTKModuleEnabled(boolean isEnabled, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enable or disable the RTK module of an aircraft. You must set this method before starting the motor, otherwise, this method is ineffective. Pass`true`in means to enable the RTK module. The aircraft has to read RTK data to take off. Also, the flight controller must use the accurate location data given by the RTK module. Pass`false`means to disable the RTK module, the flight controller will no longer use the location data from the RTK module. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true`means to enable the RTK module of an aircraft.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of execution.* |

method

[getAircraftRTKModuleEnabled](#irtkcenter_getaircraftrtkmoduleenabled_inline)

###### method getAircraftRTKModuleEnabled

|  |
| --- |
| ``` void getAircraftRTKModuleEnabled(CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get whether the RTK module of an aircraft is enabled or disabled. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the result of execution.* |

method

[setRTKReferenceStationSource](#irtkcenter_setrtkreferencestationsource_inline)

###### method setRTKReferenceStationSource

|  |
| --- |
| ``` void setRTKReferenceStationSource(RTKReferenceStationSource source, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the RTK reference station source of an aircraft. The RTK module of an aircraft provides chihiro network RTK, CMCC network RTK, custom network RTK and DJI's D-RTK 2 base station to select. **Note: After setting the RTK reference station source, you should restart the aircraft.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RTKReferenceStationSource](../../Components/IRTKCenter/IRTKCenter.md#value_rtkbasestation_enum_rtkreferencestationsource) source | *The RTK reference station source of an aircraft* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of execution.* |

method

[getRTKReferenceStationSource](#irtkcenter_getrtkreferencestationsource_inline)

###### method getRTKReferenceStationSource

|  |
| --- |
| ``` void getRTKReferenceStationSource(CommonCallbacks.CompletionCallbackWithParam<RTKReferenceStationSource> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the RTK reference station source of an aircraft. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[RTKReferenceStationSource](../../Components/IRTKCenter/IRTKCenter.md#value_rtkbasestation_enum_rtkreferencestationsource)> callback | *Return the result of execution.* |

method

[setRTKMaintainAccuracyEnabled](#irtkcenter_setrtkmaintainaccuracyenabled_inline)

###### method setRTKMaintainAccuracyEnabled

|  |
| --- |
| ``` void setRTKMaintainAccuracyEnabled(boolean enabled, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

enabled or disabled maintain positioning accuracy mode. In case of an RTK module communication error, the aircraft will automatic maintain the current RTK status with gradually decreasing accurcy. The aircraft will exit RTK if the connection is not re-established within 10 minutes. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean enabled | *’‘TRUE‘’ to enabled maintain positioning accuracy mode.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of execution.* |

method

[getRTKMaintainAccuracyEnabled](#irtkcenter_getrtkmaintainaccuracyenabled_inline)

###### method getRTKMaintainAccuracyEnabled

|  |
| --- |
| ``` void getRTKMaintainAccuracyEnabled(CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get if the maintain positioning accuracy mode is enabled. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the result of execution.* |

method

[addRTKLocationInfoListener](#irtkcenter_addrtklocationinfolistener_inline)

###### method addRTKLocationInfoListener

|  |
| --- |
| ``` void addRTKLocationInfoListener(RTKLocationInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of RTK location information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RTKLocationInfoListener](../../Components/IRTKCenter/IRTKCenter_RTKLocationInfoListener.md#irtkcenter_rtklocationinfolistener) listener | *The listener of RTK location information.* |

method

[removeRTKLocationInfoListener](#irtkcenter_removertklocationinfolistener_inline)

###### method removeRTKLocationInfoListener

|  |
| --- |
| ``` void removeRTKLocationInfoListener(RTKLocationInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of RTK location information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RTKLocationInfoListener](../../Components/IRTKCenter/IRTKCenter_RTKLocationInfoListener.md#irtkcenter_rtklocationinfolistener) listener | *The listener of RTK location information.* |

method

[clearAllRTKLocationInfoListener](#irtkcenter_clearallrtklocationinfolistener_inline)

###### method clearAllRTKLocationInfoListener

|  |
| --- |
| ``` void clearAllRTKLocationInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listener of RTK location information. `Supported since MSDK 5.0.0`

method

[addRTKSystemStateListener](#irtkcenter_addrtksystemstatelistener_inline)

###### method addRTKSystemStateListener

|  |
| --- |
| ``` void addRTKSystemStateListener(RTKSystemStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of the RTK system state. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RTKSystemStateListener](../../Components/IRTKCenter/IRTKCenter_RTKSystemStateListener.md#irtkcenter_rtksystemstatelistener) listener | *The listener of RTK system state.* |

method

[removeRTKSystemStateListener](#irtkcenter_removertksystemstatelistener_inline)

###### method removeRTKSystemStateListener

|  |
| --- |
| ``` void removeRTKSystemStateListener(RTKSystemStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of the RTK system state. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RTKSystemStateListener](../../Components/IRTKCenter/IRTKCenter_RTKSystemStateListener.md#irtkcenter_rtksystemstatelistener) listener | *The listener of RTK system state.* |

method

[clearAllRTKSystemStateListener](#irtkcenter_clearallrtksystemstatelistener_inline)

###### method clearAllRTKSystemStateListener

|  |
| --- |
| ``` void clearAllRTKSystemStateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of the RTK system state. `Supported since MSDK 5.0.0`

##### Related:

class

[INetworkRTKManager](../../Components/IRTKCenter/INetworkRTKManager.md)

class

[IRTKStationManager](../../Components/IRTKCenter/IRTKStationManager.md)

enum

[RTKReferenceStationSource](#value_rtkbasestation_enum_rtkreferencestationsource_inline)

###### enum RTKReferenceStationSource

|  |
| --- |
| ``` enum RTKReferenceStationSource implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkbasestation |

##### Description:

The reference station source of an RTK module. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| QX\_NETWORK\_SERVICE | Chihiro netowrk RTK reference station. |
| NTRIP\_NETWORK\_SERVICE | NTRIP netowrk RTK reference station. If you want to use CMCC network RTK, please set this type. |
| CUSTOM\_NETWORK\_SERVICE | Custom netowrk RTK reference station. |
| BASE\_STATION | RTK base station reference station. |

##### Class Members:

class

[RTKLocationInfo](../../Components/IRTKCenter/IRTKCenter_RTKLocationInfo.md)

class

[RTKLocation](../../Components/IRTKCenter/Value_RtkMobileStation_Struct_RTKLocation.md)

enum

[RTKPositioningSolution](#value_rtkmobilestation_enum_rtkpositioningsolution_inline)

###### enum RTKPositioningSolution

|  |
| --- |
| ``` enum RTKPositioningSolution implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

RTK positioning solution. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| NONE | No RTK positioning solution. Possible reason: No enough satellites in view, not enough time to lock satellites, and the connection between aircraft and base station is broken. |
| SINGLE\_POINT | Single Point state. |
| FLOAT | Float state. |
| FIXED\_POINT | Fixed Point state, most accurate state. |

##### Class Members:

class

[RTKSystemState](../../Components/IRTKCenter/RTKCenter_RTKSystemState.md)

class

[RTKSatelliteInfo](../../Components/IRTKCenter/Value_RtkMobileStation_Struct_RTKSatelliteInfo.md)

class

[RTKReceiverInfo](../../Components/IRTKCenter/Value_RtkMobileStation_Struct_RTKReceiverInfo.md)

enum

[GNSSType](#value_rtkmobilestation_enum_gnsstype_inline)

###### enum GNSSType

|  |
| --- |
| ``` enum GNSSType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkmobilestation |

##### Description:

GNSS system type. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| GPS | GPS system. |
| GLONASS | GLONASS system. |
| GALILEO | GALILEO system. |
| BEIDOU | BEIDOU system. |

##### Class Members:

class

[RTKLocationInfoListener](../../Components/IRTKCenter/IRTKCenter_RTKLocationInfoListener.md)

class

[RTKSystemStateListener](../../Components/IRTKCenter/IRTKCenter_RTKSystemStateListener.md)

class

[RTKBaseListener](../../Components/IRTKCenter/IRTKCenter_RTKBaseListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found