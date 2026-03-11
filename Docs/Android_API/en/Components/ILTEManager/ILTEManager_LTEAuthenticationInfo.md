**Navigation:** [ILTEManager](ILTEManager.md) > [LTEAuthenticationInfo](ILTEManager_LTEAuthenticationInfo.md)

---

# class LTEAuthenticationInfo

|  |
| --- |
| ``` @Keep  class LTEAuthenticationInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

LTE authentication information. Including whether authenticated, the authenticated mobile phone number, the last authentication time and the remaining time of authentication. `Supported since MSDK 5.2.0`

##### Class Members:

method

[isLTEAuthenticationAvailable](#iltemanager_lteauthenticationinfo_islteauthenticationavailable_inline)

###### method isLTEAuthenticationAvailable

|  |
| --- |
| ``` boolean isLTEAuthenticationAvailable() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets whether the LTE authentication function is available.

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` indicates that the LTE authentication feature is available.* |

method

[isLTEAuthenticated](#iltemanager_lteauthenticationinfo_islteauthenticated_inline)

###### method isLTEAuthenticated

|  |
| --- |
| ``` boolean isLTEAuthenticated() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets whether the drone is LTE authenticated.

##### Return:

|  |  |
| --- | --- |
| boolean | *`true` means the drone is LTE authenticated.* |

method

[getLTEAuthenticatedPhoneAreaCode](#iltemanager_lteauthenticationinfo_getlteauthenticatedphoneareacode_inline)

###### method getLTEAuthenticatedPhoneAreaCode

|  |
| --- |
| ``` String getLTEAuthenticatedPhoneAreaCode() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets the area code of the mobile phone number for this drone's LTE authentication.

##### Return:

|  |  |
| --- | --- |
| String | *Returns the area code of the mobile phone number for this drone's LTE authentication.* |

method

[getLTEAuthenticatedPhoneNumber](#iltemanager_lteauthenticationinfo_getlteauthenticatedphonenumber_inline)

###### method getLTEAuthenticatedPhoneNumber

|  |
| --- |
| ``` String getLTEAuthenticatedPhoneNumber() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets the mobile phone number for LTE authentication of this drone.

##### Return:

|  |  |
| --- | --- |
| String | *Returns the mobile phone number for LTE authentication of this drone.* |

method

[getLTELastAuthenticatedTime](#iltemanager_lteauthenticationinfo_getltelastauthenticatedtime_inline)

###### method getLTELastAuthenticatedTime

|  |
| --- |
| ``` long getLTELastAuthenticatedTime() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Gets the UTC timestamp of the last LTE authentication for this drone.

##### Return:

|  |  |
| --- | --- |
| long | *Returns the UTC timestamp of the last LTE authentication for this drone.* |

method

[getLTEAuthenticatedRemainingTime](#iltemanager_lteauthenticationinfo_getlteauthenticatedremainingtime_inline)

###### method getLTEAuthenticatedRemainingTime

|  |
| --- |
| ``` long getLTEAuthenticatedRemainingTime() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.lte |

##### Description:

Get the remaining UTC timestamp of this drone's LTE authentication. LTE authentication is valid for 30 days by default.

##### Return:

|  |  |
| --- | --- |
| long | *Returns the remaining UTC timestamp of this drone's LTE authentication.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found