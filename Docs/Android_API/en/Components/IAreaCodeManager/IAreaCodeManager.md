**Navigation:** [IAreaCodeManager](IAreaCodeManager.md)

---

# class IAreaCodeManager

|  |
| --- |
| ``` interface IAreaCodeManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used to manage area code. Users can get the current area code through this class. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getAreaCode](#iareacodemanager_getareacode_inline)

###### method getAreaCode

|  |
| --- |
| ``` AreaCodeData getAreaCode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get current area code data. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [AreaCodeData](../../Components/IAreaCodeManager/IAreaCodeManager_AreaCodeData.md#iareacodemanager_areacodedata) | *Return current area code data.* |

method

[addAreaCodeChangeListener](#iareacodemanager_addareacodechangelistener_inline)

###### method addAreaCodeChangeListener

|  |
| --- |
| ``` void addAreaCodeChangeListener(@NonNull AreaCodeChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of area code. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [AreaCodeChangeListener](../../Components/IAreaCodeManager/IAreaCodeManager_AreaCodeData_AreaCodeChangeListener.md#iareacodemanager_areacodedata_areacodechangelistener) listener | *Listener of area code.* |

method

[removeAreaCodeChangeListener](#iareacodemanager_removeareacodechangelistener_inline)

###### method removeAreaCodeChangeListener

|  |
| --- |
| ``` void removeAreaCodeChangeListener(@NonNull AreaCodeChangeListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of area code. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [AreaCodeChangeListener](../../Components/IAreaCodeManager/IAreaCodeManager_AreaCodeData_AreaCodeChangeListener.md#iareacodemanager_areacodedata_areacodechangelistener) listener | *Listener of area code.* |

method

[clearAllListeners](#iareacodemanager_clearalllisteners_inline)

###### method clearAllListeners

|  |
| --- |
| ``` void clearAllListeners() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of area code. `Supported since MSDK 5.0.0`

##### Related:

enum

[AreaCode](#iareacodemanager_areacode_inline)

###### enum AreaCode

|  |
| --- |
| ``` enum AreaCode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.areacode |

##### Description:

Aera code class. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| CHINA | China |
| UNITED\_STATES\_OF\_AMERICA | America |
| FRANCE | France |
| JAPAN | Japan |

##### Class Members:

class

[AreaCodeData](../../Components/IAreaCodeManager/IAreaCodeManager_AreaCodeData.md)

enum

[AreaCodeDataSource](#iareacodemanager_areacodedatasource_inline)

###### enum AreaCodeDataSource

|  |
| --- |
| ``` enum AreaCodeDataSource ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.areacode |

##### Description:

Area code source，which is used to get the judgment source of the current area code. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| UNRELIABLE\_SOURCE | The area code database(ac.zip) is illegally changed, and the area code will be returned to "CN". |
| AIRCRAFT\_GPS | Judge the area code through the GPS of the aircraft. |
| PHONE\_GPS | Judge the area code through the GPS of the phone. |
| MCC | Judge the area code through the MCC of the phone. |
| IP | Judge the area code through server IP. |
| LOW\_ACCURACY\_GPS | Judge the area code by calculating the nearest neighbor city through GPS, with low reliability. |
| CACHE | Get the area code through the cache. |

##### Class Members:

class

[AreaCodeChangeListener](../../Components/IAreaCodeManager/IAreaCodeManager_AreaCodeData_AreaCodeChangeListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found