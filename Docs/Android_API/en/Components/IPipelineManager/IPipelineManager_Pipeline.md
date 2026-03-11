**Navigation:** [IPipelineManager](IPipelineManager.md) > [Pipeline](IPipelineManager_Pipeline.md)

---

# class Pipeline

|  |
| --- |
| ``` class Pipeline ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Pipeline class, which can read and write data to the pipeline. `Supported since MSDK 5.3.0`

##### Class Members:

method

[getPipelineState](#ipipelinemanager_pipeline_getpipelinestate_inline)

###### method getPipelineState

|  |
| --- |
| ``` PipelineState getPipelineState() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Gets the connection state of the pipeline.

##### Return:

|  |  |
| --- | --- |
| [PipelineState](../../Components/IPipelineManager/IPipelineManager.md#value_mop_enum_pipelinestate) | *Returns the connection state of the pipeline.* |

method

[getId](#ipipelinemanager_pipeline_getid_inline)

###### method getId

|  |
| --- |
| ``` int getId() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Gets the pipeline ID, MSDK and PSDK need to use the agreed pipeline ID to read and write data.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the pipeline ID.* |

method

[getTransmissionControlType](#ipipelinemanager_pipeline_gettransmissioncontroltype_inline)

###### method getTransmissionControlType

|  |
| --- |
| ``` TransmissionControlType getTransmissionControlType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Gets the transmission control type of pipeline.

##### Return:

|  |  |
| --- | --- |
| [TransmissionControlType](../../Components/IPipelineManager/IPipelineManager.md#value_mop_enum_transmissioncontroltype) | *Returns the transmission control type of pipeline.* |

method

[getPipelineDeviceType](#ipipelinemanager_pipeline_getpipelinedevicetype_inline)

###### method getPipelineDeviceType

|  |
| --- |
| ``` PipelineDeviceType getPipelineDeviceType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Gets the device type of pipeline.

##### Return:

|  |  |
| --- | --- |
| [PipelineDeviceType](../../Components/IPipelineManager/IPipelineManager.md#value_mop_enum_pipelinedevicetype) | *Returns the device type of pipeline.* |

method

[writeData](#ipipelinemanager_pipeline_writedata_inline)

###### method writeData

|  |
| --- |
| ``` DataResult writeData(byte[] data) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Send data to the PSDK device through the pipeline.

##### Input Parameters:

|  |  |
| --- | --- |
| byte[] data | *The data to be sent.* |

##### Return:

|  |  |
| --- | --- |
| [DataResult](../../Components/IPipelineManager/IPipelineManager_DataResult.md#ipipelinemanager_dataresult) | *Return the sending result.* |

method

[readData](#ipipelinemanager_pipeline_readdata_inline)

###### method readData

|  |
| --- |
| ``` DataResult readData(byte[] data) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.mop |

##### Description:

Read data from PSDK device through pipeline.

##### Input Parameters:

|  |  |
| --- | --- |
| byte[] data | *The data to be read.* |

##### Return:

|  |  |
| --- | --- |
| [DataResult](../../Components/IPipelineManager/IPipelineManager_DataResult.md#ipipelinemanager_dataresult) | *Return the reading result.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found