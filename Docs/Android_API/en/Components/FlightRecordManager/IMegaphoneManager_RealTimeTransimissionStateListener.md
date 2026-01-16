# class RealTimeTransimissionStateListener

|  |
| --- |
| ``` interface RealTimeTransimissionStateListener ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

流式传输模式的状态监听器。

##### 类成员:

method

[onProgress](#imegaphonemanager_realtimetransimissionstatelistener_onprogress_inline)

###### method onProgress

|  |
| --- |
| ``` void onProgress(long sentBytes, long totalBytes) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

获取流式传输模式的传输进度。

##### 输入参数：

|  |  |
| --- | --- |
| long sentBytes | *已经传输的字节数。* |
| long totalBytes | *总共需要传输的字节数。* |

method

[onUploadedStatus](#imegaphonemanager_realtimetransimissionstatelistener_onuploadedstatus_inline)

###### method onUploadedStatus

|  |
| --- |
| ``` void onUploadedStatus(UploadState uploadState) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

获取流式传输模式的传输状态。

##### 输入参数：

|  |  |
| --- | --- |
| [UploadState](../../Components/FlightRecordManager/IMegaphoneManager.md#imegaphonemanager_uploadstate) uploadState | *传输状态。* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found