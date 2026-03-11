**Navigation:** [IVirtualStickManager](IVirtualStickManager.md) > [VirtualStickStateListener](IVirtualStickManager_VirtualStickStateListener.md)

---

# class VirtualStickStateListener

|  |
| --- |
| ``` interface VirtualStickStateListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

Listener of the virtual stick status. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onVirtualStickStateUpdate](#ivirtualstickmanager_virtualstickstatelistener_onvirtualstickstateupdate_inline)

###### method onVirtualStickStateUpdate

|  |
| --- |
| ``` void onVirtualStickStateUpdate(@NonNull VirtualStickState stickState) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

When the virtual stick status is changed, this function will be called back. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [VirtualStickState](../../Components/IVirtualStickManager/IVirtualStickManager_VirtualStickState.md#ivirtualstickmanager_virtualstickstate) stickState | *Virtual Stiack Status* |

method

[onChangeReasonUpdate](#ivirtualstickmanager_virtualstickstatelistener_onchangereasonupdate_inline)

###### method onChangeReasonUpdate

|  |
| --- |
| ``` void onChangeReasonUpdate(@NonNull FlightControlAuthorityChangeReason reason) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

When the flight control authority is changed,, this function will be called back.s `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlightControlAuthorityChangeReason](../../Components/IVirtualStickManager/IVirtualStickManager.md#value_flightcontroller_enum_flightcontrolauthoritychangereason) reason | *Flight control authority change reason.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found