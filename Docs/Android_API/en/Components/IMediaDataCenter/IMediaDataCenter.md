**Navigation:** [IMediaDataCenter](IMediaDataCenter.md)

---

# class IMediaDataCenter

|  |
| --- |
| ``` interface IMediaDataCenter ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used for managing media data center. It can be used to get an instance of VideoStreamManager, MediaManager and LiveStreamManager. It provides the video stream, media data and live stream management capability. **Note: `IVideoStreamManager` is deprecated starting from MSDK 5.8.0. Please use `ICameraStreamManager` to implement video stream management related functions.** `Supported since MSDK 5.0.0`

##### Class Members:

method

[getVideoStreamManager](#imediadatacenter_getvideostreammanager_inline)

###### method getVideoStreamManager

|  |
| --- |
| ``` IVideoStreamManager getVideoStreamManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get an instance of VideoStreamManager, this instance is used to manage the parameters and output of the video stream. **Note: 1. You need to call this method again to get the instance of VideoStreamManager again when a new DJI product is connected. The new DJI hardware product connection can be obtained through `KeyConnection` and `KeyProductType`. 2. This interface is deprecated starting from MSDK 5.8.0. Please call `getCameraStreamManager` to obtain a CameraStreamManager instance to implement video stream management related functions.** `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [IVideoStreamManager](../../Components/IMediaDataCenter/IVideoStreamManager.md#ivideostreammanager) | *Return an instance of VideoStreamManager* |

method

[getCameraStreamManager](#imediadatacenter_getcamerastreammanager_inline)

###### method getCameraStreamManager

|  |
| --- |
| ``` ICameraStreamManager getCameraStreamManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Obtain the CameraStreamManager instance, which supports functions such as camera lens video stream display, frame data and video stream data acquisition. CameraStreamManager has an abstract design for VideoStreamManager, shielding the concept of underlying VideoChannel. Make video stream management simpler and more stable. It is recommended that you use CameraStreamManager instead of VideoStreamManager to implement video stream management related functions. `Supported since MSDK 5.8.0`

##### Return:

|  |  |
| --- | --- |
| [ICameraStreamManager](../../Components/IMediaDataCenter/ICameraStreamManager.md#icamerastreammanager) | *Return an instance of CameraStreamManager.* |

method

[getMediaManager](#imediadatacenter_getmediamanager_inline)

###### method getMediaManager

|  |
| --- |
| ``` IMediaManager getMediaManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get instance of MediaManager. This instance is used to manage the camera functions such as media file list download, media file preview, download and video playback. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [IMediaManager](../../Components/IMediaDataCenter/IMediaManager.md#imediamanager) | *Return an instance of MediaManager.* |

method

[getLiveStreamManager](#imediadatacenter_getlivestreammanager_inline)

###### method getLiveStreamManager

|  |
| --- |
| ``` ILiveStreamManager getLiveStreamManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get an instance of LiveStreamManager, this instance is used to configure the live stream settings, start and stop the live streaming. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [ILiveStreamManager](../../Components/IMediaDataCenter/ILiveStreamManager.md#ilivestreammanager) | *Return an instance of LiveStreamManager.* |

##### Related:

class

[IVideoStreamManager](../../Components/IMediaDataCenter/IVideoStreamManager.md)

class

[ICameraStreamManager](../../Components/IMediaDataCenter/ICameraStreamManager.md)

class

[IMediaManager](../../Components/IMediaDataCenter/IMediaManager.md)

class

[ILiveStreamManager](../../Components/IMediaDataCenter/ILiveStreamManager.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found