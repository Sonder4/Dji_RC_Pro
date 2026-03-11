**Navigation:** [IIntelligentFlightManager](IIntelligentFlightManager.md) > [IFlyToMissionManager](IIntelligentFlightManager_IFlyToMissionManager.md) > [FlyToTarget](IIntelligentFlightManager_FlyToMissionManager_FlyToTarget.md)

---

# class FlyToTarget

|  |
| --- |
| ``` @Keep  class FlyToTarget ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Target of Fly-To task. `Supported since MSDK 5.14.0`

##### Class Members:

#### Members

method

[getTargetLocation](#iintelligentflightmanager_flytomissionmanager_flytotarget_gettargetlocation_inline)

###### method getTargetLocation

|  |
| --- |
| ``` LocationCoordinate3D getTargetLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Gets the location of the target point.

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) | *Returns the location of the target point.* |

method

[setTargetLocation](#iintelligentflightmanager_flytomissionmanager_flytotarget_settargetlocation_inline)

###### method setTargetLocation

|  |
| --- |
| ``` void setTargetLocation(LocationCoordinate3D targetLocation) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Sets the location of the target point.

##### Input Parameters:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) targetLocation | *The location of the target point.* |

method

[getSecurityTakeoffHeight](#iintelligentflightmanager_flytomissionmanager_flytotarget_getsecuritytakeoffheight_inline)

###### method getSecurityTakeoffHeight

|  |
| --- |
| ``` int getSecurityTakeoffHeight() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Gets the aircraft's takeoff altitude.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the aircraft's takeoff altitude.* |

method

[setSecurityTakeoffHeight](#iintelligentflightmanager_flytomissionmanager_flytotarget_setsecuritytakeoffheight_inline)

###### method setSecurityTakeoffHeight

|  |
| --- |
| ``` void setSecurityTakeoffHeight(Integer securityTakeoffHeight) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Sets the take-off altitude of the aircraft.

##### Input Parameters:

|  |  |
| --- | --- |
| Integer securityTakeoffHeight | *The take-off altitude of the aircraft.* |

method

[getMaxSpeed](#iintelligentflightmanager_flytomissionmanager_flytotarget_getmaxspeed_inline)

###### method getMaxSpeed

|  |
| --- |
| ``` int getMaxSpeed() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Gets the flight speed towards the target point.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the flight speed towards the target point.* |

method

[setMaxSpeed](#iintelligentflightmanager_flytomissionmanager_flytotarget_setmaxspeed_inline)

###### method setMaxSpeed

|  |
| --- |
| ``` void setMaxSpeed(Integer maxSpeed) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.intelligent.flyto |

##### Description:

Sets the flight speed towards the target point.

##### Input Parameters:

|  |  |
| --- | --- |
| Integer maxSpeed | *The flight speed towards the target point.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found