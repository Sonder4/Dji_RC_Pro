**Navigation:** [IDataProtectionManager](IDataProtectionManager.md)

---

# class IDataProtectionManager

|  |
| --- |
| ``` interface IDataProtectionManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

DataProtectionManager class is used to protect user data. In order to complete the function logic or report event tracking data, DJI MSDK will send user data to the DJI server in the background. You can agree or disagree with DJI MSDK to report user data through this manager. **Note: This manager only manage the user data reported by the DJI MSDK background, and does not manage the user data passed in by calling the interface.** `Supported since MSDK 5.6.0`

##### Class Members:

method

[agreeToProductImprovement](#idjidataprotectionmanager_agreetoproductimprovement_inline)

###### method agreeToProductImprovement

|  |
| --- |
| ``` void agreeToProductImprovement(boolean isAgree) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Agree or disagree with the product improvement project. Through this project, DJI MSDK will collect DJI device UUID, mobile device UUID and diagnostic and usage data for user behavior analysis. This analysis helps DJI improve products and services. DJI will take necessary data protection measures before data analysis. `Supported since MSDK 5.6.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean isAgree | *`true` to ageree product improvement project.* |

#### Product Improvement Project

method

[isAgreeToProductImprovement](#idjidataprotectionmanager_isagreetoproductimprovement_inline)

###### method isAgreeToProductImprovement

|  |
| --- |
| ``` boolean isAgreeToProductImprovement() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Gets whether to agree to the product improvement project. `Supported since MSDK 5.6.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` if it is agreed to product improvement project.* |

#### MSDK Log

method

[enableMSDKLog](#idjidataprotectionmanager_enablemsdklog_inline)

###### method enableMSDKLog

|  |
| --- |
| ``` void enableMSDKLog(boolean enabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enable or disable the MSDK log storage. This function is enabled by default. You can get the log storage path through `getMSDKLogPath`. When a problem occurs with the MSDK, you can send this MSDK log to DJI for analysis and problem solving. When there are too many logs, you can call `clearMSDKLog` to clear the logs. `Supported since MSDK 5.10.0`

##### Input Parameters:

|  |  |
| --- | --- |
| boolean enabled | *`true` means enabling the MSDK log storage function.* |

method

[isMSDKLogEnabled](#idjidataprotectionmanager_ismsdklogenabled_inline)

###### method isMSDKLogEnabled

|  |
| --- |
| ``` boolean isMSDKLogEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get whether the MSDK log storage function is enabled. `Supported since MSDK 5.10.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` means enabling the MSDK log storage function.* |

method

[getMSDKLogPath](#idjidataprotectionmanager_getmsdklogpath_inline)

###### method getMSDKLogPath

|  |
| --- |
| ``` String getMSDKLogPath() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the storage path of the MSDK. `Supported since MSDK 5.10.0`

##### Return:

|  |  |
| --- | --- |
| String | *Returns the storage path of the MSDK.* |

method

[clearMSDKLog](#idjidataprotectionmanager_clearmsdklog_inline)

###### method clearMSDKLog

|  |
| --- |
| ``` boolean clearMSDKLog() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Clear the MSDK log. `Supported since MSDK 5.10.0`

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` indicates that the MSDK log was successfully cleared.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found