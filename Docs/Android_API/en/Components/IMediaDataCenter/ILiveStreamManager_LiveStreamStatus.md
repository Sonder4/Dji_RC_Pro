**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [ILiveStreamManager](ILiveStreamManager.md) > [LiveStreamStatus](ILiveStreamManager_LiveStreamStatus.md)

---

# class LiveStreamStatus

|  |
| --- |
| ``` class LiveStreamStatus ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

The live stream status is used to get the current FPS, bitrate, resolution, packet loss, and status. `Supported since MSDK 5.0.0`

##### Class Members:

method

[isStreaming](#ilivestreammanager_livestreamstatus_isstreaming_inline)

###### method isStreaming

|  |
| --- |
| ``` boolean isStreaming() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

Get whether the status is streaming. `Support since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *Return whether the status is streaming.* |

method

[getFps](#ilivestreammanager_livestreamstatus_getfps_inline)

###### method getFps

|  |
| --- |
| ``` int getFps() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

Get the FPS of live video streaming. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the FPS of live video streaming.* |

method

[getVbps](#ilivestreammanager_livestreamstatus_getvbps_inline)

###### method getVbps

|  |
| --- |
| ``` int getVbps() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

Get the bit rate of live video streaming. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the bit rate of live video streaming.* |

method

[getResolution](#ilivestreammanager_livestreamstatus_getresolution_inline)

###### method getResolution

|  |
| --- |
| ``` VideoResolution getResolution() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

Get the resolution of live video streaming. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| VideoResolution | *Return the resolution of live video streaming.* |

method

[getPacketLoss](#ilivestreammanager_livestreamstatus_getpacketloss_inline)

###### method getPacketLoss

|  |
| --- |
| ``` int getPacketLoss() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

Get the packet loss of live video streaming. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the packet loss of live video streaming.* |

method

[getPacketCacheLen](#ilivestreammanager_livestreamstatus_getpacketcachelen_inline)

###### method getPacketCacheLen

|  |
| --- |
| ``` int getPacketCacheLen() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

Get the packet cache length of live video streaming. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the packet cache length of live video streaming.* |

method

[getRtt](#ilivestreammanager_livestreamstatus_getrtt_inline)

###### method getRtt

|  |
| --- |
| ``` int getRtt() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.datacenter.livestream |

##### Description:

Get the latency of live video streaming. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the latency of live video streaming.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found