**Navigation:** [IKeyManager](IKeyManager.md) > [KeyTools](KeyTools.md)

---

# class KeyTools

|  |
| --- |
| ``` @Keep  class KeyTools ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Key tool class. DJI encapsulates a series of more concise and easy-to-use methods to create `DJIKey` instance. `Supported since MSDK 5.0.0`

##### Class Members:

method

[createKey](#keytools_createkey_inline)

###### method createKey

|  |
| --- |
| ``` @NonNull  static <T> DJIKey<T> createKey(DJIKeyInfo<T> mKeyInfo, int productId, int componentIndex, int subComponentType, int subComponentIndex) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

The default method for creating Key instances. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKeyInfo<T> mKeyInfo | *A key interface in `DJIKey`. For example, CameraKey.`KeyCameraType`, which represents the key of the camera type.* |
| int productId | *Product ID of the currently connected device, defaults to 0.* |
| int componentIndex | *Gimbal load position. Please refer to `ComponentIndexType` to set the corresponding int value.* |
| int subComponentType | *Camera lens type. Please refer to `CameraLensType` to set the corresponding int value.* |
| int subComponentIndex | *Reserved parameter.* |

##### Return:

|  |  |
| --- | --- |
| static <T> DJIKey<T> | *Return instance of `DJIKey`.* |

method

