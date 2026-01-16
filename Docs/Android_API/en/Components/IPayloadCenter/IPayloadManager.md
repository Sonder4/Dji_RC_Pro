**Navigation:** [IPayloadCenter](IPayloadCenter.md) > [IPayloadManager](IPayloadManager.md)

---

# class IPayloadManager

|  |
| --- |
| ``` interface IPayloadManager ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

The payload management class provides functions such as get payload information, get and set widget information, and data receiving and sending. `Supported since MSDK 5.2.0`

##### Class Members:

method

[addPayloadBasicInfoListener](#ipayloadmanager_addpayloadbasicinfolistener_inline)

###### method addPayloadBasicInfoListener

|  |
| --- |
| ``` void addPayloadBasicInfoListener(@NonNull PayloadBasicInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the listener of paylaod basic information. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PayloadBasicInfoListener](../../Components/IPayloadCenter/IPayloadManager_PayloadBasicInfoListener.md#ipayloadmanager_payloadbasicinfolistener) listener | *Listener of payload basic information.* |

method

[removePayloadBasicInfoListener](#ipayloadmanager_removepayloadbasicinfolistener_inline)

###### method removePayloadBasicInfoListener

|  |
| --- |
| ``` void removePayloadBasicInfoListener(@NonNull PayloadBasicInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of paylaod basic information. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PayloadBasicInfoListener](../../Components/IPayloadCenter/IPayloadManager_PayloadBasicInfoListener.md#ipayloadmanager_payloadbasicinfolistener) listener | *Listener of payload basic information.* |

method

[clearAllPayloadBasicInfoListener](#ipayloadmanager_clearallpayloadbasicinfolistener_inline)

###### method clearAllPayloadBasicInfoListener

|  |
| --- |
| ``` void clearAllPayloadBasicInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of paylaod basic information. `Supported since MSDK 5.2.0`

method

[addPayloadWidgetInfoListener](#ipayloadmanager_addpayloadwidgetinfolistener_inline)

###### method addPayloadWidgetInfoListener

|  |
| --- |
| ``` void addPayloadWidgetInfoListener(@NonNull PayloadWidgetInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the listener of widget information. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PayloadWidgetInfoListener](../../Components/IPayloadCenter/IPayloadManager_PayloadWidgetInfoListener.md#ipayloadmanager_payloadwidgetinfolistener) listener | *Listener of widget information.* |

method

[removePayloadWidgetInfoListener](#ipayloadmanager_removepayloadwidgetinfolistener_inline)

###### method removePayloadWidgetInfoListener

|  |
| --- |
| ``` void removePayloadWidgetInfoListener(@NonNull PayloadWidgetInfoListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of widget information. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PayloadWidgetInfoListener](../../Components/IPayloadCenter/IPayloadManager_PayloadWidgetInfoListener.md#ipayloadmanager_payloadwidgetinfolistener) listener | *Listener of widget information.* |

method

[clearAllPayloadWidgetInfoListener](#ipayloadmanager_clearallpayloadwidgetinfolistener_inline)

###### method clearAllPayloadWidgetInfoListener

|  |
| --- |
| ``` void clearAllPayloadWidgetInfoListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of widget information. `Supported since MSDK 5.2.0`

method

[addPayloadDataListener](#ipayloadmanager_addpayloaddatalistener_inline)

###### method addPayloadDataListener

|  |
| --- |
| ``` void addPayloadDataListener(@NonNull final PayloadDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the listener of payload data. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull final [PayloadDataListener](../../Components/IPayloadCenter/IPayloadManager_PayloadDataListener.md#ipayloadmanager_payloaddatalistener) listener | *Listener of payload data.* |

method

[removePayloadDataListener](#ipayloadmanager_removepayloaddatalistener_inline)

###### method removePayloadDataListener

|  |
| --- |
| ``` void removePayloadDataListener(@NonNull PayloadDataListener listener) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove the listener of payload data. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [PayloadDataListener](../../Components/IPayloadCenter/IPayloadManager_PayloadDataListener.md#ipayloadmanager_payloaddatalistener) listener | *Listener of payload data.* |

method

[clearAllPayloadDataListener](#ipayloadmanager_clearallpayloaddatalistener_inline)

###### method clearAllPayloadDataListener

|  |
| --- |
| ``` void clearAllPayloadDataListener() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Remove all the listeners of payload data. `Supported since MSDK 5.2.0`

method

[pullWidgetInfoFromPayload](#ipayloadmanager_pullwidgetinfofrompayload_inline)

###### method pullWidgetInfoFromPayload

|  |
| --- |
| ``` void pullWidgetInfoFromPayload(@NonNull CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Pull widget information from the payload. After the aircraft is successfully connected to the payload, the MSDK will pull the widget information from the payload once by default. You can call the `addPayloadWidgetInfoListener` to get the widget information. If the listener does not have any callback information, you can try to call this interface to retrieve the widget information from the payload. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[setWidgetValue](#ipayloadmanager_setwidgetvalue_inline)

###### method setWidgetValue

|  |
| --- |
| ``` void setWidgetValue(@NonNull WidgetValue value, @NonNull final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Sets the value for different widget types in the payload. `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| @NonNull [WidgetValue](../../Components/IPayloadCenter/Value_Payload_Struct_WidgetValue.md#value_payload_struct_widgetvalue) value | *The widget value that needs to be set.* |
| @NonNull final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

method

[sendDataToPayload](#ipayloadmanager_senddatatopayload_inline)

###### method sendDataToPayload

|  |
| --- |
| ``` void sendDataToPayload(byte[] data, @NonNull final CommonCallbacks.CompletionCallback callback) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.interfaces |

##### Description:

Send command data to the payload. The maximum bandwidth of this channel is 3KBytes/s. **Note: M300 RTK can only send a maximum of 100 bytes at a time. Other supported aircraft can send a maximum of 255 bytes at a time.** `Supported since MSDK 5.2.0`

##### Input Parameters:

|  |  |
| --- | --- |
| byte[] data | *Data to be sent to the payload.* |
| @NonNull final [CommonCallbacks](../../Components/DJICommonCallbacks/DJICommonCallbacks.md#djicommoncallbacks).[CompletionCallback](../../Components/DJICommonCallbacks/DJICommonCallbacks_CompletionCallback.md#djicommoncallbacks_completioncallback) callback | *Return the callback of execution result.* |

##### Related:

class

[PayloadBasicInfoListener](../../Components/IPayloadCenter/IPayloadManager_PayloadBasicInfoListener.md)

class

[PayloadWidgetInfoListener](../../Components/IPayloadCenter/IPayloadManager_PayloadWidgetInfoListener.md)

class

[PayloadDataListener](../../Components/IPayloadCenter/IPayloadManager_PayloadDataListener.md)

class

[PayloadBasicInfo](../../Components/IPayloadCenter/IPayloadManager_PayloadBasicInfo.md)

class

[PayloadWidgetInfo](../../Components/IPayloadCenter/IPayloadManager_PayloadWidgetInfo.md)

class

[PayloadWidget](../../Components/IPayloadCenter/IPayloadManager_PayloadWidget.md)

class

[FloatingWindowWidget](../../Components/IPayloadCenter/IPayloadManager_FloatingWindowWidget.md)

class

[SpeakerWidget](../../Components/IPayloadCenter/IPayloadManager_SpeakerWidget.md)

class

[TextInputBoxWidget](../../Components/IPayloadCenter/IPayloadManager_TextInputBoxWidget.md)

class

[IconFilePath](../../Components/IPayloadCenter/IPayloadManager_IconFilePath.md)

class

[SubItems](../../Components/IPayloadCenter/IPayloadManager_SubItems.md)

class

[CustomizeRcButtonConfig](../../Components/IPayloadCenter/Value_Payload_Struct_CustomizeRcButtonConfig.md)

enum

[WidgetType](#value_payload_enum_widgettype_inline)

###### enum WidgetType

|  |
| --- |
| ``` enum WidgetType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.payload |

##### Description:

Widget type. `Supported since MSDK 5.2.0`

##### Enum Members:

|  |  |
| --- | --- |
| BUTTON | Button widget, usually the default value is 0. When the button is pressed, the widget value will change to 1. |
| SWITCH | Switch widget, when the switch status is "ON", the widget value will be 1, when the switch status is "OFF", the widget value will change to 0. |
| RANGE | Range widget, which can return widget value in the range of [0,100]. |
| LIST | List widget, which can return an int value in the range of [0, N]. N is determined by the firmware of the third-party payload device manufacturer. The number of sub items returned by the getSubItems method in PayloadWidget will be N+1. |
| INPUT | Input widget,which can be set to any int value, and the default value is determined by the firmware of the payload equipment manufacturer. |

##### Class Members:

class

[WidgetValue](../../Components/IPayloadCenter/Value_Payload_Struct_WidgetValue.md)

enum

[PayloadType](#value_payload_enum_payloadtype_inline)

###### enum PayloadType

|  |
| --- |
| ``` enum PayloadType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.payload |

##### Description:

Payload type. `Supported since MSDK 5.2.0`

##### Enum Members:

|  |  |
| --- | --- |
| SKYPORT\_1 | SkyPort 1.0。 |
| SKYPORT\_2 | SkyPort 2.0。 |
| X\_PORT | XPort(SkyPort2.0 + Standard gimbal). |

##### Class Members:

enum

[PayloadProductPhaseType](#value_payload_enum_payloadproductphasetype_inline)

###### enum PayloadProductPhaseType

|  |
| --- |
| ``` enum PayloadProductPhaseType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.payload |

##### Description:

负载产品阶段。 `Supported since MSDK 5.2.0`

##### Enum Members:

|  |  |
| --- | --- |
| PHASE\_DEVELOPMENT | Development phase. |
| PHASE\_RELEASE | Release phase. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found