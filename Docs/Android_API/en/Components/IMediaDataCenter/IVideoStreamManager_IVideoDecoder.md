**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IVideoStreamManager](IVideoStreamManager.md) > [IVideoDecoder](IVideoStreamManager_IVideoDecoder.md)

---

# class IVideoDecoder

|  |
| --- |
| ``` @Deprecated  interface IVideoDecoder ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

DJI decoder class. It supports YUV output mode and SURFACE output mode. You can set the output mode`DecoderOutputMode`through its constructor. You can set`VideoChannelType`to bind the decoder with channel`IVideoChannel`. **Note： If you want to set the width and height of decoder through constructor, you have to make sure the width and height are multple of 16.** `Supported since MSDK 5.0.0`

##### Class Members:

method

[getDecoderOutputMode](#ivideostreammanager_ivideodecoder_getdecoderoutputmode_inline)

###### method getDecoderOutputMode

|  |
| --- |
| ``` @NonNull     DecoderOutputMode getDecoderOutputMode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the output mode of decoder. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [DecoderOutputMode](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder.md#ivideostreammanager_ivideodecoder_decoderoutputmode) | *Return the output mode of decoder.* |

method

[getVideoChannelType](#ivideostreammanager_ivideodecoder_getvideochanneltype_inline)

###### method getVideoChannelType

|  |
| --- |
| ``` VideoChannelType getVideoChannelType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the video channel type of decoder. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [VideoChannelType](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoChannel.md#ivideostreammanager_ivideochannel_videochanneltype) | *Return the video channel type of decoder.* |

method

[setMediaFile](#ivideostreammanager_ivideodecoder_setmediafile_inline)

###### method setMediaFile

|  |
| --- |
| ``` void setMediaFile(MediaFile mediaFile) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Sets the media file that need to be decoded. Before `playVideo`, you need to call this interface to set the media file to be decoded into the decoder. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MediaFile](../../Components/IMediaDataCenter/IMediaManager_MediaFile.md#imediamanager_mediafile) mediaFile | *Media file that needs to be decoded.* |

method

[getMediaFile](#ivideostreammanager_ivideodecoder_getmediafile_inline)

###### method getMediaFile

|  |
| --- |
| ``` MediaFile getMediaFile() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Gets the media file that needs to be decoded. `Supported since MSDK 5.1.0`

##### Return:

|  |  |
| --- | --- |
| [MediaFile](../../Components/IMediaDataCenter/IMediaManager_MediaFile.md#imediamanager_mediafile) | *Returns the media file that needs to be decoded.* |

method

[queueInFrame](#ivideostreammanager_ivideodecoder_queueinframe_inline)

###### method queueInFrame

|  |
| --- |
| ``` void queueInFrame(@NonNull IVideoFrame videoFrame) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Send the video frame data to the queue for decoding. There is no need to call this interface for the use of `IVideoStreamManager`, because MSDK will automatically send the data to the corresponding decoder for decoding and display. This interface can be used to decode the video frame data from `playVideo`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [IVideoFrame](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoFrame.md#ivideostreammanager_ivideoframe) videoFrame | *Video frame data* |

method

[getVideoWidth](#ivideostreammanager_ivideodecoder_getvideowidth_inline)

###### method getVideoWidth

|  |
| --- |
| ``` int getVideoWidth() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the video width after decoding. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the video width after decoding.* |

method

[getVideoHeight](#ivideostreammanager_ivideodecoder_getvideoheight_inline)

###### method getVideoHeight

|  |
| --- |
| ``` int getVideoHeight() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get the video height after decoding. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the video height after decoding.* |

method

[addYuvDataListener](#ivideostreammanager_ivideodecoder_addyuvdatalistener_inline)

###### method addYuvDataListener

|  |
| --- |
| ``` void addYuvDataListener(@NonNull YuvDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Add the YUV data listener. After setting`DecoderOutputMode`as`YUV_MODE`, you can get YUV data through this listener. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [YuvDataListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder_YuvDataListener.md#ivideostreammanager_ivideodecoder_yuvdatalistener) listener | *YUV data listener* |

method

[removeYuvDataListener](#ivideostreammanager_ivideodecoder_removeyuvdatalistener_inline)

###### method removeYuvDataListener

|  |
| --- |
| ``` void removeYuvDataListener(@NonNull YuvDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Remove YUV data listener. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [YuvDataListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder_YuvDataListener.md#ivideostreammanager_ivideodecoder_yuvdatalistener) listener | *YUV data listener* |

method

[clearAllYuvDataListeners](#ivideostreammanager_ivideodecoder_clearallyuvdatalisteners_inline)

###### method clearAllYuvDataListeners

|  |
| --- |
| ``` void clearAllYuvDataListeners() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Clear all YUV data listeners. `Supported since MSDK 5.0.0`

method

[addDecoderStateChangeListener](#ivideostreammanager_ivideodecoder_adddecoderstatechangelistener_inline)

###### method addDecoderStateChangeListener

|  |
| --- |
| ``` void addDecoderStateChangeListener(@NonNull DecoderStateChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Add decoder state listener. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [DecoderStateChangeListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder_DecoderStateChangeListener.md#ivideostreammanager_ivideodecoder_decoderstatechangelistener) listener | *Decoder state listener* |

method

[removeDecoderStateChangeListener](#ivideostreammanager_ivideodecoder_removedecoderstatechangelistener_inline)

###### method removeDecoderStateChangeListener

|  |
| --- |
| ``` void removeDecoderStateChangeListener(@NonNull DecoderStateChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Remove decoder state listener. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [DecoderStateChangeListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder_DecoderStateChangeListener.md#ivideostreammanager_ivideodecoder_decoderstatechangelistener) listener | *Decoder state listener* |

method

[clearAllDecoderStateChangeListeners](#ivideostreammanager_ivideodecoder_clearalldecoderstatechangelisteners_inline)

###### method clearAllDecoderStateChangeListeners

|  |
| --- |
| ``` void clearAllDecoderStateChangeListeners() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Clear all decoder state listeners. `Supported since MSDK 5.0.0`

method

[getDecoderStatus](#ivideostreammanager_ivideodecoder_getdecoderstatus_inline)

###### method getDecoderStatus

|  |
| --- |
| ``` @NonNull     DecoderState getDecoderStatus() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Get decoder status. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [DecoderState](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder.md#ivideostreammanager_ivideodecoder_decoderstate) | *Return decoder status.* |

method

[onPause](#ivideostreammanager_ivideodecoder_onpause_inline)

###### method onPause

|  |
| --- |
| ``` void onPause() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Pause the decoder. `Supported since MSDK 5.0.0`

method

[onResume](#ivideostreammanager_ivideodecoder_onresume_inline)

###### method onResume

|  |
| --- |
| ``` void onResume() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.interfaces |

##### Description:

Resume the decoder. `Supported since MSDK 5.0.0`

##### Related:

enum

[DecoderOutputMode](#ivideostreammanager_ivideodecoder_decoderoutputmode_inline)

###### enum DecoderOutputMode

|  |
| --- |
| ``` @Deprecated  enum DecoderOutputMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.decoder |

##### Description:

Decoder output mode. Support YUV output mode and SURFACE output mode. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| YUV\_MODE | YUV output mode. |
| SURFACE\_MODE | SURFACE output mode. |

##### Class Members:

enum

[DecoderState](#ivideostreammanager_ivideodecoder_decoderstate_inline)

###### enum DecoderState

|  |
| --- |
| ``` @Deprecated  enum DecoderState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.decoder |

##### Description:

Decoder State. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| INITIALIZED | Initialization of decoder is completed. |
| VIDEO\_FRAME\_INPUT | Decoder is in a state of waiting for video frame input. |
| DECODING | Decoder is in decoding state. |
| PAUSED | Decoder is in pause state. |

##### Class Members:

class

[YuvDataListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder_YuvDataListener.md)

class

[DecoderStateChangeListener](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoDecoder_DecoderStateChangeListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found