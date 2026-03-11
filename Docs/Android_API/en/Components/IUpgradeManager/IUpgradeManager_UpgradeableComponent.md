**Navigation:** [IUpgradeManager](IUpgradeManager.md) > [UpgradeableComponent](IUpgradeManager_UpgradeableComponent.md)

---

# class UpgradeableComponent

|  |
| --- |
| ``` class UpgradeableComponent ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade |

##### Description:

Upgradable component information class. It is used to obtain the type of the upgradeable component, whether it is upgradeable or not, the current firmware version and release note, and the latest firmware version and release note on the server. `Supported since MSDK 5.1.0`

##### Class Members:

method

[getComponentType](#iupgrademanager_upgradeablecomponent_getcomponenttype_inline)

###### method getComponentType

|  |
| --- |
| ``` ComponentType getComponentType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade |

##### Description:

Gets the Component type.

##### Return:

|  |  |
| --- | --- |
| [ComponentType](../../Components/IUpgradeManager/IUpgradeManager.md#iupgrademanager_componenttype) | *Returns the Component type.* |

method

[getState](#iupgrademanager_upgradeablecomponent_getstate_inline)

###### method getState

|  |
| --- |
| ``` UpgradeableComponentState getState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade |

##### Description:

Gets the upgradeable state.

##### Return:

|  |  |
| --- | --- |
| [UpgradeableComponentState](../../Components/IUpgradeManager/IUpgradeManager.md#iupgrademanager_upgradeablecomponentstate) | *Returns the upgradeable state.* |

method

[getFirmwareInformation](#iupgrademanager_upgradeablecomponent_getfirmwareinformation_inline)

###### method getFirmwareInformation

|  |
| --- |
| ``` FirmwareInformation getFirmwareInformation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade |

##### Description:

Gets the current firmware information.

##### Return:

|  |  |
| --- | --- |
| [FirmwareInformation](../../Components/IUpgradeManager/IUpgradeManager_FirmwareInformation.md#iupgrademanager_firmwareinformation) | *Returns the current firmware information.* |

method

[getLatestFirmwareInformation](#iupgrademanager_upgradeablecomponent_getlatestfirmwareinformation_inline)

###### method getLatestFirmwareInformation

|  |
| --- |
| ``` FirmwareInformation getLatestFirmwareInformation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.upgrade |

##### Description:

Gets the latest firmware information.

##### Return:

|  |  |
| --- | --- |
| [FirmwareInformation](../../Components/IUpgradeManager/IUpgradeManager_FirmwareInformation.md#iupgrademanager_firmwareinformation) | *Returns the latest firmware information.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found