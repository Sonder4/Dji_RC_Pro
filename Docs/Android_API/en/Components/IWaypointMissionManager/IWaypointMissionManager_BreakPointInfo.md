**Navigation:** [IWaypointMissionManager](IWaypointMissionManager.md) > [BreakPointInfo](IWaypointMissionManager_BreakPointInfo.md)

---

# class BreakPointInfo

|  |
| --- |
| ``` class BreakPointInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Waypoint mission breakpoint information. `Supported since MSDK 5.6.0`

##### Class Members:

method

[getWaylineID](#iwaypointmissionmanager_breakpointinfo_getwaylineid_inline)

###### method getWaylineID

|  |
| --- |
| ``` Integer getWaylineID() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Gets the wayline ID of the breakpoint.

##### Return:

|  |  |
| --- | --- |
| Integer | *Returns the wayline ID of the breakpoint.* |

method

[setWaylineID](#iwaypointmissionmanager_breakpointinfo_setwaylineid_inline)

###### method setWaylineID

|  |
| --- |
| ``` void setWaylineID(Integer waylineID) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Sets the wayline ID of the breakpoint.

##### Input Parameters:

|  |  |
| --- | --- |
| Integer waylineID | *The wayline ID of the breakpoint that needs to be set.* |

method

[getWaypointID](#iwaypointmissionmanager_breakpointinfo_getwaypointid_inline)

###### method getWaypointID

|  |
| --- |
| ``` Integer getWaypointID() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Gets the waypoint ID of the breakpoint.

##### Return:

|  |  |
| --- | --- |
| Integer | *Returns the waypoint ID of the breakpoint.* |

method

[setWaypointID](#iwaypointmissionmanager_breakpointinfo_setwaypointid_inline)

###### method setWaypointID

|  |
| --- |
| ``` void setWaypointID(Integer waypointID) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Sets the waypoint ID of the breakpoint.

##### Input Parameters:

|  |  |
| --- | --- |
| Integer waypointID | *The waypoint ID of the breakpoint that needs to be set.* |

method

[getSegmentProgress](#iwaypointmissionmanager_breakpointinfo_getsegmentprogress_inline)

###### method getSegmentProgress

|  |
| --- |
| ``` Double getSegmentProgress() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Gets the segment percentage position of the breakpoint.

##### Return:

|  |  |
| --- | --- |
| Double | *Returns the segment percentage position of the breakpoint.* |

method

[setSegmentProgress](#iwaypointmissionmanager_breakpointinfo_setsegmentprogress_inline)

###### method setSegmentProgress

|  |
| --- |
| ``` void setSegmentProgress(Double progress) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Sets the segment percentage position of the breakpoint. Range: (0, 1). For example: If you want to start flying at 25% of the segment from waypoint 1 to waypoint 2, you can set the waypointID to 1 and set this parameter to 0.25.

##### Input Parameters:

|  |  |
| --- | --- |
| Double progress | *The segment percentage position of the breakpoint that needs to be set.* |

method

[getLocation](#iwaypointmissionmanager_breakpointinfo_getlocation_inline)

###### method getLocation

|  |
| --- |
| ``` LocationCoordinate3D getLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Gets the geographical location of breakpoint. **Note: This feature only supports M30 series and M3E, M3T, M3M aircraft.**

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md#value_common_struct_locationcoordinate3d) | *Returns the geographical location of breakpoint.* |

method

[getRecoverActionType](#iwaypointmissionmanager_breakpointinfo_getrecoveractiontype_inline)

###### method getRecoverActionType

|  |
| --- |
| ``` RecoverActionType getRecoverActionType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Gets the breakpoint recover action type. **Note: This breakpoint recovery type only supports M300 RTK and M350 RTK.**

##### Return:

|  |  |
| --- | --- |
| [RecoverActionType](../../Components/IWaypointMissionManager/IWaypointMissionManager.md#iwaypointmissionmanager_recoveractiontype) | *Returns the breakpoint recover action type.* |

method

[setRecoverActionType](#iwaypointmissionmanager_breakpointinfo_setrecoveractiontype_inline)

###### method setRecoverActionType

|  |
| --- |
| ``` void setRecoverActionType(RecoverActionType recoverActionType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Set breakpoint recovery type. Supports continuing flight from the paused breakpoint, and supports flying to the next waypoint and the next next waypoint of the breakpoint. **Note: This breakpoint recovery type only supports M300 RTK and M350 RTK.**

##### Input Parameters:

|  |  |
| --- | --- |
| [RecoverActionType](../../Components/IWaypointMissionManager/IWaypointMissionManager.md#iwaypointmissionmanager_recoveractiontype) recoverActionType | *The breakpoint recovery type that needs to be set.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found