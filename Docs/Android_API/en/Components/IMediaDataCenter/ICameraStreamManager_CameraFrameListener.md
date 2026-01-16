# class CameraFrameListener

|  |
| --- |
| ``` interface CameraFrameListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

video frame data listener. `Supported since MSDK 5.8.0`

##### Class Members:

method

[onFrame](#icamerastreammanager_cameraframelistener_onframe_inline)

###### method onFrame

|  |
| --- |
| ``` @WorkerThread         void onFrame(@NonNull byte[] frameData,                      int offset,                      int length,                      int width,                      int height,                      @NonNull FrameFormat format) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

When the video frame data is received, this method will be called back. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull byte[] frameData | *video frame data.* |
| int offset | *video frame data offset.* |
| int length | *video frame data length.* |
| int width | *video frame data width.* |
| int height | *video frame data height.* |
| @NonNull [FrameFormat](../../Components/IMediaDataCenter/ICameraStreamManager.md#icamerastreammanager_frameformat) format | *video frame data format.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found