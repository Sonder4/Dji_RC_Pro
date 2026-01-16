**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IMediaManager](IMediaManager.md) > [MediaFile](IMediaManager_MediaFile.md)

---

# class MediaFile

|  |
| --- |
| ``` class MediaFile implements Serializable ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Media file data class. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getFileIndex](#imediamanager_mediafile_getfileindex_inline)

###### method getFileIndex

|  |
| --- |
| ``` int getFileIndex() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get index of media file. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return index of media file.* |

method

[getFileType](#imediamanager_mediafile_getfiletype_inline)

###### method getFileType

|  |
| --- |
| ``` MediaFileType getFileType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get type of media file. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [MediaFileType](../../Components/IKeyManager/DJIValue.md#value_camera_enum_mediafiletype) | *Return type of media file.* |

method

[getFileName](#imediamanager_mediafile_getfilename_inline)

###### method getFileName

|  |
| --- |
| ``` String getFileName() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get name of media file. **Notice: Zenmuse P1 cannot obtain the extension name of the customized file. This is a legacy issue in the camera protocol design.** `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| String | *Return name of media file.* |

method

[getFileSize](#imediamanager_mediafile_getfilesize_inline)

###### method getFileSize

|  |
| --- |
| ``` long getFileSize() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get size of media file. Unit: byte. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| long | *Return size of media file.* |

method

[getDate](#imediamanager_mediafile_getdate_inline)

###### method getDate

|  |
| --- |
| ``` DateTime getDate() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get the generation time of media file. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [DateTime](../../Components/IKeyManager/Value_Camera_Struct_DateTime.md#value_camera_struct_datetime) | *Return the generation time of media file.* |

method

[getDuration](#imediamanager_mediafile_getduration_inline)

###### method getDuration

|  |
| --- |
| ``` Long getDuration() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get the duration of video play. Unit: second. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| Long | *Return the duration of video play.* |

method

[getFrameRate](#imediamanager_mediafile_getframerate_inline)

###### method getFrameRate

|  |
| --- |
| ``` VideoFrameRate getFrameRate() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get video framerate. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [VideoFrameRate](../../Components/IKeyManager/DJIValue.md#value_camera_enum_videoframerate) | *Return video framerate.* |

method

[getResolution](#imediamanager_mediafile_getresolution_inline)

###### method getResolution

|  |
| --- |
| ``` VideoResolution getResolution() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get video resolution. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| VideoResolution | *Return video resolution.* |

method

[getPhotoRatio](#imediamanager_mediafile_getphotoratio_inline)

###### method getPhotoRatio

|  |
| --- |
| ``` PhotoRatio getPhotoRatio() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get photo ratio. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [PhotoRatio](../../Components/IKeyManager/DJIValue.md#value_camera_enum_photoratio) | *Return photo ratio.* |

method

[getThumbNail](#imediamanager_imediafile_getthumbnail_inline)

###### method getThumbNail

|  |
| --- |
| ``` Bitmap getThumbNail() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get media file thumbnail. Please call `pullThumbnailFromCamera` to pull the thumbnail from the camera first, and save to the memory. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| Bitmap | *Return media file thumbnail.* |

method

[pullThumbnailFromCamera](#imediamanager_imediafile_pullthumbnailfromcamera_inline)

###### method pullThumbnailFromCamera

|  |
| --- |
| ``` void pullThumbnailFromCamera(CommonCallbacks.CompletionCallbackWithParam<Bitmap> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Pull the thumbnail of media file from the camera. The pulled thumbnail will be returned to the developer in the callback method and will be saved to the memory at the same time. You can call `getThumbNail` to get thumbnail from the memory. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Bitmap> callback | *Return the execution result of callback.* |

method

[pullPreviewFromCamera](#imediamanager_imediafile_pullpreviewfromcamera_inline)

###### method pullPreviewFromCamera

|  |
| --- |
| ``` void pullPreviewFromCamera(@NonNull CommonCallbacks.CompletionCallbackWithParam<Bitmap> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Pull the preview of media file from the camera. The pulled preview will be returned to the developer in the callback method. MSDK will no save the preview in memory and you can save it as needed. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Bitmap> callback | *Return the execution result of callback.* |

method

[stopPullPreviewFromCamera](#imediamanager_imediafile_stoppullpreviewfromcamera_inline)

###### method stopPullPreviewFromCamera

|  |
| --- |
| ``` void stopPullPreviewFromCamera(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Stop pulling the preview of media file. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[pullOriginalMediaFileFromCamera](#imediamanager_imediafile_pulloriginalmediafilefromcamera_inline)

###### method pullOriginalMediaFileFromCamera

|  |
| --- |
| ``` void pullOriginalMediaFileFromCamera(long offset, @NonNull MediaFileDownloadListener callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Pull the origin media file from the camera. The pulled media file will be returned to the developer in the callback method. MSDK will no save the origin media file in memory and you can save it as needed. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| long offset | *Offset value of media file download. Unit:byte.* |
| @NonNull [MediaFileDownloadListener](../../Components/IMediaDataCenter/IMediaManager_MediaFileDownloadListener.md#imediamanager_mediafiledownloadlistener) callback | *Return the execution result of callback.* |

method

[stopPullOriginalMediaFileFromCamera](#imediamanager_imediafile_stoppulloriginalmediafilefromcamera_inline)

###### method stopPullOriginalMediaFileFromCamera

|  |
| --- |
| ``` void stopPullOriginalMediaFileFromCamera(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Stop pulling origin media file. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result of callback.* |

method

[pullXMPFileDataFromCamera](#imediamanager_imediafile_pullxmpfiledatafromcamera_inline)

###### method pullXMPFileDataFromCamera

|  |
| --- |
| ``` void pullXMPFileDataFromCamera(CommonCallbacks.CompletionCallbackWithParam<String> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Pull XMP information in photo file from the camera. **Notice: Currently only supports Zenmuse H20 series cameras, Zenmuse L1 camera, and Zenmuse P1 camera.** `Supported since MSDK 5.7.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<String> callback | *Return the execution result of callback.* |

method

[getXMPFileData](#imediamanager_imediafile_getxmpfiledata_inline)

###### method getXMPFileData

|  |
| --- |
| ``` String getXMPFileData() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Synchronously obtain XMP information in photo file. If the return is empty, please call `pullXMPFileDataFromCamera` to pull XMP information from the camera. **Notice: Currently only supports Zenmuse H20 series cameras, Zenmuse L1 camera, and Zenmuse P1 camera.** `Supported since MSDK 5.7.0`

##### Return:

|  |  |
| --- | --- |
| String | *Return XMP information in photo file.* |

method

[pullXMPCustomInfoFromCamera](#imediamanager_imediafile_pullxmpcustominfofromcamera_inline)

###### method pullXMPCustomInfoFromCamera

|  |
| --- |
| ``` void pullXMPCustomInfoFromCamera(CommonCallbacks.CompletionCallbackWithParam<String> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Pull XMP custom information in photo file from the camera. **Notice: Currently only supports Zenmuse H20 series cameras, Zenmuse L1 camera, and Zenmuse P1 camera.** `Supported since MSDK 5.7.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<String> callback | *Return the execution result of callback.* |

method

[getXMPCustomInfo](#imediamanager_imediafile_getxmpcustominfo_inline)

###### method getXMPCustomInfo

|  |
| --- |
| ``` String getXMPCustomInfo() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Synchronously obtain XMP custom information in photo file. If the return is empty, please call `pullXMPFileDataFromCamera` to pull XMP information from the camera. **Notice: Currently only supports Zenmuse H20 series cameras, Zenmuse L1 camera, and Zenmuse P1 camera.** `Supported since MSDK 5.7.0`

##### Return:

|  |  |
| --- | --- |
| String | *Return XMP custom information in photo file.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found