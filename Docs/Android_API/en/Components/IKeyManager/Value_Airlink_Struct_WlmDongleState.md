**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [WlmDongleInfo](Value_Airlink_Struct_WlmDongleState.md)

---

# class WlmDongleInfo

|  |
| --- |
| ``` class WlmDongleInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

LTE dongle status.

##### Class Members:

method

[getSn](#value_airlink_struct_wlmdonglestate_getsn_inline)

###### method getSn

|  |
| --- |
| ``` String getSn() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Gets the Dongle SN。

##### Return:

|  |  |
| --- | --- |
| String | *Returns the Dongle SN。* |

method

[getIsNeedUpgrade](#value_airlink_struct_wlmdonglestate_getisneedupgrade_inline)

###### method getIsNeedUpgrade

|  |
| --- |
| ``` Boolean getIsNeedUpgrade() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Get whether Dongle needs to be upgraded. MSDK currently does not support Dongle upgrade. If you need to upgrade, please use the DJI official App to upgrade.

##### Return:

|  |  |
| --- | --- |
| Boolean | *Returns whether Dongle needs to be upgraded.* |

method

[getIsSimCardInserted](#value_airlink_struct_wlmdonglestate_getissimcardinserted_inline)

###### method getIsSimCardInserted

|  |
| --- |
| ``` Boolean getIsSimCardInserted() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Get whether the SIM card is inserted.

##### Return:

|  |  |
| --- | --- |
| Boolean | *Returns whether the SIM card is inserted.* |

method

[getWorkState](#value_airlink_struct_wlmdonglestate_getworkstate_inline)

###### method getWorkState

|  |
| --- |
| ``` WlmDongleWorkState getWorkState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Gets the working status of LTE Dongle.

##### Return:

|  |  |
| --- | --- |
| [WlmDongleWorkState](../../Components/IKeyManager/DJIValue.md#value_airlink_enum_wlmdongleworkstate) | *Returns the working status of LTE Dongle.* |

method

[getVersion](#value_airlink_struct_wlmdonglestate_getversion_inline)

###### method getVersion

|  |
| --- |
| ``` String getVersion() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Gets the version of the LTE Dongle.

##### Return:

|  |  |
| --- | --- |
| String | *Returns the version of the LTE Dongle.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found