**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [GimbalAngleRotation](Value_Gimbal_Struct_GimbalAngleRotation.md)

---

# class GimbalAngleRotation

|  |
| --- |
| ``` class GimbalAngleRotation implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Define setting information about the rotation of gimbal angle mode. The body coordinate system of the gimbal is used here.

##### Class Members:

#### Members

method

[getMode](#value_gimbal_struct_gimbalanglerotation_getmode_inline)

###### method getMode

|  |
| --- |
| ``` GimbalAngleRotationMode getMode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Return gimbal angle rotation mode.

##### Return:

|  |  |
| --- | --- |
| [GimbalAngleRotationMode](../../Components/IKeyManager/DJIValue.md#value_gimbal_enum_gimbalanglerotationmode) | *Gimbal Angle Rotation Mode* |

method

[setMode](#value_gimbal_struct_gimbalanglerotation_setmode_inline)

###### method setMode

|  |
| --- |
| ``` void setMode(GimbalAngleRotationMode mode) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Set the gimbal angle rotation mode.

##### Input Parameters:

|  |  |
| --- | --- |
| [GimbalAngleRotationMode](../../Components/IKeyManager/DJIValue.md#value_gimbal_enum_gimbalanglerotationmode) mode | *Gimbal angle rotation mode.* |

method

[getPitch](#value_gimbal_struct_gimbalanglerotation_getpitch_inline)

###### method getPitch

|  |
| --- |
| ``` Double getPitch() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Get the pitch angle of gimbal rotation.

##### Return:

|  |  |
| --- | --- |
| Double | *Pitch angle of gimbal rotation.* |

method

[setPitch](#value_gimbal_struct_gimbalanglerotation_setpitch_inline)

###### method setPitch

|  |
| --- |
| ``` void setPitch(Double pitch) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Set pitch angle of gimbal rotation. The positive number is rotated upward, while the negative number is rotated downward. Please setting referring: `getPitch`.

##### Input Parameters:

|  |  |
| --- | --- |
| Double pitch | *Pitch angle of gimbal rotation.* |

method

[getYaw](#value_gimbal_struct_gimbalanglerotation_getyaw_inline)

###### method getYaw

|  |
| --- |
| ``` Double getYaw() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Get the yaw angle of gimbal rotation.

##### Return:

|  |  |
| --- | --- |
| Double | *Yaw angle of gimbal rotation.* |

method

[setYaw](#value_gimbal_struct_gimbalanglerotation_setyaw_inline)

###### method setYaw

|  |
| --- |
| ``` void setYaw(Double yaw) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Set the yaw angle of gimbal rotation. The positive number is rotated right, while negative number is rotated left. Please setting referring: `getYaw`.

##### Input Parameters:

|  |  |
| --- | --- |
| Double yaw | *Yaw angle of gimbal rotation.* |

method

[getRoll](#value_gimbal_struct_gimbalanglerotation_getroll_inline)

###### method getRoll

|  |
| --- |
| ``` Double getRoll() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Get the yaw angle of gimbal rotation.

##### Return:

|  |  |
| --- | --- |
| Double | *Roll angle of gimbal rotation.* |

method

[setRoll](#value_gimbal_struct_gimbalanglerotation_setroll_inline)

###### method setRoll

|  |
| --- |
| ``` void setRoll(Double roll) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Set the roll angle of gimbal rotation. Positive number is rotated right, while negative number is rotated left. Please refer to `getRoll` to set.

##### Input Parameters:

|  |  |
| --- | --- |
| Double roll | *Roll angle of gimbal rotation.* |

method

[getDuration](#value_gimbal_struct_gimbalanglerotation_getduration_inline)

###### method getDuration

|  |
| --- |
| ``` Double getDuration() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Get the time of gimbal rotation operation, which does not take effect in waypoint mission.

##### Return:

|  |  |
| --- | --- |
| Double | *Time of gimbal rotation operation. Unit:second.* |

method

[setDuration](#value_gimbal_struct_gimbalanglerotation_setduration_inline)

###### method setDuration

|  |
| --- |
| ``` void setDuration(Double duration) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Set the time of gimbal rotation operation, which does not take effect in the waypoint mission.

##### Input Parameters:

|  |  |
| --- | --- |
| Double duration | *Time of gimbal rotation operation. Unit:second.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found