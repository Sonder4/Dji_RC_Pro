# class DJIFlightControllerKey

|  |
| --- |
| ``` class DJIFlightControllerKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`DJIFlightControllerKey`provides a set of methods to set and get the flight controller parameters and behavior. Flight controller is a micro computer. It is receiving the control data from pilots and sensor data from transducers and combine them to adjust each blade power to fly. This class can get the aircraft attitude, aircraft state, flight mode, flight control settings, compass and IMU etc. `Supported since MSDK 5.0`

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

**Parameter:** Boolean `true` means flight controller is connected. `Supported since MSDK 5.0`

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

`true` means aircraft is in flight. `Supported since MSDK 5.0`

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

Accumulated flight time since the aircraft motor started. unit: 0.1s. Cleared after power on again. `Supported since MSDK 5.0`

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

`Supported since MSDK 5.0`

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

**Parameter:** `Attitude` Current aircraft attitude data. The value range of pitch, roll and yaw are [-180，180]. if the value of pitch, roll and yaw are 0, it means that the aircraft will hover horizontally heading north. `Supported since MSDK 5.0`

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

**Parameter:** `Value_Common_Struct_Velocity3D` Current flight speed of the aircraft using NED coordinate system. `Supported since MSDK 5.0`

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

**Parameter:** Double The taking off altitude of aircraft. Unit:meter. `Supported since MSDK 5.0`

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

**Parameter:** Boolean `true` means that when the aircraft landed to 0.5 meters above the ground, the forced landing command `KeyConfirmLanding` shoulde be sent to continue the landing process. `Supported since MSDK 5.0`

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

**Parameter:** Integer To get current index of the flight log, which is useful when needs to find the corresponding flight log. `Supported since MSDK 5.0`

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

**Parameter:** String To get the serial number of flight controller. `Supported since MSDK 5.0`

Firmware Version Number

[KeyFirmwareVersion](#key_flightcontroller_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String To get the firmware version number of flight controller. `Supported since MSDK 5.0`

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

**Parameter:** Integer To get the number of GPS satellites. Usually the number can reach to more than 12 when flying at high altitude. When the period is good, the number can reach 18~19. `Supported since MSDK 5.0`

[KeyGPSSignalLevel](#key_flightcontroller_gpssignallevel_inline)

###### final KeyGPSSignalLevel

|  |
| --- |
| ``` static final DJIKeyInfo<GPSSignalLevel> KeyGPSSignalLevel = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GPSSignalLevel", new SingleValueConverter<>(GPSSignalLevel.class,FCGPSSignalLevelMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GPSSignalLevel` To get the signal level of GPS. `Supported since MSDK 5.0`

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

**Parameter:** Integer To get the number of compass. `Supported since MSDK 5.0`

