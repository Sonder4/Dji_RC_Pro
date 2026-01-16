**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IVideoStreamManager](IVideoStreamManager.md)

---

# class IVideoStreamManager

|  |
| --- |
| ``` @Deprecated  interface IVideoStreamManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that used to manage the parameters and ouput of the video stream. You can learn how to use it below： - 1. Call`getAvailableStreamSources`to get an available video stream source - 2. Call`getAvailableVideoChannels`to get an available video stream channel. - 3. Call`getAvailableVideoChannels`'s`startChannel`to set the`StreamSource`from step 1 to bind the video stream source with a channel and enable the channel. - 4. If you want to write your own decoder, you can add`addStreamDataListener`to receive the video stream data. - 5. If you don't want to write your own decoder, you can use DJI decoder`IVideoDecoder`to decode. **Note: This interface is deprecated starting from MSDK 5.8.0. Please call `getCameraStreamManager` to obtain a CameraStreamManager instance to implement video stream management related functions.** `Supported since MSDK 5.0.0`

##### Class Members:

method

[getAvailableStreamSources](#ivideostreammanager_getavailablestreamsources_inline)

###### method getAvailableStreamSources

|  |
| --- |
| ``` @Nullable     List<StreamSource> getAvailableStreamSources() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get a list of available`StreamSource`. After getting all video stream sources, you can call`getAvailableVideoChannels`to get available video channel and add the video source in. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| List<[StreamSource](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource.md#ivideostreammanager_streamsource)> | *Return a list of availableIVideoStreamManager\_StreamSource`.* |

method

[addStreamSourcesListener](#ivideostreammanager_addstreamsourceslistener_inline)

###### method addStreamSourcesListener

|  |
| --- |
| ``` void addStreamSourcesListener(StreamSourceListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a video stream source listener. This listener can monitor and obtain the new video stream sources. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [StreamSourceListener](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSourceListener.md#ivideostreammanager_streamsourcelistener) listener | *Video stream source listener* |

method

[removeStreamSourcesListener](#ivideostreammanager_removestreamsourceslistener_inline)

###### method removeStreamSourcesListener

|  |
| --- |
| ``` void removeStreamSourcesListener(StreamSourceListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove a video stream source listener. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [StreamSourceListener](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSourceListener.md#ivideostreammanager_streamsourcelistener) listener | *Video stream source listener* |

method

[clearAllStreamSourcesListeners](#ivideostreammanager_clearallstreamsourceslisteners_inline)

###### method clearAllStreamSourcesListeners

|  |
| --- |
| ``` void clearAllStreamSourcesListeners() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clear all video stream source listeners. `Supported since MSDK 5.0.0`

method

[getAvailableVideoChannels](#ivideostreammanager_getavailablevideochannels_inline)

###### method getAvailableVideoChannels

|  |
| --- |
| ``` @Nullable     List<IVideoChannel> getAvailableVideoChannels() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get all available video channels. After getting available video channels, you can call`startChannel`to bind`StreamSource`with and enable video stream. Also you can call`getAvailableVideoChannel`to get the type of video channel and set the video channels. **Note： The first video channel is the main channel`PRIMARY_STREAM_CHANNEL`. The second video channel(If there is one) is the deputy channel`SECONDARY_STREAM_CHANNEL`. The third video channel(If there is one) is the extended channel`EXTENDED_STREAM_CHANNEL`. `Supported since MSDK 5.0.0`**

##### Return:

|  |  |
| --- | --- |
| List<[IVideoChannel](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel)> | *Return a list of video channels.* |

method

[getAvailableVideoChannel](#ivideostreammanager_getavailablevideochannel_inline)

###### method getAvailableVideoChannel

|  |
| --- |
| ``` @Nullable     IVideoChannel getAvailableVideoChannel(@NonNull VideoChannelType type) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the video channel type. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [VideoChannelType](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel_videochanneltype) type | *Video channel type.* |

##### Return:

|  |  |
| --- | --- |
| [IVideoChannel](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel) | *Return the video channel type.* |

method

[destroy](#ivideostreammanager_destroy_inline)

###### method destroy

|  |
| --- |
| ``` void destroy() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Release VideoStreamManager's resources. `Supported since MSDK 5.0.0`

##### Related:

class

[StreamSource](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource.md)

class

[IVideoChannel](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md)

class

[IVideoFrame](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoFrame.md)

class

[IVideoDecoder](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder.md)

class

[StreamSourceListener](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSourceListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found