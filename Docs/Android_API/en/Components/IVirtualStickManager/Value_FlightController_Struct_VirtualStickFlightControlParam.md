**Navigation:** [IVirtualStickManager](IVirtualStickManager.md) > [VirtualStickFlightControlParam](Value_FlightController_Struct_VirtualStickFlightControlParam.md)

---

# class VirtualStickFlightControlParam

|  |
| --- |
| ``` class VirtualStickFlightControlParam implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Virtual stick advanced control parameters. `Supported since MSDK 5.1.0`

##### Class Members:

method

[setPitch](#value_flightcontroller_struct_virtualstickflightcontrolparam_setpitch_inline)

###### method setPitch

|  |
| --- |
| ``` void setPitch(Double pitch) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the pitch axis control parameters.

##### Input Parameters:

|  |  |
| --- | --- |
| Double pitch | *Pitch axis control parameters.* |

method

[getPitch](#value_flightcontroller_struct_virtualstickflightcontrolparam_getpitch_inline)

###### method getPitch

|  |
| --- |
| ``` Double getPitch() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the pitch axis control parameters.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the pitch axis control parameters.* |

method

[setRoll](#value_flightcontroller_struct_virtualstickflightcontrolparam_setroll_inline)

###### method setRoll

|  |
| --- |
| ``` void setRoll(Double roll) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the roll axis control parameters.

##### Input Parameters:

|  |  |
| --- | --- |
| Double roll | *Roll axis control parameters.* |

method

[getRoll](#value_flightcontroller_struct_virtualstickflightcontrolparam_getroll_inline)

###### method getRoll

|  |
| --- |
| ``` Double getRoll() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the pitch axis control parameters.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the roll axis control parameters.* |

method

[setYaw](#value_flightcontroller_struct_virtualstickflightcontrolparam_setyaw_inline)

###### method setYaw

|  |
| --- |
| ``` void setYaw(Double yaw) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the yaw axis control parameters.

##### Input Parameters:

|  |  |
| --- | --- |
| Double yaw | *Yaw axis control parameters.* |

method

[getYaw](#value_flightcontroller_struct_virtualstickflightcontrolparam_getyaw_inline)

###### method getYaw

|  |
| --- |
| ``` Double getYaw() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the yaw axis control parameters.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the pitch axis control parameters.* |

method

[setVerticalThrottle](#value_flightcontroller_struct_virtualstickflightcontrolparam_setverticalthrottle_inline)

###### method setVerticalThrottle

|  |
| --- |
| ``` void setVerticalThrottle(Double verticalThrottle) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the vertical throttle axis control parameters.

##### Input Parameters:

|  |  |
| --- | --- |
| Double verticalThrottle | *Vertical throttle axis control parameters.* |

method

[getVerticalThrottle](#value_flightcontroller_struct_virtualstickflightcontrolparam_getverticalthrottle_inline)

###### method getVerticalThrottle

|  |
| --- |
| ``` Double getVerticalThrottle() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the vertical throttle axis control parameters.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the vertical throttle axis control parameters.* |

method

[setRollPitchControlMode](#value_flightcontroller_struct_virtualstickflightcontrolparam_setrollpitchcontrolmode_inline)

###### method setRollPitchControlMode

|  |
| --- |
| ``` void setRollPitchControlMode(RollPitchControlMode rollPitchControlMode) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the roll and pitch Control Modes.

##### Input Parameters:

|  |  |
| --- | --- |
| [RollPitchControlMode](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md#value_flightcontroller_enum_rollpitchcontrolmode) rollPitchControlMode | *Roll and pitch Control Modes.* |

method

[getRollPitchControlMode](#value_flightcontroller_struct_virtualstickflightcontrolparam_getrollpitchcontrolmode_inline)

###### method getRollPitchControlMode

|  |
| --- |
| ``` RollPitchControlMode getRollPitchControlMode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the roll and pitch Control Modes.

##### Return:

|  |  |
| --- | --- |
| [RollPitchControlMode](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md#value_flightcontroller_enum_rollpitchcontrolmode) | *Returns the roll and pitch Control Modes.* |

method

[setRollPitchCoordinateSystem](#value_flightcontroller_struct_virtualstickflightcontrolparam_setrollpitchcoordinatesystem_inline)

###### method setRollPitchCoordinateSystem

|  |
| --- |
| ``` void setRollPitchCoordinateSystem(FlightCoordinateSystem rollPitchCoordinateSystem) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the coordinate system. This coordinate system only acts on the roll and pitch in the advanced mode of the virtual stick.

##### Input Parameters:

|  |  |
| --- | --- |
| [FlightCoordinateSystem](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_flightcoordinatesystem) rollPitchCoordinateSystem | *Coordinate system.* |

method

[getRollPitchCoordinateSystem](#value_flightcontroller_struct_virtualstickflightcontrolparam_getrollpitchcoordinatesystem_inline)

###### method getRollPitchCoordinateSystem

|  |
| --- |
| ``` FlightCoordinateSystem getRollPitchCoordinateSystem() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the coordinate system. This coordinate system only acts on the roll and pitch in the advanced mode of the virtual stick.

##### Return:

|  |  |
| --- | --- |
| [FlightCoordinateSystem](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_flightcoordinatesystem) | *Returns the coordinate system.* |

method

[setYawControlMode](#value_flightcontroller_struct_virtualstickflightcontrolparam_setyawcontrolmode_inline)

###### method setYawControlMode

|  |
| --- |
| ``` void setYawControlMode(YawControlMode yawControlMode) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the yaw Control Mode.

##### Input Parameters:

|  |  |
| --- | --- |
| [YawControlMode](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md#value_flightcontroller_enum_yawcontrolmode) yawControlMode | *Yaw control mode.* |

method

[getYawControlMode](#value_flightcontroller_struct_virtualstickflightcontrolparam_getyawcontrolmode_inline)

###### method getYawControlMode

|  |
| --- |
| ``` YawControlMode getYawControlMode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the yaw Control Mode.

##### Return:

|  |  |
| --- | --- |
| [YawControlMode](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md#value_flightcontroller_enum_yawcontrolmode) | *Returns the yaw Control Mode.* |

method

[setVerticalControlMode](#value_flightcontroller_struct_virtualstickflightcontrolparam_setverticalcontrolmode_inline)

###### method setVerticalControlMode

|  |
| --- |
| ``` void setVerticalControlMode(VerticalControlMode verticalControlMode) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Sets the vertical Control Mode.

##### Input Parameters:

|  |  |
| --- | --- |
| [VerticalControlMode](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md#value_flightcontroller_enum_verticalcontrolmode) verticalControlMode | *Vertical control mode.* |

method

[getVerticalControlMode](#value_flightcontroller_struct_virtualstickflightcontrolparam_getverticalcontrolmode_inline)

###### method getVerticalControlMode

|  |
| --- |
| ``` VerticalControlMode getVerticalControlMode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Gets the vertical Control Mode.

##### Return:

|  |  |
| --- | --- |
| [VerticalControlMode](../../Components/IVirtualStickManager/Value_FlightController_Struct_VirtualStickFlightControlParam.md#value_flightcontroller_enum_verticalcontrolmode) | *Retruns the vertical Control Mode.* |

##### Related:

enum

[RollPitchControlMode](#value_flightcontroller_enum_rollpitchcontrolmode_inline)

###### enum RollPitchControlMode

|  |
| --- |
| ``` enum RollPitchControlMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The control mode of the roll and pitch in the advanced mode of the virtual stick.

##### Enum Members:

|  |  |
| --- | --- |
| ANGLE | Angle mode. |
| VELOCITY | Velocity mode. |

##### Class Members:

enum

[YawControlMode](#value_flightcontroller_enum_yawcontrolmode_inline)

###### enum YawControlMode

|  |
| --- |
| ``` enum YawControlMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The control mode of the yaw in the advanced mode of the virtual stick.

##### Enum Members:

|  |  |
| --- | --- |
| ANGLE | Angle mode. |
| ANGULAR\_VELOCITY | Angular velocity mode. |

##### Class Members:

enum

[VerticalControlMode](#value_flightcontroller_enum_verticalcontrolmode_inline)

###### enum VerticalControlMode

|  |
| --- |
| ``` enum VerticalControlMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The control mode of the vertical in the advanced mode of the virtual stick.

##### Enum Members:

|  |  |
| --- | --- |
| VELOCITY | Velocity mode. |
| POSITION | Position mode. |

##### Class Members:

class

[VirtualStickRange](../../Components/IVirtualStickManager/IVirtualStickManager_VirtualStickRange.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found