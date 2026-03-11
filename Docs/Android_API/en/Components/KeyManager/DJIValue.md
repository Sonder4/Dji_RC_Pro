# class DJIValue

|  |
| --- |
| ``` interface DJIValue ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.base |

##### Description:

Base class of all value to be used with the valued interface and the `DJIKeyManager`.

##### Class Members:

#### Gimbal

enum

[GimbalResetType](#value_gimbal_enum_gimbalresetcommand_inline)

###### enum GimbalResetType

|  |
| --- |
| ``` enum GimbalResetType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Gimbal reset mode.

##### Enum Members:

|  |  |
| --- | --- |
| PITCH\_YAW | Reset the pitch and yaw axes back to center. |
| ONLY\_PITCH | Only reset the pitch axis back to center. |
| ONLY\_YAW | Only reset the yaw axis back to center. |
| ONLY\_ROLL | Only reset the roll axis back to center. |
| PITCH\_UP\_OR\_DOWN\_WITH\_YAW\_CENTER | Reset the pitch axis down or up and reset the yaw axis back to center. Face up when the gimbal is down, face down when the gimbal is up. |
| PITCH\_UP\_OR\_DOWN | Only reset the pitch axis down or up. Face up when the gimbal is down, face down when the gimbal is up. |

##### Class Members:

class

[GimbalCalibrationStatusInfo](../../Components/KeyManager/Value_Gimbal_Struct_GimbalCalibrationState.md)

class

[GimbalAttitudeRange](../../Components/KeyManager/Value_Gimbal_Struct_GimbalAttitudeRange.md)

class

[GimbalAngleRotation](../../Components/KeyManager/Value_Gimbal_Struct_GimbalAngleRotation.md)

enum

