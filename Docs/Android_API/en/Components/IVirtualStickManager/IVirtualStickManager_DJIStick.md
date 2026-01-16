**Navigation:** [IVirtualStickManager](IVirtualStickManager.md) > [IStick](IVirtualStickManager_DJIStick.md)

---

# class IStick

|  |
| --- |
| ``` interface IStick ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

Virtual stick class. It is used to set and get virtual stick movement amount. `Supported since MSDK 5.0.0`

##### Class Members:

method

[setVerticalPosition](#ivirtualstickmanager_djistick_setverticalposition_inline)

###### method setVerticalPosition

|  |
| --- |
| ``` void setVerticalPosition(int position) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

To set the movement amount of the control stick in the vertical position (verticalPosition). The range is [-660,660]. When the control stick moves vertically to extreme left, the value of verticalPosition is -660. When the control stick moves vertically to extreme right, the value of verticalPosition is 660.

##### Input Parameters:

|  |  |
| --- | --- |
| int position | *The movement amount of the stick in vertical direction.* |

method

[getVerticalPosition](#ivirtualstickmanager_djistick_getverticalposition_inline)

###### method getVerticalPosition

|  |
| --- |
| ``` int getVerticalPosition() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

To get the movement amount of the control stick in the vertical position (verticalPosition). The range is [-660,660]. When the control stick moves vertically to extreme left, the value of verticalPosition is -660. When the control stick moves vertically to extreme right, the value of verticalPosition is 660.

##### Return:

|  |  |
| --- | --- |
| int | *The movement amount of the stick in vertical direction.* |

method

[setHorizontalPosition](#ivirtualstickmanager_djistick_sethorizontalposition_inline)

###### method setHorizontalPosition

|  |
| --- |
| ``` void setHorizontalPosition(int position) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

To set the movement amount of the control stick in the horizontal position (horizontalPosition). The range is [-660,660]. When the control stick move horizontally to extreme left, the value of verticalPosition is -660. When the control stick move horizontally to extreme right, the value of horizontalPosition is 660.

##### Input Parameters:

|  |  |
| --- | --- |
| int position | *The movement amount of the stick in horizontal direction.* |

method

[getHorizontalPosition](#ivirtualstickmanager_djistick_gethorizontalposition_inline)

###### method getHorizontalPosition

|  |
| --- |
| ``` int getHorizontalPosition() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.virtualstick |

##### Description:

To get the movement of the control stick in the horizontal position (horizontalPosition). The range is [-660,660]. When the control stick move horizontally to extreme left, the value of verticalPosition is -660. When the control stick move horizontally to extreme right, the value of horizontalPosition is 660.

##### Return:

|  |  |
| --- | --- |
| int | *The movement amount of the stick in horizontal direction.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found