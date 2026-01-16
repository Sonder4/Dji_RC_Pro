# class FileInfo

|  |
| --- |
| ``` class FileInfo ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

文件传输方式的文件信息类。包括需要传输的类型，数据和文件。

##### 类成员:

method

[setUploadType](#imegaphonemanager_fileinfo_setuploadtype_inline)

###### method setUploadType

|  |
| --- |
| ``` void setUploadType(UploadType uploadType) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

设置传输的文件类型。包括本地音频文件数据类型和TTS二进制数据类型。 当类型为本地音频文件数据时，你需要调用`setFile`设置本地音频文件路径。当类型为TTS二进制数据时，则需要调用`setData`设置TTS二进制数据。

##### 输入参数：

|  |  |
| --- | --- |
| [UploadType](../../Components/FlightRecordManager/IMegaphoneManager.md#imegaphonemanager_uploadtype) uploadType | *传输的文件类型。* |

method

[getUploadType](#imegaphonemanager_fileinfo_getuploadtype_inline)

###### method getUploadType

|  |
| --- |
| ``` UploadType getUploadType() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

获取传输的文件类型。

##### 返回值：

|  |  |
| --- | --- |
| [UploadType](../../Components/FlightRecordManager/IMegaphoneManager.md#imegaphonemanager_uploadtype) | *返回传输的文件类型。* |

method

[setFile](#imegaphonemanager_fileinfo_setfile_inline)

###### method setFile

|  |
| --- |
| ``` void setFile(File file) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

设置需要传输给喊话器的音频文件路径。

##### 输入参数：

|  |  |
| --- | --- |
| File file | *需要传输给喊话器的音频文件路径。* |

method

[getFile](#imegaphonemanager_fileinfo_getfile_inline)

###### method getFile

|  |
| --- |
| ``` File getFile() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

获取需要传输给喊话器的音频文件路径。

##### 返回值：

|  |  |
| --- | --- |
| File | *返回需要传输给喊话器的音频文件路径。* |

method

[setData](#imegaphonemanager_fileinfo_setdata_inline)

###### method setData

|  |
| --- |
| ``` void setData(byte[] data) ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

设置需要传输给喊话器的TTS二进制数据。

##### 输入参数：

|  |  |
| --- | --- |
| byte[] data | *需要传输给喊话器的TTS二进制数据。* |

method

[getData](#imegaphonemanager_fileinfo_getdata_inline)

###### method getData

|  |
| --- |
| ``` byte[] getData() ``` |

|  |  |
| --- | --- |
| *包：* | dji.v5.manager.drone.megaphone |

##### 描述：

获取需要传输给喊话器的TTS二进制数据。

##### 返回值：

|  |  |
| --- | --- |
| byte[] | *返回需要传输给喊话器的TTS二进制数据。* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found