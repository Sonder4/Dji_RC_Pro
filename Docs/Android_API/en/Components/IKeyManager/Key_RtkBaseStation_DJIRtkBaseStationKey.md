# class DJIRtkBaseStationKey

|  |
| --- |
| ``` class DJIRtkBaseStationKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`DJIRtkBaseStationKey` is an object dedicated to providing access to RTK base station related attributes.

##### Class Members:

[KeyRTKReferenceStationSource](#key_rtkbasestation_rtkreferencestationsource_inline)

###### final KeyRTKReferenceStationSource

|  |
| --- |
| ``` static final DJIKeyInfo<RTKReferenceStationSource> KeyRTKReferenceStationSource = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RTKReferenceStationSource", new SingleValueConverter<>(RTKReferenceStationSource.class,RTKReferenceStationSourceMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**access:** get, set, listen **value:** `Value_RtkBaseStation_Struct_RTKReferenceStationSourceMsg` keyvalue\_key\_DJIRtkBaseStationKey\_RTKReferenceStationSource

[KeyRTKCustomNetworkSetting](#key_rtkbasestation_rtkcustomnetworksetting_inline)

###### final KeyRTKCustomNetworkSetting

|  |
| --- |
| ``` static final DJIKeyInfo<RTKCustomNetworkSetting> KeyRTKCustomNetworkSetting = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RTKCustomNetworkSetting", new DJIValueConverter<>(RTKCustomNetworkSetting.class)).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**access:** get, set **value:** `Value_RtkBaseStation_Struct_RTKCustomNetworkSetting` keyvalue\_key\_DJIRtkBaseStationKey\_RTKCustomNetworkSetting

[KeyRTKNetworkBindDevice](#key_rtkbasestation_rtknetworkbinddevice_inline)

###### final KeyRTKNetworkBindDevice

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,NetworkRTKParam> KeyRTKNetworkBindDevice = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"RTKNetworkBindDevice", EmptyValueConverter.converter,new DJIValueConverter<>(NetworkRTKParam.class)).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**access:** action **rsp:** `Value_RtkBaseStation_Struct_NetworkRTKParam` keyvalue\_key\_DJIRtkBaseStationKey\_KeyRTKNetworkBindDevice

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found