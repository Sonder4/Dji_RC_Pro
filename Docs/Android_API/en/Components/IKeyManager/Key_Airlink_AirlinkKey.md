**Navigation:** [IKeyManager](IKeyManager.md) > [DJIKey<T>](DJIKey.md) > [AirLinkKey](Key_Airlink_AirlinkKey.md)

---

# class AirLinkKey

|  |
| --- |
| ``` @Keep  class AirLinkKey extends DJIAirlinkKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |
| *Inherits From:* | `DJIAirlinkKey` |

##### Description:

`AirLinkKey`provides a set of methods to set and get the airlink parameters including working frequency, channel selection mode, bandwidth of download link, signal strength, etc. The function about video stream and multi-media, please use`IMediaDataCenter`. `Supported since MSDK 5.0.0`

##### Class Members:

#### Basic Information

Connected Status

[KeyConnection](#key_airlink_connection_inline)

###### final KeyConnection

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyConnection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Connection", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that airlink is connected. `Supported since MSDK 5.0.0`

Airlink Type

[KeyAirLinkType](#key_airlink_airlinktype_inline)

###### final KeyAirLinkType

|  |
| --- |
| ``` static final DJIKeyInfo<AirLinkType> KeyAirLinkType = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AirLinkType", new SingleValueConverter<>(AirLinkType.class,AirLinkTypeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AirLinkType` get the tpye of airlink. `Supported since MSDK 5.0.0`

Signal Quality

