# class WatermarkDisplayContentSettings

|  |
| --- |
| ``` class WatermarkDisplayContentSettings implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The content information settings of camera watermark.

##### Class Members:

#### Members

method

[getDroneTypeEnabled](#value_camera_struct_watermarkdisplaycontentsettings_getdronetypeenabled_inline)

###### method getDroneTypeEnabled

|  |
| --- |
| ``` Boolean getDroneTypeEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

**Parameter:** Boolean `true` means to add drone type information to the watermark.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means to add drone type information to the watermark.* |

method

[setDroneTypeEnabled](#value_camera_struct_watermarkdisplaycontentsettings_setdronetypeenabled_inline)

###### method setDroneTypeEnabled

|  |
| --- |
| ``` void setDroneTypeEnabled(Boolean droneTypeEnabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set whether to add drone type information to the watermark.

##### Input Parameters:

|  |  |
| --- | --- |
| Boolean droneTypeEnabled | *`true` means to add drone type information to the watermark.* |

method

[getDroneSnEnabled](#value_camera_struct_watermarkdisplaycontentsettings_getdronesnenabled_inline)

###### method getDroneSnEnabled

|  |
| --- |
| ``` Boolean getDroneSnEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

**Parameter:** Boolean `true` means to add drone SN information to the watermark.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means to add drone SN information to the watermark.* |

method

[setDroneSnEnabled](#value_camera_struct_watermarkdisplaycontentsettings_setdronesnenabled_inline)

###### method setDroneSnEnabled

|  |
| --- |
| ``` void setDroneSnEnabled(Boolean droneSnEnabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set whether to add drone SN information to the watermark.

##### Input Parameters:

|  |  |
| --- | --- |
| Boolean droneSnEnabled | *`true` means to add drone SN information to the watermark.* |

method

[getDateTimeEnabled](#value_camera_struct_watermarkdisplaycontentsettings_getdatetimeenabled_inline)

###### method getDateTimeEnabled

|  |
| --- |
| ``` Boolean getDateTimeEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

**Parameter:** Boolean `true` means to add time information to the watermark.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means to add time information to the watermark.* |

method

[setDateTimeEnabled](#value_camera_struct_watermarkdisplaycontentsettings_setdatetimeenabled_inline)

###### method setDateTimeEnabled

|  |
| --- |
| ``` void setDateTimeEnabled(Boolean dateTimeEnabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set whether to add time information to the watermark.

##### Input Parameters:

|  |  |
| --- | --- |
| Boolean dateTimeEnabled | *`true` means to add time information to the watermark.* |

method

[getGpsLonLatEnabled](#value_camera_struct_watermarkdisplaycontentsettings_getgpslonlatenabled_inline)

###### method getGpsLonLatEnabled

|  |
| --- |
| ``` Boolean getGpsLonLatEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

**Parameter:** Boolean `true` means to add the drone longitude and latitude location information to the watermark.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means to add the drone longitude and latitude location information to the watermark.* |

method

[setGpsLonLatEnabled](#value_camera_struct_watermarkdisplaycontentsettings_setgpslonlatenabled_inline)

###### method setGpsLonLatEnabled

|  |
| --- |
| ``` void setGpsLonLatEnabled(Boolean gpsLonLatEnabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set whether to add the drone longitude and latitude location information to the watermark. You can set the display format of longitude and latitude location information through `getLonLatFormat`.

##### Input Parameters:

|  |  |
| --- | --- |
| Boolean gpsLonLatEnabled | *`true` means to add the drone longitude and latitude location information to the watermark.* |

method

[getLonLatFormat](#value_camera_struct_watermarkdisplaycontentsettings_getlonlat_inline)

###### method getLonLatFormat

|  |
| --- |
| ``` LonLatFormat getLonLatFormat() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the display format of the latitude and longitude in the watermark.

##### Return:

|  |  |
| --- | --- |
| LonLatFormat | *Return the display format of the latitude and longitude in the watermark.* |

method

[setLonLatFormat](#value_camera_struct_watermarkdisplaycontentsettings_setlonlat_inline)

###### method setLonLatFormat

|  |
| --- |
| ``` void setLonLatFormat(LonLatFormat lonLatFormat) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set the display format of the latitude and longitude in the watermark.

##### Input Parameters:

|  |  |
| --- | --- |
| LonLatFormat lonLatFormat | *The display format of the latitude and longitude in the watermark.* |

method

[getGpsAltitudeEnabled](#value_camera_struct_watermarkdisplaycontentsettings_getgpsaltitudeenabled_inline)

###### method getGpsAltitudeEnabled

|  |
| --- |
| ``` Boolean getGpsAltitudeEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

`true` means to add aircraft altitude information to the watermark.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means to add aircraft altitude information to the watermark.* |

method

[setGpsAltitudeEnabled](#value_camera_struct_watermarkdisplaycontentsettings_setgpsaltitudeenabled_inline)

###### method setGpsAltitudeEnabled

|  |
| --- |
| ``` void setGpsAltitudeEnabled(Boolean gpsAltitudeEnabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set whether to add aircraft altitude information to the watermark.

##### Input Parameters:

|  |  |
| --- | --- |
| Boolean gpsAltitudeEnabled | *`true` means to add aircraft altitude information to the watermark.* |

method

[getLengthUnit](#value_camera_struct_watermarkdisplaycontentsettings_getunit_inline)

###### method getLengthUnit

|  |
| --- |
| ``` LengthUnit getLengthUnit() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get the display unit for height in the watermark.

##### Return:

|  |  |
| --- | --- |
| LengthUnit | *Returns the display unit for height in the watermark.* |

method

[setLengthUnit](#value_camera_struct_watermarkdisplaycontentsettings_setunit_inline)

###### method setLengthUnit

|  |
| --- |
| ``` void setLengthUnit(LengthUnit lengthUnit) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set the display unit for height in the watermark.

##### Input Parameters:

|  |  |
| --- | --- |
| LengthUnit lengthUnit | *The display unit for height in the watermark.* |

method

[getUserCustomStringEnabled](#value_camera_struct_watermarkdisplaycontentsettings_getusercustomstringenabled_inline)

###### method getUserCustomStringEnabled

|  |
| --- |
| ``` Boolean getUserCustomStringEnabled() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

`true` means to add custom copy information to the watermark.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means to add custom copy information to the watermark.* |

method

[setUserCustomStringEnabled](#value_camera_struct_watermarkdisplaycontentsettings_setusercustomstringenabled_inline)

###### method setUserCustomStringEnabled

|  |
| --- |
| ``` void setUserCustomStringEnabled(Boolean userCustomStringEnabled) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set whether to add custom copy information to the watermark.

##### Input Parameters:

|  |  |
| --- | --- |
| Boolean userCustomStringEnabled | *`true` means to add custom copy information to the watermark.* |

method

[getScreenLocation](#value_camera_struct_watermarkdisplaycontentsettings_getlocate_inline)

###### method getScreenLocation

|  |
| --- |
| ``` ControlLocation getScreenLocation() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To get where the watermark is displayed on the camera screen.

##### Return:

|  |  |
| --- | --- |
| ControlLocation | *Return where the watermark is displayed on the camera screen.* |

method

[setScreenLocation](#value_camera_struct_watermarkdisplaycontentsettings_setlocate_inline)

###### method setScreenLocation

|  |
| --- |
| ``` void setScreenLocation(ControlLocation screenLocation) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

To set where the watermark is displayed on the camera screen.

##### Input Parameters:

|  |  |
| --- | --- |
| ControlLocation screenLocation | *Where the watermark is displayed on the camera screen.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found