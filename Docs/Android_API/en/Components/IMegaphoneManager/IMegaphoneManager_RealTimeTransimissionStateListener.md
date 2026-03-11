**Navigation:** [IMegaphoneManager](IMegaphoneManager.md) > [RealTimeTransimissionStateListener](IMegaphoneManager_RealTimeTransimissionStateListener.md)

---

# class RealTimeTransimissionStateListener

|  |
| --- |
| ``` interface RealTimeTransimissionStateListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

Status listener for streaming mode. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onProgress](#imegaphonemanager_realtimetransimissionstatelistener_onprogress_inline)

###### method onProgress

|  |
| --- |
| ``` void onProgress(long sentBytes, long totalBytes) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To get the transfer progress for streaming mode. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| long sentBytes | *The number of bytes that have been transferred.* |
| long totalBytes | *The total number of bytes that need to be transferred.* |

method

[onUploadedStatus](#imegaphonemanager_realtimetransimissionstatelistener_onuploadedstatus_inline)

###### method onUploadedStatus

|  |
| --- |
| ``` void onUploadedStatus(UploadState uploadState) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To get the transmission status of the streaming mode. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [UploadState](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_uploadstate) uploadState | *Transmission Status* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found