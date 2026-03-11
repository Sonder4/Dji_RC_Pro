# class DJIAirlinkKey

|  |
| --- |
| ``` class DJIAirlinkKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`DJIAirlinkKey`provides a set of methods to set and get the airlink parameters including working frequency, channel selection mode, bandwidth of download link, signal strength etc. The fucntion about video stream and multi-media, please use`IMediaDataCenter`. `Supported since MSDK 5.0`

##### Class Members:

#### Basic Information

Airlink type

[KeyAirLinkType](#key_airlink_airlinktype_inline)

###### final KeyAirLinkType

|  |
| --- |
| ``` static final DJIKeyInfo<AirLinkType> KeyAirLinkType = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AirLinkType", new SingleValueConverter<>(AirLinkType.class,AirLinkTypeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AirLinkType` get the tpye of airlink. `Supported since MSDK 5.0`

Airlink dynamic data rate

[KeyDynamicDataRate](#key_airlink_dynamicdatarate_inline)

###### final KeyDynamicDataRate

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyDynamicDataRate = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DynamicDataRate", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get the airlink dynamic data rate, unit: Mbps. This is the dynamic data rate for download link from aircraft to remote controller. `Supported since MSDK 5.0`

Information of frequency interference

[KeyFrequencyPointRSSIInfo](#key_airlink_frequencypointrssiinfo_inline)

###### final KeyFrequencyPointRSSIInfo

|  |
| --- |
| ``` static final DJIKeyInfo<List<FrequencyInterference>> KeyFrequencyPointRSSIInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyPointRSSIInfo", new SingleValueConverter<>((Class)List.class,FrequencyPointRSSIInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** List<`FrequencyInterference`> Get the information of frequency interference `Supported since MSDK 5.0`

#### Basic Setting

Available frequency band range

[KeyAirlinkFrequencyBandRange](#key_airlink_airlinkfrequencybandrange_inline)

###### final KeyAirlinkFrequencyBandRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<AirlinkFrequencyBand>> KeyAirlinkFrequencyBandRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AirlinkFrequencyBandRange", new SingleValueConverter<>((Class)List.class,AirlinkFrequencyBandRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** List<`AirlinkFrequencyBand`> Get the available frequency band range. `Supported since MSDK 5.0`

Working frequency

[KeyAirlinkFrequencyBand](#key_airlink_airlinkfrequencyband_inline)

###### final KeyAirlinkFrequencyBand

|  |
| --- |
| ``` static final DJIKeyInfo<AirlinkFrequencyBand> KeyAirlinkFrequencyBand = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AirlinkFrequencyBand", new SingleValueConverter<>(AirlinkFrequencyBand.class,AirlinkFrequencyBandMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AirlinkFrequencyBand` Set the working frequency. `Supported since MSDK 5.0`

Channel selection mode

[KeyChannelSelectionMode](#key_airlink_channelselectionmode_inline)

###### final KeyChannelSelectionMode

|  |
| --- |
| ``` static final DJIKeyInfo<ChannelSelectionMode> KeyChannelSelectionMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ChannelSelectionMode", new SingleValueConverter<>(ChannelSelectionMode.class,ChannelSelectionModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ChannelSelectionMode` Set the channel selection mode, support auto and manual mode. `Supported since MSDK 5.0`

Bandwidth of download link

[KeyAirlinkBandwidth](#key_airlink_airlinkbandwidth_inline)

###### final KeyAirlinkBandwidth

|  |
| --- |
| ``` static final DJIKeyInfo<AirlinkBandwidth> KeyAirlinkBandwidth = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AirlinkBandwidth", new SingleValueConverter<>(AirlinkBandwidth.class,AirlinkBandwidthMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AirlinkBandwidth` Set the bandwidth of download link. Only availble when`KeyChannelSelectionMode`is`MANUAL`. `Supported since MSDK 5.0`

Frequency point setting

[KeyFrequencyPointIndexRange](#key_airlink_frequencypointindexrange_inline)

###### final KeyFrequencyPointIndexRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntMinMax> KeyFrequencyPointIndexRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyPointIndexRange", new DJIValueConverter<>(IntMinMax.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_IntValueConfig` Get the available frequency point range. `Supported since MSDK 5.0`

[KeyFrequencyPointIndex](#key_airlink_frequencypointindex_inline)

###### final KeyFrequencyPointIndex

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyFrequencyPointIndex = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyPointIndex", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set the frequency point. Only availble when`KeyChannelSelectionMode`is`MANUAL`. You can call`KeyFrequencyPointIndexRange`to get the available frequency point range. You can call`KeyFrequencyPointRSSIInfo` to get the frequency point with a strong RSSI and select the frequency point between the frequency range. `Supported since MSDK 5.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found