# class DJIGimbalKey

|  |
| --- |
| ``` class DJIGimbalKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`DJIGimbalKey` provides a set of methods set and get gimbal data, including obtaining gimbal attitude data, controlling gimbal rotation and gimbal related parameters setting. `This class is supported since MSDK 5.0`

##### Class Members:

#### Basic Information

Connection Status

[KeyConnection](#key_gimbal_connection_inline)

###### final KeyConnection

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyConnection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Connection", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that gimbal is connected. `Supported since MSDK 5.0`

Serial Number

[KeySerialNumber](#key_gimbal_serialnumber_inline)

###### final KeySerialNumber

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeySerialNumber = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SerialNumber", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String Get gimbal serial number. `Supported since MSDK 5.0`

Firmware version Number

[KeyFirmwareVersion](#key_gimbal_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String Get Firmware Version Number. `Supported since MSDK 5.0`

Attitude Data

[KeyGimbalAttitudeRange](#key_gimbal_gimbalattituderange_inline)

###### final KeyGimbalAttitudeRange

|  |
| --- |
| ``` static final DJIKeyInfo<GimbalAttitudeRange> KeyGimbalAttitudeRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalAttitudeRange", new DJIValueConverter<>(GimbalAttitudeRange.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalAttitudeRange` Get gimbal attitude angle range. `Supported since MSDK 5.0`

