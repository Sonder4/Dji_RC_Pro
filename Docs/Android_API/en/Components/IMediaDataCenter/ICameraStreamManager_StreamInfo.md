**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [ICameraStreamManager](ICameraStreamManager.md) > [StreamInfo](ICameraStreamManager_StreamInfo.md)

---

# class StreamInfo

|  |
| --- |
| ``` class StreamInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera |

##### Description:

The video stream information class, which can obtain the width, height, frame rate and other information of the video stream. `Supported since MSDK 5.8.0`

##### Class Members:

method

[getMimeType](#icamerastreammanager_streaminfo_getmimetype_inline)

###### method getMimeType

|  |
| --- |
| ``` ICameraStreamManager.MimeType getMimeType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera |

##### Description:

Gets the mime type of the video stream.

##### Return:

|  |  |
| --- | --- |
| [ICameraStreamManager](../../Components/IMediaDataCenter/ICameraStreamManager.md#icamerastreammanager).MimeType | *Returns the mime type of the video stream.* |

method

[getWidth](#icamerastreammanager_streaminfo_getwidth_inline)

###### method getWidth

|  |
| --- |
| ``` int getWidth() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera |

##### Description:

Gets the width of the video stream.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the width of the video stream.* |

method

[getHeight](#icamerastreammanager_streaminfo_getheight_inline)

###### method getHeight

|  |
| --- |
| ``` int getHeight() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera |

##### Description:

Gets the height of the video stream.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the height of the video stream.* |

method

[getFrameRate](#icamerastreammanager_streaminfo_getframerate_inline)

###### method getFrameRate

|  |
| --- |
| ``` int getFrameRate() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera |

##### Description:

Gets the frame rate of the video stream.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the frame rate of the video stream.* |

method

[getPresentationTimeMs](#icamerastreammanager_streaminfo_getpresentationtimems_inline)

###### method getPresentationTimeMs

|  |
| --- |
| ``` long getPresentationTimeMs() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera |

##### Description:

Gets the presentation time stamp of the video stream. Unit: milliseconds.

##### Return:

|  |  |
| --- | --- |
| long | *Returns the presentation time stamp of the video stream.* |

method

[isKeyFrame](#icamerastreammanager_streaminfo_iskeyframe_inline)

###### method isKeyFrame

|  |
| --- |
| ``` boolean isKeyFrame() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera |

##### Description:

Gets if the current frame of video stream data is key frame.

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` indicates that the current frame of video stream data is key frame.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found