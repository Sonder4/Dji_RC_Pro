**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [MultiControlLockAuthorityInfo](Value_Common_Struct_RCAuthorityLockControlMsg.md)

---

# class MultiControlLockAuthorityInfo

|  |
| --- |
| ``` class MultiControlLockAuthorityInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Related message of lock control.

##### Class Members:

method

[getLockAuthorityType](#value_common_struct_rcauthoritylockcontrolmsg_getlockdevice_inline)

###### method getLockAuthorityType

|  |
| --- |
| ``` MultiControlAuthorityTypeInfo getLockAuthorityType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Return control type that needs to be locked.

##### Return:

|  |  |
| --- | --- |
| [MultiControlAuthorityTypeInfo](../../Components/IKeyManager/Value_Common_Struct_RCAuthorityModeMsg.md#value_common_struct_rcauthoritymodemsg) | *Return control type that needs to be locked.* |

method

[setLockAuthorityType](#value_common_struct_rcauthoritylockcontrolmsg_setlockdevice_inline)

###### method setLockAuthorityType

|  |
| --- |
| ``` void setLockAuthorityType(MultiControlAuthorityTypeInfo lockAuthorityType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

To set the control type that needs to be locked.

##### Input Parameters:

|  |  |
| --- | --- |
| [MultiControlAuthorityTypeInfo](../../Components/IKeyManager/Value_Common_Struct_RCAuthorityModeMsg.md#value_common_struct_rcauthoritymodemsg) lockAuthorityType | *To set the control type that needs to be locked.* |

method

[getBLock](#value_common_struct_rcauthoritylockcontrolmsg_getblock_inline)

###### method getBLock

|  |
| --- |
| ``` Boolean getBLock() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

To get whether locking control is required.

##### Return:

|  |  |
| --- | --- |
| Boolean | *Return whether locking control is required.* |

method

[setBLock](#value_common_struct_rcauthoritylockcontrolmsg_setblock_inline)

###### method setBLock

|  |
| --- |
| ``` void setBLock(Boolean bLock) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

To set whether locking control is required.

##### Input Parameters:

|  |  |
| --- | --- |
| Boolean bLock | *`true` represents locking control is required.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found