**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IVideoStreamManager](IVideoStreamManager.md) > [IVideoFrame](IVideoStreamManager_IVideoFrame.md)

---

# class IVideoFrame

|  |
| --- |
| ``` @Deprecated  interface IVideoFrame ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Video frame class. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getData](#ivideostreammanager_ivideoframe_getdata_inline)

###### method getData

|  |
| --- |
| ``` byte[] getData() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the video data of current frame.

##### Return:

|  |  |
| --- | --- |
| byte[] | *Return the video data of current frame.* |

method

[getWidth](#ivideostreammanager_ivideoframe_getwidth_inline)

###### method getWidth

|  |
| --- |
| ``` int getWidth() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the width of current frame.

##### Return:

|  |  |
| --- | --- |
| int | *Return the width of video data.* |

method

[getHeight](#ivideostreammanager_ivideoframe_getheight_inline)

###### method getHeight

|  |
| --- |
| ``` int getHeight() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the height of the current frame.

##### Return:

|  |  |
| --- | --- |
| int | *Return the height of video data.* |

method

[getFps](#ivideostreammanager_ivideoframe_getfps_inline)

###### method getFps

|  |
| --- |
| ``` int getFps() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the FPS of current frame.

##### Return:

|  |  |
| --- | --- |
| int | *Return the FPS of video data.* |

method

[getSeqNumber](#ivideostreammanager_ivideoframe_getseqnumber_inline)

###### method getSeqNumber

|  |
| --- |
| ``` long getSeqNumber() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the sequence number of current frame.

##### Return:

|  |  |
| --- | --- |
| long | *Return the sequence number of video data.* |

method

[isIFrame](#ivideostreammanager_ivideoframe_isiframe_inline)

###### method isIFrame

|  |
| --- |
| ``` boolean isIFrame() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the current frame is an I frame.

##### Return:

|  |  |
| --- | --- |
| boolean | *Return whether the current frame is an I frame.* |

method

[getPTS](#ivideostreammanager_ivideoframe_getpts_inline)

###### method getPTS

|  |
| --- |
| ``` long getPTS() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the timestamp (PTS) of the current frame.

##### Return:

|  |  |
| --- | --- |
| long | *Return the timestamp (PTS) of the current frame.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found