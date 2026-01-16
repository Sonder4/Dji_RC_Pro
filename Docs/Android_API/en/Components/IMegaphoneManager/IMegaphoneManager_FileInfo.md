**Navigation:** [IMegaphoneManager](IMegaphoneManager.md) > [FileInfo](IMegaphoneManager_FileInfo.md)

---

# class FileInfo

|  |
| --- |
| ``` class FileInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

The file information class for the file transfer method, including the types, data and files that need to be transferred. `Supported since MSDK 5.0.0`

##### Class Members:

method

[setUploadType](#imegaphonemanager_fileinfo_setuploadtype_inline)

###### method setUploadType

|  |
| --- |
| ``` void setUploadType(UploadType uploadType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To set the file type transferred, including local audio file data type and TTS binary data type. When the type is local audio file data, you need to call `setFile` to set the local audio file path. When the type is TTS binary data, you need to call `setData` to set the TTS binary data.

##### Input Parameters:

|  |  |
| --- | --- |
| [UploadType](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_uploadtype) uploadType | *File type transferred.* |

method

[getUploadType](#imegaphonemanager_fileinfo_getuploadtype_inline)

###### method getUploadType

|  |
| --- |
| ``` UploadType getUploadType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To get the file type transferred.

##### Return:

|  |  |
| --- | --- |
| [UploadType](../../Components/IMegaphoneManager/IMegaphoneManager.md#imegaphonemanager_uploadtype) | *Return the file type transferred.* |

method

[setFile](#imegaphonemanager_fileinfo_setfile_inline)

###### method setFile

|  |
| --- |
| ``` void setFile(File file) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To set the path of the audio file that needs to be transferred to the megaphone.

##### Input Parameters:

|  |  |
| --- | --- |
| File file | *The path of the audio file that needs to be transferred to the megaphone.* |

method

[getFile](#imegaphonemanager_fileinfo_getfile_inline)

###### method getFile

|  |
| --- |
| ``` File getFile() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To get the path of the audio file that needs to be transferred to the megaphone.

##### Return:

|  |  |
| --- | --- |
| File | *Return the path of the audio file that needs to be transferred to the megaphone.* |

method

[setData](#imegaphonemanager_fileinfo_setdata_inline)

###### method setData

|  |
| --- |
| ``` void setData(byte[] data) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To set the TTS binary data that needs to be transmitted to the megaphone.

##### Input Parameters:

|  |  |
| --- | --- |
| byte[] data | *The TTS binary data that needs to be transmitted to the megaphone.* |

method

[getData](#imegaphonemanager_fileinfo_getdata_inline)

###### method getData

|  |
| --- |
| ``` byte[] getData() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.megaphone |

##### Description:

To get the TTS binary data that needs to be transmitted to the megaphone.

##### Return:

|  |  |
| --- | --- |
| byte[] | *Return the TTS binary data that needs to be transmitted to the megaphone.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found