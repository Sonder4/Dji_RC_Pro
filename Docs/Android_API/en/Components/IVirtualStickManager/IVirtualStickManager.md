**Navigation:** [IVirtualStickManager](IVirtualStickManager.md)

---

# class IVirtualStickManager

|  |
| --- |
| ``` interface IVirtualStickManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage virtual stick. It is used to enable/disable the virtual stick mode. It also provides the ability to listen the virtual stick values. `Supported since MSDK 5.0.0`

##### Class Members:

method

[enableVirtualStick](#ivirtualstickmanager_enablevirtualstick_inline)

###### method enableVirtualStick

|  |
| --- |
| ``` void enableVirtualStick(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Open the virtual stick mode. **Note: 1. For flight safety, when the aircraft is close(about 30 meters) to the restricted flight zone or restricted distance, the remote controller will get the control of the aircraft, and the virtual stick cannot be used. 2. The virtual stick normal mode supports obstacle avoidance by default. 3. In the virtual stick advanced mode, obstacle avoidance is supported only when the vertical control Mode is velocity mode, the yaw control mode is angular velocity mode, and the roll pitch control mode is velocity mode. 4. Currently, only M300 RTK, M350 RTK, M30 series, Mavic 3E series and Mavic 3M aircraft support obstacle avoidance in virtual joystick mode. 5. For aircraft models Mavic 3E series, and Mavic 3M, if the firmware version is below V7.01.10.03, obstacle sensing will be disabled when virtual stick mode is activated. We recommend updating the aircraft to the latest firmware version for optimal functionality.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[disableVirtualStick](#ivirtualstickmanager_disablevirtualstick_inline)

###### method disableVirtualStick

|  |
| --- |
| ``` void disableVirtualStick(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Close the virtual stick mode. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getLeftStick](#ivirtualstickmanager_getleftstick_inline)

###### method getLeftStick

|  |
| --- |
| ``` IStick getLeftStick() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To get the left stick instance of the virtual stick. Left stick controls the yaw axis and throttle of the aircraft. - When the stick moves left, the horizontalPosition is negative. The aircraft will rotate counterclockwise when looking down at the aircraft. - When the stick moves right, the horizontalPosition is positive. The aircraft will rotate clockwise when looking down at the aircraft. - When the stick moves upward, the verticalPosition is positive. The aircraft will increase its altitude. - When the stick moves downward, the verticalPosition is negative. The aircraft will lower its altitude. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [IStick](../../Components/IVirtualStickManager/IVirtualStickManager_DJIStick.md#ivirtualstickmanager_djistick) | *Return the instance of left stick.* |

method

[getRightStick](#ivirtualstickmanager_getrightstick_inline)

###### method getRightStick

|  |
| --- |
| ``` IStick getRightStick() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To get the right stick instance of the virtual stick. Right stick controls the roll axis and pitch axis of the aircraft. - When the stick moves left, the horizontalPosition is negative. The aircraft will fly left when looking down at the aircraft. - When the stick moves right, the horizontalPosition is positive. The aircraft will fly right when looking down at the aircraft. - When the stick moves upward, the verticalPosition is positive. The aircraft will fly forward when looking down at the aircraft. - When the stick moves downward, the verticalPosition is negative. The aircraft will fly backward when looking down at the aircraft. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [IStick](../../Components/IVirtualStickManager/IVirtualStickManager_DJIStick.md#ivirtualstickmanager_djistick) | *Return the instance of right stick.* |

method

[setVirtualStickAdvancedModeEnabled](#ivirtualstickmanager_setvirtualstickadvancedmodeenabled_inline)

###### method setVirtualStickAdvancedModeEnabled

|  |
| --- |
| ``` void setVirtualStickAdvancedModeEnabled(boolean isEnabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enable virtual stick advanced mode. In advanced mode, the control data of left stick `getLeftStick` and right stick `getLeftStick` will no longer take effect, you can call `sendVirtualStickAdvancedParam` to set more advanced control parameters for use with more complex application scenarios, such as using a virtual stick to implement waypoint-related functions. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true` means enable virtual stick advanced mode.* |

method

[sendVirtualStickAdvancedParam](#ivirtualstickmanager_sendvirtualstickadvancedparam_inline)

###### method sendVirtualStickAdvancedParam

|  |
| --- |
| ``` void sendVirtualStickAdvancedParam(@NonNull VirtualStickFlightControlParam param) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Send virtual stick advanced control parameters.- Please call `setVirtualStickAdvancedModeEnabled` to enable advanced mode before using this interface. - Developers can send control parameters at a specific frequency according to their own needs. The recommended sending frequency is between 5Hz and 25Hz. - For detailed usage of advanced control parameters, please refer to [Virtual stick](https://developer.dji.com/doc/mobile-sdk-tutorial/en/basic-introduction/basic-concepts/flight-controller.md#virtual-sticks) - For advanced control parameter ranges, please refer to: `VirtualStickRange`. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [VirtualStickFlightControlParam](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md#value_flightcontroller_struct_virtualstickflightcontrolparam) param | *Advanced mode parameters.* |

method

[setVirtualStickStateListener](#ivirtualstickmanager_setvirtualstickstatelistener_inline)

###### method setVirtualStickStateListener

|  |
| --- |
| ``` void setVirtualStickStateListener(VirtualStickStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To set the listener of the virtual stick status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [VirtualStickStateListener](../../Components/IVirtualStickManager/IVirtualStickManager_VirtualStickStateListener.md#ivirtualstickmanager_virtualstickstatelistener) listener | *Listener of the virtual stick status* |

method

[removeVirtualStickStateListener](#ivirtualstickmanager_removevirtualstickstatelistener_inline)

###### method removeVirtualStickStateListener

|  |
| --- |
| ``` void removeVirtualStickStateListener(VirtualStickStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of the virtual stick status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [VirtualStickStateListener](../../Components/IVirtualStickManager/IVirtualStickManager_VirtualStickStateListener.md#ivirtualstickmanager_virtualstickstatelistener) listener | *Listener of the virtual stick status* |

method

[clearAllVirtualStickStateListener](#ivirtualstickmanager_clearallvirtualstickstatelistener_inline)

###### method clearAllVirtualStickStateListener

|  |
| --- |
| ``` void clearAllVirtualStickStateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clean all the listener of the virtual stick status. `Supported since MSDK 5.0.0`

##### Related:

class

[IStick](../../Components/IVirtualStickManager/IVirtualStickManager_DJIStick.md)

class

[VirtualStickStateListener](../../Components/IVirtualStickManager/IVirtualStickManager_VirtualStickStateListener.md)

class

[VirtualStickFlightControlParam](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md)

class

[VirtualStickState](../../Components/IVirtualStickManager/IVirtualStickManager_VirtualStickState.md)

enum

[FlightControlAuthority](#value_flightcontroller_enum_flightcontrolauthority_inline)

###### enum FlightControlAuthority

|  |
| --- |
| ``` enum FlightControlAuthority implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Flight control authority. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| RC | Remote controller. |
| MSDK | MSDK. |
| OSDK | OSDK. |

##### Class Members:

enum

[FlightControlAuthorityChangeReason](#value_flightcontroller_enum_flightcontrolauthoritychangereason_inline)

###### enum FlightControlAuthorityChangeReason

|  |
| --- |
| ``` enum FlightControlAuthorityChangeReason implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Flight control authority change reason. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| MSDK\_REQUEST | MSDK got the flight control authority. |
| OSDK\_REQUEST | OSDK got the flight control authority. |
| RC\_LOST | Remote controller lost contact, reset flight control authority to the remote controller. |
| RC\_NOT\_P\_MODE | The flight mode of the aircraft is not P mode, reset flight control authority to the remote controller. |
| RC\_SWITCH | The flight mode of the aircraft is switched, reset flight control authority to the remote controller. |
| RC\_PAUSE\_STOP | The pause button is pressed, reset flight control authority to the remote controller. |
| RC\_ONE\_KEY\_GO\_HOME | The aircraft is going home, reset flight control authority to the remote controller. |
| BATTERY\_LOW\_GO\_HOME | The aircraft is going home because of the low battery, reset flight control authority to the remote controller. |
| BATTERY\_SUPER\_LOW\_LANDING | The aircraft is landing because of the low battery, reset flight control authority to the remote controller. |
| OSDK\_LOST | OSDK lost contact, reset flight control authority to the remote controller. |
| NEAR\_BOUNDARY | The aircraft is nearby the fly zone or restricted distance, reset flight control authority to the remote controller. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found