**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IMediaManager](IMediaManager.md) > [PullMediaFileListParam](IMediaManager_PullMediaFileListParam.md)

---

# class PullMediaFileListParam

|  |
| --- |
| ``` class PullMediaFileListParam ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Media file list parameter class, which is used to set parameters of media file. You can choose to pull photoes, videos or all media file list. `Supported since MSDK 5.0.0`

##### Class Members:

class

[Builder](../../Components/IMediaDataCenter/IMediaManager_PullMediaFileListParam_Builder.md)

method

[getFilter](#imediamanager_pullmediafilelistparam_getfilter_inline)

###### method getFilter

|  |
| --- |
| ``` MediaFileFilter getFilter() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Get filter of pulling media file list. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [MediaFileFilter](../../Components/IMediaDataCenter/IMediaManager.md#imediamanager_mediafilefilter) | *Return filter of pulling media file list.* |

method

[getMediaFileIndex](#imediamanager_pullmediafilelistparam_getmediafileindex_inline)

###### method getMediaFileIndex

|  |
| --- |
| ``` int getMediaFileIndex() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Gets the media file index. Indicates that the media file is pulled from the media file index, if this value is set to "-1" or no parameter is set, it means that the first media file in the storage location is pulled. You can set the count of files to be pulled through "Count", if "Count" is set to "-1" or no parameter is set, it means that all media files are pulled. `Supported since MSDK 5.2.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return media file index.* |

method

[getCount](#imediamanager_pullmediafilelistparam_getcount_inline)

###### method getCount

|  |
| --- |
| ``` int getCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.media |

##### Description:

Gets the count of media files. Indicates the count of files to be pulled. If the value is set to "-1" or no parameter is set, it means that all media files are pulled. `Supported since MSDK 5.2.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the count of media files to be pulled.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found