[KeyGimbalAttitude](#key_gimbal_gimbalattitude_inline)

###### final KeyGimbalAttitude

|  |
| --- |
| ``` static final DJIKeyInfo<Attitude> KeyGimbalAttitude = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalAttitude", new DJIValueConverter<>(Attitude.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Attitude` Get gimbal attitude data. Parameter range can be obtained through `KeyGimbalAttitudeRange`. `Supported since MSDK 5.0`

Offset Value

[KeyFineTunePitchTotalDegree](#key_gimbal_pitchadjustdegree_inline)

###### final KeyFineTunePitchTotalDegree

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyFineTunePitchTotalDegree = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FineTunePitchTotalDegree", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PitchAdjustDegree") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get total offset value of gimbal pitch axis. Fine-tune can be done through `KeyFineTunePitchInDegrees`. `Supported since MSDK 5.0`

[KeyFineTuneYawTotalDegree](#key_gimbal_yawadjustdegree_inline)

###### final KeyFineTuneYawTotalDegree

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyFineTuneYawTotalDegree = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneYawTotalDegree", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("YawAdjustDegree") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get total offset value of gimbal yaw axis. Fine-tune can be done through `KeyFineTuneYawInDegrees`. `Supported since MSDK 5.0`

[KeyFineTuneRollTotalDegree](#key_gimbal_rolladjustdegree_inline)

###### final KeyFineTuneRollTotalDegree

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyFineTuneRollTotalDegree = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneRollTotalDegree", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RollAdjustDegree") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get total offset value of gimbal roll axis. Fine-tune can be done through `KeyFineTuneRollInDegrees`. `Supported since MSDK 5.0`

Mode

[KeyGimbalMode](#key_gimbal_gimbalmode_inline)

###### final KeyGimbalMode

|  |
| --- |
| ``` static final DJIKeyInfo<GimbalMode> KeyGimbalMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalMode", new SingleValueConverter<>(GimbalMode.class,GimbalModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalMode` Set gimbal mode. `Supported since MSDK 5.0`

Rotation

[KeyRotateByAngle](#key_gimbal_rotatebyangle_inline)

###### final KeyRotateByAngle

|  |
| --- |
| ``` static final DJIActionKeyInfo<GimbalAngleRotation,EmptyMsg> KeyRotateByAngle = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RotateByAngle", new DJIValueConverter<>(GimbalAngleRotation.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalAngleRotation` Rotate the pitch angle, roll angle and yaw angel of the gimbal in angle mode. Relative angle mode and absolute angle mode is supported. `Supported since MSDK 5.0`

[KeyRotateBySpeed](#key_gimbal_rotatebyspeed_inline)

###### final KeyRotateBySpeed

|  |
| --- |
| ``` static final DJIActionKeyInfo<GimbalSpeedRotation,EmptyMsg> KeyRotateBySpeed = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RotateBySpeed", new DJIValueConverter<>(GimbalSpeedRotation.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalSpeedRotation` Rotate the pitch angle, roll angle and yaw angel of the gimbal in speed mode. `Supported since MSDK 5.0`

Reset

[KeyGimbalReset](#key_gimbal_resetgimbal_inline)

###### final KeyGimbalReset

|  |
| --- |
| ``` static final DJIActionKeyInfo<GimbalResetType,EmptyMsg> KeyGimbalReset = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalReset", new SingleValueConverter<>(GimbalResetType.class,GimbalResetCommandMsg.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("ResetGimbal") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalResetType` Gimbal reset. The pitch axis, roll axis and yaw axis can be centered or moved down through setting `GimbalResetType`. `Supported since MSDK 5.0`

Fine-tune

[KeyFineTunePitchInDegrees](#key_gimbal_finetunepitchindegrees_inline)

###### final KeyFineTunePitchInDegrees

|  |
| --- |
| ``` static final DJIActionKeyInfo<Double,EmptyMsg> KeyFineTunePitchInDegrees = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"FineTunePitchInDegrees", SingleValueConverter.DoubleConverter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Gimbal pitch axis can be fine-tuned through custom offset value. If there still have small angle offset after gimbal centering back, this function can be used to gimbal leveling. Single Fine-tune range is [-2.0, 2.0], unit: degree. Total fine-tuned range is [-10.0, 10.0], unit:degree. If offset value is negative, the gimbal will fine-tune a specified number of degrees in the counterclockwise direction. Total offset value of fine-tuning can be obtained through `KeyFineTunePitchTotalDegree`. `Supported since MSDK 5.0`

[KeyFineTuneYawInDegrees](#key_gimbal_finetuneyawindegrees_inline)

###### final KeyFineTuneYawInDegrees

|  |
| --- |
| ``` static final DJIActionKeyInfo<Double,EmptyMsg> KeyFineTuneYawInDegrees = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneYawInDegrees", SingleValueConverter.DoubleConverter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Gimbal yaw axis can be fine-tuned through custom offset value. If there still have small angle offset after gimbal centering back, this function can be used to gimbal leveling. Single Fine-tune range is [-2.0, 2.0], unit: degree. Total fine-tuned range is [-10.0, 10.0], unit:degree. If offset value is negative, the gimbal will fine-tune a specified number of degrees in the counterclockwise direction. Total offset value of fine-tuning can be obtained through `KeyFineTuneYawTotalDegree`. `Supported since MSDK 5.0`

[KeyFineTuneRollInDegrees](#key_gimbal_finetunerollindegrees_inline)

###### final KeyFineTuneRollInDegrees

|  |
| --- |
| ``` static final DJIActionKeyInfo<Double,EmptyMsg> KeyFineTuneRollInDegrees = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneRollInDegrees", SingleValueConverter.DoubleConverter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Gimbal roll axis can be fine-tuned through custom offset value. If there still have small angle offset after gimbal centering back, this function can be used to gimbal leveling. Single Fine-tune range is [-2.0, 2.0], unit: degree. Total fine-tuned range is [-10.0, 10.0], unit:degree. If offset value is negative, the gimbal will fine-tune a specified number of degrees in the counterclockwise direction. Total offset value of fine-tuning can be obtained through `KeyFineTuneRollTotalDegree`. `Supported since MSDK 5.0`

Calibration

[KeyGimbalCalibrate](#key_gimbal_calibrategimbal_inline)

###### final KeyGimbalCalibrate

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyGimbalCalibrate = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalCalibrate", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("CalibrateGimbal") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start gimbal automatic calibration. Calibration status can be monitored through `KeyGimbalCalibrationStatus`. `Supported since MSDK 5.0`

[KeyGimbalCalibrationStatus](#key_gimbal_gimbalcalibrationstate_inline)

###### final KeyGimbalCalibrationStatus

|  |
| --- |
| ``` static final DJIKeyInfo<GimbalCalibrationStatusInfo> KeyGimbalCalibrationStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalCalibrationStatus", new DJIValueConverter<>(GimbalCalibrationStatusInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("GimbalCalibrationState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalCalibrationStatusInfo` Get gimbal calibration status and calibration progress. `Supported since MSDK 5.0`

Fatory Setting

[KeyRestoreFactorySettings](#key_gimbal_restorefactorysettings_inline)

###### final KeyRestoreFactorySettings

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyRestoreFactorySettings = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RestoreFactorySettings", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Restore gimbal parameter to fatory setting. `Supported since MSDK 5.0`

#### Basic setting

Pitch Limit Extension

[KeyPitchRangeExtensionEnabled](#key_gimbal_pitchrangeextensionenabled_inline)

###### final KeyPitchRangeExtensionEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyPitchRangeExtensionEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PitchRangeExtensionEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean Set to turn on or turn off the gimbal pitch limit extension. After turning on the pitch limit extension, when the gimbal moving downward, pitch axis can be controlled above the horizontal line. When the gimbal moving upward, pitch axis can be controlled below the horizontal line. `Supported since MSDK 5.0`

Maximum Speed Percentage

[KeyPitchControlMaxSpeed](#key_gimbal_pitchcontrollermaxspeed_inline)

###### final KeyPitchControlMaxSpeed

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyPitchControlMaxSpeed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PitchControlMaxSpeed", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PitchControllerMaxSpeed") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set maximum spped of gimbal pitch control. This value is a percentage. The larger the value, the faster the speed. 100% means that gimbal is controlled by maximum physical speed it can reached. `Supported since MSDK 5.0`

#### Basic Setting

Maximum Speed Percentage

[KeyYawControlMaxSpeed](#key_gimbal_yawcontrollermaxspeed_inline)

###### final KeyYawControlMaxSpeed

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyYawControlMaxSpeed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"YawControlMaxSpeed", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("YawControllerMaxSpeed") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set maximum spped of gimbal yaw control. This value is a percentage. The larger the value, the faster the speed. 100% means that gimbal is controlled by maximum physical speed it can reached. `Supported since MSDK 5.0`

Slow Start/Stop

[KeyPitchSmoothingFactor](#key_gimbal_pitchsmoothingfactor_inline)

###### final KeyPitchSmoothingFactor

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyPitchSmoothingFactor = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PitchSmoothingFactor", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set gimbal to slow start/stop in pitch direction. The range is [0,30]. The larger the value, the longer the buffer distance of controlling gimbal pitch axis to start/stop turning. `Supported since MSDK 5.0`

[KeyYawSmoothingFactor](#key_gimbal_yawsmoothingfactor_inline)

###### final KeyYawSmoothingFactor

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyYawSmoothingFactor = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"YawSmoothingFactor", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set gimbal to slow start/stop in yaw direction. The range is [0,30]. The larger the value, the longer the buffer distance of controlling gimbal pitch axis to start/stop turning. `Supported since MSDK 5.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found