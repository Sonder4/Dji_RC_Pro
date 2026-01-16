**Navigation:** [IKeyManager](IKeyManager.md) > [DJIKey<T>](DJIKey.md) > [FlightControllerKey](Key_FlightController_FlightControllerKey.md)

---

# class FlightControllerKey

|  |
| --- |
| ``` @Keep  class FlightControllerKey extends DJIFlightControllerKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |
| *Inherits From:* | `DJIFlightControllerKey` |

##### Description:

`FlightControllerKey`provides a set of methods to set and get the flight controller parameters and behavior. The flight controller is a microcomputer. It is receiving the control data from aircraft and sensor data from transducers and combines them to adjust each blade power to fly. This class can get the aircraft attitude, aircraft state, flight mode, flight control settings, compass and IMU, etc. `This class is supported since MSDK 5.0`

##### Class Members:

#### Basic Information

Connection Status

[KeyConnection](#key_flightcontroller_connection_inline)

###### final KeyConnection

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyConnection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Connection", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means flight controller is connected. `Supported since MSDK 5.0.0`

Flight Status

[KeyIsFlying](#key_flightcontroller_isflying_inline)

###### final KeyIsFlying

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsFlying = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsFlying", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`true` means aircraft is in flight. `Supported since MSDK 5.0.0`

Flight Time

[KeyFlightTimeInSeconds](#key_flightcontroller_flighttimeinseconds_inline)

###### final KeyFlightTimeInSeconds

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyFlightTimeInSeconds = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FlightTimeInSeconds", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Accumulated flight time since the aircraft motor started, unit: 0.1s. Reset after power on again. `Supported since MSDK 5.0.0`

Total Flight Duration

[KeyAircraftTotalFlightDuration](#key_flightcontroller_aircrafttotalflightduration_inline)

###### final KeyAircraftTotalFlightDuration

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyAircraftTotalFlightDuration = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AircraftTotalFlightDuration", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Total flight duration, unit: Second. It will not be reset after the aircraft is powered off. `Supported since MSDK 5.5.0`

Total Flight Distance

[KeyAircraftTotalFlightDistance](#key_flightcontroller_aircrafttotalflightdistance_inline)

###### final KeyAircraftTotalFlightDistance

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyAircraftTotalFlightDistance = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AircraftTotalFlightDistance", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Total flight distance, unit: Meter. It will not be reset after the aircraft is powered off. `Supported since MSDK 5.5.0`

Total Flight Times

[KeyAircraftTotalFlightTimes](#key_flightcontroller_aircrafttotalflighttimes_inline)

###### final KeyAircraftTotalFlightTimes

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyAircraftTotalFlightTimes = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AircraftTotalFlightTimes", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Total number of takeoffs and landings. It will not be reset after the aircraft is powered off. `Supported since MSDK 5.5.0`

Aircraft Location

[KeyAircraftLocation3D](#key_flightcontroller_aircraftlocation3d_inline)

###### final KeyAircraftLocation3D

|  |
| --- |
| ``` static final DJIKeyInfo<LocationCoordinate3D> KeyAircraftLocation3D = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AircraftLocation3D", new DJIValueConverter<>(LocationCoordinate3D.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To get the position of the aircraft, including longitude, latitude and altitude. `Supported since MSDK 5.0.0`

Aircraft Attitude Data

