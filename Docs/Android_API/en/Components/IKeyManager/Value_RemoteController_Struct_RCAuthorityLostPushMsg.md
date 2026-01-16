**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [MultiControlLostControlInfo](Value_RemoteController_Struct_RCAuthorityLostPushMsg.md)

---

# class MultiControlLostControlInfo

|  |
| --- |
| ``` class MultiControlLostControlInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

This class is used to define the information returned when the device with the control right loses connection.

##### Class Members:

method

[getType](#value_remotecontroller_struct_rcauthoritylostpushmsg_gettype_inline)

###### method getType

|  |
| --- |
| ``` MultiControlChannel getType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Return channel type of the device that lost connection.

##### Return:

|  |  |
| --- | --- |
| [MultiControlChannel](../../Components/IKeyManager/DJIValue.md#value_common_enum_rcmodechannel) | *Return channel type of the device that lost connection.* |

method

[getValue](#value_remotecontroller_struct_rcauthoritylostpushmsg_getvalue_inline)

###### method getValue

|  |
| --- |
| ``` List<MultiControlAuthorityTypeInfo> getValue() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Return control type of the device that lost connection.

##### Return:

|  |  |
| --- | --- |
| List<[MultiControlAuthorityTypeInfo](../../Components/IKeyManager/Value_Common_Struct_RCAuthorityModeMsg.md#value_common_struct_rcauthoritymodemsg)> | *Return control type of the device that lost connection.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found