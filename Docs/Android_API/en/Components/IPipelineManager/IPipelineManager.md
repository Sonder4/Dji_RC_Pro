**Navigation:** [IPipelineManager](IPipelineManager.md)

---

# class IPipelineManager

|  |
| --- |
| ``` interface IPipelineManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Class to manage SDK interconnection pipeline. pipeline can be used to establish a connection between MSDK and PSDK devices, and is used to transmit data between MSDK and PSDK devices. `Supported since MSDK 5.3.0`

##### Class Members:

method

[addPipelineConnectionListener](#ipipelinemanager_addpipelineconnectionlistener_inline)

###### method addPipelineConnectionListener

|  |
| --- |
| ``` void addPipelineConnectionListener(PipelineConnectionListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the pipeline connection listener. You need to call `connectPipeline` to connect the pipeline. When the pipeline is successfully connected, the connection event can be obtained through this listener. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [PipelineConnectionListener](../../Components/IPipelineManager/IPipelineManager_PipelineConnectionListener.md#ipipelinemanager_pipelineconnectionlistener) listener | *Listener of pipline connection.* |

method

[removePipelineConnectionListener](#ipipelinemanager_removepipelineconnectionlistener_inline)

###### method removePipelineConnectionListener

|  |
| --- |
| ``` void removePipelineConnectionListener(PipelineConnectionListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of the pipline connection. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| [PipelineConnectionListener](../../Components/IPipelineManager/IPipelineManager_PipelineConnectionListener.md#ipipelinemanager_pipelineconnectionlistener) listener | *Listener of pipline connection.* |

method

[clearAllPipelineConnectionListener](#ipipelinemanager_clearallpipelineconnectionlistener_inline)

###### method clearAllPipelineConnectionListener

|  |
| --- |
| ``` void clearAllPipelineConnectionListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listener of the pipline connection. `Supported since MSDK 5.3.0`

method

[getPipelines](#ipipelinemanager_getpipelines_inline)

###### method getPipelines

|  |
| --- |
| ``` Map<Integer, Pipeline> getPipelines() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Retrieves instances of the products's pipelines. The key of the map table refers to the pipeline ID that connected with the payload device, and the value refers to the pipeline instance object. When the pipeline connection is established successfully, a pipeline object is created and placed in the map table. When `disconnectPipeline` is invoked, the expired pipeline will be removed from the pipelins map table. `Supported since MSDK 5.3.0`

##### Return:

|  |  |
| --- | --- |
| Map<Integer, [Pipeline](../../Components/IPipelineManager/IPipelineManager_Pipeline.md#ipipelinemanager_pipeline)> | *The Pipeline Map object.* |

method

[connectPipeline](#ipipelinemanager_connectpipeline_inline)

###### method connectPipeline

|  |
| --- |
| ``` IDJIError connectPipeline(int pipelineId, PipelineDeviceType deviceType, TransmissionControlType transmissionControlType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Establishes the pipeline connection with the pipeline's ID. Returns an instance of the pipeline when it is connected successfully, otherwise, error information will be returned. After a successful connection, the pipeline instance can be obtained by adding the `addPipelineConnectionListener` listener. You can also call `getPipelines` to get a list of all connected pipelines synchronously. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int pipelineId | *Pipeline ID, this ID needs to be negotiated with the PSDK payload developer.* |
| [PipelineDeviceType](../../Components/IPipelineManager/IPipelineManager.md#value_mop_enum_pipelinedevicetype) deviceType | *Device type, you can set ONBOARD or PAYLOAD type.* |
| [TransmissionControlType](../../Components/IPipelineManager/IPipelineManager.md#value_mop_enum_transmissioncontroltype) transmissionControlType | *Transmission control type, you can set reliable transmission type or unreliable transmission type.* |

##### Return:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) | *Returns NULL if connecting the pipe successfully, and returns the corresponding error code if connecting the pipe fails.* |

method

[disconnectPipeline](#ipipelinemanager_disconnectpipeline_inline)

###### method disconnectPipeline

|  |
| --- |
| ``` IDJIError disconnectPipeline(int pipelineId, PipelineDeviceType deviceType, TransmissionControlType transmissionControlType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Disconnect from the PSDK device through the pipeline ID, and clear the cached data in the pipeline list. If the connection is successfully disconnected, the pipe instance will be deleted from the pipe mapping table, otherwise an error message will be returned. `Supported since MSDK 5.3.0`

##### Input Parameters:

|  |  |
| --- | --- |
| int pipelineId | *Pipeline ID, obtained from the connected pipeline instance.* |
| [PipelineDeviceType](../../Components/IPipelineManager/IPipelineManager.md#value_mop_enum_pipelinedevicetype) deviceType | *Device type, obtained from the connected pipelne instance.* |
| [TransmissionControlType](../../Components/IPipelineManager/IPipelineManager.md#value_mop_enum_transmissioncontroltype) transmissionControlType | *Transport control type, obtained from the connected pipeline instance.* |

##### Return:

|  |  |
| --- | --- |
| [IDJIError](../../Components/DJIError/DJIError.md#djierror) | *Returns NULL if disconnecting the pipe successfully, and returns the corresponding error code if disconnecting the pipe fails.* |

##### Related:

class

[Pipeline](../../Components/IPipelineManager/IPipelineManager_Pipeline.md)

class

[DataResult](../../Components/IPipelineManager/IPipelineManager_DataResult.md)

enum

[PipelineState](#value_mop_enum_pipelinestate_inline)

###### enum PipelineState

|  |
| --- |
| ``` enum PipelineState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.mop |

##### Description:

The state of pipeline connection. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| CONNECTED | The pipeline is connected. |
| DISCONNECTED | The pipeline is disconnected. |

##### Class Members:

enum

[PipelineDeviceType](#value_mop_enum_pipelinedevicetype_inline)

###### enum PipelineDeviceType

|  |
| --- |
| ``` enum PipelineDeviceType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.mop |

##### Description:

The device type of pipeline. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| ONBOARD | Onboard type. |
| PAYLOAD | Payload type. |

##### Class Members:

enum

[TransmissionControlType](#value_mop_enum_transmissioncontroltype_inline)

###### enum TransmissionControlType

|  |
| --- |
| ``` enum TransmissionControlType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.mop |

##### Description:

Transmission control type. `Supported since MSDK 5.3.0`

##### Enum Members:

|  |  |
| --- | --- |
| STABLE | Stable transmission control type. Packets will be transmitted in the order they were sent, similar to the TCP transmission protocol. |
| UNRELIABLE | Unreliable transmission control type. Packets can be corrupted or lost in transit, similar to the UDP transmission protocol. |

##### Class Members:

class

[PipelineConnectionListener](../../Components/IPipelineManager/IPipelineManager_PipelineConnectionListener.md)

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found