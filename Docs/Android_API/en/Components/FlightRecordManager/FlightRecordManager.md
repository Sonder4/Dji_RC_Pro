# No Source No Source

##### 描述：

飞行记录管理类。可通过此类获取飞行记录和飞控精简日志的本地存储路径。其中飞行日志由MSDK定义并存储在移动设备上,飞控精简日志由飞控定义并存储在移动设备上。 MSDK对这些日志不做任何处理，它们只是为了方便开发人员和用户而提供的。如果用户提出保修申请，他们可以在DJI用户中心使用这些日志。只有使用MSDK的开发人员和移动设备的用户才能访问这些日志。只有使用MSDK的开发人员和移动设备的用户才能访问这些日志。随着时间的推移，较旧的日志会被较新的日志覆盖，因此开发人员和用户可以通这些日志路径把日志拷贝出来用于备份。

##### 类成员:

method

[getFlightLogPath](#flightrecordmanager_getflightlogpath_inline)

###### method getFlightLogPath

|  |
| --- |
| ``` String getFlightLogPath() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取飞行记录的本地存储路径。

##### 返回值：

|  |  |
| --- | --- |
| String | *返回飞行记录的本地存储路径。* |

method

[getFlyClogPath](#flightrecordmanager_getflyclogpath_inline)

###### method getFlyClogPath

|  |
| --- |
| ``` String getFlyClogPath() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.interfaces |

##### 描述：

获取飞控精简日志的本地存储路径。

##### 返回值：

|  |  |
| --- | --- |
| String | *返回飞控精简日志的本地存储路径。* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found