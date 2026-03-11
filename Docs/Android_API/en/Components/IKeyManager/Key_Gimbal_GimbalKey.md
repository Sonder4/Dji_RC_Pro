**Navigation:** [IKeyManager](IKeyManager.md) > [DJIKey<T>](DJIKey.md) > [GimbalKey](Key_Gimbal_GimbalKey.md)

---

# class GimbalKey

|  |
| --- |
| ``` @Keep  class GimbalKey extends DJIGimbalKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |
| *Inherits From:* | `DJIGimbalKey` |

##### Description:

`GimbalKey` provides a set of methods to set and get gimbal data, including obtaining the gimbal attitude data, controlling the gimbal rotation, and setting the related parameters of the gimbal. `Supported since MSDK 5.0.0`

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

**Parameter:** Boolean `true` means that gimbal is connected. `Supported since MSDK 5.0.0`

Firmware version

[KeyFirmwareVersion](#key_gimbal_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String Get Firmware Version. `Supported since MSDK 5.0.0`

Attitude Data

[KeyGimbalAttitude](#key_gimbal_gimbalattitude_inline)

###### final KeyGimbalAttitude

|  |
| --- |
| ``` static final DJIKeyInfo<Attitude> KeyGimbalAttitude = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalAttitude", new DJIValueConverter<>(Attitude.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Attitude` Get gimbal attitude data. The yaw angle uses the north east down coordinate system. If you need to get the yaw angle of the gimbal relative to the nose of the aircraft, please call `KeyYawRelativeToAircraftHeading`. `Supported since MSDK 5.0.0`

