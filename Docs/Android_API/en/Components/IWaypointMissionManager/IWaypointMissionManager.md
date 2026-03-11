**Navigation:** [IWaypointMissionManager](IWaypointMissionManager.md)

---

# class IWaypointMissionManager

|  |
| --- |
| ``` interface IWaypointMissionManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The class to manage the waypoint mission, it is used to upload, execute, pause and resume the mission and it also provides a listener to monitor the execution status of a waypoint mission. For waypoint editing functions, please refer to: `IWPMZManager`. `Supported since MSDK 5.0.0`

##### Class Members:

method

[addWaypointMissionExecuteStateListener](#iwaypointmissionmanager_addwaypointmissionexecutestatelistener_inline)

###### method addWaypointMissionExecuteStateListener

|  |
| --- |
| ``` void addWaypointMissionExecuteStateListener(WaypointMissionExecuteStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener of waypoint mission execution state. It can be used to listen the execution status of waypoint mission, such as uploading waypoint mission KMZ file, enter wayline flying, and wwaypoint mission finished, etc. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [WaypointMissionExecuteStateListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaypointMissionExecuteStateListener.md#iwaypointmissionmanager_waypointmissionexecutestatelistener) listener | *a listener of waypoint mission execution state.* |

method

[removeWaypointMissionExecuteStateListener](#iwaypointmissionmanager_removewaypointmissionexecutestatelistener_inline)

###### method removeWaypointMissionExecuteStateListener

|  |
| --- |
| ``` void removeWaypointMissionExecuteStateListener(WaypointMissionExecuteStateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove a listener of waypoint mission execution state. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [WaypointMissionExecuteStateListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaypointMissionExecuteStateListener.md#iwaypointmissionmanager_waypointmissionexecutestatelistener) listener | *a listener of waypoint mission execution state.* |

method

[clearAllWaypointMissionExecuteStateListener](#iwaypointmissionmanager_clearallwaypointmissionexecutestatelistener_inline)

###### method clearAllWaypointMissionExecuteStateListener

|  |
| --- |
| ``` void clearAllWaypointMissionExecuteStateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of the waypoint mission execution state. `Supported since MSDK 5.0.0`

method

[addWaylineExecutingInfoListener](#iwaypointmissionmanager_addwaylineexecutinginfolistener_inline)

###### method addWaylineExecutingInfoListener

|  |
| --- |
| ``` void addWaylineExecutingInfoListener(WaylineExecutingInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener of waylines execution information. The wayline information includes the currently executed wayline ID and the currently executed waypoint index. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [WaylineExecutingInfoListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaylineExecutingInfoListener.md#iwaypointmissionmanager_waylineexecutinginfolistener) listener | *a listener of waylines execution information.* |

method

[removeWaylineExecutingInfoListener](#iwaypointmissionmanager_removewaylineexecutinginfolistener_inline)

###### method removeWaylineExecutingInfoListener

|  |
| --- |
| ``` void removeWaylineExecutingInfoListener(WaylineExecutingInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove a listener of waylines execution information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [WaylineExecutingInfoListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaylineExecutingInfoListener.md#iwaypointmissionmanager_waylineexecutinginfolistener) listener | *a listener of waylines execution information.* |

method

[clearAllWaylineExecutingInfoListener](#iwaypointmissionmanager_clearallwaylineexecutinginfolistener_inline)

###### method clearAllWaylineExecutingInfoListener

|  |
| --- |
| ``` void clearAllWaylineExecutingInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of waylines execution information. `Supported since MSDK 5.0.0`

method

[addWaypointActionListener](#iwaypointmissionmanager_addwaypointactionlistener_inline)

###### method addWaypointActionListener

|  |
| --- |
| ``` void addWaypointActionListener(WaypointActionListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener of waypoint action execution. Including waypoint action start execution information and finish execution information. **Note: M300 RTK and M350 RTK only support 1 wayline ID.** **Note: For the V2 waypoint design scheme used by the M300 RTK and M350 RTK, the waypoint action callback information cannot be obtained when the waypoint executed by DJI Pilot is switched to the MSDK App. It is recommended to save the waypoint mission generated by DJI Pilot and then load the waypoint KMZ file in the MSDK App to execute the mission.** `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [WaypointActionListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaypointActionListener.md#iwaypointmissionmanager_waypointactionlistener) listener | *a listener of waypoint action execution.* |

method

[removeWaypointActionListener](#iwaypointmissionmanager_removewaypointactionlistener_inline)

###### method removeWaypointActionListener

|  |
| --- |
| ``` void removeWaypointActionListener(WaypointActionListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove a listener of waypoint action execution. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [WaypointActionListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaypointActionListener.md#iwaypointmissionmanager_waypointactionlistener) listener | *a listener of waypoint action execution.* |

method

[clearAllWaypointActionListener](#iwaypointmissionmanager_clearallwaypointactionlistener_inline)

###### method clearAllWaypointActionListener

|  |
| --- |
| ``` void clearAllWaypointActionListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of waypoint action execution. `Supported since MSDK 5.6.0`

method

[pushKMZFileToAircraft](#iwaypointmissionmanager_pushkmzfiletoaircraft_inline)

###### method pushKMZFileToAircraft

|  |
| --- |
| ``` void pushKMZFileToAircraft(String missionFilePath, CommonCallbacks.CompletionCallbackWithProgress<Double> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Upload a waypoint mission.- The waypoint mission is defined in a KMZ file, KMZ file is defined according to the DJI standard WPML. The WPML is defined according to XML and KML. For more details, please see: [DJI WPML](https://developer.dji.com/doc/cloud-api-tutorial/en/api-reference/dji-wpml/overview.md). - Users can generate a KMZ file according to the template provided by DJI Pilot 2. You can also define custom waylines.wpml according to DJI WPML standard and upload it after compressing it into a KMZ file. - The newly uploaded KMZ file will override the previously uploaded KMZ file if they have the same mission KMZ file name. - You can call this interface multiple times to upload multiple waypoint mission KMZ files. You can set mission KMZ file name to select the mission to start`startMission` or stop`stopMission`. - One KMZ file includes one waypoint mission. One waypoint mission can contain multiple waylines. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String missionFilePath | *The file path of the waypoint mission KMZ file.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithProgress<Double> callback | *return the execution result. Return the upload status of the KMZ file including uploading progress and errors.* |

method

[startMission](#iwaypointmissionmanager_startmission_inline)

###### method startMission

|  |
| --- |
| ``` void startMission(String missionFileName, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start the waypoint mission. **Note: Please do not call `KeyStartGoHome` during the take-off phase of the waypoint mission:. If you need to stop the waypoint mission, you can call `stopMission`.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String missionFileName | *The file name of the Mission to be executed.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *return the execution result.* |

method

[getAvailableWaylineIDs](#iwaypointmissionmanager_getavailablewaylineids_inline)

###### method getAvailableWaylineIDs

|  |
| --- |
| ``` List<Integer> getAvailableWaylineIDs(String missionFileName) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Returns a list of wayline IDs available in the specified mission file. After obtaining this wayline ID list, you can select the wayline ID to be executed and pass it as a parameter to `startMission` to execute the waypoint mission. **Note: M300 RTK and M350 RTK only support 1 wayline ID.** `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String missionFileName | *The specified mission filename.* |

##### Return:

|  |  |
| --- | --- |
| List<Integer> | *Get a list of wayline IDs available in the specified mission file.* |

method

[startMission](#iwaypointmissionmanager_startmissionwaylineids_inline)

###### method startMission

|  |
| --- |
| ``` void startMission(String missionFileName, List<Integer> waylineIDs, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start executing the waypoint mission of the specified Wayline IDs list. A list of available wayline IDs can be obtained through `getAvailableWaylineIDs`, after obtaining this wayline IDs list, you can select the wayline ID to be executed as a parameter and pass it to this interface, the aircraft will execute the waypoint mission in sequence in the order of the wayline IDs list. If the wayline IDs list parameter is empty or the length of the wayline IDs list is 0, the aircraft will execute all waylines in the waypoint mission. **Note: 1. M300 RTK and M350 RTK only support 1 wayline ID. 2. Please do not call `KeyStartGoHome` during the take-off phase of the waypoint mission:. If you need to stop the waypoint mission, you can call `stopMission`.** `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String missionFileName | *The file name of the Mission to be executed.* |
| List<Integer> waylineIDs | *The wayline IDs list to be executed.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result.* |

method

[queryBreakPointInfoFromAircraft](#iwaypointmissionmanager_querybreakpointinfofromaircraft_inline)

###### method queryBreakPointInfoFromAircraft

|  |
| --- |
| ``` void queryBreakPointInfoFromAircraft(String missionFileName ,CommonCallbacks.CompletionCallbackWithParam<BreakPointInfo> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Query the breakpoint information when the waypoint mission is paused. The breakpoint information includes the position of the aircraft where the waypoint mission is paused. After the breakpoint information is obtained, it can be passed as a parameter to `startMission` or `resumeMission` to execute the waypoint mission. The aircraft will fly to the incoming breakpoint position, and then execute the waypoint mission in sequence according to the waypoint order. **Note:**

1. The M300 RTK and M350 RTK do not support the breakpoint function.
2. The breakpoint information can only be queried after calling the pause waypoint mission interface `pauseMission`.   
     
     
   `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String missionFileName | *The file name of the Mission to be executed.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[BreakPointInfo](../../Components/IWaypointMissionManager/IWaypointMissionManager_BreakPointInfo.md#iwaypointmissionmanager_breakpointinfo)> callback | *Return the execution result.* |

method

[startMission](#iwaypointmissionmanager_startmissionbreakpointinfo_inline)

###### method startMission

|  |
| --- |
| ``` void startMission(String missionFileName , BreakPointInfo breakPointInfo , CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start executing the waypoint mission at the specified breakpoint. You can use `queryBreakPointInfoFromAircraft` to query the breakpoint information when the last waypoint mission was suspended. After the breakpoint information is obtained, it can be passed as a parameter to this interface, and the aircraft will fly to the incoming breakpoint position, and then execute waypoint mission in sequence according to the waypoint order. If you don’t want to start the waypoint mission from the position of the aircraft when it was paused last time, you can also construct the breakpoint information by yourself and set any waypoint position to start the waypoint mission. **Note:**

1. The M300 RTK and M350 RTK do not support this interface to execute the waypoint mission at the specified breakpoint. If you want to resume flying to the next waypoint after pausing, please set the breakpoint recovery type `setRecoverActionType` through `resumeMission`.
2. After suspending the waypoint mission, this interface can be called to re-execute the waypoint mission only after exiting the waypoint mission. Common application scenarios include: return and land after the flight mission is completed or the aircraft is powered on again.
3. If you want to start flying from the specified breakpoint without exiting the waypoint mission. You can call `resumeMission` to resume execution of the waypoint mission.   
     
     
   `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String missionFileName | *The file name of the Mission to be executed.* |
| [BreakPointInfo](../../Components/IWaypointMissionManager/IWaypointMissionManager_BreakPointInfo.md#iwaypointmissionmanager_breakpointinfo) breakPointInfo | *Breakpoint information of the Mission to be executed.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result.* |

method

[pauseMission](#iwaypointmissionmanager_pausemission_inline)

###### method pauseMission

|  |
| --- |
| ``` void pauseMission(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Pause the waypoint mission. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *return the execution result.* |

method

[resumeMission](#iwaypointmissionmanager_resumemission_inline)

###### method resumeMission

|  |
| --- |
| ``` void resumeMission(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Resume the waypoint mission from the pause point. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result.* |

method

[resumeMission](#iwaypointmissionmanager_resumemissionbreakpointinfo_inline)

###### method resumeMission

|  |
| --- |
| ``` void resumeMission(BreakPointInfo breakPointInfo, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Resume execution of the waypoint mission from the specified breakpoint. You can query the breakpoint information when the waypoint mission is paused through `queryBreakPointInfoFromAircraft`. After the breakpoint information is obtained, it can be passed as a parameter to this interface, and the aircraft will fly to the incoming breakpoint position, and then execute waypoint mission in sequence according to the waypoint order. If you don’t want to start the waypoint mission from the position of the aircraft when it was paused last time, you can also construct the breakpoint information by yourself and set any waypoint position to resume the waypoint mission. **Note: M300 RTK and M350 RTK do not support querying the breakpoint information when the waypoint mission is paused through `queryBreakPointInfoFromAircraft`. Only support to construct the breakpoint information by yourself and setting breakpoint recovery type `RecoverActionType` through `setRecoverActionType` to resume waypint mission.** `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [BreakPointInfo](../../Components/IWaypointMissionManager/IWaypointMissionManager_BreakPointInfo.md#iwaypointmissionmanager_breakpointinfo) breakPointInfo | *The breakpoint information needed to resume execution.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the execution result.* |

method

[stopMission](#iwaypointmissionmanager_stopmission_inline)

###### method stopMission

|  |
| --- |
| ``` void stopMission(String missionFileName, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Stop the waypoint mission. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String missionFileName | *The file name of the Mission to be stopped.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *return the execution result.* |

##### Related:

class

[WaypointMissionExecuteStateListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaypointMissionExecuteStateListener.md)

class

[WaylineExecutingInfoListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaylineExecutingInfoListener.md)

class

[WaypointActionListener](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaypointActionListener.md)

enum

[WaypointMissionExecuteState](#iwaypointmissionmanager_waypointmissionexecutestate_inline)

###### enum WaypointMissionExecuteState

|  |
| --- |
| ``` enum WaypointMissionExecuteState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Waypoint mission execution state. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Initialize state. |
| NOT\_SUPPORTED | This aircraft does not support waypoint mission 3.0. |
| READY | The ready state of a waypoint mission. You can upload a new KMZ file or execute an uploaded waypoint mission. |
| UPLOADING | The KMZ file is uploading. |
| PREPARING | Preparing to execute the waypoint mission. After calling`startMission`, the aircraft will start preparing for the waypoint mission. |
| ENTER\_WAYLINE | Enter wayline flying, aircraft goes to the first waypoint of the specified wayline. |
| EXECUTING | Waypoint mission is executing. |
| INTERRUPTED | The waypoint mission is interrupted, which means the user has called`pauseMission`to pause the waypoint mission or the flight controller is abnormal. |
| RECOVERING | Resume the waypoint mission. |
| FINISHED | The waypoint mission is finished. It means the waypoint mission execution is completed or the user has called`stopMission`to stop the waypoint mission. |

##### Class Members:

class

[WaylineExecutingInfo](../../Components/IWaypointMissionManager/IWaypointMissionManager_WaylineExecutingInfo.md)

class

[BreakPointInfo](../../Components/IWaypointMissionManager/IWaypointMissionManager_BreakPointInfo.md)

enum

[RecoverActionType](#iwaypointmissionmanager_recoveractiontype_inline)

###### enum RecoverActionType

|  |
| --- |
| ``` enum RecoverActionType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.waypoint3.model |

##### Description:

Interrupt recover action type **Note: This breakpoint recovery type only supports M300 RTK and M350 RTK.** `Supported since MSDK 5.9.0`

##### Enum Members:

|  |  |
| --- | --- |
| GoBackToRecordPoint | When the waypoint mission is resumed, the aircraft flies to the breakpoint where it was paused. |
| GoBackToNextPoint | When the waypoint mission is resumed, the aircraft flies to the next waypoint where it was paused. |
| GoBackToNextNextPoint | When the waypoint mission is resumed, the aircraft flies to the next next waypoint where it was paused. |

##### Class Members:

class

[IWPMZManager](../../Components/IWaypointMissionManager/IWPMZManager.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found