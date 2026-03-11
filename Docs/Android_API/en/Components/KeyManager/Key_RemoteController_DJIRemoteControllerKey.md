# class DJIRemoteControllerKey

|  |
| --- |
| ``` class DJIRemoteControllerKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`DJIRemoteControllerKey` provides methods to set and get data from Remote Controller. Remote Controller has sticks, buttons, wheels, GPS, batteries and output ports for video. The mobile device can be connected to Remote Controller, and Remote Controller will send all information coming from the aircraft to mobile device. `This class is supported since MSDK 5.0`

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

**Parameter:**Boolean `true` represents remote controller is connected. `supported since MSDK 5.0`

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

**Parameter:**`RemoteControllerType` To get remote controller type. `supported since MSDK 5.0`

Control Mode

[KeyRcControllerMode](#key_remotecontroller_rccontrollermode_inline)

###### final KeyRcControllerMode

|  |
| --- |
| ``` static final DJIKeyInfo<RcControllerModeMsg> KeyRcControllerMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RcControllerMode", new DJIValueConverter<>(RcControllerModeMsg.class)).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`Value_RemoteController_Struct_RcControllerModeMsg` To get remote controller control mode. `supported since MSDK 5.0`

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

**Parameter:**`BatteryInfo` To get remote controller battery power information. `supported since MSDK 5.0`

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

**Parameter:**String To get remote controller serial number. `supported since MSDK 5.0`

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

**Parameter:**String To get remote controller firmware version. `supported since MSDK 5.0`

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

**Parameter:**Integer To get remote controller left stick vertical offset Value. The value range is [-660,660]. Move the stick to the buttom, the offset value is -660. Move the stick to the top, the offset value is 660. The meaning of this value should combine with the setting of `KeyRcControllerMode`. For example, if `KeyRcControllerMode` is set to DJI\_RC\_CONTROL\_MODE\_USA, this value means that the aircraft is flying upward or downward. `supported since MSDK 5.0`

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

**Parameter:**Integer To get remote controller left stick horizontal offset Value. The value range is [-660,660]. Move the stick to the far left, the offset value is -660. Move the stick to the far right, the offset value is 660. The meaning of this value should combine with the setting of `KeyRcControllerMode`. For example, if `KeyRcControllerMode` is set to DJI\_RC\_CONTROL\_MODE\_USA, this value means that the aircraft is turning to the left or to the right. `supported since MSDK 5.0`

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

**Parameter:**Integer To get remote controller right stick vertical offset Value. The value range is [-660,660]. Move the stick to the buttom, the offset value is -660. Move the stick to the top, the offset value is 660. The meaning of this value should combine with the setting of `KeyRcControllerMode`. For example, if `KeyRcControllerMode` is set to DJI\_RC\_CONTROL\_MODE\_USA, this value means that the aircraft is flying forward or backward. `supported since MSDK 5.0`

