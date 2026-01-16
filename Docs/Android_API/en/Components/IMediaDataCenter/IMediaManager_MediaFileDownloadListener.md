**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IMediaManager](IMediaManager.md) > [MediaFileDownloadListener](IMediaManager_MediaFileDownloadListener.md)

---

# class MediaFileDownloadListener

|  |
| --- |
| ``` interface MediaFileDownloadListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Listener of media file download. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onStart](#imediamanager_mediafiledownloadlistener_onstart_inline)

###### method onStart

|  |
| --- |
| ``` void onStart() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

When the media file starts downloading, this method will be called back. `Supported since MSDK 5.0.0`

method

[onProgress](#imediamanager_mediafiledownloadlistener_onprogress_inline)

###### method onProgress

|  |
| --- |
| ``` void onProgress(long total, long current) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

When the edia file download progress changes, this method will be called back. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| long total | *The total number of bytes of media file to be downloaded.* |
| long current | *Bytes of downloaded media files.* |

method

[onRealtimeDataUpdate](#imediamanager_mediafiledownloadlistener_onrealtimedataupdate_inline)

###### method onRealtimeDataUpdate

|  |
| --- |
| ``` void onRealtimeDataUpdate(byte[] data, long position) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

When the media file data is downloaded, this method will be called back. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| byte[] data | *The media file data download from camera by byte.* |
| long position | *The byte offset into the meida file at which the data starts.* |

method

[onFinish](#imediamanager_mediafiledownloadlistener_onfinish_inline)

###### method onFinish

|  |
| --- |
| ``` void onFinish() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

When the media file is downloaded, this method will be called back. `Supported since MSDK 5.0.0`

method

[onFailure](#imediamanager_mediafiledownloadlistener_onfailure_inline)

###### method onFailure

|  |
| --- |
| ``` void onFailure(IDJIError error) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

when an error occurs in the media file downloading, this method will be called back. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *Media file download error.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found