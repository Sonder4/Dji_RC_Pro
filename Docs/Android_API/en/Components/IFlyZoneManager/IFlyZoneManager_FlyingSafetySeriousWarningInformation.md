**Navigation:** [IFlyZoneManager](IFlyZoneManager.md) > [FlySafeSeriousWarningInformation](IFlyZoneManager_FlyingSafetySeriousWarningInformation.md)

---

# class FlySafeSeriousWarningInformation

|  |
| --- |
| ``` class FlySafeSeriousWarningInformation ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Fly safety serious warning information. Including the event enumeration and description of fly safety serious warning information, automatic landing countdown, height limit and fly zone information. `Supported since MSDK 5.3.0`

##### Class Members:

method

[getEvent](#iflyzonemanager_flyingsafetyseriouswarninginformation_getevent_inline)

###### method getEvent

|  |
| --- |
| ``` FlySafeSeriousWarningEvent getEvent() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the event of fly safety serious warning. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [FlySafeSeriousWarningEvent](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_flysafeseriouswarningevent) | *Returns the event of fly safety serious warning.* |

method

[getDescription](#iflyzonemanager_flyingsafetyseriouswarninginformation_getdescription_inline)

###### method getDescription

|  |
| --- |
| ``` String getDescription() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the description of fly safety serious warning. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| String | *Returns the description of fly safety serious warning.* |

method

[getCountdown](#iflyzonemanager_flyingsafetyseriouswarninginformation_getcountdown_inline)

###### method getCountdown

|  |
| --- |
| ``` int getCountdown() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the countdown to start automatically landing. After the countdown is over, it will automatically drop to the limit height, you will not be able to control the throttle, but you can adjust the horizontal direction to avoid obstacles. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the countdown to start automatically landing.* |

method

[getHeightLimit](#iflyzonemanager_flyingsafetyseriouswarninginformation_getheightlimit_inline)

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

[getFlyZoneInformation](#iflyzonemanager_flyingsafetyseriouswarninginformation_getflyzoneinformation_inline)

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