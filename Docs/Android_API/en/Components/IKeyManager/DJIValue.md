**Navigation:** [IKeyManager](IKeyManager.md) > [DJIValue](DJIValue.md)

---

# class DJIValue

|  |
| --- |
| ``` interface DJIValue ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.base |

##### Description:

Base class for all Value classes supported by MSDK.

##### Class Members:

#### Gimbal

class

[LightGimbalTotalAdjustInfo](../../Components/IKeyManager/Value_FlightController_LightGimbalTotalAdjustInfo.md)

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
| PITCH\_YAW | Reset the pitch and yaw axes back to the center. |
| ONLY\_PITCH | Only reset the pitch axis back to the center. |
| ONLY\_YAW | Only reset the yaw axis back to the center. |
| ONLY\_ROLL | Only reset the roll axis back to the center. |
| PITCH\_UP\_OR\_DOWN\_WITH\_YAW\_CENTER | Reset the pitch axis down or up and reset the yaw axis back to the center. Face up when the gimbal is down, face down when the gimbal is up. |
| PITCH\_UP\_OR\_DOWN | Only reset the pitch axis down or up. Face up when the gimbal is down, face down when the gimbal is up. |

##### Class Members:

class

[GimbalCalibrationStatusInfo](../../Components/IKeyManager/Value_Gimbal_Struct_GimbalCalibrationState.md)

class

[GimbalAttitudeRange](../../Components/IKeyManager/Value_Gimbal_Struct_GimbalAttitudeRange.md)

class

[LightGimbalAdjustInfo](../../Components/IKeyManager/Value_FlightController_LightGimbalAdjustInfo.md)

class

[GimbalAngleRotation](../../Components/IKeyManager/Value_Gimbal_Struct_GimbalAngleRotation.md)

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
| RELATIVE\_ANGLE | Relative angle mode. The rotation value set here is the angle value relative to the current angle of the gimbal. For example, the pitch rotation value is set to 30 degrees, then the gimbal will rotate upward by 30 degrees based on the current pitch angle. |
| ABSOLUTE\_ANGLE | Absolute angle mode. 0 degree is the current attitude angle of aircraft. For example, pitch rotation value is set to 30 degrees, then the gimbal will rotate upward by 30 degrees based on the current aircraft pitch angle. |

##### Class Members:

class

[GimbalSpeedRotation](../../Components/IKeyManager/Value_Gimbal_Struct_GimbalSpeedRotation.md)

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
| FREE | Gimbal free mode. In this mode, even if the aircraft attitude changes, the gimbal and camera attitude will remain the same. |
| YAW\_FOLLOW | Gimbal yaw following mode. In this mode, the yaw angle of the gimbal will change with the yaw angle of the aircraft, and the pitch and roll angles of the gimbal and camera are free to be controlled |
| FPV | Gimbal FPV mode. In this mode, the yaw and roll angles of the gimbal will change with the yaw and roll angles of the aircraft. |

##### Class Members:

enum

[LightGimbal](#value_payload_enum_lightgimbal_inline)

###### enum LightGimbal

|  |
| --- |
| ``` enum LightGimbal implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.payload |

##### Description:

Searchlight gimbal type.

##### Enum Members:

|  |  |
| --- | --- |
| LEFT | Left gimbal. |
| RIGHT | Right gimbal. |
| BOTH | 左右双云台。 |

##### Class Members:

class

[MultiGimbalSyncControlInfo](../../Components/IKeyManager/Value_FlightController_Struct_MultiGimbalSyncControlMsg.md)

class

[MultiGimbalSyncStatus](../../Components/IKeyManager/Value_FlightController_Struct_MultiGimbalSyncStatus.md)

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

Laser Measure State

enum

