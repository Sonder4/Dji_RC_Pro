**Navigation:** [ISimulatorManager](ISimulatorManager.md) > [InitializationSettings](ISimulatorManager_InitializationSettings.md)

---

# class InitializationSettings

|  |
| --- |
| ``` final class InitializationSettings ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

Simulator initialization settings class， which is used to initialize simulator parameters. `Supported since MSDK 5.0.0`

##### Class Members:

method

[createInstance](#isimulatormanager_initializationsettings_createinstance_inline)

###### method createInstance

|  |
| --- |
| ``` static InitializationSettings createInstance(LocationCoordinate2D location,                                                        @IntRange(from=0,to=20) int satelliteCount) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

The initialized method of simulator parameter. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d) location | *The initial coordinates of the aircraft in the simulator.* |
| @IntRange(from=0,to=20) int satelliteCount | *The initial number of satellites of the aircraft in the simulator. The range of satellite numbers is [0, 20].* |

##### Return:

|  |  |
| --- | --- |
| static [InitializationSettings](../../Components/ISimulatorManager/ISimulatorManager_InitializationSettings.md#isimulatormanager_initializationsettings) | *Return the instance of simulator.* |

method

[getLocation](#isimulatormanager_initializationsettings_getlocation_inline)

###### method getLocation

|  |
| --- |
| ``` LocationCoordinate2D getLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the initial coordinates of the aircraft in the simulator. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d) | *Return the initial coordinates of the aircraft in the simulator.* |

method

[getSatelliteCount](#isimulatormanager_initializationsettings_getsatellitecount_inline)

###### method getSatelliteCount

|  |
| --- |
| ``` int getSatelliteCount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the initial number of satellites of the aircraft in the simulator. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| int | *Return the initial number of satellites of the aircraft in the simulator.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found