[createKey](#keytools_createkeymkeyinfo_inline)

###### method createKey

|  |
| --- |
| ``` @NonNull  static <T> DJIKey<T> createKey(DJIKeyInfo<T> mKeyInfo) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Create a Key instance by passing a `DJIKeyInfo` instance of a Key interface. This method is suitable for instance creation of keys that have nothing to do with the gimbal load and camera. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKeyInfo<T> mKeyInfo | *A key interface in `DJIKey`. For example, FlightControllerKey.`KeyAircraftAttitude`, which represents the key to get the aircraft attitude.* |

##### Return:

|  |  |
| --- | --- |
| static <T> DJIKey<T> | *Return instance of `DJIKey`.* |

method

[createKey](#keytools_createkeymkeyinfocomponentindex_inline)

###### method createKey

|  |
| --- |
| ``` @NonNull  static <T> DJIKey<T> createKey(DJIKeyInfo<T> mKeyInfo, @NonNull ComponentIndexType componentIndexType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Create a Key instance by passing in the `DJIKeyInfo` instance of a Key interface and the load position of the gimbal. This method is suitable for instance creation of keys related to the gimbal load. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKeyInfo<T> mKeyInfo | *A key interface in `DJIKey`. For example, GimbalKey.`KeyGimbalReset`, which represents the key to reset the gimbal.* |
| @NonNull [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) componentIndexType | *Gimbal load position.* |

##### Return:

|  |  |
| --- | --- |
| static <T> DJIKey<T> | *Return instance of `DJIKey`.* |

method

[createCameraKey](#keytools_createcamerakeyinfocomponentindexlensindex_inline)

###### method createCameraKey

|  |
| --- |
| ``` @NonNull  static <T> DJIKey<T> createCameraKey(DJIKeyInfo<T> mKeyInfo, ComponentIndexType componentIndexType, CameraLensType cameraLensType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Create a Key instance by passing in the `DJIKeyInfo` instance of a Key interface, camera lens type and the load position of the gimbal. This method is suitable for instance creation of keys related to the camera. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIKeyInfo<T> mKeyInfo | *A key interface in `DJIKey`. For example, CameraKey.`KeyCameraType`, which represents the key of the camera type.* |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) componentIndexType | *Gimbal load position.* |
| [CameraLensType](../../Components/IKeyManager/KeyTools.md#value_common_enum_cameralenstype) cameraLensType | *Camera lens type.* |

##### Return:

|  |  |
| --- | --- |
| static <T> DJIKey<T> | *Return instance of `DJIKey`.* |

method

[createKey](#keytools_createactionkey_inline)

###### method createKey

|  |
| --- |
| ``` @NonNull  static <T, R> DJIKey.ActionKey<T, R> createKey(DJIActionKeyInfo<T, R> mKeyInfo, int productId, int componentIndex, int subComponentType, int subComponentIndex) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

The default method for creating an ActionKey instance. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIActionKeyInfo<T, R> mKeyInfo | *A key interface in `DJIKey`. For example, CameraKey.`KeyCameraType`, which represents the key of the camera type.* |
| int productId | *Product ID of the currently connected device, defaults to 0.* |
| int componentIndex | *Gimbal load position. Please refer to `ComponentIndexType` to set the corresponding int value.* |
| int subComponentType | *Camera lens type. Please refer to `CameraLensType` to set the corresponding int value.* |
| int subComponentIndex | *Reserved parameter.* |

##### Return:

|  |  |
| --- | --- |
| static <T, R> DJIKey.ActionKey<T, R> | *Return instance of `DJIKey`.* |

method

[createKey](#keytools_createactionkeymkeyinfo_inline)

###### method createKey

|  |
| --- |
| ``` @NonNull  static <T, R> DJIKey.ActionKey<T, R> createKey(DJIActionKeyInfo<T, R> mKeyInfo) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Create an ActionKey instance by passing in a DJIKeyInfo instance of a Key interface. This method is suitable for instance creation of ActionKey which is not related to gimbal load and camera. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIActionKeyInfo<T, R> mKeyInfo | *A key interface in `DJIKey`. For example, CameraKey.`KeyCameraType`, which represents the key of the camera type.* |

##### Return:

|  |  |
| --- | --- |
| static <T, R> DJIKey.ActionKey<T, R> | *Return instance of `DJIKey`.* |

method

[createKey](#keytools_createactionkeymkeyinfocomponentindex_inline)

###### method createKey

|  |
| --- |
| ``` static <T, R> DJIKey.ActionKey<T, R> createKey(DJIActionKeyInfo<T, R> mKeyInfo, ComponentIndexType componentIndexType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Create a Key instance by passing in the `DJIKeyInfo` instance of an ActionKey interface and the load position of the gimbal. This method is suitable for instance creation of ActionKey related to gimbal load. `Supported since MSDK 5.0.0`

##### Input Parameters:

|  |  |
| --- | --- |
| DJIActionKeyInfo<T, R> mKeyInfo | *A key interface in `DJIKey`. For example, CameraKey.`KeyCameraType`, which represents the key of the camera type.* |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) componentIndexType | *Gimbal load position.* |

##### Return:

|  |  |
| --- | --- |
| static <T, R> DJIKey.ActionKey<T, R> | *Return instance of `DJIKey`.* |

method

[createCameraKey](#keytools_createcamerakeymkeyinfocomponentindexlensindex_inline)

###### method createCameraKey

|  |
| --- |
| ``` @NonNull  static <T, R> DJIKey.ActionKey<T, R> createCameraKey(DJIActionKeyInfo<T, R> mKeyInfo, ComponentIndexType componentIndexType, CameraLensType cameraLensType) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Create a Key instance by passing in the `DJIKeyInfo` instance of an ActionKey interface, camera lens type and the load position of the gimbal. This method is suitable for instance creation of ActionKey related to camera.

##### Input Parameters:

|  |  |
| --- | --- |
| DJIActionKeyInfo<T, R> mKeyInfo | *A key interface in `DJIKey`. For example, CameraKey.`KeyCameraType`, which represents the key of the camera type.* |
| [ComponentIndexType](../../Components/IKeyManager/KeyTools.md#value_common_enum_componentindextype) componentIndexType | *Gimbal load position.* |
| [CameraLensType](../../Components/IKeyManager/KeyTools.md#value_common_enum_cameralenstype) cameraLensType | *Camera lens type.* |

##### Return:

|  |  |
| --- | --- |
| static <T, R> DJIKey.ActionKey<T, R> | *Return instance of `DJIKey`.* |

##### Related:

class

[DJIKeyInfo](../../Components/IKeyManager/DJIKeyInfo.md)

enum

[ComponentIndexType](#value_common_enum_componentindextype_inline)

###### enum ComponentIndexType

|  |
| --- |
| ``` enum ComponentIndexType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Gimbal load position.

##### Enum Members:

|  |  |
| --- | --- |
| LEFT\_OR\_MAIN | Portside(Main) position. |
| RIGHT | Starboard position. |
| UP | Upside location. |
| FPV | Nose（FPV）location. |
| PORT\_1 | Port 1 location. |
| PORT\_2 | Port 2 location. |
| PORT\_3 | Port 3 location. |
| PORT\_4 | Port 4 location. |
| PORT\_5 | Port 5 location. |
| PORT\_6 | Port 6 location. |
| PORT\_7 | Port 7 location. |
| PORT\_8 | Port 8 location. |

##### Class Members:

enum

[CameraLensType](#value_common_enum_cameralenstype_inline)

###### enum CameraLensType

|  |
| --- |
| ``` enum CameraLensType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Type of camera lens.

##### Enum Members:

|  |  |
| --- | --- |
| CAMERA\_LENS\_ZOOM | Zoom lens. |
| CAMERA\_LENS\_WIDE | Wide lens. |
| CAMERA\_LENS\_THERMAL | Thermal lens. |
| CAMERA\_LENS\_RGB | Multispectral RGB lenses. |
| CAMERA\_LENS\_MS\_NDVI | Multispectral NDVI lenses. |
| CAMERA\_LENS\_MS\_G | Multispectral G lenses. |
| CAMERA\_LENS\_MS\_R | Multispectral R lenses. |
| CAMERA\_LENS\_MS\_RE | Multispectral RE lenses. |
| CAMERA\_LENS\_MS\_NIR | Multispectral NIR lenses. |
| CAMERA\_LENS\_DEFAULT | Default lens. For cameras with only one lens, there is no need to distinguish between zoom, wide or infrared lenses. Use this default lens type. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found