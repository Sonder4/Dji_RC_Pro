**Navigation:** [IPayloadCenter](IPayloadCenter.md)

---

# class IPayloadCenter

|  |
| --- |
| ``` interface IPayloadCenter ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The payload center class provides functions such as get the PayloadManager instance list of the payload supported by the aircraft. PayloadManager provides functions to get payload information, get and set widget information, and data receiving and sending. `Supported since MSDK 5.2.0`

##### Class Members:

method

[getPayloadManager](#ipayloadcenter_getpayloadmanager_inline)

###### method getPayloadManager

|  |
| --- |
| ``` Map<PayloadIndexType, IPayloadManager> getPayloadManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the PayloadManager instance list of the payload supported by the aircraft, which supports portside(main) payload, starboard payload, upper payload and extend(OSDK) payload. `Supported since MSDK 5.2.0`

##### Return:

|  |  |
| --- | --- |
| Map<PayloadIndexType, [IPayloadManager](../../Components/IPayloadCenter/IPayloadManager.md#ipayloadmanager)> | *Returns the PayloadManager instance list of the payload supported by the aircraft.* |

method

[getIntelligentBoxManager](#ipayloadcenter_getintelligentboxmanager_inline)

###### method getIntelligentBoxManager

|  |
| --- |
| ``` Map<PayloadIndexType, IIntelligentBoxManager> getIntelligentBoxManager() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the IntelligentBoxManager instance list of the Manifold supported by the aircraft. `Supported since MSDK 5.15.0`

##### Return:

|  |  |
| --- | --- |
| Map<PayloadIndexType, [IIntelligentBoxManager](../../Components/IPayloadCenter/IIntelligentBoxManager.md#iintelligentboxmanager)> | *Returns the IntelligentBoxManager instance list of the Manifold supported by the aircraft.* |

##### Related:

class

[IPayloadManager](../../Components/IPayloadCenter/IPayloadManager.md)

class

[IIntelligentBoxManager](../../Components/IPayloadCenter/IIntelligentBoxManager.md)

enum

[PayloadIndexType](#ipayloadcenter_payloadindextype_inline)

###### enum PayloadIndexType

|  |
| --- |
| ``` enum PayloadIndexType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload |

##### Description:

Payload/Manifold index type. `Supported since MSDK 5.2.0`

##### Enum Members:

|  |  |
| --- | --- |
| LEFT\_OR\_MAIN | The payload is attached to the portside(main) position of the aircraft. |
| RIGHT | The payload is attached to the starboard position of the aircraft. |
| UP | The payload is attached to the upper position of the aircraft. |
| EXTERNAL | The payload is attached to the extend position of the aircraft. On the M300 RTK, this location is the OSDK extension location. On the M350 RTK, this location is the E-PORT location. |
| PORT\_1 | The payload is attached to the port 1 position of the aircraft. |
| PORT\_2 | The payload is attached to the port 2 position of the aircraft. |
| PORT\_3 | The payload is attached to the port 3 position of the aircraft. |
| PORT\_4 | The payload is attached to the port 4 position of the aircraft. |
| PORT\_5 | The payload is attached to the port 5 position of the aircraft. |
| PORT\_6 | The payload is attached to the port 6 position of the aircraft. |
| PORT\_7 | The payload is attached to the port 7 position of the aircraft. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found