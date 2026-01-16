**Navigation:** [IKeyManager](IKeyManager.md) > [DJIKey<T>](DJIKey.md) > [RemoteControllerKey](Key_RemoteController_RemoteControllerKey.md)

---

# class RemoteControllerKey

|  |
| --- |
| ``` @Keep  class RemoteControllerKey extends DJIRemoteControllerKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |
| *Inherits From:* | `DJIRemoteControllerKey` |

##### Description:

`RemoteControllerKey` provides methods to set and get data from Remote Controller. Remote Controller has sticks, buttons, wheels, GPS, batteries and output ports for video. The mobile device can be connected to Remote Controller, and Remote Controller will send all information coming from the aircraft to the mobile device. `This class is supported since MSDK 5.0`

##### Class Members:

#### Basic Information

Connected Status

[KeyConnection](#key_remotecontroller_connection_inline)

###### final KeyConnection

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyConnection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Connection", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` represents remote controller is connected. `Supported since MSDK 5.0.0`

Type

[KeyRemoteControllerType](#key_remotecontroller_remotecontrollertype_inline)

###### final KeyRemoteControllerType

|  |
| --- |
| ``` static final DJIKeyInfo<RemoteControllerType> KeyRemoteControllerType = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RemoteControllerType", new SingleValueConverter<>(RemoteControllerType.class,RemoteControllerTypeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`RemoteControllerType` To get remote controller type. `Supported since MSDK 5.0.0`

Control Mode

[KeyControlMode](#key_remotecontroller_controlmode_inline)

###### final KeyControlMode

|  |
| --- |
| ``` static final DJIKeyInfo<ControlMode> KeyControlMode = new KeyControlMode()            .canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`ControlMode` To get remote controller control mode. When calling the get method for the first time, you need to use the asynchronous method `getValue` to get the value. `Supported since MSDK 5.0.0`

Battery Power Information

[KeyBatteryInfo](#key_remotecontroller_rcparamchargeremaining_inline)

###### final KeyBatteryInfo

|  |
| --- |
| ``` static final DJIKeyInfo<BatteryInfo> KeyBatteryInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"BatteryInfo", new DJIValueConverter<>(BatteryInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RcParamChargeRemaining") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`BatteryInfo` To get remote controller battery power information. `Supported since MSDK 5.0.0`

[KeySecondBatteryInfo](#key_remotecontroller_secondrcparamchargeremaining_inline)

###### final KeySecondBatteryInfo

|  |
| --- |
| ``` static final DJIKeyInfo<BatteryInfo> KeySecondBatteryInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SecondBatteryInfo", new DJIValueConverter<>(BatteryInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("SecondRcParamChargeRemaining") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`BatteryInfo` To get remote controller external battery power information. `Supported since MSDK 5.0.0`

Serial Number

[KeySerialNumber](#key_remotecontroller_serialnumber_inline)

###### final KeySerialNumber

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeySerialNumber = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SerialNumber", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**String To get remote controller serial number. `Supported since MSDK 5.0.0`

Firmware Version

[KeyFirmwareVersion](#key_remotecontroller_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**String To get the remote controller firmware version. `Supported since MSDK 5.0.0`

#### Physical Button Information Monitor

Left stick

[KeyStickLeftVertical](#key_remotecontroller_rcstickleftvertical_inline)

###### final KeyStickLeftVertical

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyStickLeftVertical = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"StickLeftVertical", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCStickLeftVertical") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the remote controller left stick vertical offset Value. The value range is [-660,660]. Move the stick to the buttom, the offset value is -660. Move the stick to the top, the offset value is 660. `Supported since MSDK 5.0.0`

Left Stick

[KeyStickLeftHorizontal](#key_remotecontroller_rcsticklefthorizontal_inline)

###### final KeyStickLeftHorizontal

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyStickLeftHorizontal = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"StickLeftHorizontal", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCStickLeftHorizontal") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the remote controller left stick horizontal offset Value. The value range is [-660,660]. Move the stick to the far left, the offset value is -660. Move the stick to the far right, the offset value is 660. `Supported since MSDK 5.0.0`

Right Stick

[KeyStickRightVertical](#key_remotecontroller_rcstickrightvertical_inline)

###### final KeyStickRightVertical

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyStickRightVertical = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"StickRightVertical", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCStickRightVertical") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the remote controller right stick vertical offset Value. The value range is [-660,660]. Move the stick to the buttom, the offset value is -660. Move the stick to the top, the offset value is 660. `Supported since MSDK 5.0.0`

[KeyStickRightHorizontal](#key_remotecontroller_rcstickrighthorizontal_inline)

###### final KeyStickRightHorizontal

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyStickRightHorizontal = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"StickRightHorizontal", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCStickRightHorizontal") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the remote controller right stick horizontal offset Value. The value range is [-660,660]. Move the stick to the far left, the offset value is -660. Move the stick to the far right, the offset value is 660. `Supported since MSDK 5.0.0`

Shutter Button

[KeyShutterButtonDown](#key_remotecontroller_rcshutterbuttondown_inline)

###### final KeyShutterButtonDown

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyShutterButtonDown = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ShutterButtonDown", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCShutterButtonDown") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means shutter button is pressed. The camera will switch to shoot mode or take photo.

Record Button

[KeyRecordButtonDown](#key_remotecontroller_rcrecordbuttondown_inline)

###### final KeyRecordButtonDown

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyRecordButtonDown = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RecordButtonDown", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCRecordButtonDown") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means record button is pressed. The camera will switch to record mode or start/stop recording. `Supported since MSDK 5.0.0`

Return Button

[KeyGoHomeButtonDown](#key_remotecontroller_rcgohomebuttondown_inline)

###### final KeyGoHomeButtonDown

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyGoHomeButtonDown = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"GoHomeButtonDown", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCGohomeButtonDown") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means return button is pressed. Aircraft will start returning or stop returning。 `Supported since MSDK 5.0.0`

Emergency Stop Button

[KeyPauseButtonDown](#key_remotecontroller_rcpausebuttondown_inline)

###### final KeyPauseButtonDown

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyPauseButtonDown = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PauseButtonDown", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCPauseButtonDown") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means emergency stop button is pressed. In Matrice 30 Series and Matrice 300 RTK, if emergency stop button is pressed, aircraft will stop intelligent flight. `Supported since MSDK 5.0.0`

Shoot Photo And Record Button

[KeyRCSwitchButtonDown](#key_remotecontroller_rcswitchbuttondown_inline)

###### final KeyRCSwitchButtonDown

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyRCSwitchButtonDown = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RCSwitchButtonDown", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means press the shooting and video switching button. `Supported since MSDK 5.3.0`

Left Dial

[KeyLeftDial](#key_remotecontroller_rcleftwheel_inline)

###### final KeyLeftDial

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyLeftDial = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LeftDial", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCLeftWheel") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get remote controller left dial offset value. The value range is [-660,660]. The default value is 0. Turn the wheel to the far left, the value is -660. Turn the wheel to the far right, the value is 660. Left dial is usually used to control the pitch behavior of the gimbal. `Supported since MSDK 5.0.0`

Right Dial

[KeyRightDial](#key_remotecontroller_rcrightwheel_inline)

###### final KeyRightDial

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyRightDial = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RightDial", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCRightWheel") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get remote controller right dial offset value. The value range is [-660,660]. The default value is 0. Turn the wheel to the far left, the value is -660. Turn the wheel to the far right, the value is 660. Right dial is usually used to control the yaw behavior of the gimbal. `Supported since MSDK 5.0.0`

Scroll Wheel

[KeyScrollWheel](#key_remotecontroller_rcrightnewwheel_inline)

###### final KeyScrollWheel

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyScrollWheel = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ScrollWheel", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCRightNewWheel") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the rolling amount of the remote controller scroll wheel. For M30 series remote controller DJI RC plus, it is used to monitor the rolling amount of the scroll wheel behind the remote controller, which is usually used to control the zoom of the camera lens. `Supported since MSDK 5.1.0`

Five Dimension Button

[KeyFiveDimensionPressedStatus](#key_remotecontroller_rcfivedimensionpressedstatus_inline)

###### final KeyFiveDimensionPressedStatus

|  |
| --- |
| ``` static final DJIKeyInfo<FiveDimensionPressedStatus> KeyFiveDimensionPressedStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FiveDimensionPressedStatus", new DJIValueConverter<>(FiveDimensionPressedStatus.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RcFiveDimensionPressedStatus") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`FiveDimensionPressedStatus` To get remote controller five dimenion button status. `Supported since MSDK 5.0.0`

Custom Button

[KeyCustomButton1Down](#key_remotecontroller_rccustombutton1down_inline)

###### final KeyCustomButton1Down

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCustomButton1Down = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomButton1Down", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCCustomButton1Down") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means Custom Button C1 is pressed. Custom Button C1 is used for gimbal returning center by default, and it can be custom configured. `Supported since MSDK 5.0.0`

[KeyCustomButton2Down](#key_remotecontroller_rccustombutton2down_inline)

###### final KeyCustomButton2Down

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCustomButton2Down = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomButton2Down", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCCustomButton2Down") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means Custom Button C2 is pressed. Custom Button C2 is used for main and auxiliary screen switching by default, and it can be custom configured. `Supported since MSDK 5.0.0`

[KeyCustomButton3Down](#key_remotecontroller_rccustombutton3down_inline)

###### final KeyCustomButton3Down

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCustomButton3Down = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomButton3Down", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCCustomButton3Down") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means Custom Button C3 is pressed. Custom Button C3 can be custom configured. `Supported since MSDK 5.0.0`

#### Basic Operation

Pairing

[KeyRcFirmwareInfo](#key_remotecontroller_rcfirmwareinfo_inline)

###### final KeyRcFirmwareInfo

|  |
| --- |
| ``` static final DJIKeyInfo<RcFirmwareInfo> KeyRcFirmwareInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RcFirmwareInfo", new DJIValueConverter<>(RcFirmwareInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Get the current firmware information of the remote controller. **Note: This Key only supports DJI RC Plus remote controller and DJI RC N1 remote controller, and is used to obtain remote controller firmware information and switch firmware type.** `Supported since MSDK 5.4.0`

[KeySwitchRcFirmware](#key_remotecontroller_switchrcfirmware_inline)

###### final KeySwitchRcFirmware

|  |
| --- |
| ``` static final DJIActionKeyInfo<RcFirmwareType,EmptyMsg> KeySwitchRcFirmware = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"SwitchRcFirmware", new SingleValueConverter<>(RcFirmwareType.class,RcFirmwareMsg.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Switch the remote controller firmware type. Take DJI RC Plus remote controller as an example: DJI RC Plus remote controller can be used for M30 series, M300 RTK and M350 RTK. You can call this interface to switch the firmware of different aircraft, and then call `KeyRequestPairing` for pairing. `Supported since MSDK 5.4.0`

[KeyRequestPairing](#key_remotecontroller_rcrequestpairing_inline)

###### final KeyRequestPairing

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyRequestPairing = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RequestPairing", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("RcRequestPairing") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start the remote controller pairing operation, then the remote controller will start pairing with the aircraft. When the remote controller cannot be connected to the aircraft or the remote controller is replaced, the remote controller can be connected to the aircraft through pairing operation. **Note: DJI RC Plus remote controller and DJI RC N1 remote controller support multiple aircraft types. Please first call `KeyRcFirmwareInfo` to get the firmware type of the current remote controller. If the firmware type is inconsistent with the type of aircraft that needs to be paired, please call `KeySwitchRcFirmware` to switch the firmware of the remote controller to the type that needs to be paired before performing the pairing operation.** `Supported since MSDK 5.4.0`

[KeyStopPairing](#key_remotecontroller_rcstoppairing_inline)

###### final KeyStopPairing

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopPairing = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopPairing", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("RcStopPairing") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Stop the remote controller pairing operation. `Supported since MSDK 5.0.0`

[KeyPairingStatus](#key_remotecontroller_rcpairingstate_inline)

###### final KeyPairingStatus

|  |
| --- |
| ``` static final DJIKeyInfo<PairingState> KeyPairingStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PairingStatus", new SingleValueConverter<>(PairingState.class,RcPairingStateMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RcPairingState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PairingState` To get remote controller pairing status. `Supported since MSDK 5.0.0`

Gimbal Control

[KeyControllingGimbal](#key_remotecontroller_controllinggimbal_inline)

###### final KeyControllingGimbal

|  |
| --- |
| ``` static final DJIKeyInfo<ComponentIndexType> KeyControllingGimbal = new KeyControllingGimbal()            .canGet(false).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`ComponentIndexType` Set the gimbal and camera that the remote controller needs to control. After the aircraft is powered on, the remote controller defaults to controlling the main (left) gimbal and camera. You can set the remote controller to control the right gimbal and camera, or the up gimbal and camera through this key. **Note: Supported Aircraft: M300 RTK and M350 RTK.** `Supported since MSDK 5.7.0`

Reboot

[KeyRebootDevice](#key_remotecontroller_rebootdevice_inline)

###### final KeyRebootDevice

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyRebootDevice = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RebootDevice", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Reboot the remote controller. `Supported since MSDK 5.1.0`

#### Dual Control

whether dual control is supported

[KeyMultiControlIsSupported](#key_remotecontroller_ismasterslavemodev4supported_inline)

###### final KeyMultiControlIsSupported

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyMultiControlIsSupported = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlIsSupported", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IsMasterSlaveModeV4Supported") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means dual control is supported. `Supported since MSDK 5.0.0`

Dual Control Pairing

[KeyMultiControlRequestPairing](#key_remotecontroller_startmodepairing_inline)

###### final KeyMultiControlRequestPairing

|  |
| --- |
| ``` static final DJIActionKeyInfo<MultiControlChannel,EmptyMsg> KeyMultiControlRequestPairing = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlRequestPairing", new SingleValueConverter<>(MultiControlChannel.class,MultiControlChannelInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("StartModePairing") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlChannel` Start dual control pairing. The remote controller can be switched to A controller or B controller for pairing with the aircraft. **Note: Call this interface to pair the remote controller to `CHANNEL_B`, `IRTKCenter` will not be available.** `Supported since MSDK 5.0.0`

[KeyMultiControlChannel](#key_remotecontroller_multicontrolchannel_inline)

###### final KeyMultiControlChannel

|  |
| --- |
| ``` static final DJIKeyInfo<MultiControlChannel> KeyMultiControlChannel = new KeyMultiControlChannel()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `MultiControlChannel` Get the muti-control channel of currently connected remote controller. `Supported since MSDK 5.0.0`

Online Remote Controller Number

[KeyMultiControlOnlineRcCount](#key_remotecontroller_multirconlinerccount_inline)

###### final KeyMultiControlOnlineRcCount

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyMultiControlOnlineRcCount = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlOnlineRcCount", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRcOnlineRCCount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer get the number of currently connected remote controller. `Supported since MSDK 5.0.0`

Online App Number

[KeyMultiControlOnlineAppCount](#key_remotecontroller_multirconlineappcount_inline)

###### final KeyMultiControlOnlineAppCount

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyMultiControlOnlineAppCount = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlOnlineAppCount", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRcOnlineAPPCount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer get the number of currently connected App. NOTICE: The App mentioned here includes DJI's official Apps and Third-party Apps developed through DJI MSDK. `Supported since MSDK 5.0.0`

Dual Control Connection Status

[KeyMultiControlStatus](#key_remotecontroller_rcmultistatus_inline)

###### final KeyMultiControlStatus

|  |
| --- |
| ``` static final DJIKeyInfo<MultiControlStatusInfo> KeyMultiControlStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlStatus", new DJIValueConverter<>(MultiControlStatusInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RcMultiStatus") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlStatusInfo` To get dual controller connection status. This interface can be used to check the online status of A controller, the Apps in A controller, B controller and the Apps in B controller. `Supported since MSDK 5.0.0`

Dual Control Control Right

[KeyMultiControlFlightControlAuthorityOwner](#key_remotecontroller_multircflightcontrolauthowner_inline)

###### final KeyMultiControlFlightControlAuthorityOwner

|  |
| --- |
| ``` static final DJIKeyInfo<MultiControlFlightControlAuthorityOwnerInfo> KeyMultiControlFlightControlAuthorityOwner = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlFlightControlAuthorityOwner", new DJIValueConverter<>(MultiControlFlightControlAuthorityOwnerInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRCFlightControlAuthOwner") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlFlightControlAuthorityOwnerInfo` To get the current flight control whether it is in control A or control B. `Supported since MSDK 5.0.0`

[KeyMultiControlGimbalAuthorityOwner](#key_remotecontroller_multircgimbalscontrolauthowner_inline)

###### final KeyMultiControlGimbalAuthorityOwner

|  |
| --- |
| ``` static final DJIKeyInfo<List<MultiControlChannelInfo>> KeyMultiControlGimbalAuthorityOwner = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlGimbalAuthorityOwner", new SingleValueConverter<>((Class)List.class,MultiRcGimbalsControlAuthOwnerMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRcGimbalsControlAuthOwner") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**List<`MultiControlChannelInfo`> To get the current gimbal control whether it is in control A or control B. `Supported since MSDK 5.0.0`

[KeyMultiControlAuthorityObtain](#key_remotecontroller_multirccontrolauthoritysurpass_inline)

###### final KeyMultiControlAuthorityObtain

|  |
| --- |
| ``` static final DJIActionKeyInfo<List<MultiControlAuthorityType>,EmptyMsg> KeyMultiControlAuthorityObtain = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlAuthorityObtain", new SingleValueConverter<>((Class)List.class,RCAuthorityModes.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("MultiRcControlAuthoritySurpass") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**List<`MultiControlAuthorityType`> Grab control of flight or gimbal. If a remote controller can only control the gimbal, it can use control stocks. If a user has both aircraft flight control and gimbal control, he/she can use control sticks to control the orientation of the aircraft, and gimbal dial to control the gimbal. `Supported since MSDK 5.0.0`

[KeyMultiControlLockAuthority](#key_remotecontroller_multirccontrollockright_inline)

###### final KeyMultiControlLockAuthority

|  |
| --- |
| ``` static final DJIActionKeyInfo<MultiControlLockAuthorityInfo,EmptyMsg> KeyMultiControlLockAuthority = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlLockAuthority", new DJIValueConverter<>(MultiControlLockAuthorityInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("MultiRcControlLockRight") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlLockAuthorityInfo` Lock control of flight or gimbal. Control will not be grabbed after being locked. `Supported since MSDK 5.0.0`

[KeyMultiControlFlightControlAuthorityLockStatus](#key_remotecontroller_multircflightcontrolauthlockstate_inline)

###### final KeyMultiControlFlightControlAuthorityLockStatus

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyMultiControlFlightControlAuthorityLockStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlFlightControlAuthorityLockStatus", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRCFlightControlAuthLockState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean Return whether the flight control is locked. `Supported since MSDK 5.0.0`

[KeyMultiControlLostControlNotice](#key_remotecontroller_multirclostnotice_inline)

###### final KeyMultiControlLostControlNotice

|  |
| --- |
| ``` static final DJIKeyInfo<MultiControlLostControlInfo> KeyMultiControlLostControlNotice = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlLostControlNotice", new DJIValueConverter<>(MultiControlLostControlInfo.class)).canGet(false).canSet(false).canListen(true).canPerformAction(false).setIsEvent(true).setInnerIdentifier("MultiRcLostNotice") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlLostControlInfo` Monitor the notification event that the remote control with flight control or gimbal control loses connection. When remote controller with flight control loses connection, remote controller without flight control can call `KeyMultiControlAuthorityObtain` to take over flight control, or call `KeyMultiControlExecuteFailSafeActionWhenLostControl` to make the aircraft immediately execute the fail safe behavior which gets with `KeyFailsafeAction`. `Supported since MSDK 5.0.0`

[KeyMultiControlExecuteFailSafeActionWhenLostControl](#key_remotecontroller_multircexecuteaircraftlostlogic_inline)

###### final KeyMultiControlExecuteFailSafeActionWhenLostControl

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyMultiControlExecuteFailSafeActionWhenLostControl = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlExecuteFailSafeActionWhenLostControl", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("MultiRcExecuteAircraftLostLogic") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

When remote controller with flight control loses connection, remote controller without flight control can call this interface to make the aircraft immediately execute the fail safe behavior which gets with `KeyFailsafeAction`. `Supported since MSDK 5.0.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found