[GimbalAngleRotationMode](#value_gimbal_enum_gimbalanglerotationmode_inline)

###### enum GimbalAngleRotationMode

|  |
| --- |
| ``` enum GimbalAngleRotationMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Gimbal angle rotation mode.

##### Enum Members:

|  |  |
| --- | --- |
| RELATIVE\_ANGLE | Relative angle mode. The rotation value set here is the angle value relative to the current angle of the gimbal. For example, pitch rotation value is set to 30 degrees, then the gimbal will rotate upward by 30 degrees based on current pitch angle. |
| ABSOLUTE\_ANGLE | Absolute angle mode. 0 degree is current attitude angle of aircraft. For example, pitch rotation value is set to 30 degrees, then the gimbal will rotate upward by 30 degrees based on current aircraft pitch angle. |

##### Class Members:

class

[GimbalSpeedRotation](../../Components/KeyManager/Value_Gimbal_Struct_GimbalSpeedRotation.md)

enum

[GimbalCalibrationState](#value_gimbal_enum_gimbalcalibrationstatus_inline)

###### enum GimbalCalibrationState

|  |
| --- |
| ``` enum GimbalCalibrationState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Gimbal automatic calibration status.

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Calibration not started or calibration completed. |
| IN\_PROGRESS | Calibrating. |
| FAILED | Calibration failed. |

##### Class Members:

enum

[GimbalMode](#value_gimbal_enum_gimbalmode_inline)

###### enum GimbalMode

|  |
| --- |
| ``` enum GimbalMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.gimbal |

##### Description:

Gimbal working mode.

##### Enum Members:

|  |  |
| --- | --- |
| FREE | Gimbal free mode. In this mode, even if the aircraft attitude changed, the gimbal and camera attitude will remain the same. |
| YAW\_FOLLOW | Gimbal yaw following mode. In this mode, the yaw angle of gimbal will changed with the yaw angle of aircraft, and the pitch and roll angles of gimbal and camera are free to be controlled |
| FPV | Gimbal FPV mode. In this mode, the yaw and roll angles of gimbal will changed with the yaw and roll angles of aircraft. |

##### Class Members:

#### Camera

Gain Mode

enum

[ThermalGainMode](#value_camera_enum_camerathermalgainmode_inline)

###### enum ThermalGainMode

|  |
| --- |
| ``` enum ThermalGainMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Thermal gain mode.

##### Enum Members:

|  |  |
| --- | --- |
| LOW | Low gain mode. |
| HIGH | High gain mode. |

##### Class Members:

Display Mode

enum

[ThermalDisplayMode](#value_camera_enum_cameradisplaymode_inline)

###### enum ThermalDisplayMode

|  |
| --- |
| ``` enum ThermalDisplayMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Display mode of infrared lens.

##### Enum Members:

|  |  |
| --- | --- |
| THERMAL\_ONLY | Infrared explicit mode. Only the thermal image of the infrared camera is displayed. |
| PIP | Split screen display mode. The images of the infrared camera and the zoom camera are displayed in a left-right split screen. |

##### Class Members:

store information

enum

[CameraStorageLocation](#value_camera_enum_camerastoragelocation_inline)

###### enum CameraStorageLocation

|  |
| --- |
| ``` enum CameraStorageLocation implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Camera store type.

##### Enum Members:

|  |  |
| --- | --- |
| SDCARD | SD Card. |
| INTERNAL | Onboard memory. |

##### Class Members:

Video Resolution

enum

[VideoResolution](#value_camera_enum_videoresolution_inline)

###### enum VideoResolution

|  |
| --- |
| ``` enum VideoResolution implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Type of video resolution.

##### Enum Members:

|  |  |
| --- | --- |
| RESOLUTION\_640x512 | The video resolution is 640x512. |
| RESOLUTION\_1920x1080 | The video resolution is 1920x1080. |
| RESOLUTION\_3840x2160 | The video resolution is 3840x2160. |

##### Class Members:

Palette

enum

[CameraThermalPalette](#value_camera_enum_camerathermalpalette_inline)

###### enum CameraThermalPalette

|  |
| --- |
| ``` enum CameraThermalPalette implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Thermal palette. Distinct colors are used to show temperature differences in the thermal image, which are related to grayscale intensity. The temperature range of the image is mapped to 256 colors and displayed in the 8-bit JPEG or MP4 format.

##### Enum Members:

|  |  |
| --- | --- |
| WHITE\_HOT | White Hot. The most commonly used pseudo color, using white for high temperatures and black for low temperatures, which is a natural association for people. |
| BLACK\_HOT | Black Hot. The opposite to White Hot, using black for warmer objects and white for cooler objects. The heat distribution of high temperature targets can be better observed when outdoors. |
| RED\_HOT | 红热。低温使用黑白色，高温使用醒目的红色，可以快速检测高温目标。适用于高对比度环境，在夜间准确、快速地找到高温目标。在DJI Pilot中显示为描红。 //TODO：这部分翻译需要对照pilot。 |
| IRONBOW1 | Iron Red. This palette displays nuanced differences in heat signatures, quickly displaying anomalies and human bodies. Hotter objects appear as light warm colors and colder objects appear as dark cool colors. |
| COLOR2 | Hot Iron. Red represents high temperatures, and cool colors represent low temperatures. It is able to identify hot targets quickly, while showing the details of cool targets. |
| ICE\_FIRE | Arctic. Uses the same palette as Medical, except switching the purple for a cool blue to better reflect temperature changes. |
| GREEN\_HOT | Green Hot. Use different colors to show small temperature differences, best suited for scenes with small heat changes. In low contrast conditions, objects and slight temperature changes can still be detected, and it is now commonly used in the medical field to better display the temperature distribution of the human body. Appears as Medical in DJI Pilot. |
| COLOR1 | Fulgurite. Dark red represents low temperatures and white represents high temperatures. The warm tone of this palette aligns with people’s association with hot temperatures. |
| RAINBOW | Rainbow 1. Similar to Medical, it reduces the warm color ratio and increases the cold color ratio for high temperature targets to better show the details of cool targets. |
| RAIN | Rainbow 2. The color transition is reduced, the warm and cold colors are moderately proportioned, which can show the details of high and low temperature targets at the same time. |
| FUSION | 融合。 |
| SEPIA | 深褐。 |
| IRONBOW2 | 铁红2。 |
| GLOWBOW | 暗光。 |

##### Class Members:

LED Close

enum

[AutoTurnOffLEDMode](#value_camera_enum_autoturnoffledmode_inline)

###### enum AutoTurnOffLEDMode

|  |
| --- |
| ``` enum AutoTurnOffLEDMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Mode that automatically turns off LED when shooting photo and recording video.

##### Enum Members:

|  |  |
| --- | --- |
| DISABLED | The arm LED will not be automatically turnde off when shooting photo and recording video. |
| FRONT\_LEDS\_ONLY | The front LED and light LED will be automaticallu turned off when shooting photo and recording video. |
| BACK\_LEDS\_ONLY | The back LED and light LED will be automaticallu turned off when shooting photo and recording video. |
| ALL\_LEDS | The front LED, back LED and night LED will be automaticallu turned off when shooting photo and recording video. |

##### Class Members:

Shutter Speed

enum

[CameraShutterSpeed](#value_camera_enum_camerashutterspeed_inline)

###### enum CameraShutterSpeed

|  |
| --- |
| ``` enum CameraShutterSpeed implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Shutter speed range.

##### Enum Members:

|  |  |
| --- | --- |
| SHUTTER\_SPEED\_AUTO | The shutter speed of camera is set to AUTO. Not available when exposure mode is `MANUAL`. |
| SHUTTER\_SPEED1\_20000 | The shutter speed of camera is set to 1/20000 s. |
| SHUTTER\_SPEED1\_16000 | The shutter speed of camera is set to 1/16000 s. |
| SHUTTER\_SPEED1\_12800 | The shutter speed of camera is set to 1/12800 s. |
| SHUTTER\_SPEED1\_10000 | The shutter speed of camera is set to 1/10000 s. |
| SHUTTER\_SPEED1\_8000 | The shutter speed of camera is set to 1/8000 s. |
| SHUTTER\_SPEED1\_6400 | The shutter speed of camera is set to 1/6400 s. |
| SHUTTER\_SPEED1\_6000 | The shutter speed of camera is set to 1/6000 s. |
| SHUTTER\_SPEED1\_5000 | The shutter speed of camera is set to 1/5000 s. |
| SHUTTER\_SPEED1\_4000 | The shutter speed of camera is set to 1/4000 s. |
| SHUTTER\_SPEED1\_3200 | The shutter speed of camera is set to 1/3200 s. |
| SHUTTER\_SPEED1\_3000 | The shutter speed of camera is set to 1/3000 s. |
| SHUTTER\_SPEED1\_2500 | The shutter speed of camera is set to 1/2500 s. |
| SHUTTER\_SPEED1\_2000 | The shutter speed of camera is set to 1/2000 s. |
| SHUTTER\_SPEED1\_1600 | The shutter speed of camera is set to 1/1600 s. |
| SHUTTER\_SPEED1\_1500 | The shutter speed of camera is set to 1/1500 s. |
| SHUTTER\_SPEED1\_1250 | The shutter speed of camera is set to 1/1250 s. |
| SHUTTER\_SPEED1\_1000 | The shutter speed of camera is set to 1/1000 s. |
| SHUTTER\_SPEED1\_800 | The shutter speed of camera is set to 1/800 s. |
| SHUTTER\_SPEED1\_725 | The shutter speed of camera is set to 1/725 s. |
| SHUTTER\_SPEED1\_640 | The shutter speed of camera is set to 1/640 s. |
| SHUTTER\_SPEED1\_500 | The shutter speed of camera is set to 1/500 s. |
| SHUTTER\_SPEED1\_400 | The shutter speed of camera is set to 1/400 s. |
| SHUTTER\_SPEED1\_350 | The shutter speed of camera is set to 1/350 s. |
| SHUTTER\_SPEED1\_320 | The shutter speed of camera is set to 1/320 s. |
| SHUTTER\_SPEED1\_250 | The shutter speed of camera is set to 1/250 s. |
| SHUTTER\_SPEED1\_240 | The shutter speed of camera is set to 1/240 s. |
| SHUTTER\_SPEED1\_200 | The shutter speed of camera is set to 1/200 s. |
| SHUTTER\_SPEED1\_180 | The shutter speed of camera is set to 1/180 s. |
| SHUTTER\_SPEED1\_160 | The shutter speed of camera is set to 1/160 s. |
| SHUTTER\_SPEED1\_125 | The shutter speed of camera is set to 1/125 s. |
| SHUTTER\_SPEED1\_120 | The shutter speed of camera is set to 1/120 s. |
| SHUTTER\_SPEED1\_100 | The shutter speed of camera is set to 1/100 s. |
| SHUTTER\_SPEED1\_90 | The shutter speed of camera is set to 1/90 s. |
| SHUTTER\_SPEED1\_80 | The shutter speed of camera is set to 1/80 s. |
| SHUTTER\_SPEED1\_60 | The shutter speed of camera is set to 1/60 s. |
| SHUTTER\_SPEED1\_50 | The shutter speed of camera is set to 1/50 s. |
| SHUTTER\_SPEED1\_40 | The shutter speed of camera is set to 1/40 s. |
| SHUTTER\_SPEED1\_30 | The shutter speed of camera is set to 1/30 s. |
| SHUTTER\_SPEED1\_25 | The shutter speed of camera is set to 1/25 s. |
| SHUTTER\_SPEED1\_20 | The shutter speed of camera is set to 1/20 s. |
| SHUTTER\_SPEED1\_15 | The shutter speed of camera is set to 1/15 s. |
| SHUTTER\_SPEED1\_12DOT5 | The shutter speed of camera is set to 1/12.5 s. |
| SHUTTER\_SPEED1\_10 | The shutter speed of camera is set to 1/10 s. |
| SHUTTER\_SPEED1\_8 | The shutter speed of camera is set to 1/8秒。 |
| SHUTTER\_SPEED1\_6DOT25 | The shutter speed of camera is set to 1/6.25 s. |
| SHUTTER\_SPEED1\_5 | The shutter speed of camera is set to 1/5 s. |
| SHUTTER\_SPEED1\_4 | The shutter speed of camera is set to 1/4 s. |
| SHUTTER\_SPEED1\_3 | The shutter speed of camera is set to 1/3 s. |
| SHUTTER\_SPEED1\_2DOT5 | The shutter speed of camera is set to 1/2.5 s. |
| SHUTTER\_SPEED1\_2 | The shutter speed of camera is set to 1/2 s. |
| SHUTTER\_SPEED1\_1DOT67 | The shutter speed of camera is set to 1/1.67 s. |
| SHUTTER\_SPEED1\_1DOT25 | The shutter speed of camera is set to 1/1.25 s. |
| SHUTTER\_SPEED1 | The shutter speed of camera is set to 1.0 s. |
| SHUTTER\_SPEED1DOT3 | The shutter speed of camera is set to 1.3 s. |
| SHUTTER\_SPEED1DOT6 | The shutter speed of camera is set to 1.6 s. |
| SHUTTER\_SPEED2 | The shutter speed of camera is set to 2.0 s. |
| SHUTTER\_SPEED2DOT5 | The shutter speed of camera is set to 2.5 s. |
| SHUTTER\_SPEED3 | The shutter speed of camera is set to 3.0 s. |
| SHUTTER\_SPEED3DOT2 | The shutter speed of camera is set to 3.2 s. |
| SHUTTER\_SPEED4 | The shutter speed of camera is set to 4.0 s. |
| SHUTTER\_SPEED5 | The shutter speed of camera is set to 5.0 s. |
| SHUTTER\_SPEED6 | The shutter speed of camera is set to 6.0 s. |
| SHUTTER\_SPEED7 | The shutter speed of camera is set to 7.0 s. |
| SHUTTER\_SPEED8 | The shutter speed of camera is set to 8.0 s. |
| SHUTTER\_SPEED9 | The shutter speed of camera is set to 9.0 s. |
| SHUTTER\_SPEED10 | The shutter speed of camera is set to 10.0 s. |
| SHUTTER\_SPEED11 | The shutter speed of camera is set to 11.0 s. |
| SHUTTER\_SPEED13 | The shutter speed of camera is set to 13.0 s. |
| SHUTTER\_SPEED15 | The shutter speed of camera is set to 15.0 s. |
| SHUTTER\_SPEED16 | The shutter speed of camera is set to 16.0 s. |
| SHUTTER\_SPEED20 | The shutter speed of camera is set to 20.0 s. |
| SHUTTER\_SPEED23 | The shutter speed of camera is set to 23.0 s. |
| SHUTTER\_SPEED25 | The shutter speed of camera is set to 25.0 s. |
| SHUTTER\_SPEED30 | The shutter speed of camera is set to 30.0 s. |
| SHUTTER\_SPEED40 | The shutter speed of camera is set to 40.0 s. |
| SHUTTER\_SPEED50 | The shutter speed of camera is set to 50.0 s. |
| SHUTTER\_SPEED60 | The shutter speed of camera is set to 60.0 s. |
| SHUTTER\_SPEED80 | The shutter speed of camera is set to 80.0 s. |
| SHUTTER\_SPEED100 | The shutter speed of camera is set to 100.0 s. |
| SHUTTER\_SPEED120 | The shutter speed of camera is set to 120.0 s. |

##### Class Members:

hybrid Zoom

class

[CameraHybridZoomSpec](../../Components/KeyManager/Value_Camera_Struct_CameraHybridZoomSpec.md)

Infrared Function

enum

[ThermalScene](#value_camera_enum_thermalscene_inline)

###### enum ThermalScene

|  |
| --- |
| ``` enum ThermalScene implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Thermal scene.

##### Enum Members:

|  |  |
| --- | --- |
| DEFAULT | Default scene. Automatically adjust DDE, ACE, SSO, Brightness and Contrast. |
| MANUAL | Customized scene. Allows manual setting of DDE, brightness and contrast. |
| INSPECTION | Inspection scene. Automatically adjust DDE, ACE, SSO, brightness and contrast, with presets optimized for inspection scenarios. |

##### Class Members:

Photo Shooting and Video Recording Storage

class

[VideoResolutionFrameRate](../../Components/KeyManager/Value_Camera_Struct_VideoResolutionFrameRate.md)

High-Res Grid Photo

enum

[SuperResolutionState](#value_camera_enum_superresolutionstatus_inline)

###### enum SuperResolutionState

|  |
| --- |
| ``` enum SuperResolutionState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The status of high-res grid photo.

##### Enum Members:

|  |  |
| --- | --- |
| SELECTING | The high-res grid photo area is selecting. You can use `KeySuperResolutionCaptureArea` to set the high-res grid photo area. |
| IN\_PROGRESS | The high-res grid photo is shooting. |
| FINISH | The high-res grid photo shooting has ended. |
| EXIT\_OUT\_OF\_STORAGE | Stop shooting, because there is no more storage. |
| EXIT\_SHOOT\_FAIL | Stop shooting, because shooting is failed. |
| EXIT\_GIMBAL\_BLOCK | Stop shooting, because the gimbal is stuck. |
| EXIT\_GIMBAL\_ABNORMAL\_MOVE | Stop shooting, because the gimbal cannot move or the gimbal is abnormal. |
| TIP\_GIMBAL\_OUT\_OF\_RANGE | Stop shooting, because the gimbal angle exceeds the maximum limit. |
| TIP\_OUT\_OF\_ZOOM | Camera zoom out of maximum range. |

##### Class Members:

class

[SuperResolutionInfo](../../Components/KeyManager/Value_Camera_Struct_SuperResolutionStateMsg.md)

Media Type

enum

[MediaFileType](#value_camera_enum_mediafiletype_inline)

###### enum MediaFileType

|  |
| --- |
| ``` enum MediaFileType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Type of media file.

##### Enum Members:

|  |  |
| --- | --- |
| JPEG | The media file type is JPEG photo. |
| DNG | The media file type is DNG photo. |
| MOV | The media file type is MOV video. |
| MP4 | The media file type is MP4 video. |

##### Class Members:

Photo Format

enum

[PhotoFileFormat](#value_camera_enum_photostorageformat_inline)

###### enum PhotoFileFormat

|  |
| --- |
| ``` enum PhotoFileFormat implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The type of photo format.

##### Enum Members:

|  |  |
| --- | --- |
| RAW | Photo format is set to RAW. |
| JPEG | Photo format is set to JPEG. |
| RAW\_JPEG | Photo format is set to RAW+JPEG. |

##### Class Members:

Video Source Type

enum

[CameraVideoStreamSourceType](#value_camera_enum_liveviewsourcecameratype_inline)

###### enum CameraVideoStreamSourceType

|  |
| --- |
| ``` enum CameraVideoStreamSourceType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Video Source Type supported by camera.

##### Enum Members:

|  |  |
| --- | --- |
| WIDE\_CAMERA | Wide. |
| ZOOM\_CAMERA | Zoom. |
| INFRARED\_CAMERA | Infrared. |

##### Class Members:

ROI

enum

[ThermalROI](#value_camera_enum_camerathermalroi_inline)

###### enum ThermalROI

|  |
| --- |
| ``` enum ThermalROI implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Thermal ROI.

##### Enum Members:

|  |  |
| --- | --- |
| FULL | Full sceen. |
| SKY\_EXCLUDED33 | The sky area is exclued (33%). |
| SKY\_EXCLUDED50 | The sky area is exclued (50%). |

##### Class Members:

Laser Working Mode

enum

[LaserWorkMode](#value_camera_enum_laserworkmode_inline)

###### enum LaserWorkMode

|  |
| --- |
| ``` enum LaserWorkMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Laser working mode.

##### Enum Members:

|  |  |
| --- | --- |
| OPEN\_ON\_DEMAND | Open the laser based on requirement. In this mode, the laser is only turned on when necessary, and the laser module can be turned on or off through `KeyLaserMeasureEnabled`. After the laser module is turned off, the laser-assisted focusing function is disabled, and the camera's focusing ability is weakened. When using the night vision module and Tyndall phenomenon occurs, you can choose this mode to turn off the laser module. The laser module will revert to enhanced mode after restarting the camera. |
| OPEN\_ALWAYS | Enhanced mode. The laser module will be turned on by default. In this mode, functions such as laser-assisted focusing, intelligent tracking, spot positioning, and laser ranging can be used normally. |

##### Class Members:

Camera Type

enum

[CameraType](#value_camera_enum_cameratype_inline)

###### enum CameraType

|  |
| --- |
| ``` enum CameraType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Supported camera types.

##### Enum Members:

|  |  |
| --- | --- |
| M30 | M30 dual light camera. |
| M30T | M30T triple light camera. |

##### Class Members:

Video Format

enum

[VideoFileFormat](#value_camera_enum_videostorageformat_inline)

###### enum VideoFileFormat

|  |
| --- |
| ``` enum VideoFileFormat implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Type of video format.

##### Enum Members:

|  |  |
| --- | --- |
| MOV | The video format is MOV. |
| MP4 | The video format is MP4. |

##### Class Members:

Panoramic Photo

enum

[PhotoPanoramaMode](#value_camera_enum_visionphotopanoramamode_inline)

###### enum PhotoPanoramaMode

|  |
| --- |
| ``` enum PhotoPanoramaMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Panoramic photo of camera.

##### Enum Members:

|  |  |
| --- | --- |
| MODE\_3x1 | The camera will take 1 column of 3 photos. Each photo uses the same aircraft heading angle and different gimbal angles to take photos. |
| MODE\_3x3 | The camera will take 9 pictures in 3 columns and 3 rows. The photos in each column were taken with different aircraft heading angles. Each row of photos is taken with different gimbal angles. |
| MODE\_SPHERE | The camera will take 24 photos in 8 columns, 3 rows and an additional photo with the gimbal facing down. The photos in each column were taken with different aircraft heading angles. Each row of photos is taken with different gimbal angles. |

##### Class Members:

Exposure Mode

enum

[CameraExposureMode](#value_camera_enum_cameraexposuremode_inline)

###### enum CameraExposureMode

|  |
| --- |
| ``` enum CameraExposureMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The type of exposure mode.

##### Enum Members:

|  |  |
| --- | --- |
| MANUAL | Exposure mode is set to manual exposure. In this mode the shutter can be adjusted manually, the aperture is fixed, and the ISO can be adjusted manually. |
| PROGRAM | Exposure mode is set to auto exposure. In this mode the shutter can be adjusted manually, the aperture is fixed, and the ISO can be adjusted manually. |
| SHUTTER\_PRIORITY | Exposure mode is set to shutter priority exposure. |
| APERTURE\_PRIORITY | Exposure mode is set to aperture priority exposurre。 |

##### Class Members:

ISO

enum

[CameraISO](#value_camera_enum_cameraiso_inline)

###### enum CameraISO

|  |
| --- |
| ``` enum CameraISO implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

ISO range.

##### Enum Members:

|  |  |
| --- | --- |
| ISO\_AUTO | ISO value is set to AUTO. Not available when exposure mode is `MANUAL`. |
| ISO\_50 | ISO value is set to 50. |
| ISO\_100 | ISO value is set to 100. |
| ISO\_200 | ISO value is set to 200. |
| ISO\_400 | ISO value is set to 400. |
| ISO\_800 | ISO value is set to 800. |
| ISO\_1600 | ISO value is set to 1600. |
| ISO\_3200 | ISO value is set to 3200. |
| ISO\_6400 | ISO value is set to 6400. |
| ISO\_12800 | ISO value is set to 12800. |
| ISO\_25600 | ISO value is set to 25600. |

##### Class Members:

Exposure Compensation

enum

[CameraExposureCompensation](#value_camera_enum_cameraexposurecompensation_inline)

###### enum CameraExposureCompensation

|  |
| --- |
| ``` enum CameraExposureCompensation implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The range of exposure compensation.

##### Enum Members:

|  |  |
| --- | --- |
| NEG\_5P0EV | Exposure compensation is set to -5.0ev. |
| NEG\_4P7EV | Exposure compensation is set to -4.7ev. |
| NEG\_4P3EV | Exposure compensation is set to -4.3ev. |
| NEG\_4P0EV | Exposure compensation is set to -4.0ev. |
| NEG\_3P7EV | Exposure compensation is set to -3.7ev. |
| NEG\_3P3EV | Exposure compensation is set to -3.3ev. |
| NEG\_3P0EV | Exposure compensation is set to -3.0ev. |
| NEG\_2P7EV | Exposure compensation is set to -2.7ev. |
| NEG\_2P3EV | Exposure compensation is set to -2.3ev. |
| NEG\_2P0EV | Exposure compensation is set to -2.0ev. |
| NEG\_1P7EV | Exposure compensation is set to -1.7ev. |
| NEG\_1P3EV | Exposure compensation is set to -1.3ev. |
| NEG\_1P0EV | Exposure compensation is set to -1.0ev. |
| NEG\_0P7EV | Exposure compensation is set to -0.7ev. |
| NEG\_0P3EV | Exposure compensation is set to -0.3ev. |
| NEG\_0EV | Exposure compensation is set to 0.0ev. |
| POS\_0P3EV | Exposure compensation is set to +0.3ev. |
| POS\_0P7EV | Exposure compensation is set to +0.7ev. |
| POS\_1P0EV | Exposure compensation is set to +1.0ev. |
| POS\_1P3EV | Exposure compensation is set to +1.3ev. |
| POS\_1P7EV | Exposure compensation is set to +1.7ev. |
| POS\_2P0EV | Exposure compensation is set to +2.0ev. |
| POS\_2P3EV | Exposure compensation is set to +2.3ev. |
| POS\_2P7EV | Exposure compensation is set to +2.7ev. |
| POS\_3P0EV | Exposure compensation is set to +3.0ev. |
| POS\_3P3EV | Exposure compensation is set to +3.3ev. |
| POS\_3P7EV | Exposure compensation is set to +3.7ev. |
| POS\_4P0EV | Exposure compensation is set to +4.0ev. |
| POS\_4P3EV | Exposure compensation is set to +4.3ev. |
| POS\_4P7EV | Exposure compensation is set to +4.7ev. |
| POS\_5P0EV | Exposure compensation is set to +5.0ev. |
| FIXED | Exposure compensation is set to FIXED. The exposure compensation of the camera lens is set by the camera to a fixed value. |

##### Class Members:

FFC Calibration Mode

enum

[ThermalFFCMode](#value_camera_enum_camerathermalffcmode_inline)

###### enum ThermalFFCMode

|  |
| --- |
| ``` enum ThermalFFCMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

FFC mode. Automatic or manual FFC calibration can be selected.

##### Enum Members:

|  |  |
| --- | --- |
| AUTO | Automatic FFC calibration. When is set to automatic FFC calibration, the Infrare lens will be automatically calibrated at a interval time. |
| MANUAL | Manual FFC calibration. After setting to manual FFC calibration, `KeyThermalTriggerFFC` can be called to perform manual FFC calibration. |

##### Class Members:

Photo and Video Storage

class

[CameraStreamSettingsInfo](../../Components/KeyManager/Value_Camera_Struct_CameraStreamSettingsMsg.md)

Water Mark

class

[CameraWatermarkSettings](../../Components/KeyManager/Value_Camera_Struct_CameraWatermarkSettings.md)

class

[WatermarkDisplayContentSettings](../../Components/KeyManager/Value_Camera_Struct_WatermarkDisplayContentSettings.md)

custom name

class

[CustomExpandNameSettings](../../Components/KeyManager/Value_Camera_Struct_CustomExpandNameSettings.md)

Storage Information

enum

[SDCardLoadState](#value_camera_enum_sdcardloadstate_inline)

###### enum SDCardLoadState

|  |
| --- |
| ``` enum SDCardLoadState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The state of the SD Card inserted.

##### Enum Members:

|  |  |
| --- | --- |
| INSERTED | The SD Card is inserted. |
| NOT\_INSERTED | The SD Card is not inserted. |

##### Class Members:

class

[CameraStorageInfos](../../Components/KeyManager/Value_Camera_Struct_CameraStorageStateMsg.md)

class

[CameraStorageInfo](../../Components/KeyManager/Value_Camera_Struct_CameraStorageState.md)

Video Frame Rate

enum

[VideoFrameRate](#value_camera_enum_videoframerate_inline)

###### enum VideoFrameRate

|  |
| --- |
| ``` enum VideoFrameRate implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Video frame rate.

##### Enum Members:

|  |  |
| --- | --- |
| RATE\_30FPS | The video frame rate is 30fps. |
| RATE\_60FPS | The video frame rate is 60fps. |

##### Class Members:

区域测温

class

[ThermalGainModeTemperatureRange](../../Components/KeyManager/Value_Camera_Struct_ThermalGainModeTemperatureRangeMsg.md)

Camera Mode

enum

[CameraFlatMode](#value_camera_enum_quickcameramode_inline)

###### enum CameraFlatMode

|  |
| --- |
| ``` enum CameraFlatMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Settable camera mode.

##### Enum Members:

|  |  |
| --- | --- |
| PHOTO\_NORMAL | Camera mode is set to normal photo mode. The M30 camera will take two photos by default, a wide-angle photo and a zoom photo. The M30T camera will take three photos by default, namely wide-angle photos, zoom photos and infrared photos. You can choose to store the photos you need through `KeyCaptureCameraStreamSettings`. |
| VIDEO\_NORMAL | Camera mode is set to normal record mode. The M30 camera will record two videos by default, a wide-angle video and a zoom video. The M30T camera will shoot three videos by default, namely wide-angle video, zoom video and infrared video. You can choose to store the videos you need through `KeyRecordCameraStreamSettings`. |
| PHOTO\_SMART | Camera mode is set to intelligent photo mode. In a dimly lit environment, the camera will automatically simulate a long exposure for shooting, which can shoot the object more clearly. It only takes effect when the zoom lens is zoomed above 5x. |
| PHOTO\_INTERVAL | Camera mode is set to interval photo mode.Set the camera to take one or more pictures continuously at a set time interval. You can set the timed photo parameters through `KeyPhotoIntervalShootSettings`. Since the camera needs a response time to take photos, the minimum photo interval needs to be set to more than 2 seconds. After setting this mode, you need to call `KeyStartShootPhoto` to take pictures regularly. During the timed photo shooting, you can call `KeyStopShootPhoto` to stop the timed photo shooting. |
| PHOTO\_REGIONAL\_SR | Camera mode is set to high-resolution matirx photo mode. In this mode, dragging to select an area in the wide-angle view, the gimbal will automatically move and take a series of 20MP photos of the selected area with the zoom camera at the current zoom ratio. All photos and generated HTML files will be saved in the same folder on the microSD card. To view the global photo and partial close-up photo, please open the HTML file with a browser on your PC for viewing. If you want to view the global photo and local close-up photo in the application, please use `KeySuperResolutionCaptureArea` to set the high-resolution matrix capture area. Use `KeySuperResolutionInfo` to monitor the camera state of high-resolution matrix shooting. After setting this mode, you need to call `KeyStartShootPhoto` to take a photo with the high-resolution matrix. During the shooting process, you can call `KeyStopShootPhoto` to stop the timed high-resolution matrix photo shooting. |
| PHOTO\_PANO | Camera mode is set to panoramic photo mode. The aircraft took a series of photos at different gimbal angles and aircraft headings. This set of photos can be stitched into panoramic photos using third-party libraries. The panorama photo mode can be set by `KeyPhotoPanoramaMode`, the panorama photo mode can be judged by `KeyIsShootingPhotoPanorama`, and the progress of panorama photo can be obtained by `KeyPhotoPanoramaProgress`. After setting this mode, you need to call `KeyStartShootPhoto` to take a panoramic photo. During the shooting process, you can call `KeyStopShootPhoto` to stop panorama shooting.  **Notic:   Panoramic photo mode can only be activated after the aircraft takes off.** |

##### Class Members:

Media File

class

[GeneratedMediaFileInfo](../../Components/KeyManager/Value_Camera_Struct_GeneratedMediaFileInfo.md)

Isotherm

enum

[ThermalIsothermUnit](#value_camera_enum_camerathermalisothermunit_inline)

###### enum ThermalIsothermUnit

|  |
| --- |
| ``` enum ThermalIsothermUnit implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Unit of isotherm.

##### Enum Members:

|  |  |
| --- | --- |
| PERCENTAGE | Percentage. |
| CELSIUS | Celsius. |

##### Class Members:

Focus Mode

enum

[CameraFocusMode](#value_camera_enum_camerafocusmode_inline)

###### enum CameraFocusMode

|  |
| --- |
| ``` enum CameraFocusMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Camera focus mode.

##### Enum Members:

|  |  |
| --- | --- |
| MANUAL | Manual focus. In this mode, user can adjust the focus by setting the focus ring value through `KeyCameraFocusRingValue`. The focus target is the center point of the screen. |
| AF | Auto focus. In this mode, user can set the focus target through `KeyCameraFocusTarget` for auto focusing. |
| AFC | Continuous auto focus. In this mode, the camera will focus on the center point of the screen. When the subject is moved, the auto focusing system can adjust the lens in real time according to the change of focus, so that the subject remains clear. |

##### Class Members:

Metering Mode

enum

[CameraMeteringMode](#value_camera_enum_camerameteringmode_inline)

###### enum CameraMeteringMode

|  |
| --- |
| ``` enum CameraMeteringMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The metering mode of camera lens.

##### Enum Members:

|  |  |
| --- | --- |
| CENTER | Center metering. In this mode, the camera will meter based on the light at the center of the screen. |
| AVERAGE | Average metering. In this mode, the camera will meter based on average screen brightness. |
| SPOT | Area metering(including spot metering). In this mode, the camera will meter based on the area brightness set by the user. |

##### Class Members:

Interval Shooting

class

[PhotoIntervalShootSettings](../../Components/KeyManager/Value_Camera_Struct_PhotoIntervalShootSettings.md)

Photo Ratio

enum

[PhotoRatio](#value_camera_enum_photoratio_inline)

###### enum PhotoRatio

|  |
| --- |
| ``` enum PhotoRatio implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Type of camera photo ratio.

##### Enum Members:

|  |  |
| --- | --- |
| RATIO\_4COLON3 | The photo ratio is set to 4:3. |
| RATIO\_16COLON9 | The photo ratio is set to 16:9. |
| RATIO\_3COLON2 | The photo ratio is set to 3:2. |
| RATIO\_SQUARE | The photo ratio is set to 1:1. |
| RATIO\_18COLON9 | The photo ratio is set to 18:9. |

##### Class Members:

Regional Temperature Measurement

class

[ThermalAreaTemperatureAggregations](../../Components/KeyManager/Value_Camera_Struct_ThermalAreaTemperatureAggregationsMsg.md)

Infrared Zoom

enum

[ThermalDigitalZoomFactor](#value_camera_enum_thermaldigitalzoomfactor_inline)

###### enum ThermalDigitalZoomFactor

|  |
| --- |
| ``` enum ThermalDigitalZoomFactor implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

IR zoom magnification.

##### Enum Members:

|  |  |
| --- | --- |
| FACTOR\_X1 | 1X. |
| FACTOR\_X2 | 2X. |
| FACTOR\_X4 | 4X. |
| FACTOR\_X8 | 8X. |

##### Class Members:

#### Common

Time Data

class

[DateTime](../../Components/KeyManager/Value_Camera_Struct_DateTime.md)

姿态数据

class

[Attitude](../../Components/KeyManager/Value_Common_Struct_Attitude.md)

浮点数据的范围

class

[DoubleMinMax](../../Components/KeyManager/Value_Common_Struct_DoubleMinMax.md)

#### Remote Controller

enum

[MultiControlAuthorityType](#value_common_enum_rcauthoritymode_inline)

###### enum MultiControlAuthorityType

|  |
| --- |
| ``` enum MultiControlAuthorityType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Define available control mode, including flight and gimbal control.

##### Enum Members:

|  |  |
| --- | --- |
| FLIGHT\_CONTROL\_AUTHORITY | Flight control. |
| GIMBAL\_LEFT\_CONTROL\_AUTHORITY | Left gimbal control. If there is only one gimbal, this mode is main gimbal control. |
| GIMBAL\_RIGHT\_CONTROL\_AUTHORITY | Right gimbal control. |
| GIMBAL\_UP\_CONTROL\_AUTHORITY | Above gimbal control. |

##### Class Members:

class

[MultiControlAuthorityTypeInfo](../../Components/KeyManager/Value_Common_Struct_RCAuthorityModeMsg.md)

class

[MultiControlLockAuthorityInfo](../../Components/KeyManager/Value_Common_Struct_RCAuthorityLockControlMsg.md)

class

[BatteryInfo](../../Components/KeyManager/Value_RemoteController_Struct_RcParamChargeRemainingInfo.md)

enum

[MultiControlChannel](#value_common_enum_rcmodechannel_inline)

###### enum MultiControlChannel

|  |
| --- |
| ``` enum MultiControlChannel implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

This class is used to define remote controller muti-control channel. If dual control needs to be realized, different remote controller should be set to different channel, and then pair frequency with the aircraft. NOTICE: If remote controller A is already set to A control, and then remote controller B is set to A control to link with the aircraft, the remote controller A will be disconnected from the aircraft and it needs to link again.

##### Enum Members:

|  |  |
| --- | --- |
| CHANNEL\_A | A control. |
| CHANNEL\_B | B control. |

##### Class Members:

class

[MultiControlStatusInfo](../../Components/KeyManager/Value_RemoteController_Struct_RcMultiStatusMsg.md)

enum

[RemoteControllerType](#value_remotecontroller_enum_remotecontrollertype_inline)

###### enum RemoteControllerType

|  |
| --- |
| ``` enum RemoteControllerType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Remote controller type.

##### Enum Members:

|  |  |
| --- | --- |
| DJI\_RC\_PLUS | DJI RC PLUS remote controller. It adopts O3 Pro high-definition image transmission technology, which can be used with aircrafts that support this image transmission technology, and transmits high-definition images in real time. With the complete function buttons of the remote control, the operation and setting of the aircraft and the camera can be completed. Support M30 series aircraft. |

##### Class Members:

class

[MultiControlLostControlInfo](../../Components/KeyManager/Value_RemoteController_Struct_RCAuthorityLostPushMsg.md)

enum

[ControlMode](#value_remotecontroller_enum_rccontrolmode_inline)

###### enum ControlMode

|  |
| --- |
| ``` enum ControlMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Remote controller control mode.

##### Enum Members:

|  |  |
| --- | --- |
| JP | Japanese mode. |
| USA | American mode. |
| CH | Chinese mode. |

##### Class Members:

enum

[PairingState](#value_remotecontroller_enum_rcpairingstate_inline)

###### enum PairingState

|  |
| --- |
| ``` enum PairingState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Remote controller frequency pairing status.

##### Enum Members:

|  |  |
| --- | --- |
| UNPAIRED | Remote controller is not pairing frequency. |
| PAIRING | Remote controller is pairing frequency . |
| PAIRED | Remote controller pairs frequency successfully. |

##### Class Members:

class

[MultiControlFlightControlAuthorityOwnerInfo](../../Components/KeyManager/Value_RemoteController_Struct_MultiRCFlightControlAuthOwnerMsg.md)

class

[MultiControlChannelInfo](../../Components/KeyManager/Value_RemoteController_Struct_RCModeChannelTypeMsg.md)

class

[FiveDimensionPressedStatus](../../Components/KeyManager/Value_RemoteController_Struct_RcFiveDimensionPressedStatus.md)

class

[FrequencyInterference](../../Components/KeyManager/Value_Airlink_Struct_FrequencyInterference.md)

#### FlightController

Smart low battery return-to-home

enum

[LowBatteryRTHState](#value_flightcontroller_enum_smartrthstate_inline)

###### enum LowBatteryRTHState

|  |
| --- |
| ``` enum LowBatteryRTHState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Status of smart low battery return-to-home.

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Smart low battery return-to-home is not triggered. The status will be reset to this status after the aircrft returns and lands. |
| COUNTING\_DOWN | The smart low battery return-to-home is triggered, and the aircraft is counting down. If the cancellation of request to return-to-home is not recevied from user in 10 seconds, the aircraft will perform return-to-home operation, and the status will change to `EXECUTED`. If user cancels the request to return-to-home, the status will change to `CANCELLED`. |
| EXECUTED | Exceed the smart low battery return-to-home. The status will not be reset until the aircraft returned and landed or the return-to-home was cancelled midway. |
| CANCELLED | The smart low battery return-to-home is cancelled. This status will not be reset until the aircraft landed. |

##### Class Members:

Compass Calibration

enum

[CompassCalibrationState](#value_flightcontroller_enum_fccompasscalibrationstate_inline)

###### enum CompassCalibrationState

|  |
| --- |
| ``` enum CompassCalibrationState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The calibration status of the compass.

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Normal status. The compass is not calibrated. |
| HORIZONTAL | The horizontal calibration of the compass. User should horizontally hold the aircraft and rotate it 360 degrees. |
| VERTICAL | The compass vertical calibration. User should vertically hold the aircraft, with the aircraft head facing the ground and rotate it 360 degrees. |
| SUCCEEDED | Compass calibration success. |
| FAILED | Compass calibration is failed. User should make sure there are no magnets or metal objects near the aircrft, and retry. |

##### Class Members:

IMU Calibration

class

[IMUState](../../Components/KeyManager/Value_FlightController_Struct_IMUState.md)

Smart Return to Home

enum

[GoHomeState](#value_flightcontroller_enum_fcgohomestate_inline)

###### enum GoHomeState

|  |
| --- |
| ``` enum GoHomeState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Smart return-to-home status.

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Smart return-to-home is not triggered. The state of the aircraft will also be reset to this state after returning to home and landing. |
| PREASCENDING | Return-to-home preparing for ascend. |
| ALIGN | Return-to-home heading alignment. The aircraft is turning the heading direction to the home point. |
| ASCENDING | Return-to-home ascending. The aircraft is ascending to the return-to-home altitude. |
| CRUISE | Returning to home. The aircraft is flying horizontally to home point. |
| BRAKING | Return-to-home braking. The aircraft is braking to avoid a collision. |
| AVOID\_ASCENDING | Return-to-home avoiding obstacles. The aircraft is trying to avoid obstacles. |
| COMPLETED | Return-to-home is complete. |

##### Class Members:

class

[LowBatteryRTHInfo](../../Components/KeyManager/Value_FlightController_Struct_GoHomeAssessment.md)

GNSS

enum

[NavigationSatelliteSystem](#value_flightcontroller_enum_navigationsatellitesystem_inline)

###### enum NavigationSatelliteSystem

|  |
| --- |
| ``` enum NavigationSatelliteSystem implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The type of GNSS that can be set.

##### Enum Members:

|  |  |
| --- | --- |
| GPS\_GLONASS | Using GPS and GLONSS. |
| BEIDOU | Only use Beidou system. |

##### Class Members:

GPS信号等级GPS Signal Level

enum

[GPSSignalLevel](#value_flightcontroller_enum_fcgpssignallevel_inline)

###### enum GPSSignalLevel

|  |
| --- |
| ``` enum GPSSignalLevel implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

GPS signal level.

##### Enum Members:

|  |  |
| --- | --- |
| LEVEL\_0 | Level 0. There is almost no GPS signal. |
| LEVEL\_1 | Level 1. GPS signal is weak. |
| LEVEL\_2 | Level 2. GPS signal is weak. From this level, the return-to-home function of the aircraft can work. |
| LEVEL\_3 | Level 3. GPS sigal is good. From this level, the aircraft can hover in the air. |
| LEVEL\_4 | Level 4. GPS signal is very good. From this level, the aircraft can record the home point. |
| LEVEL\_5 | Level 5. GPS signal is strong. |
| LEVEL\_10 | Level 10. GPS is combined with RTK information. |
| LEVEL\_NONE | There is no GPS signal. |

##### Class Members:

Flight Mode

enum

[RemoteControllerFlightMode](#value_flightcontroller_enum_remotecontrollerflightmode_inline)

###### enum RemoteControllerFlightMode

|  |
| --- |
| ``` enum RemoteControllerFlightMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The flight mode of the aircraft.

##### Enum Members:

|  |  |
| --- | --- |
| F | 功能模式，简称为“F模式”。表现与P模式相同，并启用任务和智能方向控制等智能功能。 |
| A | Attitude mode, which is referred to as "A mode". In this mode, the aircraft only provides attitude stablization, and GPS module and vision system are not used for positioning. In real operation, aircraft will drift noticeably and can not hover, which needs the pilot to correct the position of the aircraft instantly through remote controller. Attitude mode tests the pilot's handling ability of the aircraft. In some emergency situation, the aircraft needs to switch attitude mode. |
| S | 运动模式，简称为“S模式”。使用GPS模块和视觉系统以实现精准悬停，该模式下飞行器的感度值被适当调高，务必格外谨慎飞行。飞行器最大水平速度可达20米/秒。 |
| P | 定位模式，简称为“P模式”。GPS模块和视觉系统（如果可用）将用于飞行器定位。对于没有F模式的产品，可以在P模式下执行任务和智能方向控制等智能功能。 |
| M | Manual mode, which is referred to as "M mode". In this mode, All actions of the aircraft, including stable attitude and stable altitude, need to be controlled by the pilot through remote controller. It is danguous for beginner to operate. |
| T | 三角架模式，简称为“T模式”。在此模式下，飞行器飞行速度和转动灵敏度都将显著降低，可以更精准的控制画面，获得更加稳定的拍摄效果。 |

##### Class Members:

Wind Speed

enum

[WindWarning](#value_flightcontroller_enum_fcwindwarning_inline)

###### enum WindWarning

|  |
| --- |
| ``` enum WindWarning implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The wind speed of the environment that the aircraft stay in.

##### Enum Members:

|  |  |
| --- | --- |
| LEVEL\_0 | Level 0, which means there is currently no wind. |
| LEVEL\_1 | Level 1, which means the wind speed is fast. Please flight the aircraft carefully and ensure the aircraft in sight. |
| LEVEL\_2 | Level 2, which means the wind speed is very fast. Please flight the aircraft carefully and ensure the aircraft in sight. |

##### Class Members:

Wind Direction

enum

[WindDirection](#value_flightcontroller_enum_fcwinddirectionstatus_inline)

###### enum WindDirection

|  |
| --- |
| ``` enum WindDirection implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The wind direction of the environment that the aircraft stay in. It's using the world coordinate system.

##### Enum Members:

|  |  |
| --- | --- |
| WINDLESS | Currently no wind. |
| NORTH | North wind. |
| NORTH\_EAST | Northeast wind. |
| EAST | East wind. |
| SOUTH\_EAST | Southeast wind. |
| SOUTH | South wind. |
| SOUTH\_WEST | Southwest wind. |
| WEST | West wind. |
| NORTH\_WEST | Northwest wind. |

##### Class Members:

RC Signal Lost Protection

enum

[FailsafeAction](#value_flightcontroller_enum_fcfailsafeaction_inline)

###### enum FailsafeAction

|  |
| --- |
| ``` enum FailsafeAction implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

RC signal lost behavior type of the aircraft.

##### Enum Members:

|  |  |
| --- | --- |
| HOVER | Hover. When the remote controller and aircraft lose connection, the aircraft will hover. |
| LANDING | Land. When the remote controller and aircraft lose connection, the aircraft will land in place. |
| GOHOME | Return-to-Home. If the aircraft has successfully recorded the home point (`KeyHomeLocation`) and the GPS signal is good, the aircraft will automatically return to home point and land to prevent accident when the remote controller and aircraft lose connection signal. During the return-to-home process, if forward vision system is opened and the environment condition is good, the aircraft will automatically climb up to avoid obstacles when the head of aircraft encounter obstacles. To ensure the orientation of the aircraft head, user can not adjust the orientation of the aircraft head and can not control the aircraft to fly left and right. |

##### Class Members:

LED

class

[LEDsSettings](../../Components/KeyManager/Value_FlightController_Struct_LEDsSettings.md)

#### Battery

Battery LED

class

[BatteryLedsInfo](../../Components/KeyManager/Value_Battery_Struct_BatteryLedsControlMsg.md)

#### Accessory

enum

[AudioStorageLocation](#value_accessory_enum_audiostoragelocation_inline)

###### enum AudioStorageLocation

|  |
| --- |
| ``` enum AudioStorageLocation implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.accessory |

##### Description:

keyvalue\_value\_accessory\_AudioStorageLocation

##### Enum Members:

|  |  |
| --- | --- |
| TMEPORARY | keyvalue\_value\_accessory\_AudioStorageLocation\_MEPORARY |
| PERMANENT | keyvalue\_value\_accessory\_AudioStorageLocation\_PERMANENT |
| UNKNOWN | keyvalue\_value\_accessory\_AudioStorageLocation\_UNKNOWN |

##### Class Members:

class

[PlayingAudioFileInfo](../../Components/KeyManager/Value_Accessory_Struct_PlayingAudioFileInfo.md)

#### Air Link

enum

[AirlinkBandwidth](#value_airlink_enum_airlinkbandwidth_inline)

###### enum AirlinkBandwidth

|  |
| --- |
| ``` enum AirlinkBandwidth implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

The settings of download link bandwidth. Set the narrow bandwidth will lower the data transmitting rate but make the airlink signal stronger.

##### Enum Members:

|  |  |
| --- | --- |
| BANDWIDTH\_10MHZ | 10MHz(Rate: 23Mbps). |
| BANDWIDTH\_20MHZ | 20MHz(Rate: 46Mbps). |
| BANDWIDTH\_40MHZ | 40MHz(Rate: 80Mbps). |

##### Class Members:

enum

[AirLinkType](#value_airlink_enum_airlinktype_inline)

###### enum AirLinkType

|  |
| --- |
| ``` enum AirLinkType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Airlink type.

##### Enum Members:

|  |  |
| --- | --- |
| WIFI | WiFi type. |
| LIGHT\_BRIDGE | Light bridge type. |
| OCU\_SYNC | OcuSync type. |

##### Class Members:

enum

[ChannelSelectionMode](#value_airlink_enum_channelselectionmode_inline)

###### enum ChannelSelectionMode

|  |
| --- |
| ``` enum ChannelSelectionMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Airlink channel selection mode, support auto or manual mode.

##### Enum Members:

|  |  |
| --- | --- |
| AUTO | Auto mode, the airlink system will select the best suited channel according to the signal strength of each channel. |
| MANUAL | Manual mode, you can select the channel. |

##### Class Members:

enum

[AirlinkFrequencyBand](#value_airlink_enum_airlinkfrequencyband_inline)

###### enum AirlinkFrequencyBand

|  |
| --- |
| ``` enum AirlinkFrequencyBand implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

The type of airlink frequency band.

##### Enum Members:

|  |  |
| --- | --- |
| BAND\_DUAL | Dual band mode, either 2.4GHz or 5.8GHz. |
| BAND\_2\_DOT\_4G | 2.4GHz. |
| BAND\_5\_DOT\_8G | 5.8GHz. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found