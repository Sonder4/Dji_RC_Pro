# class SuperResolutionInfo

|  |
| --- |
| ``` class SuperResolutionInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The status and information of high-res grid photo shooting.

##### Class Members:

#### Members

method

[getMaxArea](#value_camera_struct_superresolutionstatemsg_getmaxarea_inline)

###### method getMaxArea

|  |
| --- |
| ``` DoubleRect4Sides getMaxArea() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the maximum area of high-res grid photo shooting. This range is automatically calculated by the camera based on the camera angle.

##### Return:

|  |  |
| --- | --- |
| DoubleRect4Sides | *Return the maximum area of high-res grid photo shooting. (0,0) is the upper left corner of the video interface, (1,1) is the lower right corner of the video interface.* |

method

[getZoomTotalCount](#value_camera_struct_superresolutionstatemsg_getzoomtotalcount_inline)

###### method getZoomTotalCount

|  |
| --- |
| ``` Integer getZoomTotalCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the total number of zoom photos that need to be taken for high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the total number of zoom photos that need to be taken for high-res grid photo shooting.* |

method

[getCaptureArea](#value_camera_struct_superresolutionstatemsg_getcapturearea_inline)

###### method getCaptureArea

|  |
| --- |
| ``` DoubleRect4Sides getCaptureArea() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the currently set area of high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| DoubleRect4Sides | *Return the currently set area of high-res grid photo shooting. (0,0) is the upper left corner of the video interface, (1,1) is the lower right corner of the video interface.* |

method

[getCaptureColumnCount](#value_camera_struct_superresolutionstatemsg_getcapturecolumncount_inline)

###### method getCaptureColumnCount

|  |
| --- |
| ``` Integer getCaptureColumnCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the row number of high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the row number of high-res grid photo shooting.* |

method

[getCaptureRowCount](#value_camera_struct_superresolutionstatemsg_getcapturerowcount_inline)

###### method getCaptureRowCount

|  |
| --- |
| ``` Integer getCaptureRowCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the column number of high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| Integer | *Returns the column number of high-res grid photo shooting.* |

method

[getIsStorageOverflow](#value_camera_struct_superresolutionstatemsg_getisstorageoverflow_inline)

###### method getIsStorageOverflow

|  |
| --- |
| ``` Boolean getIsStorageOverflow() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get whether the SD Card is full.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means that the SD Card is full.* |

method

[getCaptureRemainingTime](#value_camera_struct_superresolutionstatemsg_getcaptureremainingtime_inline)

###### method getCaptureRemainingTime

|  |
| --- |
| ``` Integer getCaptureRemainingTime() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the remaining time of high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the remaining time of high-res grid photo shooting.* |

method

[getProgress](#value_camera_struct_superresolutionstatemsg_getprogress_inline)

###### method getProgress

|  |
| --- |
| ``` Integer getProgress() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the progress of high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the progress of high-res grid photo shooting.* |

method

[getZoomCapturedCount](#value_camera_struct_superresolutionstatemsg_getzoomcapturedcount_inline)

###### method getZoomCapturedCount

|  |
| --- |
| ``` Integer getZoomCapturedCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the number of shots taken by high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the number of shots taken by high-res grid photo shooting.* |

method

[getStatus](#value_camera_struct_superresolutionstatemsg_getstatus_inline)

###### method getStatus

|  |
| --- |
| ``` SuperResolutionState getStatus() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the status of high-res grid photo shooting.

##### Return:

|  |  |
| --- | --- |
| [SuperResolutionState](../../Components/KeyManager/DJIValue.md#value_camera_enum_superresolutionstatus) | *Return the status of high-res grid photo shooting.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found