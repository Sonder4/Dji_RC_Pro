**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [CameraStorageInfos](Value_Camera_Struct_CameraStorageInfos.md)

---

# class CameraStorageInfos

|  |
| --- |
| ``` class CameraStorageInfos ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The storage information for camera SD card and onboard memory.

##### Class Members:

#### Members

method

[getCurrentStorageType](#value_camera_struct_camerastorageinfos_getcurrentstoragetype_inline)

###### method getCurrentStorageType

|  |
| --- |
| ``` CameraStorageLocation getCurrentStorageType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Get the current storage location in use by the camera. 0 means that the SD card is currently being used to store media files. 1 indicates that the onboard memory is currently being used to store media files.

##### Return:

|  |  |
| --- | --- |
| [CameraStorageLocation](../../Components/IKeyManager/DJIValue.md#value_camera_enum_camerastoragelocation) | *Returns the current storage location in use by the camera.* |

method

[getCameraStorageInfoList](#value_camera_struct_camerastorageinfos_getcamerastorageinfolist_inline)

###### method getCameraStorageInfoList

|  |
| --- |
| ``` List<CameraStorageInfo> getCameraStorageInfoList() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the details of the current camera storage card, including the details of the SD Card and onboard memory.

##### Return:

|  |  |
| --- | --- |
| List<[CameraStorageInfo](../../Components/IKeyManager/Value_Camera_Struct_CameraStorageInfo.md#value_camera_struct_camerastorageinfo)> | *Returns details of the current camera storage card.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found