[KeyAircraftAttitude](#key_flightcontroller_attitude_inline)

###### final KeyAircraftAttitude

|  |
| --- |
| ``` static final DJIKeyInfo<Attitude> KeyAircraftAttitude = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AircraftAttitude", new DJIValueConverter<>(Attitude.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("Attitude") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Attitude` Current aircraft attitude data. The value range of pitch, roll and yaw are [-180，180]. if the value of pitch, roll and yaw are 0, it means that the aircraft will hover horizontally heading north. `Supported since MSDK 5.0.0`

Aircraft Speed

[KeyAircraftVelocity](#key_flightcontroller_velocity_inline)

###### final KeyAircraftVelocity

|  |
| --- |
| ``` static final DJIKeyInfo<Velocity3D> KeyAircraftVelocity = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AircraftVelocity", new DJIValueConverter<>(Velocity3D.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("Velocity") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Velocity3D` Current flight speed of the aircraft using NED coordinate system. `Supported since MSDK 5.0.0`

Takeoff Altitude

[KeyTakeoffLocationAltitude](#key_flightcontroller_takeofflocationaltitude_inline)

###### final KeyTakeoffLocationAltitude

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyTakeoffLocationAltitude = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"TakeoffLocationAltitude", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double The taking off altitude of aircraft. Unit:meter. `Supported since MSDK 5.0.0`

Aircraft Landing

[KeyIsLandingConfirmationNeeded](#key_flightcontroller_islandingconfirmationneeded_inline)

###### final KeyIsLandingConfirmationNeeded

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsLandingConfirmationNeeded = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsLandingConfirmationNeeded", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that when the aircraft landed 0.5 meters above the ground, the forced landing command `KeyConfirmLanding` should be sent to continue the landing process. `Supported since MSDK 5.0.0`

Flight Log

[KeyFlightLogIndex](#key_flightcontroller_flightlogindex_inline)

###### final KeyFlightLogIndex

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyFlightLogIndex = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FlightLogIndex", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get the current index of the flight log, which is useful when one needs to find the corresponding flight log. `Supported since MSDK 5.0.0`

Serial Number

[KeySerialNumber](#key_flightcontroller_serialnumber_inline)

###### final KeySerialNumber

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeySerialNumber = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SerialNumber", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String To get the serial number of flight controller. `Supported since MSDK 5.0.0`

Firmware Version

[KeyFirmwareVersion](#key_flightcontroller_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String To get the firmware version of flight controller. `Supported since MSDK 5.0.0`

#### Sensor

GPS

[KeyGPSSatelliteCount](#key_flightcontroller_satellitecount_inline)

###### final KeyGPSSatelliteCount

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyGPSSatelliteCount = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GPSSatelliteCount", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("SatelliteCount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get the number of GPS satellites. Usually the number can reach more than 12 when flying at a high altitude. When the period is good, the number can reach 18~19. `Supported since MSDK 5.0.0`

[KeyGPSSignalLevel](#key_flightcontroller_gpssignallevel_inline)

###### final KeyGPSSignalLevel

|  |
| --- |
| ``` static final DJIKeyInfo<GPSSignalLevel> KeyGPSSignalLevel = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GPSSignalLevel", new SingleValueConverter<>(GPSSignalLevel.class,FCGPSSignalLevelMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GPSSignalLevel` To get the signal level of GPS. `Supported since MSDK 5.0.0`

Compass

[KeyCompassCount](#key_flightcontroller_compasscount_inline)

###### final KeyCompassCount

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCompassCount = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CompassCount", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get the number of compass. `Supported since MSDK 5.0.0`

[KeyCompassHeading](#key_flightcontroller_compassheading_inline)

###### final KeyCompassHeading

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyCompassHeading = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CompassHeading", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double To get the heading of the compass. Unit: degree. The north is 0 degrees, the east is 90 degrees. The value range is [-180,180]. `Supported since MSDK 5.0.0`

[KeyCompassHasError](#key_flightcontroller_compasshaserror_inline)

###### final KeyCompassHasError

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCompassHasError = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CompassHasError", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the compass data is wrong. When the aircraft is used in areas with strong interference or magnetic fields, compass data Key\_Gimbal\_RestoreFactorySettings might occur. `KeyStartCompassCalibration` should be called to calibrate compass. `Supported since MSDK 5.0.0`

[KeyStartCompassCalibration](#key_flightcontroller_startcompasscalibration_inline)

###### final KeyStartCompassCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartCompassCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartCompassCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start compass calibration. Make sure there are not any magnets or metal objects near the aircraft and lift the aircraft to about 1.5m above the groud. Calibration status can be obtained through `KeyCompassCalibrationStatus`. The following is the operation process of compass calibration, taking M30 as an example:- 1. Call `KeyStartCompassCalibration` to start compass calibration, `CompassCalibrationState` will change to horizontal calibration state`HORIZONTAL`, refer to the figure below to rotate the aircraft 360 degrees horizontally for horizontal calibration. ![](https://terra-1-g.djicdn.com/71a7d383e71a4fb8887a310eb746b47f/msdk/API-DOC/H.jpg) - 2. After the horizontal calibration is successful, `CompassCalibrationState` will change to vertical calibration state`VERTICAL`，refer to the figure below to rotate the aircraft 360 degrees vertically for vertical calibration. ![](https://terra-1-g.djicdn.com/71a7d383e71a4fb8887a310eb746b47f/msdk/API-DOC/V.jpg) - 3. After the calibration is successful, `CompassCalibrationState` will change to success state `SUCCEEDED`. **note: Please do not start the compass calibration after the aircraft motor starts. call `KeyAreMotorsOn` to judge whether the aircraft motor starts.** `Supported since MSDK 5.0.0`

[KeyStopCompassCalibration](#key_flightcontroller_stopcompasscalibration_inline)

###### final KeyStopCompassCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopCompassCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopCompassCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop compass calibration. `Supported since MSDK 5.0.0`

[KeyIsCompassCalibrating](#key_flightcontroller_iscompasscalibrating_inline)

###### final KeyIsCompassCalibrating

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsCompassCalibrating = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsCompassCalibrating", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the compass is calibrating. `Supported since MSDK 5.0.0`

[KeyCompassCalibrationStatus](#key_flightcontroller_compasscalibrationstate_inline)

###### final KeyCompassCalibrationStatus

|  |
| --- |
| ``` static final DJIKeyInfo<CompassCalibrationState> KeyCompassCalibrationStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CompassCalibrationStatus", new SingleValueConverter<>(CompassCalibrationState.class,FCCompassCalibrationStateMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CompassCalibrationState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `CompassCalibrationState` To get compass calibration status. `Supported since MSDK 5.0.0`

IMU

[KeyIMUCount](#key_flightcontroller_imucount_inline)

###### final KeyIMUCount

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyIMUCount = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IMUCount", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get the number of IMU. `Supported since MSDK 5.0.0`

[KeyStartIMUCalibration](#key_flightcontroller_startimucalibration_inline)

###### final KeyStartIMUCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartIMUCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartIMUCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start IMU calibration. For aircraft with multiple IMUs, this method will calibrate all IMUs. Calling `KeyRebootDevice` to restart the device after IMU calibration is suggested. The following is the operation process of IMU calibration:- 1. Call `KeyStartIMUCalibration` to start IMU calibration, and listen `KeyIMUCalibrationInfo` to get the IMU calibration information. `IMUCalibrationState` change to `CALIBRATING` indicates that IMU calibration is successfully started. - 2. Call `getOrientationsToCalibrate` to get the aircraft orientations that have not been calibrated yet. Reference to the figure in `IMUCalibrationOrientation`, place the aircraft in turn for IMU calibration in each direction, and keep the aircraft stationary and level during the calibration process. - 3. Call `getOrientationCalibrationState` to get the IMU calibration state for each orientation, when the calibraion status changes to `SUCCESSFUL` then put the aircraft in the next direction. - 3. When the uncalibrated orientation list `getOrientationsToCalibrate` is empty, and the calibraion state `getOrientationCalibrationState` changes to `SUCCESSFUL` indicates that the IMU calibraion has been completed. **note: Please do not start the IMU calibration after the aircraft motor starts. call `KeyAreMotorsOn` to judge whether the aircraft motor starts.** `Supported since MSDK 5.0.0`

[KeyIMUCalibrationInfo](#key_flightcontroller_imucalibrationhint_inline)

###### final KeyIMUCalibrationInfo

|  |
| --- |
| ``` static final DJIKeyInfo<IMUCalibrationInfo> KeyIMUCalibrationInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IMUCalibrationInfo", new DJIValueConverter<>(IMUCalibrationInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IMUCalibrationHint") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `IMUCalibrationInfo` To get IMU calibration information. IMU calibration includes IMU calibration of multiple aircraft orientations. IMU calibration information includes the overall calibration status of IMU, the calibration status of each aircraft orientation, as well as the list of uncalibrated aircraft orientations and the list of calibrated aircraft orientations. `Supported since MSDK 5.1.0`

Ultrasound

[KeyUltrasonicHeight](#key_flightcontroller_ultrasonicheight_inline)

###### final KeyUltrasonicHeight

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyUltrasonicHeight = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"UltrasonicHeight", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get the height of ultrasound distance measuring, unit:dm. The height is the distance measured from top to bottom after the aircraft takes off. Barometer altitude is often incorporated to make the data more accurate. **note:**

1. The maximum measurable distance at this altitude is usually 25.5 meters, depending on the actual capabilities of the aircraft.
2. If the aircraft is equipped with a downward-looking sensor, this height will also be integrated with the ranging height of the downward-looking sensor.
3. If the aircraft is equipped with an infrared sensor, this altitude will also be combined with the infrared sensor ranging altitude.
     
     
   `Supported since MSDK 5.0.0`

Wind Speed

[KeyWindWarning](#key_flightcontroller_windwarning_inline)

###### final KeyWindWarning

|  |
| --- |
| ``` static final DJIKeyInfo<WindWarning> KeyWindWarning = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WindWarning", new SingleValueConverter<>(WindWarning.class,FCWindWarningMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `WindWarning` To get current wind speed level. `Supported since MSDK 5.0.0`

[KeyWindSpeed](#key_flightcontroller_windspeed_inline)

###### final KeyWindSpeed

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyWindSpeed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WindSpeed", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get current wind speed, unit: dm/s. `Supported since MSDK 5.0.0`

[KeyWindDirection](#key_flightcontroller_winddirection_inline)

###### final KeyWindDirection

|  |
| --- |
| ``` static final DJIKeyInfo<WindDirection> KeyWindDirection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WindDirection", new SingleValueConverter<>(WindDirection.class,FCWindDirectionStatusMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `WindDirection` To get current wind direction and the world coordinate is used. `Supported since MSDK 5.0.0`

#### Basic Setting

Flight Mode

[KeyMultipleFlightModeEnabled](#key_flightcontroller_multipleflightmodeenabled_inline)

###### final KeyMultipleFlightModeEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyMultipleFlightModeEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultipleFlightModeEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that flight mode switch is allowed. `KeyFlightMode` can be set to T - tripod, A - attitude, P(N) - position, S - sport mode by flipping the three-segment selector switch on the remote control. `false` means the flight mode will maintain P(N) - position mode and can not be changed. `Supported since MSDK 5.0.0`

[KeyRemoteControllerFlightMode](#key_flightcontroller_remotecontrollerflightmode_inline)

###### final KeyRemoteControllerFlightMode

|  |
| --- |
| ``` static final DJIKeyInfo<RemoteControllerFlightMode> KeyRemoteControllerFlightMode = new KeyRemoteControllerFlightMode().canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `RemoteControllerFlightMode` To get the remote controller switch Mode. `Supported since MSDK 5.0.0`

[KeyFlightMode](#key_flightcontroller_msdkflightmode_inline)

###### final KeyFlightMode

|  |
| --- |
| ``` static final DJIKeyInfo<FlightMode> KeyFlightMode = new KeyFlightMode().canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `FlightMode` To get the flight Mode. `Supported since MSDK 5.1.0`

Fail Safe

[KeyIsFailSafe](#key_flightcontroller_isfailsafe_inline)

###### final KeyIsFailSafe

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsFailSafe = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsFailSafe", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the aircraft is out of control and is about to perform fail safe behavior. `Supported since MSDK 5.0.0`

[KeyFailsafeAction](#key_flightcontroller_failsafeaction_inline)

###### final KeyFailsafeAction

|  |
| --- |
| ``` static final DJIKeyInfo<FailsafeAction> KeyFailsafeAction = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FailsafeAction", new SingleValueConverter<>(FailsafeAction.class,FCFailsafeActionMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `FailsafeAction` To set and get fail safe behavior. When the remote controller loses connection with the aircraft, the aircraft will perform according to the set fail safe behavior. `Supported since MSDK 5.0.0`

Low Power

[KeyLowBatteryWarningThreshold](#key_flightcontroller_lowbatterywarningthreshold_inline)

###### final KeyLowBatteryWarningThreshold

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyLowBatteryWarningThreshold = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LowBatteryWarningThreshold", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set and get the threshold for battery low power warning. The threshold is a percentage and the range is [15,50]. When the battery power is lower than the threshold, the aircraft will make a low battery alarm. `Supported since MSDK 5.0.0`

[KeyIsLowBatteryWarning](#key_flightcontroller_islowbatterywarning_inline)

###### final KeyIsLowBatteryWarning

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsLowBatteryWarning = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsLowBatteryWarning", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the battery is in low power status. `Supported since MSDK 5.0.0`

Severely Low Battery

[KeySeriousLowBatteryWarningThreshold](#key_flightcontroller_seriouslowbatterywarningthreshold_inline)

###### final KeySeriousLowBatteryWarningThreshold

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeySeriousLowBatteryWarningThreshold = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SeriousLowBatteryWarningThreshold", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get the threshold of severely low battery warning. The default threshold is 10%, and it can not be set for Matrice 30 Series. If the battery power is lower than the threshold, the aircraft will go back to home. `Supported since MSDK 5.0.0`

[KeyIsSeriousLowBatteryWarning](#key_flightcontroller_isseriouslowbatterywarning_inline)

###### final KeyIsSeriousLowBatteryWarning

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsSeriousLowBatteryWarning = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsSeriousLowBatteryWarning", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the battery is in severely low status. `Supported since MSDK 5.0.0`

LED

[KeyLEDsSettings](#key_flightcontroller_ledssettings_inline)

###### final KeyLEDsSettings

|  |
| --- |
| ``` static final DJIKeyInfo<LEDsSettings> KeyLEDsSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LEDsSettings", new DJIValueConverter<>(LEDsSettings.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LEDsSettings` LED related settings. The settings for forearm lights, status indicators and night lights are supported. `Supportd since MSDK 5.0`

Motor ESC

[KeyAreMotorsOn](#key_flightcontroller_aremotorson_inline)

###### final KeyAreMotorsOn

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyAreMotorsOn = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AreMotorsOn", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the motor starts. `Supported since MSDK 5.0.0`

[KeyLockMotors](#key_flightcontroller_lockmotors_inline)

###### final KeyLockMotors

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLockMotors = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LockMotors", SingleValueConverter.BooleanConverter).canGet(false).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the motor is locked and will not turn when the remote controller stick is moved. `Supported since MSDK 5.0.0`

[KeyESCBeepEnabled](#key_flightcontroller_escbeepenabled_inline)

###### final KeyESCBeepEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyESCBeepEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ESCBeepEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the ESC beeping function is opened. When the aircraft loses contact by accident, this function can be turned on to identify the position of the aircraft by sound. If motors are running, Stop them and try again. `Supported since MSDK 5.0.0`

Coordinated Turn

[KeyCoordinatedTurnEnabled](#key_flightcontroller_coordinatedturnenabled_inline)

###### final KeyCoordinatedTurnEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCoordinatedTurnEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CoordinatedTurnEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the coordinated turn function is opened. When the aircraft turns, the node follows the flight direction. There is no sideslip flight, and the resistance of flight is smaller. `Supported since MSDK 5.0.0`

GNSS

[KeyNavigationSatelliteSystemSource](#key_flightcontroller_navigationsatellitesystemsource_inline)

###### final KeyNavigationSatelliteSystemSource

|  |
| --- |
| ``` static final DJIKeyInfo<NavigationSatelliteSystem> KeyNavigationSatelliteSystemSource = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"NavigationSatelliteSystemSource", new SingleValueConverter<>(NavigationSatelliteSystem.class,NavigationSatelliteSystemMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `NavigationSatelliteSystem` To set the GNSS used. The GNSS is only supported when the RTK location function is closed, and can not be changed when the aircraft is in the air. After the GNSS is switched, do not take off until satellite signal strength reaches at least level 3. If RTK location function is opened, the aircraft will use GPS, GLONASS, Galileo and Beidou GNSS. `Supported since MSDK 5.0.0`

#### Basic Operation

Take Off

[KeyStartTakeoff](#key_flightcontroller_starttakeoff_inline)

###### final KeyStartTakeoff

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartTakeoff = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartTakeoff", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start the automatic taking off of the aircraft. When the aircraft hovers at an altitude of 1.2 meters(4 feet) above the ground, taking off is completed. If the motor is already opened, this command can not be executed. `Supported since MSDK 5.0.0`

[KeyStopTakeoff](#key_flightcontroller_stoptakeoff_inline)

###### final KeyStopTakeoff

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopTakeoff = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopTakeoff", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop the automatic take-off of the aircraft. If this interface is called before automatic take-off is finished, the aircraft will stop taking off and hover at the current altitude. `Supported since MSDK 5.0.0`

Landing

[KeyStartAutoLanding](#key_flightcontroller_startautolanding_inline)

###### final KeyStartAutoLanding

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartAutoLanding = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartAutoLanding", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start the automatic landing of the aircraft. `Supported since MSDK 5.0.0`

[KeyStopAutoLanding](#key_flightcontroller_stopautolanding_inline)

###### final KeyStopAutoLanding

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopAutoLanding = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopAutoLanding", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop the autonomuous landing of the aircraft. If this interface is called during the automatic landing process, the aircraft will stop landing and hover at the current altitude. `Supported since MSDK 5.0.0`

[KeyConfirmLanding](#key_flightcontroller_confirmlanding_inline)

###### final KeyConfirmLanding

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyConfirmLanding = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"ConfirmLanding", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To confirm continue landing. When the aircraft is at an altitude of less than 0.7 meters above the ground, the aircraft will stop landing and wait for confirmation. `KeyIsLandingConfirmationNeeded` can be used to check whether landing confirmation is needed. `Supported since MSDK 5.0.0`

Restart

[KeyRebootDevice](#key_flightcontroller_rebootdevice_inline)

###### final KeyRebootDevice

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyRebootDevice = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RebootDevice", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To restart the core device of the aircraft. It is suggested to call this method after IMU calibration. **note: Please do not restart the core device of the aircraft after the aircraft motor starts. call `KeyAreMotorsOn` to judge whether the aircraft motor starts.** `Supported since MSDK 5.0.0`

LookAt

[KeyLookAt](#key_flightcontroller_msdklookat_inline)

###### final KeyLookAt

|  |
| --- |
| ``` static final DJIActionKeyInfo<LookAtInfo, EmptyMsg> KeyLookAt = new KeyLookAt().canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LookAtInfo` The aircraft will look at the GPS point you set up. `Supported since MSDK 5.9.0`

Maximum Altitude

[KeyHeightLimitRange](#key_flightcontroller_heightlimitrange_inline)

###### final KeyHeightLimitRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntValueConfig> KeyHeightLimitRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"HeightLimitRange", new DJIValueConverter<>(IntValueConfig.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `IntValueConfig` To get the height limit setting range. Unit: meter. `Supported since MSDK 5.0.0`

[KeyHeightLimit](#key_flightcontroller_heightlimit_inline)

###### final KeyHeightLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyHeightLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"HeightLimit", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set the maximum flight altitude of the aircraft. Unit: meter. `KeyHeightLimitRange` can be used to get setting range. `Supported since MSDK 5.0.0`

[KeyIsNearHeightLimit](#key_flightcontroller_isnearheightlimit_inline)

###### final KeyIsNearHeightLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsNearHeightLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsNearHeightLimit", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the aircraft has reached the maximum flight altitude. `Supported since MSDK 5.0.0`

Maximum Flight Distance

[KeyDistanceLimitEnabled](#key_flightcontroller_distancelimitenabled_inline)

###### final KeyDistanceLimitEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyDistanceLimitEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DistanceLimitEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the distance limitation switch is opened. After this switch is opened, `KeyDistanceLimit` can be used to set the maximum flight distance of the aircraft. `Supported since MSDK 5.0.0`

[KeyDistanceLimitRange](#key_flightcontroller_distancelimitrange_inline)

###### final KeyDistanceLimitRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntValueConfig> KeyDistanceLimitRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DistanceLimitRange", new DJIValueConverter<>(IntValueConfig.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `IntValueConfig` To get diatance limitation setting range. Unit: meter. `Supported since MSDK 5.0.0`

[KeyDistanceLimit](#key_flightcontroller_distancelimit_inline)

###### final KeyDistanceLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyDistanceLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DistanceLimit", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set the maximum flight distance of the aircraft. Unit: meter. This value is the distance between aircraft and home point. `KeyDistanceLimitRange` can be used to get the setting range. `Supported since MSDK 5.0.0`

[KeyIsNearDistanceLimit](#key_flightcontroller_isneardistancelimit_inline)

###### final KeyIsNearDistanceLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsNearDistanceLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsNearDistanceLimit", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the aircraft has reached the maximum flight distance. `Supported since MSDK 5.0.0`

Multi Gimbal Synchronization Control

[KeyStartMultiGimbalSyncControl](#key_flightcontroller_startmultigimbalsynccontrol_inline)

###### final KeyStartMultiGimbalSyncControl

|  |
| --- |
| ``` static final DJIActionKeyInfo<MultiGimbalSyncControlInfo,EmptyMsg> KeyStartMultiGimbalSyncControl = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartMultiGimbalSyncControl", new DJIValueConverter<>(MultiGimbalSyncControlInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `MultiGimbalSyncControlInfo` Enable multi-gimbal Synchronization control. Multiple gimbals can be controlled at the same time through one remote controller. Please make sure that the set gimbals have been mounted on the aircraft. This interface is suitable for aircraft that can mount multiple gimbals, such as M300 RTK and M350 RTK. `Supported since MSDK 5.2.0`

[KeyStopMultiGimbalSyncControl](#key_flightcontroller_stopmultigimbalsynccontrol_inline)

###### final KeyStopMultiGimbalSyncControl

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopMultiGimbalSyncControl = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopMultiGimbalSyncControl", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Disable multi-gimbal Synchronization control. `Supported since MSDK 5.2.0`

[KeyMultiGimbalSyncStatus](#key_flightcontroller_multigimbalsyncstatus_inline)

###### final KeyMultiGimbalSyncStatus

|  |
| --- |
| ``` static final DJIKeyInfo<MultiGimbalSyncStatus> KeyMultiGimbalSyncStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiGimbalSyncStatus", new DJIValueConverter<>(MultiGimbalSyncStatus.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `MultiGimbalSyncStatus` Gets multi-gimbal Synchronization control status. `Supported since MSDK 5.2.0`

Basic Operation

[KeyPropellerRotation](#key_flightcontroller_propellerrotation_inline)

###### final KeyPropellerRotation

|  |
| --- |
| ``` static final DJIActionKeyInfo<PropellerRotationCommand,PropellerRotationCommandResult> KeyPropellerRotation = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"PropellerRotation", new SingleValueConverter<>(PropellerRotationCommand.class,PropellerRotationCommandMsg.class),new DJIValueConverter<>(PropellerRotationCommandResult.class)).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean Aircraft slow propeller function. **Note: Currently, only the M400 model supports this interface, but it does not support LOW\_SPEED\_REVERSE\_ROTATION. Therefore, the aircraft slow propeller currently supports rotation in only one direction.** `Supported since MSDK 5.15.0`

#### Return-to-Home Function

Intelligent Return-to-Home

[KeyIsHomeLocationSet](#key_flightcontroller_ishomelocationset_inline)

###### final KeyIsHomeLocationSet

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsHomeLocationSet = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsHomeLocationSet", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means home point has been set. When the GPS signal is good, the aircraft will automatically set the current position as home point. `KeyIsHomeLocationSet` will return `true`. `Supported since MSDK 5.0.0`

[KeyHomeLocation](#key_flightcontroller_homelocation_inline)

###### final KeyHomeLocation

|  |
| --- |
| ``` static final DJIKeyInfo<LocationCoordinate2D> KeyHomeLocation = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"HomeLocation", new DJIValueConverter<>(LocationCoordinate2D.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LocationCoordinate2D` To set and get current home point location, including longitude and latitude. When GPS signal is good, aircraft will automatically set current position as home point. This key can also be used to change home point location. `Supported since MSDK 5.0.0`

[KeyGoHomeHeight](#key_flightcontroller_gohomeheight_inline)

###### final KeyGoHomeHeight

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyGoHomeHeight = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GoHomeHeight", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set and get relative altitude when returning home. Unit: meter. This value is related to the altitude when taking off. `KeyGoHomeHeightRange` can be used to get return-to-home height setting range. If the horizontal distance between aircraft and home point is within 50 meters, the aircraft will ignore the set return-to-home altitude and return at current altitude. If the forward vision system does not work properly, the aircraft will climb to the set altitude and return. `Supported since MSDK 5.0.0`

[KeyGoHomeHeightRange](#key_flightcontroller_gohomeheightrange_inline)

###### final KeyGoHomeHeightRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntValueConfig> KeyGoHomeHeightRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GoHomeHeightRange", new DJIValueConverter<>(IntValueConfig.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `IntValueConfig` To get the setting range of the return-to-home altitude. Unit: meter. `Supported since MSDK 5.0.0`

[KeyHomeLocationUsingCurrentAircraftLocation](#key_flightcontroller_homelocationusingcurrentaircraftlocation_inline)

###### final KeyHomeLocationUsingCurrentAircraftLocation

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyHomeLocationUsingCurrentAircraftLocation = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"HomeLocationUsingCurrentAircraftLocation", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To set current aircraft location as home point location. `Supported since MSDK 5.0.0`

[KeyStartGoHome](#key_flightcontroller_startgohome_inline)

###### final KeyStartGoHome

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartGoHome = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartGoHome", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start intelligent return-to-home. When the GPS signal is not good, intelligent return-to-home can not be started. Intelligent return-to-home can also be started by the smart return-to-home button on the remote controller. During the return-to-home home process, the user can control the altitude of the aircraft with the throttle stick and control the speed of the aircraft with the pitch stick to avoid obstacles. During the return-to-home process, the user can regain control of the aircraft after exiting the intelligent return-to-home home via the intelligent return-to-home button or by calling `KeyStopGoHome`. `KeyGoHomeStatus` can be used to get intelligent return-to-home status. `Supported since MSDK 5.0.0`

[KeyStopGoHome](#key_flightcontroller_stopgohome_inline)

###### final KeyStopGoHome

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopGoHome = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopGoHome", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop intelligent return-to-home. `Supported since MSDK 5.0.0`

[KeyGoHomeStatus](#key_flightcontroller_gohomestatus_inline)

###### final KeyGoHomeStatus

|  |
| --- |
| ``` static final DJIKeyInfo<GoHomeState> KeyGoHomeStatus = new KeyGoHomeStatus().canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GoHomeState` To get intelligent return-to-home status. `Supported since MSDK 5.0.0`

Return-to-Home Information

[KeyGoHomeInfo](#key_flightcontroller_msdkgohomeinfo_inline)

###### final KeyGoHomeInfo

|  |
| --- |
| ``` static final DJIKeyInfo<GoHomeInfo> KeyGoHomeInfo = new KeyGoHomeInfo().canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GoHomeInfo` Get the information related to return-to-home. When the obtained `getType` status is `NORMAL`, you need to call the `KeyGoHomeConfirm` interface to confirm or cancel the return-to-home operation. `Supported since MSDK 5.12.0`

Return-to-Home Confirm

[KeyGoHomeConfirm](#key_flightcontroller_msdkgohomeconfirm_inline)

###### final KeyGoHomeConfirm

|  |
| --- |
| ``` static final DJIActionKeyInfo<Boolean, EmptyMsg> KeyGoHomeConfirm = new KeyGoHomeConfirm().canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean Confirm or cancel the return home operation. When the status of `getType` obtained by calling `KeyGoHomeInfo` is `NORMAL`, you need to call this interface to confirm or cancel the return-to-home operation. `true` means confirming the return-to-home. `false` means canceling the return-to-home. `Supported since MSDK 5.12.0`

Low Battery Return-to-Home

[KeyLowBatteryRTHEnabled](#key_flightcontroller_smartbatteryrthenabled_inline)

###### final KeyLowBatteryRTHEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLowBatteryRTHEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LowBatteryRTHEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("SmartBatteryRTHEnabled") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means intelligent low battery return-to-home function is opened. To avoid unnecessary dangers dangerous due to the low battery, aircraft will intelligently judge whether the current battery is sufficient according to the flight position information. If current battery power is only enough to complete the return-to-home process, MSDK will prompt whether operate the return-to-home process. If the user does not make a selection within 10s, MSDK will automatically return-to-home after 10s. During the returning process, you can short press the smart return-to-home button on the remote control to cancel the return-to-home process. Smart low battery return-to-home only occurs once during one flight. If the user cancels the low-battery return-to-home reminder and continues to fly, the aircraft may be forced to land due to insufficient power when returning, resulting in the aircraft being lost or crashed. For security reasons, it is not recommended to turn this function off. `Supported since MSDK 5.0.0`

[KeyLowBatteryRTHInfo](#key_flightcontroller_msdklowbatteryrthinfo_inline)

###### final KeyLowBatteryRTHInfo

|  |
| --- |
| ``` static final DJIKeyInfo<LowBatteryRTHInfo> KeyLowBatteryRTHInfo = new KeyLowBatteryRTHInfo().canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LowBatteryRTHInfo` To get related information of the intelligent low battery power. This function is only valid when `KeyLowBatteryRTHEnabled` is enabled. `Supported since MSDK 5.0.0`

[KeyLowBatteryRTHConfirm](#key_flightcontroller_msdklowbatteryrthconfirm_inline)

###### final KeyLowBatteryRTHConfirm

|  |
| --- |
| ``` static final DJIActionKeyInfo<Boolean, EmptyMsg> KeyLowBatteryRTHConfirm = new KeyLowBatteryRTHConfirm().canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean Confirm or cancel the return home operation. When calling `KeyLowBatteryRTHInfo` and the status obtained is `COUNTING_DOWN`, you can call this interface to confirm or cancel the return operation. `true` indicates confirmation of return. `false` means to cancel the return flight. `Supported since MSDK 5.8.0`

#### Security Code

Device Status

[KeyAccessLockerAllDeviceStatus](#key_flightcontroller_accesslockerv1alldevicestates_inline)

###### final KeyAccessLockerAllDeviceStatus

|  |
| --- |
| ``` static final DJIKeyInfo<List<AccessLockerDeviceStatus>> KeyAccessLockerAllDeviceStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AccessLockerAllDeviceStatus", new SingleValueConverter<>((Class)List.class,AccessLockerV1AllDeviceStates.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("AccessLockerV1AllDeviceStates") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** List<`AccessLockerDeviceStatus`> Get the security code status of the storage device. Through this interface, you can obtain whether the storage device on the aircraft supports the security code function, whether the security code function is enabled, and whether the security code needs to be verified. Security code is to keep your media data safe. After the security code is set, the MSDK App needs to perform password verification after connecting to the aircraft. After the verification is passed, it can take photos, record videos and read and write media data in the storage device. - `getIsFeatureSupported` is `true`, indicating that the storage device supports the security code function. You can call `KeyAccessLockerSetSecurityCode` to set the security code. - `getIsFeatureEnabled` is `true`, indicating that the security code function of the storage device is enabled. You can call `KeyAccessLockerModifySecurityCode` for security code modification. If you forget the security code, you can call `KeyAccessLockerResetSecurityCode` to reset the security code. - `getIsFeatureNeedToBeVerified` is `true`, indicating that the storage device needs to verify the security code. Please call `KeyAccessLockerVerifySecurityCode` for security code verification, otherwise, you will not be able to take photos and record videos, and you will not be able to read and write media data in the storage device. The security code is usually required to be verified when the aircraft is restarted or the storage device is inserted. - Security code is neither saved on device nor accessible by DJI. - It is not possible to reset security code. If security code is lost, format SD card to delete code. - Security code settings only available for Zenmuse H20, Matrice 30 series cameras and Mavic 3 Enterprise series cameras. Zenmuse P1, Zenmuse L1, and third-party payloads currently not supported. `Supported since MSDK 5.1.0`

Set Security Code

[KeyAccessLockerSetSecurityCode](#key_flightcontroller_accesslockerv1setupuseraccount_inline)

###### final KeyAccessLockerSetSecurityCode

|  |
| --- |
| ``` static final DJIActionKeyInfo<AccessLockerSetSecurityCodeInfo,EmptyMsg> KeyAccessLockerSetSecurityCode = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"AccessLockerSetSecurityCode", new DJIValueConverter<>(AccessLockerSetSecurityCodeInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("AccessLockerV1SetupUserAccount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AccessLockerSetSecurityCodeInfo` Set a security code. The device and storage type can be selected for security code setting through this interface.

- The storage device will be formatted after setting the security code, please make sure the media data has been backed up.
- Please keep the security code in a safe place. If you lose it, you will not be able to retrieve it. You can only unlock the storage device by formatting it.
- you should set a security code with a length of at least 4 characters. It is recommended that use numbers, uppercase and lowercase letters to form the security code.
  
  
`Supported since MSDK 5.1.0`

Verify Security Code

[KeyAccessLockerVerifySecurityCode](#key_flightcontroller_accesslockerv1verifyuseraccount_inline)

###### final KeyAccessLockerVerifySecurityCode

|  |
| --- |
| ``` static final DJIActionKeyInfo<AccessLockerVerifySecurityCodeInfo,EmptyMsg> KeyAccessLockerVerifySecurityCode = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"AccessLockerVerifySecurityCode", new DJIValueConverter<>(AccessLockerVerifySecurityCodeInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("AccessLockerV1VerifyUserAccount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AccessLockerVerifySecurityCodeInfo` Verify the security code. The security code can be verified through this interface. After the verification is passed, you can take photos, record videos and read and write media data in the storage device. `Supported since MSDK 5.1.0`

Modify Security Code

[KeyAccessLockerModifySecurityCode](#key_flightcontroller_accesslockerv1modifyuseraccount_inline)

###### final KeyAccessLockerModifySecurityCode

|  |
| --- |
| ``` static final DJIActionKeyInfo<AccessLockerModifySecurityCodeInfo,EmptyMsg> KeyAccessLockerModifySecurityCode = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"AccessLockerModifySecurityCode", new DJIValueConverter<>(AccessLockerModifySecurityCodeInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("AccessLockerV1ModifyUserAccount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AccessLockerModifySecurityCodeInfo` Modify the security code. `Supported since MSDK 5.1.0`

Reset Security Code

[KeyAccessLockerResetSecurityCode](#key_flightcontroller_accesslockerv1resetuseraccount_inline)

###### final KeyAccessLockerResetSecurityCode

|  |
| --- |
| ``` static final DJIActionKeyInfo<AccessLockerResetSecurityCodeInfo,EmptyMsg> KeyAccessLockerResetSecurityCode = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"AccessLockerResetSecurityCode", new DJIValueConverter<>(AccessLockerResetSecurityCodeInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("AccessLockerV1ResetUserAccount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AccessLockerResetSecurityCodeInfo` Reset the security code. Reset the security security code will format the storage device, please make sure the media data is backed up. `Supported since MSDK 5.1.0`

#### AirSense

[KeyAirSenseSystemSupported](#key_flightcontroller_airsensesystemsupported_inline)

###### final KeyAirSenseSystemSupported

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyAirSenseSystemSupported = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AirSenseSystemSupported", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean Whether to support DJI AirSense system. `Supported since MSDK 5.9.0`

[KeyAirSenseSystemInformation](#key_flightcontroller_airsensesysteminformation_inline)

###### final KeyAirSenseSystemInformation

|  |
| --- |
| ``` static final DJIKeyInfo<AirSenseSystemInformation> KeyAirSenseSystemInformation = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AirSenseSystemInformation", new DJIValueConverter<>(AirSenseSystemInformation.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AirSenseSystemInformation` Gets information about the DJI AirSense system. Contains warning levels and detailed status of detected airplanes (Manned aircraft with AirSense system. For example, civil aircraft, helicopter and so on. It is referred to airplane.). DJI AirSense only sends out warnings of nearby airplanes under certain circumstances. It will NOT control DJI aircraft to avoid other airplanes automatically. Make sure to fly with your DJI aircraft within visual line of sight at all times, and always fly with caution. After receiving warnings, lower your DJI aircraft to a safe height. In addition, DJI AirSense has the following limitations: - DJI AirSense can only receive messages sent from airplane equipped with an ADS-B out device under 1090ES (RTCA DO-260) or UAT (RTCA Do-282) standards. For airplane without ADS-B outs or with malfunctioning ADS-B outs, DJI AirSense cannot receive related broadcasted messages or send out warnings. - When there are obstacles between the airplane and DJI aircraft, DJI AirSense will fail to receive ADS-B messages sent from airplane or warnings. - DJI AirSense may fail to receive ADS-B messages sent from airplane or send out warnings due to environmental changes and disturbances. It is highly recommended to fly with caution and stay aware of your surroundings during flight. - DJI AirSense cannot send out warnings when the DJI aircraft cannot accurately determine its location. - DJI AirSense cannot receive ADS-B messages sent from airplane or send out warnings when it is disabled or misconfigured. `Supported since MSDK 5.5.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found