**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [ICameraStreamManager](ICameraStreamManager.md)

---

# class ICameraStreamManager

|  |
| --- |
| ``` interface ICameraStreamManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Camera stream management class. Supports functions such as camera lens video stream display, video frame data and video stream data acquisition. The following is the interface usage process:

- Add a listener to obtain the available camera index through `addAvailableCameraUpdatedListener`.
- Call `putCameraStreamSurface` to display the camera video stream to a specific surface.
- Add the video stream data listener `addReceiveStreamListener` to receive the video stream data. The video stream data can be used for functions such as self-decoding display and third-party livestreaem.
- Add the frame data listener `addFrameListener` to receive frame data. Frame data can be used for algorithm processing of functions such as AI recognition.
  
  
`Supported since MSDK 5.8.0`

##### Class Members:

method

[addAvailableCameraUpdatedListener](#icamerastreammanager_addavailablecameraupdatedlistener_inline)

###### method addAvailableCameraUpdatedListener

|  |
| --- |
| ``` void addAvailableCameraUpdatedListener(@NonNull AvailableCameraUpdatedListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set available camera listener. This listener allows you to listen for available camera indexes. After obtaining the available camera indexes, you can call `putCameraStreamSurface` to display the video stream of a specific camera. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull AvailableCameraUpdatedListener listener | *Available camera listener* |

method

[removeAvailableCameraUpdatedListener](#icamerastreammanager_removeavailablecameraupdatedlistener_inline)

###### method removeAvailableCameraUpdatedListener

|  |
| --- |
| ``` void removeAvailableCameraUpdatedListener(@NonNull AvailableCameraUpdatedListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of avilable camera. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull AvailableCameraUpdatedListener listener | *Available camera listener* |

method

[putCameraStreamSurface](#icamerastreammanager_putcamerastreamsurface_inline)

###### method putCameraStreamSurface

|  |
| --- |
| ``` void putCameraStreamSurface(@NonNull ComponentIndexType cameraIndex,                             @NonNull Surface surface,                             int surfaceWidth,                             int surfaceHeight,                             @NonNull ScaleType scaleType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Display the camera video stream to a specific surface. A single surface can display at most one cameraIndex camera video stream at the same time. If this interface is called multiple times for the same surface, the parameters of the surface will be updated, such as updating the camera index, surface’s size or scale type. **Note: If you want to display images from different camera lenses, you can call `KeyCameraVideoStreamSource` to switch camera lenses.** `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Set the camera index for stream display.* |
| @NonNull Surface surface | *Set the surface that needs to display the video stream. Supports SurfaceView, TextureView and MediaCodeC Surface, does not support GLSurfaceView surface and any surface bound to OpenGL.* |
| int surfaceWidth | *Set the surface width required for video stream display.* |
| int surfaceHeight | *Set the surface height required for video stream display.* |
| @NonNull ScaleType scaleType | *Set the scale type required for video stream display.* |

method

[removeCameraStreamSurface](#icamerastreammanager_removecamerastreamsurface_inline)

###### method removeCameraStreamSurface

|  |
| --- |
| ``` void removeCameraStreamSurface(@NonNull Surface surface) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the added surface. When you no longer need to use the surface, please call this interface to remove the added surface, otherwise it will cause memory leaks. After removal, the surface will no longer display the video stream. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull Surface surface | *Set the Surface to be removed.* |

method

[addFrameListener](#icamerastreammanager_addframelistener_inline)

###### method addFrameListener

|  |
| --- |
| ``` void addFrameListener(@NonNull ComponentIndexType cameraIndex,                       @NonNull FrameFormat format,                       @NonNull CameraFrameListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add video frame data listener. This listener can listen the video frame data of the specified camera. You can use video frame data to implement functions such as AI recognition. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Set the camera index to be listen.* |
| @NonNull FrameFormat format | *Set the frame format to be listen.* |
| @NonNull CameraFrameListener listener | *video frame data listener* |

method

[removeFrameListener](#icamerastreammanager_removeframelistener_inline)

###### method removeFrameListener

|  |
| --- |
| ``` void removeFrameListener(@NonNull CameraFrameListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove video frame data listener. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull CameraFrameListener listener | *video frame data listener* |

method

[addReceiveStreamListener](#icamerastreammanager_addreceivestreamlistener_inline)

###### method addReceiveStreamListener

|  |
| --- |
| ``` void addReceiveStreamListener(@NonNull ComponentIndexType cameraIndex, @NonNull ReceiveStreamListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a video stream data listener. Through this listener, you can receive the raw video stream data of the specified camera. You can use this stream data to decode and display it yourself or implement functions such as third-party live streaming. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Set the camera index to be listen.* |
| @NonNull ReceiveStreamListener listener | *video stream data listener* |

method

[removeReceiveStreamListener](#icamerastreammanager_removereceivestreamlistener_inline)

###### method removeReceiveStreamListener

|  |
| --- |
| ``` void removeReceiveStreamListener(@NonNull ReceiveStreamListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove video stream data listener. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull ReceiveStreamListener listener | *video stream data listener* |

method

[setKeepAliveDecoding](#icamerastreammanager_setkeepalivevideodecoder_inline)

###### method setKeepAliveDecoding

|  |
| --- |
| ``` void setKeepAliveDecoding(boolean isKeepALive) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Whether to let the decoder continue decoding in the background, the default value is `false`. If it is `false`, when the manager does not reference any Surface, ReceiveStreamListener, or CameraFrameListener, the decoder will pause decoding to reduce background performance/power consumption, but this will increase the delay in pushing camera video stream data for the first time. If `true`, the decoder will continue to decode in the background, which will increase performance/power consumption, but can reduce the delay in pushing camera video stream data for the first time. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isKeepALive | *`true` means the decoder continues decoding in the background.* |

method

[getLiveViewLocationWithGPS](#icamerastreammanager_getliveviewlocationwithgps_inline)

###### method getLiveViewLocationWithGPS

|  |
| --- |
| ``` @NonNull     PinPointInfo getLiveViewLocationWithGPS(@NonNull LocationCoordinate3D pointPos, @NonNull ComponentIndexType type) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the pin point information with GPS which is used to show pin point in the liveview. `Supported since MSDK 5.9.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) pointPos | *The GPS location, you could get the location from `KeyLaserMeasureInformation`: `getLocation3D` or get the location with map.* |
| @NonNull [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) type | *Set the camera index displayed by the current stream.* |

##### Return:

|  |  |
| --- | --- |
| [PinPointInfo](../../Components/IMediaDataCenter/ICameraStreamManager_PinPointInfo.md#icamerastreammanager_pinpointinfo) | *Return the pin point information.* |

method

[enableVisionAssist](#icamerastreammanager_enablevisionassist_inline)

###### method enableVisionAssist

|  |
| --- |
| ``` void enableVisionAssist(boolean enabled, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enabled the vision assist function. When enabled, vision sensor liveview will be displayed in the lower right corner of Camera View. (Vision Assist not supported in Spotlight Pro, Mission Flight, or RTH mode). You can call `setVisionAssistViewDirection` to switch the direction of the vision assist. `Supported since MSDK 5.12.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean enabled | *`true` turns on vision assist.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[setVisionAssistViewDirection](#icamerastreammanager_setvisionassistmode_inline)

###### method setVisionAssistViewDirection

|  |
| --- |
| ``` void setVisionAssistViewDirection(VisionAssistDirection direction, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the direction of vision assist. `Supported since MSDK 5.12.0`

##### Input Parameters:

|  |  |
| --- | --- |
| VisionAssistDirection direction | *Vision assist direction.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[addVisionAssistStatusListener](#icamerastreammanager_addvisionassiststatuslistener_inline)

###### method addVisionAssistStatusListener

|  |
| --- |
| ``` void addVisionAssistStatusListener(@NonNull VisionAssistStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add an vision assist status listener. Through this listener, you can obtain the vision assist’s on/off status, the direction of vision assist, and the supported vision assist direction range. `Supported since MSDK 5.12.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull VisionAssistStatusListener listener | *Vision assist status listener.* |

method

[removeVisionAssistStatusListener](#icamerastreammanager_removevisionassiststatuslistener_inline)

###### method removeVisionAssistStatusListener

|  |
| --- |
| ``` void removeVisionAssistStatusListener(@NonNull VisionAssistStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the vision assist status listener. `Supported since MSDK 5.12.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull VisionAssistStatusListener listener | *Vision assist status listener.* |

method

[setStreamEncoderBitrate](#icamerastreammanager_setstreamencoderbitrate_inline)

###### method setStreamEncoderBitrate

|  |
| --- |
| ``` void setStreamEncoderBitrate(ComponentIndexType cameraIndex, int bitrate) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the encoder bitrate. Unit: bit/s。 `Supported since MSDK 5.12.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Camera index.* |
| int bitrate | *Encoder bitrate.* |

method

[getStreamEncoderBitrate](#icamerastreammanager_getstreamencoderbitrate_inline)

###### method getStreamEncoderBitrate

|  |
| --- |
| ``` int getStreamEncoderBitrate(ComponentIndexType cameraIndex) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the encoder bitrate. `Supported since MSDK 5.12.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Camera index.* |

##### Return:

|  |  |
| --- | --- |
| int | *Returns the encoder bitrate.* |

method

[setStreamPriority](#icamerastreammanager_setstreampriority_inline)

###### method setStreamPriority

|  |
| --- |
| ``` void setStreamPriority(ComponentIndexType cameraIndex, ChannelPriority priority) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

set channel priority. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Camera index.* |
| ChannelPriority priority | *Channel priority.* |

method

[getStreamPriority](#icamerastreammanager_getstreampriority_inline)

###### method getStreamPriority

|  |
| --- |
| ``` ChannelPriority getStreamPriority(ComponentIndexType cameraIndex) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

get channel priority. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Camera index.* |

##### Return:

|  |  |
| --- | --- |
| ChannelPriority | *Returns the channel priority.* |

method

[enableStream](#icamerastreammanager_enablestream_inline)

###### method enableStream

|  |
| --- |
| ``` void enableStream(ComponentIndexType cameraIndex, boolean enable) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

endable/disable the stream. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Camera index.* |
| boolean enable | *endable/disable action.* |

##### Related:

unknown

###### unknown

|  |
| --- |
| ``` interface AvailableCameraUpdatedListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Available camera listener `Supported since MSDK 5.8.0`

unknown

###### unknown

|  |
| --- |
| ``` interface CameraFrameListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

video frame data listener. `Supported since MSDK 5.8.0`

unknown

###### unknown

|  |
| --- |
| ``` interface ReceiveStreamListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

video stream data listener `Supported since MSDK 5.8.0`

class

[StreamInfo](../../Components/IMediaDataCenter/ICameraStreamManager_StreamInfo.md)

unknown

###### unknown

|  |
| --- |
| ``` enum MimeType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Mime type, supports H264 and H265. `Supported since MSDK 5.8.0`

unknown

###### unknown

|  |
| --- |
| ``` enum ScaleType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Scale type. `Supported since MSDK 5.8.0`

unknown

###### unknown

|  |
| --- |
| ``` enum FrameFormat ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The format of the video stream data. `Supported since MSDK 5.8.0`

class

[PinPointInfo](../../Components/IMediaDataCenter/ICameraStreamManager_PinPointInfo.md)

class

[PinPoint](../../Components/IMediaDataCenter/ICameraStreamManager_PinPoint.md)

#### Members

enum

[Result](#icamerastreammanager_pinpointresult_inline)

###### enum Result

|  |
| --- |
| ``` enum Result ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.camera.view |

##### Description:

The result of pin point callback.

##### Enum Members:

|  |  |
| --- | --- |
| SUCCESS | Success to get the pin point location, the point is insde the liveview. |
| SUCCESS\_BUT\_OUT\_OF\_SCREEN | Success to get the pin point location, but point is out of liveview, should show as side indicator, please reference to DJI pilot. |
| FAILED | Failed to get the pin point location. |
| INVALID\_DRONE\_POSITION | Failed to get the pin point location, because the drone position is invalid. |
| INVALID\_POINT\_POSITION | Failed to get the pin point location, because the input GPS location is invalid. |
| GIMBAL\_ATTI\_ERROR | Failed to get the pin point location, because of gimbal attitude error. |
| CAMEAR\_ERROR | Failed to get the pin point location, because of camera error. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found