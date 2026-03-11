**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [GoHomeInfo](Value_FlightController_GoHomeInfo.md)

---

# class GoHomeInfo

|  |
| --- |
| ``` class GoHomeInfo implements DJIValue, JNIProguardKeepTag, ByteStream, Parcelable ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Return-to-home information.

##### Class Members:

#### Members

method

[getType](#value_flightcontroller_gohomeinfo_gohomeneedconfirmtype_inline)

###### method getType

|  |
| --- |
| ``` GoHomeNeedConfirmType getType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

**parameters:** `GoHomeNeedConfirmType` Return-to-home confirm status. When the status is `NORMAL`, you need to call the `KeyGoHomeConfirm` interface to confirm or cancel the eturn-to-home operation.

##### Return:

|  |  |
| --- | --- |
| [GoHomeNeedConfirmType](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_msdkgohomeneedconfirmtype) | *Return whether need to confirm return-to-home.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found