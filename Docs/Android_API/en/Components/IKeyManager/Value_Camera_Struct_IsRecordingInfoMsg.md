**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [VideoRecordingStatus](Value_Camera_Struct_IsRecordingInfoMsg.md)

---

# class VideoRecordingStatus

|  |
| --- |
| ``` class VideoRecordingStatus implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Recroding status.

##### Class Members:

#### Members

method

[getIsRecording](#value_camera_struct_isrecordinginfomsg_getisrecording_inline)

###### method getIsRecording

|  |
| --- |
| ``` Boolean getIsRecording() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets whether the camera is recording.

##### Return:

|  |  |
| --- | --- |
| Boolean | *Returns whether the camera is recording.* |

method

[getIsPreRecording](#value_camera_struct_isrecordinginfomsg_getisprerecording_inline)

###### method getIsPreRecording

|  |
| --- |
| ``` Boolean getIsPreRecording() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets whether the camera is pre-recording.

##### Return:

|  |  |
| --- | --- |
| Boolean | *Returns whether the camera is pre-recording.* |

method

[getState](#value_camera_struct_isrecordinginfomsg_getstate_inline)

###### method getState

|  |
| --- |
| ``` VideoRecordingState getState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Get camera recording status. When the status is `STARTING` and `STOPPING`, camera photo and video operations cannot be performed.

##### Return:

|  |  |
| --- | --- |
| [VideoRecordingState](../../Components/IKeyManager/DJIValue.md#value_camera_enum_recordingstatev1) | *Return to camera recording state.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found