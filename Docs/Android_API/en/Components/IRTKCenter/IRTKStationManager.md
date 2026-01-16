**Navigation:** [IRTKCenter](IRTKCenter.md) > [IRTKStationManager](IRTKStationManager.md)

---

# class IRTKStationManager

|  |
| --- |
| ``` interface IRTKStationManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

A class that manages the RTK base station, which is used to connect and configure the RTK base station. `Supported since MSDK 5.0.0`

##### Class Members:

method

[startSearchRTKStation](#irtkstationmanager_startsearchrtkstation_inline)

###### method startSearchRTKStation

|  |
| --- |
| ``` void startSearchRTKStation(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start searching the RTK base station. You can use`addSearchRTKStationListener`to add a listener to monitor this process. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[stopSearchRTKStation](#irtkstationmanager_stopsearchrtkstation_inline)

###### method stopSearchRTKStation

|  |
| --- |
| ``` void stopSearchRTKStation(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stop searching the RTK base station. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[startConnectToRTKStation](#irtkstationmanager_startconnecttortkstation_inline)

###### method startConnectToRTKStation

|  |
| --- |
| ``` void startConnectToRTKStation(int stationId, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Connect to the RTK station through base station id. You can get base station id from`RTKStationInfo`by calling `SearchRTKStationListener`. After connecting to the RTk base station, you can monitor the connection status by calling`addRTKStationConnectStatusListener` and monitor the base station information by calling`addConnectedRTKStationInfoListener`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int stationId | *RTK bse station id.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[addSearchRTKStationListener](#irtkstationmanager_addsearchrtkstationlistener_inline)

###### method addSearchRTKStationListener

|  |
| --- |
| ``` void addSearchRTKStationListener(@NonNull SearchRTKStationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of RTK base station search result. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [SearchRTKStationListener](../../Components/IRTKCenter/IRTKStationManager_SearchStationListener.md#irtkstationmanager_searchstationlistener) listener | *The listener of RTK base station search result.* |

method

[removeSearchRTKStationListener](#irtkstationmanager_removesearchrtkstationlistener_inline)

###### method removeSearchRTKStationListener

|  |
| --- |
| ``` void removeSearchRTKStationListener(@NonNull SearchRTKStationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of the RTK base station search result. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [SearchRTKStationListener](../../Components/IRTKCenter/IRTKStationManager_SearchStationListener.md#irtkstationmanager_searchstationlistener) listener | *The listener of RTK base station search result.* |

method

[clearAllSearchRTKStationListener](#irtkstationmanager_clearallsearchrtkstationlistener_inline)

###### method clearAllSearchRTKStationListener

|  |
| --- |
| ``` void clearAllSearchRTKStationListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of RTK base station search results. `Supported since MSDK 5.0.0`

method

[addRTKStationConnectStatusListener](#irtkstationmanager_addrtkstationconnectstatuslistener_inline)

###### method addRTKStationConnectStatusListener

|  |
| --- |
| ``` void addRTKStationConnectStatusListener(@NonNull RTKStationConnectStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of RTK base station connection status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [RTKStationConnectStatusListener](../../Components/IRTKCenter/IRTKStationManager_RTKStationConnectStatusListener.md#irtkstationmanager_rtkstationconnectstatuslistener) listener | *The listener of RTK base station connection status.* |

method

[removeRTKStationConnectStatusListener](#irtkstationmanager_removertkstationconnectstatuslistener_inline)

###### method removeRTKStationConnectStatusListener

|  |
| --- |
| ``` void removeRTKStationConnectStatusListener(@NonNull RTKStationConnectStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of RTK base station connection status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [RTKStationConnectStatusListener](../../Components/IRTKCenter/IRTKStationManager_RTKStationConnectStatusListener.md#irtkstationmanager_rtkstationconnectstatuslistener) listener | *The listener of RTK base station connection status.* |

method

[clearAllRTKStationConnectStatusListener](#irtkstationmanager_clearallrtkstationconnectstatuslistener_inline)

###### method clearAllRTKStationConnectStatusListener

|  |
| --- |
| ``` void clearAllRTKStationConnectStatusListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of RTK base station connection status. `Supported since MSDK 5.0.0`

method

[addConnectedRTKStationInfoListener](#irtkstationmanager_addconnectedrtkstationinfolistener_inline)

###### method addConnectedRTKStationInfoListener

|  |
| --- |
| ``` void addConnectedRTKStationInfoListener(@NonNull ConnectedRTKStationInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of RTK base station information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [ConnectedRTKStationInfoListener](../../Components/IRTKCenter/IRTKStationManager_ConnectedRTKStationInfoListener.md#irtkstationmanager_connectedrtkstationinfolistener) listener | *The listener of RTK base station information.* |

method

[removeConnectedRTKStationInfoListener](#irtkstationmanager_removeconnectedrtkstationinfolistener_inline)

###### method removeConnectedRTKStationInfoListener

|  |
| --- |
| ``` void removeConnectedRTKStationInfoListener(@NonNull ConnectedRTKStationInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of RTK base station information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [ConnectedRTKStationInfoListener](../../Components/IRTKCenter/IRTKStationManager_ConnectedRTKStationInfoListener.md#irtkstationmanager_connectedrtkstationinfolistener) listener | *The listener of RTK base station information.* |

method

[clearAllConnectedRTKStationInfoListener](#irtkstationmanager_clearallconnectedrtkstationinfolistener_inline)

###### method clearAllConnectedRTKStationInfoListener

|  |
| --- |
| ``` void clearAllConnectedRTKStationInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of RTK base station information. `Supported since MSDK 5.0.0`

Advanced settings

method

[loginRTKStation](#irtkstationmanager_loginrtkstation_inline)

###### method loginRTKStation

|  |
| --- |
| ``` void loginRTKStation(String password, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Login to the RTK base station. You can configure the advanced settings of the RTK base station after successful login. The password has to be starting from 0 to 9 with 6 digits. The default password is "123456". `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String password | *login password.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[setRTKStationReferencePosition](#irtkstationmanager_setrtkstationreferenceposition_inline)

###### method setRTKStationReferencePosition

|  |
| --- |
| ``` void setRTKStationReferencePosition(LocationCoordinate3D locationCoordinate3D, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the RTK base station reference position, when a user can get a more accurate location of the base station by using extrnal tools, the accurate location can be set to the base station to imporve the accuracy of the aircraft locaiton. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) locationCoordinate3D | *RTK base station reference position.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[getRTKStationReferencePosition](#irtkstationmanager_getrtkstationreferenceposition_inline)

###### method getRTKStationReferencePosition

|  |
| --- |
| ``` void getRTKStationReferencePosition(@NonNull CommonCallbacks.CompletionCallbackWithParam<LocationCoordinate3D> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the RTK base station reference position. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d)> callback | *Return a result of the execution.* |

method

[resetRTKStationReferencePosition](#irtkstationmanager_resetrtkstationreferenceposition_inline)

###### method resetRTKStationReferencePosition

|  |
| --- |
| ``` void resetRTKStationReferencePosition(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Reset the RTK base station reference position to {1,1,1}. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[resetRTKStationPassword](#irtkstationmanager_resetrtkstationpassword_inline)

###### method resetRTKStationPassword

|  |
| --- |
| ``` void resetRTKStationPassword(RTKBaseStationResetPasswordInfo passwordInfo, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Reset the password of the RTK base station. Note: The password has to be starting from 0 to 9 with 6 digits. The default password is "123456". `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| RTKBaseStationResetPasswordInfo passwordInfo | *The password of the RTK base station.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[setRTKStationName](#irtkstationmanager_setrtkstationname_inline)

###### method setRTKStationName

|  |
| --- |
| ``` void setRTKStationName(String name, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the name of the RTK base station. Tye name of the RTK base station is encoded by UTF-8, 4 characters in total. If you set the name as "abcdef", it will be saved as "abcd". `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String name | *The name of the RTK base station.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

##### Related:

class

[RTKStationInfo](../../Components/IRTKCenter/Value_RtkBaseStation_Struct_RTKBaseStationConnectInfo.md)

enum

[RTKStationConnetState](#value_rtkbasestation_enum_rtkbasestationconnectstate_inline)

###### enum RTKStationConnetState

|  |
| --- |
| ``` enum RTKStationConnetState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.rtkbasestation |

##### Description:

The connection state of RTK base station. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Idle. |
| SCANNING | Scanning. |
| CONNECTING | Connecting. |
| CONNECTED | Connected. |
| DISCONNECTED | Disconnected. |

##### Class Members:

class

[ConnectedRTKStationInfo](../../Components/IRTKCenter/IRTKStationManager_ConnectedTKStationInfo.md)

class

[SearchRTKStationListener](../../Components/IRTKCenter/IRTKStationManager_SearchStationListener.md)

class

[RTKStationConnectStatusListener](../../Components/IRTKCenter/IRTKStationManager_RTKStationConnectStatusListener.md)

class

[ConnectedRTKStationInfoListener](../../Components/IRTKCenter/IRTKStationManager_ConnectedRTKStationInfoListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found