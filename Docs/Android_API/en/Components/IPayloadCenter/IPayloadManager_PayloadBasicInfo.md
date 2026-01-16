**Navigation:** [IPayloadCenter](IPayloadCenter.md) > [IPayloadManager](IPayloadManager.md) > [PayloadBasicInfo](IPayloadManager_PayloadBasicInfo.md)

---

# class PayloadBasicInfo

|  |
| --- |
| ``` class PayloadBasicInfo ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Payload basic information class. `Supported since MSDK 5.2.0`

##### Class Members:

#### Members

method

[isConnected](#ipayloadmanager_payloadbasicinfo_isconnected_inline)

###### method isConnected

|  |
| --- |
| ``` boolean isConnected() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Gets whether the payload is connected.

##### Return:

|  |  |
| --- | --- |
| boolean | *Returns whether the payload is connected.* |

method

[getPayloadType](#ipayloadmanager_payloadbasicinfo_getpayloadtype_inline)

###### method getPayloadType

|  |
| --- |
| ``` PayloadType getPayloadType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Gets the paylaod type.

##### Return:

|  |  |
| --- | --- |
| [PayloadType](../../Components/IPayloadCenter/IPayloadManager.md#value_payload_enum_payloadtype) | *Returns the payload type.* |

method

[getPayloadProductName](#ipayloadmanager_payloadbasicinfo_getpayloadproductname_inline)

###### method getPayloadProductName

|  |
| --- |
| ``` String getPayloadProductName() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Gets the product name defined by the payload manufacturer.

##### Return:

|  |  |
| --- | --- |
| String | *Returns the product name defined by the payload manufacturer.* |

method

[getPayloadProductPhaseType](#ipayloadmanager_payloadbasicinfo_getpayloadproductphasetype_inline)

###### method getPayloadProductPhaseType

|  |
| --- |
| ``` PayloadProductPhaseType getPayloadProductPhaseType() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Gets the current payload product phase.

##### Return:

|  |  |
| --- | --- |
| [PayloadProductPhaseType](../../Components/IPayloadCenter/IPayloadManager.md#value_payload_enum_payloadproductphasetype) | *Returns the current payload product phase.* |

method

[isFeatureOpened](#ipayloadmanager_payloadbasicinfo_isfeatureopened_inline)

###### method isFeatureOpened

|  |
| --- |
| ``` boolean isFeatureOpened() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Get the paylaod product feature open status. If the feature of the product is not fully turned on, the following are the possible reasons. - 1. The PSDK is not bound. - 2. The payload status is abnormal, please check the application LOG of the payload. - 3. The encryption chip is abnormal, please contact the technical support.</ li> - 4. The aircraft does not support this payload.

##### Return:

|  |  |
| --- | --- |
| boolean | *Return to the product feature open status.* |

method

[getSerialNumber](#ipayloadmanager_payloadbasicinfo_getserialnumber_inline)

###### method getSerialNumber

|  |
| --- |
| ``` String getSerialNumber() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Gets the payload serial number.

##### Return:

|  |  |
| --- | --- |
| String | *Returns the payload serial number.* |

method

[getFirmwareVersion](#ipayloadmanager_payloadbasicinfo_getfirmwareversion_inline)

###### method getFirmwareVersion

|  |
| --- |
| ``` String getFirmwareVersion() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.payload.data |

##### Description:

Gets the payload Firmware version.

##### Return:

|  |  |
| --- | --- |
| String | *Returns the payload Firmware version.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found