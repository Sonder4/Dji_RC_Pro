**Navigation:** [IRTKCenter](IRTKCenter.md) > [INetworkRTKManager](INetworkRTKManager.md)

---

# class INetworkRTKManager

|  |
| --- |
| ``` interface INetworkRTKManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

A class that manages network RTK, which is used to initialize and enable the chihiro network RTK, CMCC network RTK or custom network RTK service. `Supported since MSDK 5.0.0`

##### Class Members:

method

[setCustomNetworkRTKSettings](#inetworkrtkmanager_setcustomnetworkrtksettings_inline)

###### method setCustomNetworkRTKSettings

|  |
| --- |
| ``` void setCustomNetworkRTKSettings(RTKCustomNetworkSetting settings) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Configure the settings of custom network RTK. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RTKCustomNetworkSetting](../../Components/IRTKCenter/Value_RtkBaseStation_Struct_RTKCustomNetworkSetting.md#value_rtkbasestation_struct_rtkcustomnetworksetting) settings | *The settings of custom network RTK.* |

method

[getCustomNetworkRTKSettings](#inetworkrtkmanager_getcustomnetworkrtksettings_inline)

###### method getCustomNetworkRTKSettings

|  |
| --- |
| ``` RTKCustomNetworkSetting getCustomNetworkRTKSettings() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the settings of custom network RTK. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [RTKCustomNetworkSetting](../../Components/IRTKCenter/Value_RtkBaseStation_Struct_RTKCustomNetworkSetting.md#value_rtkbasestation_struct_rtkcustomnetworksetting) | *Return the settings of custom network RTK.* |

method

[startNetworkRTKService](#inetworkrtkmanager_startnetworkrtkservicecoordinatesystem_inline)

###### method startNetworkRTKService

|  |
| --- |
| ``` void startNetworkRTKService(CoordinateSystem coordinateSystem, final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start network RTK service. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CoordinateSystem](../../Components/IRTKCenter/INetworkRTKManager.md#rtk_coordinatesystem) coordinateSystem | *Network RTK coordinate system. This parameter only needs to be set when using Chihiro network RTK and CMCC network RTK. If it is not set, the default coordinate system of Chihiro network RTK is `CGCS2000`, and CMCC network RTK must set the coordinate system.* |
| final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[stopNetworkRTKService](#inetworkrtkmanager_stopnetworkrtkservice_inline)

###### method stopNetworkRTKService

|  |
| --- |
| ``` void stopNetworkRTKService(@NonNull final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stop network RTK service. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a result of the execution.* |

method

[getNetworkRTKCoordinateSystem](#inetworkrtkmanager_getnetworkrtkcoordinatesystem_inline)

###### method getNetworkRTKCoordinateSystem

|  |
| --- |
| ``` void getNetworkRTKCoordinateSystem(@NonNull final CommonCallbacks.CompletionCallbackWithParam<CoordinateSystem> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the coordinate system of the network RTK. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[CoordinateSystem](../../Components/IRTKCenter/INetworkRTKManager.md#rtk_coordinatesystem)> callback | *Return a result of the execution.* |

method

[addNetworkRTKServiceInfoListener](#inetworkrtkmanager_addnetworkrtkserviceinfolistener_inline)

###### method addNetworkRTKServiceInfoListener

|  |
| --- |
| ``` void addNetworkRTKServiceInfoListener(@NonNull final INetworkServiceInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of a network RTK service information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [INetworkServiceInfoListener](../../Components/IRTKCenter/INetworkRTKManager_NetworkServiceInfoListener.md#inetworkrtkmanager_networkserviceinfolistener) listener | *The listener of a network RTK service information.* |

method

[removeNetworkRTKServiceInfoListener](#inetworkrtkmanager_removenetworkrtkserviceinfolistener_inline)

###### method removeNetworkRTKServiceInfoListener

|  |
| --- |
| ``` void removeNetworkRTKServiceInfoListener(@NonNull final INetworkServiceInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of a network RTK service information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [INetworkServiceInfoListener](../../Components/IRTKCenter/INetworkRTKManager_NetworkServiceInfoListener.md#inetworkrtkmanager_networkserviceinfolistener) listener | *The listener of a network RTK service information.* |

method

[clearAllNetworkRTKServiceInfoListener](#inetworkrtkmanager_clearallnetworkrtkserviceinfolistener_inline)

###### method clearAllNetworkRTKServiceInfoListener

|  |
| --- |
| ``` void clearAllNetworkRTKServiceInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of a network RTK service information. `Supported since MSDK 5.0.0`

##### Related:

class

[INetworkServiceInfoListener](../../Components/IRTKCenter/INetworkRTKManager_NetworkServiceInfoListener.md)

class

[RTKCustomNetworkSetting](../../Components/IRTKCenter/Value_RtkBaseStation_Struct_RTKCustomNetworkSetting.md)

enum

[CoordinateSystem](#rtk_coordinatesystem_inline)

###### enum CoordinateSystem

|  |
| --- |
| ``` enum  CoordinateSystem ``` |

|  |  |
| --- | --- |
| *Package:* | dji.rtk |

##### Description:

The coordinate system of the network RTK. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| WGS84 | The World Geodetic System in 1984(WGS84). |
| CGCS2000 | China Geodetic Coordinate System in 2000(CGCS2000). |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found