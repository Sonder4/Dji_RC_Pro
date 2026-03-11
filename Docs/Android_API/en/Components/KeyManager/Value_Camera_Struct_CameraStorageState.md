# class CameraStorageInfo

|  |
| --- |
| ``` class CameraStorageInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Details of the camera storage card. storage cards include: SD Card and onboard memory.

##### Class Members:

#### Members

method

[getStorageType](#value_camera_struct_camerastoragestate_getstoragetype_inline)

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
| [CameraStorageLocation](../../Components/KeyManager/DJIValue.md#value_camera_enum_camerastoragelocation) | *Return camera storage type.* |

method

[getStorageState](#value_camera_struct_camerastoragestate_getstoragestate_inline)

###### method getStorageState

|  |
| --- |
| ``` SDCardLoadState getStorageState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Get the storage card insertion status. On-board memory is plugged in by default.

##### Return:

|  |  |
| --- | --- |
| [SDCardLoadState](../../Components/KeyManager/DJIValue.md#value_camera_enum_sdcardloadstate) | *Return the storage card inserted state.* |

method

[getStorageCapacity](#value_camera_struct_camerastoragestate_getstoragecapacity_inline)

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

[getStorageLeftCapacity](#value_camera_struct_camerastoragestate_getstorageleftcapacity_inline)

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

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found