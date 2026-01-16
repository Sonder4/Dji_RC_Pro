# class DJICameraKey

|  |
| --- |
| ``` class DJICameraKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

`DJICameraKey`provides a set of methods to set and get the camera parameters including camera type, camera settings etc. `Supported since MSDK 5.0`

##### Class Members:

#### Basic Information

Camera Type

[KeyCameraType](#key_camera_cameratype_inline)

###### final KeyCameraType

|  |
| --- |
| ``` static final DJIKeyInfo<CameraType> KeyCameraType = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraType", new SingleValueConverter<>(CameraType.class,CameraTypeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `CameraType` To get camera type. `Supported since MSDK 5.0`

#### Shoot Photo and Record Video

Camera Working Mode

[KeyCameraFlatModeRange](#key_camera_quickcameramoderange_inline)

###### final KeyCameraFlatModeRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraFlatMode>> KeyCameraFlatModeRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFlatModeRange", new SingleValueConverter<>((Class)List.class,QuickCameraModeRangeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("QuickCameraModeRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraFlatMode`> To get the working mode of camera that can be set currently. `Supported since MSDK 5.0`

[KeyCameraFlatMode](#key_camera_quickcameramode_inline)

###### final KeyCameraFlatMode

|  |
| --- |
| ``` static final DJIKeyInfo<CameraFlatMode> KeyCameraFlatMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFlatMode", new SingleValueConverter<>(CameraFlatMode.class,QuickCameraModeValue.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("QuickCameraMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraFlatMode` To set working mode of camera, including shooting photo and recording video. Working mode of camera that can be set currently can be get through `KeyCameraFlatModeRange`. This mode takes effect for all lenses. `Supported since MSDK 5.0`

Shoot Photo

[KeyIsShootingPhoto](#key_camera_isshootingphoto_inline)

###### final KeyIsShootingPhoto

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsShootingPhoto = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsShootingPhoto", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means camera is shooting. `Supported since MSDK 5.0`

[KeyStartShootPhoto](#key_camera_startshootphoto_inline)

###### final KeyStartShootPhoto

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartShootPhoto = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartShootPhoto", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start shooting photo. Camera must be in shooting photo mode. For infrared lenses, users can take photos while recording video. If the shooting photo mode is `PHOTO_INTERVAL`, you need to call `KeyStopShootPhoto` to stop the camera from shooting photo. Before using this method, you should check the SD Card status to make sure enough space. `Supported since MSDK 5.0`

[KeyStopShootPhoto](#key_camera_stopshootphoto_inline)

###### final KeyStopShootPhoto

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopShootPhoto = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopShootPhoto", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Stop shooting photo. When the camera shooting mode is `PHOTO_INTERVAL`, `PHOTO_REGIONAL_SR` and `PHOTO_PANO`, and `KeyStartShootPhoto`is called to start shooting, you can call this interface to stop shooting. If the camera shooting mode is set to be single shooting, camera will stop shooting automatically after single photo is shoot, there is no need to call this interface. `Supported since MSDK 5.0`

[KeyCaptureCameraStreamSettings](#key_camera_capturecamerastreamsettings_inline)

###### final KeyCaptureCameraStreamSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CameraStreamSettingsInfo> KeyCaptureCameraStreamSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CaptureCameraStreamSettings", new DJIValueConverter<>(CameraStreamSettingsInfo.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraStreamSettingsInfo` Multi-lens photo storage settings. All lenses of this camera takes photos by default, and the photos stores to the SD Card. You can call this interface to select required lens for shooting photo and storage. Before calling this interface, you need to call `KeyCameraFlatMode` to set camera shooting mode to `PHOTO_NORMAL`. `Supported since MSDK 5.0`

[KeyPhotoFileFormatRange](#key_camera_photostorageformatrange_inline)

###### final KeyPhotoFileFormatRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<PhotoFileFormat>> KeyPhotoFileFormatRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoFileFormatRange", new SingleValueConverter<>((Class)List.class,PhotoStorageFormatRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PhotoStorageFormatRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`PhotoFileFormat`> To get the camera's photo format range. `Supported since MSDK 5.0`

[KeyPhotoFileFormat](#key_camera_photostorageformat_inline)

###### final KeyPhotoFileFormat

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoFileFormat> KeyPhotoFileFormat = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoFileFormat", new SingleValueConverter<>(PhotoFileFormat.class,PhotoStorageFormatMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PhotoStorageFormat") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoFileFormat` To set and get the photo format supported by the camera. `Supported since MSDK 5.0`

[KeyPhotoIntervalShootSettings](#key_camera_photointervalshootsettings_inline)

###### final KeyPhotoIntervalShootSettings

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoIntervalShootSettings> KeyPhotoIntervalShootSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoIntervalShootSettings", new DJIValueConverter<>(PhotoIntervalShootSettings.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoIntervalShootSettings` Set and get the number of pictures and the interval time between pictures， and is used when `KeyCameraFlatMode` is `PHOTO_INTERVAL`. `Supported since MSDK 5.0`

[KeyPhotoIntervalCountdown](#key_camera_photointervalcountdown_inline)

###### final KeyPhotoIntervalCountdown

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyPhotoIntervalCountdown = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoIntervalCountdown", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the countdown of interval photo shooting. When interval shooting mode is set, after calling `KeyStartShootPhoto` to start shooting, the camera will count down the interval time between photos and will shoot one photo after the countdown reaches 0. `Supported since MSDK 5.0`

[KeySuperResolutionInfo](#key_camera_superresolutionstate_inline)

###### final KeySuperResolutionInfo

|  |
| --- |
| ``` static final DJIKeyInfo<SuperResolutionInfo> KeySuperResolutionInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SuperResolutionInfo", new DJIValueConverter<>(SuperResolutionInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("SuperResolutionState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`SuperResolutionInfo` To get the status and information of ultra-clear matrix photo, and is used when `KeyCameraFlatMode` is `PHOTO_REGIONAL_SR`. `Supported since MSDK 5.0`

[KeySuperResolutionCaptureArea](#key_camera_superresolutioncapturearea_inline)

###### final KeySuperResolutionCaptureArea

|  |
| --- |
| ``` static final DJIKeyInfo<DoubleRect4Sides> KeySuperResolutionCaptureArea = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SuperResolutionCaptureArea", new DJIValueConverter<>(DoubleRect4Sides.class)).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`Value_Common_Struct_RectF` To set the area of ultra-clear matrix photo shooting, and is used when `KeyCameraFlatMode` is `PHOTO_REGIONAL_SR`. Area range: `getMaxArea`. `Supported since MSDK 5.0`

[KeyPhotoPanoramaMode](#key_camera_visionphotopanoramamode_inline)

###### final KeyPhotoPanoramaMode

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoPanoramaMode> KeyPhotoPanoramaMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoPanoramaMode", new SingleValueConverter<>(PhotoPanoramaMode.class,VisionPhotoPanoramaModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VisionPhotoPanoramaMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoPanoramaMode` To set panoramic photo shooting mode, and is used when `KeyCameraFlatMode` is `PHOTO_PANO`. `Supported since MSDK 5.0`

[KeyIsShootingPhotoPanorama](#key_camera_isshootingvisionpanoramaphoto_inline)

###### final KeyIsShootingPhotoPanorama

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsShootingPhotoPanorama = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsShootingPhotoPanorama", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IsShootingVisionPanoramaPhoto") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means panoramic photo is being shot, and is used when `KeyCameraFlatMode` is `PHOTO_PANO`. `Supported since MSDK 5.0`

[KeyPhotoPanoramaProgress](#key_camera_panoramaphotocaptureprogress_inline)

###### final KeyPhotoPanoramaProgress

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyPhotoPanoramaProgress = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoPanoramaProgress", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PanoramaPhotoCaptureProgress") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the percentage of paronamic photo shooting, and is used when `KeyCameraFlatMode` is `PHOTO_PANO`. `Supported since MSDK 5.0`

Record Video

[KeyIsRecording](#key_camera_isrecording_inline)

###### final KeyIsRecording

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsRecording = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsRecording", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that camera is recording video. `Supported since MSDK 5.0`

[KeyStartRecord](#key_camera_startrecord_inline)

###### final KeyStartRecord

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartRecord = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartRecord", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start recording video. It is required that calling `KeyCameraFlatMode` to set camera working mode to `VIDEO_NORMAL`. For Infrared lens, user can shooting photo when the video is recording. Before using this method, SD Card status should be checked to make sure there has enough space. `Supported since MSDK 5.0`

[KeyStopRecord](#key_camera_stoprecord_inline)

###### final KeyStopRecord

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopRecord = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopRecord", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Stop recording video. `Supported since MSDK 5.0`

[KeyRecordingTime](#key_camera_recordingtime_inline)

###### final KeyRecordingTime

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyRecordingTime = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RecordingTime", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

The duration of the video currently recorded by the camera. Unit:second. `Supported since MSDK 5.0`

[KeyRecordCameraStreamSettings](#key_camera_recordcamerastreamsettings_inline)

###### final KeyRecordCameraStreamSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CameraStreamSettingsInfo> KeyRecordCameraStreamSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RecordCameraStreamSettings", new DJIValueConverter<>(CameraStreamSettingsInfo.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraStreamSettingsInfo` Multi-lens recording sotrage setting. By default, all lenses of this camera can record video and the video will store in SD Card. You can call this interface to choose required lens to record video and store. Before calling this interface, `KeyCameraFlatMode` should be called to set camera working mode to `VIDEO_NORMAL`. `Supported since MSDK 5.0`

[KeyVideoFileFormatRange](#key_camera_videostorageformatrange_inline)

###### final KeyVideoFileFormatRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<VideoFileFormat>> KeyVideoFileFormatRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoFileFormatRange", new SingleValueConverter<>((Class)List.class,VideoStorageFormatRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VideoStorageFormatRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`VideoFileFormat`> To get recording format range of the camera. `Supported since MSDK 5.0`

[KeyVideoFileFormat](#key_camera_videostorageformat_inline)

###### final KeyVideoFileFormat

|  |
| --- |
| ``` static final DJIKeyInfo<VideoFileFormat> KeyVideoFileFormat = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoFileFormat", new SingleValueConverter<>(VideoFileFormat.class,VideoStorageFormatMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VideoStorageFormat") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoFileFormat` To set and get video format supported by the camera. `Supported since MSDK 5.0`

Media File

[KeyNewlyGeneratedMediaFile](#key_camera_newlygeneratedmediafile_inline)

###### final KeyNewlyGeneratedMediaFile

|  |
| --- |
| ``` static final DJIKeyInfo<GeneratedMediaFileInfo> KeyNewlyGeneratedMediaFile = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"NewlyGeneratedMediaFile", new DJIValueConverter<>(GeneratedMediaFileInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`GeneratedMediaFileInfo` After the photo shooting and video recording is started, camera will generate new photo or video. This interface can be used to get media file information generated. `Supported since MSDK 5.0`

Customized File Folder

[KeyCustomExpandDirectoryNameSettings](#key_camera_customexpanddirectorynamesettings_inline)

###### final KeyCustomExpandDirectoryNameSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CustomExpandNameSettings> KeyCustomExpandDirectoryNameSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomExpandDirectoryNameSettings", new DJIValueConverter<>(CustomExpandNameSettings.class)).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CustomExpandNameSettings` To set and get extension name of customized file folder. After calling this interface, newly generated media file (video or photo) will be stored in new file folder. Default file folder name plus the extension name you set will be the name of new file folder. For example, the default file folder name is “DJI\_202001012359\_01”, and the customized extension name of file folder is “Mission1”, therefore the new file folder name will be “DJI\_202001012359\_01\_Mission1”. In one flight, you can set multiple extension directory names to create multiple customized folders. `Supported since MSDK 5.0`

[KeyCustomExpandFileNameSettings](#key_camera_customexpandfilenamesettings_inline)

###### final KeyCustomExpandFileNameSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CustomExpandNameSettings> KeyCustomExpandFileNameSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomExpandFileNameSettings", new DJIValueConverter<>(CustomExpandNameSettings.class)).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CustomExpandNameSettings` To set and get exxtension name of customized file. After calling this interface, the default file name plus the extension name you set will be the name of nest media file(video or photo). For example, the default name photo name is “DJI\_2020012091415\_999\_WIDE.JPG”, and the extension name of the customized file you set is “Waypoint1”, therefore the name of new photo will be “DJI\_2020012091415\_999\_WIDE\_Waypoint1.JPG”. This operation only takes effect once. If it is required that to all media file in one flight have same extension name, you need to set same extension name before each photo shooting. `Supported since MSDK 5.0`

Storage

[KeyCameraStorageInfos](#key_camera_camerastorageinfostate_inline)

###### final KeyCameraStorageInfos

|  |
| --- |
| ``` static final DJIKeyInfo<CameraStorageInfos> KeyCameraStorageInfos = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraStorageInfos", new DJIValueConverter<>(CameraStorageInfos.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraStorageInfoState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraStorageInfos` To get the storage information of camera SD Card and onboard memory. Onboard memory of M30 and M30T camera are for internal use only. `Supported since MSDK 5.0`

Video Subtitles

[KeyCameraVideoCaptionEnabled](#key_camera_cameravideocaptionenabled_inline)

###### final KeyCameraVideoCaptionEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyCameraVideoCaptionEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraVideoCaptionEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means that video subtitles is opened. The location of aircraft, flight time and camera parameters will be stored in video subtitles srt file in SD Card. `Supported since MSDK 5.0`

Water Mark

[KeyCameraWatermarkSettings](#key_camera_camerawatermarksettings_inline)

###### final KeyCameraWatermarkSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CameraWatermarkSettings> KeyCameraWatermarkSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraWatermarkSettings", new DJIValueConverter<>(CameraWatermarkSettings.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraWatermarkSettings` Water mark setting of camera. It can be set whether to generate water mark in photo or video or not. You can set the content that needs to be displayed in the watermark through `KeyWatermarkDisplayContentSettings`. Set custom watermark text via `KeyWatermarkUserCustomInfo`. `Supported since MSDK 5.0`

[KeyWatermarkDisplayContentSettings](#key_camera_watermarkdisplaycontentsettings_inline)

###### final KeyWatermarkDisplayContentSettings

|  |
| --- |
| ``` static final DJIKeyInfo<WatermarkDisplayContentSettings> KeyWatermarkDisplayContentSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WatermarkDisplayContentSettings", new DJIValueConverter<>(WatermarkDisplayContentSettings.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`WatermarkDisplayContentSettings` Content setting of camera water mark. Content of water mark that needs to display in photo and video can be set. You can set customized content of water mark through `KeyWatermarkUserCustomInfo`. `Supported since MSDK 5.0`

[KeyWatermarkUserCustomInfo](#key_camera_watermarkusercustominfo_inline)

###### final KeyWatermarkUserCustomInfo

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyWatermarkUserCustomInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WatermarkUserCustomInfo", SingleValueConverter.StringConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**String To set and get the customized copywriting in water mark. You can set whether to display customized copywriting through `KeyWatermarkDisplayContentSettings`. `Supported since MSDK 5.0`

Gimbal Lock

[KeyLockGimbalDuringShootPhotoEnabled](#key_camera_lockgimbalduringshootphotoenabled_inline)

###### final KeyLockGimbalDuringShootPhotoEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLockGimbalDuringShootPhotoEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LockGimbalDuringShootPhotoEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that gimbal will be locked when shooting photo. If this function is opened, flight movement influence on image effect can be avoided. `Supported since MSDK 5.0`

LED Turn Off

[KeyAutoTurnOffLEDMode](#key_camera_autoturnoffledmode_inline)

###### final KeyAutoTurnOffLEDMode

|  |
| --- |
| ``` static final DJIKeyInfo<AutoTurnOffLEDMode> KeyAutoTurnOffLEDMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AutoTurnOffLEDMode", new SingleValueConverter<>(AutoTurnOffLEDMode.class,AutoTurnOffLEDModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `AutoTurnOffLEDMode` To set LED automatically turn off when shooting photo and recording video. The LEDs include the front arm LED, rear arm LED and navigation LED. `Supported since MSDK 5.0`

Laser Mode

[KeyLaserWorkMode](#key_camera_laserworkmode_inline)

###### final KeyLaserWorkMode

|  |
| --- |
| ``` static final DJIKeyInfo<LaserWorkMode> KeyLaserWorkMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LaserWorkMode", new SingleValueConverter<>(LaserWorkMode.class,LaserWorkModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `LaserWorkMode` To set laser working mode. You can choose enhance mode or open laser according to requirement. The laser module will revert to enhance mode after the camera restarts. `Supported since MSDK 5.0`

[KeyLaserMeasureEnabled](#key_camera_lasermeasureenable_inline)

###### final KeyLaserMeasureEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLaserMeasureEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LaserMeasureEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("LaserMeasureEnable") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean To open or close laser module. When `KeyLaserWorkMode`is set to be `OPEN_ON_DEMAND`, you can open laser module through this interface. `Supported since MSDK 5.0`

#### lens setting

Video Source

[KeyCamearaVideoStreamSource](#key_camera_liveviewcamerasource_inline)

###### final KeyCamearaVideoStreamSource

|  |
| --- |
| ``` static final DJIKeyInfo<CameraVideoStreamSourceType> KeyCamearaVideoStreamSource = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CamearaVideoStreamSource", new SingleValueConverter<>(CameraVideoStreamSourceType.class,LiveViewSourceCameraMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("LiveViewCameraSource") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraVideoStreamSourceType` To set camera video source. For camera with multiple lens, video have different lens source. After setting camera video source, current live video stream will be swifted to the set lens video stream. `Supported since MSDK 5.0`

Exposure Mode

[KeyExposureModeRange](#key_camera_exposuremoderange_inline)

###### final KeyExposureModeRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraExposureMode>> KeyExposureModeRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ExposureModeRange", new SingleValueConverter<>((Class)List.class,CameraExposureModeRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraExposureMode`> To get the exposure mode range of selected lens. `Supported since MSDK 5.0`

[KeyExposureMode](#key_camera_exposuremode_inline)

###### final KeyExposureMode

|  |
| --- |
| ``` static final DJIKeyInfo<CameraExposureMode> KeyExposureMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ExposureMode", new SingleValueConverter<>(CameraExposureMode.class,CameraExposureModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraExposureMode` To set the exposure mode of selected lens. The exposure mode decides whether the aperture, shutter speed and ISO can be set automatically or manually. The settable exposure mode can be obtained through `KeyExposureModeRange`. When `CameraVideoStreamSourceType` is `INFRARED_CAMERA`, exposure mode can not be set. `Supported since MSDK 5.0`

Exposure Compensation

[KeyExposureCompensationRange](#key_camera_exposurecompensationrange_inline)

###### final KeyExposureCompensationRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraExposureCompensation>> KeyExposureCompensationRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ExposureCompensationRange", new SingleValueConverter<>((Class)List.class,CameraExposureCompensationRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraExposureCompensation`> To get the exposure compensation range of camera lens. `Supported since MSDK 5.0`

[KeyExposureCompensation](#key_camera_exposurecompensation_inline)

###### final KeyExposureCompensation

|  |
| --- |
| ``` static final DJIKeyInfo<CameraExposureCompensation> KeyExposureCompensation = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ExposureCompensation", new SingleValueConverter<>(CameraExposureCompensation.class,CameraExposureCompensationMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraExposureCompensation` To set exposure compensation value of camera lens. Settable exposure compensation value can be obtained through `KeyExposureCompensationRange`. To use this function, please get the current live video stream through `KeyCamearaVideoStreamSource` interface, and set the lens compensation mode of current live video stream to `PROGRAM`. `Supported since MSDK 5.0`

Automatic Exposure

[KeyAELockEnabled](#key_camera_aelockenabled_inline)

###### final KeyAELockEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyAELockEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AELockEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean The automatic exposure lock of camera lens. If AE Lock is started, `KeyCameraMeteringMode` function can not be used. `Supported since MSDK 5.0`

Metering

[KeyCameraMeteringMode](#key_camera_camerameteringmode_inline)

###### final KeyCameraMeteringMode

|  |
| --- |
| ``` static final DJIKeyInfo<CameraMeteringMode> KeyCameraMeteringMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraMeteringMode", new SingleValueConverter<>(CameraMeteringMode.class,CameraMeteringModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraMeteringMode` To set metering mode of camera lens. If you want to use center metering or average metering function, you only need to set `CENTER` or `AVERAGE` once, the lens will operate metering once. If you want to use area metering function, `KeySpotMeteringTargetArea` should be called to set meiering area, and the lens will perform area metering once. **Caution: If you want to use metering function, please use `KeyExposureMode` to set the exposure mode to `PROGRAM`, `SHUTTER_PRIORITY` or `APERTURE_PRIORITY`, and call `KeyAELockEnabled` to set AE Lock to `false`.** `Supported since MSDK 5.0`

[KeySpotMeteringTargetArea](#key_camera_spotmeteringtargetarea_inline)

###### final KeySpotMeteringTargetArea

|  |
| --- |
| ``` static final DJIKeyInfo<DoubleRect> KeySpotMeteringTargetArea = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SpotMeteringTargetArea", new DJIValueConverter<>(DoubleRect.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`Value_Common_Struct_DoubleRect` Area metering of camera lens. (x,y) of `Value_Common_Struct_DoubleRect` is the coordinate position of normalized (0-1). width and height are the area size of normalized (0-1). If width and height are not 0, (x,y) is the upper left coordinate, which is used for area metering. If width and height are 0, (x,y) is the center point coordinate, which is used for spot metering. `Supported since MSDK 5.0`

ISO

[KeyISORange](#key_camera_isorange_inline)

###### final KeyISORange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraISO>> KeyISORange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ISORange", new SingleValueConverter<>((Class)List.class,CameraISORangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraISO`> To get ISO range of camera lens. `Supported since MSDK 5.0`

[KeyISO](#key_camera_iso_inline)

###### final KeyISO

|  |
| --- |
| ``` static final DJIKeyInfo<CameraISO> KeyISO = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ISO", new SingleValueConverter<>(CameraISO.class,CameraISOMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraISO` To set and get ISO value of camera lens. The ISO range can be obtained through `KeyISORange`. `Supported since MSDK 5.0`

Shutter

[KeyShutterSpeedRange](#key_camera_shutterspeedrange_inline)

###### final KeyShutterSpeedRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraShutterSpeed>> KeyShutterSpeedRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ShutterSpeedRange", new SingleValueConverter<>((Class)List.class,CameraShutterSpeedRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraShutterSpeed`> To get shutter speed range of camera lens. `Supported since MSDK 5.0`

[KeyShutterSpeed](#key_camera_shutterspeed_inline)

###### final KeyShutterSpeed

|  |
| --- |
| ``` static final DJIKeyInfo<CameraShutterSpeed> KeyShutterSpeed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ShutterSpeed", new SingleValueConverter<>(CameraShutterSpeed.class,CameraShutterSpeedMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraShutterSpeed` To set shutter speed value of camera lens. Settable shutter speed can be obtained through `KeyShutterSpeedRange`.When current camera working mode is `VIDEO_NORMAL`, the shutter speed should not lower than the video frame rate。 If the video frame rate is 30fps, the shutter spped must be <=1/30. `Supported since MSDK 5.0`

Photo Ratio

[KeyPhotoRatioRange](#key_camera_photoratiorange_inline)

###### final KeyPhotoRatioRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<PhotoRatio>> KeyPhotoRatioRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoRatioRange", new SingleValueConverter<>((Class)List.class,PhotoRatioRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`PhotoRatio`> To get photo ratio of camera lens. `Supported since MSDK 5.0`

[KeyPhotoRatio](#key_camera_photoratio_inline)

###### final KeyPhotoRatio

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoRatio> KeyPhotoRatio = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoRatio", new SingleValueConverter<>(PhotoRatio.class,PhotoRatioMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoRatio` To set photo ratio of camera lens. Settable photo ratio can be obtained through `KeyPhotoRatioRange`. `Supported since MSDK 5.0`

Video Resolution and Frame Rate

[KeyVideoResolutionFrameRateRange](#key_camera_videoresolutionframeraterange_inline)

###### final KeyVideoResolutionFrameRateRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<VideoResolutionFrameRate>> KeyVideoResolutionFrameRateRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoResolutionFrameRateRange", new SingleValueConverter<>((Class)List.class,VideoResolutionFrameRateRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`VideoResolutionFrameRate`> To get the video resolution and frame rate range of camera lens. `Supported since MSDK 5.0`

[KeyVideoResolutionFrameRate](#key_camera_videoresolutionframerate_inline)

###### final KeyVideoResolutionFrameRate

|  |
| --- |
| ``` static final DJIKeyInfo<VideoResolutionFrameRate> KeyVideoResolutionFrameRate = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoResolutionFrameRate", new DJIValueConverter<>(VideoResolutionFrameRate.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoResolutionFrameRate` To set the video resolution and frame rate range of camera lens. Settable video resolution and frame rate can be obtained through `KeyVideoResolutionFrameRateRange`. `Supported since MSDK 5.0`

Hybrid Zoom

[KeyCameraHybridZoomSpec](#key_camera_camerahybridzoomspec_inline)

###### final KeyCameraHybridZoomSpec

|  |
| --- |
| ``` static final DJIKeyInfo<CameraHybridZoomSpec> KeyCameraHybridZoomSpec = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraHybridZoomSpec", new DJIValueConverter<>(CameraHybridZoomSpec.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraHybridZoomSpec` To get hybrid zoom parameters of zoom lens. `Supported since MSDK 5.0`

[KeyCameraHybridZoomFocalLength](#key_camera_camerahybridzoomfocallength_inline)

###### final KeyCameraHybridZoomFocalLength

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraHybridZoomFocalLength = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraHybridZoomFocalLength", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Interger To set and get focal length of zoom lens. Unit: 0.1 mm. Settable focal length range and step size can be obtained through `KeyCameraHybridZoomSpec`. Hybrid zoom combines optical zoom and digital zoom. When the optical zoom range is exceeded, the lens will automatically switch to digital zoom. `Supported since MSDK 5.0`

Focus

[KeyCameraFocusMode](#key_camera_camerafocusmode_inline)

###### final KeyCameraFocusMode

|  |
| --- |
| ``` static final DJIKeyInfo<CameraFocusMode> KeyCameraFocusMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusMode", new SingleValueConverter<>(CameraFocusMode.class,CameraFocusModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraFocusMode` To set and get focus mode of zoom lens. `Supported since MSDK 5.0`

[KeyCameraFocusRingMinValue](#key_camera_camerafocusringminvalue_inline)

###### final KeyCameraFocusRingMinValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraFocusRingMinValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusRingMinValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer The minimum settable value that the camera manually focusing. `Supported since MSDK 5.0`

[KeyCameraFocusRingMaxValue](#key_camera_camerafocusringmaxvalue_inline)

###### final KeyCameraFocusRingMaxValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraFocusRingMaxValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusRingMaxValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer The maximum settable value that the camera manually focusing. `Supported since MSDK 5.0`

[KeyCameraFocusRingValue](#key_camera_camerafocusringvalue_inline)

###### final KeyCameraFocusRingValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraFocusRingValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusRingValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer The manually focusing value of camera. The maximum and minimum settable value that the camera manually focusing can be obtained through `KeyCameraFocusRingMinValue` and `KeyCameraFocusRingMaxValue`. In `MANUAL` mode, camera will focus once with the center point of the screen as the focus target after setting the focus value. `Supported since MSDK 5.0`

[KeyCameraFocusTarget](#key_camera_camerafocustarget_inline)

###### final KeyCameraFocusTarget

|  |
| --- |
| ``` static final DJIKeyInfo<DoublePoint2D> KeyCameraFocusTarget = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusTarget", new DJIValueConverter<>(DoublePoint2D.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`Value_Common_Struct_DoublePoint2D` The focus target of camera automaic focus. [0,0] means the upper left corner of camera screen. [1,1] means the lower left corner of camera screen. In `AF` mode, camera will automatically focus on the focus target once after setting focus target. `Supported since MSDK 5.0`

Night Scene Mode

[KeyIRCutEnabled](#key_camera_ircutenable_inline)

###### final KeyIRCutEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIRCutEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IRCutEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IRCutEnable") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means night scene mode is opened. Night scene mode improves the shooting effect in low-light environments by turning on the infrared filter in the zoom camera. It only supports zoom lens. `Supported since MSDK 5.0`

Lens Defogging

[KeyDefogEnabled](#key_camera_defogenable_inline)

###### final KeyDefogEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyDefogEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DefogEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("DefogEnable") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means lens defogging function is opened. Lens defogging requires continuous heating for 5 seconds. `Supported since MSDK 5.0`

#### Infrared Function

Point Temperature Measurement

[KeyThermalSpotMeteringTargetPoint](#key_camera_thermalspotmeteringtargetpoint_inline)

###### final KeyThermalSpotMeteringTargetPoint

|  |
| --- |
| ``` static final DJIKeyInfo<DoublePoint2D> KeyThermalSpotMeteringTargetPoint = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalSpotMeteringTargetPoint", new DJIValueConverter<>(DoublePoint2D.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_DoublePoint2D` To set the location of the point that needs to be measured. [0,0] means the upper left corner of camera screen. [1,1] means the lower left corner of camera screen. After setting the location of temperature measuring point, current temperature of measuring point can be obtained through `KeyThermalSpotMeteringTemperature`. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalSpotMeteringTemperature](#key_camera_thermaltemperaturedata_inline)

###### final KeyThermalSpotMeteringTemperature

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyThermalSpotMeteringTemperature = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalSpotMeteringTemperature", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("ThermalTemperatureData") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double To get current temperature of measuring point. Calling `KeyThermalSpotMeteringTargetPoint` to set current location of temperature measuring point. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Regional Temperature Measurement

[KeyThermalSpotMeteringArea](#key_camera_thermalspotmeteringarea_inline)

###### final KeyThermalSpotMeteringArea

|  |
| --- |
| ``` static final DJIKeyInfo<DoubleRect> KeyThermalSpotMeteringArea = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalSpotMeteringArea", new DJIValueConverter<>(DoubleRect.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Common_Struct_DoubleRect` To set regional location that needs temperature measurement. [0,0] means the upper left corner of camera screen. [1,1] means the lower left corner of camera screen. After setting the location of temperature measuring point, temperature information of current temperature measuring region can be obtained through `KeyThermalAreaTemperatureAggregations`. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalAreaTemperatureAggregations](#key_camera_thermalareatemperatureaggregations_inline)

###### final KeyThermalAreaTemperatureAggregations

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalAreaTemperatureAggregations> KeyThermalAreaTemperatureAggregations = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalAreaTemperatureAggregations", new DJIValueConverter<>(ThermalAreaTemperatureAggregations.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `Value_Camera_Struct_ThermalAreaTemperatutemreAggregationsMsg` To get current temperature information of current temperature measuring region. The information includes average temperatur, minimum temperature and maximum temperature. Regional location that needs temperature measurement can be obtained through `KeyThermalSpotMeteringArea`. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Gain Mode

[KeyThermalGainMode](#key_camera_camerathermalgainmode_inline)

###### final KeyThermalGainMode

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalGainMode> KeyThermalGainMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalGainMode", new SingleValueConverter<>(ThermalGainMode.class,CameraThermalGainModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalGainMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalGainMode` To set and get gain mode. The temperature applicable range under different gain modes can be obtained through `KeyThermalGainModeTemperatureRange`. In high gain mode, a narrower temperature range can be captured with a higher sensitivity to temperature differences. In low gain mode, a wider temperature range can be captured with a lower sensitivity to temperature difference. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalGainModeTemperatureRange](#key_camera_thermalgainmodetemperaturerange_inline)

###### final KeyThermalGainModeTemperatureRange

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalGainModeTemperatureRange> KeyThermalGainModeTemperatureRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalGainModeTemperatureRange", new DJIValueConverter<>(ThermalGainModeTemperatureRange.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalGainModeTemperatureRange` To get temperature range of different gain modes. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Palette

[KeyThermalPaletteRange](#key_camera_camerathermalpaletterange_inline)

###### final KeyThermalPaletteRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraThermalPalette>> KeyThermalPaletteRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalPaletteRange", new SingleValueConverter<>((Class)List.class,CameraThermalPaletteRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalPaletteRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraThermalPalette`> To get settable palette range of infrared lens. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalPalette](#key_camera_camerathermalpalette_inline)

###### final KeyThermalPalette

|  |
| --- |
| ``` static final DJIKeyInfo<CameraThermalPalette> KeyThermalPalette = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalPalette", new SingleValueConverter<>(CameraThermalPalette.class,CameraThermalPaletteMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalPalette") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `CameraThermalPalette` To set palette type. Settable palette range can be obtained through `KeyThermalPaletteRange`. Distinct colors are used to show temperature differences in the thermal image, which are related to grayscale intensity. The temperature range of the image is mapped to 256 colors and displayed in the 8-bit JPEG or MP4 format. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Isotherm

[KeyThermalIsothermEnabled](#key_camera_camerathermalisothermenabled_inline)

###### final KeyThermalIsothermEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyThermalIsothermEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalIsothermEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalIsothermEnabled") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean Enable or disable isotherms. Turn on the isotherm function to see objects in the specified temperature range more intuitively. The temperature range is set by two thresholds of high temperature and low temperature. When disabled, all 256 values (8-bits) are dedicated to the temperature histogram of the scene. When enabled, only 128 values (0-127) are mapped linearly to temperature. Then three bands 128-175, 176-223 and 224-255 can be mapped to the user defined temperatures to highlight them to the user. Using some of the false color palettes (like RainbowIso) results in a thermal image that is grey scale except for three specific bands highlighted by either reds, blues or greens. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalIsothermUnit](#key_camera_camerathermalisothermunit_inline)

###### final KeyThermalIsothermUnit

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalIsothermUnit> KeyThermalIsothermUnit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalIsothermUnit", new SingleValueConverter<>(ThermalIsothermUnit.class,CameraThermalIsothermUnitMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalIsothermUnit") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalIsothermUnit` Units for isotherms. Sets the unit of the Isotherm ranges to either Celsius or percent. Different units results in different value ranges for Isotherms. If the unit is percent, the settable value range is [0,100]. If the unit is Celsius, the settable upper value and lower value can refer to `KeyThermalGainModeTemperatureRange`. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalIsothermUpperValue](#key_camera_camerathermalisothermuppervalue_inline)

###### final KeyThermalIsothermUpperValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyThermalIsothermUpperValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalIsothermUpperValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalIsothermUpperValue") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer The upper threshold value for Isotherm. All temperature values above this value will use the colors 224-255 in the palette. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalIsothermLowerValue](#key_camera_camerathermalisothermlowervalue_inline)

###### final KeyThermalIsothermLowerValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyThermalIsothermLowerValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalIsothermLowerValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalIsothermLowerValue") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer The lower threshold value for Isotherm. Temperature values between the lower and medium isotherm thresholds will be displayed in 128-175 colors in the palette. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Display Mode

[KeyThermalDisplayMode](#key_camera_cameradisplaymode_inline)

###### final KeyThermalDisplayMode

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalDisplayMode> KeyThermalDisplayMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalDisplayMode", new SingleValueConverter<>(ThermalDisplayMode.class,CameraDisplayModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraDisplayMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalDisplayMode` To set display mode of infrared lens. The mode includes infrared display and split screen display. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

FFC Calibration

[KeyThermalFFCMode](#key_camera_camerathermalffcmode_inline)

###### final KeyThermalFFCMode

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalFFCMode> KeyThermalFFCMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalFFCMode", new SingleValueConverter<>(ThermalFFCMode.class,CameraThermalFFCModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalFFCMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalFFCMode` To set FFC calibration mode. FFC calibration is a function of infrared lens. Image quality will be improved after the calibration. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

[KeyThermalTriggerFFC](#key_camera_thermaltriggerffc_inline)

###### final KeyThermalTriggerFFC

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyThermalTriggerFFC = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalTriggerFFC", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Manually trigger the FFC calibration mode once. During the calibration process, the camera screen may pause for 1 second, and the camera will make a "click" sound. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Zoom Ratio

[KeyThermalDigitalZoomFactor](#key_camera_thermaldigitalzoomfactor_inline)

###### final KeyThermalDigitalZoomFactor

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalDigitalZoomFactor> KeyThermalDigitalZoomFactor = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalDigitalZoomFactor", new SingleValueConverter<>(ThermalDigitalZoomFactor.class,ThermalDigitalZoomFactorMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalDigitalZoomFactor` To set the infrared zoom ratio. And 1x, 2x, 4x and 8x zoom ratio is supported. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Super Resolution

[KeyThermalSuperResolution](#key_camera_thermalsuperresolution_inline)

###### final KeyThermalSuperResolution

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyThermalSuperResolution = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalSuperResolution", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean To set the infrared super resolution open and close. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Region of Interest (ROI)

[KeyThermalROI](#key_camera_camerathermalroi_inline)

###### final KeyThermalROI

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalROI> KeyThermalROI = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalROI", new SingleValueConverter<>(ThermalROI.class,CameraThermalROIMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalROI") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalROI` To set the region of interest (ROI). User can choose ROI based on their needs. When the region of interest is full screen, the color levels of the entire image will be distributed in the default configuration. For instance, if there is a large patch of sky (relatively low temperature) in your image, much of the color spectrum will be allocated to the lower range, meaning that other parts of the spectrum will have a lower contrast. In this case, you may select “Sky excluded (33%)” or “Sky excluded (50%)” to ignore areas of the sky so that most of the spectrum can be allocated to remaining areas, providing higher contrast and utility for analysis. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Scene

[KeyThermalScene](#key_camera_thermalscene_inline)

###### final KeyThermalScene

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalScene> KeyThermalScene = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalScene", new SingleValueConverter<>(ThermalScene.class,ThermalSceneMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalScene` To set thermal scene. It supports default and inspection scenarios, as well as custom scenarios. Users can set and save customized parameters according to their needs. The camera will adjust the camera parameters according to different scenes to optimize the image processing. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

DDE

[KeyThermalDDE](#key_camera_thermaldde_inline)

###### final KeyThermalDDE

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyThermalDDE = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalDDE", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set thermal Digital Detail Enhancement (DDE). The sharpness of images and outlines can be improved. Manual setting is only possible when the scene mode is `MANUAL`. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

Contrast

[KeyThermalContrast](#key_camera_thermalcontrast_inline)

###### final KeyThermalContrast

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyThermalContrast = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalContrast", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set thermal contrast. The ratio of screen black to white. The larger the ratio, the richer the color performance. Manual setting is only possible when the scene mode is Scene `MANUAL`. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0</code`

Brightness

[KeyThermalBrightness](#key_camera_thermalbrightness_inline)

###### final KeyThermalBrightness

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyThermalBrightness = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalBrightness", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer To set thermal brightness. Manual setting is only possible when the scene mode is `MANUAL`. **Notice： 1. To use this function, please call `KeyCamearaVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. subComponentType needs to pass in IR.** `Supported since MSDK 5.0`

#### Basic Setting

Parameter Reset

[KeyResetCameraSetting](#key_camera_resetcamerasetting_inline)

###### final KeyResetCameraSetting

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyResetCameraSetting = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"ResetCameraSetting", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To reset camera parameters. `Supported since MSDK 5.0`

Format Storage

[KeyFormatStorage](#key_camera_formatstorage_inline)

###### final KeyFormatStorage

|  |
| --- |
| ``` static final DJIActionKeyInfo<CameraStorageLocation,EmptyMsg> KeyFormatStorage = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"FormatStorage", new SingleValueConverter<>(CameraStorageLocation.class,CameraStorageLocationMsg.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraStorageLocation` To format storage. M30 and M30T camera only supports SD Card. `Supported since MSDK 5.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found