[KeyYawRelativeToAircraftHeading](#key_gimbal_yawrelativetobodyheading_inline)

###### final KeyYawRelativeToAircraftHeading

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyYawRelativeToAircraftHeading = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"YawRelativeToAircraftHeading", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("YawRelativeToBodyHeading") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Double Get the yaw angle of the gimbal relative to the nose of the aircraft. `Supported since MSDK 5.0.0`

#### Basic Operation

Mode

[KeyGimbalCMode](#key_gimbal_gimbalmode_inline)

###### final KeyGimbalCMode

|  |
| --- |
| ``` static final DJIKeyInfo<GimbalMode> KeyGimbalCMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalCMode", new SingleValueConverter<>(GimbalMode.class,GimbalModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("GimbalMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalMode` Set gimbal mode. `Supported since MSDK 5.0.0`

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

**Parameter:** `GimbalAngleRotation` Rotate the pitch angle, roll angle and yaw angle of the gimbal in angle mode. Relative angle mode and absolute angle mode are supported. **Note: Different gimbal payloads have different controllable angles. The following provides a method to obtain the maximum and minimum limit angles of the gimbal: Please use the remote controller's gimbal dial to control the pitch, roll and yaw of gimbal to the maximum and minimum limit, and then call `KeyGimbalAttitude` to get the adjustable limit angle of the gimbal.** `Supported since MSDK 5.0.0`

[KeyRotateBySpeed](#key_gimbal_rotatebyspeed_inline)

###### final KeyRotateBySpeed

|  |
| --- |
| ``` static final DJIActionKeyInfo<GimbalSpeedRotation,EmptyMsg> KeyRotateBySpeed = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RotateBySpeed", new DJIValueConverter<>(GimbalSpeedRotation.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalSpeedRotation` Rotate the pitch angle, roll angle and yaw angle of the gimbal in speed mode. Unit: 0.1 degree/second, range: [-359.9, 359.9]. The greater value you set, the greater the rotating speed. Both the frequency of this method call and the signal strength of the airlink will influence the final gimbal attitude which may cause some offset. `Supported since MSDK 5.0.0`

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

**Parameter:** `GimbalResetType` Gimbal reset. The pitch axis, roll axis and yaw axis can be centered or moved down through the setting `GimbalResetType`. `Supported since MSDK 5.0.0`

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

**Parameter:** Double Gimbal pitch axis can be fine-tuned through custom offset value. If there still have small angle offset after gimbal centering back, this function can be used to gimbal leveling. Single Fine-tune range is [-2.0, 2.0], unit: degree. Total fine-tuned range is [-10.0, 10.0], unit:degree. If the offset value is negative, the gimbal will fine-tune a specified number of degrees in the counterclockwise direction. The total offset value of fine-tuning can be obtained through `KeyFineTunePitchTotalDegree`. `Supported since MSDK 5.0.0`

[KeyFineTuneYawInDegrees](#key_gimbal_finetuneyawindegrees_inline)

###### final KeyFineTuneYawInDegrees

|  |
| --- |
| ``` static final DJIActionKeyInfo<Double,EmptyMsg> KeyFineTuneYawInDegrees = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneYawInDegrees", SingleValueConverter.DoubleConverter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Gimbal yaw axis can be fine-tuned through custom offset value. If there still have small angle offset after gimbal centering back, this function can be used to gimbal leveling. Single Fine-tune range is [-2.0, 2.0], unit: degree. Total fine-tuned range is [-10.0, 10.0], unit:degree. If offset value is negative, the gimbal will fine-tune a specified number of degrees in the counterclockwise direction. Total offset value of fine-tuning can be obtained through `KeyFineTuneYawTotalDegree`. `Supported since MSDK 5.0.0`

[KeyFineTuneRollInDegrees](#key_gimbal_finetunerollindegrees_inline)

###### final KeyFineTuneRollInDegrees

|  |
| --- |
| ``` static final DJIActionKeyInfo<Double,EmptyMsg> KeyFineTuneRollInDegrees = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneRollInDegrees", SingleValueConverter.DoubleConverter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Gimbal roll axis can be fine-tuned through custom offset value. If there still have small angle offset after gimbal centering back, this function can be used to gimbal leveling. Single Fine-tune range is [-2.0, 2.0], unit: degree. Total fine-tuned range is [-10.0, 10.0], unit:degree. If offset value is negative, the gimbal will fine-tune a specified number of degrees in the counterclockwise direction. Total offset value of fine-tuning can be obtained through `KeyFineTuneRollTotalDegree`. `Supported since MSDK 5.0.0`

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

**Parameter:** Double Get total offset value of gimbal pitch axis. Fine-tune can be done through `KeyFineTunePitchInDegrees`. `Supported since MSDK 5.0.0`

[KeyFineTuneYawTotalDegree](#key_gimbal_yawadjustdegree_inline)

###### final KeyFineTuneYawTotalDegree

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyFineTuneYawTotalDegree = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneYawTotalDegree", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("YawAdjustDegree") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get total offset value of gimbal yaw axis. Fine-tune can be done through `KeyFineTuneYawInDegrees`. `Supported since MSDK 5.0.0`

[KeyFineTuneRollTotalDegree](#key_gimbal_rolladjustdegree_inline)

###### final KeyFineTuneRollTotalDegree

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyFineTuneRollTotalDegree = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FineTuneRollTotalDegree", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RollAdjustDegree") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get total offset value of gimbal roll axis. Fine-tune can be done through `KeyFineTuneRollInDegrees`. `Supported since MSDK 5.0.0`

Searchlight AL1

[KeyLightGimbalCalibrate](#key_gimbalkey_keylightgimbalcalibrate_inline)

###### final KeyLightGimbalCalibrate

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg, EmptyMsg> KeyLightGimbalCalibrate = new KeyLightGimbalCalibrate().canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start automatic calibration of the searchlight gimbal. You can monitor the calibration status through `KeyLightGimbalCalibrationStatus`. Only supports Searchlight AL1. `Supported since MSDK 5.12.0`

[KeyLightGimbalCalibrationStatus](#key_gimbalkey_keylightgimbalcalibrationstatus_inline)

###### final KeyLightGimbalCalibrationStatus

|  |
| --- |
| ``` static final DJIKeyInfo<GimbalCalibrationStatusInfo> KeyLightGimbalCalibrationStatus = new KeyLightGimbalCalibrationStatus().canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalCalibrationStatusInfo` Get the searchlight gimbal calibration status and progress. The searchlight gimbal calibration status and progress can be obtained only after calling `KeyLightGimbalCalibrate` to start the searchlight gimbal calibration. `Supported since MSDK 5.12.0`

[KeyLightGimbalFineTunePitchInDegrees](#key_gimbalkey_keylightgimbalfinetunepitchindegrees_inline)

###### final KeyLightGimbalFineTunePitchInDegrees

|  |
| --- |
| ``` static final DJIActionKeyInfo<LightGimbalAdjustInfo, EmptyMsg> KeyLightGimbalFineTunePitchInDegrees = new KeyLightGimbalFineTunePitchInDegrees().canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LightGimbalAdjustInfo` The searchlight gimbal pitch axis can be fine-tuned by a custom offset. When the searchlight gimbal still has a small angle offset after returning to the center, this function can be used to level the gimbal. The single fine-tuning range is [-2.0, 2.0], unit: degree, and the total fine-tuning range is [-10.0, 10.0], unit: degree. If the offset is a negative number, the searchlight gimbal will fine-tune the specified degree in the counterclockwise direction. The total fine-tuning offset can be obtained through `KeyLightGimbalPitchTotalInDegrees`. `Supported since MSDK 5.12.0`

[KeyLightGimbalPitchTotalInDegrees](#key_gimbalkey_keylightgimbalpitchtotalindegrees_inline)

###### final KeyLightGimbalPitchTotalInDegrees

|  |
| --- |
| ``` static final DJIKeyInfo<LightGimbalTotalAdjustInfo> KeyLightGimbalPitchTotalInDegrees = new KeyLightGimbalPitchTotalInDegrees().canGet(true).canSet(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LightGimbalTotalAdjustInfo` Get the total offset of the searchlight gimbal pitch axis. Fine-tune it via `KeyLightGimbalFineTunePitchInDegrees`. `Supported since MSDK 5.12.0`

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

Start gimbal automatic calibration. Calibration status can be monitored through `KeyGimbalCalibrationStatus`. `Supported since MSDK 5.0.0`

[KeyGimbalCalibrationStatus](#key_gimbal_gimbalcalibrationstate_inline)

###### final KeyGimbalCalibrationStatus

|  |
| --- |
| ``` static final DJIKeyInfo<GimbalCalibrationStatusInfo> KeyGimbalCalibrationStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalCalibrationStatus", new DJIValueConverter<>(GimbalCalibrationStatusInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("GimbalCalibrationState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `GimbalCalibrationStatusInfo` Get gimbal calibration status and calibration progress after you called `KeyGimbalCalibrate` to start gimbal calibration. `Supported since MSDK 5.0.0`

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

Restore gimbal parameter to fatory setting. `Supported since MSDK 5.0.0`

#### Basic Setting

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

**Parameter:** Boolean Set to enable or disable the pitch range extension of gimbal. Taking M30 series for example, their gimbal is mounted under the drone. When the pitch range extension of gimbal is disabled, the pitch range is [45, -90]. When the pitch range extension of gimbal is enabled, the range extends to [45, -120]. It means when the pitch range extension of gimbal is enabled and the gimbal pitch axis is moving downward, the angle can reach -120 degrees. `Supported since MSDK 5.0.0`

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

**Parameter:** Integer Set maximum spped of gimbal pitch control. This value is a percentage, the range is [1,100]. The larger the value, the faster the speed. 100% means that gimbal is controlled by maximum physical speed it can reached. `Supported since MSDK 5.0.0`

[KeyYawControlMaxSpeed](#key_gimbal_yawcontrollermaxspeed_inline)

###### final KeyYawControlMaxSpeed

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyYawControlMaxSpeed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"YawControlMaxSpeed", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("YawControllerMaxSpeed") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set maximum spped of gimbal yaw control. This value is a percentage, the range is [1,100]. The larger the value, the faster the speed. 100% means that gimbal is controlled by maximum physical speed it can reached. `Supported since MSDK 5.0.0`

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

**Parameter:** Integer Set gimbal to slow start/stop in pitch direction. The range is [0,30]. The larger the value, the longer the buffer distance of controlling gimbal pitch axis to start/stop turning. `Supported since MSDK 5.0.0`

[KeyYawSmoothingFactor](#key_gimbal_yawsmoothingfactor_inline)

###### final KeyYawSmoothingFactor

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyYawSmoothingFactor = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"YawSmoothingFactor", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set gimbal to slow start/stop in yaw direction. The range is [0,30]. The larger the value, the longer the buffer distance of controlling gimbal pitch axis to start/stop turning. `Supported since MSDK 5.0.0`

Vertical Shot

[KeyGimbalVerticalShotEnabled](#key_gimbal_gimbalverticalshotenabled_inline)

###### final KeyGimbalVerticalShotEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyGimbalVerticalShotEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GimbalVerticalShotEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean Set to enable or disable vertical shooting. Typically used in consumer aircraft. `Supported since MSDK 5.3.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found