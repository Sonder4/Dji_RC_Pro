**Navigation:** [IFlyZoneManager](IFlyZoneManager.md) > [FlySafeTipInformation](IFlyZoneManager_FlySafeTipInformation.md)

---

# class FlySafeTipInformation

|  |
| --- |
| ``` class FlySafeTipInformation ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Fly safety tips information. Including the event enumeration and description of fly safety tips information, height limit and fly zone information. `Supported since MSDK 5.3.0`

##### Class Members:

method

[getEvent](#iflyzonemanager_flysafetipinformation_getevent_inline)

###### method getEvent

|  |
| --- |
| ``` FlySafeTipEvent getEvent() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the event of fly safety tip. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [FlySafeTipEvent](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_flysafetipevent) | *Returns the event of fly safety tip.* |

method

[getDescription](#iflyzonemanager_flysafetipinformation_getdescription_inline)

###### method getDescription

|  |
| --- |
| ``` String getDescription() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the description of fly safety tip information. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| String | *Returns the description of fly safety tip information.* |

method

[getHeightLimit](#iflyzonemanager_flysafetipinformation_getheightlimit_inline)

###### method getHeightLimit

|  |
| --- |
| ``` int getHeightLimit() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the height limit of current fly zone. Returns 0 if not in the height limit zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the height limit of current fly zone.* |

method

[getFlyZoneInformation](#iflyzonemanager_flysafetipinformation_getflyzoneinformation_inline)

###### method getFlyZoneInformation

|  |
| --- |
| ``` List<FlyZoneInformation> getFlyZoneInformation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets information about the fly zones. Returns null if not in a restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| List<[FlyZoneInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneInformation.md#iflyzonemanager_flyzoneinformation)> | *Returns information about the fly zones.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found