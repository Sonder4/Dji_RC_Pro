**Navigation:** [ISimulatorManager](ISimulatorManager.md)

---

# class ISimulatorManager

|  |
| --- |
| ``` interface ISimulatorManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class that is used for managing simulator. It can be used to enable/disable, set up parameters/listeners for simulator. `Supported since MSDK 5.0.0`

##### Class Members:

method

[isSimulatorEnabled](#isimulatormanager_issimulatorenabled_inline)

###### method isSimulatorEnabled

|  |
| --- |
| ``` boolean isSimulatorEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To get whether the simulator is turned on. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` means that the simulator is turned on.* |

method

[enableSimulator](#isimulatormanager_enablesimulator_inline)

###### method enableSimulator

|  |
| --- |
| ``` void enableSimulator(@NonNull final InitializationSettings settings, @Nullable final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Turn on the simulator. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [InitializationSettings](../../Components/ISimulatorManager/ISimulatorManager_InitializationSettings.md#isimulatormanager_initializationsettings) settings | *Initialized parameter of simulator.* |
| @Nullable final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[disableSimulator](#isimulatormanager_disablesimulator_inline)

###### method disableSimulator

|  |
| --- |
| ``` void disableSimulator(@Nullable final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Turn off the simulator. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[addSimulatorStateListener](#isimulatormanager_addsimulatorstatelistener_inline)

###### method addSimulatorStateListener

|  |
| --- |
| ``` void addSimulatorStateListener(@NonNull SimulatorStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

To set the listener of the simulator status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [SimulatorStatusListener](../../Components/ISimulatorManager/ISimulatorManager_SimulatorStatusListener.md#isimulatormanager_simulatorstatuslistener) listener | *Listener of the simulator status.* |

method

[removeSimulatorStateListener](#isimulatormanager_removesimulatorstatelistener_inline)

###### method removeSimulatorStateListener

|  |
| --- |
| ``` void removeSimulatorStateListener(@NonNull SimulatorStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of the simulator status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [SimulatorStatusListener](../../Components/ISimulatorManager/ISimulatorManager_SimulatorStatusListener.md#isimulatormanager_simulatorstatuslistener) listener | *Listener of the simulator status.* |

method

[clearAllSimulatorStateListener](#isimulatormanager_clearallsimulatorstatelistener_inline)

###### method clearAllSimulatorStateListener

|  |
| --- |
| ``` void clearAllSimulatorStateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clear all listeners of the simulator status. `Supported since MSDK 5.0.0`

##### Related:

class

[InitializationSettings](../../Components/ISimulatorManager/ISimulatorManager_InitializationSettings.md)

class

[SimulatorStatusListener](../../Components/ISimulatorManager/ISimulatorManager_SimulatorStatusListener.md)

class

[SimulatorState](../../Components/ISimulatorManager/ISimulatorManager_SimulatorState.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found