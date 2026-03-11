**Navigation:** [IPerceptionManager](IPerceptionManager.md) > [ObstacleData](IPerceptionManager_ObstacleData.md)

---

# class ObstacleData

|  |
| --- |
| ``` class ObstacleData ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception.data |

##### Description:

obstacle data, including obstacle ranging data. `Supported since MSDK 5.1.0`

##### Class Members:

#### Members

method

[getHorizontalAngleInterval](#iperceptionmanager_obstacledata_gethorizontalangleinterval_inline)

###### method getHorizontalAngleInterval

|  |
| --- |
| ``` int getHorizontalAngleInterval() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception.data |

##### Description:

Gets the angular interval of horizontal obstacle ranging data, which is an angular interval around the aircraft at 360 degrees in the horizontal direction. for example: If the value is 1, it means that `getHorizontalObstacleDistance` has 360 data (360/1) of horizontal obstacle ranging data obtained.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the angular interval of horizontal obstacle ranging data.* |

method

[getHorizontalObstacleDistance](#iperceptionmanager_obstacledata_gethorizontalobstacledistance_inline)

###### method getHorizontalObstacleDistance

|  |
| --- |
| ``` List<Integer> getHorizontalObstacleDistance() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception.data |

##### Description:

Gets horizontal obstacle ranging data. Unit: millimeter. The angular interval of the horizontal obstacle ranging data can be obtained through `getHorizontalAngleInterval`.

##### Return:

|  |  |
| --- | --- |
| List<Integer> | *Returns horizontal obstacle ranging data.* |

method

[getUpwardObstacleDistance](#iperceptionmanager_obstacledata_getupwardobstacledistance_inline)

###### method getUpwardObstacleDistance

|  |
| --- |
| ``` int getUpwardObstacleDistance() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception.data |

##### Description:

Gets the distance measurement data of upward obstacles. Unit: millimeter. Indicates the distance between the aircraft and the upward obstacle.

##### Return:

|  |  |
| --- | --- |
| int | *Returns upward obstacle ranging data.* |

method

[getDownwardObstacleDistance](#iperceptionmanager_obstacledata_getdownwardobstacledistance_inline)

###### method getDownwardObstacleDistance

|  |
| --- |
| ``` int getDownwardObstacleDistance() ``` |

|  |  |
| --- | --- |
| *Package:* | dji.v5.manager.aircraft.perception.data |

##### Description:

Gets the distance measurement data of downward obstacles. Unit: millimeter. Indicates the distance between the aircraft and the downward obstacle.

##### Return:

|  |  |
| --- | --- |
| int | *Returns the downward obstacle ranging data.* |

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found