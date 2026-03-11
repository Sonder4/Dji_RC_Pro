**Navigation:** [IUpgradeManager](IUpgradeManager.md)

---

# class IUpgradeManager

|  |
| --- |
| ``` interface IUpgradeManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class to manage Firmware upgrade, It is used to get the latest firmware version and release note. After getting the upgradeable prompt, you can open the official DJI App to upgrade the firmware online, or call the `startOfflineUpgrade` firmware offline upgrade interface to upgrade. `Supported since MSDK 5.1.0`

##### Class Members:

method

[addUpgradeableComponentListener](#iupgrademanager_addupgradeablecomponentlistener_inline)

###### method addUpgradeableComponentListener

|  |
| --- |
| ``` void addUpgradeableComponentListener(@NonNull UpgradeableComponentListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener for upgradeable components. After obtaining the upgradeable component information, you can call `getUpgradeableComponents` to obtain the types of all upgradeable components, whether they are upgradeable or not, the current firmware information, and the latest firmware information. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [UpgradeableComponentListener](../../Components/IUpgradeManager/IUpgradeManager_UpgradeableComponentListener.md#iupgrademanager_upgradeablecomponentlistener) listener | *The listener of upgradeable components.* |

method

[removeUpgradeableComponentListener](#iupgrademanager_removeupgradeablecomponentlistener_inline)

###### method removeUpgradeableComponentListener

|  |
| --- |
| ``` void removeUpgradeableComponentListener(@NonNull UpgradeableComponentListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove listeners for upgradeable components. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [UpgradeableComponentListener](../../Components/IUpgradeManager/IUpgradeManager_UpgradeableComponentListener.md#iupgrademanager_upgradeablecomponentlistener) listener | *The listener of upgradeable components.* |

method

[removeAllUpgradeableComponentListener](#iupgrademanager_removeallupgradeablecomponentlistener_inline)

###### method removeAllUpgradeableComponentListener

|  |
| --- |
| ``` void removeAllUpgradeableComponentListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners of upgradeable components. `Supported since MSDK 5.1.0`

method

[getUpgradeableComponents](#iupgrademanager_getupgradeablecomponents_inline)

###### method getUpgradeableComponents

|  |
| --- |
| ``` List<UpgradeableComponent> getUpgradeableComponents() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets a list of upgradeable components. `Supported since MSDK 5.1.0`

##### Return:

|  |  |
| --- | --- |
| List<[UpgradeableComponent](../../Components/IUpgradeManager/IUpgradeManager_UpgradeableComponent.md#iupgrademanager_upgradeablecomponent)> | *Returns a list of upgradeable components.* |

method

[checkUpgradeableComponents](#iupgrademanager_checkupgradeablecomponents_inline)

###### method checkUpgradeableComponents

|  |
| --- |
| ``` void checkUpgradeableComponents(CommonCallbacks.CompletionCallbackWithParam<ComponentType> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Refresh upgradeable component information. Calling this interface will update the latest upgradeable information from the server. `Supported since MSDK 5.1.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<[ComponentType](../../Components/IUpgradeManager/IUpgradeManager.md#iupgrademanager_componenttype)> callback | *Return the result of the execution result.* |

method

[startOfflineUpgrade](#iupgrademanager_startofflineupgrade_inline)

###### method startOfflineUpgrade

|  |
| --- |
| ``` void startOfflineUpgrade(ComponentType type , String offlineFirmwareFilePath ,                             CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Start offline upgrade. The offline upgrade package can be obtained from the download page of the corresponding product on the DJI official website. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [ComponentType](../../Components/IUpgradeManager/IUpgradeManager.md#iupgrademanager_componenttype) type | *The type of component that needs to be upgraded.* |
| String offlineFirmwareFilePath | *The offline upgrade package path corresponding to the upgrade component. The offline upgrade package can be obtained from the download page of the corresponding product on the DJI official website.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the result of the execution result.* |

method

[addUpgradeInfoListener](#iupgrademanager_addupgradeinfolistener_inline)

###### method addUpgradeInfoListener

|  |
| --- |
| ``` void addUpgradeInfoListener(UpgradeInfoListener listener ) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add a listener for upgrade progress information. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [UpgradeInfoListener](../../Components/IUpgradeManager/IUpgradeManager_UpgradeInfoListener.md#iupgrademanager_upgradeinfolistener) listener | *The listener of upgrade progress information.* |

method

[removeUpgradeInfoListener](#iupgrademanager_removeupgradeinfolistener_inline)

###### method removeUpgradeInfoListener

|  |
| --- |
| ``` void removeUpgradeInfoListener(UpgradeInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener for upgrade progress information. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [UpgradeInfoListener](../../Components/IUpgradeManager/IUpgradeManager_UpgradeInfoListener.md#iupgrademanager_upgradeinfolistener) listener | *The listener of upgrade progress information.* |

method

[removeAllUpgradeInfoListener](#iupgrademanager_removeallupgradeinfolistener_inline)

###### method removeAllUpgradeInfoListener

|  |
| --- |
| ``` void removeAllUpgradeInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all listeners for upgrade progress information. `Supported since MSDK 5.3.0`

##### Related:

enum

[ComponentType](#iupgrademanager_componenttype_inline)

###### enum ComponentType

|  |
| --- |
| ``` enum ComponentType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade.model |

##### Description:

Upgradable component types, including aircraft and remote controller. `Supported since MSDK 5.1.0`

##### Enum Members:

|  |  |
| --- | --- |
| AIRCRAFT | Aircraft. |
| REMOTE\_CONTROLLER | Remote controller. |

##### Class Members:

class

[FirmwareInformation](../../Components/IUpgradeManager/IUpgradeManager_FirmwareInformation.md)

class

[UpgradeableComponent](../../Components/IUpgradeManager/IUpgradeManager_UpgradeableComponent.md)

enum

[UpgradeableComponentState](#iupgrademanager_upgradeablecomponentstate_inline)

###### enum UpgradeableComponentState

|  |
| --- |
| ``` enum UpgradeableComponentState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade.model |

##### Description:

Upgradable component state. `Supported since MSDK 5.1.0`

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Idle, initialized state. |
| CHECKING | Checking update, pull the latest firmware information from the server. |
| UP\_TO\_DATE | Up to date. The current firmware is the latest version, no need to upgrade. |
| UPGRADE\_AVAILABLE | Upgrade availble. The current firmware needs to be upgraded. |

##### Class Members:

class

[UpgradeInfo](../../Components/IUpgradeManager/IUpgradeManager_UpgradeInfo.md)

enum

[UpgradeProgressState](#iupgrademanager_upgradeprogressstate_inline)

###### enum UpgradeProgressState

|  |
| --- |
| ``` enum UpgradeProgressState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade |

##### Description:

Upgrade progress status. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| INITIALIZING | Upgrade initializing. |
| TRANSFERRING | Upgrade transferring. |
| TRANSFER\_FAILED | Upgrade transfer failed. |
| TRANSFER\_SUCCESS | Upgrade transfer success. |
| UPGRADING | Upgrading. |
| UPGRADE\_FAILED | Upgrade failed. |
| UPGRADE\_SUCCESS | Upgrade success. |

##### Class Members:

class

[UpgradeableComponentListener](../../Components/IUpgradeManager/IUpgradeManager_UpgradeableComponentListener.md)

class

[UpgradeInfoListener](../../Components/IUpgradeManager/IUpgradeManager_UpgradeInfoListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found