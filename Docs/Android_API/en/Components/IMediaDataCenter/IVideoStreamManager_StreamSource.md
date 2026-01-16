**Navigation:** [IMediaDataCenter](IMediaDataCenter.md) > [IVideoStreamManager](IVideoStreamManager.md) > [StreamSource](IVideoStreamManager_StreamSource.md)

---

# class StreamSource

|  |
| --- |
| ``` @Deprecated  class StreamSource implements KeepProguard ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

This class represents the video source. It is used to get the unique ID of video source, its physical equipment's information and position. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getStreamId](#ivideostreammanager_streamsource_getstreamid_inline)

###### method getStreamId

|  |
| --- |
| ``` int getStreamId() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

Get the unique ID of video source.

##### Return:

|  |  |
| --- | --- |
| int | *Return the unique ID of video source.* |

method

[getPhysicalDeviceCategory](#ivideostreammanager_streamsource_getphysicaldevicecategory_inline)

###### method getPhysicalDeviceCategory

|  |
| --- |
| ``` PhysicalDeviceCategory getPhysicalDeviceCategory() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

Get the equipment type of video source.

##### Return:

|  |  |
| --- | --- |
| [PhysicalDeviceCategory](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource.md#ivideostreammanager_streamsource_physicaldevicecategory) | *Return the equipment type of video source.* |

method

[getPhysicalDeviceType](#ivideostreammanager_streamsource_getphysicaldevicetype_inline)

###### method getPhysicalDeviceType

|  |
| --- |
| ``` PhysicalDeviceType getPhysicalDeviceType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

Get the equipment model of video source.

##### Return:

|  |  |
| --- | --- |
| [PhysicalDeviceType](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource_PhysicalDeviceType.md#ivideostreammanager_streamsource_physicaldevicetype) | *Return the equipment model of video source.* |

method

[getPhysicalDevicePosition](#ivideostreammanager_streamsource_getphysicaldeviceposition_inline)

###### method getPhysicalDevicePosition

|  |
| --- |
| ``` PhysicalDevicePosition getPhysicalDevicePosition() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

Get the equipment position of video source.

##### Return:

|  |  |
| --- | --- |
| [PhysicalDevicePosition](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource.md#ivideostreammanager_streamsource_physicaldeviceposition) | *Return the equipment position of video source.* |

##### Related:

enum

[PhysicalDeviceCategory](#ivideostreammanager_streamsource_physicaldevicecategory_inline)

###### enum PhysicalDeviceCategory

|  |
| --- |
| ``` @Deprecated  enum PhysicalDeviceCategory ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

Equipment category of video source.

##### Enum Members:

|  |  |
| --- | --- |
| CAMERA | The video source is camera. |
| RADAR | The video source is radar. |
| PAYLOAD | The video source is 3rd party payload. |

##### Class Members:

class

[PhysicalDeviceType](../../Components/IMediaDataCenter/IVideoStreamManager_StreamSource_PhysicalDeviceType.md)

enum

[PhysicalDevicePosition](#ivideostreammanager_streamsource_physicaldeviceposition_inline)

###### enum PhysicalDevicePosition

|  |
| --- |
| ``` @Deprecated  enum PhysicalDevicePosition ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.video.stream |

##### Description:

Equipment position of video source.

##### Enum Members:

|  |  |
| --- | --- |
| PORTSIDE | The equipment of this video source is at portside. |
| STARBOARD | The equipment of this video source is at starboard. |
| UPSIDE | The equipment of this video source is at upside. |
| NOSE | The equipment of this video source is at nose. Normally it is FPV camera. |
| DEFAULT | The equipment of this video source is right under the aircraft. Normally a consumer level product will return this position because it has single camera. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found