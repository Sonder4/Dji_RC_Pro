# class ReceiveStreamListener

|  |
| --- |
| ``` interface ReceiveStreamListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

video stream data listener `Supported since MSDK 5.8.0`

##### Class Members:

method

[onReceiveStream](#icamerastreammanager_receivestreamlistener_onreceivestream_inline)

###### method onReceiveStream

|  |
| --- |
| ``` @WorkerThread         void onReceiveStream(                 @NonNull byte[] data,                 int offset,                 int length,                 @NonNull StreamInfo info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

When the video stream data is received, this method will be called back. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull byte[] data | *video stream data.* |
| int offset | *video stream data offset.* |
| int length | *video stream data length.* |
| @NonNull [StreamInfo](../../Components/IMediaDataCenter/ICameraStreamManager_StreamInfo.md#icamerastreammanager_streaminfo) info | *video stream data information.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found