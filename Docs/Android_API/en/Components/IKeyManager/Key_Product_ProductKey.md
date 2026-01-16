**Navigation:** [IKeyManager](IKeyManager.md) > [DJIKey<T>](DJIKey.md) > [ProductKey](Key_Product_ProductKey.md)

---

# class ProductKey

|  |
| --- |
| ``` @Keep  class ProductKey extends DJIProductKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |
| *Inherits From:* | `DJIProductKey` |

##### Description:

`ProductKey` provides a set of methods to get hardware product information including getting the connection status, product type and firmware version of the hardware product. `Supported since MSDK 5.0.0`

##### Class Members:

#### Basic Information

Connected Status

[KeyConnection](#key_product_connection_inline)

###### final KeyConnection

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyConnection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Connection", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` represents hardware product is connected. `Supported since MSDK 5.0.0`

Product Type

[KeyProductType](#key_product_producttype_inline)

###### final KeyProductType

|  |
| --- |
| ``` static final DJIKeyInfo<ProductType> KeyProductType = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ProductType", new SingleValueConverter<>(ProductType.class,ProductTypeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`ProductType` To get hardware product type. `Supported since MSDK 5.0.0`

Firmware Version

[KeyFirmwareVersion](#key_product_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**String To get hardware product firmware version. `Supported since MSDK 5.0.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found