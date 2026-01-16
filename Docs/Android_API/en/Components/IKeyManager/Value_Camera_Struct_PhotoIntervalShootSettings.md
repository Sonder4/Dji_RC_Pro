**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [PhotoIntervalShootSettings](Value_Camera_Struct_PhotoIntervalShootSettings.md)

---

# class PhotoIntervalShootSettings

|  |
| --- |
| ``` class PhotoIntervalShootSettings implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Setting of interval shooting parameter.

##### Class Members:

#### Members

method

[getCount](#value_camera_struct_photointervalshootsettings_getcount_inline)

###### method getCount

|  |
| --- |
| ``` Integer getCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get photo number of interval shooting.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return photo number of interval shooting.* |

method

[setCount](#value_camera_struct_photointervalshootsettings_setcount_inline)

###### method setCount

|  |
| --- |
| ``` void setCount(Integer count) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set the photo number of interval shooting.

##### Input Parameters:

|  |  |
| --- | --- |
| Integer count | *Photo number of interval shooting that needs to be set.* |

method

[getInterval](#value_camera_struct_photointervalshootsettings_getinterval_inline)

###### method getInterval

|  |
| --- |
| ``` Double getInterval() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the time of interval shooting.

##### Return:

|  |  |
| --- | --- |
| Double | *Return the time of interval shooting.* |

method

[setInterval](#value_camera_struct_photointervalshootsettings_setinterval_inline)

###### method setInterval

|  |
| --- |
| ``` void setInterval(Double interval) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set the time of interval shooting. Since the camera needs a response time to take photos, the minimum photo interval needs to be set to more than 2 seconds.

##### Input Parameters:

|  |  |
| --- | --- |
| Double interval | *The time of interval shooting needs to be set.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found