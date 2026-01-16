**Navigation:** [IUserAccountManager](IUserAccountManager.md) > [LoginInfo](IUserAccountManager_LoginInfo.md)

---

# class LoginInfo

|  |
| --- |
| ``` class LoginInfo implements KeepProguard ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.account |

##### Description:

Login information class, which is used to get login status and login account. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getLoginState](#iuseraccountmanager_logininfo_getloginstate_inline)

###### method getLoginState

|  |
| --- |
| ``` LoginState getLoginState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.account |

##### Description:

Get login status.

##### Return:

|  |  |
| --- | --- |
| [LoginState](../../Components/IUserAccountManager/IUserAccountManager.md#iuseraccountmanager_loginstate) | *Return login status.* |

method

[getAccount](#iuseraccountmanager_logininfo_getaccount_inline)

###### method getAccount

|  |
| --- |
| ``` String getAccount() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.account |

##### Description:

Get login account. When the `LoginState` is `LOGGED_IN`, the login account can be got.

##### Return:

|  |  |
| --- | --- |
| String | *Return login account.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found