# class SDKManagerCallback

|  |
| --- |
| ``` interface SDKManagerCallback ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

SDKManager回调类。

##### 类成员:

method

[onInitProcess](#isdkmanager_sdkmanagercallback_oninitprocess_inline)

###### method onInitProcess

|  |
| --- |
| ``` void onInitProcess(DJISDKInitEvent event, int totalProcess) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

MSDK初始化进度的回调方法。

##### 输入参数：

|  |  |
| --- | --- |
| [DJISDKInitEvent](../../Components/DJISDKManager/ISDKManager_SDKManagerCallback.md#isdkmanager_sdkmanagercallback_djisdkinitevent) event | *事件类型。* |
| int totalProcess | *总进度。* |

method

[onRegisterSuccess](#isdkmanager_sdkmanagercallback_onregistersuccess_inline)

###### method onRegisterSuccess

|  |
| --- |
| ``` void onRegisterSuccess() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

MSDK注册成功后的回调方法。

method

[onRegisterFailure](#isdkmanager_sdkmanagercallback_onregisterfailure_inline)

###### method onRegisterFailure

|  |
| --- |
| ``` void onRegisterFailure(IDJIError error) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

MSDK注册完成时调用。如果注册期间发生错误，会返回一个错误码对象，如果注册成功，`error`将为`null`。

##### 输入参数：

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) error | *错误码* |

method

[onProductDisconnect](#isdkmanager_sdkmanagercallback_onproductdisconnect_inline)

###### method onProductDisconnect

|  |
| --- |
| ``` void onProductDisconnect(int productId) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

硬件设备断连后的回调方法。

##### 输入参数：

|  |  |
| --- | --- |
| int productId | *硬件设备的产品ID，默认为0。* |

method

[onProductConnect](#isdkmanager_sdkmanagercallback_onproductconnect_inline)

###### method onProductConnect

|  |
| --- |
| ``` void onProductConnect(int productId) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

硬件设备连接后的回调方法。

##### 输入参数：

|  |  |
| --- | --- |
| int productId | *硬件设备的产品ID，默认为0。* |

##### Related:

enum

[DJISDKInitEvent](#isdkmanager_sdkmanagercallback_djisdkinitevent_inline)

###### enum DJISDKInitEvent

|  |
| --- |
| ``` enum  DJISDKInitEvent ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.common.register |

##### 描述：

MSDK初始化的事件类型。

##### Enum Members:

|  |  |
| --- | --- |
| START\_TO\_INITIALIZE | MSDK开始初始化。 |
| INITIALIZE\_COMPLETE | MSDK完成初始化。 |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found