**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md) > [MultiControlStatusInfo](Value_RemoteController_Struct_RcMultiStatusMsg.md)

---

# class MultiControlStatusInfo

|  |
| --- |
| ``` class MultiControlStatusInfo implements DJIValue, JNIProguardKeepTag, ByteStream ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

This class is used to define the dual control status of the remote controller.

##### Class Members:

method

[getRcAOnline](#value_remotecontroller_struct_rcmultistatusmsg_getrcaonline_inline)

###### method getRcAOnline

|  |
| --- |
| ``` Boolean getRcAOnline() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Return A control connection status. `false` means A control lost control. The possible reason is that A control is turned off or loses connection with the aircraft.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means A control is connected.* |

method

[getRcAAppOnline](#value_remotecontroller_struct_rcmultistatusmsg_getrcaapponline_inline)

###### method getRcAAppOnline

|  |
| --- |
| ``` Boolean getRcAAppOnline() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Return the connection status of App in A control. `false` means App in A control lost control. The possible reason is that the App in A control has exited, please reopen the App in A control.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means that App in A control is connected.* |

method

[getRcBOnline](#value_remotecontroller_struct_rcmultistatusmsg_getrcbonline_inline)

###### method getRcBOnline

|  |
| --- |
| ``` Boolean getRcBOnline() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Return B control connection status.`false` means B control lost control. The possible reason is that B control is not connected to the frequency, turns off or loses connection with the aircraft.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means that B control is connected.* |

method

[getRcBAppOnline](#value_remotecontroller_struct_rcmultistatusmsg_getrcbapponline_inline)

###### method getRcBAppOnline

|  |
| --- |
| ``` Boolean getRcBAppOnline() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Return the connection status of App in B control.`false` means App in B control lost control. The possible reason is that the App in B control has exited, please reopen the App in B control.

##### Return:

|  |  |
| --- | --- |
| Boolean | *`true` means that App in B control is connected.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found