[KeyCompassHeading](#key_flightcontroller_compassheading_inline)

###### final KeyCompassHeading

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyCompassHeading = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CompassHeading", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double To get the heading of compass. Unit: degree. The north is 0 degrees, the east is 90 degrees. The value range is [-180,180]. `Supported since MSDK 5.0`

[KeyCompassHasError](#key_flightcontroller_compasshaserror_inline)

###### final KeyCompassHasError

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCompassHasError = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CompassHasError", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the compass data is wrong. When the aircraft is used in areas with strong interference or magnetic fields, compass data Key\_Gimbal\_RestoreFactorySettings might occur. `KeyStartCompassCalibration` should be called to calibrate compass. `Supported since MSDK 5.0`

[KeyStartCompassCalibration](#key_flightcontroller_startcompasscalibration_inline)

###### final KeyStartCompassCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartCompassCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartCompassCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start compass calibration. Make sure there are not no magnets or metal objects near the aircraft. Calibration status can be obtained through `KeyCompassCalibrationStatus`. `Supported since MSDK 5.0`

[KeyStopCompassCalibration](#key_flightcontroller_stopcompasscalibration_inline)

###### final KeyStopCompassCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopCompassCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopCompassCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop compass calibration. `Supported since MSDK 5.0`

[KeyIsCompassCalibrating](#key_flightcontroller_iscompasscalibrating_inline)

###### final KeyIsCompassCalibrating

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsCompassCalibrating = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsCompassCalibrating", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the compass is calibrating. `Supported since MSDK 5.0`

[KeyCompassCalibrationStatus](#key_flightcontroller_compasscalibrationstate_inline)

###### final KeyCompassCalibrationStatus

|  |
| --- |
| ``` static final DJIKeyInfo<CompassCalibrationState> KeyCompassCalibrationStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CompassCalibrationStatus", new SingleValueConverter<>(CompassCalibrationState.class,FCCompassCalibrationStateMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CompassCalibrationState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `CompassCalibrationState` To get compass calibration status. `Supported since MSDK 5.0`

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

**Parameter:** Integer To get the number of IMU. `Supported since MSDK 5.0`

[KeyStartIMUCalibration](#key_flightcontroller_startimucalibration_inline)

###### final KeyStartIMUCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartIMUCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartIMUCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start IMU calibration. For aircraft with multiple IMUs, this method will calibrate all IMUs. Keep the aircraft still and level during the calibration process, which will take 5 to 10 minutes. Execution status of IMU calibration can be got through `Key_FlightController_IMUState`. Calling `KeyRebootDevice` to restart the device after IMU calibration is suggested. `Supported since MSDK 5.0`

Ultrasound

[KeyIsUltrasonicUsed](#key_flightcontroller_isultrasonicused_inline)

###### final KeyIsUltrasonicUsed

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsUltrasonicUsed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsUltrasonicUsed", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means ultrasound distance measuring is used. Usually when the distance between aircraft and ground is lower than 8 meters, the ultrasound sensor will start to work. `Supported since MSDK 5.0`

[KeyUltrasonicHasError](#key_flightcontroller_ultrasonichaserror_inline)

###### final KeyUltrasonicHasError

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyUltrasonicHasError = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"UltrasonicHasError", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `false` means ultrasound is in normal use with no error. `Supported since MSDK 5.0`

[KeyUltrasonicHeight](#key_flightcontroller_ultrasonicheight_inline)

###### final KeyUltrasonicHeight

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyUltrasonicHeight = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"UltrasonicHeight", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get the height of ultrasound distance measuring. The accuracy of height is 0.1 meters. The maximum measurable distance is 25.5 meters. The data can be used only when `KeyIsUltrasonicUsed` returns `true` and `KeyUltrasonicHasError` returns `false`. `Supported since MSDK 5.0`

Vision

[KeyIsVisionSensorUsed](#key_flightcontroller_isvisionsensorused_inline)

###### final KeyIsVisionSensorUsed

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsVisionSensorUsed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsVisionSensorUsed", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means vision sensor distance measuring is used. Usually when the aircraft is less than 3 meters above the ground and the ground has enough texture, the vision sensor will start to work. `Supported since MSDK 5.0`

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

**Parameter:** `WindWarning` To get current wind speed. `Supported since MSDK 5.0`

[KeyWindDirection](#key_flightcontroller_winddirection_inline)

###### final KeyWindDirection

|  |
| --- |
| ``` static final DJIKeyInfo<WindDirection> KeyWindDirection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WindDirection", new SingleValueConverter<>(WindDirection.class,FCWindDirectionStatusMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `WindDirection` To get current wind direction and the world coordinate is used. `Supported since MSDK 5.0`

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

**Parameter:** Boolean `true` means that flight mode switch is allowed. Flight mode can be set to T - tripod, A - attitude, P - position, S - sport mode by flipping the three-segment selector switch on the remote control. `false` means the flight mode will maintain P - position mode and can not be changed. The current flight mode of the aircraft can be obtained through `KeyCurrentRCFlightMode`. `Supported since MSDK 5.0`

[KeyCurrentRCFlightMode](#key_flightcontroller_currentrcflightmode_inline)

###### final KeyCurrentRCFlightMode

|  |
| --- |
| ``` static final DJIKeyInfo<RemoteControllerFlightModeMsg> KeyCurrentRCFlightMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CurrentRCFlightMode", new DJIValueConverter<>(RemoteControllerFlightModeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `RemoteControllerFlightMode` To get current flight mode of aircraft. `Supported since MSDK 5.0`

Out of Control

[KeyIsFailSafe](#key_flightcontroller_isfailsafe_inline)

###### final KeyIsFailSafe

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsFailSafe = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsFailSafe", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the aircraft is out of control and is about to perform out-of-control behavior. `Supported since MSDK 5.0`

[KeyFailsafeAction](#key_flightcontroller_failsafeaction_inline)

###### final KeyFailsafeAction

|  |
| --- |
| ``` static final DJIKeyInfo<FailsafeAction> KeyFailsafeAction = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FailsafeAction", new SingleValueConverter<>(FailsafeAction.class,FCFailsafeActionMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `FailsafeAction` To set and get out-of-control behaviour. When the remote controller loses connection with the aircraft, the aircraft will perform according to the set out-of-control behavior. `Supported since MSDK 5.0`

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

**Parameter:** Integer To set and get the threshold for battery low power worning. The threshold is percentage and the range is [15,50. When the battery power is lower than the threshold, the aircraft will make a low battery alarm. `Supported since MSDK 5.0`

[KeyIsLowBatteryWarning](#key_flightcontroller_islowbatterywarning_inline)

###### final KeyIsLowBatteryWarning

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsLowBatteryWarning = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsLowBatteryWarning", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the battery is in low power status. `Supported since MSDK 5.0`

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

**Parameter:** Integer To get the threshold of severely low battery warning. The default threshold is 10%, and it can not be set for Matrice 30 Series. If the battery power is lower than the threshold, the aircraft will go back to home. `Supported since MSDK 5.0`

[KeyIsSeriousLowBatteryWarning](#key_flightcontroller_isseriouslowbatterywarning_inline)

###### final KeyIsSeriousLowBatteryWarning

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsSeriousLowBatteryWarning = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsSeriousLowBatteryWarning", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the battery is in severely low status. `Supported since MSDK 5.0`

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

**Parameter:** Boolean `true` means the motor starts. `Supported since MSDK 5.0`

[KeyLockMotors](#key_flightcontroller_lockmotors_inline)

###### final KeyLockMotors

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLockMotors = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LockMotors", SingleValueConverter.BooleanConverter).canGet(false).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that the motor is locked and will not turn when the remote controller stick is moved. `Supported since MSDK 5.0`

[KeyESCBeepEnabled](#key_flightcontroller_escbeepenabled_inline)

###### final KeyESCBeepEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyESCBeepEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ESCBeepEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the ESC beeping function is opened. When the aircraft loses contact by accident, this function can be turned on to identify the position of the aircraft by sound. `Supported since MSDK 5.0`

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

**Parameter:** Boolean `true` means the coordinated turn function is opened. When the aircraft turns, the node follws the flight direction. There is no sideflip flight, and the resistance of flight is smaller. `Supported since MSDK 5.0`

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

**Parameter:** `NavigationSatelliteSystem` To set the GNSS used. The GNSS is only supported when the RTK locationing function is closed, and can not be changed when the aircraft is in the air. After the GNSS is switched, do not take off until satellite signal strength reaches at least level 3. If RTK locationing function is opened, the aircraft will use GPS, GLONASS, Galileo and Beidou GNSS. `Supported since MSDK 5.0`

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

To start the autonomously taking off of the aircraft. When the aircraft hovers at an altitude of 1.2 meters(4 feet) above the ground, taking off is completed. If the motor is already opened, this command can not be executed. `Supported since MSDK 5.0`

[KeyPrecisionStartTakeoff](#key_flightcontroller_precisionstarttakeoff_inline)

###### final KeyPrecisionStartTakeoff

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyPrecisionStartTakeoff = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"PrecisionStartTakeoff", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start autonomous and accurate take-off of the aircraft. The aircraft will take off vertically above 7 meters, and automatically collect information around the take-off points. If sufficient information is collected, the aircraft will land precisely at the take-off point when returning home. Please call `Key_FlightAssistant_PrecisionLandingEnabled` for precise autonomous landing. `Supported since MSDK 5.0`

[KeyStopTakeoff](#key_flightcontroller_stoptakeoff_inline)

###### final KeyStopTakeoff

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopTakeoff = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopTakeoff", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop the autonomous take-off of the aircraft. If this interface is called before autonomous take-off is finished, the aircraft will stop taking off and hover at current altitude. `Supported since MSDK 5.0`

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

To start the autonomuous landing of the aircraft. `Supported since MSDK 5.0`

[KeyStopAutoLanding](#key_flightcontroller_stopautolanding_inline)

###### final KeyStopAutoLanding

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopAutoLanding = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopAutoLanding", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop the autonomuous landing of the aircraft. If this interface is called during the autonomous landing process, the aircraft will stop landing and hover at current altitude. `Supported since MSDK 5.0`

[KeyConfirmLanding](#key_flightcontroller_confirmlanding_inline)

###### final KeyConfirmLanding

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyConfirmLanding = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"ConfirmLanding", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To confirm continue landing. When the aircraft is at an altitude of less than 0.7 meters above the ground, the aircraft will stop landing and wait for confirmation. `KeyIsLandingConfirmationNeeded` can be used to check whether landing confirmation is needed. `Supported since MSDK 5.0`

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

To restart the core device of the aircraft. It is suggested to call this method after IMU calibration. `Supported since MSDK 5.0`

Maximum Altitude

[KeyHeightLimitRange](#key_flightcontroller_heightlimitrange_inline)

###### final KeyHeightLimitRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntMinMax> KeyHeightLimitRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"HeightLimitRange", new DJIValueConverter<>(IntMinMax.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_IntValueConfig` To get the height limit setting range. Unit: meter. `Supported since MSDK 5.0`

[KeyHeightLimit](#key_flightcontroller_heightlimit_inline)

###### final KeyHeightLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyHeightLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"HeightLimit", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set maximum flight altitude of the aircraft. Unit: meter. `KeyHeightLimitRange` can be used to get setting range. `Supported since MSDK 5.0`

[KeyIsNearHeightLimit](#key_flightcontroller_isnearheightlimit_inline)

###### final KeyIsNearHeightLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsNearHeightLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsNearHeightLimit", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the aircraft has reached the maximum flight altitude. `Supported since MSDK 5.0`

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

**Parameter:** Boolean `true` means distance limitation switch is opened. After this switch is opened, `KeyDistanceLimit` can be used to set maximum flight distance of the aircraft. `Supported since MSDK 5.0`

[KeyDistanceLimitRange](#key_flightcontroller_distancelimitrange_inline)

###### final KeyDistanceLimitRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntMinMax> KeyDistanceLimitRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DistanceLimitRange", new DJIValueConverter<>(IntMinMax.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_IntValueConfig` To get diatance limitation setting range. Unit: meter. `Supported since MSDK 5.0`

[KeyDistanceLimit](#key_flightcontroller_distancelimit_inline)

###### final KeyDistanceLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyDistanceLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DistanceLimit", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set maximum flight distance of the aircraft. Unit: meter. This value is the distance between aircraft and home point. `KeyDistanceLimitRange` can be used to get the setting range. `Supported since MSDK 5.0`

[KeyIsNearDistanceLimit](#key_flightcontroller_isneardistancelimit_inline)

###### final KeyIsNearDistanceLimit

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsNearDistanceLimit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsNearDistanceLimit", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means the aircraft has reached the maximum flight distance. `Supported since MSDK 5.0`

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

**Parameter:** Boolean `true` means home point has been set. When GPS signal is good, aircraft will automatically set current position as home point. `KeyIsHomeLocationSet` will return `true`. `Supported since MSDK 5.0`

[KeyHomeLocation](#key_flightcontroller_homelocation_inline)

###### final KeyHomeLocation

|  |
| --- |
| ``` static final DJIKeyInfo<LocationCoordinate2D> KeyHomeLocation = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"HomeLocation", new DJIValueConverter<>(LocationCoordinate2D.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_LocationCoordinate2D` To set and get current home point location. When GPS signal is good, aircraft will automatically set current position as home point. This key can also be used to change home point location. `Supported since MSDK 5.0`

[KeyGoHomeHeight](#key_flightcontroller_gohomeheight_inline)

###### final KeyGoHomeHeight

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyGoHomeHeight = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GoHomeHeight", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set and get relative altitude when returning home. Unit: meter. This value is related to the altitude when taking off. `KeyGoHomeHeightRange` can be used to get return-to-home height setting range. If the horizontal distance between aircraft and home point is within 50 meters, the aircraft will ignore the set return-to-home altitude and return at current altitude. If the forward vision system does no work properly, the aircraft will climb to the set altitude and return. `Supported since MSDK 5.0`

[KeyGoHomeHeightRange](#key_flightcontroller_gohomeheightrange_inline)

###### final KeyGoHomeHeightRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntMinMax> KeyGoHomeHeightRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GoHomeHeightRange", new DJIValueConverter<>(IntMinMax.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_IntValueConfig` To get the setting range of the return-to-home altitude. Unit: meter. `Supported since MSDK 5.0`

[KeyHomeLocationUsingCurrentAircraftLocation](#key_flightcontroller_homelocationusingcurrentaircraftlocation_inline)

###### final KeyHomeLocationUsingCurrentAircraftLocation

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyHomeLocationUsingCurrentAircraftLocation = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"HomeLocationUsingCurrentAircraftLocation", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To set current aircraft location as home point location. `Supported since MSDK 5.0`

[KeyHomeLocationUsingCurrentRemoteControllerLocation](#key_flightcontroller_homelocationusingcurrentremotecontrollerlocation_inline)

###### final KeyHomeLocationUsingCurrentRemoteControllerLocation

|  |
| --- |
| ``` static final DJIActionKeyInfo<LocationCoordinate2D,EmptyMsg> KeyHomeLocationUsingCurrentRemoteControllerLocation = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"HomeLocationUsingCurrentRemoteControllerLocation", new DJIValueConverter<>(LocationCoordinate2D.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_LocationCoordinate2D` To set current remote controller location as home point location. `Supported since MSDK 5.0`

[KeyStartGoHome](#key_flightcontroller_startgohome_inline)

###### final KeyStartGoHome

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartGoHome = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartGoHome", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To start intelligent return-to-home. When the GPS signal is not good, intelligent return-to-home can not be started. Intelligent return-to-home can also be started by the samrt return-to-home button on the remote controller. During the return-to-home home process, the user can control the altitude of the aircraft with the throttle stick and control the speed of the aircraft with the pitch stick to avoid obstacles. During the return-to-home process, user can regain control of the aircraft after exiting intelligent return-to-home home via the intelligent return-to-home button or by calling `KeyStopGoHome`. `KeyGoHomeStatus` can be used to get intelligent return-to-home status. `Supported since MSDK 5.0`

[KeyStopGoHome](#key_flightcontroller_stopgohome_inline)

###### final KeyStopGoHome

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopGoHome = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopGoHome", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To stop intelligent return-to-home. `Supported since MSDK 5.0`

[KeyGoHomeStatus](#key_flightcontroller_gohomestate_inline)

###### final KeyGoHomeStatus

|  |
| --- |
| ``` static final DJIKeyInfo<GoHomeState> KeyGoHomeStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GoHomeStatus", new SingleValueConverter<>(GoHomeState.class,FCGoHomeStateMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("GoHomeState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GoHomeState` To get intelligent return-to-home status. `Supported since MSDK 5.0`

Low Battery Return-to-Home

[KeyLowBatteryRTHEnabled](#key_flightcontroller_smartbatteryrthenabled_inline)

###### final KeyLowBatteryRTHEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLowBatteryRTHEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LowBatteryRTHEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false).setInnerIdentifier("SmartBatteryRTHEnabled") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means intelligent low battery return-to-home function is opened. To avoid unneccsary dangerous due to the low battery, aircraft will intelligently judge whether the current battery is sufficient according to the flight position information. If current battery power is only enough to complete the return-to-home process, MSDK will prompt whether operate the return-to-home process. If user does not make a selection within 10s, MSDK will automatically return-to-home after 10s. During the returning process, you can short press the smart return-to-home button on the remote control to cancel the return-to-home process. Smart low battery return-to-home only occurs once during the one flight. If the user cancels the low-battery return-to-home reminder and continues to fly, the aircraft may be forced to land due to insufficient power when returning, resulting in the aircraft being lost or crashed. For security reasons, it is not recommended to turn this function off. `Supported since MSDK 5.0`

[KeyLowBatteryRTHInfo](#key_flightcontroller_gohomeassessment_inline)

###### final KeyLowBatteryRTHInfo

|  |
| --- |
| ``` static final DJIKeyInfo<LowBatteryRTHInfo> KeyLowBatteryRTHInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LowBatteryRTHInfo", new DJIValueConverter<>(LowBatteryRTHInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("GoHomeAssessment") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LowBatteryRTHInfo` To get related information of the intelligent low battery power. This function is only valid when `KeyLowBatteryRTHEnabled` is enabled. `Supported since MSDK 5.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found