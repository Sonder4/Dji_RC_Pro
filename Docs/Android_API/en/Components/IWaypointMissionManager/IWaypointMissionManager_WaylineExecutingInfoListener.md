**Navigation:** [IWaypointMissionManager](IWaypointMissionManager.md) > [WaylineExecutingInfoListener](IWaypointMissionManager_WaylineExecutingInfoListener.md)

---

# class WaylineExecutingInfoListener

|  |
| --- |
| ``` interface WaylineExecutingInfoListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

Listener of waylines execution information. `Supported since MSDK 5.0.0`

##### Class Members:

method

[onWaylineExecutingInfoUpdate](#iwaypointmissionmanager_waylineexecutinginfolistener_onwaylineexecutinginfoupdate_inline)

###### method onWaylineExecutingInfoUpdate

|  |
| --- |
| ``` void onWaylineExecutingInfoUpdate(WaylineExecutingInfo excutingWaylineInfo) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

The listener will return when the wayline information is changed. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [WaylineExecutingInfo](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaylineExecutingInfo.md#iwaypointmissionmanager_waylineexecutinginfo) excutingWaylineInfo | *The current executing wayline information.* |

method

[onWaylineExecutingInterruptReasonUpdate](#iwaypointmissionmanager_waylineexecutinginfolistener_onwaylineexecutinginterruptreasonupdate_inline)

###### method onWaylineExecutingInterruptReasonUpdate

|  |
| --- |
| ``` default void onWaylineExecutingInterruptReasonUpdate(IDJIError error) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

The listener will return when the wayline execution is interrupted is changed. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *Reason of wayline execution interruption.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found