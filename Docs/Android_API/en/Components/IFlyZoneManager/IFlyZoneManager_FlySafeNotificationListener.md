**Navigation:** [IFlyZoneManager](IFlyZoneManager.md) > [FlySafeNotificationListener](IFlyZoneManager_FlySafeNotificationListener.md)

---

# class FlySafeNotificationListener

|  |
| --- |
| ``` interface FlySafeNotificationListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

Fly safety notification listener. `Supported since MSDK 5.3.0`

##### Class Members:

method

[onTipNotificationUpdate](#iflyzonemanager_flysafenotificationlistener_ontipnotificationupdate_inline)

###### method onTipNotificationUpdate

|  |
| --- |
| ``` void onTipNotificationUpdate(@NonNull FlySafeTipInformation info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

When the fly safety tips information changes, this method will be called back. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeTipInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeTipInformation.md#iflyzonemanager_flysafetipinformation) info | *Fly safety tips information.* |

method

[onWarningNotificationUpdate](#iflyzonemanager_flysafenotificationlistener_onwarningnotificationupdate_inline)

###### method onWarningNotificationUpdate

|  |
| --- |
| ``` void onWarningNotificationUpdate(@NonNull FlySafeWarningInformation info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

When the fly safety warning information changes, this method will be called back. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeWarningInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyingSafetyWarningInformation.md#iflyzonemanager_flyingsafetywarninginformation) info | *Fly safety warning information.* |

method

[onSeriousWarningNotificationUpdate](#iflyzonemanager_flysafenotificationlistener_onseriouswarningnotificationupdate_inline)

###### method onSeriousWarningNotificationUpdate

|  |
| --- |
| ``` void onSeriousWarningNotificationUpdate(@NonNull FlySafeSeriousWarningInformation info) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

When the fly safety serious warning information changes, this method will be called back. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeSeriousWarningInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyingSafetySeriousWarningInformation.md#iflyzonemanager_flyingsafetyseriouswarninginformation) info | *Fly safety serious warning information.* |

method

[onSurroundingFlyZonesUpdate](#iflyzonemanager_flysafenotificationlistener_onsurroundingflyzonesupdate_inline)

###### method onSurroundingFlyZonesUpdate

|  |
| --- |
| ``` void onSurroundingFlyZonesUpdate(@NonNull List<FlyZoneInformation> infos) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

When the restricted fly zones within 50 kilometers of the aircraft changes, this method will be called back. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull List<[FlyZoneInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneInformation.md#iflyzonemanager_flyzoneinformation)> infos | *restricted fly zones within 50 kilometers of the aircraft.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found