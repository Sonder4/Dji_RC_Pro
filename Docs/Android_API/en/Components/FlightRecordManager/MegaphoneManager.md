# class IMegaphoneManager

|  |
| --- |
| ``` interface IMegaphoneManager ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

麦克风喊话器负载管理类。可通过此类设置喊话器的参数，录制和播放。

##### 类成员:

method

[setVolume](#megaphonemanager_setvolume_inline)

###### method setVolume

|  |
| --- |
| ``` void setVolume(@NonNull int volume, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

设置喊话器音量。

##### 输入参数：

|  |  |
| --- | --- |
| @NonNull int volume | *需要设置的音量。* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[getVolume](#megaphonemanager_getvolume_inline)

###### method getVolume

|  |
| --- |
| ``` void getVolume(@Nullable CommonCallbacks.CompletionCallbackWithParam<Integer> callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取喊话器音量。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Integer> callback | *返回执行结果的回调。* |

method

[setPlayMode](#megaphonemanager_setplaymode_inline)

###### method setPlayMode

|  |
| --- |
| ``` void setPlayMode(@NonNull PlayMode playMode, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

设置喊话器播放模式。包括单次播放和循环播放。

##### 输入参数：

|  |  |
| --- | --- |
| @NonNull PlayMode playMode | *需要设置的播放模式。* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[getPlayMode](#megaphonemanager_getplaymode_inline)

###### method getPlayMode

|  |
| --- |
| ``` void getPlayMode(@Nullable CommonCallbacks.CompletionCallbackWithParam<PlayMode> callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取喊话器播放模式。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<PlayMode> callback | *返回执行结果的回调。* |

method

[setWorkMode](#megaphonemanager_setworkmode_inline)

###### method setWorkMode

|  |
| --- |
| ``` void setWorkMode(@NonNull WorkMode workMode,@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

设置喊话器工作模式。包括TTS文本模式和语音模式。

##### 输入参数：

|  |  |
| --- | --- |
| @NonNull [WorkMode](../../Components/FlightRecordManager/MegaphoneManager.md#megaphonemanager_workmode) workMode | *需要设置的工作模式。* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[getWorkMode](#megaphonemanager_getworkmode_inline)

###### method getWorkMode

|  |
| --- |
| ``` void getWorkMode(@Nullable CommonCallbacks.CompletionCallbackWithParam<WorkMode> callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取喊话器工作模式。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[WorkMode](../../Components/FlightRecordManager/MegaphoneManager.md#megaphonemanager_workmode)> callback | *返回执行结果的回调。* |

method

[startPlay](#megaphonemanager_startplay_inline)

###### method startPlay

|  |
| --- |
| ``` void startPlay(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

喊话器开始播放。在调用开始播放接口之前，你需要调用文件传输方式`MegaphoneManager_startpushingFileToMegaphone`或者流式传输方式`startRealTimeTransmission`把需要播放的数据传给喊话器。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[stopPlay](#megaphonemanager_stopplay_inline)

###### method stopPlay

|  |
| --- |
| ``` void stopPlay(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

喊话器停止播放。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[startPushingFileToMegaphone](#megaphonemanager_startpushingfiletomegaphone_inline)

###### method startPushingFileToMegaphone

|  |
| --- |
| ``` void startPushingFileToMegaphone(@NonNull FileInfo fileInfo, @Nullable CommonCallbacks.CompletionCallbackWithProgress callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

传输TTS数据或者音频文件到喊话器。当数据传输完成后，你可以调用`startPlay`接口进行播放。

##### 输入参数：

|  |  |
| --- | --- |
| @NonNull [FileInfo](../../Components/FlightRecordManager/MegaphoneManager_FileInfo.md#megaphonemanager_fileinfo) fileInfo | *文件信息。* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithProgress callback | *返回执行结果的回调。* |

method

[cancelPushingFileToMegaphone](#megaphonemanager_cancelpushingfiletomegaphone_inline)

###### method cancelPushingFileToMegaphone

|  |
| --- |
| ``` void cancelPushingFileToMegaphone(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

取消传输TTS数据或者音频文件到喊话器。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[startRealTimeTransmission](#megaphonemanager_startrealtimetransmission_inline)

###### method startRealTimeTransmission

|  |
| --- |
| ``` void startRealTimeTransmission(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

开启流式传输模式。你可以调用`sendRealTimeDataToMegaphone`向喊话器发送需要播放的数据，调用`appendEOFToRealTimeData`添加EOF标志位，表示数据发送完毕。然后调用`startPlay`接口进行播放。 **注意：流式传输模式的数据必须为OPUS编码格式的音频数据。**

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[sendRealTimeDataToMegaphone](#megaphonemanager_sendrealtimedatatomegaphone_inline)

###### method sendRealTimeDataToMegaphone

|  |
| --- |
| ``` void sendRealTimeDataToMegaphone(byte[] data, int length, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

向喊话器发送需要播放数据。可以多次调用此接口发送播放数据。

##### 输入参数：

|  |  |
| --- | --- |
| byte[] data | *需要传输的数据，数据必须为OPUS编码格式的音频数据。* |
| int length | *数据长度。* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[appendEOFToRealTimeData](#megaphonemanager_appendeoftorealtimedata_inline)

###### method appendEOFToRealTimeData

|  |
| --- |
| ``` void appendEOFToRealTimeData(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

添加EOF标志位，表示数据发送完毕。你可以调用`startPlay`接口进行播放。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[cancelRealTimeTransmission](#megaphonemanager_cancelrealtimetransmission_inline)

###### method cancelRealTimeTransmission

|  |
| --- |
| ``` void cancelRealTimeTransmission(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

取消本次传输，将清空已上传的数据。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *返回执行结果的回调。* |

method

[addRealTimeTransmissionStateListener](#megaphonemanager_addrealtimetransmissionstatelistener_inline)

###### method addRealTimeTransmissionStateListener

|  |
| --- |
| ``` void addRealTimeTransmissionStateListener(@Nullable RealTimeTransimissionStateListener listener) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

设置流式传输模式的状态监听器。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [RealTimeTransimissionStateListener](../../Components/FlightRecordManager/MegaphoneManager_RealTimeTransimissionStateListener.md#megaphonemanager_realtimetransimissionstatelistener) listener | *流式传输模式的状态监听器。* |

method

[removeRealTimeTransimissionStateListener](#megaphonemanager_removerealtimetransimissionstatelistener_inline)

###### method removeRealTimeTransimissionStateListener

|  |
| --- |
| ``` void removeRealTimeTransimissionStateListener(@Nullable RealTimeTransimissionStateListener listener) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

移除流式传输模式的状态监听器。

##### 输入参数：

|  |  |
| --- | --- |
| @Nullable [RealTimeTransimissionStateListener](../../Components/FlightRecordManager/MegaphoneManager_RealTimeTransimissionStateListener.md#megaphonemanager_realtimetransimissionstatelistener) listener | *流式传输模式的状态监听器。* |

method

[clearAllRealTimeTransimissionStateListener](#megaphonemanager_clearallrealtimetransimissionstatelistener_inline)

###### method clearAllRealTimeTransimissionStateListener

|  |
| --- |
| ``` void clearAllRealTimeTransimissionStateListener() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

移除所有流式传输模式的状态监听器。

##### Related:

enum

[WorkMode](#megaphonemanager_workmode_inline)

###### enum WorkMode

|  |
| --- |
| ``` enum WorkMode ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

喊话器工作模式。包括TTS文本模式和语音模式。

##### Enum Members:

|  |  |
| --- | --- |
| TTS | TTS模式，输入本文转换成语音进行播放。 |
| VOICE | 语音模式，输入语音进行播放。 |

##### Class Members:

enum

[MegaphoneStatus](#megaphonemanager_megaphonestatus_inline)

###### enum MegaphoneStatus

|  |
| --- |
| ``` enum MegaphoneStatus ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

喊话器状态。

##### Enum Members:

|  |  |
| --- | --- |
| IDEL | 喊话器处于空闲状态。 |
| IN\_TRANSMISSION | 喊话器处于数据传输状态。 |
| PLAYING | 喊话器处于数据播放状态。 |
| IN\_EXCEPTION | 喊话器处于异常状态，请联系设备提供商解决。 |

##### Class Members:

class

[FileInfo](../../Components/FlightRecordManager/MegaphoneManager_FileInfo.md)

enum

[UploadType](#megaphonemanager_uploadtype_inline)

###### enum UploadType

|  |
| --- |
| ``` enum UploadType ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

文件传输方式的传输的类型。包括本地音频文件数据和TTS二进制数据。

##### Enum Members:

|  |  |
| --- | --- |
| TTS\_DATA | TTS二进制数据类型，TTS二进制数据需要采用UTF-8格式。设置为此类型，你需要调用`setData`设置TTS二进制数据。 |
| VOICE\_FILE | 本地音频文件类型，音频文件需要采用OPUS编码格式。设置为此类型，你需要调用`setFile`设置本地音频文件路径。 |

##### Class Members:

class

[RealTimeTransimissionStateListener](../../Components/FlightRecordManager/MegaphoneManager_RealTimeTransimissionStateListener.md)

enum

[UploadState](#megaphonemanager_uploadstate_inline)

###### enum UploadState

|  |
| --- |
| ``` enum UploadState ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

流式传输模式上传状态。

##### Enum Members:

|  |  |
| --- | --- |
| UPLOADING | 上传中。 |
| UPLOAD\_SUCCESS | 上传成功。 |
| UPLOAD\_FAILED | 上传失败，请重新上传。 |
| MD5\_FAILED | MD5校验失败。 |
| ABORTED | 上传终止。 |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found