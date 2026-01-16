# class ISDKManager

|  |
| --- |
| ``` interface ISDKManager ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

此类是MSDK入口管理类，用于初始化和注册MSDK，提供设备连接和设备信息获取等功能。

##### 类成员:

method

[init](#isdkmanager_init_inline)

###### method init

|  |
| --- |
| ``` void init(Context context, SDKManagerCallback callback) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

MSDK初始化，进行MSDK内部模块初始化操作，初始化完成以后，需要调用`registerApp`进行MSDK注册操作。

##### 输入参数：

|  |  |
| --- | --- |
| Context context | *应用的Context实例。* |
| [SDKManagerCallback](../../Components/DJISDKManager/ISDKManager_SDKManagerCallback.md#isdkmanager_sdkmanagercallback) callback | *返回执行结果的回调。* |

method

[isRegistered](#isdkmanager_isregistered_inline)

###### method isRegistered

|  |
| --- |
| ``` boolean isRegistered() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取MSDK注册状态。`true`表示MSDK已经注册成功。

##### 返回值：

|  |  |
| --- | --- |
| boolean | *`true`表示MSDK已经注册成功。* |

method

[registerApp](#isdkmanager_registerapp_inline)

###### method registerApp

|  |
| --- |
| ``` void registerApp() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

MSDK注册，MSDK通过互联网连接DJI服务器验证开发者在官网申请的APP KEY。本方法需要在`onInitProcess`方法中返回`ISDKManager_DJISDKInitEvent_INITIALIZE_COMPLETE`事件后开始调用，无论是注册成功还是注册失败都会在`SDKManagerCallback`中返回，成功注册后MSDK会自动开始连接硬件产品，如果硬件产品连接成功，会回调`onProductConnect`方法。 **注意： 1. 如果开发者在`init`之前使用离线License文件方式开启LDM，将无需联网就可以完成注册操作。 2. 如果使用联网下载License文件的方式开启LDM，则需要在`enableLDM`传入`MSDK_INIT_AND_REGISTRATION`，才能通过互联网连接DJI服务器验证APP KEY，完成注册流程。 3. MSDK注册成功以后，会把注册信息缓存在本地，再次启动MSDK的时候，MSDK会校验本地的缓存信息完成注册流程，无需连接互联网，直到应用重新安装后才需要重新进行联网注册操作来完成注册流程。**

method

[getSDKVersion](#isdkmanager_getsdkversion_inline)

###### method getSDKVersion

|  |
| --- |
| ``` String getSDKVersion() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取MSDK的版本号。

##### 返回值：

|  |  |
| --- | --- |
| String | *返回MSDK版本号。* |

method

[getProductCategory](#isdkmanager_getproductcategory_inline)

###### method getProductCategory

|  |
| --- |
| ``` PackageProductCategory getProductCategory() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取MSDK类型。

##### 返回值：

|  |  |
| --- | --- |
| [PackageProductCategory](../../Components/DJISDKManager/DJISDKManager.md#isdkmanager_packageproductcategory) | *返回MSDK类型。* |

method

[destroy](#isdkmanager_destroy_inline)

###### method destroy

|  |
| --- |
| ``` void destroy() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

释放MSDK占用的资源，释放后，所有回调接口都不会再收到系统的任何事件更新。 **注意：`init`和`destroy`需要在APP中成对出现。**

##### Related:

enum

[PackageProductCategory](#isdkmanager_packageproductcategory_inline)

###### enum PackageProductCategory

|  |
| --- |
| ``` enum PackageProductCategory ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.common.register |

##### 描述：

MSDK类型。

##### Enum Members:

|  |  |
| --- | --- |
| AIRCRAFT | 飞行器类型，此MSDK只支持飞行器设备。 |

##### Class Members:

class

[SDKManagerCallback](../../Components/DJISDKManager/ISDKManager_SDKManagerCallback.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found