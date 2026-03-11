**Navigation:** [IWaypointMissionManager](IWaypointMissionManager.md) > [WaypointActionListener](IWaypointMissionManager_WaypointActionListener.md)

---

# class WaypointActionListener

|  |
| --- |
| ``` interface WaypointActionListener ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

Listener of waypoint action execution. `Supported since MSDK 5.6.0`

##### Class Members:

method

[onExecutionStart](#iwaypointmissionmanager_waypointactionlistener_onexecutionstart_inline)

###### method onExecutionStart

|  |
| --- |
| ``` @Deprecated     void onExecutionStart(int actionId) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

This method will be called back when the waypint action starts to execute. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int actionId | *The waypoint action ID to start executing.* |

method

[onExecutionFinish](#iwaypointmissionmanager_waypointactionlistener_onexecutionfinish_inline)

###### method onExecutionFinish

|  |
| --- |
| ``` @Deprecated     void onExecutionFinish(int actionId, @Nullable IDJIError error) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

This method will be called back when the waypoint action finishes executing. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int actionId | *The waypoint action ID to end execution.* |
| @Nullable [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *The error code for the execution interruption of the waypoint action.* |

method

[onExecutionStart](#iwaypointmissionmanager_waypointactionlistener_onexecutionstartwithgroup_inline)

###### method onExecutionStart

|  |
| --- |
| ``` void onExecutionStart(int actionGroup , int actionId) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

This method will be called back when the waypint action starts to execute. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int actionGroup | *The waypoint action group to start executing.* |
| int actionId | *The waypoint action ID to start executing.* |

method

[onExecutionFinish](#iwaypointmissionmanager_waypointactionlistener_onexecutionfinishwithgroup_inline)

###### method onExecutionFinish

|  |
| --- |
| ``` void onExecutionFinish(int actionGroup, int actionId, @Nullable IDJIError error) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3 |

##### Description:

This method will be called back when the waypoint action finishes executing. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int actionGroup | *The waypoint action group to start executing.* |
| int actionId | *The waypoint action ID to end execution.* |
| @Nullable [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *The error code for the execution interruption of the waypoint action.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found