[LaserMeasureState](#value_camera_enum_lasermeasureerrorstatus_inline)

###### enum LaserMeasureState

|  |
| --- |
| ``` enum LaserMeasureState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Laser measure state.

##### Enum Members:

|  |  |
| --- | --- |
| NORMAL | The laser sensor's measuring state is normal. |
| TOO\_CLOSE | The laser sensor is too close to the target object. |
| TOO\_FAR | The laser sensor is too far away from the target object. |
| NO\_SIGNAL | The laser sensor cannot provide location information, please make sure that the aircraft is used with a good GPS signal. |

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

Display mode of the infrared lens.

##### Enum Members:

|  |  |
| --- | --- |
| THERMAL\_ONLY | Infrared explicit mode. Only the thermal image of the infrared camera is displayed. |
| PIP | Split screen display mode. The images of the infrared camera and the zoom camera are displayed on a left-right split screen. |

##### Class Members:

enum

[MultiSpectralDisplayMode](#value_camera_enum_multispectraldisplaymode_inline)

###### enum MultiSpectralDisplayMode

|  |
| --- |
| ``` enum MultiSpectralDisplayMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Display mode of the multispectral lens.

##### Enum Members:

|  |  |
| --- | --- |
| NDVI\_ONLY | NDVI display mode. Only the NDVI image is displayed. |
| PIP | Split screen display mode. The images of the NDVI lens and the RGB lens are displayed on a left-right split screen. |

##### Class Members:

Night Scene Mode

enum

[CameraNightSceneMode](#value_camera_enum_cameranightscenemode_inline)

###### enum CameraNightSceneMode

|  |
| --- |
| ``` enum CameraNightSceneMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Night scene mode.

##### Enum Members:

|  |  |
| --- | --- |
| DISABLE | Disable night scene mode. |
| ENABLE | Enable night scene mode. Denoise level and IR cut functions are supported in this mode. If you enabled the denoise levle, you can further enhance the image quality, but it will further reduce the recording frame rate. Please use this function with caution in situations that have high requirements for real-time performance of the image. If IR cut is enabled, the zoom lens will remove the infrared filter to improve shooting results in low-light environments, and the camera only provides grayscale images. |
| AUTO | Automatic night scene mode, the camera automatically turns on night scene mode in low-light environments. |

##### Class Members:

Camera Mode

enum

[CameraMode](#value_camera_enum_cameramode_inline)

###### enum CameraMode

|  |
| --- |
| ``` enum CameraMode ``` |

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
| PHOTO\_HYPER\_LIGHT | Camera mode is set to intelligent photo mode. In a dimly lit environment, the camera will automatically simulate a long exposure for shooting, which can shoot the object more clearly. It only takes effect when the zoom lens is zoomed above 5x. |
| PHOTO\_AEB | The camera mode is set to AEB automatic exposure bracketing mode, which is a mode for obtaining correct exposure photos by taking multiple photos with different exposures of the same object and surrounding them together. |
| PHOTO\_BURST | The camera mode is set to BURST mode, which is a mode to take multiple photos at once. |
| PHOTO\_HIGH\_RESOLUTION | Sets the camera to the high resolution mode. In this mode, the camera will take photos with a resolution of 48MP. |
| PHOTO\_INTERVAL | Camera mode is set to interval photo mode. Set the camera to take one at a set time interval. You can set the timed photo parameters through `KeyPhotoIntervalShootSettings`. `setCount` is used to set the total number of shots, `setInterval` is used to set the time interval for taking photos, since the camera needs a response time to take photos, the minimum photo interval needs to be set to more than 2 seconds. After setting this mode, you need to call `KeyStartShootPhoto` to take pictures regularly. During the timed photo shooting, you can call `KeyStopShootPhoto` to stop the timed photo shooting. |
| PHOTO\_SUPER\_RESOLUTION | Camera mode is set to super resolution photo mode. In this mode, dragging to select an area in the wide-angle view, the gimbal will automatically move and take a series of 20MP photos of the selected area with the zoom camera at the current zoom ratio. All photos and generated HTML files will be saved in the same folder on the microSD card. To view the global photo and partial close-up photo, please open the HTML file with a browser on your PC for viewing. If you want to view the global photo and local close-up photo in the application, please use `KeySuperResolutionCaptureArea` to set the super resolution capture area. Use `KeySuperResolutionInfo` to monitor the camera state of super resolution shooting. After setting this mode, you need to call `KeyStartShootPhoto` to take a photo with the super resolution During the shooting process, you can call `KeyStopShootPhoto` to stop the timed super resolution photo shooting. |
| PHOTO\_PANORAMA | Camera mode is set to panoramic photo mode. The aircraft took a series of photos at different gimbal angles and aircraft headings. This set of photos can be stitched into panoramic photos using third-party libraries. The panorama photo mode can be set by `KeyPhotoPanoramaMode`, the panorama photo mode can be judged by `KeyIsShootingPhotoPanorama`, and the progress of panorama photo can be obtained by `KeyPhotoPanoramaProgress`. After setting this mode, you need to call `KeyStartShootPhoto` to take a panoramic photo. During the shooting process, you can call `KeyStopShootPhoto` to stop panorama shooting.  **Notic:   Panoramic photo mode can only be activated after the aircraft takes off.** |

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

Display Position

enum

[ThermalPIPPosition](#value_camera_enum_pipposition_inline)

###### enum ThermalPIPPosition

|  |
| --- |
| ``` enum ThermalPIPPosition implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Possible positions to place the window of the thermal camera video feed when the `KeyThermalDisplayMode` is `PIP`.

##### Enum Members:

|  |  |
| --- | --- |
| SIDE\_BY\_SIDE | Position the thermal window on the right side of the main window (the visual camera video feed). Scale both windows with the same height. |

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
| RED\_HOT | Red Hot. Use black and white for low temperature and eye-catching red for high temperature, which can quickly detect high temperature targets. Suitable for high-contrast environments, accurately and quickly find high-temperature targets at night. Appears as Tint in DJI Pilot. |
| IRONBOW1 | Iron Red. This palette displays nuanced differences in heat signatures, quickly displaying anomalies and human bodies. Hotter objects appear as light warm colors and colder objects appear as dark cool colors. |
| COLOR2 | Hot Iron. Red represents high temperatures, and cool colors represent low temperatures. It is able to identify hot targets quickly while showing the details of cool targets. |
| ICE\_FIRE | Arctic. Uses the same palette as Medical, except switching the purple for a cool blue to better reflect temperature changes. |
| GREEN\_HOT | Green Hot. Use different colors to show small temperature differences, best suited for scenes with small heat changes. In low contrast conditions, objects and slight temperature changes can still be detected, and it is now commonly used in the medical field to better display the temperature distribution of the human body. Appears as Medical in DJI Pilot. |
| COLOR1 | Fulgurite. Dark red represents low temperatures and white represents high temperatures. The warm tone of this palette aligns with people’s association with hot temperatures. |
| RAINBOW | Rainbow 1. Similar to Medical, it reduces the warm color ratio and increases the cold color ratio for high temperature targets to better show the details of cool targets. |
| RAIN | Rainbow 2. The color transition is reduced, the warm and cold colors are moderately proportioned, which can show the details of high and low temperature targets at the same time. |

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

The mode that automatically turns off LED when shooting photo and recording video.

##### Enum Members:

|  |  |
| --- | --- |
| DISABLED | The arm LED will not be automatically turned off when shooting photo and recording video. |
| FRONT\_LEDS\_ONLY | The front LED and light LED will be automatically turned off when shooting photo and recording video. |
| BACK\_LEDS\_ONLY | The back LED and light LED will be automatically turned off when shooting photo and recording video. |
| ALL\_LEDS | The front LED, back LED and night LED will be automaticallu turned off when shooting photo and recording video. |

##### Class Members:

Denoise Level

enum

[CameraDenoiseLevel](#value_camera_enum_cameradenoiselevel_inline)

###### enum CameraDenoiseLevel

|  |
| --- |
| ``` enum CameraDenoiseLevel implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Denoise level settings.

##### Enum Members:

|  |  |
| --- | --- |
| DISABLE | Disable denoise level. |
| STANDARD | Standard level. Corresponding to the low level of DJI Pilot, the image transmission and video frame rate is 25fps. |
| ENHANCE | Enhance level. Corresponding to the mid-level DJI Pilot, the image transmission and video frame rate is 15fps. |
| EXTRA | Extra level, corresponding to the high level of DJI Pilot, with image transmission and video frame rate of 5fps. |

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
| SHUTTER\_SPEED\_AUTO | The shutter speed of the camera is set to AUTO. Not available when the exposure mode is `MANUAL`. |
| SHUTTER\_SPEED1\_20000 | The shutter speed of the camera is set to 1/20000 s. |
| SHUTTER\_SPEED1\_16000 | The shutter speed of the camera is set to 1/16000 s. |
| SHUTTER\_SPEED1\_12800 | The shutter speed of the camera is set to 1/12800 s. |
| SHUTTER\_SPEED1\_10000 | The shutter speed of the camera is set to 1/10000 s. |
| SHUTTER\_SPEED1\_8000 | The shutter speed of the camera is set to 1/8000 s. |
| SHUTTER\_SPEED1\_6400 | The shutter speed of the camera is set to 1/6400 s. |
| SHUTTER\_SPEED1\_6000 | The shutter speed of the camera is set to 1/6000 s. |
| SHUTTER\_SPEED1\_5000 | The shutter speed of the camera is set to 1/5000 s. |
| SHUTTER\_SPEED1\_4000 | The shutter speed of the camera is set to 1/4000 s. |
| SHUTTER\_SPEED1\_3200 | The shutter speed of the camera is set to 1/3200 s. |
| SHUTTER\_SPEED1\_3000 | The shutter speed of the camera is set to 1/3000 s. |
| SHUTTER\_SPEED1\_2500 | The shutter speed of the camera is set to 1/2500 s. |
| SHUTTER\_SPEED1\_2000 | The shutter speed of the camera is set to 1/2000 s. |
| SHUTTER\_SPEED1\_1600 | The shutter speed of the camera is set to 1/1600 s. |
| SHUTTER\_SPEED1\_1500 | The shutter speed of the camera is set to 1/1500 s. |
| SHUTTER\_SPEED1\_1250 | The shutter speed of the camera is set to 1/1250 s. |
| SHUTTER\_SPEED1\_1000 | The shutter speed of the camera is set to 1/1000 s. |
| SHUTTER\_SPEED1\_800 | The shutter speed of the camera is set to 1/800 s. |
| SHUTTER\_SPEED1\_725 | The shutter speed of the camera is set to 1/725 s. |
| SHUTTER\_SPEED1\_640 | The shutter speed of the camera is set to 1/640 s. |
| SHUTTER\_SPEED1\_500 | The shutter speed of the camera is set to 1/500 s. |
| SHUTTER\_SPEED1\_400 | The shutter speed of the camera is set to 1/400 s. |
| SHUTTER\_SPEED1\_350 | The shutter speed of the camera is set to 1/350 s. |
| SHUTTER\_SPEED1\_320 | The shutter speed of the camera is set to 1/320 s. |
| SHUTTER\_SPEED1\_250 | The shutter speed of the camera is set to 1/250 s. |
| SHUTTER\_SPEED1\_240 | The shutter speed of the camera is set to 1/240 s. |
| SHUTTER\_SPEED1\_200 | The shutter speed of the camera is set to 1/200 s. |
| SHUTTER\_SPEED1\_180 | The shutter speed of the camera is set to 1/180 s. |
| SHUTTER\_SPEED1\_160 | The shutter speed of the camera is set to 1/160 s. |
| SHUTTER\_SPEED1\_125 | The shutter speed of the camera is set to 1/125 s. |
| SHUTTER\_SPEED1\_120 | The shutter speed of the camera is set to 1/120 s. |
| SHUTTER\_SPEED1\_100 | The shutter speed of the camera is set to 1/100 s. |
| SHUTTER\_SPEED1\_90 | The shutter speed of the camera is set to 1/90 s. |
| SHUTTER\_SPEED1\_80 | The shutter speed of the camera is set to 1/80 s. |
| SHUTTER\_SPEED1\_60 | The shutter speed of the camera is set to 1/60 s. |
| SHUTTER\_SPEED1\_50 | The shutter speed of the camera is set to 1/50 s. |
| SHUTTER\_SPEED1\_40 | The shutter speed of the camera is set to 1/40 s. |
| SHUTTER\_SPEED1\_30 | The shutter speed of the camera is set to 1/30 s. |
| SHUTTER\_SPEED1\_25 | The shutter speed of the camera is set to 1/25 s. |
| SHUTTER\_SPEED1\_20 | The shutter speed of the camera is set to 1/20 s. |
| SHUTTER\_SPEED1\_15 | The shutter speed of the camera is set to 1/15 s. |
| SHUTTER\_SPEED1\_12DOT5 | The shutter speed of the camera is set to 1/12.5 s. |
| SHUTTER\_SPEED1\_10 | The shutter speed of the camera is set to 1/10 s. |
| SHUTTER\_SPEED1\_8 | The shutter speed of the camera is set to 1/8秒。 |
| SHUTTER\_SPEED1\_6DOT25 | The shutter speed of the camera is set to 1/6.25 s. |
| SHUTTER\_SPEED1\_5 | The shutter speed of the camera is set to 1/5 s. |
| SHUTTER\_SPEED1\_4 | The shutter speed of the camera is set to 1/4 s. |
| SHUTTER\_SPEED1\_3 | The shutter speed of the camera is set to 1/3 s. |
| SHUTTER\_SPEED1\_2DOT5 | The shutter speed of the camera is set to 1/2.5 s. |
| SHUTTER\_SPEED1\_2 | The shutter speed of the camera is set to 1/2 s. |
| SHUTTER\_SPEED1\_1DOT67 | The shutter speed of the camera is set to 1/1.67 s. |
| SHUTTER\_SPEED1\_1DOT25 | The shutter speed of the camera is set to 1/1.25 s. |
| SHUTTER\_SPEED1 | The shutter speed of the camera is set to 1.0 s. |
| SHUTTER\_SPEED1DOT3 | The shutter speed of the camera is set to 1.3 s. |
| SHUTTER\_SPEED1DOT6 | The shutter speed of the camera is set to 1.6 s. |
| SHUTTER\_SPEED2 | The shutter speed of the camera is set to 2.0 s. |
| SHUTTER\_SPEED2DOT5 | The shutter speed of the camera is set to 2.5 s. |
| SHUTTER\_SPEED3 | The shutter speed of the camera is set to 3.0 s. |
| SHUTTER\_SPEED3DOT2 | The shutter speed of the camera is set to 3.2 s. |
| SHUTTER\_SPEED4 | The shutter speed of the camera is set to 4.0 s. |
| SHUTTER\_SPEED5 | The shutter speed of the camera is set to 5.0 s. |
| SHUTTER\_SPEED6 | The shutter speed of the camera is set to 6.0 s. |
| SHUTTER\_SPEED7 | The shutter speed of the camera is set to 7.0 s. |
| SHUTTER\_SPEED8 | The shutter speed of the camera is set to 8.0 s. |
| SHUTTER\_SPEED9 | The shutter speed of the camera is set to 9.0 s. |
| SHUTTER\_SPEED10 | The shutter speed of the camera is set to 10.0 s. |
| SHUTTER\_SPEED11 | The shutter speed of the camera is set to 11.0 s. |
| SHUTTER\_SPEED13 | The shutter speed of the camera is set to 13.0 s. |
| SHUTTER\_SPEED15 | The shutter speed of the camera is set to 15.0 s. |
| SHUTTER\_SPEED16 | The shutter speed of the camera is set to 16.0 s. |
| SHUTTER\_SPEED20 | The shutter speed of the camera is set to 20.0 s. |
| SHUTTER\_SPEED23 | The shutter speed of the camera is set to 23.0 s. |
| SHUTTER\_SPEED25 | The shutter speed of the camera is set to 25.0 s. |
| SHUTTER\_SPEED30 | The shutter speed of the camera is set to 30.0 s. |
| SHUTTER\_SPEED40 | The shutter speed of the camera is set to 40.0 s. |
| SHUTTER\_SPEED50 | The shutter speed of the camera is set to 50.0 s. |
| SHUTTER\_SPEED60 | The shutter speed of the camera is set to 60.0 s. |
| SHUTTER\_SPEED80 | The shutter speed of the camera is set to 80.0 s. |
| SHUTTER\_SPEED100 | The shutter speed of the camera is set to 100.0 s. |
| SHUTTER\_SPEED120 | The shutter speed of the camera is set to 120.0 s. |

##### Class Members:

hybrid Zoom

class

[CameraHybridZoomSpec](../../Components/IKeyManager/Value_Camera_Struct_CameraHybridZoomSpec.md)

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

[VideoResolutionFrameRate](../../Components/IKeyManager/Value_Camera_Struct_VideoResolutionFrameRate.md)

Super Resolutio Photo

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

The status of super resolution photo.

##### Enum Members:

|  |  |
| --- | --- |
| SELECTING | The super resolution photo area is selecting. You can use `KeySuperResolutionCaptureArea` to set the super resolution photo area. |
| IN\_PROGRESS | The super resolution photo is shooting. |
| FINISH | The super resolution photo shooting has ended. |
| EXIT\_OUT\_OF\_STORAGE | Stop shooting, because there is no more storage. |
| EXIT\_SHOOT\_FAIL | Stop shooting, because shooting is failed. |
| EXIT\_GIMBAL\_BLOCK | Stop shooting, because the gimbal is stuck. |
| EXIT\_GIMBAL\_ABNORMAL\_MOVE | Stop shooting, because the gimbal cannot move or the gimbal is abnormal. |
| TIP\_GIMBAL\_OUT\_OF\_RANGE | Stop shooting, because the gimbal angle exceeds the maximum limit. |
| TIP\_OUT\_OF\_ZOOM | Camera zoom out of maximum range. |

##### Class Members:

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

Zoom Ratios

class

[ZoomRatiosRange](../../Components/IKeyManager/Value_Camera_Struct_MSDKZoomRatiosRange.md)

Video Mime Type

enum

[VideoMimeType](#value_camera_enum_videofilecompressionstandard_inline)

###### enum VideoMimeType

|  |
| --- |
| ``` enum VideoMimeType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Camera video mime type.

##### Enum Members:

|  |  |
| --- | --- |
| H264 | H264, with good compatibility. |
| H265 | 265, to save storage space. If the device does not support it, it may cause abnormal video playback or sharing. |

##### Class Members:

PreRecord

enum

[VideoRecordPlan](#value_camera_enum_videorecordplan_inline)

###### enum VideoRecordPlan

|  |
| --- |
| ``` enum VideoRecordPlan implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Camera record mode.

##### Enum Members:

|  |  |
| --- | --- |
| DEFAULT | Defalt. Normal recording mode. |
| PRE\_RECORD | Pre-record mode. |

##### Class Members:

class

[VideoPreRecordDurationInfo](../../Components/IKeyManager/Value_Camera_Struct_VideoPreRecordDurationMsg.md)

class

[VideoRecordPlanInfo](../../Components/IKeyManager/Value_Camera_Struct_VideoRecordPlanMsg.md)

enum

[VideoRecordingState](#value_camera_enum_recordingstatev1_inline)

###### enum VideoRecordingState

|  |
| --- |
| ``` enum VideoRecordingState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Video recording state.

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | IDLE, video not recorded. |
| STARTING | Starting, video is starting recording. |
| RECORDING | Recording, video is recording. |
| STOPPING | Stopping, Video is stopping recording |
| PRE\_RECORDING | Pre-recording, video is pre-recording. |

##### Class Members:

class

[VideoRecordingStatus](../../Components/IKeyManager/Value_Camera_Struct_IsRecordingInfoMsg.md)

enum

[VideoPreRecordDuration](#value_camera_enum_videoprerecordduration_inline)

###### enum VideoPreRecordDuration

|  |
| --- |
| ``` enum VideoPreRecordDuration implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Pre-record duration.

##### Enum Members:

|  |  |
| --- | --- |
| DURATION\_5\_SEC | 5 second. |
| DURATION\_10\_SEC | 10 second. |
| DURATION\_15\_SEC | 15 second. |
| DURATION\_30\_SEC | 30 second. |
| DURATION\_60\_SEC | 60 second. |

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

Dehaze Level

enum

[DehazeLevel](#value_camera_enum_dehazelevel_inline)

###### enum DehazeLevel

|  |
| --- |
| ``` enum DehazeLevel implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Camera dehaze level.

##### Enum Members:

|  |  |
| --- | --- |
| LEVEL\_1 | Level 1. corresponds to the low level of DJI Pilot and the normal settings. |
| LEVEL\_2 | Level 2. Reserved level, currently not supported, setting is invalid. |
| LEVEL\_3 | Level 3. Corresponds to the high level of DJI Pilot. |
| LEVEL\_4 | Level 4. Reserved level, currently not supported, setting is invalid. |

##### Class Members:

Dehaze Mode

enum

[DehazeMode](#value_camera_enum_dehazemode_inline)

###### enum DehazeMode

|  |
| --- |
| ``` enum DehazeMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Camera dehaze mode.

##### Enum Members:

|  |  |
| --- | --- |
| DISABLE | Disable dehaze mode. |
| ENABLE | Enable dehaze mode. |
| AUTO | Dehaze mode will automaic turn on. |

##### Class Members:

Video Source Type

enum

[CameraVideoStreamSourceType](#value_camera_enum_cameravideostreamsourcetype_inline)

###### enum CameraVideoStreamSourceType

|  |
| --- |
| ``` enum CameraVideoStreamSourceType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Video Source Type supported by the camera.

##### Enum Members:

|  |  |
| --- | --- |
| DEFAULT\_CAMERA | Default. Single lens cameras use this lens type by default. |
| WIDE\_CAMERA | Wide. |
| ZOOM\_CAMERA | Zoom. |
| INFRARED\_CAMERA | Infrared. |
| RGB\_CAMERA | Multispectral RGB. |
| NDVI\_CAMERA | Multispectral NDVI. |
| MS\_G\_CAMERA | Multispectral G. |
| MS\_R\_CAMERA | Multispectral R. |
| MS\_RE\_CAMERA | Multispectral RE. |
| MS\_NIR\_CAMERA | Multispectral NIR. |
| POINT\_CLOUD\_CAMERA | Point Cloud. Only Supported by Zenmuse L2. |
| VISION\_CAMERA | Visible light. Only supports Mavic 3E series and Mavic 3M. |

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
| FULL | Full screen. |
| SKY\_EXCLUDED33 | The sky area is excluded (33%). |
| SKY\_EXCLUDED50 | The sky area is excluded (50%). |

##### Class Members:

Temperature Measurement Mode

enum

[ThermalTemperatureMeasureMode](#value_camera_enum_camerathermalmeasurementmode_inline)

###### enum ThermalTemperatureMeasureMode

|  |
| --- |
| ``` enum ThermalTemperatureMeasureMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Thermal temperature measurement mode.

##### Enum Members:

|  |  |
| --- | --- |
| SPOT | Spot measurement mode. |
| REGION | Region measurement mode. |
| CENTRAL\_POINT\_METERING | Center point measurement mode. As the camera picture changes, the temperature of the center point of the picture can be listen through `KeyThermalSpotMetersureTemperature`, which only supports H30 series cameras. |

##### Class Members:

Multipectral Fusion Type

enum

[MultiSpectralFusionType](#value_camera_enum_multispectralfusiontype_inline)

###### enum MultiSpectralFusionType

|  |
| --- |
| ``` enum MultiSpectralFusionType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Fusion types supported by multispectral cameras.

##### Enum Members:

|  |  |
| --- | --- |
| NDVI | NDVI, displays the NVDI (normalized difference vegetation index) frames as video feed produced by the multispectral camera. |
| GNDVI | GNDVI, displays the GNVDI (green normalized difference vegetation index) frames as video feed produced by the multispectral camera. |
| NDRE | NDRE, displays the NDRE (normalized difference red edge index) frames as video feed produced by the multispectral camera. |

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
| OPEN\_ON\_DEMAND | Open the laser based on requirement. In this mode, the laser is only turned on when necessary, and the laser module can be turned on or off through `KeyLaserMeasureEnabled`. After the laser module is turned off, the laser-assisted focusing function is disabled, and the camera's focusing ability is weakened. When using the night vision module and the Tyndall phenomenon occurs, you can choose this mode to turn off the laser module. The laser module will revert to enhanced mode after restarting the camera. |
| OPEN\_ALWAYS | Enhanced mode. The laser module will be turned on by default. In this mode, functions such as laser-assisted focusing, intelligent tracking, spot positioning, and laser ranging can be used normally. |

##### Class Members:

Infinity Calibration

enum

[InfinityCalibrationState](#value_camera_enum_mfdemarcatestate_inline)

###### enum InfinityCalibrationState

|  |
| --- |
| ``` enum InfinityCalibrationState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Infinity calibration state.

##### Enum Members:

|  |  |
| --- | --- |
| NOT\_CALIBRATE | Not calibrated. If the camera lens is not calibrated, the cameara focus will be affected. Please call `KeyStartInfinityCalibration` for infinity calibration. |
| CALIBRATED | Calibrated. |
| CALIBRATING | Calibrating. |
| WAITING\_FOR\_CALIBRATE | Waiting for calibration. |

##### Class Members:

enum

[InfinityCalibrationResult](#value_camera_enum_mfdemarcateresult_inline)

###### enum InfinityCalibrationResult

|  |
| --- |
| ``` enum InfinityCalibrationResult implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Infinity calibration result.

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Idle. |
| SUCCEEDED | Succeeded. |
| TOOCLOSE | Calibration failed. The calibrated object is too close to the aircraft. |
| ERROR | Calibration failed, the focus area is wrong or the calibration data is read and written incorrectly, please try again. |

##### Class Members:

Camera Type

enum

[CameraType](#value_camera_enum_msdkcameratype_inline)

###### enum CameraType

|  |
| --- |
| ``` enum CameraType ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Supported camera types.

##### Enum Members:

|  |  |
| --- | --- |
| M4D | Matrice 4D dual light camera. |
| M4TD | Matrice 4TD triple light camera. |
| M3E | Mavic 3E dual light camera. |
| M3T | Mavic 3T triple light camera. |
| M3TA | Mavic 3TA triple light camera. |
| M3M | Mavic 3 multispectral camera. |
| M30 | Matrice 30 dual light camera. |
| M30T | Matrice 30T triple light camera. |
| ZENMUSE\_H30 | Zenmuse H30 dual light camera. |
| ZENMUSE\_H30T | Zenmuse H30T triple light camera. |
| ZENMUSE\_H20 | Zenmuse H20 dual light camera. |
| ZENMUSE\_H20T | Zenmuse H20T triple light camera. |
| ZENMUSE\_P1 | Zenmuse P1 camera. |
| ZENMUSE\_L1 | Zenmuse L1 camera. |
| ZENMUSE\_L2 | Zenmuse L2 camera. |
| DJI\_MINI\_3 | DJI Mini 3 camera. |
| DJI\_MINI\_3\_PRO | DJI Mini 3 Pro camera. |
| DJI\_MINI\_4\_PRO | DJI Mini 4 Pro camera. |

##### Class Members:

Laser Measure Information

class

[LaserMeasureInformation](../../Components/IKeyManager/Value_Camera_Struct_LaserMeasureInformationMsg.md)

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

Multispectral Fusion Display Range

class

[MultiSpectralFusionDisplayRange](../../Components/IKeyManager/Value_Camera_Struct_MultiSpectralFusionDisplayRangeMsg.md)

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
| PROGRAM | Exposure mode is set to auto exposure. In this mode, the shutter speed is automatically adjusted, the aperture is fixed, and the ISO is automatically adjusted. |
| SHUTTER\_PRIORITY | Exposure mode is set to shutter priority exposure. |
| APERTURE\_PRIORITY | Exposure mode is set to aperture priority exposure。 |

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
| ISO\_AUTO | ISO value is set to AUTO. Not available when the exposure mode is `MANUAL`. |
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

White Balance

enum

[CameraWhiteBalanceMode](#value_camera_enum_camerawhitebalancemode_inline)

###### enum CameraWhiteBalanceMode

|  |
| --- |
| ``` enum CameraWhiteBalanceMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

White balance mode.

##### Enum Members:

|  |  |
| --- | --- |
| AUTO | Auto. |
| SUNNY | Sunny. |
| CLOUDY | Cloudy. |
| WATER\_SURFACE | Water surface. |
| UNDERWATER | Under water. |
| INDOOR\_INCANDESCENT | Indoor incandescent. |
| INDOOR\_FLUORESCENT | Indoor fluorescent. |
| NATURAL | Natural. |
| MANUAL | Manual. |

##### Class Members:

class

[CameraWhiteBalanceInfo](../../Components/IKeyManager/Value_Camera_Struct_CameraWhiteBalance.md)

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
| AUTO | Automatic FFC calibration. When is set to automatic FFC calibration, the Infrared lens will be automatically calibrated at an interval time. |
| MANUAL | Manual FFC calibration. After setting to manual FFC calibration, `KeyThermalTriggerFFC` can be called to perform manual FFC calibration. |

##### Class Members:

Anti Flicker

enum

[CameraAntiFlicker](#value_camera_enum_cameraantiflicker_inline)

###### enum CameraAntiFlicker

|  |
| --- |
| ``` enum CameraAntiFlicker implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Anti flicker setting type.

##### Enum Members:

|  |  |
| --- | --- |
| ANTI\_FLICKER\_AUTO | Auto. |
| ANTI\_FLICKER\_60HZ | 60HZ. |
| ANTI\_FLICKER\_50HZ | 50HZ. |
| ANTI\_FLICKER\_OFF | Turn off anti flicker. |

##### Class Members:

Photo and Video Storage

class

[CameraStreamSettingsInfo](../../Components/IKeyManager/Value_Camera_Struct_MSDKCameraStreamSettings.md)

Water Mark

class

[CameraWatermarkSettings](../../Components/IKeyManager/Value_Camera_Struct_CameraWatermarkSettings.md)

class

[WatermarkDisplayContentSettings](../../Components/IKeyManager/Value_Camera_Struct_WatermarkDisplayContentSettings.md)

Video Bitrate Mode

enum

[VideoBitrateMode](#value_camera_enum_videoencryptstrategy_inline)

###### enum VideoBitrateMode

|  |
| --- |
| ``` enum VideoBitrateMode implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

Camera video bitrate mode.

##### Enum Members:

|  |  |
| --- | --- |
| CBR | CBR. Prioritizes keeping the video bitrate constant. |
| VBR | VBR. Dynamically compresses the video bit rate to save storage space. |

##### Class Members:

custom name

class

[CustomExpandNameSettings](../../Components/IKeyManager/Value_Camera_Struct_CustomExpandNameSettings.md)

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

The state of the SD Card was inserted.

##### Enum Members:

|  |  |
| --- | --- |
| INSERTED | The SD Card is inserted. |
| NOT\_INSERTED | The SD Card is not inserted. |

##### Class Members:

class

[CameraStorageInfos](../../Components/IKeyManager/Value_Camera_Struct_CameraStorageInfos.md)

class

[CameraStorageInfo](../../Components/IKeyManager/Value_Camera_Struct_CameraStorageInfo.md)

Aim To

class

[ZoomTargetPointInfo](../../Components/IKeyManager/Value_Camera_Struct_ZoomPointTargetMsg.md)

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

Regional Temperature Measurement

class

[ThermalGainModeTemperatureRange](../../Components/IKeyManager/Value_Camera_Struct_ThermalGainModeTemperatureRangeMsg.md)

class

[ThermalAreaMetersureTemperature](../../Components/IKeyManager/Value_Camera_Struct_ThermalAreaTemperatureAggregationsMsg.md)

Media File

class

[GeneratedMediaFileInfo](../../Components/IKeyManager/Value_Camera_Struct_GeneratedMediaFileInfo.md)

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

Unit of the isotherm.

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
| MANUAL | Manual focus. In this mode, the user can adjust the focus by setting the focus ring value through `KeyCameraFocusRingValue`. The focus target is the center point of the screen. |
| AF | Auto focus. In this mode, the user can set the focus target through `KeyCameraFocusTarget` for auto focusing. Shown as AFS in Pilot. |
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
| REGION | Area metering(including spot metering). In this mode, the camera will meter based on the area brightness set by the user. |

##### Class Members:

Interval Shooting

class

[PhotoIntervalShootSettings](../../Components/IKeyManager/Value_Camera_Struct_PhotoIntervalShootSettings.md)

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

Super Resolution Photo

class

[SuperResolutionInfo](../../Components/IKeyManager/Value_Camera_Struct_SuperResolutionStateMsg.md)

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

Photo Size

enum

[PhotoSize](#value_camera_enum_photosize_inline)

###### enum PhotoSize

|  |
| --- |
| ``` enum PhotoSize implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.camera |

##### Description:

The type of photo size.

##### Enum Members:

|  |  |
| --- | --- |
| SIZE\_DEFAULT | The default size type corresponds to 4K on the Pilot. |
| SIZE\_LARGE | The large size type corresponds to 4K on the Pilot. |

##### Class Members:

#### Common

Time Data

class

[DateTime](../../Components/IKeyManager/Value_Camera_Struct_DateTime.md)

class

[Date](../../Components/IKeyManager/Value_Common_Struct_Date.md)

Rectangle Data

class

[DoubleRect](../../Components/IKeyManager/Value_Common_Struct_DoubleRect.md)

class

[DoubleRect4Sides](../../Components/IKeyManager/Value_Common_Struct_RectF.md)

Screen Location

enum

[ControlLocation](#value_common_enum_controllocation_inline)

###### enum ControlLocation

|  |
| --- |
| ``` enum ControlLocation implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Screen location.

##### Enum Members:

|  |  |
| --- | --- |
| LEFT\_TOP | Upper left. |
| LEFT\_BOTTOM | Lower left. |
| RIGHT\_TOP | Upper right. |
| RIGHT\_BOTTOM | Lower right. |

##### Class Members:

Three dimensional velocity.

class

[Velocity3D](../../Components/IKeyManager/Value_Common_Struct_Velocity3D.md)

Range of Int Value

class

[IntValueConfig](../../Components/IKeyManager/Value_Common_Struct_IntValueConfig.md)

Attitude Data

class

[Attitude](../../Components/IKeyManager/Value_Common_Struct_Attitude.md)

Length Unit

enum

[LengthUnit](#value_common_enum_lengthunit_inline)

###### enum LengthUnit

|  |
| --- |
| ``` enum LengthUnit implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Display unit of height and length.

##### Enum Members:

|  |  |
| --- | --- |
| METER | Metric units: Meter. |
| FOOT | Imperial units: foot. |

##### Class Members:

Range of Float Value

class

[DoubleMinMax](../../Components/IKeyManager/Value_Common_Struct_DoubleMinMax.md)

Three dimensional position coordinates.

class

[LocationCoordinate3D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate3D.md)

Format of Latitude and Longitude.

enum

[LonLatFormat](#value_common_enum_lonlatformat_inline)

###### enum LonLatFormat

|  |
| --- |
| ``` enum LonLatFormat implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.common |

##### Description:

Display format of latitude and longitude.

##### Enum Members:

|  |  |
| --- | --- |
| DECIMAL\_DEGREE | Degree format. dd.ddddd°. |
| DEGREES\_DECIMAL\_MINUTES | Degree and minute format.dd°mm.mmm'. |
| SEXAGESIMAL\_DEGREE | 度分秒格式Degree, minute and second format. dd°mm'ss''. |

##### Class Members:

Two Dimensional Float Data

class

[DoublePoint2D](../../Components/IKeyManager/Value_Common_Struct_DoublePoint2D.md)

2D Position Coordinates

class

[LocationCoordinate2D](../../Components/IKeyManager/Value_Common_Struct_LocationCoordinate2D.md)

#### product

enum

[ProductType](#value_product_enum_producttype_inline)

###### enum ProductType

|  |
| --- |
| ``` enum ProductType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.product |

##### Description:

Product type.

##### Enum Members:

|  |  |
| --- | --- |
| DJI\_MAVIC\_3\_ENTERPRISE\_SERIES | Mavic 3 Enterprise Series. |
| DJI\_MATRICE\_4D\_SERIES | Matrice 4D Series. |
| M30\_SERIES | Matrice 30 Series. |
| M300\_RTK | Matrice 300 RTK. |
| M350\_RTK | Matrice 350 RTK. |
| DJI\_MINI\_3 | DJI Mini 3. |
| DJI\_MINI\_3\_PRO | DJI Mini 3 Pro. |
| DJI\_MINI\_4\_PRO | DJI Mini 4 Pro. |

##### Class Members:

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

Available control types, including flight control and gimbal control.

##### Enum Members:

|  |  |
| --- | --- |
| FLIGHT\_CONTROL\_AUTHORITY | Flight control. |

##### Class Members:

class

[MultiControlAuthorityTypeInfo](../../Components/IKeyManager/Value_Common_Struct_RCAuthorityModeMsg.md)

class

[MultiControlLockAuthorityInfo](../../Components/IKeyManager/Value_Common_Struct_RCAuthorityLockControlMsg.md)

class

[BatteryInfo](../../Components/IKeyManager/Value_RemoteController_Struct_RcParamChargeRemainingInfo.md)

enum

[RcFirmwareType](#value_remotecontroller_enum_rcfirmwaretype_inline)

###### enum RcFirmwareType

|  |
| --- |
| ``` enum RcFirmwareType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.remotecontroller |

##### Description:

Remote controller firmware type.

##### Enum Members:

|  |  |
| --- | --- |
| M300\_RTK | M300 RTK. |
| M350\_RTK | M350 RTK. |
| M30\_SERIES | M30 Series. |
| DJI\_MINI\_3\_PRO | DJI Mini 3 Pro. |
| DJI\_MINI\_3 | DJI Mini 3. |

##### Class Members:

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

This class is used to define remote controller muti-control channel. If dual control needs to be realized, the different remote controllers should be set to a different channels, and then pair frequency with the aircraft. NOTICE: If remote controller A is already set to A control, and then remote controller B is set to A control to link with the aircraft, the remote controller A will be disconnected from the aircraft and it needs to link again.

##### Enum Members:

|  |  |
| --- | --- |
| CHANNEL\_A | A control. |
| CHANNEL\_B | B control. |

##### Class Members:

class

[MultiControlStatusInfo](../../Components/IKeyManager/Value_RemoteController_Struct_RcMultiStatusMsg.md)

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
| DJI\_RC\_PLUS | DJI RC PLUS remote controller. It adopts O3 Pro high-definition image transmission technology, which can be used with aircraft that support this image transmission technology, and transmits high-definition images in real time. With the complete function buttons of the remote control, the operation and setting of the aircraft and the camera can be completed. Support M30 series aircraft. |
| M300\_RTK\_RC | M300 RTK remote controller. |

##### Class Members:

class

[MultiControlLostControlInfo](../../Components/IKeyManager/Value_RemoteController_Struct_RCAuthorityLostPushMsg.md)

class

[RcFirmwareInfo](../../Components/IKeyManager/Value_RemoteController_Struct_RcFirmwareInfo.md)

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
| PAIRING | Remote controller is pairing frequency. |
| PAIRED | Remote controller pairs frequency successfully. |

##### Class Members:

class

[MultiControlFlightControlAuthorityOwnerInfo](../../Components/IKeyManager/Value_RemoteController_Struct_MultiRCFlightControlAuthOwnerMsg.md)

class

[MultiControlChannelInfo](../../Components/IKeyManager/Value_RemoteController_Struct_RCModeChannelTypeMsg.md)

class

[FiveDimensionPressedStatus](../../Components/IKeyManager/Value_RemoteController_Struct_RcFiveDimensionPressedStatus.md)

class

[FrequencyInterferenceInfo](../../Components/IKeyManager/Value_Airlink_Struct_FrequencyInterference.md)

#### FlightController

LookAt

class

[LookAtInfo](../../Components/IKeyManager/Value_FlightController_LookAtInfo.md)

IMU Calibration

enum

[IMUCalibrationOrientation](#value_flightcontroller_enum_imucalibrationorientation_inline)

###### enum IMUCalibrationOrientation

|  |
| --- |
| ``` enum IMUCalibrationOrientation implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The different orientations of the aircraft needs for a multi-orientation IMU calibration. The following illustration takes M30 as an example.

##### Enum Members:

|  |  |
| --- | --- |
| NOSE\_DOWN | The front/nose of the aircraft should be pointed down. |
| TAIL\_DOWN | The back/tail of the aircraft should be pointed down. |
| RIGHT\_DOWN | The right/starboard side of the aircraft should be pointed down. |
| LEFT\_DOWN | The left/port side of the aircraft should be pointed down. |
| BOTTOM\_DOWN | The bottom/underbelly of the aircraft should be pointed down. |
| TOP\_DOWN | The top of the aircraft should be pointed down. |

##### Class Members:

enum

[IMUCalibrationState](#value_flightcontroller_enum_imucalibrationstate_inline)

###### enum IMUCalibrationState

|  |
| --- |
| ``` enum IMUCalibrationState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

IMU calibraion state.

##### Enum Members:

|  |  |
| --- | --- |
| CALIBRATING | IMU calibration is in progress. |
| SUCCESSFUL | IMU calibraion is successful. |
| FAILED | IMU calibraion is falied. |

##### Class Members:

class

[IMUCalibrationInfo](../../Components/IKeyManager/Value_FlightController_Struct_IMUCalibrationHint.md)

enum

[IMUOrientationCalibrationState](#value_flightcontroller_enum_imuorientationcalibrationstate_inline)

###### enum IMUOrientationCalibrationState

|  |
| --- |
| ``` enum IMUOrientationCalibrationState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

IMU calibration status for the current aircraft orientation.

##### Enum Members:

|  |  |
| --- | --- |
| CALIBRATING | IMU calibration of current aircraft orientation is in progress. |
| SUCCESSFUL | IMU calibration of current aircraft orientation is successul. |

##### Class Members:

Look At Mode

enum

[LookAtMode](#value_flightcontroller_lookatmode_inline)

###### enum LookAtMode

|  |
| --- |
| ``` enum LookAtMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Look at mode.

##### Enum Members:

|  |  |
| --- | --- |
| LOOK\_AT\_GIMBAL\_FREE | Gimbal free mode, only control the gimbal to look at the target, it will automatically exit after completion. |
| LOOK\_AT\_GIMBAL\_FOLLOWING | Gimbal following mode, control gimbal and aircraft to look at the target, it will automatically exit after completion. |

##### Class Members:

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
| IDLE | Smart low battery return-to-home is not triggered. The status will be reset to this status after the aircraft returns and lands. |
| COUNTING\_DOWN | The smart low battery return-to-home is triggered, and the aircraft is counting down. If the cancellation of request to return-to-home is not received from the user in 10 seconds, the aircraft will perform the return-to-home operation, and the status will change to `EXECUTED`. If the user cancels the request to return-to-home, the status will change to `CANCELLED`. |
| EXECUTED | Exceed the smart low battery return-to-home. The status will not be reset until the aircraft returned and landed or the return-to-home was canceled midway. |
| CANCELLED | The smart low battery return-to-home is canceled. This status will not be reset until the aircraft lands. |

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
| HORIZONTAL | The horizontal calibration of the compass. The user should horizontally hold the aircraft and rotate it 360 degrees. |
| VERTICAL | The compass vertical calibration. The user should vertically hold the aircraft and rotate it 360 degrees. |
| SUCCEEDED | Compass calibration success. |
| FAILED | Compass calibration is failed. The user should make sure there are no magnets or metal objects near the aircraft and retry. |

##### Class Members:

Smart Return to Home

enum

[GoHomeState](#value_flightcontroller_enum_gohomestate_inline)

###### enum GoHomeState

|  |
| --- |
| ``` enum GoHomeState ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Smart return-to-home status.

##### Enum Members:

|  |  |
| --- | --- |
| IDLE | Smart return-to-home is not triggered. The state of the aircraft will also be reset to this state after returning home and landing. |
| RETURNING\_TO\_HOME | Returning to home. |
| LANDING | Landing. |
| COMPLETED | Return-to-home is complete. |

##### Class Members:

class

[LowBatteryRTHInfo](../../Components/IKeyManager/Value_FlightController_Struct_GoHomeAssessment.md)

class

[GoHomeInfo](../../Components/IKeyManager/Value_FlightController_GoHomeInfo.md)

enum

[GoHomeNeedConfirmType](#value_flightcontroller_enum_msdkgohomeneedconfirmtype_inline)

###### enum GoHomeNeedConfirmType

|  |
| --- |
| ``` enum GoHomeNeedConfirmType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Return-to-home confirm status.

##### Enum Members:

|  |  |
| --- | --- |
| NONE | Noneed to confirm. |
| NORMAL | The aircraft detected a complex environment during the Return-to-home process and required user confirmation before continuing the Return-to-home. |

##### Class Members:

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
| GPS\_GLONASS | Using GPS and GLONASS. |
| BEIDOU | Only use the Beidou system. |

##### Class Members:

Flight Coordinate System

enum

[FlightCoordinateSystem](#value_flightcontroller_enum_flightcoordinatesystem_inline)

###### enum FlightCoordinateSystem

|  |
| --- |
| ``` enum FlightCoordinateSystem implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The flight coordinate system of the aircraft. For a detailed definition of the coordinate system, please refer to [Coordinate System](https://developer.dji.com/doc/mobile-sdk-tutorial/cn/basic-introduction/basic-concepts/flight-control.md#body%E5%9D%90%E6%A0%87%E7%B3%BB)

##### Enum Members:

|  |  |
| --- | --- |
| GROUND | Ground coordinate system. |
| BODY | Body coordinate system. |

##### Class Members:

GPS Signal Level

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
| LEVEL\_3 | Level 3. GPS signal is good. From this level, the aircraft can hover in the air. |
| LEVEL\_4 | Level 4. GPS signal is very good. From this level, the aircraft can record the home point. |
| LEVEL\_5 | Level 5. GPS signal is strong. |
| LEVEL\_10 | Level 10. GPS is combined with RTK information.  **Note:  This enumeration is deprecated starting from MSDK 5.10.0 version. Please use the call `getPositioningSolution` to obtain the RTK status.** |
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
| F | Function mode, which is referred to as "F mode". F mode behaves the same as in P mode and enables smart functions like Mission and Smart Directional Control. |
| A | Attitude mode, which is referred to as "A mode". In this mode, the aircraft only provides attitude stabilization, and GPS module and vision system are not used for positioning. In real operation, aircraft will drift noticeably and can not hover, which needs the pilot to correct the position of the aircraft instantly through the remote controller. Attitude mode tests the pilot's handling ability of the aircraft. In some emergency situations, the aircraft needs to switch attitude mode. |
| S | Sport mode, which is referred to as "S mode". The GPS module and vision system are used to achieve precise hovering. In this mode, the gain value of the aircraft is appropriately increased, so be sure to fly with extreme caution. The maximum horizontal speed of the aircraft can reach 20 m/s. |
| P | Position mode, which is referred to as "P mode"("N mode").GPS module and vision system (if available) will be used for aircraft positioning. For products without F mode, smart functions such as mission and smart directional control can be performed in P mode. |
| M | Manual mode, which is referred to as "M mode". In this mode, All actions of the aircraft, including stable attitude and stable altitude, need to be controlled by the pilot through the remote controller. It is dangerous for beginner to operate. |
| T | Tripod mode, which is referred to as "T mode".In this mode, the flight speed and rotation sensitivity of the aircraft will be significantly reduced, allowing more precise control of the screen and more stable shooting results. |

##### Class Members:

enum

[FlightMode](#value_flightcontroller_enum_flightmode_inline)

###### enum FlightMode

|  |
| --- |
| ``` enum FlightMode ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The flight mode of the aircraft.

##### Enum Members:

|  |  |
| --- | --- |
| MANUAL | Manual mode, which is referred to as "M mode". In this mode, All actions of the aircraft, including stable attitude and stable altitude, need to be controlled by the pilot through the remote controller. It is dangerous for beginner to operate. |
| ATTI | Attitude mode, which is referred to as "A mode". In this mode, the aircraft only provides attitude stabilization, and GPS module and vision system are not used for positioning. In real operation, aircraft will drift noticeably and can not hover, which needs the pilot to correct the position of the aircraft instantly through the remote controller. Attitude mode tests the pilot's handling ability of the aircraft. In some emergency situations, the aircraft needs to switch attitude mode. |
| GPS\_NORMAL | GPS normal mode, which is referred to as "N mode"("P mode").GPS module and vision system (if available) will be used for aircraft positioning. |
| GPS\_SPORT | GPS sport mode, which is referred to as "S mode". The GPS module and vision system are used to achieve precise hovering. In this mode, the gain value of the aircraft is appropriately increased, so be sure to fly with extreme caution. The maximum horizontal speed of the aircraft can reach 20 m/s. |
| GPS\_TRIPOD | GPS tripod mode, which is referred to as "T mode".In this mode, the flight speed and rotation sensitivity of the aircraft will be significantly reduced, allowing more precise control of the screen and more stable shooting results. |
| MOTOR\_START | Motro start. |
| TAKE\_OFF\_READY | Take off ready. |
| AUTO\_TAKE\_OFF | Auto take off. |
| AUTO\_LANDING | Auto landing. |
| FORCE\_LANDING | Force landing. |
| GO\_HOME | Go home. |
| WAYPOINT | Waypoint. |
| VIRTUAL\_STICK | Virtual stick. |
| SMART\_FLY | Smart fly. |
| POI | Point of interest(POI). |
| PANO | Panorama. |
| AUTO\_AVOIDANCE | Auto avoidance. |
| APAS | Advanced pilot assistance systems(APAS). |

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

The wind direction of the environment that the aircraft stays in. It’s using the world coordinate system.

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
| GOHOME | Return-to-Home. If the aircraft has successfully recorded the home point (`KeyHomeLocation`) and the GPS signal is good, the aircraft will automatically return to the home point and land to prevent an accident when the remote controller and aircraft lose the connection signal. During the return-to-home process, if the forward vision system is opened and the environment condition is good, the aircraft will automatically climb up to avoid obstacles when the head of the aircraft encounter obstacles. To ensure the orientation of the aircraft head, the user can not adjust the orientation of the aircraft head and can not control the aircraft to fly left and right. |

##### Class Members:

LED

class

[LEDsSettings](../../Components/IKeyManager/Value_FlightController_Struct_LEDsSettings.md)

Security Code

class

[AccessLockerVerifySecurityCodeInfo](../../Components/IKeyManager/Value_FlightController_Struct_AccessLockerV1VerifyUserAccountInfo.md)

class

[AccessLockerDeviceStatus](../../Components/IKeyManager/Value_FlightController_Struct_AccessLockerV1DeviceState.md)

enum

[AccessLockerStorageType](#value_flightcontroller_enum_accesslockerv1featurepoint_inline)

###### enum AccessLockerStorageType

|  |
| --- |
| ``` enum AccessLockerStorageType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Storage type.

##### Enum Members:

|  |  |
| --- | --- |
| SD\_CARD | SD card. |

##### Class Members:

class

[AccessLockerEncryptionStatus](../../Components/IKeyManager/Value_FlightController_Struct_AccessLockerV1EncryptionState.md)

class

[AccessLockerModifySecurityCodeInfo](../../Components/IKeyManager/Value_FlightController_Struct_AccessLockerV1ModifyUserAccountInfo.md)

class

[AccessLockerResetSecurityCodeInfo](../../Components/IKeyManager/Value_FlightController_Struct_AccessLockerV1ResetUserAccountInfo.md)

class

[AccessLockerSetSecurityCodeInfo](../../Components/IKeyManager/Value_FlightController_Struct_AccessLockerV1SetupUserAccountInfo.md)

enum

[AccessLockerDeviceType](#value_flightcontroller_enum_accesslockerv1deviceid_inline)

###### enum AccessLockerDeviceType

|  |
| --- |
| ``` enum AccessLockerDeviceType implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

Device type.

##### Enum Members:

|  |  |
| --- | --- |
| MASTER | Master device, usually the aircraft. |
| SLAVE\_1 | Slave device 1, usually the portside (master) payload of the aircraft. |
| SLAVE\_2 | Slave device 2, usually the starboard payload of the aircraft. |
| SLAVE\_3 | Slave device 3, usually the upper side payload of the aircraft. |

##### Class Members:

Warning Level

enum

[AirSenseWarningLevel](#value_flightcontroller_enum_airsensewarninglevel_inline)

###### enum AirSenseWarningLevel

|  |
| --- |
| ``` enum AirSenseWarningLevel implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The warning level determined by the DJI AirSense system. The warning level is based on the distance between the airplane and the DJI aircraft, and the heading of the airplane.

##### Enum Members:

|  |  |
| --- | --- |
| LEVEL\_0 | The system detects the airplane but the DJI aircraft is either far away from the airplane or is in the opposite direction of the airplane's heading. |
| LEVEL\_1 | The system detects the airplane. The probability that it will pass through the location of the DJI aircraft is considered low. |
| LEVEL\_2 | The system detects the airplane. The probability that it will pass through the location of the DJI aircraft is considered medium. |
| LEVEL\_3 | The system detects the airplane. The probability that it will pass through the location of the DJI aircraft is considered high. |
| LEVEL\_4 | The system detects the airplane. The probability that it will pass through the location of the DJI aircraft is very high. |

##### Class Members:

enum

[AirSenseDirection](#value_flightcontroller_enum_airsensedirection_inline)

###### enum AirSenseDirection

|  |
| --- |
| ``` enum AirSenseDirection implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.flightcontroller |

##### Description:

The direction of the airplane relative to the DJI aircraft.

##### Enum Members:

|  |  |
| --- | --- |
| NORTH | North. |
| NORTH\_EAST | Northeast. |
| EAST | East. |
| SOUTH\_EAST | Southeast. |
| SOUTH | South. |
| SOUTH\_WEST | Southwest. |
| WEST | West. |
| NORTH\_WEST | Northwest. |

##### Class Members:

class

[AirSenseAirplaneState](../../Components/IKeyManager/Value_FlightController_Struct_AirSenseAirplaneState.md)

AirSense

class

[AirSenseSystemInformation](../../Components/IKeyManager/Value_FlightController_Struct_AirSenseSystemInformation.md)

#### Battery

Battery LED

class

[BatteryLedsInfo](../../Components/IKeyManager/Value_Battery_Struct_BatteryLedsControlMsg.md)

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

[PlayingAudioFileInfo](../../Components/IKeyManager/Value_Accessory_Struct_PlayingAudioFileInfo.md)

#### Air Link

enum

[Bandwidth](#value_airlink_enum_airlinkbandwidth_inline)

###### enum Bandwidth

|  |
| --- |
| ``` enum Bandwidth implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

The settings of the download link bandwidth. Sets the narrow bandwidth will lower the data transmitting rate but make the airlink signal stronger.

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

[FrequencyBand](#value_airlink_enum_airlinkfrequencyband_inline)

###### enum FrequencyBand

|  |
| --- |
| ``` enum FrequencyBand implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

The type of airlink frequency band.

##### Enum Members:

|  |  |
| --- | --- |
| BAND\_2\_DOT\_4G | 2.4GHz. |
| BAND\_5\_DOT\_8G | 5.8GHz. |

##### Class Members:

class

[WlmLinkQualityLevelInfo](../../Components/IKeyManager/Value_Airlink_Struct_WlmLinkQualityMsg.md)

class

[WlmDongleInfo](../../Components/IKeyManager/Value_Airlink_Struct_WlmDongleState.md)

enum

[WlmDongleWorkState](#value_airlink_enum_wlmdongleworkstate_inline)

###### enum WlmDongleWorkState

|  |
| --- |
| ``` enum WlmDongleWorkState implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

LTE Dongle working state.

##### Enum Members:

|  |  |
| --- | --- |
| NOT\_REGISTER\_NO\_SEARCH | The SIM card is not registered and the mobile network is not searched. |
| NOT\_REGISTER\_SEARCHING | SIM card is not registered, searching the mobile network. |
| REGISTER\_HOME\_NETWORK | The SIM card has been registered and is being used in the home network. |
| REGISTER\_ROAMING | SIM card is registered and and is roaming the network. |
| REGISTER\_DENIED | SIM card registration denied. |

##### Class Members:

class

[WlmDongleListInfo](../../Components/IKeyManager/Value_Airlink_Struct_WlmDongleInfoMsg.md)

enum

[WlmLinkQualityLevel](#value_airlink_enum_wlmlinkquality_inline)

###### enum WlmLinkQualityLevel

|  |
| --- |
| ``` enum WlmLinkQualityLevel implements JNIProguardKeepTag ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.value.airlink |

##### Description:

Wireless link manager signal quality level.

##### Enum Members:

|  |  |
| --- | --- |
| NO\_SIGNAL | no signal. |
| LEVEL\_1 | Level 1, the signal is weak. |
| LEVEL\_2 | Level 2, the signal is relatively weak. |
| LEVEL\_3 | Level 3, the signal is normal. |
| LEVEL\_4 | Level 4, the signal is relatively strong. |
| LEVEL\_5 | Level 5, the signal is strong. |

##### Class Members:

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found