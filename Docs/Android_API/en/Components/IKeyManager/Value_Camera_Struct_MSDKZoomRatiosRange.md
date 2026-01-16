**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [ZoomRatiosRange](Value_Camera_Struct_MSDKZoomRatiosRange.md)

---

# class ZoomRatiosRange

|  |
| --- |
| ``` class ZoomRatiosRange ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Zoom ratios range.

##### Class Members:

#### Members

method

[isContinuous](#value_camera_struct_msdkzoomratiosrange_iscontinuous_inline)

###### method isContinuous

|  |
| --- |
| ``` boolean isContinuous() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets whether the zoom ratio range is continuous. If it is not continuous, it means that only the value in the key gear can be set. If continuous, it means that any value between the minimum value and maximum value in the key gear can be set. Take M3T as an example: isContinuous is `TRUE`, and the key gears are: [1,2,4,7,28,56], which means that any value of [1,56] can be set.

##### Return:

|  |  |
| --- | --- |
| boolean | *Returns whether the zoom ratio range is continuous.* |

method

[getGears](#value_camera_struct_msdkzoomratiosrange_getgears_inline)

###### method getGears

|  |
| --- |
| ``` int[] getGears() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets to the key gear of the zoom ratio range.

##### Return:

|  |  |
| --- | --- |
| int[] | *Returns to the key gear of the zoom ratio range.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found