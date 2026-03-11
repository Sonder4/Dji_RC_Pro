**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IVideoStreamManager](IVideoStreamManager.md) > [IVideoDecoder](IVideoStreamManager_IVideoDecoder.md) > [YuvDataListener](IVideoStreamManager_IVideoDecoder_YuvDataListener.md)

---

# class YuvDataListener

|  |
| --- |
| ``` @Deprecated  interface YuvDataListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

YUV data listener. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onReceive](#ivideostreammanager_ivideodecoder_yuvdatalistener_onreceive_inline)

###### method onReceive

|  |
| --- |
| ``` void onReceive(MediaFormat mediaFormat, byte[] data, int width, int height) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

The listener will be triggered when the decoder has output YUV data for a frame. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| MediaFormat mediaFormat | *The media format of video.* |
| byte[] data | *The YUV data of current frame.* |
| int width | *The width of the YUV data of current frame.* |
| int height | *The height of the YUV data of current frame.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found