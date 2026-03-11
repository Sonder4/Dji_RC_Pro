**Navigation:** [IRTKCenter](IRTKCenter.md) > [INetworkRTKManager](INetworkRTKManager.md) > [INetworkServiceInfoListener](INetworkRTKManager_NetworkServiceInfoListener.md)

---

# class INetworkServiceInfoListener

|  |
| --- |
| ``` interface INetworkServiceInfoListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk.network |

##### Description:

The listener of network service information. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onServiceStateUpdate](#inetworkrtkmanager_networkserviceinfolistener_onservicestateupdate_inline)

###### method onServiceStateUpdate

|  |
| --- |
| ``` void onServiceStateUpdate(RTKServiceState state) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk.network |

##### Description:

This method will be called when the network RTK state is changed. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| RTKServiceState state | *Network RTK state* |

method

[onErrorCodeUpdate](#inetworkrtkmanager_networkserviceinfolistener_onerrorcodeupdate_inline)

###### method onErrorCodeUpdate

|  |
| --- |
| ``` void onErrorCodeUpdate(IDJIError code) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.rtk.network |

##### Description:

This method will be called when the network RTK has any errors. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) code | *Error code of the network RTK* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found