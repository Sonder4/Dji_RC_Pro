**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [IMUCalibrationInfo](Value_FlightController_Struct_IMUCalibrationHint.md)

---

# class IMUCalibrationInfo

|  |
| --- |
| ``` class IMUCalibrationInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

IMU calibraion information.

##### Class Members:

#### Members

method

[getCalibrationState](#value_flightcontroller_struct_imucalibrationhint_getcalibrationstate_inline)

###### method getCalibrationState

|  |
| --- |
| ``` IMUCalibrationState getCalibrationState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Get the IMU calibraion state.

##### Return:

|  |  |
| --- | --- |
| [IMUCalibrationState](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_imucalibrationstate) | *Return the IMU calibraion state.* |

method

[getOrientationCalibrationState](#value_flightcontroller_struct_imucalibrationhint_getorientationcalibrationstate_inline)

###### method getOrientationCalibrationState

|  |
| --- |
| ``` IMUOrientationCalibrationState getOrientationCalibrationState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Get the calibration status of the current orientation.

##### Return:

|  |  |
| --- | --- |
| [IMUOrientationCalibrationState](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_imuorientationcalibrationstate) | *Return the calibration status of the current orientation.* |

method

[getOrientationsToCalibrate](#value_flightcontroller_struct_imucalibrationhint_getorientationstocalibrate_inline)

###### method getOrientationsToCalibrate

|  |
| --- |
| ``` List<IMUCalibrationOrientation> getOrientationsToCalibrate() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Get a list with the aircraft orientations that have not been calibrated yet.

##### Return:

|  |  |
| --- | --- |
| List<[IMUCalibrationOrientation](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_imucalibrationorientation)> | *Return a list with the aircraft orientations that have not been calibrated yet.* |

method

[getOrientationsCalibrated](#value_flightcontroller_struct_imucalibrationhint_getorientationscalibrated_inline)

###### method getOrientationsCalibrated

|  |
| --- |
| ``` List<IMUCalibrationOrientation> getOrientationsCalibrated() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Get a list with the aircraft orientations that have been calibrated.

##### Return:

|  |  |
| --- | --- |
| List<[IMUCalibrationOrientation](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_imucalibrationorientation)> | *Return a list with the aircraft orientations that have been calibrated.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found