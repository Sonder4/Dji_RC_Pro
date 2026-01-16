**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IMediaManager](IMediaManager.md) > [MediaFrameListener](IMediaManager_MediaFrameListener.md)

---

# class MediaFrameListener

|  |
| --- |
| ``` interface MediaFrameListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Media file data listener. `Supported since MSDK 5.8.0`

##### Class Members:

method

[onReceiveStream](#imediamanager_mediaframelistener_onreceivestream_inline)

###### method onReceiveStream

|  |
| --- |
| ``` @WorkerThread         void onReceiveStream(                 byte[] data,                 int offset,                 int length,                 @NonNull StreamInfo info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

When the media file data is received, this method will be called back. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| byte[] data | *Media file data.* |
| int offset | *Media file data offset.* |
| int length | *Media file data length.* |
| @NonNull [StreamInfo](../../Components/IMediaDataCenter/ICameraStreamManager_StreamInfo.md#icamerastreammanager_streaminfo) info | *Media file data information.* |

method

[onError](#imediamanager_mediaframelistener_onerror_inline)

###### method onError

|  |
| --- |
| ``` void onError(@NonNull IDJIError error) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

When an error occurs during media file playback., this method will be called back. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *Error during playback of media files.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found