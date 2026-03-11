**Navigation:** [IFlyZoneManager](IFlyZoneManager.md) > [FlyZoneInformation](IFlyZoneManager_FlyZoneInformation.md)

---

# class FlyZoneInformation

|  |
| --- |
| ``` final class FlyZoneInformation ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Restricted fl zone information class. Including restricted-fly zone ID, restricted fly zone name, type of restricted fly zone, restricted fly zone shape, restricted fly zone strategy, etc. `Supported since MSDK 5.3.0`

##### Class Members:

#### Basic Information

method

[getFlyZoneID](#iflyzonemanager_flyzoneinformation_getflyzoneid_inline)

###### method getFlyZoneID

|  |
| --- |
| ``` int getFlyZoneID() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the fly zone's ID. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the fly zone's ID.* |

method

[getName](#iflyzonemanager_flyzoneinformation_getname_inline)

###### method getName

|  |
| --- |
| ``` String getName() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the name of the fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| String | *Returns the name of the fly zone.* |

method

[getFlyZoneType](#iflyzonemanager_flyzoneinformation_getflyzonetype_inline)

###### method getFlyZoneType

|  |
| --- |
| ``` FlyZoneType getFlyZoneType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the type fly zone. Includes airports, parks, schools, stadium, etc. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [FlyZoneType](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_flyzonetype) | *Returns the type fly zone.* |

method

[getCategory](#iflyzonemanager_flyzoneinformation_getcategory_inline)

###### method getCategory

|  |
| --- |
| ``` FlyZoneCategory getCategory() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the category of the fly zone. Includes restricted zone, authorization zone, warning zone and enhanced warning zone, etc. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [FlyZoneCategory](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_flyzonecategory) | *Returns the category of the fly zone.* |

method

[getShape](#iflyzonemanager_flyzoneinformation_getshape_inline)

###### method getShape

|  |
| --- |
| ``` FlyZoneShape getShape() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the shape of the fly zone. Includes circles and polygons. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [FlyZoneShape](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_flyzoneshape) | *Returns the shape of the fly zone.* |

#### GeoZone Data

method

[getLowerLimit](#iflyzonemanager_flyzoneinformation_getlowerlimit_inline)

###### method getLowerLimit

|  |
| --- |
| ``` double getLowerLimit() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the lower limit of GeoZone data, currently only supports EU GeoZone data. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| double | *Returns the lower limit of GeoZone data.* |

method

[getUpperLimit](#iflyzonemanager_flyzoneinformation_getupperlimit_inline)

###### method getUpperLimit

|  |
| --- |
| ``` double getUpperLimit() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the upper limit of GeoZone data, currently only supports EU GeoZone data. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| double | *Returns the upper limit of GeoZone data.* |

#### Circular Restricted Fly Zone Information

method

[getCircleCenter](#iflyzonemanager_flyzoneinformation_getcirclecenter_inline)

###### method getCircleCenter

|  |
| --- |
| ``` LocationCoordinate2D getCircleCenter() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the coordinates of the center of the circular restricted-fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d) | *Returns the coordinates of the center of the circular restricted-fly zone.* |

method

[getCircleRadius](#iflyzonemanager_flyzoneinformation_getcircleradius_inline)

###### method getCircleRadius

|  |
| --- |
| ``` double getCircleRadius() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the radius of the circular restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| double | *Returns the radius of the circular restricted fly zone.* |

#### Polygon Restricted Fly Zone Information

method

[getMultiPolygonFlyZoneInformation](#iflyzonemanager_flyzoneinformation_getmultipolygonflyzoneinformation_inline)

###### method getMultiPolygonFlyZoneInformation

|  |
| --- |
| ``` List<MultiPolygonFlyZoneInformation> getMultiPolygonFlyZoneInformation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the polygon restricted fly zone information. Contains cylindrical and polygonal no-fly zone information. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| List<[MultiPolygonFlyZoneInformation](../../Components/IFlyZoneManager/IFlyZoneManager_MultiPolygonFlyZoneInformation.md#iflyzonemanager_multipolygonflyzoneinformation)> | *Returns information about the restricted fly zone for the polygon.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found