**Navigation:** [IPayloadCenter](IPayloadCenter.md) > [IIntelligentBoxManager](IIntelligentBoxManager.md)

---

# class IIntelligentBoxManager

|  |
| --- |
| ``` interface IIntelligentBoxManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The IntelligentBoxManager class provides functions to handle Manifold. `Supported since MSDK 5.15.0`

##### Class Members:

method

[addBoxInfoListener](#iintelligentboxmanager_addboxinfolistener_inline)

###### method addBoxInfoListener

|  |
| --- |
| ``` void addBoxInfoListener(@NonNull IntelligentBoxInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the listener of intelligent box basic information. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [IntelligentBoxInfoListener](../../Components/IPayloadCenter/IIntelligentBoxManager_IntelligentBoxInfoListener.md#iintelligentboxmanager_intelligentboxinfolistener) listener | *Listener of intelligent box basic information.* |

method

[removeBoxInfoListener](#iintelligentboxmanager_removeboxinfolistener_inline)

###### method removeBoxInfoListener

|  |
| --- |
| ``` void removeBoxInfoListener(@NonNull IntelligentBoxInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of intelligent box basic information. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [IntelligentBoxInfoListener](../../Components/IPayloadCenter/IIntelligentBoxManager_IntelligentBoxInfoListener.md#iintelligentboxmanager_intelligentboxinfolistener) listener | *Listener of intelligent box basic information.* |

method

[clearAllBoxInfoListener](#iintelligentboxmanager_clearallboxinfolistener_inline)

###### method clearAllBoxInfoListener

|  |
| --- |
| ``` void clearAllBoxInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of intelligent box basic information. `Supported since MSDK 5.15.0`

method

[getBoxSerialNumber](#iintelligentboxmanager_getboxserialnumber_inline)

###### method getBoxSerialNumber

|  |
| --- |
| ``` void getBoxSerialNumber(@NonNull CommonCallbacks.CompletionCallbackWithParam<String> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the Box Serial Number. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<String> callback | *Return the callback of execution result.* |

method

[enableApp](#iintelligentboxmanager_enableapp_inline)

###### method enableApp

|  |
| --- |
| ``` void enableApp(String appID, @Nullable final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Enable the app through app ID on intelligent box. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String appID | *The app id that needs to enable.* |
| @Nullable final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[disableApp](#iintelligentboxmanager_disableapp_inline)

###### method disableApp

|  |
| --- |
| ``` void disableApp(String appID, @Nullable final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Disable the app through app ID on intelligent box. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String appID | *The app id that needs to disable.* |
| @Nullable final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[uninstallApp](#iintelligentboxmanager_uninstallapp_inline)

###### method uninstallApp

|  |
| --- |
| ``` void uninstallApp(String appID, @Nullable final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Uninstall the app through app ID on intelligent box. `Supported since MSDK 5.15.0`

##### Input Parameters:

|  |  |
| --- | --- |
| String appID | *The app id that needs to uninstall.* |
| @Nullable final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

##### Related:

class

[IntelligentBoxInfoListener](../../Components/IPayloadCenter/IIntelligentBoxManager_IntelligentBoxInfoListener.md)

class

[IntelligentBoxInfo](../../Components/IPayloadCenter/IIntelligentBoxManager_IntelligentBoxInfo.md)

class

[IntelligentBoxAppInfo](../../Components/IPayloadCenter/IIntelligentBoxManager_IntelligentBoxAppInfo.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found