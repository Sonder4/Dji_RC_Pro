**Navigation:** [IPipelineManager](IPipelineManager.md) > [DataResult](IPipelineManager_DataResult.md)

---

# class DataResult

|  |
| --- |
| ``` class DataResult ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

The result of pipeline reads and writes . You can get the result status after pipeline read and write. `Supported since MSDK 5.3.0`

##### Class Members:

method

[getLength](#ipipelinemanager_dataresult_getlength_inline)

###### method getLength

|  |
| --- |
| ``` int getLength() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Gets the length of data read and written by the pipeline.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the length of data read and written by the pipeline.* |

method

[getError](#ipipelinemanager_dataresult_geterror_inline)

###### method getError

|  |
| --- |
| ``` IDJIError getError() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Gets the result of pipeline reads and writes. If the read and write are successful, NULL will be returned, otherwise an error code will be returned.

##### Return:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) | *Returns the result of pipe reads and writes.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found