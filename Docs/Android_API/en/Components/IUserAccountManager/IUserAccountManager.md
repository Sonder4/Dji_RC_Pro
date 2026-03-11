**Navigation:** [IUserAccountManager](IUserAccountManager.md)

---

# class IUserAccountManager

|  |
| --- |
| ``` interface IUserAccountManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The class to manage user account. It is used to login and logout DJI account. When using chihiro network RTK, this interface should be called to login DJI account. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getLoginInfo](#iuseraccountmanager_getlogininfo_inline)

###### method getLoginInfo

|  |
| --- |
| ``` @NonNull     LoginInfo getLoginInfo() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get current login information, including login status and login account. `Supported since MSDK 5.0.0`

##### Return:

|  |  |
| --- | --- |
| [LoginInfo](../../Components/IUserAccountManager/IUserAccountManager_LoginInfo.md#iuseraccountmanager_logininfo) | *Return current login information.* |

method

[logInDJIUserAccount](#iuseraccountmanager_logindjiuseraccount_inline)

###### method logInDJIUserAccount

|  |
| --- |
| ``` void logInDJIUserAccount(FragmentActivity fragmentActivity, boolean isFullScreen, @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Login the DJI account by calling the login UI. When the `LoginState` is `NOT_LOGGED_IN` or `TOKEN_OUT_OF_DATE`, this interface should be called to login account. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| FragmentActivity fragmentActivity | *fragmentActivity* |
| boolean isFullScreen | *`true` means that display the login box in full screen.* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[logInDJIUserAccount](#iuseraccountmanager_logindjiuseraccountwithinterface_inline)

###### method logInDJIUserAccount

|  |
| --- |
| ``` void logInDJIUserAccount(@NonNull String userName, @NonNull String password, @Nullable String verificationCode,                                   @Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Login the DJI account by calling interface. This interface can be used in unmanned scenarios, such as nest applications. When the `LoginState` is `NOT_LOGGED_IN` or `TOKEN_OUT_OF_DATE`, this interface should be called to login account. **Note: After the login fails, if the callback of this interface returns an error of `Login`, it means that the verification code needs to be entered when you login again. Please call `getVerificationCodeImageURL` to get the image verification code.** `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull String userName | *Login user name* |
| @NonNull String password | *Login password* |
| @Nullable String verificationCode | *Login verification code* |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[getVerificationCodeImageURL](#iuseraccountmanager_getverificationcodeimageurl_inline)

###### method getVerificationCodeImageURL

|  |
| --- |
| ``` String getVerificationCodeImageURL() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the webpage link of the image verification code, you can visit this link to get the image verification code. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| String | *Returns the link to the webpage where the verification code is obtained.* |

method

[logOutDJIUserAccount](#iuseraccountmanager_logoutdjiuseraccount_inline)

###### method logOutDJIUserAccount

|  |
| --- |
| ``` void logOutDJIUserAccount(@Nullable CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Logout the DJI account. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @Nullable [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[addLoginInfoUpdateListener](#iuseraccountmanager_addlogininfoupdatelistener_inline)

###### method addLoginInfoUpdateListener

|  |
| --- |
| ``` void addLoginInfoUpdateListener(@NonNull LoginInfoUpdateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Add the listener of login information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [LoginInfoUpdateListener](../../Components/IUserAccountManager/IUserAccountManager_LoginInfoUpdateListener.md#iuseraccountmanager_logininfoupdatelistener) listener | *Listener of login information.* |

method

[removeLoginInfoUpdateListener](#iuseraccountmanager_removelogininfoupdatelistener_inline)

###### method removeLoginInfoUpdateListener

|  |
| --- |
| ``` void removeLoginInfoUpdateListener(@NonNull LoginInfoUpdateListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of login information. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [LoginInfoUpdateListener](../../Components/IUserAccountManager/IUserAccountManager_LoginInfoUpdateListener.md#iuseraccountmanager_logininfoupdatelistener) listener | *Listener of login information.* |

method

[clearAllLoginInfoUpdateListener](#iuseraccountmanager_clearalllogininfoupdatelistener_inline)

###### method clearAllLoginInfoUpdateListener

|  |
| --- |
| ``` void clearAllLoginInfoUpdateListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listener of login information. `Supported since MSDK 5.0.0`

##### Related:

class

[LoginInfo](../../Components/IUserAccountManager/IUserAccountManager_LoginInfo.md)

enum

[LoginState](#iuseraccountmanager_loginstate_inline)

###### enum LoginState

|  |
| --- |
| ``` enum LoginState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.account |

##### Description:

Login status. `Supported since MSDK 5.0.0`

##### Enum Members:

|  |  |
| --- | --- |
| NOT\_LOGGED\_IN | Not logged in. |
| LOGGED\_IN | Logged in. |
| TOKEN\_OUT\_OF\_DATE | Login expired. |

##### Class Members:

class

[LoginInfoUpdateListener](../../Components/IUserAccountManager/IUserAccountManager_LoginInfoUpdateListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found