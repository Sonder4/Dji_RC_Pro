# class CameraStorageStateMsg

|  |
| --- |
| ``` class CameraStorageStateMsg implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The storage information for camera SD card and onboard memory.

##### Class Members:

#### Members

method

[getCurStorageType](#value_camera_struct_camerastoragestatemsg_getcurstoragetype_inline)

###### method getCurStorageType

|  |
| --- |
| ``` Integer getCurStorageType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Get the current storage location in use by the camera. 0 means that the SD card is currently being used to store media files. 1 indicates that the onboard memory is currently being used to store media files.

##### Return:

|  |  |
| --- | --- |
| Integer | *Returns the current storage location in use by the camera.* |

method

[getStorageInfos](#value_camera_struct_camerastoragestatemsg_getstorageinfos_inline)

###### method getStorageInfos

|  |
| --- |
| ``` List<CameraStorageState> getStorageInfos() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the details of the current camera storage card, including the details of the SD Card and onboard memory.

##### Return:

|  |  |
| --- | --- |
| List<[CameraStorageState](../../Components/IKeyManager/Value_Camera_Struct_CameraStorageState.md#value_camera_struct_camerastoragestate)> | *Returns details of the current camera storage card.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found