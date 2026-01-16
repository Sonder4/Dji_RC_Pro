**Navigation:** [IFlyZoneManager](IFlyZoneManager.md) > [FlyZoneLicenseInfo](IFlyZoneManager_FlyZoneLicenseInfo.md)

---

# class FlyZoneLicenseInfo

|  |
| --- |
| ``` class FlyZoneLicenseInfo implements KeepProguard ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Restricted fly zone licenses class. Including the basic information of the unlocking licenses, such as license type, validity period, flight controller SN bound to the license, enabling status, etc. `Supported since MSDK 5.3.0`

##### Class Members:

#### Basic Information

method

[getLicenseType](#iflyzonemanager_flyzonelicenseinfo_getlicensetype_inline)

###### method getLicenseType

|  |
| --- |
| ``` FlySafeLicenseType getLicenseType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the type of the restricted fly zone licnese. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| FlySafeLicenseType | *Returns the type of the restricted fly zone licnese.* |

method

[getSN](#iflyzonemanager_flyzonelicenseinfo_getsn_inline)

###### method getSN

|  |
| --- |
| ``` String getSN() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the flight controller SN of the aircraft that is bound to the licnese. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| String | *Returns the flight controller SN of the aircraft that is bound to the licnese.* |

method

[getStartTimeStamp](#iflyzonemanager_flyzonelicenseinfo_getstarttimestamp_inline)

###### method getStartTimeStamp

|  |
| --- |
| ``` long getStartTimeStamp() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the start timestamp of the validity period of the restricted fly zone license. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| long | *Returns the start timestamp of the validity period of the restricted fly zone license.* |

method

[getEndTimeStamp](#iflyzonemanager_flyzonelicenseinfo_getendtimestamp_inline)

###### method getEndTimeStamp

|  |
| --- |
| ``` long getEndTimeStamp() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the end timestamp of the validity period of the restricted fly zone license. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| long | *Returns the send timestamp of the validity period of the restricted fly zone license.* |

method

[getLicenseId](#iflyzonemanager_flyzonelicenseinfo_getlicenseid_inline)

###### method getLicenseId

|  |
| --- |
| ``` int getLicenseId() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the ID of the restricted fly zone release license. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the ID of the restricted fly zone release license.* |

method

[isEnabled](#iflyzonemanager_flyzonelicenseinfo_isenabled_inline)

###### method isEnabled

|  |
| --- |
| ``` boolean isEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets whether the restricted fly zone license is valid. You can call `setFlyZoneLicensesEnabled` to enable or disable the licnese. After enabling the license, the aircraft will be able to take off normally in the restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *Returns whether the restricted fly zone license is valid.* |

method

[isValid](#iflyzonemanager_flyzonelicenseinfo_isvalid_inline)

###### method isValid

|  |
| --- |
| ``` boolean isValid() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets whether the restricted fly zone licnese is valid. If the licnese has expired, please re-apply the license on the DJI official website. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *Returns whether the restricted fly zone licnese is valid.* |

method

[getDescription](#iflyzonemanager_flyzonelicenseinfo_getdescription_inline)

###### method getDescription

|  |
| --- |
| ``` String getDescription() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the description of the restricted fly zone licnese. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| String | *Returns the description of the restricted fly zone licnese.* |

#### Restricted Fly Zone Unlock information

method

[getFlyZoneIDs](#iflyzonemanager_flyzonelicenseinfo_getflyzoneids_inline)

###### method getFlyZoneIDs

|  |
| --- |
| ``` List<Integer> getFlyZoneIDs() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the ID of unlock restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| List<Integer> | *Returns the ID of unlock restricted fly zone.* |

#### Cylinder Zone Unlock Information

method

[getCylinderLatitude](#iflyzonemanager_flyzonelicenseinfo_getcylinderlatitude_inline)

###### method getCylinderLatitude

|  |
| --- |
| ``` float getCylinderLatitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the latitude of the unlock cylinder zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| float | *Returns the latitude of the unlock cylinder zone.* |

method

[getCylinderLongitude](#iflyzonemanager_flyzonelicenseinfo_getcylinderlongitude_inline)

###### method getCylinderLongitude

|  |
| --- |
| ``` float getCylinderLongitude() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the longitude of the unlock cylinder zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| float | *Returns the longitude of the unlock cylinder zone.* |

method

[getCylinderRadius](#iflyzonemanager_flyzonelicenseinfo_getcylinderradius_inline)

###### method getCylinderRadius

|  |
| --- |
| ``` float getCylinderRadius() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the radius of the unlock cylinder zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| float | *Returns the radius of the unlock cylinder zone.* |

method

[getCylinderHeight](#iflyzonemanager_flyzonelicenseinfo_getcylinderheight_inline)

###### method getCylinderHeight

|  |
| --- |
| ``` int getCylinderHeight() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the height limit of the unlock cylinder zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the height limit of the cylinder zone.* |

#### Polygon Zone Unlock Information

method

[getPolygonPoints](#iflyzonemanager_flyzonelicenseinfo_getpolygonpoints_inline)

###### method getPolygonPoints

|  |
| --- |
| ``` List<LocationCoordinate2D> getPolygonPoints() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the range of the unlock polygon zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| List<[LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d)> | *Returns the range of the polygon zone.* |

#### Height Limit Unlock Information

method

[getLimitedHeight](#iflyzonemanager_flyzonelicenseinfo_getlimitedheight_inline)

###### method getLimitedHeight

|  |
| --- |
| ``` int getLimitedHeight() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the unlock height limit. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the unlock height limit.* |

#### Country Unlock Information

method

[getCountryId](#iflyzonemanager_flyzonelicenseinfo_getcountryid_inline)

###### method getCountryId

|  |
| --- |
| ``` int getCountryId() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the country unlock zone ID. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the country unlock zone ID.* |

#### RID Unlock Information

method

[getRidUnlockType](#iflyzonemanager_flyzonelicenseinfo_getridunlocktype_inline)

###### method getRidUnlockType

|  |
| --- |
| ``` RidUnlockType getRidUnlockType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the RID unlock type. `Supported since MSDK 5.8.0`

##### Return:

|  |  |
| --- | --- |
| [RidUnlockType](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_ridunlocktype) | *Returns the RID unlock type.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found