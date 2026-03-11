**Navigation:** [IUASRemoteIDManager](IUASRemoteIDManager.md)

---

# class IUASRemoteIDManager

|  |
| --- |
| ``` interface IUASRemoteIDManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

RID management class of the drone system. In order to meet the increasingly standardized drone regulatory requirements around the world, The drone in flight should be able to broadcast the identification and location of the operator and the drone. `Supported since MSDK 5.0.0`

##### Class Members:

#### Area code

method

[setAreaCode](#iuasremoteidmanager_setareacode_inline)

###### method setAreaCode

|  |
| --- |
| ``` IDJIError setAreaCode(AreaCode areaCode) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the area code of country or area. The UAS remote identification function supported by the country or area can only be used after the corresponding area code is set. Currently the supported country or area list and the supported function list is as follows:- USA: remote identification broadcast of drone - Japan: remote identification broadcast of drone, registration number of drone. - Frence: EID Switch **Note: This interface has been deprecated since MSDK 5.3.0. Please call `setUASRemoteIDAreaStrategy` instead of this function.** `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [AreaCode](../../Components/IAreaCodeManager/IAreaCodeManager.md#iareacodemanager_areacode) areaCode | *Area code of country or area.* |

##### Return:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) | *Return the setting execution result of area code.* |

#### Area strategy

method

[setUASRemoteIDAreaStrategy](#iuasremoteidmanager_setuasremoteidareastrategy_inline)

###### method setUASRemoteIDAreaStrategy

|  |
| --- |
| ``` IDJIError setUASRemoteIDAreaStrategy(AreaStrategy strategy) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the area strategy, and the UAS remote identification function supported by the country or area can be used only after the corresponding area strategy is set. After MSDK obtains the area code of the current area, it will set the corresponding area strategy by default. If you need to set other area strategy for development, you can call this interface to set the corresponding area strategy to make it take effect. Currently the supported country or area list and the supported function list is as follows:- USA: remote identification broadcast of drone - Japan: remote identification broadcast of drone, registration number of drone. - European: remote identification broadcast of drone, european certification C-Class, registration number of operator. - Singapore: remote identification broadcast of drone, registration number of operator. - United Arab Emirates: remote identification broadcast of drone, registration number of operator. - China: remote identification broadcast of drone, real name. - Frence: EID Switch `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [AreaStrategy](../../Components/IUASRemoteIDManager/IUASRemoteIDManager.md#iuasremoteidmanager_areastrategy) strategy | *Area strategy* |

##### Return:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) | *Return the setting execution result of area strategy.* |

#### Remote identification broadcast of the drone

method

[getUASRemoteIDStatus](#iuasremoteidmanager_getuasremoteidstatus_inline)

###### method getUASRemoteIDStatus

|  |
| --- |
| ``` UASRemoteIDStatus getUASRemoteIDStatus() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the remote identification broadcast status of the drone system. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [UASRemoteIDStatus](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UASRemoteIDStatus.md#iuasremoteidmanager_uasremoteidstatus) | *Return the remote identification status of the drone system.* |

method

[addUASRemoteIDStatusListener](#iuasremoteidmanager_adduasremoteidstatuslistener_inline)

###### method addUASRemoteIDStatusListener

|  |
| --- |
| ``` void addUASRemoteIDStatusListener(UASRemoteIDStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the remote identification broadcast status listener of the drone system. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [UASRemoteIDStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UASRemoteIDStatusListener.md#iuasremoteidmanager_uasremoteidstatuslistener) listener | *The remote identification broadcast status listener of the drone system* |

method

[removeUASRemoteIDStatusListener](#iuasremoteidmanager_removeuasremoteidstatuslistener_inline)

###### method removeUASRemoteIDStatusListener

|  |
| --- |
| ``` void removeUASRemoteIDStatusListener(UASRemoteIDStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the remote identification broadcast status listener of the drone system. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [UASRemoteIDStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UASRemoteIDStatusListener.md#iuasremoteidmanager_uasremoteidstatuslistener) listener | *The remote identification broadcast status listener of the drone system* |

method

[clearUASRemoteIDStatusListener](#iuasremoteidmanager_clearuasremoteidstatuslistener_inline)

###### method clearUASRemoteIDStatusListener

|  |
| --- |
| ``` void clearUASRemoteIDStatusListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the remote identification broadcast status listeners of the drone system. `Supported since MSDK 5.0.0`

#### EID Switch

method

[setElectronicIDEnabled](#iuasremoteidmanager_setelectronicidenabled_inline)

###### method setElectronicIDEnabled

|  |
| --- |
| ``` void setElectronicIDEnabled(boolean isEnabled, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enable or disable the EID function. After this function is enabled, the drone will broadcast the EID. EID is an electronic ID standard dedined by Frence. The value is the flight control SN of the drone. The prefix `1581F` means it is a droen produced by DJI. For DJI flight control SN that is not starting with `1581F`, `1581F` should be added to the flight control SN. For example:- For M300 RTK, this value is `1581F` + the flight control SN(`KeySerialNumber`)+ `0` - for M350 RTK, M30 and M3E Series, this value is the flight control SN(`KeySerialNumber`) `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isEnabled | *`true` means the EID function is enabled.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of the execution result.* |

method

[getElectronicIDEnabled](#iuasremoteidmanager_getelectronicidenabled_inline)

###### method getElectronicIDEnabled

|  |
| --- |
| ``` void getElectronicIDEnabled(@NonNull CommonCallbacks.CompletionCallbackWithParam<Boolean> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get whether the EID is enabled. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<Boolean> callback | *Return the result of the execution result.* |

method

[addElectronicIDStatusListener](#iuasremoteidmanager_addelectronicidstatuslistener_inline)

###### method addElectronicIDStatusListener

|  |
| --- |
| ``` void addElectronicIDStatusListener(ElectronicIDStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of EID status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ElectronicIDStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_ElectronicIDStatusListener.md#iuasremoteidmanager_electronicidstatuslistener) listener | *The listener of EID status* |

method

[removeElectronicIDStatusListener](#iuasremoteidmanager_removeelectronicidstatuslistener_inline)

###### method removeElectronicIDStatusListener

|  |
| --- |
| ``` void removeElectronicIDStatusListener(ElectronicIDStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of EID status. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ElectronicIDStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_ElectronicIDStatusListener.md#iuasremoteidmanager_electronicidstatuslistener) listener | *The listener of EID status* |

method

[clearAllElectronicIDStatusListener](#iuasremoteidmanager_clearallelectronicidstatuslistener_inline)

###### method clearAllElectronicIDStatusListener

|  |
| --- |
| ``` void clearAllElectronicIDStatusListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of EID status. `Supported since MSDK 5.0.0`

#### Registration number of the drone

method

[setUARegistrationNumber](#iuasremoteidmanager_setuaregistrationnumber_inline)

###### method setUARegistrationNumber

|  |
| --- |
| ``` void setUARegistrationNumber(String number, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the registration number based on the drone management policies and regulations of different countries.- The drone registration number application entrance of Japan: <https://www.dips.mlit.go.jp>，Format of drone registration code:{"registration\_code":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","key\_info":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","nonce\_info":"xxxxxxxxxxxx"}, After setting the drone registration code, `isBroadcastRemoteIdEnabled` will be enabled. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String number | *The information registration number of the drone* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of the execution result.* |

method

[getUARegistrationNumber](#iuasremoteidmanager_getuaregistrationnumber_inline)

###### method getUARegistrationNumber

|  |
| --- |
| ``` void getUARegistrationNumber(@NonNull CommonCallbacks.CompletionCallbackWithParam<String> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the set drone registration number. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<String> callback | *Return the result of the execution result.* |

method

[addUARegistrationNumberStatusListener](#iuasremoteidmanager_adduaregistrationnumberstatuslistener_inline)

###### method addUARegistrationNumberStatusListener

|  |
| --- |
| ``` void addUARegistrationNumberStatusListener(UARegistrationNumberStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the registration number status listener of the drone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [UARegistrationNumberStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UARegistrationNumberStatusListener.md#iuasremoteidmanager_uaregistrationnumberstatuslistener) listener | *The registration number status listener of the drone* |

method

[removeUARegistrationNumberStatusListener](#iuasremoteidmanager_removeuaregistrationnumberstatuslistener_inline)

###### method removeUARegistrationNumberStatusListener

|  |
| --- |
| ``` void removeUARegistrationNumberStatusListener(UARegistrationNumberStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the registration number status listener of the drone. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [UARegistrationNumberStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UARegistrationNumberStatusListener.md#iuasremoteidmanager_uaregistrationnumberstatuslistener) listener | *The registration number status listener of the drone* |

method

[clearAllUARegistrationNumberStatusListener](#iuasremoteidmanager_clearalluaregistrationnumberstatuslistener_inline)

###### method clearAllUARegistrationNumberStatusListener

|  |
| --- |
| ``` void clearAllUARegistrationNumberStatusListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the registration number status listeners of the drone. `Supported since MSDK 5.0.0`

#### European Certification C-Class

method

[addCClassStatusListener](#iuasremoteidmanager_addcclassstatuslistener_inline)

###### method addCClassStatusListener

|  |
| --- |
| ``` void addCClassStatusListener(CClassStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener for European Certification C-Class status. Used to get the C-Class type. For detailed description, please refer to: [European Aviation Safety Agency](https://www.easa.europa.eu/en/domains/drones-air-mobility/operating-drone/open-category-low-risk-civil-drones) `Supported since MSDK 5.13.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CClassStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_CClassStatusListener.md#iuasremoteidmanager_cclassstatuslistener) listener | *The european certification C-Class status listener of the drone* |

method

[removeCClassStatusListener](#iuasremoteidmanager_removecclassstatuslistener_inline)

###### method removeCClassStatusListener

|  |
| --- |
| ``` void removeCClassStatusListener(CClassStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the european certification C-Class status listener of the drone. `Supported since MSDK 5.13.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CClassStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_CClassStatusListener.md#iuasremoteidmanager_cclassstatuslistener) listener | *The european certification C-Class status listener of the drone* |

method

[clearAllCClassStatusListener](#iuasremoteidmanager_clearallcclassstatuslistener_inline)

###### method clearAllCClassStatusListener

|  |
| --- |
| ``` void clearAllCClassStatusListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the european certification C-Class status listener s of the drone. `Supported since MSDK 5.13.0`

#### Registration number of the operator

method

[setOperatorRegistrationNumber](#iuasremoteidmanager_setoperatorregistrationnumber_inline)

###### method setOperatorRegistrationNumber

|  |
| --- |
| ``` void setOperatorRegistrationNumber(String number,  @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set operator registration code. It needs to be applied and set up according to the drone control policies and regulations of each country.

- EU operator registration code application portal: [https://www.easa.europa.eu /en/domains/civil-drones/naa](https://www.easa.europa.eu/en/domains/civil-drones/naa), after setting the operator registration code, `isBroadcastRemoteIdEnabled` will be enabled.
  
  
`Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String number | *The information registration number of the operator* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of the execution result.* |

method

[getOperatorRegistrationNumber](#iuasremoteidmanager_getoperatorregistrationnumber_inline)

###### method getOperatorRegistrationNumber

|  |
| --- |
| ``` void getOperatorRegistrationNumber(@NonNull CommonCallbacks.CompletionCallbackWithParam<String> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the set operator registration number. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<String> callback | *Return the result of the execution result.* |

method

[addOperatorRegistrationNumberStatusListener](#iuasremoteidmanager_addoperatorregistrationnumberstatuslistener_inline)

###### method addOperatorRegistrationNumberStatusListener

|  |
| --- |
| ``` void addOperatorRegistrationNumberStatusListener(OperatorRegistrationNumberStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the registration number status listener of the operator. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [OperatorRegistrationNumberStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_OperatorRegistrationNumberStatusListener.md#iuasremoteidmanager_operatorregistrationnumberstatuslistener) listener | *The registration number status listener of the operator* |

method

[removeOperatorRegistrationNumberStatusListener](#iuasremoteidmanager_removeoperatorregistrationnumberstatuslistener_inline)

###### method removeOperatorRegistrationNumberStatusListener

|  |
| --- |
| ``` void removeOperatorRegistrationNumberStatusListener(OperatorRegistrationNumberStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the registration number status listener of the opeartor. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [OperatorRegistrationNumberStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_OperatorRegistrationNumberStatusListener.md#iuasremoteidmanager_operatorregistrationnumberstatuslistener) listener | *The registration number status listener of the operator* |

method

[clearAllOperatorRegistrationNumberStatusListener](#iuasremoteidmanager_clearalloperatorregistrationnumberstatuslistener_inline)

###### method clearAllOperatorRegistrationNumberStatusListener

|  |
| --- |
| ``` void clearAllOperatorRegistrationNumberStatusListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the registration number status listeners of the operator. `Supported since MSDK 5.8.0`

#### Real name

method

[addRealNameRegistrationStatusListener](#iuasremoteidmanager_addrealnameregistrationstatuslistener_inline)

###### method addRealNameRegistrationStatusListener

|  |
| --- |
| ``` void addRealNameRegistrationStatusListener(RealNameRegistrationStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the real name status listener. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RealNameRegistrationStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_RealNameRegistrationStatusListener.md#iuasremoteidmanager_realnameregistrationstatuslistener) listener | *Real name status listener* |

method

[removeRealNameRegistrationStatusListener](#iuasremoteidmanager_removerealnameregistrationstatuslistener_inline)

###### method removeRealNameRegistrationStatusListener

|  |
| --- |
| ``` void removeRealNameRegistrationStatusListener(RealNameRegistrationStatusListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the real name status listener. `Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [RealNameRegistrationStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_RealNameRegistrationStatusListener.md#iuasremoteidmanager_realnameregistrationstatuslistener) listener | *Real name status listener* |

method

[clearAllRealNameRegistrationStatusListener](#iuasremoteidmanager_clearallrealnameregistrationstatuslistener_inline)

###### method clearAllRealNameRegistrationStatusListener

|  |
| --- |
| ``` void clearAllRealNameRegistrationStatusListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the real name status listener. `Supported since MSDK 5.8.0`

method

[updateRealNameRegistrationStateFromUOM](#iuasremoteidmanager_updaterealnameregistrationstatefromuom_inline)

###### method updateRealNameRegistrationStateFromUOM

|  |
| --- |
| ``` void updateRealNameRegistrationStateFromUOM( CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Update the real name system status to the aircraft from the China UOM system. In accordance with the requirements of mainland China's unmanned aircraft flight management regulations, starting from January 1, 2024, any flying drone purchased and activated after this date to fly in mainland China will need to register with a real name on the UOM system, and You need to be connected to the Internet to update the real name registration information to the aircraft, otherwise the aircraft will not be able to take off normally.

- Real name registration portal: <https://uom.caac.gov.cn/#/login>, After the real name registration is implemented, you can call this interface to update the real name status to the aircraft.
  
  
`Supported since MSDK 5.8.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of the execution result.* |

##### Related:

enum

[AreaStrategy](#iuasremoteidmanager_areastrategy_inline)

###### enum AreaStrategy

|  |
| --- |
| ``` enum AreaStrategy ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.uas |

##### Description:

The remote identification area strategy. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| US\_STRATEGY | America strategy. |
| FRANCE\_STRATEGY | France strategy. |
| JAPAN\_STRATEGY | Japan strategy. |
| EUROPEAN\_STRATEGY | European strategy. |

##### Class Members:

enum

[RemoteIdWorkingState](#iuasremoteidmanager_remoteidworkingstate_inline)

###### enum RemoteIdWorkingState

|  |
| --- |
| ``` enum RemoteIdWorkingState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.uas |

##### Description:

The remote identification working status of the drone. `Supported since MSDK 5.1.0`

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Idle, initialization state. |
| WORKING | Working, it indicates that Remote ID is broadcasting. |
| OPERATOR\_LOCATION\_LOST\_ERROR | Not working, because the operator's position is lost, resulting in the Remote ID not being broadcast. |
| FIRMWARE\_ERROR | Not working, because the firmware error, resulting in the Remote ID not being broadcast. |
| NO\_BROADCAST | Not broadcast. |
| NOT\_SUPPORTED | Not supported. |
| NOT\_SUPPORTED | Not working, because the unknown error, resulting in the Remote ID not being broadcast. |

##### Class Members:

class

[UASRemoteIDStatus](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UASRemoteIDStatus.md)

class

[UASRemoteIDStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UASRemoteIDStatusListener.md)

class

[ElectronicIDStatus](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_ElectronicIDStatus.md)

class

[ElectronicIDStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_ElectronicIDStatusListener.md)

class

[UARegistrationNumberStatus](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UARegistrationNumberStatus.md)

enum

[CClassStatus](#iuasremoteidmanager_cclassstatus_inline)

###### enum CClassStatus

|  |
| --- |
| ``` enum CClassStatus ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.uas |

##### Description:

European certification C-Class status. `Supported since MSDK 5.13.0`

##### Enum Members:

|  |  |
| --- | --- |
| CCLASS\_C0 | C0-Class. |
| CCLASS\_C1 | C1-Class. |
| CCLASS\_C2 | C2-Class. |
| CCLASS\_C3 | C3-Class. |

##### Class Members:

class

[CClassStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_CClassStatusListener.md)

class

[UARegistrationNumberStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_UARegistrationNumberStatusListener.md)

class

[OperatorRegistrationNumberStatus](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_OperatorRegistrationNumberStatus.md)

class

[OperatorRegistrationNumberStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_OperatorRegistrationNumberStatusListener.md)

class

[RealNameRegistrationStatus](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_RealNameRegistrationStatus.md)

class

[RealNameRegistrationStatusListener](../../Components/IUASRemoteIDManager/IUASRemoteIDManager_RealNameRegistrationStatusListener.md)

enum

[RealNameRegistrationState](#value_flightcontroller_enum_uomrealnamefcstatus_inline)

###### enum RealNameRegistrationState

|  |
| --- |
| ``` enum RealNameRegistrationState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Real name registration status of aircraft. `Supported since MSDK 5.8.0`

##### Enum Members:

|  |  |
| --- | --- |
| NOT\_AUTH | NOT AUTH, the aircraft will not be able to take off. In accordance with the requirements of mainland China's unmanned aircraft flight management regulations, if you need to fly in mainland China, please register with your real name on the UOM system.- Real name registration portal: <https://uom.caac.gov.cn/#/login>, After the real name registration is implemented, you can call this interface to update the real name status to the aircraft. |
| VAILD\_AUTH | VAILD AUTH, the aircraft can take off normally. |
| CANCELLED | CANCELLED, the registration information on the UOM system has been canceled and the aircraft will not be able to take off. In accordance with the requirements of mainland China's unmanned aircraft flight management regulations, if you need to fly in mainland China, please register with your real name on the UOM system.- Real name registration portal: <https://uom.caac.gov.cn/#/login>, After the real name registration is implemented, you can call this interface to update the real name status to the aircraft. |
| NETWORK\_ERROR | NETWORK ERROR, lease make sure the network is normal and try again |
| VERIFIED\_AND\_CANCLLED | VERIFIED AND CANCLLED, after the aircraft has been authenticated, the status on the UOM system has been canceled. In accordance with the requirements of mainland China's unmanned aircraft flight management regulations, if you need to fly in mainland China, please register with your real name on the UOM system.- Real name registration portal: <https://uom.caac.gov.cn/#/login>, After the real name registration is implemented, you can call this interface to update the real name status to the aircraft. |
| UNSUPPORTED | The aircraft does not support the real name function. |
| NOT\_ACTIVE\_YET | The aircraft has not been activated. Please activate it with the DJI official APP first. |
| DONT\_NEED\_CHECK\_REALNAME | The aircraft does not need the real name function. |
| DONT\_IN\_CHINA\_MAINLAND | The aircraft is not in mainland China. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found