**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [LowBatteryRTHInfo](Value_FlightController_Struct_GoHomeAssessment.md)

---

# class LowBatteryRTHInfo

|  |
| --- |
| ``` class LowBatteryRTHInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Smart return-to-home information.

##### Class Members:

#### Members

method

[getRemainingFlightTime](#value_flightcontroller_struct_gohomeassessment_getremainingflighttime_inline)

###### method getRemainingFlightTime

|  |
| --- |
| ``` Integer getRemainingFlightTime() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

**Parameter:** Integer To get the estimated remaining flight time. Unit: second. The estimated remaining flight time is the time required for the aircraft to return home with 10% of the remaining battery power, including the time for the aircraft to land. If the aircraft is using the simulator, the value will be 0.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the estimated remaining flight time.* |

method

[getTimeNeededToGoHome](#value_flightcontroller_struct_gohomeassessment_gettimeneededtogohome_inline)

###### method getTimeNeededToGoHome

|  |
| --- |
| ``` Integer getTimeNeededToGoHome() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

**Parameter:** Integer To get the time required to complete smart return-to-home. Unit: second. The estimated time of the aircraft flying from present location to home point.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the time required to complete smart return-to-home.* |

method

[getTimeNeededToLand](#value_flightcontroller_struct_gohomeassessment_gettimeneededtoland_inline)

###### method getTimeNeededToLand

|  |
| --- |
| ``` Integer getTimeNeededToLand() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

**Parameter:** Integer To get the time required to complete the landing. Unit: second. The estimated time of the aircraft to complete landing in the present position.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the time required to complete landing.* |

method

[getBatteryPercentNeededToGoHome](#value_flightcontroller_struct_gohomeassessment_getbatterypercentneededtogohome_inline)

###### method getBatteryPercentNeededToGoHome

|  |
| --- |
| ``` Integer getBatteryPercentNeededToGoHome() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

**Parameter:** Integer To get the remaining power percentage when the aircraft needs smart low battery return-to-home. When the remaining power percentage is lower than this value, low battery return-to-home will be prompted.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the remaining power percentage when the aircraft needs to return home.* |

method

[getBatteryPercentNeededToLand](#value_flightcontroller_struct_gohomeassessment_getbatterypercentneededtoland_inline)

###### method getBatteryPercentNeededToLand

|  |
| --- |
| ``` Integer getBatteryPercentNeededToLand() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

**Parameter:** Integer To get the remaining power percentage when the aircraft needs of low battery landing. When the remaining power percentage is lower than this value, the low battery landing will be prompted.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the remaining power percentage when the aircraft needs to land.* |

method

[getMaxRadiusCanFlyAndGoHome](#value_flightcontroller_struct_gohomeassessment_getmaxradiuscanflyandgohome_inline)

###### method getMaxRadiusCanFlyAndGoHome

|  |
| --- |
| ``` Double getMaxRadiusCanFlyAndGoHome() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

To get the maximum safety flight radius with the home point as the center. Unit: meter. The maximum safety flight radius that the aircraft can safely return to the home point， which is calculated based on the flight altitude, distance from the home point, remaining battery power and other information. If the flight distance of the aircraft exceeds the radius, the aircraft may land on the way back.

##### Return:

|  |  |
| --- | --- |
| Double | *Return the maximum safty flight radius with the home point as the center.* |

method

[getSmartRTHCountdown](#value_flightcontroller_struct_gohomeassessment_getsmartrthcountdown_inline)

###### method getSmartRTHCountdown

|  |
| --- |
| ``` Integer getSmartRTHCountdown() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

To get the user confirmed countdown when returning home in low battery. Unit: second. Once the countdown reaches 0, the aircraft will automatically return home. Only when the `getLowBatteryRTHStatus` is `COUNTING_DOWN`, it can work.

##### Return:

|  |  |
| --- | --- |
| Integer | *Return the user confirmed countdown when returning to home in low battery.* |

method

[getLowBatteryRTHStatus](#value_flightcontroller_struct_gohomeassessment_getsmartrthstate_inline)

###### method getLowBatteryRTHStatus

|  |
| --- |
| ``` LowBatteryRTHState getLowBatteryRTHStatus() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

To get the status of low battery return-to-home.

##### Return:

|  |  |
| --- | --- |
| [LowBatteryRTHState](../../Components/IKeyManager/DJIValue.md#value_flightcontroller_enum_smartrthstate) | *Return the status of low battery return-to-home.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found