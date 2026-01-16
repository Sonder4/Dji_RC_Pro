**Navigation:** [IFlyZoneManager](IFlyZoneManager.md)

---

# class IFlyZoneManager

|  |
| --- |
| ``` interface IFlyZoneManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class to manage unlock fly zone, which mainly supports the following functions:

- Get the notification about flying safety.
- Get the fly zone around the aircraft.
- Unlock the enhanced warning zone.
- Unlock the authorization fly zone.
- Unlock the restricted fly zone: download the licenses for the restricted fly zone, push the licenses to the aircraft, and enable or disable the licenses for the aircraft.
- Fly safety database upgrade.
  
  
`Supported since MSDK 5.3.0`

##### Class Members:

#### Fly Safety Notification

method

[addFlySafeNotificationListener](#iflyzonemanager_addflysafenotificationlistener_inline)

###### method addFlySafeNotificationListener

|  |
| --- |
| ``` void addFlySafeNotificationListener(@NonNull FlySafeNotificationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener for fly safety notification. Fly safety n notification information includes: tips information, return home information, warning information, serious warning information, and fly zone near the aircraft. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeNotificationListener](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeNotificationListener.md#iflyzonemanager_flysafenotificationlistener) listener | *Fly safety notification listener.* |

method

[removeFlySafeNotificationListener](#iflyzonemanager_removeflysafenotificationlistener_inline)

###### method removeFlySafeNotificationListener

|  |
| --- |
| ``` void removeFlySafeNotificationListener(@NonNull FlySafeNotificationListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

remove the listener for fly safety notification. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeNotificationListener](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeNotificationListener.md#iflyzonemanager_flysafenotificationlistener) listener | *Fly Safety Notification listener.* |

method

[clearAllFlySafeNotificationListener](#iflyzonemanager_clearallflysafenotificationlistener_inline)

###### method clearAllFlySafeNotificationListener

|  |
| --- |
| ``` void clearAllFlySafeNotificationListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clear all listeners for fly safety notification. `Supported since MSDK 5.3.0`

method

[getFlyZonesInSurroundingArea](#iflyzonemanager_getflyzonesinsurroundingarea_inline)

###### method getFlyZonesInSurroundingArea

|  |
| --- |
| ``` void getFlyZonesInSurroundingArea(@NonNull LocationCoordinate2D location, @NonNull CommonCallbacks.CompletionCallbackWithParam<List<FlyZoneInformation>> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the fly zones within 50 kilometers from the central location. **Note: If there are too many fly zones obtained, drawing the fly zones on the map may cause ANR problems. It is recommended to optimize the display.** `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d) location | *Set the the central location.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<List<[FlyZoneInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneInformation.md#iflyzonemanager_flyzoneinformation)>> callback | *Return the callback of execution result.* |

#### Enhanced Warning Fly Zone

method

[unlockAllEnhancedWarningFlyZone](#iflyzonemanager_unlockallenhancedwarningflyzone_inline)

###### method unlockAllEnhancedWarningFlyZone

|  |
| --- |
| ``` void unlockAllEnhancedWarningFlyZone(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Unlock all enhanced warning fly zones. After unlocking, the aircraft will no longer prompt any enhanced warning zone, and will continue to warn after reboot the aircraft. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

#### Authorization Fly Zone

method

[unlockAuthorizationFlyZone](#iflyzonemanager_unlockauthorizationflyzone_inline)

###### method unlockAuthorizationFlyZone

|  |
| --- |
| ``` void unlockAuthorizationFlyZone(int flyZoneID, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Unlock the authorization fly zone. After unlocking the authorization fly zone, the aircraft will be able to take off normally in the authorization fly zone. **Note: M300 RTK, M350 RTK, M30 series, M3E series and other enterprise aircraft can be directly transferred to flyZoneID to unlock the authorized area, and the authorization fly zone will continue to be banned after reboot the aircraft. DJI Mini 3 and DJI Mini 3 Pro and other consumer aircraft, you need to refer to the unlocking process of the restricted fly zone. After applying for unlocking on the official website of DJI, call `downloadFlyZoneLicensesFromServer` and `pushFlyZoneLicensesToAircraft` to unlock the authorization fly zone.** `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int flyZoneID | *Authorization fly zone ID.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

#### Restricted Fly Zone

method

[downloadFlyZoneLicensesFromServer](#iflyzonemanager_downloadflyzonelicensesfromserver_inline)

###### method downloadFlyZoneLicensesFromServer

|  |
| --- |
| ``` void downloadFlyZoneLicensesFromServer(@NonNull CommonCallbacks.CompletionCallbackWithParam<List<FlyZoneLicenseInfo>> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the licenses for restricted fly zone from the server. In order to get the unlock licenses for the relevant account, please call `logInDJIUserAccount` to log in to the DJI account first, **Note: If you want to draw the restricted fly zone in the license on the map, please pass the areaID in the restricted fly zone to `getFlyZonesByAreaID`, then you can get the restricted fly zone information of the areaID for drawing.** `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<List<[FlyZoneLicenseInfo](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneLicenseInfo.md#iflyzonemanager_flyzonelicenseinfo)>> callback | *Return the callback of execution result.* |

method

[getFlyZonesByAreaID](#iflyzonemanager_getflyzonesbyareaid_inline)

###### method getFlyZonesByAreaID

|  |
| --- |
| ``` @NonNull     List<FlyZoneInformation> getFlyZonesByAreaID(int areaID) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the restricted fly zone information for a specific areaID. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int areaID | *Restricted fly zone ID.* |

##### Return:

|  |  |
| --- | --- |
| List<[FlyZoneInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneInformation.md#iflyzonemanager_flyzoneinformation)> | *Returns the restricted fly zone information for a specific areaID.* |

method

[pushFlyZoneLicensesToAircraft](#iflyzonemanager_pushflyzonelicensestoaircraft_inline)

###### method pushFlyZoneLicensesToAircraft

|  |
| --- |
| ``` void pushFlyZoneLicensesToAircraft(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Push the unlock licenses into the aircraft. MSDK will push the licenses matching the SN of the flight controller into the aircraft. After the push is successful, you can call `pullFlyZoneLicensesFromAircraft` to get the list of licenses that have been pushed to the aircraft. If you need to unlock the restricted zone, you can call `setFlyZoneLicensesEnabled` to enable or disable the unlock licenses. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[pullFlyZoneLicensesFromAircraft](#iflyzonemanager_pullflyzonelicensesfromaircraft_inline)

###### method pullFlyZoneLicensesFromAircraft

|  |
| --- |
| ``` void pullFlyZoneLicensesFromAircraft(@NonNull CommonCallbacks.CompletionCallbackWithParam<List<FlyZoneLicenseInfo>> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets the licenses pushed to the aircraft. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<List<[FlyZoneLicenseInfo](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneLicenseInfo.md#iflyzonemanager_flyzonelicenseinfo)>> callback | *Return the callback of execution result.* |

method

[deleteFlyZoneLicensesFromAircraft](#iflyzonemanager_deleteflyzonelicensesfromaircraft_inline)

###### method deleteFlyZoneLicensesFromAircraft

|  |
| --- |
| ``` void deleteFlyZoneLicensesFromAircraft(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Delete the licenses pushed to the aircraft. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[setFlyZoneLicensesEnabled](#iflyzonemanager_setflyzonelicensesenabled_inline)

###### method setFlyZoneLicensesEnabled

|  |
| --- |
| ``` void setFlyZoneLicensesEnabled(@NonNull FlyZoneLicenseInfo info, boolean isEnabled, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enable or disable unlock license. After enabling the license, the aircraft will be able to take off normally in the restricted fly zone. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlyZoneLicenseInfo](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneLicenseInfo.md#iflyzonemanager_flyzonelicenseinfo) info | *license information.* |
| boolean isEnabled | *`true` means enabled the licence.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

#### Fly Safety Database

method

[addFlySafeDatabaseListener](#iflyzonemanager_addflysafedatabaselistener_inline)

###### method addFlySafeDatabaseListener

|  |
| --- |
| ``` void addFlySafeDatabaseListener(@NonNull FlySafeDatabaseListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener for the fly safety database. Fly safety database information includes: database upgrade mode, database upgrade status, and manually imported database information. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeDatabaseListener](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeDatabaseListener.md#iflyzonemanager_flysafedatabaselistener) listener | *Fly safety database listener.* |

method

[removeFlySafeDatabaseListener](#iflyzonemanager_removeflysafedatabaselistener_inline)

###### method removeFlySafeDatabaseListener

|  |
| --- |
| ``` void removeFlySafeDatabaseListener(@NonNull FlySafeDatabaseListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener for fly safety database. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeDatabaseListener](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeDatabaseListener.md#iflyzonemanager_flysafedatabaselistener) listener | *Fly safety database listener.* |

method

[clearAllFlySafeDatabaseListener](#iflyzonemanager_clearallflysafedatabaselistener_inline)

###### method clearAllFlySafeDatabaseListener

|  |
| --- |
| ``` void clearAllFlySafeDatabaseListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clear all listeners for fly safety database. `Supported since MSDK 5.8.0`

method

[setFlySafeDynamicDatabaseUpgradeMode](#iflyzonemanager_setflysafedynamicdatabaseupgrademode_inline)

###### method setFlySafeDynamicDatabaseUpgradeMode

|  |
| --- |
| ``` void setFlySafeDynamicDatabaseUpgradeMode(@NonNull FlySafeDatabaseUpgradeMode flySafeDynamicDatabaseUpgradeMode , @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the upgrade mode of the fly safety dynamic database. Currently, only manual import of EU GeoZone data is supported. If you want to fly your drone compliantly in the EU, please refer to the following process to import EU GeoZone data into the aircraft.

- Check [European Aviation Safety Agency](https://www.easa.europa.eu/en/domains/civil-drones/naa) official website to download GeoZone data and import it into the aircraft.< /li>
- Call `setFlySafeDynamicDatabaseUpgradeMode` to set the fly safety dynamic database upgrade mode to manual import mode.
- Call `importFlySafeDynamicDatabaseToMSDK` to import GeoZone data into MSDK.
- Call `pushFlySafeDynamicDatabaseToAircraft` to synchronize GeoZone data to the aircraft.
- Call `getFlyZonesInSurroundingArea` to obtain GeoZone data within a range of 50 kilometers centered on the aircraft.
  
  
`Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [FlySafeDatabaseUpgradeMode](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_flysafedatabaseupgrademode) flySafeDynamicDatabaseUpgradeMode | *Fly safety dynamic database upgrade mode. Currently only supports manual import mode.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[importFlySafeDynamicDatabaseToMSDK](#iflyzonemanager_importflysafedynamicdatabasetomsdk_inline)

###### method importFlySafeDynamicDatabaseToMSDK

|  |
| --- |
| ``` void importFlySafeDynamicDatabaseToMSDK(String databaseFilePath, @NonNull CommonCallbacks.CompletionCallbackWithProgress<Double> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Import the fly safety dynamic database into the MSDK. Currently, only only support to import the EU GeoZone data. If you want to fly your drone compliantly in the EU, please refer to the following process to import EU GeoZone data into the aircraft.

- Check [European Aviation Safety Agency](https://www.easa.europa.eu/en/domains/civil-drones/naa) official website to download GeoZone data and import it into the aircraft.< /li>
- Call `setFlySafeDynamicDatabaseUpgradeMode` to set the fly safety dynamic database upgrade mode to manual import mode.
- Call `importFlySafeDynamicDatabaseToMSDK` to import GeoZone data into MSDK.
- Call `pushFlySafeDynamicDatabaseToAircraft` to synchronize GeoZone data to the aircraft.
- Call `getFlyZonesInSurroundingArea` to obtain GeoZone data within a range of 50 kilometers centered on the aircraft.
  
  
`Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String databaseFilePath | *Database file path, the database file must be in JSON format and needs to comply with the ED-269 specification.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithProgress<Double> callback | *Return the callback of execution result.* |

method

[pushFlySafeDynamicDatabaseToAircraft](#iflyzonemanager_pushflysafedynamicdatabasetoaircraft_inline)

###### method pushFlySafeDynamicDatabaseToAircraft

|  |
| --- |
| ``` void pushFlySafeDynamicDatabaseToAircraft(@NonNull CommonCallbacks.CompletionCallbackWithProgress<Double>  callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Synchronize the fly safety dynamic database to the aircraft. Currently only support to synchronize the EU GeoZone data. If you want to fly your drone compliantly in the EU, please refer to the following process to import EU GeoZone data into the aircraft.

- Check [European Aviation Safety Agency](https://www.easa.europa.eu/en/domains/civil-drones/naa) official website to download GeoZone data and import it into the aircraft.< /li>
- Call `setFlySafeDynamicDatabaseUpgradeMode` to set the fly safety dynamic database upgrade mode to manual import mode.
- Call `importFlySafeDynamicDatabaseToMSDK` to import GeoZone data into MSDK.
- Call `pushFlySafeDynamicDatabaseToAircraft` to synchronize GeoZone data to the aircraft.
- Call `getFlyZonesInSurroundingArea` to obtain GeoZone data within a range of 50 kilometers centered on the aircraft.
  
  
`Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithProgress<Double> callback | *Return the callback of execution result.* |

##### Related:

enum

[FlySafeTipEvent](#iflyzonemanager_flysafetipevent_inline)

###### enum FlySafeTipEvent

|  |
| --- |
| ``` enum FlySafeTipEvent ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Event enumeration of fly safety prompt information. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| TAKE\_OFF\_IN\_HEIGHT\_LIMIT\_ZONE\_WITHOUT\_GPS | The aircraft is below the height limit zone and has no GPS signal, please pay attention to flight safety. |
| TAKE\_OFF\_IN\_HEIGHT\_LIMIT\_ZONE | The aircraft is below the height limit zone, please pay attention to flight safety. |
| TAKE\_OFF\_IN\_WARNING\_AREA | The aircraft is in the warning zone, please pay attention to flight safety. |
| TOUCH\_NO\_FLY\_ZONE | The aircraft has touched the no-fly zone, please control the aircraft as far as possible. |
| TOUCH\_AUTHORIZATION\_ZONE | The aircraft has touched the authorized area, please control the aircraft as far as possible. |
| REACH\_MAXIMUM\_FLIGHT\_HEIGHT | The aircraft has reached the maximum flight height in the height limit zone. |
| RTH\_AFFECTED\_AT\_BOUNDARY\_OF\_GEO\_ZONE | Your aircraft is at the boundary of a limited fly zone, Return To Home may be affected. Please fly away with caution. |
| AT\_BOUNDARY\_OF\_CUSTOM\_UNLOCK\_ZONE | Your aircraft is at the boundary of an Custom Unlocking Zone. |
| ENTER\_LIMIT\_HEIGHT\_AREA | The aircraft has entered the height limited area. |
| COLLISION\_WITH\_LIMIT\_HEIGHT\_AREA\_SOON\_IN\_WHITE\_LIST\_VALID\_AREA | The aircraft is approaching the fly zone, please pay attention to flight safety. |

##### Class Members:

class

[FlySafeTipInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeTipInformation.md)

enum

[FlySafeWarningEvent](#iflyzonemanager_flysafewarningevent_inline)

###### enum FlySafeWarningEvent

|  |
| --- |
| ``` enum FlySafeWarningEvent ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Event enumeration of fly safety warning information. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| TAKE\_OFF\_FAILED\_IN\_NO\_FLY\_ZONE | The aircraft was unable to take off in the no-fly zone. |
| TAKE\_OFF\_FAILED\_IN\_NO\_FLY\_ZONE\_WITHOUT\_GPS | The mobile device is in the no-fly zone, and the GPS signal of the aircraft is not good enough to take off. |
| TAKE\_OFF\_FAILED\_WITHOUT\_CUSTOM\_UNLOCKING | Cannot take off. You have not applied to fly in this zone. |
| TAKE\_OFF\_FAILED\_IN\_AUTHORIZED\_AREA\_WITHOUT\_GPS\_AND\_UNLOCKING | The mobile device is in the authorized area, and the GPS signal of the aircraft is not good, and it cannot take off before unlocking. |
| TAKE\_OFF\_FAILED\_IN\_AUTHORIZED\_ZONE\_WITHOUT\_UNLOCKING | The aircraft is not allowed to take off before being unlocked in the authorized area. |
| TAKE\_OFF\_FAILED\_IN\_AUTHORIZED\_ZONE | The aircraft is in the authorized area and you have obtained a fly license. |
| TAKE\_OFF\_IN\_ENHANCED\_WARNING\_ZONE | The aircraft is in the enhanced warning zone, please pay attention to fly safety. |
| TAKE\_OFF\_NEAR\_NO\_FLY\_ZONE | There is a no-fly zone nearby and there are fly restrictions. |
| LIMITED\_RESTRICTIONS\_NEARBY | There are limited restrictions in the nearby area. |
| AUTHORIZED\_ZONE\_WITHOUT\_UNLOCKING\_NEARBY | There is a authorized zone nearby and there are flight restrictions. |
| AUTHORIZED\_ZONE\_AND\_RESTRICTIONS\_NEARBY | There are authorized areas nearby and you have obtained a flight certificate. |
| RESTRICTIONS\_IN\_LIMITED\_FLY\_ZONE\_NEARBY | There are flight restrictions in the nearby limited flight area. |
| IN\_AUTHORIZATION\_ZONE | The aircraft is in the authorized area and you have obtained a flight certificate. |
| TAKE\_OFF\_FAILED\_UNDER\_LIMIT\_AREA\_WITH\_GPS\_ONCE | The mobile device is in the no-fly zone, and the GPS signal of the aircraft is not good enough to take off. |
| HAVE\_ONE\_HOUR\_WILL\_APPLY\_TFRS | After 1 hour, a temporary restricted flight zone will take effect within 7KM, please pay attention to flight safety. |

##### Class Members:

class

[FlySafeWarningInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyingSafetyWarningInformation.md)

enum

[FlySafeSeriousWarningEvent](#iflyzonemanager_flysafeseriouswarningevent_inline)

###### enum FlySafeSeriousWarningEvent

|  |
| --- |
| ``` enum FlySafeSeriousWarningEvent ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Event enumeration of fly safety serious warning information. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| IN\_NO\_FLY\_ZONE | The aircraft is in the no-fly zone. After the countdown is over, it will force an automatic drop. You will not be able to control the throttle, but you can adjust the horizontal direction to avoid obstacles. |
| IN\_LIMIT\_HEIGHT\_AREA | The aircraft is in the height limit zone. After the countdown is over, it will automatically drop to the limit height, you will not be able to control the throttle, but you can adjust the horizontal direction to avoid obstacles. |
| IN\_AUTHORIZATION\_ZONE\_WITHOUT\_UNLOCKING | The aircraft is in the authorized area. After the countdown is over, it will automatically drop to the limit height, you will not be able to control the throttle, but you can adjust the horizontal direction to avoid obstacles. |
| HAVE\_THREE\_MIN\_WILL\_APPLY\_TFRS | Three minutes later, a temporary restricted flight zone will take effect nearby, please pay attention to fly safety. |

##### Class Members:

class

[FlySafeSeriousWarningInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyingSafetySeriousWarningInformation.md)

enum

[FlySafeReturnToHomeEvent](#iflyzonemanager_flysafereturntohomeevent_inline)

###### enum FlySafeReturnToHomeEvent

|  |
| --- |
| ``` enum FlySafeReturnToHomeEvent ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Event enumeration of fly safety return to home information. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| NEAR\_NO\_FLY\_ZONE | Approaching a No-Fly Zone. Return to home may be affected. Fly with caution. |
| CROSS\_NO\_FLY\_ZONE | Current Return to home route will pass a No-Fly zone. Pay attention to the aircraft's position to avoid Return to home failure. |

##### Class Members:

class

[FlySafeReturnToHomeInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeReturnToHomeInformation.md)

enum

[FlyZoneType](#iflyzonemanager_flyzonetype_inline)

###### enum FlyZoneType

|  |
| --- |
| ``` enum FlyZoneType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Type of restricted fly zone. Including airports, parks, schools, stadium, etc. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| AIRPORT | Airport. |
| COMMERCIAL\_AIRPORTS | Commercial airport. |
| PRIVATE\_COMMERCIAL\_AIRPORTS | Private commercial airport. |
| RECREATIONAL\_AIRPORTS | Recreational airport. |
| PRIVATE\_RECREATIONAL\_AIRPORTS | Private recreational airport. |
| HELIPORT | Heliport. |
| UNPAVED\_AIRPORT | Unpaved airport. |
| NATIONAL\_PARKS | National park. |
| NOAA | NOAA. |
| PARCEL | Post office. |
| POWER\_PLANT | Power plant. |
| SCHOOL | School. |
| STADIUM | stadium. |
| NUCLEAR\_POWER\_PLANT | Nuclear power plant. |
| PROHIBITED\_SPECIAL\_USE | Special Use. |
| RESTRICTED\_SPECIAL\_USE | Special Use. |
| SPECIAL | Special fly zone. |
| TEMPORARY\_FLIGHT\_RESTRICTIONS | Temporary flight restriction. |
| CLASS\_B\_AIR\_SPACE | Class B controlled airspace. See http://www.dji.com/flysafe/geo-system#notes for more information on the controlled airspace (Class B, C, D, E) in the United States. |
| CLASS\_C\_AIR\_SPACE | Class C controlled airspace. See http://www.dji.com/flysafe/geo-system#notes for more information on the controlled airspace (Class B, C, D, E) in the United States. |
| CLASS\_D\_AIR\_SPACE | Class D controlled airspace. See http://www.dji.com/flysafe/geo-system#notes for more information on the controlled airspace (Class B, C, D, E) in the United States. |
| CLASS\_E\_AIR\_SPACE | Class E controlled airspace. See http://www.dji.com/flysafe/geo-system#notes for more information on the controlled airspace (Class B, C, D, E) in the United States. |
| PRISON | Prison. |
| MILITARY | Military. |

##### Class Members:

enum

[FlyZoneCategory](#iflyzonemanager_flyzonecategory_inline)

###### enum FlyZoneCategory

|  |
| --- |
| ``` enum FlyZoneCategory ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Restricted Fly Zone category. Including restricted zone, authorization zone, warning zone and enhanced warning zone, etc. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| WARNING | Warning. |
| ENHANCED\_WARNING | Enhanced warning. |
| AUTHORIZATION | authorization fly zone. |
| RESTRICTED | Restricted fly zone. |

##### Class Members:

enum

[FlyZoneShape](#iflyzonemanager_flyzoneshape_inline)

###### enum FlyZoneShape

|  |
| --- |
| ``` enum FlyZoneShape ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Restricted fly zone shape type. Includes circles and polygons. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| CIRCLE | circle. |
| MULTI\_POLYGON | Polygon. Contains cylindrical and polygonal restricted fly zones. |

##### Class Members:

enum

[MultiPolygonFlyZoneShape](#iflyzonemanager_multipolygonflyzoneshape_inline)

###### enum MultiPolygonFlyZoneShape

|  |
| --- |
| ``` enum MultiPolygonFlyZoneShape ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

The shape type of polygonal restricted fly zone. Includes cylinders and polygons. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| CYLINDER | Cylinder. |
| POLYGON | Polygon. |

##### Class Members:

class

[MultiPolygonFlyZoneInformation](../../Components/IFlyZoneManager/IFlyZoneManager_MultiPolygonFlyZoneInformation.md)

class

[FlyZoneInformation](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneInformation.md)

class

[FlyZoneLicenseInfo](../../Components/IFlyZoneManager/IFlyZoneManager_FlyZoneLicenseInfo.md)

enum

[RidUnlockType](#iflyzonemanager_ridunlocktype_inline)

###### enum RidUnlockType

|  |
| --- |
| ``` enum RidUnlockType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

RID unlock type. `Supported since MSDK 5.8.0`

##### Enum Members:

|  |  |
| --- | --- |
| EUROPEAN | European. |
| CHINA | China. |

##### Class Members:

class

[FlySafeNotificationListener](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeNotificationListener.md)

class

[FlySafeDatabaseListener](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeDatabaseListener.md)

class

[FlySafeDatabaseInfo](../../Components/IFlyZoneManager/IFlyZoneManager_FlySafeDatabaseInfo.md)

enum

[FlySafeDatabaseUpgradeMode](#iflyzonemanager_flysafedatabaseupgrademode_inline)

###### enum FlySafeDatabaseUpgradeMode

|  |
| --- |
| ``` enum FlySafeDatabaseUpgradeMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

Fly safety database upgrade mode. Includes manual import and automatic upgrade. MSDK only supports manual import mode. If you need to use automatic upgrade mode, please use DJI official APP. `Supported since MSDK 5.8.0`

##### Enum Members:

|  |  |
| --- | --- |
| MANUAL\_IMPORT | Manual import mode. MSDK only supports manual upgrade mode. If you need to use automatic upgrade mode, please use the DJI official APP. |
| AUTOMATIC\_UPGRADE | Automatic upgrade mode. MSDK only supports manual upgrade mode. If you need to use automatic upgrade mode, please use the DJI official APP. The automatic update mode can only be used as a supplement for fly safety. If you want to fly your aircraft compliantly in the EU, please refer to manually importing EU GeoZone data into the aircraft. |

##### Class Members:

enum

[FlySafeDatabaseComponent](#iflyzonemanager_flysafedatabasecomponent_inline)

###### enum FlySafeDatabaseComponent

|  |
| --- |
| ``` enum FlySafeDatabaseComponent ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

Fly safety database component type. Includes MSDK and aircraft. `Supported since MSDK 5.8.0`

##### Enum Members:

|  |  |
| --- | --- |
| AIRCRAFT | Aircraft. |
| MSDK | MSDK. |

##### Class Members:

enum

[FlySafeDatabaseState](#iflyzonemanager_flysafedatabasestate_inline)

###### enum FlySafeDatabaseState

|  |
| --- |
| ``` enum FlySafeDatabaseState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe |

##### Description:

Fly safety database state. `Supported since MSDK 5.8.0`

##### Enum Members:

|  |  |
| --- | --- |
| NEED\_TO\_IMPORT | Need to import. Indicates that the current database of MSDK does not contain data on the current position of the aircraft. Please call `importFlySafeDynamicDatabaseToMSDK` to import the database near the aircraft location. |
| NEED\_TO\_SYNC | Need to synchronize. Indicates that the database in MSDK needs to be synchronized to the aircraft. Please call `pushFlySafeDynamicDatabaseToAircraft` to push the database in MSDK to the aircraft. |
| UP\_TO\_DATE | Up to date. Indicates that the MSDK database has been successfully synchronized to the aircraft. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found