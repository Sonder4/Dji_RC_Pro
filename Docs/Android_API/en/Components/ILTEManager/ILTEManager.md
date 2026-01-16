**Navigation:** [ILTEManager](ILTEManager.md)

---

# class ILTEManager

|  |
| --- |
| ``` interface ILTEManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

LTE (Long Term Evolution) management class, used to authentication, enable and disable enhanced video transmission, and obtain LTE signal level and LTE status. This feature is currently only supported in mainland China. Enhanced video transmission combines video transmission and LTE 4G automatic enhanced video transmission technology. When the video transmission signal is good, the 4G link will maintain the basic data connection but no video transmission, in order to achieve high image quality and low latency, while reducing 4G traffic consumption. When the video transmission is interfered or blocked or in the scenario of long-distance transmission, the 4G transmission will be automatically turned on and enter the enhanced video transmission stage. After the video transmission is disconnected, the 4G transmission will work independently. At this time, the video transmission will be completely transmitted through the 4G transmission. `Supported since MSDK 5.2.0`

##### Class Members:

method

[addLTEDongleInfoListener](#iltemanager_addltedongleinfolistener_inline)

###### method addLTEDongleInfoListener

|  |
| --- |
| ``` void addLTEDongleInfoListener(@NonNull final LTEDongleInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener of LTE dongle information, through which the dongle information of the LTE can be obtained. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [LTEDongleInfoListener](../../Components/ILTEManager/ILTEManager_LTEDongleInfoListener.md#iltemanager_ltedongleinfolistener) listener | *Listener of LTE dongle information.* |

method

[removeLTEDongleInfoListener](#iltemanager_removeltedongleinfolistener_inline)

###### method removeLTEDongleInfoListener

|  |
| --- |
| ``` void removeLTEDongleInfoListener(@NonNull final LTEDongleInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove a listener of LTE dongle information. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [LTEDongleInfoListener](../../Components/ILTEManager/ILTEManager_LTEDongleInfoListener.md#iltemanager_ltedongleinfolistener) listener | *Listener of LTE dongle information.* |

method

[clearAllLTEDongleInfoListener](#iltemanager_clearallltedongleinfolistener_inline)

###### method clearAllLTEDongleInfoListener

|  |
| --- |
| ``` void clearAllLTEDongleInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of LTE dongle information. `Supported since MSDK 5.2.0`

method

[addLTEAuthenticationInfoListener](#iltemanager_addlteauthenticationinfolistener_inline)

###### method addLTEAuthenticationInfoListener

|  |
| --- |
| ``` void addLTEAuthenticationInfoListener(@NonNull final LTEAuthenticationInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener of LTE authentication information, through which the authentication status and authentication information can be obtained. This function requires access to the DJI server for authentication, please ensure that the network connection is normal. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [LTEAuthenticationInfoListener](../../Components/ILTEManager/ILTEManager_LTEAuthenticationInfoListener.md#iltemanager_lteauthenticationinfolistener) listener | *Listener of LTE authentication information.* |

method

[removeLTEAuthenticationInfoListener](#iltemanager_removelteauthenticationinfolistener_inline)

###### method removeLTEAuthenticationInfoListener

|  |
| --- |
| ``` void removeLTEAuthenticationInfoListener(@NonNull final LTEAuthenticationInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove a listener of LTE authentication information. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [LTEAuthenticationInfoListener](../../Components/ILTEManager/ILTEManager_LTEAuthenticationInfoListener.md#iltemanager_lteauthenticationinfolistener) listener | *Listener of LTE authentication information.* |

method

[clearAllLTEAuthenticationInfoListener](#iltemanager_clearalllteauthenticationinfolistener_inline)

###### method clearAllLTEAuthenticationInfoListener

|  |
| --- |
| ``` void clearAllLTEAuthenticationInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of LTE authentication information. `Supported since MSDK 5.2.0`

method

[updateLTEAuthenticationInfo](#iltemanager_updatelteauthenticationinfo_inline)

###### method updateLTEAuthenticationInfo

|  |
| --- |
| ``` void updateLTEAuthenticationInfo(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Update LTE authentication information. If `LTEAuthenticationInfoListener` fails to monitor the authentication information, you can call this interface to update the authentication information from the DJI server. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getLTEAuthenticationVerificationCode](#iltemanager_getlteauthenticationverificationcode_inline)

###### method getLTEAuthenticationVerificationCode

|  |
| --- |
| ``` void getLTEAuthenticationVerificationCode(String phoneAreaCode, String phoneNumber, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Obtain the LTE authentication verification code. If `isLTEAuthenticated` is `false`, it means that the aircraft is not LTE-authenticated. You can obtain the LTE authentication verification code through this interface, and then call `startLTEAuthentication` to start LTE authentication . `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String phoneAreaCode | *The area code of the LTE authentication mobile phone number, currently only supports mainland China (86).* |
| String phoneNumber | *LTE authentication mobile phone number.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[startLTEAuthentication](#iltemanager_startlteauthentication_inline)

###### method startLTEAuthentication

|  |
| --- |
| ``` void startLTEAuthentication(String phoneAreaCode, String phoneNumber, String verificationCode, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start LTE authentication, call `getLTEAuthenticationVerificationCode` to get the LTE authentication verification code, and then call this interface to start LTE authentication. After passing the authentication, you can call `setLTEEnhancedTransmissionType` to enable and disable LTE enhanced video transmission. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String phoneAreaCode | *The area code of the LTE authentication mobile phone number, currently only supports mainland China (86).* |
| String phoneNumber | *LTE authentication mobile phone number.* |
| String verificationCode | *LTE authentication verification code.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getLTEEnhancedTransmissionType](#iltemanager_getlteenhancedtransmissiontype_inline)

###### method getLTEEnhancedTransmissionType

|  |
| --- |
| ``` void getLTEEnhancedTransmissionType(CommonCallbacks.CompletionCallbackWithParam<LTELinkType> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Obtain the LTE enhanced transmission type. `OCU_SYNC_LTE` means the enhanced image transmission function is enabled, and `OCU_SYNC` means the enhanced image transmission function is disabled and OCUSYNC video transmission is used. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[LTELinkType](../../Components/ILTEManager/ILTEManager.md#iltemanager_ltelinktype)> callback | *Return the callback of execution result.* |

method

[setLTEEnhancedTransmissionType](#iltemanager_setlteenhancedtransmissiontype_inline)

###### method setLTEEnhancedTransmissionType

|  |
| --- |
| ``` void setLTEEnhancedTransmissionType(@NonNull LTELinkType lteLinkType, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the LTE enhanced transmission type, enable or disable the enhanced video transmission function. If the type is `OCU_SYNC_LTE`, it means that the enhanced video transmission function is enabled, and if the type is `OCU_SYNC`, it means that the enhanced video transmission function is disabled, and OCUSYNC video transmission is used. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [LTELinkType](../../Components/ILTEManager/ILTEManager.md#iltemanager_ltelinktype) lteLinkType | *LTE enhanced transmission type.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[addLTELinkInfoListener](#iltemanager_addltelinkinfolistener_inline)

###### method addLTELinkInfoListener

|  |
| --- |
| ``` void addLTELinkInfoListener(@NonNull final LTELinkInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener of LTE link information, through which the link quality level can be obtained. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [LTELinkInfoListener](../../Components/ILTEManager/ILTEManager_LTELinkInfoListener.md#iltemanager_ltelinkinfolistener) listener | *Listener of LTE link information.* |

method

[removeLTELinkInfoListener](#iltemanager_removeltelinkinfolistener_inline)

###### method removeLTELinkInfoListener

|  |
| --- |
| ``` void removeLTELinkInfoListener(@NonNull final LTELinkInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove a listener of LTE link information. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [LTELinkInfoListener](../../Components/ILTEManager/ILTEManager_LTELinkInfoListener.md#iltemanager_ltelinkinfolistener) listener | *Listener of LTE link information.* |

method

[clearAllLTELinkInfoListener](#iltemanager_clearallltelinkinfolistener_inline)

###### method clearAllLTELinkInfoListener

|  |
| --- |
| ``` void clearAllLTELinkInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of LTE link information. `Supported since MSDK 5.2.0`

method

[setLTEAircraftPrivatizationServerInfo](#iltemanager_setlteaircraftprivatizationserverinfo_inline)

###### method setLTEAircraftPrivatizationServerInfo

|  |
| --- |
| ``` void setLTEAircraftPrivatizationServerInfo(@NonNull LTEPrivatizationServerInfo serverInfo, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the aircraft LTE privatization server address. You can call `getAircraftPrivatizationServerInfo` to monitor the address of the aircraft LTE privatization server that has been set. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [LTEPrivatizationServerInfo](../../Components/ILTEManager/ILTEManager_LTEPrivatizationServerInfo.md#iltemanager_lteprivatizationserverinfo) serverInfo | *The LTE privatization server address information that needs to be set.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[clearLTEAircraftPrivatizationServer](#iltemanager_clearlteaircraftprivatizationserver_inline)

###### method clearLTEAircraftPrivatizationServer

|  |
| --- |
| ``` void clearLTEAircraftPrivatizationServer(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clear the aircraft LTE privatization server address. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[setLTERemoteControllerPrivatizationServerInfo](#iltemanager_setlteremotecontrollerprivatizationserverinfo_inline)

###### method setLTERemoteControllerPrivatizationServerInfo

|  |
| --- |
| ``` void setLTERemoteControllerPrivatizationServerInfo(@NonNull LTEPrivatizationServerInfo serverInfo, @NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the remote controller LTE privatization server address. You can call `getRemoteControllerPrivatizationServerInfo` to monitor the address of the remote controller LTE privatization server that has been set. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [LTEPrivatizationServerInfo](../../Components/ILTEManager/ILTEManager_LTEPrivatizationServerInfo.md#iltemanager_lteprivatizationserverinfo) serverInfo | *The LTE privatization server address information that needs to be set.* |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[clearLTERemoteControllerPrivatizationServer](#iltemanager_clearlteremotecontrollerprivatizationserver_inline)

###### method clearLTERemoteControllerPrivatizationServer

|  |
| --- |
| ``` void clearLTERemoteControllerPrivatizationServer(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clear the remote controller LTE privatization server address. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

##### Related:

class

[LTEAuthenticationInfo](../../Components/ILTEManager/ILTEManager_LTEAuthenticationInfo.md)

enum

[LTELinkType](#iltemanager_ltelinktype_inline)

###### enum LTELinkType

|  |
| --- |
| ``` @Keep  enum LTELinkType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

LTE link type. `OCU_SYNC_LTE` means to enable the enhanced video transmission function, and `OCU_SYNC` means to disable the enhanced video transmission function and use OCUSYNC video transmission. `Supported since MSDK 5.2.0`

##### Enum Members:

|  |  |
| --- | --- |
| OCU\_SYNC | Ocusync video transmission. |
| OCU\_SYNC\_LTE | LTE Enhanced video transmission. |

##### Class Members:

class

[LTELinkInfo](../../Components/ILTEManager/ILTEManager_LTELinkInfo.md)

class

[LTEPrivatizationServerInfo](../../Components/ILTEManager/ILTEManager_LTEPrivatizationServerInfo.md)

class

[LTEAuthenticationInfoListener](../../Components/ILTEManager/ILTEManager_LTEAuthenticationInfoListener.md)

class

[LTELinkInfoListener](../../Components/ILTEManager/ILTEManager_LTELinkInfoListener.md)

class

[LTEDongleInfoListener](../../Components/ILTEManager/ILTEManager_LTEDongleInfoListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found