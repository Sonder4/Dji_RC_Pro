# class DJIBatteryKey

|  |
| --- |
| ``` class DJIBatteryKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`DJIBatteryKey`provides a set of methods to manage the battery status and information. You can set componentIndex to get the related battery status and information: 0 means battery 1 in DJI Pilot, 1 means batter 2 in DJI Pilot. `Supported since MSDK 5.0`

##### Class Members:

#### Basic Information

Connection Status

[KeyConnection](#key_battery_connection_inline)

###### final KeyConnection

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyConnection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Connection", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that battery is connected. `Supported since MSDK 5.0`

Fully Charged

[KeyFullChargeCapacity](#key_battery_fullchargecapacity_inline)

###### final KeyFullChargeCapacity

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyFullChargeCapacity = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FullChargeCapacity", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Get the battery capacity when the battery is fully charged. Unit: mAh. As the battery is continuously used, the capacity of the battery when fully charged will decrease over time. `Supported since MSDK 5.0`

Remaining Battery

[KeyChargeRemaining](#key_battery_chargeremaining_inline)

###### final KeyChargeRemaining

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyChargeRemaining = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ChargeRemaining", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To get remaining battery capacity. Unit: mAh. `Supported since MSDK 5.0`

Percentage of Remaining Battery

[KeyChargeRemainingInPercent](#key_battery_chargeremaininginpercent_inline)

###### final KeyChargeRemainingInPercent

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyChargeRemainingInPercent = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ChargeRemainingInPercent", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Get the percentage of remaining battery power. If you need to get the total power percentage of all batteries, you can get all the remaining battery power and battery capacity by setting different componentIndex for calculation. `Supported since MSDK 5.0`

Temperature

[KeyBatteryTemperature](#key_battery_batterytemperature_inline)

###### final KeyBatteryTemperature

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyBatteryTemperature = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"BatteryTemperature", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double Get the battery temperature. Unit: Celsius. Range interval: [-128, 127]. `Supported since MSDK 5.0`

Voltage

[KeyVoltage](#key_battery_voltage_inline)

###### final KeyVoltage

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyVoltage = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Voltage", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Get the total battery voltage. Unit: mV. `Supported since MSDK 5.0`

Current

[KeyCurrent](#key_battery_current_inline)

###### final KeyCurrent

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCurrent = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Current", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Get the real-time current consumption of the battery. Unit: mA. Negative values indicate that the battery is discharging, and positive values indicate that it is charging. `Supported since MSDK 5.0`

Number of Discharges

[KeyNumberOfDischarges](#key_battery_numberofdischarges_inline)

###### final KeyNumberOfDischarges

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyNumberOfDischarges = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"NumberOfDischarges", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Gets the total number of discharges that a battery has experienced during its lifetime. The total number of discharges includes discharges that occur through normal use and those that are manually set. `Supported since MSDK 5.0`

Cell Information

[KeyNumberOfCells](#key_battery_numberofcells_inline)

###### final KeyNumberOfCells

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyNumberOfCells = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"NumberOfCells", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Get the number of cells contained in the battery. `Supported since MSDK 5.0`

[KeyCellVoltages](#key_battery_cellvoltages_inline)

###### final KeyCellVoltages

|  |
| --- |
| ``` static final DJIKeyInfo<List<Integer>> KeyCellVoltages = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CellVoltages", new SingleValueConverter<>((Class)List.class,BatteryCellVoltages.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** List Get the voltage of each cell contained in the battery.Unit: mV. `Supported since MSDK 5.0`

Production Date

[KeyBatteryManufacturedDate](#key_battery_batterymanufactureddate_inline)

###### final KeyBatteryManufacturedDate

|  |
| --- |
| ``` static final DJIKeyInfo<Date> KeyBatteryManufacturedDate = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"BatteryManufacturedDate", new DJIValueConverter<>(Date.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_Date` Get the production date of battery. `Supported since MSDK 5.0`

Serial Number

[KeySerialNumber](#key_battery_serialnumber_inline)

###### final KeySerialNumber

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeySerialNumber = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SerialNumber", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String Get the serial number of battery. `Supported since MSDK 5.0`

Firmware Version

[KeyFirmwareVersion](#key_battery_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String Get the firmware version of battery. `Supported since MSDK 5.0`

#### Basic Setting

Self Discharge

[KeyBatterySelfDischargeInDays](#key_battery_batteryselfdischargeindays_inline)

###### final KeyBatterySelfDischargeInDays

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyBatterySelfDischargeInDays = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"BatterySelfDischargeInDays", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Set and get the battery self-discharge days. The range interval: [1, 10]. In order to ensure the safety of the battery, when the number of days of placing the battery for a long time exceeds the number of self-discharge days, the battery will automatically discharge to the safely placing power. `Supported since MSDK 5.0`

LED

[KeyBatteryLEDsEnabled](#key_battery_batteryledsenabled_inline)

###### final KeyBatteryLEDsEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<BatteryLedsInfo> KeyBatteryLEDsEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"BatteryLEDsEnabled", new DJIValueConverter<>(BatteryLedsInfo.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `BatteryLedsInfo` Set and get battery LED status. The power LED indicator of the aircraft can be set to off only when all the battery LEDs on the aircraft are turned off. `Supported since MSDK 5.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found