**Navigation:** [ILDMManager](ILDMManager.md)

---

# class ILDMManager

|  |
| --- |
| ``` interface ILDMManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

LDM(Local Data Mode) manager is used to enable or disable the LDM mode. After enabling LDM, the MSDK's internet access will be restricted. You can call`enableLDM`to enable LDM mode. Also, you can configure the exempt modules when enabling the LDM in order to allow those modules to access internet. For example, allowing RTK module as an exempt module in LDM will allow the RTK module to connect with RTK server to ensure the accuracy of navigation. Also, allowing a flysafe module as an exempt module in LDM can still access the flysafe ever to ensure the safety of flight. You have to apply successfully from our developer website(<https://developer.dji.com/user/ldm/>) and receive a LDM licence in order to use this feature. `Supported since MSDK 5.0.0`

##### Class Members:

method

[isLDMLicenseLoaded](#ildmmanager_isldmlicenseloaded_inline)

###### method isLDMLicenseLoaded

|  |
| --- |
| ``` boolean isLDMLicenseLoaded() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

After calling`enableLDM`, MSDK will check if there is a local LDM license. Otherwise, it will ask the server to transmit a LDM license. If the LDM license has been loaded and activated, then return`true`. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true`means the LDM certificate has been loaded and activated.* |

method

[isLDMEnabled](#ildmmanager_isldmenabled_inline)

###### method isLDMEnabled

|  |
| --- |
| ``` boolean isLDMEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Return whether the LDM is enabled. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true`means LDM is enabled.* |

method

[isNetworkServiceEnabledForModule](#ildmmanager_isnetworkserviceenabledformodule_inline)

###### method isNetworkServiceEnabledForModule

|  |
| --- |
| ``` boolean isNetworkServiceEnabledForModule(LDMExemptModule module) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Check the internet access of input LDMExemptModule.

##### Input Parameters:

|  |  |
| --- | --- |
| [LDMExemptModule](../../Components/ILDMManager/ILDMManager.md#ildmmanager_ldmexemptmodule) module | *LDM exempt module.* |

##### Return:

|  |  |
| --- | --- |
| boolean | *`true`means LDM is not enabled or the input LDMExemptModule still has internet access when LDM is enabled.`false` means LDM is enabled and the input LDMExemptModule has no internet access.* |

method

[loadLocalLDMLicenseContent](#ildmmanager_loadlocalldmlicensecontent_inline)

###### method loadLocalLDMLicenseContent

|  |
| --- |
| ``` IDJIError loadLocalLDMLicenseContent(String content) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Load the content of LDM license, the content is the license\_appname.txt given by developer website after successfully applying the LDM license. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String content | *The content of LDM License, the content is the license\_appname.txt given by developer website after successfully applying for the LDM license.* |

##### Return:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) | *Return the loading execution result of LDM License. The result is successful if returning null.* |

method

[getLocalLDMLicensePath](#ildmmanager_getlocalldmlicensepath_inline)

###### method getLocalLDMLicensePath

|  |
| --- |
| ``` String getLocalLDMLicensePath(Context ctx) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the saved path of the offline LDM license. Please put the offline LDM license under this directory. `MSDK 5.0.0 start support`

##### Input Parameters:

|  |  |
| --- | --- |
| Context ctx | *Current application's context* |

##### Return:

|  |  |
| --- | --- |
| String | *Return the saved path of the offline LDM license.* |

method

[enableLDM](#ildmmanager_enableldm_inline)

###### method enableLDM

|  |
| --- |
| ``` void enableLDM(Context context, CommonCallbacks.CompletionCallback callback, LDMExemptModule... modules) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

A method of enabling LDM mode. When calling this method, you can pass multiple LDMExemptModule parameters in. It means the exempt modules when LDM is enabled. E.g. When you pass RTK and FLY\_SAFE in, those modules can still access internet after enabling LDM. **Note: 1. You have to call this method after calling SDKManager.init() method in order to enable LDM. If you don't have an offline license, then you need to pass this parameter in, `MSDK_INIT_AND_REGISTRATION`. This will allow MSDK registration module to complete the registration. 2. Make sure your offline LDM license（license\_appname.txt）is saved in the correct directory when you use an offline LDM license to enable LDM. You can use `getLocalLDMLicensePath` to get the offline LDM License path. 3. Except save the offline LDM license to the specified path, you can also call `loadLocalLDMLicenseContent` to load the content of LDM license to the LDM module. 4. If you don't have an offline LDM license, the first time you call this method will access DJI LDM server to download the LDM license. Later on, calling this method will read the local cache. 5. If you want to use the offline LDM license, you need to apply from<https://developer.dji.com>loin your user account and apply. `Supported since MSDK 5.0.0`**

##### Input Parameters:

|  |  |
| --- | --- |
| Context context | *Pass a Application's Context in.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a callback of the execution result.* |
| [LDMExemptModule](../../Components/ILDMManager/ILDMManager.md#ildmmanager_ldmexemptmodule)... modules | *If you need some modules than are exempt when LDM is enabled so that they can still access internet, you can set this in modules.* |

method

[disableLDM](#ildmmanager_disableldm_inline)

###### method disableLDM

|  |
| --- |
| ``` void disableLDM(CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Disable LDM. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return a callback of the execution result.* |

##### Related:

enum

[LDMExemptModule](#ildmmanager_ldmexemptmodule_inline)

###### enum LDMExemptModule

|  |
| --- |
| ``` enum LDMExemptModule ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.common.ldm |

##### Description:

LDM exempt modules type, set the LDM exempt modules in order to allow them access internet. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| MSDK\_INIT\_AND\_REGISTRATION | MSDK registration module. It can still access internet when LDM is calling `enableLDM` and LDM gets enabled. |
| LIVE\_STREAMING | Live stream module. It can still access internet when LDM is enabled. |
| RTK | RTK module. It can still access internet when LDM is enabled. |
| FIRMWARE\_UPGRADE | Firmware upgrade module. It can still access internet when LDM is enabled. |
| EXPERENCE\_IMPROVEMENT | Experience improvement module. It can still access internet when LDM is enabled. |
| USER\_ACCOUNT | User account module. It can still access internet when LDM is enabled. |
| LTE | LTE module. It can still access internet when LDM is enabled. |
| FLY\_SAFE | Fly safe module. It can still access internet when LDM is enabled. |
| REAL\_NAME | Real name module. It can still access internet when LDM is enabled. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found