[KeySignalQuality](#key_airlink_signalquality_inline)

###### final KeySignalQuality

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeySignalQuality = new KeySignalQuality()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Get the signal quality of the airlink. Unit: percentage. If the signal quality is below 40%, that means the signal quality is bad. If the signal quality is between 40% and 60%, that means the signal quality is normal. If the signal quality is above 60%, then means the signal quality is good. `MSDK 5.0.0 start support`

Airlink Data Rate

[KeyDynamicDataRate](#key_airlink_dynamicdatarate_inline)

###### final KeyDynamicDataRate

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyDynamicDataRate = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DynamicDataRate", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get the airlink dynamic data rate, unit: Mbps. This is the dynamic data rate for download link from aircraft to remote controller. `Supported since MSDK 5.0.0`

Frequency Interference Information

[KeyFrequencyInterference](#key_airlink_frequencypointrssiinfo_inline)

###### final KeyFrequencyInterference

|  |
| --- |
| ``` static final DJIKeyInfo<List<FrequencyInterferenceInfo>> KeyFrequencyInterference = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyInterference", new SingleValueConverter<>((Class)List.class,FrequencyPointRSSIInfo.class)).canGet(false).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("FrequencyPointRSSIInfo") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** List<`FrequencyInterferenceInfo`> Get the information of frequency interference `Supported since MSDK 5.0.0`

#### Basic Setting

Available Frequency Band Range

[KeyFrequencyBandRange](#key_airlink_airlinkfrequencybandrange_inline)

###### final KeyFrequencyBandRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<FrequencyBand>> KeyFrequencyBandRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyBandRange", new SingleValueConverter<>((Class)List.class,AirlinkFrequencyBandRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("AirlinkFrequencyBandRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** List<`FrequencyBand`> Get the available frequency band range. `Supported since MSDK 5.0.0`

Working Frequency

[KeyFrequencyBand](#key_airlink_airlinkfrequencyband_inline)

###### final KeyFrequencyBand

|  |
| --- |
| ``` static final DJIKeyInfo<FrequencyBand> KeyFrequencyBand = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyBand", new SingleValueConverter<>(FrequencyBand.class,AirlinkFrequencyBandMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("AirlinkFrequencyBand") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `FrequencyBand` Set the working frequency. `Supported since MSDK 5.0.0`

Channel Selection Mode

[KeyChannelSelectionMode](#key_airlink_channelselectionmode_inline)

###### final KeyChannelSelectionMode

|  |
| --- |
| ``` static final DJIKeyInfo<ChannelSelectionMode> KeyChannelSelectionMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ChannelSelectionMode", new SingleValueConverter<>(ChannelSelectionMode.class,ChannelSelectionModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ChannelSelectionMode` Set the channel selection mode, support auto and manual mode. `Supported since MSDK 5.0.0`

Download Link Bandwidth

[KeyBandwidth](#key_airlink_airlinkbandwidth_inline)

###### final KeyBandwidth

|  |
| --- |
| ``` static final DJIKeyInfo<Bandwidth> KeyBandwidth = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Bandwidth", new SingleValueConverter<>(Bandwidth.class,AirlinkBandwidthMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("AirlinkBandwidth") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Bandwidth` Set the bandwidth of download link. Only availble when`KeyChannelSelectionMode`is`MANUAL`. `Supported since MSDK 5.0.0`

Frequency Point Setting

[KeyFrequencyPointRange](#key_airlink_frequencypointindexrange_inline)

###### final KeyFrequencyPointRange

|  |
| --- |
| ``` static final DJIKeyInfo<IntMinMax> KeyFrequencyPointRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyPointRange", new DJIValueConverter<>(IntMinMax.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("FrequencyPointIndexRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `IntValueConfig` Get the available frequency point range. `Supported since MSDK 5.0.0`

[KeyFrequencyPoint](#key_airlink_frequencypointindex_inline)

###### final KeyFrequencyPoint

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyFrequencyPoint = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FrequencyPoint", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("FrequencyPointIndex") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set the frequency point. Only availble when`KeyChannelSelectionMode`is`MANUAL`. You can call`KeyFrequencyPointRange`to get the available frequency point range. You can call`KeyFrequencyInterference` to get the frequency point with a strong RSSI and select the frequency point between the frequency range. `Supported since MSDK 5.0.0`

#### Wireless Link Manager

Signal Quality

[KeyWlmLinkQualityLevel](#key_airlink_wlmlinkquality_inline)

###### final KeyWlmLinkQualityLevel

|  |
| --- |
| ``` static final DJIKeyInfo<WlmLinkQualityLevelInfo> KeyWlmLinkQualityLevel = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WlmLinkQualityLevel", new DJIValueConverter<>(WlmLinkQualityLevelInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("WlmLinkQuality") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `WlmLinkQualityLevelInfo` Get the signal quality level of wireless link manager. The signal quality level of OcuSync link and LTE link can be obtained through this interface. **Notic: It is recommended to use `ILTEManager` with more complete functions, support LTE authentication, and enable and disable the enhanced transmission function.** `Supported since MSDK 5.1.0`

LTE Dongle Information

[KeyWlmAircraftDongleListInfo](#key_airlink_wlmuavdongleinfo_inline)

###### final KeyWlmAircraftDongleListInfo

|  |
| --- |
| ``` static final DJIKeyInfo<WlmDongleListInfo> KeyWlmAircraftDongleListInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WlmAircraftDongleListInfo", new DJIValueConverter<>(WlmDongleListInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("WlmUavDongleInfo") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `WlmDongleListInfo` Gets the LTE dongle list information on the aircraft. The information and working status of the dongle plugged into the aircraft can be obtained through this interface. **Notic: It is recommended to use `ILTEManager` with more complete functions, support LTE authentication, and enable and disable the enhanced transmission function.** `Supported since MSDK 5.1.0`

[KeyWlmRcDongleListInfo](#key_airlink_wlmrcdongleinfo_inline)

###### final KeyWlmRcDongleListInfo

|  |
| --- |
| ``` static final DJIKeyInfo<WlmDongleListInfo> KeyWlmRcDongleListInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WlmRcDongleListInfo", new DJIValueConverter<>(WlmDongleListInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("WlmRcDongleInfo") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `WlmDongleListInfo` Gets the LTE dongle list information on the remote controller. The information and working status of the dongle plugged into the remote controller can be obtained through this interface. **Notic: It is recommended to use `ILTEManager` with more complete functions, support LTE authentication, and enable and disable the enhanced transmission function.** `Supported since MSDK 5.1.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found