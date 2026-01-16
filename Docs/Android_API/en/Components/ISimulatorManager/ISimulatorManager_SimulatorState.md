**Navigation:** [ISimulatorManager](ISimulatorManager.md) > [SimulatorState](ISimulatorManager_SimulatorState.md)

---

# class SimulatorState

|  |
| --- |
| ``` class SimulatorState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

Simulator status. `Supported since MSDK 5.0.0`

##### Class Members:

#### Members

method

[areMotorsOn](#isimulatormanager_simulatorstate_aremotorson_inline)

###### method areMotorsOn

|  |
| --- |
| ``` boolean areMotorsOn() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

Return whether the aircraft motor in the simulator has started to spin.

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` means the aircraft motor in the simulator has started to spin.* |

method

[isFlying](#isimulatormanager_simulatorstate_isflying_inline)

###### method isFlying

|  |
| --- |
| ``` boolean isFlying() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

Return whether the aircraft in the simulator is in flight.

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` means the aircraft in the simulator is in flight.* |

method

[getPitch](#isimulatormanager_simulatorstate_getpitch_inline)

###### method getPitch

|  |
| --- |
| ``` float getPitch() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the pitch angle of the aircraft in the simulator. Unit: degree.

##### Return:

|  |  |
| --- | --- |
| float | *Return the pitch angle of the aircraft in the simulator.* |

method

[getRoll](#isimulatormanager_simulatorstate_getroll_inline)

###### method getRoll

|  |
| --- |
| ``` float getRoll() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the roll angle of the aircraft in the simulator. Unit: degree.

##### Return:

|  |  |
| --- | --- |
| float | *Return the roll angle of the aircraft in the simulator.* |

method

[getYaw](#isimulatormanager_simulatorstate_getyaw_inline)

###### method getYaw

|  |
| --- |
| ``` float getYaw() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the yaw angle of the aircraft in the simulator. Unit: degree.

##### Return:

|  |  |
| --- | --- |
| float | *Return the yaw angle of the aircraft in the simulator.* |

method

[getPositionX](#isimulatormanager_simulatorstate_getpositionx_inline)

###### method getPositionX

|  |
| --- |
| ``` float getPositionX() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the east-west distance from the aircraft's current position in the simulator to the aircraft's initial position. Unit: meter. East is positive. The North East coordinate system is used.

##### Return:

|  |  |
| --- | --- |
| float | *Return the east-west distance from the aircraft's current position in the simulator to the aircraft's initial position.* |

method

[getPositionY](#isimulatormanager_simulatorstate_getpositiony_inline)

###### method getPositionY

|  |
| --- |
| ``` float getPositionY() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the north-south distance from the aircraft's current position in the simulator to the aircraft's initial position. Unit: meter. North is positive. The North East coordinate system is used.

##### Return:

|  |  |
| --- | --- |
| float | *Return the north-south distance from the aircraft's current position in the simulator to the aircraft's initial position.* |

method

[getPositionZ](#isimulatormanager_simulatorstate_getpositionz_inline)

###### method getPositionZ

|  |
| --- |
| ``` float getPositionZ() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the vertical distance from the aircraft's current position in the simulator to the aircraft's initial position. Unit: meter. If the altitude of the aircraft is higher than the altitude of the initial position, the value should be negative.

##### Return:

|  |  |
| --- | --- |
| float | *Return the vertical distance from the aircraft's current position in the simulator to the aircraft's initial position.* |

method

[getLocation](#isimulatormanager_simulatorstate_getlocation_inline)

###### method getLocation

|  |
| --- |
| ``` LocationCoordinate2D getLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.simulator |

##### Description:

To get the coordinates of the current position of the aircraft in the simulator.

##### Return:

|  |  |
| --- | --- |
| [LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md#value_common_struct_locationcoordinate2d) | *Return the coordinates of the current position of the aircraft in the simulator.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found