**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [ILiveStreamManager](ILiveStreamManager.md)

---

# class ILiveStreamManager

|  |
| --- |
| ``` interface ILiveStreamManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that used to manage the live steam. It is used to configure the live stream settings, start and stop the live streaming. `Supported since MSDK 5.0.0`

##### Class Members:

method

[isStreaming](#ilivestreammanager_isstreaming_inline)

###### method isStreaming

|  |
| --- |
| ``` boolean isStreaming() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get whether the aircraft is live streaming now. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` means it is live streaming now.* |

method

[setLiveStreamSettings](#ilivestreammanager_setlivestreamsettings_inline)

###### method setLiveStreamSettings

|  |
| --- |
| ``` void setLiveStreamSettings(LiveStreamSettings settings) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set live stream settings. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [LiveStreamSettings](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamSettings.md#ilivestreammanager_livestreamsettings) settings | *Live stream settings.* |

method

[getLiveStreamSettings](#ilivestreammanager_getlivestreamsettings_inline)

###### method getLiveStreamSettings

|  |
| --- |
| ``` LiveStreamSettings getLiveStreamSettings() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the live stream settings. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [LiveStreamSettings](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamSettings.md#ilivestreammanager_livestreamsettings) | *Return the live stream settings.* |

method

[setVideoChannelType](#ilivestreammanager_setvideochanneltype_inline)

###### method setVideoChannelType

|  |
| --- |
| ``` @Deprecated     void setVideoChannelType(VideoChannelType videoChannelType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the video channel for live streaming. **Note: This interface is deprecated starting from MSDK 5.8.0. Please call `setCameraIndex` to set the camera index to live stream.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [VideoChannelType](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel_videochanneltype) videoChannelType | *The type of video channel.* |

method

[getVideoChannelType](#ilivestreammanager_getvideochanneltype_inline)

###### method getVideoChannelType

|  |
| --- |
| ``` @Deprecated     VideoChannelType getVideoChannelType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the type of video channel for live streaming. **Note: This interface is deprecated starting from MSDK 5.8.0. Please call `getCameraIndex` to get the camera index.** `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [VideoChannelType](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel_videochanneltype) | *Return the type of video channel.* |

method

[setCameraIndex](#ilivestreammanager_setcameraindex_inline)

###### method setCameraIndex

|  |
| --- |
| ``` void setCameraIndex(@NonNull ComponentIndexType cameraIndex) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the camera index that needs to live stream. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) cameraIndex | *Camera index set for live stream.* |

method

[getCameraIndex](#ilivestreammanager_getcameraindex_inline)

###### method getCameraIndex

|  |
| --- |
| ``` ComponentIndexType getCameraIndex() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the set camera index. `Supported since MSDK 5.8.0`

##### Return:

|  |  |
| --- | --- |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) | *Returns the set camera index.* |

method

[startStream](#ilivestreammanager_startstream_inline)

###### method startStream

|  |
| --- |
| ``` void startStream(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start live streaming. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of execution.* |

method

[stopStream](#ilivestreammanager_stopstream_inline)

###### method stopStream

|  |
| --- |
| ``` void stopStream(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stop live streaming. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of execution.* |

method

[setLiveStreamQuality](#ilivestreammanager_setlivestreamquality_inline)

###### method setLiveStreamQuality

|  |
| --- |
| ``` void setLiveStreamQuality(StreamQuality quality) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the type of live stream video quality. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [StreamQuality](../../Components/IMediaDataCenter/ILiveStreamManager.md#ilivestreammanager_streamquality) quality | *The type of live stream video quality.* |

method

[getLiveStreamQuality](#ilivestreammanager_getlivestreamquality_inline)

###### method getLiveStreamQuality

|  |
| --- |
| ``` StreamQuality getLiveStreamQuality() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the type of live stream video quality. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [StreamQuality](../../Components/IMediaDataCenter/ILiveStreamManager.md#ilivestreammanager_streamquality) | *Return the type of live stream video quality.* |

method

[setLiveVideoBitrateMode](#ilivestreammanager_setlivevideobitratemode_inline)

###### method setLiveVideoBitrateMode

|  |
| --- |
| ``` void setLiveVideoBitrateMode(LiveVideoBitrateMode bitrateMode) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the mode of live stream video bit rate. Under AUTO mode, MSDK will set the live stream video bit rate automatically. Under MANUAL mode, you can call`setLiveVideoBitrate`to set the live stream video bit rate. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [LiveVideoBitrateMode](../../Components/IMediaDataCenter/ILiveStreamManager.md#ilivestreammanager_livevideobitratemode) bitrateMode | *The mode of live stream video bit rate.* |

method

[getLiveVideoBitrateMode](#ilivestreammanager_getlivevideobitratemode_inline)

###### method getLiveVideoBitrateMode

|  |
| --- |
| ``` LiveVideoBitrateMode getLiveVideoBitrateMode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the mode of live stream video bitrate. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [LiveVideoBitrateMode](../../Components/IMediaDataCenter/ILiveStreamManager.md#ilivestreammanager_livevideobitratemode) | *Return the mode of live stream video bitrate.* |

method

[setLiveVideoBitrate](#ilivestreammanager_setlivevideobitrate_inline)

###### method setLiveVideoBitrate

|  |
| --- |
| ``` void setLiveVideoBitrate(int bitrate) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the live stream bitrate, unit: bit/s. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int bitrate | *Live stream video bitrate.* |

method

[getLiveVideoBitrate](#ilivestreammanager_getlivevideobitrate_inline)

###### method getLiveVideoBitrate

|  |
| --- |
| ``` int getLiveVideoBitrate() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the live stream video bitrate. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the live stream video bitrate.* |

method

[setLiveStreamScaleType](#ilivestreammanager_setlivestreamscaletype_inline)

###### method setLiveStreamScaleType

|  |
| --- |
| ``` void setLiveStreamScaleType(ICameraStreamManager.ScaleType scaleType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the live stream video scale type. `Supported since MSDK 5.10.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ICameraStreamManager](../../Components/IMediaDataCenter/ICameraStreamManager.md#icamerastreammanager).ScaleType scaleType | *Scale type.* |

method

[getLiveStreamScaleType](#ilivestreammanager_getlivestreamscaletype_inline)

###### method getLiveStreamScaleType

|  |
| --- |
| ``` ICameraStreamManager.ScaleType getLiveStreamScaleType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the live stream video scale type. `Supported since MSDK 5.10.0`

##### Return:

|  |  |
| --- | --- |
| [ICameraStreamManager](../../Components/IMediaDataCenter/ICameraStreamManager.md#icamerastreammanager).ScaleType | *Return the live stream video scale type.* |

method

[addLiveStreamStatusListener](#ilivestreammanager_addlivestreamstatuslistener_inline)

###### method addLiveStreamStatusListener

|  |
| --- |
| ``` void addLiveStreamStatusListener(LiveStreamStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of live stream status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [LiveStreamStatusListener](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamStatusListener.md#ilivestreammanager_livestreamstatuslistener) listener | *The listener of live stream status* |

method

[removeLiveStreamStatusListener](#ilivestreammanager_removelivestreamstatuslistener_inline)

###### method removeLiveStreamStatusListener

|  |
| --- |
| ``` void removeLiveStreamStatusListener(LiveStreamStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of live stream status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [LiveStreamStatusListener](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamStatusListener.md#ilivestreammanager_livestreamstatuslistener) listener | *The listener of live stream status* |

##### Related:

class

[LiveStreamSettings](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamSettings.md)

enum

[LiveStreamType](#ilivestreammanager_livestreamtype_inline)

###### enum LiveStreamType

|  |
| --- |
| ``` enum LiveStreamType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

The type of live stream protocol. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| RTMP | RTMP type. |
| RTSP | RTSP type. |
| GB28181 | GB28181 type. |
| AGORA | Agora type. |

##### Class Members:

class

[RtspSettings](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamSettings_RtspSettings.md)

class

[RtmpSettings](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamSettings_RtmpSettings.md)

class

[GB28181Settings](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamSettings_GB28181Settings.md)

class

[AgoraSettings](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamSettings_AgoraSettings.md)

enum

[StreamQuality](#ilivestreammanager_streamquality_inline)

###### enum StreamQuality

|  |
| --- |
| ``` enum StreamQuality ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

The type of live video stream quality. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| SD | Standard definition, resolution is 960\*540, FPS is 30, bit rate is about 74KByte/s. |
| HD | High definition, resolution is 1280\*720, FPS is 30, bit rate is about 168KByte/s. |
| FULL\_HD | Full high definition, resolution is 1920\*1080, FPS is 30, bit rate is about 380KByte/s. |

##### Class Members:

enum

[LiveVideoBitrateMode](#ilivestreammanager_livevideobitratemode_inline)

###### enum LiveVideoBitrateMode

|  |
| --- |
| ``` enum LiveVideoBitrateMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

The mode of live stream video bitrate. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| AUTO | AUTO. Under this mode, the MSDK will set the bitrate automatically according to fluency of live video. |
| MANUAL | MANUAL, the default mode of live video bitrate mode. The deafult bit rate is 2\*1024kbps. |

##### Class Members:

class

[LiveStreamStatus](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamStatus.md)

class

[VideoResolution](../../Components/IMediaDataCenter/ILiveStreamManager_VideoResolution.md)

class

[LiveStreamStatusListener](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamStatusListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found