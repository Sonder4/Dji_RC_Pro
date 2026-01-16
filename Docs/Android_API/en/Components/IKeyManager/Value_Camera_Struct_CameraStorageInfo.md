**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [CameraStorageInfo](Value_Camera_Struct_CameraStorageInfo.md)

---

# class CameraStorageInfo

|  |
| --- |
| ``` class CameraStorageInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Details of the camera storage card. storage cards include SD Card and onboard memory.

##### Class Members:

#### Members

method

[getStorageType](#value_camera_struct_camerastorageinfo_getstoragetype_inline)

###### method getStorageType

|  |
| --- |
| ``` CameraStorageLocation getStorageType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get camera storage type.

##### Return:

|  |  |
| --- | --- |
| [CameraStorageLocation](../../Components/IKeyManager/DJIValue.md#value_camera_enum_camerastoragelocation) | *Return camera storage type.* |

method

[getStorageState](#value_camera_struct_camerastorageinfo_getstoragestate_inline)

###### method getStorageState

|  |
| --- |
| ``` SDCardLoadState getStorageState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Get the storage card insertion status. The on-board memory is plugged in by default.

##### Return:

|  |  |
| --- | --- |
| [SDCardLoadState](../../Components/IKeyManager/DJIValue.md#value_camera_enum_sdcardloadstate) | *Return the storage card inserted state.* |

method

[getStorageCapacity](#value_camera_struct_camerastorageinfo_getstoragecapacity_inline)

###### method getStorageCapacity

|  |
| --- |
| ``` Integer getStorageCapacity() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the storage card capacity. Unit: MB.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the storage card capacity.* |

method

[getStorageLeftCapacity](#value_camera_struct_camerastorageinfo_getstorageleftcapacity_inline)

###### method getStorageLeftCapacity

|  |
| --- |
| ``` Integer getStorageLeftCapacity() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the remaining capacity of the memory card. Unit: MB.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the remaining capacity of the memory card.* |

method

[getAvailablePhotoCount](#value_camera_struct_camerastorageinfo_getavailablephotocount_inline)

###### method getAvailablePhotoCount

|  |
| --- |
| ``` Integer getAvailablePhotoCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the remaining available photo count.

##### Return:

|  |  |
| --- | --- |
| Integer | *Returns the remaining available photo count.* |

method

[getAvailableVideoDuration](#value_camera_struct_camerastorageinfo_getavailablevideoduration_inline)

###### method getAvailableVideoDuration

|  |
| --- |
| ``` Integer getAvailableVideoDuration() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the remaining available video duration. Unit: second.

##### Return:

|  |  |
| --- | --- |
| Integer | *Returns the remaining available video duration. Unit: second.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found