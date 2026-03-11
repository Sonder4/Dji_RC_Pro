**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [VideoRecordPlanInfo](Value_Camera_Struct_VideoRecordPlanMsg.md)

---

# class VideoRecordPlanInfo

|  |
| --- |
| ``` class VideoRecordPlanInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Recording mode settings, support pre-recording on and off.

##### Class Members:

#### Members

method

[getPlan](#value_camera_struct_videorecordplanmsg_getplan_inline)

###### method getPlan

|  |
| --- |
| ``` VideoRecordPlan getPlan() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Get the recording mode.

##### Return:

|  |  |
| --- | --- |
| [VideoRecordPlan](../../Components/IKeyManager/DJIValue.md#value_camera_enum_videorecordplan) | *Return to recording mode.* |

method

[setPlan](#value_camera_struct_videorecordplanmsg_setplan_inline)

###### method setPlan

|  |
| --- |
| ``` void setPlan(VideoRecordPlan plan) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Set recording mode. `DEFAULT` indicates normal recording mode, `PRE_RECORD` indicates turning on pre-recording mode.

##### Input Parameters:

|  |  |
| --- | --- |
| [VideoRecordPlan](../../Components/IKeyManager/DJIValue.md#value_camera_enum_videorecordplan) plan | *The recording mode needs to be set.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found