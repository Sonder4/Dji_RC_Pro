**Navigation:** [IKeyManager](IKeyManager.md)

---

# class IKeyManager

|  |
| --- |
| ``` interface IKeyManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The class to manage the MSDK keys, MSDK provides a series of methods to access and control the parameters and behaviors of the hardware module which include the set and get method of a `DJIKey`value. Also they include the listener and action of the`DJIKey`value. You can use createKey method from`KeyTools`class to create an instance of DJIKey more easily. `Supported since MSDK 5.0.0`

##### Class Members:

method

[getValue](#ikeymanager_getvalue_inline)

###### method getValue

|  |
| --- |
| ``` @Nullable     <R> R getValue(DJIKey<R> key) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Obtain the DJIValue value of the DJIKey synchronously, which is the value obtained from the MSDK cache. To get the value asynchronously from the hardware device, please call `getValue`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<R> key | *`DJIKey` instance.* |

##### Return:

|  |  |
| --- | --- |
| <R> R | *Return `DJIValue` value.* |

method

[getValue](#ikeymanager_getvalue_defaultvalue_inline)

###### method getValue

|  |
| --- |
| ``` @NonNull     <R> R getValue(DJIKey<R> key, @NonNull R defaultValue) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Obtain the DJIValue value of the DJIKey synchronously, which is the value obtained from the MSDK cache. If the value cannot be read, the set defaultValue will be returned. To get the value asynchronously from the hardware device, please call `getValue`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<R> key | *`DJIKey` instance.* |
| @NonNull R defaultValue | *When the MSDK cache value cannot be read, will return the default value.* |

##### Return:

|  |  |
| --- | --- |
| <R> R | *Return `DJIValue` value.* |

method

[getValue](#ikeymanager_getvaluecompletioncallbackwithparam_inline)

###### method getValue

|  |
| --- |
| ``` <R> void getValue(DJIKey<R> key, CommonCallbacks.CompletionCallbackWithParam<R> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Get the `DJIValue` value of `DJIKey` asynchronously, which is the value obtained from the hardware device. To read the value from the MSDK cache, please call `getValue`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<R> key | *`DJIKey` instance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<R> callback | *Return callback of the execution result.* |

method

[setValue](#ikeymanager_setvalue_inline)

###### method setValue

|  |
| --- |
| ``` <P> void setValue(DJIKey<P> key, P param, CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Set the parameter of `DJIKey`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<P> key | *`DJIKey` instance.* |
| P param | *Parameter set.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return callback of the execution result.* |

method

[performAction](#ikeymanager_performaction_inline)

###### method performAction

|  |
| --- |
| ``` <R> void performAction(DJIKey.ActionKey<?, R> key, CommonCallbacks.CompletionCallbackWithParam<R> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Execute the Action behavior of `DJIKey`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey.ActionKey<?, R> key | *`DJIKey` instance.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<R> callback | *Return callback of the execution result.* |

method

[performAction](#ikeymanager_performactionparam_inline)

###### method performAction

|  |
| --- |
| ``` <P, R> void performAction(DJIKey.ActionKey<P, R> key, P param, CommonCallbacks.CompletionCallbackWithParam<R> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Execute the Action behavior of `DJIKey` with parameters. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey.ActionKey<P, R> key | *`DJIKey` instance.* |
| P param | *Action parameter.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).CompletionCallbackWithParam<R> callback | *Return callback of the execution result.* |

method

[listen](#ikeymanager_listen_inline)

###### method listen

|  |
| --- |
| ``` <R> void listen(DJIKey<R> key, Object listenHolder, CommonCallbacks.KeyListener<R> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Listen to the `DJIValue` value of `DJIKey`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<R> key | *`DJIKey` instance.* |
| Object listenHolder | *The holder of the listener.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).KeyListener<R> callback | *Return callback of the execution result.* |

method

[listen](#ikeymanager_listengetonce_inline)

###### method listen

|  |
| --- |
| ``` <R> void listen(DJIKey<R> key, Object listenHolder, boolean getOnce, CommonCallbacks.KeyListener<R> callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Listen to the `DJIValue` value of `DJIKey`. While listening, the `DJIValue` value can be obtained asynchronously once. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<R> key | *`DJIKey` instance.* |
| Object listenHolder | *The holder of the listener.* |
| boolean getOnce | *`true` means that the value is obtained asynchronously once while listening.* |
| [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).KeyListener<R> callback | *Return callback of the execution result.* |

method

[cancelListen](#ikeymanager_cancellistenkeylistenholder_inline)

###### method cancelListen

|  |
| --- |
| ``` void cancelListen(DJIKey<?> key, Object listenHolder) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Cancel the listening of specific `DJIKey` and specific listenHolder. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<?> key | *`DJIKey` instance.* |
| Object listenHolder | *The holder of the listener.* |

method

[cancelListen](#ikeymanager_cancellistenkey_inline)

###### method cancelListen

|  |
| --- |
| ``` void cancelListen(DJIKey<?> key) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Cancel the listening of specific `DJIKey`. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKey<?> key | *`DJIKey` instance.* |

method

[cancelListen](#ikeymanager_cancellistenlistenholder_inline)

###### method cancelListen

|  |
| --- |
| ``` void cancelListen(Object listenHolder) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Cancels all listeners for a specific Holder. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| Object listenHolder | *Holder Listener.* |

##### Related:

class

[KeyTools](../../Components/IKeyManager/KeyTools.md)

class

[KeyListener](../../Components/IKeyManager/IKeyManager_KeyListener.md)

class

[DJIKey](../../Components/IKeyManager/DJIKey.md)

class

[DJIValue](../../Components/IKeyManager/DJIValue.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found