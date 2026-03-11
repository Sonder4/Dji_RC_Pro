**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IMediaManager](IMediaManager.md)

---

# class IMediaManager

|  |
| --- |
| ``` interface IMediaManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Media file management class, which providing functions such as media file list download, media file preview, download and video playback. `Supported since MSDK 5.0.0`

##### Class Members:

method

[enable](#imediamanager_enable_inline)

###### method enable

|  |
| --- |
| ``` void enable(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enter the media file management module. Calling this interface, camera will enter media file management module. In this mode, you can pull media file list and playback the video in camera. At this time , camera will no be able to take photo and record, image transmission can not be display normally. It is suggested that you call this interface when you enter the album. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[disable](#imediamanager_disable_inline)

###### method disable

|  |
| --- |
| ``` void disable(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Exit the media file management module. Calling this interface, camera will exit media file management module. Camera can take photo and record normally and the image transmission will diaplay normally. It is suggested that you call this interface when you exit the album. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[addMediaFileListStateListener](#imediamanager_addmediafileliststatelistener_inline)

###### method addMediaFileListStateListener

|  |
| --- |
| ``` void addMediaFileListStateListener(MediaFileListStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set media file list status listener. Through this listener, the status of media file list can be monitored. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MediaFileListStateListener](../../Components/IMediaDataCenter/IMediaManager_MediaFileListStateListener.md#imediamanager_mediafileliststatelistener) listener | *Listener of media file list status.* |

method

[removeMediaFileListStateListener](#imediamanager_removemediafileliststatelistener_inline)

###### method removeMediaFileListStateListener

|  |
| --- |
| ``` void removeMediaFileListStateListener(MediaFileListStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of media file list status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MediaFileListStateListener](../../Components/IMediaDataCenter/IMediaManager_MediaFileListStateListener.md#imediamanager_mediafileliststatelistener) listener | *Listener of media file list status.* |

method

[removeAllMediaFileListStateListener](#imediamanager_removeallmediafileliststatelistener_inline)

###### method removeAllMediaFileListStateListener

|  |
| --- |
| ``` void removeAllMediaFileListStateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of media file list status. `Supported since MSDK 5.0.0`

method

[getMediaFileListState](#imediamanager_getmediafileliststate_inline)

###### method getMediaFileListState

|  |
| --- |
| ``` MediaFileListState getMediaFileListState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the media file list status. This is an synchronous interface. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [MediaFileListState](../../Components/IMediaDataCenter/IMediaManager.md#imediamanager_mediafileliststate) | *Return the media file list status.* |

method

[setMediaFileDataSource](#imediamanager_setmediafiledatasource_inline)

###### method setMediaFileDataSource

|  |
| --- |
| ``` void setMediaFileDataSource(MediaFileListDataSource source) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the data source of media file that needed. You can choose media file from selected storage location and selected loads. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MediaFileListDataSource](../../Components/IMediaDataCenter/IMediaManager_MediaFileListDataSource.md#imediamanager_mediafilelistdatasource) source | *Data source of media file.* |

method

[pullMediaFileListFromCamera](#imediamanager_pullmediafilelistfromcamera_inline)

###### method pullMediaFileListFromCamera

|  |
| --- |
| ``` void pullMediaFileListFromCamera(@NonNull PullMediaFileListParam param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Pull the media file list from camera. When the media file list status `MediaFileListState` is `IDLE`, this method should be called to pull full data. When new media files are generated, MSDK will automatically pull the latest media file list from the camera. When the media file list status `MediaFileListState` changes to `UP_TO_DATE`, it means that the process of pulling media file list is finished. Please call the `getMediaFileListData` to get media file list data. **Notice: Zenmuse P1 and Zenmuse L1 only support pulling the full multimedia file list, and setting mediaFileIndex and count is invalid.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PullMediaFileListParam](../../Components/IMediaDataCenter/IMediaManager_PullMediaFileListParam.md#imediamanager_pullmediafilelistparam) param | *Parameters of media file list. Zenmuse P1 and Zenmuse L1 only support pulling the full multimedia file list, and setting mediaFileIndex and count is invalid.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[stopPullMediaFileListFromCamera](#imediamanager_stoppullmediafilelistfromcamera_inline)

###### method stopPullMediaFileListFromCamera

|  |
| --- |
| ``` void stopPullMediaFileListFromCamera() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stop pulling the media file list from camera. `Supported since MSDK 5.0.0`

method

[getMediaFileListData](#imediamanager_getmediafilelistdata_inline)

###### method getMediaFileListData

|  |
| --- |
| ``` MediaFileListData getMediaFileListData() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get parameters of media file list. When the media file list status `MediaFileListState` is monitored to change to `UP_TO_DATE`, it means media file list have updated. This interface should be called again to update the media file list data. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [MediaFileListData](../../Components/IMediaDataCenter/IMediaManager_MediaFileListData.md#imediamanager_mediafilelistdata) | *Return parameters of media file list.* |

method

[deleteMediaFiles](#imediamanager_deletemediafiles_inline)

###### method deleteMediaFiles

|  |
| --- |
| ``` void deleteMediaFiles(List<MediaFile> files, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Delete the media file in camera. After calling this interface, media file list status `MediaFileListState` will change to `UPDATING`. After the deletion, the media file list status `MediaFileListState` is `UP_TO_DATE`, please call `getMediaFileListData` to update media file list data. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| List<[MediaFile](../../Components/IMediaDataCenter/IMediaManager_MediaFile.md#imediamanager_mediafile)> files | *Media file list that needs to be deleted.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[setMediaFileXMPCustomInfo](#imediamanager_setmediafilexmpcustominfo_inline)

###### method setMediaFileXMPCustomInfo

|  |
| --- |
| ``` void setMediaFileXMPCustomInfo(@NonNull String information, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the XMP custom information. After the setting is successful, each time a photo file is generated, the XMP custom information will be stored in the XMP metadata of the photo file. You can call the `pullXMPCustomInfoFromCamera` in the MdeiaFile class to pull the XMP custom information of the specified photo file from the camera. **Notice: 1. One setting is valid for multiple times. After the camera is reboot, the custom information will be cleared, and it needs to be set again. 2. Currently only supports Zenmuse H20 series cameras, Zenmuse L1 camera, and Zenmuse P1 camera.** `Supported since MSDK 5.7.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull String information | *XMP custom information that needs to be set. Use UTF-8 encoding and be less than or equal to 31 characters in length.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[getMediaFileXMPCustomInfo](#imediamanager_getmediafilexmpcustominfo_inline)

###### method getMediaFileXMPCustomInfo

|  |
| --- |
| ``` void getMediaFileXMPCustomInfo(@NonNull final CommonCallbacks.CompletionCallbackWithParam<String> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the XMP custom information. **Notice: Currently only supports Zenmuse H20 series cameras, Zenmuse L1 camera, and Zenmuse P1 camera.** `Supported since MSDK 5.7.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<String> callback | *Return the execution result of callback.* |

method

[playVideo](#imediamanager_playvideo_inline)

###### method playVideo

|  |
| --- |
| ``` @Deprecated     void playVideo(MediaFile mediaFile, CommonCallbacks.CompletionCallbackWithParam<IVideoFrame> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start to play the set media file. Media file data can be obtained by callback method. - 1. If you want to decode to display the video by yourself, you can call custom decoder to decode the video through the video frame data `IVideoFrame` obtained from callback. - 2. You can also use the decoder `IVideoDecoder` provided by DJI to decode video. Using `EXTENDED_STREAM_CHANNEL` to init `IVideoDecoder`, and then call `setMediaFile` to set the media file into the decoder. Finally, call `queueInFrame` to send the video frame data `IVideoFrame` to decode. **Note: This interface is deprecated starting from MSDK 5.8.0. Please call `playVideoToSurface` to play media file.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MediaFile](../../Components/IMediaDataCenter/IMediaManager_MediaFile.md#imediamanager_mediafile) mediaFile | *Media file list that needs to playback.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[IVideoFrame](../../Components/IMediaDataCenter/IVideoStreamManager_IVideoFrame.md#ivideostreammanager_ivideoframe)> callback | *Return the execution result of callback.* |

method

[playVideoToSurface](#imediamanager_playvideotosurface_inline)

###### method playVideoToSurface

|  |
| --- |
| ``` void playVideoToSurface(MediaFile mediaFile,                         Surface surface,                         int surfaceWidth,                         int surfaceHeight,                         ICameraStreamManager.ScaleType scaleType,                         MediaFrameListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start playing the set media file. Media file data can be obtained by callback method. **Note: If you want to decode to display the video by yourself, you can call custom decoder to decode the video through the video stream data from the listener.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [MediaFile](../../Components/IMediaDataCenter/IMediaManager_MediaFile.md#imediamanager_mediafile) mediaFile | *Media file list that needs to playback.* |
| Surface surface | *The surface that needs to be displayed.* |
| int surfaceWidth | *The width of surface that needs to be displayed.* |
| int surfaceHeight | *The height of surface that needs to be displayed.* |
| [ICameraStreamManager](../../Components/IMediaDataCenter/ICameraStreamManager.md#icamerastreammanager).ScaleType scaleType | *Set the scale type required for video stream display.* |
| [MediaFrameListener](../../Components/IMediaDataCenter/IMediaManager_MediaFrameListener.md#imediamanager_mediaframelistener) listener | *Return the execution result of callback.* |

method

[seekVideo](#imediamanager_seekvideo_inline)

###### method seekVideo

|  |
| --- |
| ``` void seekVideo(int position, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Jump to play media file. You can set a new position to play. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int position | *The location where you need to jump to play. Unit:second.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[pauseVideo](#imediamanager_pausevideo_inline)

###### method pauseVideo

|  |
| --- |
| ``` void pauseVideo(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Pause playing the media file. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[resumeVideo](#imediamanager_resumevideo_inline)

###### method resumeVideo

|  |
| --- |
| ``` void resumeVideo(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Resume to play the media file. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[stopVideo](#imediamanager_stopvideo_inline)

###### method stopVideo

|  |
| --- |
| ``` void stopVideo(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stop playing the media file. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[addVideoPlayStateListener](#imediamanager_addvideoplaystatelistener_inline)

###### method addVideoPlayStateListener

|  |
| --- |
| ``` void addVideoPlayStateListener(VideoPlayStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the listener of media file playing status. Through this listener, status of media file playing can be monitored. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [VideoPlayStateListener](../../Components/IMediaDataCenter/IMediaManager_VideoPlayStateListener.md#imediamanager_videoplaystatelistener) listener | *Listener of media file playing status.* |

method

[removeVideoPlayStateListener](#imediamanager_removevideoplaystatelistener_inline)

###### method removeVideoPlayStateListener

|  |
| --- |
| ``` void removeVideoPlayStateListener(VideoPlayStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove listener of media file playing status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [VideoPlayStateListener](../../Components/IMediaDataCenter/IMediaManager_VideoPlayStateListener.md#imediamanager_videoplaystatelistener) listener | *Listener of media file playing status.* |

method

[removeAllVideoPlayStateListener](#imediamanager_removeallvideoplaystatelistener_inline)

###### method removeAllVideoPlayStateListener

|  |
| --- |
| ``` void removeAllVideoPlayStateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove listeners of media file playing status. `Supported since MSDK 5.0.0`

method

[release](#imediamanager_release_inline)

###### method release

|  |
| --- |
| ``` void release() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Resource release. Calling this interface will cancel the media file data downloading, clear the thumbnail cache and clear the media file list data. It is recommended that you call it when the system memory is insufficient. `Supported since MSDK 5.0.0`

##### Related:

class

[MediaFileListDataSource](../../Components/IMediaDataCenter/IMediaManager_MediaFileListDataSource.md)

class

[PullMediaFileListParam](../../Components/IMediaDataCenter/IMediaManager_PullMediaFileListParam.md)

enum

[MediaFileFilter](#imediamanager_mediafilefilter_inline)

###### enum MediaFileFilter

|  |
| --- |
| ``` enum MediaFileFilter ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Pulling media file list filter setting class. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| VIDEO | Video. |
| PHOTO | Photo. |
| ALL | All media files. |

##### Class Members:

class

[MediaFileListData](../../Components/IMediaDataCenter/IMediaManager_MediaFileListData.md)

class

[MediaFile](../../Components/IMediaDataCenter/IMediaManager_MediaFile.md)

enum

[MediaFileListState](#imediamanager_mediafileliststate_inline)

###### enum MediaFileListState

|  |
| --- |
| ``` enum MediaFileListState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Media file list status. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | IDLE status. When the media file list is in IDLE status, `pullMediaFileListFromCamera` should be called to pull full data. |
| UP\_TO\_DATE | Updated status. When the media file list is in updated status, `getMediaFileListData` should be called to update the media file list data. |
| UPDATING | Updating status. When the media file list is in updating status, it means that the MSDK is synchronizing with date in camera. After the synchronization, the status will change to `UP_TO_DATE`, `getMediaFileListData` should be called to update the media file list data. |

##### Class Members:

class

[MediaFileListStateListener](../../Components/IMediaDataCenter/IMediaManager_MediaFileListStateListener.md)

enum

[VideoPlayState](#imediamanager_videoplaystate_inline)

###### enum VideoPlayState

|  |
| --- |
| ``` enum VideoPlayState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Enum type of media file playing status. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | IDEL status. The media file play function is not enabled. `playVideo` can be called to play media file. |
| PLAYING | Playing. |
| ENDED | Play is ended. |
| STOPPED | Play is stopped. When `stopVideo` is called successfully to stop playing media file, this status will be called back. |

##### Class Members:

class

[VideoPlayStatus](../../Components/IMediaDataCenter/IMediaManager_VideoPlayStatus.md)

class

[VideoPlayStateListener](../../Components/IMediaDataCenter/IMediaManager_VideoPlayStateListener.md)

class

[MediaFileDownloadListener](../../Components/IMediaDataCenter/IMediaManager_MediaFileDownloadListener.md)

class

[MediaFrameListener](../../Components/IMediaDataCenter/IMediaManager_MediaFrameListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found