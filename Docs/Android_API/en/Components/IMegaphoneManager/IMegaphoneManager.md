**Navigation:** [IMegaphoneManager](IMegaphoneManager.md)

---

# class IMegaphoneManager

|  |
| --- |
| ``` interface IMegaphoneManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage megaphone. Users can setup parameters and play videos on megaphone through this class. `Supported since MSDK 5.0.0`

##### Class Members:

method

[addMegaphoneInfoListener](#imegaphonemanager_addmegaphoneinfolistener_inline)

###### method addMegaphoneInfoListener

|  |
| --- |
| ``` void addMegaphoneInfoListener(MegaphoneInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the listener of megaphone information. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MegaphoneInfoListener](../../Components/IMegaphoneManager/IMegaphoneManager_MegaphoneInfoListener.md#imegaphonemanager_megaphoneinfolistener) listener | *Megaphone information listener.* |

method

[removeMegaphoneInfoListener](#imegaphonemanager_removemegaphoneinfolistener_inline)

###### method removeMegaphoneInfoListener

|  |
| --- |
| ``` void removeMegaphoneInfoListener(MegaphoneInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of megaphone information. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MegaphoneInfoListener](../../Components/IMegaphoneManager/IMegaphoneManager_MegaphoneInfoListener.md#imegaphonemanager_megaphoneinfolistener) listener | *Megaphone information listener.* |

method

[clearAllMegaphoneInfoListener](#imegaphonemanager_clearallmegaphoneinfolistener_inline)

###### method clearAllMegaphoneInfoListener

|  |
| --- |
| ``` void clearAllMegaphoneInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of megaphone information. `Supported since MSDK 5.8.0`

method

[setVolume](#imegaphonemanager_setvolume_inline)

###### method setVolume

|  |
| --- |
| ``` void setVolume(@NonNull int volume, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To set the volume of megaphone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull int volume | *Volume that needs to be set.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getVolume](#imegaphonemanager_getvolume_inline)

###### method getVolume

|  |
| --- |
| ``` void getVolume(@Nullable CommonCallbacks.CompletionCallbackWithParam<Integer> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To get the volume of megaphone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Integer> callback | *Return the callback of execution result.* |

method

[setPlayMode](#imegaphonemanager_setplaymode_inline)

###### method setPlayMode

|  |
| --- |
| ``` void setPlayMode(@NonNull PlayMode playMode, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To set the megaphone play mode, including single play and loop play. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PlayMode](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_playmode) playMode | *Play mode that needs to be set.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getPlayMode](#imegaphonemanager_getplaymode_inline)

###### method getPlayMode

|  |
| --- |
| ``` void getPlayMode(@Nullable CommonCallbacks.CompletionCallbackWithParam<PlayMode> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To get the megaphone play mode. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[PlayMode](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_playmode)> callback | *Return the callback of execution result.* |

method

[setWorkMode](#imegaphonemanager_setworkmode_inline)

###### method setWorkMode

|  |
| --- |
| ``` void setWorkMode(@NonNull WorkMode workMode,@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To set the work mode of the megaphone, including TTS text mode and voice mode. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [WorkMode](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_workmode) workMode | *Work mode that needs to be set.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getWorkMode](#imegaphonemanager_getworkmode_inline)

###### method getWorkMode

|  |
| --- |
| ``` void getWorkMode(@Nullable CommonCallbacks.CompletionCallbackWithParam<WorkMode> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To get the work mode of the megaphone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[WorkMode](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_workmode)> callback | *Return the callback of execution result.* |

method

[getStatus](#imegaphonemanager_getstatus_inline)

###### method getStatus

|  |
| --- |
| ``` void getStatus(@Nullable CommonCallbacks.CompletionCallbackWithParam<MegaphoneStatus> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To get the status of megaphone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[MegaphoneStatus](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_megaphonestatus)> callback | *Return the callback of execution result.* |

method

[startPlay](#imegaphonemanager_startplay_inline)

###### method startPlay

|  |
| --- |
| ``` void startPlay(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The megaphone starts playing. Before calling the start play interface, you need to call the file transfer method `startPushingFileToMegaphone` or the streaming transfer method `startRealTimeTransmission` to transmit the data to be played to the megaphone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[stopPlay](#imegaphonemanager_stopplay_inline)

###### method stopPlay

|  |
| --- |
| ``` void stopPlay(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The megaphone stops playing. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[startPushingFileToMegaphone](#imegaphonemanager_startpushingfiletomegaphone_inline)

###### method startPushingFileToMegaphone

|  |
| --- |
| ``` void startPushingFileToMegaphone(@NonNull FileInfo fileInfo, @Nullable CommonCallbacks.CompletionCallbackWithProgress<Integer> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Transfer TTS data or audio files to the megaphone. When the data transfer is complete, you can call the `startPlay` interface to play. **Notice: The data in streaming transferring mode must be audio data in OPUS encoded format. If it is an MP3 file, please use `PCMTools` to convert it to an OPUS file. Starting from MSDK version 5.14.0, OPUS files converted by `DJI` will no longer be supported.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FileInfo](../../Components/IMegaphoneManager/IMegaphoneManager_FileInfo.md#imegaphonemanager_fileinfo) fileInfo | *File Information.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithProgress<Integer> callback | *Return the callback of execution result.* |

method

[cancelPushingFileToMegaphone](#imegaphonemanager_cancelpushingfiletomegaphone_inline)

###### method cancelPushingFileToMegaphone

|  |
| --- |
| ``` void cancelPushingFileToMegaphone(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stop transferring TTS data or audio files to the megaphone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[startRealTimeTransmission](#imegaphonemanager_startrealtimetransmission_inline)

###### method startRealTimeTransmission

|  |
| --- |
| ``` void startRealTimeTransmission(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Turn on streaming transferring mode. You can call `sendRealTimeDataToMegaphone` to send the data to be played to the megaphone, and call `appendEOFToRealTimeData` to add the EOF flag, indicating that the data is sent. Then call the `startPlay` interface to play. **Notice: The data in streaming transferring mode must be audio data in OPUS encoded format.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[sendRealTimeDataToMegaphone](#imegaphonemanager_sendrealtimedatatomegaphone_inline)

###### method sendRealTimeDataToMegaphone

|  |
| --- |
| ``` void sendRealTimeDataToMegaphone(byte[] data, int length, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Send the data to be played to the megaphone. You can call this interface multiple times to send data to be played. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| byte[] data | *The data to be transmitted must be audio data in OPUS encoding format.* |
| int length | *Data length.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[appendEOFToRealTimeData](#imegaphonemanager_appendeoftorealtimedata_inline)

###### method appendEOFToRealTimeData

|  |
| --- |
| ``` void appendEOFToRealTimeData(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the EOF flag to indicate that the data is sent. You can call the `startPlay` interface to play. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[cancelRealTimeTransmission](#imegaphonemanager_cancelrealtimetransmission_inline)

###### method cancelRealTimeTransmission

|  |
| --- |
| ``` void cancelRealTimeTransmission(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Cancel this transfer and clear the uploaded data. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[addRealTimeTransmissionStateListener](#imegaphonemanager_addrealtimetransmissionstatelistener_inline)

###### method addRealTimeTransmissionStateListener

|  |
| --- |
| ``` void addRealTimeTransmissionStateListener(@Nullable RealTimeTransimissionStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To set the status listener for streaming transferring mode. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [RealTimeTransimissionStateListener](../../Components/IMegaphoneManager/IMegaphoneManager_RealTimeTransimissionStateListener.md#imegaphonemanager_realtimetransimissionstatelistener) listener | *Status listener for streaming transferring mode.* |

method

[removeRealTimeTransmissionStateListener](#imegaphonemanager_removerealtimetransmissionstatelistener_inline)

###### method removeRealTimeTransmissionStateListener

|  |
| --- |
| ``` void removeRealTimeTransmissionStateListener(@Nullable RealTimeTransimissionStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the status listener for streaming transferring mode. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [RealTimeTransimissionStateListener](../../Components/IMegaphoneManager/IMegaphoneManager_RealTimeTransimissionStateListener.md#imegaphonemanager_realtimetransimissionstatelistener) listener | *Status listener for streaming transferring mode.* |

method

[clearAllRealTimeTransmissionStateListener](#imegaphonemanager_clearallrealtimetransmissionstatelistener_inline)

###### method clearAllRealTimeTransmissionStateListener

|  |
| --- |
| ``` void clearAllRealTimeTransmissionStateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove every status listener for streaming transferring mode. `Supported since MSDK 5.0.0`

##### Related:

enum

[MegaphoneIndex](#imegaphonemanager_megaphoneindex_inline)

###### enum MegaphoneIndex

|  |
| --- |
| ``` enum MegaphoneIndex ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

Megaphone position. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| PORTSIDE | The magaphone is at portside(Main). |
| STARBOARD | The megaphone is at starboard. |
| UPSIDE | The megaphone is at upside. |
| OSDK | The megaphone is conncet with OSDK port. |
| PORT\_1 | The megaphone is at port 1. |
| PORT\_2 | The megaphone is at port 2. |
| PORT\_3 | The megaphone is at port 3. |
| PORT\_4 | The megaphone is at port 4. |
| PORT\_5 | The megaphone is at port 5. |
| PORT\_6 | The megaphone is at port 6. |
| PORT\_7 | The megaphone is at port 7. |

##### Class Members:

enum

[WorkMode](#imegaphonemanager_workmode_inline)

###### enum WorkMode

|  |
| --- |
| ``` enum WorkMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

Speaker working mode. Includes TTS text mode and voice mode. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| TTS | TTS mode. Input text is converted into voice to play. |
| VOICE | Voice mode. Input voice to play. |
| REAL\_TIME | Real time mode. Only support by AS1 Megaphone. |

##### Class Members:

enum

[PlayMode](#imegaphonemanager_playmode_inline)

###### enum PlayMode

|  |
| --- |
| ``` enum PlayMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

Speaker playing mode, including single playing mode and loop playing mode. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| SINGLE | Single play mode. |
| LOOP | Loop play mode. |

##### Class Members:

enum

[MegaphoneStatus](#imegaphonemanager_megaphonestatus_inline)

###### enum MegaphoneStatus

|  |
| --- |
| ``` enum MegaphoneStatus ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

Speaker status. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | The megaphone is idle. |
| IN\_TRANSMISSION | The megaphone is in data transmission status. |
| PLAYING | The megaphone is in data playing status. |
| IN\_EXCEPTION | The microphone is in an abnormal status. Please contact the equipment provider to solve it. |
| TTS\_IN\_CONVERSION | TTS is being converted to voice. |

##### Class Members:

class

[FileInfo](../../Components/IMegaphoneManager/IMegaphoneManager_FileInfo.md)

enum

[UploadType](#imegaphonemanager_uploadtype_inline)

###### enum UploadType

|  |
| --- |
| ``` enum UploadType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

The type for the file transfer method, including local audio file data and TTS binary data. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| TTS\_DATA | TTS binary data type. To set this mode, you need to call `setData` to set the TTS binary data. |
| VOICE\_FILE | Local audio file type. To set this mode, you need to call `setFile` to set the local audio file path. |

##### Class Members:

class

[RealTimeTransimissionStateListener](../../Components/IMegaphoneManager/IMegaphoneManager_RealTimeTransimissionStateListener.md)

enum

[UploadState](#imegaphonemanager_uploadstate_inline)

###### enum UploadState

|  |
| --- |
| ``` enum UploadState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

Streaming mode upload status. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| UPLOADING | Uploading. |
| UPLOAD\_SUCCESS | Upload successfully. |
| UPLOAD\_FAILED | Upload failed. Please upload again. |
| MD5\_FAILED | MD5 verification failed. |
| ABORTED | Uploading aborted. |

##### Class Members:

class

[MegaphoneInfoListener](../../Components/IMegaphoneManager/IMegaphoneManager_MegaphoneInfoListener.md)

class

[MegaphoneInfo](../../Components/IMegaphoneManager/IMegaphoneManager_MegaphoneInfo.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found