[KeyStickRightHorizontal](#key_remotecontroller_rcstickrighthorizontal_inline)

###### final KeyStickRightHorizontal

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyStickRightHorizontal = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"StickRightHorizontal", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCStickRightHorizontal") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get remote controller right stick horizontal offset Value. The value range is [-660,660]. Move the stick to the far left, the offset value is -660. Move the stick to the far right, the offset value is 660. The meaning of this value should combine with the setting of `KeyRcControllerMode`. For example, if `KeyRcControllerMode` is set to DJI\_RC\_CONTROL\_MODE\_USA, this value means that the aircraft is flying to the left or to the right. `supported since MSDK 5.0`

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

**Parameter:**Boolean `true` means record button is pressed. The camera will switch to record mode or start/stop recording. `supported since MSDK 5.0`

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

**Parameter:**Boolean `true` means return button is pressed. Aircraft will start returning or stop returning。 `supported since MSDK 5.0`

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

**Parameter:**Boolean `true` means emergency stop button is pressed. In Matrice 30 Series and Matrice 300 RTK, if emergency stop button is pressed, aircraft will stop intelligent flight. `supported since MSDK 5.0`

Left Wheel

[KeyLeftDial](#key_remotecontroller_rcleftwheel_inline)

###### final KeyLeftDial

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyLeftDial = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LeftDial", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCLeftWheel") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get remote controller left wheel offset value. The value range is [-660,660]. The default value is 0. Turn the wheel to the far left, the value is -660. Turn the wheel to the far right, the value is 660. Left wheel is usually used to control the pitch behaviour of the gimbal. `supported since MSDK 5.0`

Right Wheel

[KeyRightDial](#key_remotecontroller_rcrightwheel_inline)

###### final KeyRightDial

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyRightDial = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RightDial", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCRightWheel") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get remote controller right wheel offset value. The value range is [-660,660]. The default value is 0. Turn the wheel to the far left, the value is -660. Turn the wheel to the far right, the value is 660. Right wheel is usually used to control the yaw behaviour of the gimbal. `supported since MSDK 5.0`

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

**Parameter:**`FiveDimensionPressedStatus` To get remote controller five dimenion button status. `supported since MSDK 5.0`

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

**Parameter:**Boolean `true` means Custom Button C1 is pressed. Custom Button C1 is used for gimbal returning center by default, and it can be custom configured. `supported since MSDK 5.0`

[KeyCustomButton2Down](#key_remotecontroller_rccustombutton2down_inline)

###### final KeyCustomButton2Down

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCustomButton2Down = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomButton2Down", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCCustomButton2Down") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means Custom Button C2 is pressed. Custom Button C2 is used for main and auxiliary screen switching by default, and it can be custom configured. `supported since MSDK 5.0`

[KeyCustomButton3Down](#key_remotecontroller_rccustombutton3down_inline)

###### final KeyCustomButton3Down

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCustomButton3Down = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomButton3Down", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RCCustomButton3Down") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means Custom Button C3 is pressed. Custom Button C3 can be custom configured. `supported since MSDK 5.0`

#### Basic Operation

Pair Frequency

[KeyRequestPairing](#key_remotecontroller_rcrequestpairing_inline)

###### final KeyRequestPairing

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyRequestPairing = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RequestPairing", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("RcRequestPairing") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start the remote controller frequency pairing operation, then the remote controller will start frequency pairing with the aircraft. When the remote controller cannot be connected to the aircraft or the remote controller is replaced, the remote controller can be connected to the aircraft through frequency pairing operation. `supported since MSDK 5.0`

[KeyStopPairing](#key_remotecontroller_rcstoppairing_inline)

###### final KeyStopPairing

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopPairing = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopPairing", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("RcStopPairing") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Stop the remote controller frequency pairing operation. `supported since MSDK 5.0`

对频

[KeyPairingStatus](#key_remotecontroller_rcpairingstate_inline)

###### final KeyPairingStatus

|  |
| --- |
| ``` static final DJIKeyInfo<PairingState> KeyPairingStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PairingStatus", new SingleValueConverter<>(PairingState.class,RcPairingStateMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("RcPairingState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PairingState` To get remote controller frequency pairing status. `supported since MSDK 5.0`

#### Dual Control

whether dual control is supported or not

[KeyMultiControlIsSupported](#key_remotecontroller_ismasterslavemodev4supported_inline)

###### final KeyMultiControlIsSupported

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyMultiControlIsSupported = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlIsSupported", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IsMasterSlaveModeV4Supported") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means dual control is supported. `supported since MSDK 5.0`

Dual Control Frequency Pairing

[KeyMultiControlRequestPairing](#key_remotecontroller_startmodepairing_inline)

###### final KeyMultiControlRequestPairing

|  |
| --- |
| ``` static final DJIActionKeyInfo<MultiControlChannel,EmptyMsg> KeyMultiControlRequestPairing = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlRequestPairing", new SingleValueConverter<>(MultiControlChannel.class,MultiControlChannelInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("StartModePairing") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlChannel` Start dual control frequency pairing. The remote controller can be switched to A controller or B controller for frequency pairing with the aircraft. `supported since MSDK 5.0`

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

**Parameter:**Integer get the number of currently connected remote controller. `supported since MSDK 5.0`

Online APP Number

[KeyMultiControlOnlineAppCount](#key_remotecontroller_multirconlineappcount_inline)

###### final KeyMultiControlOnlineAppCount

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyMultiControlOnlineAppCount = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlOnlineAppCount", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRcOnlineAPPCount") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer get the number of currently connected APP. NOTICE: The APP mentioned here include DJI's official APPs and Third-party APPs developed through DJI MSDK. `supported since MSDK 5.0`

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

**Parameter:**`MultiControlStatusInfo` To get dual controller connection status. This interface can be used to check the online status of A controller, the APPs in A controller, B controller and the APPs in B controller. `supported since MSDK 5.0`

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

**Parameter:**`MultiControlFlightControlAuthorityOwnerInfo` To get the current flight control whether it is in control A or control B. `supported since MSDK 5.0`

[KeyMultiControlGimbalAuthorityOwner](#key_remotecontroller_multircgimbalscontrolauthowner_inline)

###### final KeyMultiControlGimbalAuthorityOwner

|  |
| --- |
| ``` static final DJIKeyInfo<List<MultiControlChannelInfo>> KeyMultiControlGimbalAuthorityOwner = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlGimbalAuthorityOwner", new SingleValueConverter<>((Class)List.class,MultiRcGimbalsControlAuthOwnerMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRcGimbalsControlAuthOwner") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**List<`MultiControlChannelInfo`> To get the current gimbal control whether it is in control A or control B. `supported since MSDK 5.0`

[KeyMultiControlAuthorityObtain](#key_remotecontroller_multirccontrolauthoritysurpass_inline)

###### final KeyMultiControlAuthorityObtain

|  |
| --- |
| ``` static final DJIActionKeyInfo<List<MultiControlAuthorityType>,EmptyMsg> KeyMultiControlAuthorityObtain = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlAuthorityObtain", new SingleValueConverter<>((Class)List.class,RCAuthorityModes.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("MultiRcControlAuthoritySurpass") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**List<`MultiControlAuthorityType`> Grab control of flight or gimbal. `supported since MSDK 5.0`

[KeyMultiControlLockAuthority](#key_remotecontroller_multirccontrollockright_inline)

###### final KeyMultiControlLockAuthority

|  |
| --- |
| ``` static final DJIActionKeyInfo<MultiControlLockAuthorityInfo,EmptyMsg> KeyMultiControlLockAuthority = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlLockAuthority", new DJIValueConverter<>(MultiControlLockAuthorityInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("MultiRcControlLockRight") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlLockAuthorityInfo` Lock control of flight or gimbal. Control will not be grabbed after being locked. `supported since MSDK 5.0`

[KeyMultiControlFlightControlAuthorityLockStatus](#key_remotecontroller_multircflightcontrolauthlockstate_inline)

###### final KeyMultiControlFlightControlAuthorityLockStatus

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyMultiControlFlightControlAuthorityLockStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlFlightControlAuthorityLockStatus", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MultiRCFlightControlAuthLockState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean Return whether the flight control is locked. `supported since MSDK 5.0`

[KeyMultiControlLostControlNotice](#key_remotecontroller_multirclostnotice_inline)

###### final KeyMultiControlLostControlNotice

|  |
| --- |
| ``` static final DJIKeyInfo<MultiControlLostControlInfo> KeyMultiControlLostControlNotice = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlLostControlNotice", new DJIValueConverter<>(MultiControlLostControlInfo.class)).canGet(false).canSet(false).canListen(true).canPerformAction(false).setIsEvent(true).setInnerIdentifier("MultiRcLostNotice") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`MultiControlLostControlInfo` Monitor the notification event that the remote control with flight control or gimbal control loses connection. When remote controller with flight control loses connection, remote controller without flight control can call `KeyMultiControlObtainAuthorityWhenLostControl` to take over flight control, otherwise the aircraft will perform out of control behavior. `supported since MSDK 5.0`

[KeyMultiControlObtainAuthorityWhenLostControl](#key_remotecontroller_multircexecuteaircraftlostlogic_inline)

###### final KeyMultiControlObtainAuthorityWhenLostControl

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyMultiControlObtainAuthorityWhenLostControl = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"MultiControlObtainAuthorityWhenLostControl", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("MultiRcExecuteAircraftLostLogic") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

When remote controller with flight control loses connection, remote controller without flight control can call this interface to take over flight control, otherwise the aircraft will perform out of control behavior. `supported since MSDK 5.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found