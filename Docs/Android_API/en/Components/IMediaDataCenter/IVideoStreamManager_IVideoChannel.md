**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IVideoStreamManager](IVideoStreamManager.md) > [IVideoChannel](IVideoStreamManager_IVideoChannel.md)

---

# class IVideoChannel

|  |
| --- |
| ``` @Deprecated  interface IVideoChannel ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

This class represents the video channels, it is used to manage video source. Every video channel can set a video source. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getVideoChannelType](#ivideostreammanager_ivideochannel_getvideochanneltype_inline)

###### method getVideoChannelType

|  |
| --- |
| ``` @NonNull     VideoChannelType getVideoChannelType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the type of video channel. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [VideoChannelType](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel_videochanneltype) | *Return the type of video channel* |

method

[startChannel](#ivideostreammanager_ivideochannel_startchannel_inline)

###### method startChannel

|  |
| --- |
| ``` void startChannel(@NonNull StreamSource source, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Set the video source and enable it. The channel will start receiving video data after you call this method. You can call`addStreamDataListener` to recive the video data. After receiving the video data, you can decode it and render it. You can use DJI decoder`IVideoDecoder`to decode the video stream, it supports YUV output mode and SURFACE output mode. - You can call`addVideoChannelStateChangeListener` to recive the video data. After enabling it, the channel state will change to`ON`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [StreamSource](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource.md#ivideostreammanager_streamsource) source | *An instance of video source.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback that returns the execution result.* |

method

[closeChannel](#ivideostreammanager_ivideochannel_closechannel_inline)

###### method closeChannel

|  |
| --- |
| ``` void closeChannel(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Disable the video channel. After disabling the video channel, the`androidx.annotation.NonNull`will stop receiving video data. After disabling the channel successfully, The channel state will change to`CLOSE`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *A callback which returns the execution result.* |

method

[addStreamDataListener](#ivideostreammanager_ivideochannel_addstreamdatalistener_inline)

###### method addStreamDataListener

|  |
| --- |
| ``` void addStreamDataListener(@Nullable StreamDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Add the video data listener. You can use this listener to receive the video data on video channel. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable StreamDataListener listener | *Video data listener* |

method

[removeStreamDataListener](#ivideostreammanager_ivideochannel_removestreamdatalistener_inline)

###### method removeStreamDataListener

|  |
| --- |
| ``` void removeStreamDataListener(@Nullable StreamDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Remove the video data listener. After removing this listener, it will not receive any video data on video stream channel. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable StreamDataListener listener | *Video data listener* |

method

[clearAllStreamDataListener](#ivideostreammanager_ivideochannel_clearallstreamdatalistener_inline)

###### method clearAllStreamDataListener

|  |
| --- |
| ``` void clearAllStreamDataListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Clear all video data listeners on this channel. `Supported since MSDK 5.0.0`

method

[addVideoChannelStateChangeListener](#ivideostreammanager_ivideochannel_addvideochannelstatechangelistener_inline)

###### method addVideoChannelStateChangeListener

|  |
| --- |
| ``` void addVideoChannelStateChangeListener(@Nullable VideoChannelStateChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Add a video state listener. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [VideoChannelStateChangeListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel_VideoChannelStateChangeListener.md#ivideostreammanager_ivideochannel_videochannelstatechangelistener) listener | *Video state listener* |

method

[removeVideoChannelStateChangeListener](#ivideostreammanager_ivideochannel_removevideochannelstatechangelistener_inline)

###### method removeVideoChannelStateChangeListener

|  |
| --- |
| ``` void removeVideoChannelStateChangeListener(@Nullable VideoChannelStateChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Remove a video state listener. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [VideoChannelStateChangeListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel_VideoChannelStateChangeListener.md#ivideostreammanager_ivideochannel_videochannelstatechangelistener) listener | *Video state listener* |

method

[clearAllVideoChannelStateChangeListeners](#ivideostreammanager_ivideochannel_clearallvideochannelstatechangelisteners_inline)

###### method clearAllVideoChannelStateChangeListeners

|  |
| --- |
| ``` void clearAllVideoChannelStateChangeListeners() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Clear all video state listeners on this channel. `Supported since MSDK 5.0.0`

method

[getVideoChannelStatus](#ivideostreammanager_ivideochannel_getvideochannelstatus_inline)

###### method getVideoChannelStatus

|  |
| --- |
| ``` @NonNull     VideoChannelState getVideoChannelStatus() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the status of video channel. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [VideoChannelState](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel_videochannelstate) | *Return the status of video channel.* |

method

[getStreamSource](#ivideostreammanager_ivideochannel_getstreamsource_inline)

###### method getStreamSource

|  |
| --- |
| ``` StreamSource getStreamSource() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the video source on this video channel. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [StreamSource](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource.md#ivideostreammanager_streamsource) | *Return the video source on this video channel.* |

method

[getVideoStreamFormat](#ivideostreammanager_ivideochannel_getvideostreamformat_inline)

###### method getVideoStreamFormat

|  |
| --- |
| ``` @NonNull     VideoStreamFormat getVideoStreamFormat() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the video stream format on this video channel. `Supported since MSDK 5.1.0`

##### Return:

|  |  |
| --- | --- |
| [VideoStreamFormat](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_videostreamformat) | *Return the video stream format on this video channel.* |

##### Related:

enum

[VideoChannelType](#ivideostreammanager_ivideochannel_videochanneltype_inline)

###### enum VideoChannelType

|  |
| --- |
| ``` @Deprecated  enum VideoChannelType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.channel |

##### Description:

The type of video channel. Every video channel has an unique video channel type. The main video channel has the highest priority. When the transmitting bandwidth is narrow, we will ensure the main video channel is working properly first. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| PRIMARY\_STREAM\_CHANNEL | Primary video channel, highest priority, we will make sure this channel is working properly when the transmitting bandwidth is narrow. |
| SECONDARY\_STREAM\_CHANNEL | Deputy video channel, lower priority than the primary video channel. |
| EXTENDED\_STREAM\_CHANNEL | Extended video channel, lowest priority. It will lose data packet when the transmitting bandwidth is narrow. |

##### Class Members:

enum

[VideoChannelState](#ivideostreammanager_ivideochannel_videochannelstate_inline)

###### enum VideoChannelState

|  |
| --- |
| ``` @Deprecated  enum VideoChannelState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.channel |

##### Description:

The state of video channel. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| CLOSE | Current video channel is disabled. |
| ON | Current video channel is enabled. |

##### Class Members:

enum

[VideoStreamFormat](#ivideostreammanager_videostreamformat_inline)

###### enum VideoStreamFormat

|  |
| --- |
| ``` enum VideoStreamFormat ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

Video Stream format. `Supported since MSDK 5.1.0`

##### Enum Members:

|  |  |
| --- | --- |
| H264 | H264. |
| H265 | H265. |

##### Class Members:

property

[androidx.annotation.NonNull](#ivideostreammanager_ivideochannel_streamdatalistener_inline)

###### property androidx.annotation.NonNull

|  |
| --- |
| ``` import androidx.annotation.NonNull ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Video data listener. `Supported since MSDK 5.0.0`

class

[VideoChannelStateChangeListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel_VideoChannelStateChangeListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found