**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [CameraWhiteBalanceInfo](Value_Camera_Struct_CameraWhiteBalance.md)

---

# class CameraWhiteBalanceInfo

|  |
| --- |
| ``` class CameraWhiteBalanceInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Setting of white balance.

##### Class Members:

#### Members

method

[getWhiteBalanceMode](#value_camera_struct_camerawhitebalance_getmode_inline)

###### method getWhiteBalanceMode

|  |
| --- |
| ``` CameraWhiteBalanceMode getWhiteBalanceMode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get setted White Balance.

##### Return:

|  |  |
| --- | --- |
| [CameraWhiteBalanceMode](../../Components/IKeyManager/DJIValue.md#value_camera_enum_camerawhitebalancemode) | *Return setted White Balance.* |

method

[setWhiteBalanceMode](#value_camera_struct_camerawhitebalance_setmode_inline)

###### method setWhiteBalanceMode

|  |
| --- |
| ``` void setWhiteBalanceMode(CameraWhiteBalanceMode whiteBalanceMode) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set White Balance.

##### Input Parameters:

|  |  |
| --- | --- |
| [CameraWhiteBalanceMode](../../Components/IKeyManager/DJIValue.md#value_camera_enum_camerawhitebalancemode) whiteBalanceMode | *White Balance that needs to be set.* |

method

[getColorTemperature](#value_camera_struct_camerawhitebalance_getcolortemperature_inline)

###### method getColorTemperature

|  |
| --- |
| ``` Integer getColorTemperature() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get setted color temperature. Real color temperature value (K) = value \* 100. For example, 50 -> 5000K.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return setted color temperature.* |

method

[setColorTemperature](#value_camera_struct_camerawhitebalance_setcolortemperature_inline)

###### method setColorTemperature

|  |
| --- |
| ``` void setColorTemperature(Integer colorTemperature) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set color temperatur. Unit: kelvin. The color temperature can only be set when the white balance mode is set to `MANUAL`.

##### Input Parameters:

|  |  |
| --- | --- |
| Integer colorTemperature | *Color temperature value to be set in the range of [20, 100]. Real color temperature value (K) = value \* 100. For example, 50 -> 5000K.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found