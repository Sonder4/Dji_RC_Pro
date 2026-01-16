**Navigation:** [IFlyZoneManager](IFlyZoneManager.md) > [MultiPolygonFlyZoneInformation](IFlyZoneManager_MultiPolygonFlyZoneInformation.md)

---

# class MultiPolygonFlyZoneInformation

|  |
| --- |
| ``` class MultiPolygonFlyZoneInformation ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Polygon restricted fly zone information. Includes cylindrical and polygonal restricted fly zone information. `Supported since MSDK 5.3.0`

##### Class Members:

#### Basic Information

method

[getFlyZoneID](#iflyzonemanager_multipolygonflyzoneinformation_getflyzoneid_inline)

###### method getFlyZoneID

|  |
| --- |
| ``` int getFlyZoneID() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the ID of the restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the ID of the restricted fly zone.* |

method

[getShape](#iflyzonemanager_multipolygonflyzoneinformation_getshape_inline)

###### method getShape

|  |
| --- |
| ``` MultiPolygonFlyZoneShape getShape() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Get the shape type of the restricted fly zone. Contains cylinders and polygons. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [MultiPolygonFlyZoneShape](../../Components/IFlyZoneManager/IFlyZoneManager.md#iflyzonemanager_multipolygonflyzoneshape) | *Returns shape type of the restricted fly zone.* |

method

[getLimitedHeight](#iflyzonemanager_multipolygonflyzoneinformation_getlimitedheight_inline)

###### method getLimitedHeight

|  |
| --- |
| ``` int getLimitedHeight() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the height limit of restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| int | *Returns the height limit of restricted fly zone.* |

#### Cylindrical Restricted Fly Zone Information

method

[getCylinderCenter](#iflyzonemanager_multipolygonflyzoneinformation_getcylindercenter_inline)

###### method getCylinderCenter

|  |
| --- |
| ``` LocationCoordinate2D getCylinderCenter() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the coordinates of the center of the cylindrical restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d) | *Returns the coordinates of the center of the cylindrical restricted fly zone.* |

method

[getCylinderRadius](#iflyzonemanager_multipolygonflyzoneinformation_getcylinderradius_inline)

###### method getCylinderRadius

|  |
| --- |
| ``` double getCylinderRadius() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the radius of the cylindrical restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| double | *Returns the radius of the cylindrical restricted fly zone.* |

#### Polygon Restricted Fly Zone Information

method

[getPolygonPoints](#iflyzonemanager_multipolygonflyzoneinformation_getpolygonpoints_inline)

###### method getPolygonPoints

|  |
| --- |
| ``` List<LocationCoordinate2D> getPolygonPoints() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.flysafe.info |

##### Description:

Gets the range of the polygon's restricted fly zone. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| List<[LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d)> | *Returns the range of the polygon's restricted fly zone.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found