**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [LaserMeasureInformation](Value_Camera_Struct_LaserMeasureInformationMsg.md)

---

# class LaserMeasureInformation

|  |
| --- |
| ``` class LaserMeasureInformation implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Laser measure information.

##### Class Members:

#### Members

method

[getLocation3D](#value_camera_struct_lasermeasureinformationmsg_getlocation3d_inline)

###### method getLocation3D

|  |
| --- |
| ``` LocationCoordinate3D getLocation3D() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets the location of the target point, including longitude, latitude and altitude.

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) | *Returns the location of the target point.* |

method

[getDistance](#value_camera_struct_lasermeasureinformationmsg_getdistance_inline)

###### method getDistance

|  |
| --- |
| ``` Double getDistance() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets the distance of the target point from the laser sensor. The target point must be at least 3m away from The laser sensor.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the distance of the target point from the laser sensor.* |

method

[getTargetPoint](#value_camera_struct_lasermeasureinformationmsg_gettargetpoint_inline)

###### method getTargetPoint

|  |
| --- |
| ``` DoublePoint2D getTargetPoint() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets the position of the target point on the camera screen. Range of x and y: [0,100]. [0,0] represents the upper left corner of the camera screen, [100,100] represents the lower right corner of the camera screen.

##### Return:

|  |  |
| --- | --- |
| [DoublePoint2D](../../Components/IKeyManager/Value_Common_Struct_DoublePoint2D.md#value_common_struct_doublepoint2d) | *Returns the position of the target point on the camera screen.* |

method

[getLaserMeasureState](#value_camera_struct_lasermeasureinformationmsg_geterrorstate_inline)

###### method getLaserMeasureState

|  |
| --- |
| ``` LaserMeasureState getLaserMeasureState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Gets the laser measure status.

##### Return:

|  |  |
| --- | --- |
| [LaserMeasureState](../../Components/IKeyManager/DJIValue.md#value_camera_enum_lasermeasureerrorstatus) | *Returns the laser measure status.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found