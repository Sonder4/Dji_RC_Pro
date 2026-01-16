**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [ILiveStreamManager](ILiveStreamManager.md) > [LiveStreamStatusListener](ILiveStreamManager_LiveStreamStatusListener.md)

---

# class LiveStreamStatusListener

|  |
| --- |
| ``` interface LiveStreamStatusListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

The listener of live stream status. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onLiveStreamStatusUpdate](#ilivestreammanager_livestreamstatuslistener_onlivestreamstatusupdate_inline)

###### method onLiveStreamStatusUpdate

|  |
| --- |
| ``` void onLiveStreamStatusUpdate(LiveStreamStatus status) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

The listener will return if the live stream status is changed. `Support since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [LiveStreamStatus](../../Components/IMediaDataCenter/ILiveStreamManager_LiveStreamStatus.md#ilivestreammanager_livestreamstatus) status | *Live stream status* |

method

[onError](#ilivestreammanager_livestreamstatuslistener_onerror_inline)

###### method onError

|  |
| --- |
| ``` void onError(IDJIError error) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

The listener will call this method if an error has occured. `Support since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *error code* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found