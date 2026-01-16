**Navigation:** [IKeyManager](IKeyManager.md) > [DJIKey<T>](DJIKey.md) > [CameraKey](Key_Camera_CameraKey.md)

---

# class CameraKey

|  |
| --- |
| ``` @Keep  class CameraKey extends DJICameraKey ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |
| *Inherits From:* | `DJICameraKey` |

##### Description:

`CameraKey`provides a set of methods to set and get the camera parameters including camera type, camera settings, etc. `Supported since MSDK 5.0.0`

##### Class Members:

#### Basic Information

Connected Status

[KeyConnection](#key_camera_connection_inline)

###### final KeyConnection

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyConnection = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"Connection", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean `true` means that camera is connected. `Supported since MSDK 5.0.0`

Camera Type

[KeyCameraType](#key_camera_msdkcameratype_inline)

###### final KeyCameraType

|  |
| --- |
| ``` static final DJIKeyInfo<CameraType> KeyCameraType = new KeyCameraType()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `CameraType` To get camera type. `Supported since MSDK 5.0.0`

Firmware version

[KeyFirmwareVersion](#key_camera_firmwareversion_inline)

###### final KeyFirmwareVersion

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyFirmwareVersion = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"FirmwareVersion", SingleValueConverter.StringConverter).canGet(true).canSet(false).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** String Get Firmware Version. `Supported since MSDK 5.0.0`

#### Camera Settings

Camera Mode

[KeyCameraModeRange](#key_camera_cameramoderange_inline)

###### final KeyCameraModeRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraMode>> KeyCameraModeRange = new KeyCameraModeRange()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraMode`> To get the mode of camera that can be set currently. `Supported since MSDK 5.0.0`

[KeyCameraMode](#key_camera_cameramode_inline)

###### final KeyCameraMode

|  |
| --- |
| ``` static final DJIKeyInfo<CameraMode> KeyCameraMode = new KeyCameraMode()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraMode` To set working mode of the camera, including shooting photo and recording video. The mode of the camera that can be set currently can get through `KeyCameraModeRange`. This mode takes effect for all lenses. **Notic: Panoramic photo mode can only be activated after the aircraft takes off.** `Supported since MSDK 5.0.0`

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

**Parameter:** Boolean `true` means camera is shooting. `Supported since MSDK 5.0.0`

[KeyStartShootPhoto](#key_camera_startshootphoto_inline)

###### final KeyStartShootPhoto

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartShootPhoto = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartShootPhoto", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start shooting photo. The camera must be in shooting photo mode. For infrared lenses, users can take photos while recording video. If the shooting photo mode is `PHOTO_INTERVAL`, you need to call `KeyStopShootPhoto` to stop the camera from shooting photo. Before using this method, you should check the SD Card status to make sure enough space. `Supported since MSDK 5.0.0`

[KeyStopShootPhoto](#key_camera_stopshootphoto_inline)

###### final KeyStopShootPhoto

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopShootPhoto = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopShootPhoto", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Stop shooting photo. When the camera shooting mode is `PHOTO_INTERVAL`, `PHOTO_SUPER_RESOLUTION` and `PHOTO_PANORAMA`, and `KeyStartShootPhoto`is called to start shooting, you can call this interface to stop shooting. If the camera shooting mode is set to be single shooting, the camera will stop shooting automatically after a single photo is shoot, there is no need to call this interface. `Supported since MSDK 5.0.0`

[KeyCaptureCameraStreamSettings](#key_camera_msdkcapturecamerastreamsettings_inline)

###### final KeyCaptureCameraStreamSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CameraStreamSettingsInfo> KeyCaptureCameraStreamSettings = new KeyCaptureCameraStreamSettings()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraStreamSettingsInfo` Multi-lens photo storage settings. All lenses of this camera take photos by default, and the photos are stored in the SD Card. You can call this interface to select the required lens for shooting photo and storage. Before calling this interface, you need to call `KeyCameraMode` to set the camera shooting mode to `PHOTO_NORMAL`. **Note: For DJI Mavic 3M, only the following two settings are supported:**

1. Store only RGB photos:[RGB\_CAMERA]
2. Store RGB and multispectral photos:[RGB\_CAMERA, NDVI\_CAMERA, MS\_G\_CAMERA, MS\_R\_CAMERA, MS\_RE\_CAMERA, MS\_NIR\_CAMERA]   
     
     
   `Supported since MSDK 5.0.0`

[KeyPhotoFileFormatRange](#key_camera_photostorageformatrange_inline)

###### final KeyPhotoFileFormatRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<PhotoFileFormat>> KeyPhotoFileFormatRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoFileFormatRange", new SingleValueConverter<>((Class)List.class,PhotoStorageFormatRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PhotoStorageFormatRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`PhotoFileFormat`> To get the camera's photo format range. `Supported since MSDK 5.0.0`

[KeyPhotoFileFormat](#key_camera_photostorageformat_inline)

###### final KeyPhotoFileFormat

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoFileFormat> KeyPhotoFileFormat = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoFileFormat", new SingleValueConverter<>(PhotoFileFormat.class,PhotoStorageFormatMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PhotoStorageFormat") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoFileFormat` To set and get the photo format supported by the camera, and can only be set when the `KeyCameraMode` is photo mode. `Supported since MSDK 5.0.0`

[KeyPhotoCSize](#key_camera_photosize_inline)

###### final KeyPhotoCSize

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoSize> KeyPhotoCSize = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoCSize", new SingleValueConverter<>(PhotoSize.class,PhotoSizeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PhotoSize") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoSize` To set and get the photo size supported by the camera. **Note: 1. It can only be set when `KeyCameraMode` is in camera mode. 2. M30, M30T, Mavic 3T can support settings: `SIZE_DEFAULT` and `SIZE_LARGE`. 3. The photo size of other types of cameras defaults to `SIZE_DEFAULT` and does not need to be set.** `Supported since MSDK 5.6.0`

[KeyPhotoIntervalShootSettings](#key_camera_photointervalshootsettings_inline)

###### final KeyPhotoIntervalShootSettings

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoIntervalShootSettings> KeyPhotoIntervalShootSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoIntervalShootSettings", new DJIValueConverter<>(PhotoIntervalShootSettings.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoIntervalShootSettings` Set and get the number of pictures and the interval time between pictures， and is used when `KeyCameraMode` is `PHOTO_INTERVAL`. `Supported since MSDK 5.0.0`

[KeyPhotoIntervalCountdown](#key_camera_photointervalcountdown_inline)

###### final KeyPhotoIntervalCountdown

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyPhotoIntervalCountdown = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoIntervalCountdown", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the countdown of interval photo shooting. When the interval shooting mode is set, after calling `KeyStartShootPhoto` to start shooting, the camera will count down the interval time between photos and will shoot one photo after the countdown reaches 0. `Supported since MSDK 5.0.0`

[KeySuperResolutionInfo](#key_camera_superresolutionstate_inline)

###### final KeySuperResolutionInfo

|  |
| --- |
| ``` static final DJIKeyInfo<SuperResolutionInfo> KeySuperResolutionInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SuperResolutionInfo", new DJIValueConverter<>(SuperResolutionInfo.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("SuperResolutionState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`SuperResolutionInfo` To get the status and information of ultra-clear matrix photo, and is used when `KeyCameraMode` is `PHOTO_SUPER_RESOLUTION`. `Supported since MSDK 5.0.0`

[KeySuperResolutionCaptureArea](#key_camera_superresolutioncapturearea_inline)

###### final KeySuperResolutionCaptureArea

|  |
| --- |
| ``` static final DJIKeyInfo<DoubleRect4Sides> KeySuperResolutionCaptureArea = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"SuperResolutionCaptureArea", new DJIValueConverter<>(DoubleRect4Sides.class)).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`DoubleRect4Sides` To set the area of ultra-clear matrix photo shooting, and is used when `KeyCameraMode` is `PHOTO_SUPER_RESOLUTION`. Area range: `getMaxArea`. `Supported since MSDK 5.0.0`

[KeyPhotoPanoramaMode](#key_camera_visionphotopanoramamode_inline)

###### final KeyPhotoPanoramaMode

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoPanoramaMode> KeyPhotoPanoramaMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoPanoramaMode", new SingleValueConverter<>(PhotoPanoramaMode.class,VisionPhotoPanoramaModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VisionPhotoPanoramaMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoPanoramaMode` To set the panorama photo shooting mode, and is used when `KeyCameraMode` is `PHOTO_PANORAMA`. `Supported since MSDK 5.0.0`

[KeyIsShootingPhotoPanorama](#key_camera_isshootingvisionpanoramaphoto_inline)

###### final KeyIsShootingPhotoPanorama

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIsShootingPhotoPanorama = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IsShootingPhotoPanorama", SingleValueConverter.BooleanConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IsShootingVisionPanoramaPhoto") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means panorama photo is being shot and is used when `KeyCameraMode` is `PHOTO_PANORAMA`. `Supported since MSDK 5.0.0`

[KeyPhotoPanoramaProgress](#key_camera_panoramaphotocaptureprogress_inline)

###### final KeyPhotoPanoramaProgress

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyPhotoPanoramaProgress = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"PhotoPanoramaProgress", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("PanoramaPhotoCaptureProgress") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer To get the percentage of panorama photo shooting, and is used when `KeyCameraMode` is `PHOTO_PANORAMA`. `Supported since MSDK 5.0.0`

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

**Parameter:** Boolean `true` means that camera is recording video. `Supported since MSDK 5.0.0`

[KeyStartRecord](#key_camera_startrecord_inline)

###### final KeyStartRecord

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartRecord = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartRecord", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start recording video. It is required that calling `KeyCameraMode` to set camera working mode to `VIDEO_NORMAL`. For the Infrared lens, the user can shooting photo when the video is recording. Before using this method, SD Card status should be checked to make sure there has enough space. `Supported since MSDK 5.0.0`

[KeyStopRecord](#key_camera_stoprecord_inline)

###### final KeyStopRecord

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopRecord = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopRecord", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Stop recording video. `Supported since MSDK 5.0.0`

[KeyRecordingTime](#key_camera_recordingtime_inline)

###### final KeyRecordingTime

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyRecordingTime = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"RecordingTime", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

The duration of the video currently recorded by the camera. Unit:second. `Supported since MSDK 5.0.0`

[KeyVideoRecordPlan](#key_camera_videorecordplan_inline)

###### final KeyVideoRecordPlan

|  |
| --- |
| ``` static final DJIKeyInfo<VideoRecordPlanInfo> KeyVideoRecordPlan = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoRecordPlan", new DJIValueConverter<>(VideoRecordPlanInfo.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoRecordPlanInfo` Turn on or off the camera pre-recording function. Pre-recording will automatically record the number of seconds you set before recording the video and add it to the video, which consumes device storage space and affects the writing life of the SD card. Please try to avoid pulling out the card during pre-recording. You can call `KeyVideoPreRecordDuration` to set the pre-recording duration. You can call `KeyVideoRecordingStatus` to obtain the pre-recording status. **Note: Only H30 series cameras support this feature.** `Supported since MSDK 5.9.0`

[KeyVideoPreRecordDuration](#key_camera_videoprerecordduration_inline)

###### final KeyVideoPreRecordDuration

|  |
| --- |
| ``` static final DJIKeyInfo<VideoPreRecordDurationInfo> KeyVideoPreRecordDuration = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoPreRecordDuration", new DJIValueConverter<>(VideoPreRecordDurationInfo.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoPreRecordDurationInfo` Set the camera pre-recording duration. **Note: Only H30 series cameras support this feature.** `Supported since MSDK 5.9.0`

[KeyVideoRecordingStatus](#key_camera_isrecordinginfo_inline)

###### final KeyVideoRecordingStatus

|  |
| --- |
| ``` static final DJIKeyInfo<VideoRecordingStatus> KeyVideoRecordingStatus = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoRecordingStatus", new DJIValueConverter<>(VideoRecordingStatus.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IsRecordingInfo") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoRecordingStatus` Get camera pre-recording status. When the status is `STARTING` and `STOPPING`, the camera cannot take pictures and record videos.。 **Note: Only H30 series cameras support this feature.** `Supported since MSDK 5.9.0`

[KeyRecordCameraStreamSettings](#key_camera_msdkrecordcamerastreamsettings_inline)

###### final KeyRecordCameraStreamSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CameraStreamSettingsInfo> KeyRecordCameraStreamSettings = new KeyRecordCameraStreamSettings()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraStreamSettingsInfo` Multi-lens recording storage setting. By default, all lenses of this camera can record video and the video will store in SD Card. You can call this interface to choose the required lens to record video and store. Before calling this interface, `KeyCameraMode` should be called to set camera working mode to `VIDEO_NORMAL`. **Note: For DJI Mavic 3M, only the following two settings are supported: 1. Store only RGB video:[RGB\_CAMERA] 2. Store RGB and NDVI video:[RGB\_CAMERA, NDVI\_CAMERA]** `Supported since MSDK 5.0.0`

[KeyVideoFileFormatRange](#key_camera_videostorageformatrange_inline)

###### final KeyVideoFileFormatRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<VideoFileFormat>> KeyVideoFileFormatRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoFileFormatRange", new SingleValueConverter<>((Class)List.class,VideoStorageFormatRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VideoStorageFormatRange") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`VideoFileFormat`> To get recording format range of the camera. `Supported since MSDK 5.0.0`

[KeyVideoFileFormat](#key_camera_videostorageformat_inline)

###### final KeyVideoFileFormat

|  |
| --- |
| ``` static final DJIKeyInfo<VideoFileFormat> KeyVideoFileFormat = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoFileFormat", new SingleValueConverter<>(VideoFileFormat.class,VideoStorageFormatMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VideoStorageFormat") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoFileFormat` To set and get video format supported by the camera, and can only be set when the `KeyCameraMode` is video mode. `Supported since MSDK 5.0.0`

[KeyVideoBitrateMode](#key_camera_videoencryptstrategy_inline)

###### final KeyVideoBitrateMode

|  |
| --- |
| ``` static final DJIKeyInfo<VideoBitrateMode> KeyVideoBitrateMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoBitrateMode", new SingleValueConverter<>(VideoBitrateMode.class,VideoEncryptStrategyMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VideoEncryptStrategy") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoBitrateMode` Set and get the video bitrate mode supported by the camera. It can only be set when `KeyCameraMode` is in recording mode. **Note: Only H30 series cameras support this feature.** `Supported since MSDK 5.9.0`

[KeyVideoMimeType](#key_camera_videofilecompressionstandard_inline)

###### final KeyVideoMimeType

|  |
| --- |
| ``` static final DJIKeyInfo<VideoMimeType> KeyVideoMimeType = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoMimeType", new SingleValueConverter<>(VideoMimeType.class,VideoFileCompressionStandardMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("VideoFileCompressionStandard") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoMimeType` Set and get the video mime type supported by the camera. It can only be set when `KeyCameraMode` is in recording mode. **Note: Only H30 series cameras support this feature.** `Supported since MSDK 5.9.0`

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

**Parameter:**`GeneratedMediaFileInfo` After the photo shooting and video recording are started, the camera will generate new photo or video. This interface can be used to get media file information generated. `Supported since MSDK 5.0.0`

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

**Parameter:**`CustomExpandNameSettings` To set and get the extension name of the customized file folder. After calling this interface, the newly generated media file (video or photo) will be stored in a new file folder. Default file folder name plus the extension name you set will be the name of the new file folder. For example, the default file folder name is “DJI\_202001012359\_01”, and the customized extension name of the file folder is “Mission1”, therefore the new file folder name will be “DJI\_202001012359\_01\_Mission1”. In one flight, you can set multiple extension directory names to create multiple customized folders. **Note: 1. Do not set the extension name of the customized file folder during video recording. 2. Do not use pure numbers and special characters as the extension name, otherwise the multimedia file will fail to download. It is recommended to use a combination of numbers and letters as the extension name. 3. The length of the multimedia file name [original file name + extension name] is less than 128 bytes.** `Supported since MSDK 5.0.0`

[KeyCustomExpandFileNameSettings](#key_camera_customexpandfilenamesettings_inline)

###### final KeyCustomExpandFileNameSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CustomExpandNameSettings> KeyCustomExpandFileNameSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CustomExpandFileNameSettings", new DJIValueConverter<>(CustomExpandNameSettings.class)).canGet(true).canSet(true).canListen(false).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CustomExpandNameSettings` To set and get extension name of the customized file. After calling this interface, the default file name plus the extension name you set will be the name of the nest media file(video or photo). For example, the default name photo name is “DJI\_2020012091415\_999\_WIDE.JPG”, and the extension name of the customized file you set is “Waypoint1”, therefore the name of the new photo will be “DJI\_2020012091415\_999\_WIDE\_Waypoint1.JPG”. This operation only takes effect once. If it is required that all media files in one flight have the same extension name, you need to set the same extension name before each photo shooting. **Note: 1. Do not set the extension name of the customized file during video recording. 2. Do not use pure numbers and special characters as the extension name, otherwise the multimedia file will fail to download. It is recommended to use a combination of numbers and letters as the extension name. 3. The length of the multimedia file name [original file name + extension name] is less than 128 bytes.** `Supported since MSDK 5.0.0`

Storage

[KeyCameraStorageInfos](#key_camera_camerastorageinfos_inline)

###### final KeyCameraStorageInfos

|  |
| --- |
| ``` static final DJIKeyInfo<CameraStorageInfos> KeyCameraStorageInfos = new KeyCameraStorageInfo()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraStorageInfos` To get the storage information of the camera SD Card and onboard memory. The onboard memory of M30 and M30T cameras are for internal use only. `Supported since MSDK 5.0.0`

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

**Parameter:**Boolean `true` means that video subtitles are opened. The location of aircraft, flight time and camera parameters will be stored in video subtitles srt file in SD Card. **Note: If you want to use this function, please use `KeyCameraMode` to set the camera mode to `VIDEO_NORMAL`.** `Supported since MSDK 5.0.0`

Watermark

[KeyCameraWatermarkSettings](#key_camera_camerawatermarksettings_inline)

###### final KeyCameraWatermarkSettings

|  |
| --- |
| ``` static final DJIKeyInfo<CameraWatermarkSettings> KeyCameraWatermarkSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraWatermarkSettings", new DJIValueConverter<>(CameraWatermarkSettings.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraWatermarkSettings` Watermark setting of the camera. It can be set whether to generate watermark in photo or video or not. You can set the content that needs to be displayed in the watermark through `KeyWatermarkDisplayContentSettings`. Set custom watermark text via `KeyWatermarkUserCustomInfo`. `Supported since MSDK 5.0.0`

[KeyWatermarkDisplayContentSettings](#key_camera_watermarkdisplaycontentsettings_inline)

###### final KeyWatermarkDisplayContentSettings

|  |
| --- |
| ``` static final DJIKeyInfo<WatermarkDisplayContentSettings> KeyWatermarkDisplayContentSettings = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WatermarkDisplayContentSettings", new DJIValueConverter<>(WatermarkDisplayContentSettings.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`WatermarkDisplayContentSettings` Content setting of the camera watermark. Content of watermark that needs to display in photo and video can be set. You can set customized content of watermark through `KeyWatermarkUserCustomInfo`. `Supported since MSDK 5.0.0`

[KeyWatermarkUserCustomInfo](#key_camera_watermarkusercustominfo_inline)

###### final KeyWatermarkUserCustomInfo

|  |
| --- |
| ``` static final DJIKeyInfo<String> KeyWatermarkUserCustomInfo = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WatermarkUserCustomInfo", SingleValueConverter.StringConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**String To set and get the customized copywriting in watermark. You can set whether to display customized copywriting through `KeyWatermarkDisplayContentSettings`. `Supported since MSDK 5.0.0`

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

**Parameter:** Boolean `true` means that the gimbal will be locked when shooting photo. If this function is opened, flight movement influence on image effect can be avoided. `Supported since MSDK 5.0.0`

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

**Parameter:** `AutoTurnOffLEDMode` To set LED automatically turn off when shooting photo and recording video. The LEDs include the front arm LED, rear arm LED and navigation LED. `Supported since MSDK 5.0.0`

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

**Parameter:** `LaserWorkMode` To set laser working mode. You can choose to enhance mode or open laser according to requirement. The laser module will revert to enhance mode after the camera restarts. `Supported since MSDK 5.0.0`

[KeyLaserMeasureEnabled](#key_camera_lasermeasureenable_inline)

###### final KeyLaserMeasureEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLaserMeasureEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LaserMeasureEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("LaserMeasureEnable") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Boolean To open or close the laser module. When `KeyLaserWorkMode`is set to be `OPEN_ON_DEMAND`, you can open the laser module through this interface. `Supported since MSDK 5.0.0`

[KeyLaserMeasureInformation](#key_camera_lasermeasureinformation_inline)

###### final KeyLaserMeasureInformation

|  |
| --- |
| ``` static final DJIKeyInfo<LaserMeasureInformation> KeyLaserMeasureInformation = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LaserMeasureInformation", new DJIValueConverter<>(LaserMeasureInformation.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`LaserMeasureInformation` Gets the information of the laser sensor, including the location information of the target point, the distance between the target point and the laser sensor, the position of the target point on the camera screen and the status of the laser sensor. The laser sensor must be at least 3m away from the target point. `Supported since MSDK 5.1.0`

Aim To

[KeyTapZoomAtTarget](#key_camera_tapzoomattarget_inline)

###### final KeyTapZoomAtTarget

|  |
| --- |
| ``` static final DJIActionKeyInfo<ZoomTargetPointInfo,EmptyMsg> KeyTapZoomAtTarget = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"TapZoomAtTarget", new DJIValueConverter<>(ZoomTargetPointInfo.class),EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`ZoomTargetPointInfo` Tap zoom at target function, double-click the target point on the screen, the gimbal will automatically rotate to place the target point in the center of the screen. You only need to pass in the target coordinates of the screen, other parameters are reserved fields and can be ignored. `Supported since MSDK 5.9.0`

Infinity Calibration

[KeyInfinityCalibrationState](#key_camera_mfdemarcatestate_inline)

###### final KeyInfinityCalibrationState

|  |
| --- |
| ``` static final DJIKeyInfo<InfinityCalibrationState> KeyInfinityCalibrationState = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"InfinityCalibrationState", new SingleValueConverter<>(InfinityCalibrationState.class,MFDemarcateStateMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MFDemarcateState") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `InfinityCalibrationState` Get the status of infinity calibration. If the state is `NOT_CALIBRATE`, please call `KeyStartInfinityCalibration` for infinity calibration. **Note: Only Zenmuse P1 supports infinity calibration.** `Supported since MSDK 5.9.0`

[KeyStartInfinityCalibration](#key_camera_startmfdemarcate_inline)

###### final KeyStartInfinityCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStartInfinityCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StartInfinityCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("StartMFDemarcate") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Start the camera infinity calibration. If you encounter the following situations, please call this interface for infinity calibration.

- Zemmuse P1 supports the replacement of a variety of fixed-focus lenses, so it is recommended that you recalibrate after changing the lens.
- Please listen `KeyInfinityCalibrationState`. If the state is `NOT_CALIBRATE`, please recalibrate.
- Please listen to device health information through `addDJIDeviceHealthInfoChangeListener`. If you get the following error code, please recalibrate.
  
      1. hms\_0x1C000D02: The camera calibration file failed to load, please perform infinity calibration.
  
      2. hms\_0x1C000D03: Infinity calibration data has expired, please perform infinity calibration again.
  
      3. hms\_0x1C000D04: Infinity calibration data has expired, please perform infinity calibration again.

The following are the infinity calibration steps:

- Take off and ascend to 102 meters (335 feet) or higher to begin calibration.
- Call `KeyStartInfinityCalibration` to start calibration, and the calibration state will change to `WAITING_FOR_CALIBRATE`.
- Set the gimbal angle to -90 degrees (perpendicular to the ground). Call `KeyCameraFocusTarget` to set the object with multiple distinct visual patterns at the center of the camera view as focus, and then the calibration state will be changed to `CALIBRATING`.
- After the calibration is completed, the calibration state will change to `CALIBRATED`. You can use `KeyInfinityCalibrationResult` to listen the calibration results.
  
  
**Note:  
 Only Zenmuse P1 supports infinity calibration.**   
  
`Supported since MSDK 5.9.0`

[KeyStopInfinityCalibration](#key_camera_stopmfdemarcate_inline)

###### final KeyStopInfinityCalibration

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyStopInfinityCalibration = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"StopInfinityCalibration", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false).setInnerIdentifier("StopMFDemarcate") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Stop the camera infinity calibration. **Note: Only Zenmuse P1 supports infinity calibration.** `Supported since MSDK 5.9.0`

[KeyInfinityCalibrationResult](#key_camera_mfdemarcateresult_inline)

###### final KeyInfinityCalibrationResult

|  |
| --- |
| ``` static final DJIKeyInfo<InfinityCalibrationResult> KeyInfinityCalibrationResult = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"InfinityCalibrationResult", new SingleValueConverter<>(InfinityCalibrationResult.class,MFDemarcateResultMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("MFDemarcateResult") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `InfinityCalibrationResult` Get the result of infinity calibration. **Note: Only Zenmuse P1 supports infinity calibration.** `Supported since MSDK 5.9.0`

[KeyLensCalibratedInfinityValue](#key_camera_lenscalibratedinfinityvalue_inline)

###### final KeyLensCalibratedInfinityValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyLensCalibratedInfinityValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LensCalibratedInfinityValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer Calibrated infinity focus. Make sure you have calibrated the lens' infinity focusing with `KeyStartInfinityCalibration`. When in use, the lens uses infinity focus as calibrated. To help ensure focusing accuracy, recalibrate the infinity focus when there is a large temperature difference between the mission environment and the calibration environment (>15 degrees Celsius). **Note: Only Zenmuse P1 supports infinity calibration.** `Supported since MSDK 5.9.0`

Anti Flicker

[KeyAntiFlicker](#key_camera_antiflicker_inline)

###### final KeyAntiFlicker

|  |
| --- |
| ``` static final DJIKeyInfo<CameraAntiFlicker> KeyAntiFlicker = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"AntiFlicker", new SingleValueConverter<>(CameraAntiFlicker.class,CameraAntiFlickerMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraAntiFlicker` Sets anti flicker. Typically used in consumer aircraft. `Supported since MSDK 5.3.0`

White Balance

[KeyCameraWhiteBalanceRange](#key_camera_camerawhitebalancerange_inline)

###### final KeyCameraWhiteBalanceRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraWhiteBalanceMode>> KeyCameraWhiteBalanceRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraWhiteBalanceRange", new SingleValueConverter<>((Class)List.class,WhiteBalanceRangeMsg.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraWhiteBalanceMode`> To get the white balance range of camera. `Supported since MSDK 5.3.0`

[KeyWhiteBalance](#key_camera_whitebalance_inline)

###### final KeyWhiteBalance

|  |
| --- |
| ``` static final DJIKeyInfo<CameraWhiteBalanceInfo> KeyWhiteBalance = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"WhiteBalance", new DJIValueConverter<>(CameraWhiteBalanceInfo.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraWhiteBalanceInfo` Sets white balance. The settable white balance can be obtained through `KeyCameraWhiteBalanceRange`. `Supported since MSDK 5.3.0`

Night Scene Mode

[KeyCameraNightSceneMode](#key_camera_cameranightscenemode_inline)

###### final KeyCameraNightSceneMode

|  |
| --- |
| ``` static final DJIKeyInfo<CameraNightSceneMode> KeyCameraNightSceneMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraNightSceneMode", new SingleValueConverter<>(CameraNightSceneMode.class,CameraNightSceneModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraNightSceneMode` Night scene mode settings. Turning on night scene mode can improve the camera's shooting effect in low-density environments. **Note: 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA` or `WIDE_CAMERA`.**

2. When using `KeyTools` to create a `DJIKey` instance,
   Set `CameraLensType` to `CAMERA_LENS_ZOOM` or `CAMERA_LENS_WIDE`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.
3. Taking photos and videos in night scene mode only supports 1080P.
     
     
   `Supported since MSDK 5.9.0`

Denoise Level

[KeyCameraDenoiseLevel](#key_camera_cameradenoiselevel_inline)

###### final KeyCameraDenoiseLevel

|  |
| --- |
| ``` static final DJIKeyInfo<CameraDenoiseLevel> KeyCameraDenoiseLevel = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraDenoiseLevel", new SingleValueConverter<>(CameraDenoiseLevel.class,CameraDenoiseLevelMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraDenoiseLevel` Denoise level settings, the higher the denoise level, the lower the frame rate. Take the H30 series camera as an example: the default image transmission and video frame rate is 25fps. When the denoise level is low, the frame rate is 25fps. When the denoise level is medium, the frame rate is 15fps. When the denoise level is high, the frame rate is 5fps. **Note: 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA` or `WIDE_CAMERA`.**

2. When using `KeyTools` to create a `DJIKey` instance,
   Set `CameraLensType` to `CAMERA_LENS_ZOOM` or `CAMERA_LENS_WIDE`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.
3. Please call `KeyCameraNightSceneMode` to set the night scene mode to
   `ENABLE`.
4. Only H20N and H30 series cameras support the enhanced noise reduction level function.
     
     
   `Supported since MSDK 5.9.0`

Laser Fill Light

[KeyLaserFillLightEnabled](#key_camera_enablelaserfilllight_inline)

###### final KeyLaserFillLightEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLaserFillLightEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LaserFillLightEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("EnableLaserFillLight") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean Laser fill light settings. When the laser fill light is turned on, `KeyIRCutEnabled` will be turned on at the same time. **Note: 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA` or `WIDE_CAMERA`.**

2. When using `KeyTools` to create a `DJIKey` instance,
   Set `CameraLensType` to `CAMERA_LENS_ZOOM` or `CAMERA_LENS_WIDE`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.
3. Please call `KeyCameraNightSceneMode` to set the night scene mode to
   `ENABLE`.
4. Only H20N and H30 series cameras support the laser fill light function.
     
     
   `Supported since MSDK 5.9.0`

IR Cut

[KeyIRCutEnabled](#key_camera_ircutenable_inline)

###### final KeyIRCutEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyIRCutEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"IRCutEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("IRCutEnable") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean `true` means to turn on the IR Cut function. When IR Cut is turned on, the image transmission of the zoom lens will become black and white. **Note: 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA` or `WIDE_CAMERA`.**

2. When using `KeyTools` to create a `DJIKey` instance,
   Set `CameraLensType` to `CAMERA_LENS_ZOOM` or `CAMERA_LENS_WIDE`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.
3. Please call `KeyCameraNightSceneMode` to set the night scene mode to
   `ENABLE`.
     
     
   `Supported since MSDK 5.0.0`

Dehaze Mode

[KeyDehazeMode](#key_camera_dehazemode_inline)

###### final KeyDehazeMode

|  |
| --- |
| ``` static final DJIKeyInfo<DehazeMode> KeyDehazeMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DehazeMode", new SingleValueConverter<>(DehazeMode.class,DehazeModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`DehazeMode` Dehaze mode settings. Enable the dehaze mode to improve screen transparency in hazy weather. Only H30 series cameras support this feature. **Note: 1. To use this function, please call `KeyCameraVideoStreamSource` and set the video source to `ZOOM_CAMERA`.**

2. When using `KeyTools` to create a `DJIKey` instance,
   Set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.
3. This function can be used only when the night scene mode `KeyCameraNightSceneMode` is disabled.
     
     
   `Supported since MSDK 5.9.0`

Dehaze Level

[KeyDehazeLevel](#key_camera_dehazelevel_inline)

###### final KeyDehazeLevel

|  |
| --- |
| ``` static final DJIKeyInfo<DehazeLevel> KeyDehazeLevel = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"DehazeLevel", new SingleValueConverter<>(DehazeLevel.class,DehazeLevelMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`DehazeLevel` Dehaze level Settings. The dehaze level can only be set when `KeyDehazeMode` is set to `ENABLE`. Only H30 series cameras support this feature. **Note: 1. To use this function, please call `KeyCameraVideoStreamSource` and set the video source to `ZOOM_CAMERA`.**

2. When using `KeyTools` to create a `DJIKey` instance,
   Set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.
     
     
   `Supported since MSDK 5.9.0`

Lens Clean Fog

[KeyCleanFog](#key_camera_cleanfog_inline)

###### final KeyCleanFog

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyCleanFog = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"CleanFog", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

To perform once lens defogging operation, Lens clean fog requires continuous heating for 5 seconds. `Supported since MSDK 5.0.0`

#### Lens Settings

Video Source

[KeyCameraVideoStreamSourceRange](#key_camera_cameravideostreamsourcerange_inline)

###### final KeyCameraVideoStreamSourceRange

|  |
| --- |
| ``` static final DJIKeyInfo<List<CameraVideoStreamSourceType>> KeyCameraVideoStreamSourceRange = new KeyCameraVideoStreamSourceRange()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`CameraVideoStreamSourceType`> To get the video source range of camera. `Supported since MSDK 5.0.0`

[KeyCameraVideoStreamSource](#key_camera_cameravideostreamsource_inline)

###### final KeyCameraVideoStreamSource

|  |
| --- |
| ``` static final DJIKeyInfo<CameraVideoStreamSourceType> KeyCameraVideoStreamSource = new KeyCameraVideoStreamSource()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraVideoStreamSourceType` To set camera video source. For camera with multiple lenses, video have different lens sources. After setting the camera video source, the current live video stream will be switched to the set lens video stream. You can get the list of lens types supported by the current camera through `KeyCameraVideoStreamSourceRange`. For single lens cameras, `DEFAULT_CAMERA` is used by default. **Note: When `CameraMode` is `PHOTO_SUPER_RESOLUTION`, the camera video source cannot be set to `INFRARED_CAMERA`.** `Supported since MSDK 5.0.0`

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

**Parameter:**list<`CameraExposureMode`> To get the exposure mode range of selected lens. `Supported since MSDK 5.0.0`

[KeyExposureMode](#key_camera_exposuremode_inline)

###### final KeyExposureMode

|  |
| --- |
| ``` static final DJIKeyInfo<CameraExposureMode> KeyExposureMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ExposureMode", new SingleValueConverter<>(CameraExposureMode.class,CameraExposureModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraExposureMode` To set the exposure mode of the selected lens. The exposure mode decides whether the aperture, shutter speed and ISO can be set automatically or manually. The settable exposure mode can be obtained through `KeyExposureModeRange`. When `CameraVideoStreamSourceType` is `INFRARED_CAMERA`, exposure mode can not be set. **Note: 1. Exposure mode settings can only be performed on the lens of the current liveview stream source, otherwise the settings will be unsuccessful. 2. You could obtain the liveview camera source through `KeyCameraVideoStreamSource`. Then set the `CameraLensType` with the current liveview camera source when the `DJIKey` instance was created using `KeyTools`. 3. In the super resolution photo mode, the exposure mode cannot be set.** `Supported since MSDK 5.0.0`

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

**Parameter:**list<`CameraExposureCompensation`> To get the exposure compensation range of camera lens. `Supported since MSDK 5.0.0`

[KeyExposureCompensation](#key_camera_exposurecompensation_inline)

###### final KeyExposureCompensation

|  |
| --- |
| ``` static final DJIKeyInfo<CameraExposureCompensation> KeyExposureCompensation = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ExposureCompensation", new SingleValueConverter<>(CameraExposureCompensation.class,CameraExposureCompensationMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraExposureCompensation` To set exposure compensation value of camera lens. Settable exposure compensation value can be obtained through `KeyExposureCompensationRange`. To use this function, the `CameraExposureMode` of the current liveview stream source must be set to `PROGRAM`. **Note: 1. Exposure mode settings can only be performed on the lens of the current liveview stream source, otherwise the settings will be unsuccessful. 2. You could obtain the liveview camera source through `KeyCameraVideoStreamSource`. 3.Then set the `CameraLensType` with the current liveview camera source when the `DJIKey` instance was created using `KeyTools`.** `Supported since MSDK 5.0.0`

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

**Parameter:**Boolean The automatic exposure lock of camera lens. To use this function, the `CameraExposureMode` of the current liveview stream source must be set to `PROGRAM`. `Supported since MSDK 5.0.0`

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

**Parameter:**`CameraMeteringMode` To set metering mode of camera lens. If you want to use center metering or average metering function, you only need to set `CENTER` or `AVERAGE` once, the lens will operate metering once. If you want to use spot or area metering function, `KeySpotMeteringPoint` or `KeyRegionMeteringArea` should be called to set meiering parameters, and the lens will perform spot or area metering once. **Caution: If you want to use metering function, please use `KeyExposureMode` to set the exposure mode to `PROGRAM`, `SHUTTER_PRIORITY` or `APERTURE_PRIORITY`, and call `KeyAELockEnabled` to set AE Lock to `false`.** `Supported since MSDK 5.2.0`

[KeySpotMeteringPoint](#key_camera_spotmeteringpoint_inline)

###### final KeySpotMeteringPoint

|  |
| --- |
| ``` static final DJIKeyInfo<DoublePoint2D> KeySpotMeteringPoint = new KeySpotMeteringPoint()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`DoublePoint2D` Spot metering of the camera lens. (x,y) of `DoublePoint2D` is the center point coordinate of normalized (0-1). `Supported since MSDK 5.2.0`

[KeyRegionMeteringArea](#key_camera_regionmeteringarea_inline)

###### final KeyRegionMeteringArea

|  |
| --- |
| ``` static final DJIKeyInfo<DoubleRect> KeyRegionMeteringArea = new KeyRegionMeteringArea()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`DoubleRect` Area metering of the camera lens. (x,y) is the upper left coordinate, and is the coordinate position of normalized (0-1). Width and height are the area size of normalized (0-1) and width and height can not be 0. `Supported since MSDK 5.2.0`

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

**Parameter:**list<`CameraISO`> To get ISO range of camera lens. **Note: If you want to use IOS function, please call `KeyExposureMode` set the exposure mode to `MANUAL`.** `Supported since MSDK 5.0.0`

[KeyISO](#key_camera_iso_inline)

###### final KeyISO

|  |
| --- |
| ``` static final DJIKeyInfo<CameraISO> KeyISO = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ISO", new SingleValueConverter<>(CameraISO.class,CameraISOMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraISO` To set and get the ISO value of the camera lens. The ISO range can be obtained through `KeyISORange`. **Note: 1. Exposure mode settings can only be performed on the lens of the current liveview stream source, otherwise the settings will be unsuccessful. 2. You could obtain the liveview camera source through `KeyCameraVideoStreamSource`. 3.Then set the `CameraLensType` with the current liveview camera source when the `DJIKey` instance was created using `KeyTools`. 4.Call `KeyExposureMode` set the exposure mode to `MANUAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:**list<`CameraShutterSpeed`> To get shutter speed range of camera lens. **Note: If you want to use IOS function, please call `KeyExposureMode` set the exposure mode to `MANUAL`.** `Supported since MSDK 5.0.0`

[KeyShutterSpeed](#key_camera_shutterspeed_inline)

###### final KeyShutterSpeed

|  |
| --- |
| ``` static final DJIKeyInfo<CameraShutterSpeed> KeyShutterSpeed = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ShutterSpeed", new SingleValueConverter<>(CameraShutterSpeed.class,CameraShutterSpeedMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`CameraShutterSpeed` To set shutter speed value of camera lens. Settable shutter speed can be obtained through `KeyShutterSpeedRange`. **Note: 1. Exposure mode settings can only be performed on the lens of the current liveview stream source, otherwise the settings will be unsuccessful. 2. You could obtain the liveview camera source through `KeyCameraVideoStreamSource`. 3.Then set the `CameraLensType` with the current liveview camera source when the `DJIKey` instance was created using `KeyTools`. 4.Call `KeyExposureMode` set the exposure mode to `MANUAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:**list<`PhotoRatio`> To get photo ratio of camera lens. **Note: 1. Photo ratio settings can only be performed on the lens of the current liveview stream source, otherwise the settings will be unsuccessful. 2. You could obtain the liveview camera source through `KeyCameraVideoStreamSource`. 3.Then set the `CameraLensType` with the current liveview camera source when the `DJIKey` instance was created using `KeyTools`. 4.You need to call `KeyCameraMode` to set the camera shooting mode to `PHOTO_NORMAL`** `Supported since MSDK 5.0.0`

[KeyPhotoRatio](#key_camera_msdkphotoratio_inline)

###### final KeyPhotoRatio

|  |
| --- |
| ``` static final DJIKeyInfo<PhotoRatio> KeyPhotoRatio = new KeyPhotoRatio()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`PhotoRatio` To set photo ratio of the camera lens. The settable photo ratio can be obtained through `KeyPhotoRatioRange`. **Note: 1. Photo ratio settings can only be performed on the lens of the current liveview stream source, otherwise the settings will be unsuccessful. 2. You could obtain the liveview camera source through `KeyCameraVideoStreamSource`. 3.Then set the `CameraLensType` with the current liveview camera source when the `DJIKey` instance was created using `KeyTools`. 4.You need to call `KeyCameraMode` to set the camera shooting mode to `PHOTO_NORMAL`** `Supported since MSDK 5.0.0`

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

**Parameter:**list<`VideoResolutionFrameRate`> To get the video resolution and frame rate range of camera lens. `Supported since MSDK 5.0.0`

[KeyVideoResolutionFrameRate](#key_camera_videoresolutionframerate_inline)

###### final KeyVideoResolutionFrameRate

|  |
| --- |
| ``` static final DJIKeyInfo<VideoResolutionFrameRate> KeyVideoResolutionFrameRate = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"VideoResolutionFrameRate", new DJIValueConverter<>(VideoResolutionFrameRate.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`VideoResolutionFrameRate` To set the video resolution and frame rate range of the camera lens. Settable video resolution and frame rate can be obtained through `KeyVideoResolutionFrameRateRange`. **Note: If you want to use this function, please use `KeyCameraMode` to set the camera mode to `VIDEO_NORMAL`.** `Supported since MSDK 5.0.0`

Zoom Ratios

[KeyCameraZoomRatiosRange](#key_camera_camerazoomratiosrange_inline)

###### final KeyCameraZoomRatiosRange

|  |
| --- |
| ``` static final DJIKeyInfo<ZoomRatiosRange> KeyCameraZoomRatiosRange = new KeyCameraZoomRatiosRange()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**list<`ZoomRatiosRange`> To get the zoom ratios range of camera lens. `Supported since MSDK 5.3.0`

[KeyCameraZoomRatios](#key_camera_camerazoomratios_inline)

###### final KeyCameraZoomRatios

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyCameraZoomRatios = new KeyCameraZoomRatios()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Double Set the zoom ratios of the camera lens. The recommended minimum accuracy is 0.1. Different lenses have different camera ratios ranges, please call `KeyCameraZoomRatiosRange` to get the zoom ratios supported by the lens. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is an M3M aircraft, set it to `CAMERA_LENS_RGB`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.** `Supported since MSDK 5.0.0`

[KeyCameraZoomFocalLength](#key_camera_camerazoomfocallength_inline)

###### final KeyCameraZoomFocalLength

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraZoomFocalLength = new KeyCameraZoomFocalLength()            .canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Interger Gets the focal length of the zoom lens, unit: mm. Please use `KeyCameraZoomRatios` to set the zoom ratios. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is an M3M aircraft, set it to `CAMERA_LENS_RGB`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.** `Supported since MSDK 5.0.0`

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

**Parameter:**`CameraFocusMode` To set and get focus mode of zoom lens. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is an M3M aircraft, set it to `CAMERA_LENS_RGB`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`. 3. Zenmuse P1, Mini 3 and Mini 3 Pro only support settings: `MANUAL` and `AF`.** `Supported since MSDK 5.0.0`

[KeyCameraFocusRingMinValue](#key_camera_camerafocusringminvalue_inline)

###### final KeyCameraFocusRingMinValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraFocusRingMinValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusRingMinValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer The minimum settable value that the camera manually focusing. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is an M3M aircraft, set it to `CAMERA_LENS_RGB`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.** `Supported since MSDK 5.0.0`

[KeyCameraFocusRingMaxValue](#key_camera_camerafocusringmaxvalue_inline)

###### final KeyCameraFocusRingMaxValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraFocusRingMaxValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusRingMaxValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer The maximum settable value that the camera manually focusing. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is an M3M aircraft, set it to `CAMERA_LENS_RGB`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.** `Supported since MSDK 5.0.0`

[KeyCameraFocusRingValue](#key_camera_camerafocusringvalue_inline)

###### final KeyCameraFocusRingValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyCameraFocusRingValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusRingValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Integer The manually focusing value of the camera. The maximum and minimum settable value that the camera manually focusing can be obtained through `KeyCameraFocusRingMinValue` and `KeyCameraFocusRingMaxValue`. In `MANUAL` mode, camera will focus once with the center point of the screen as the focus target after setting the focus value. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is an M3M aircraft, set it to `CAMERA_LENS_RGB`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.** `Supported since MSDK 5.0.0`

[KeyCameraFocusTarget](#key_camera_camerafocustarget_inline)

###### final KeyCameraFocusTarget

|  |
| --- |
| ``` static final DJIKeyInfo<DoublePoint2D> KeyCameraFocusTarget = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"CameraFocusTarget", new DJIValueConverter<>(DoublePoint2D.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`DoublePoint2D` The focus target of camera automaic focus. [0,0] means the upper left corner of camera screen. [1,1] means the lower right corner of camera screen. In `AF` mode, camera will automatically focus on the focus target once after setting focus target. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `ZOOM_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_ZOOM`. If it is an M3M aircraft, set it to `CAMERA_LENS_RGB`. If it is a DJI single-lens camera or PSDK single-lens camera, set it to `CAMERA_LENS_DEFAULT`.** `Supported since MSDK 5.0.0`

#### Infrared Function

Temperature Measurement

[KeyThermalTemperatureMeasureMode](#key_camera_camerathermalmeasurementmode_inline)

###### final KeyThermalTemperatureMeasureMode

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalTemperatureMeasureMode> KeyThermalTemperatureMeasureMode = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalTemperatureMeasureMode", new SingleValueConverter<>(ThermalTemperatureMeasureMode.class,CameraThermalMeasurementModeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalMeasurementMode") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**`ThermalTemperatureMeasureMode` To set the temperature measurement mode. It supports two modes: spot measurement and region temperature measurement. `Supported since MSDK 5.0.0`

[KeyThermalSpotMetersurePoint](#key_camera_thermalspotmeteringtargetpoint_inline)

###### final KeyThermalSpotMetersurePoint

|  |
| --- |
| ``` static final DJIKeyInfo<DoublePoint2D> KeyThermalSpotMetersurePoint = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalSpotMetersurePoint", new DJIValueConverter<>(DoublePoint2D.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("ThermalSpotMeteringTargetPoint") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `DoublePoint2D` To set the location of the point that needs to be measured. [0,0] means the upper left corner of camera screen. [1,1] means the lower right corner of camera screen. After setting the location of temperature measuring point, current temperature of measuring point can be obtained through `KeyThermalSpotMetersureTemperature`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`. 3. Call `KeyThermalTemperatureMeasureMode` to set `ThermalTemperatureMeasureMode` to `SPOT`.** `Supported since MSDK 5.0.0`

[KeyThermalSpotMetersureTemperature](#key_camera_thermaltemperaturedata_inline)

###### final KeyThermalSpotMetersureTemperature

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyThermalSpotMetersureTemperature = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalSpotMetersureTemperature", SingleValueConverter.DoubleConverter).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("ThermalTemperatureData") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double To get current temperature of measuring point. Calling `KeyThermalSpotMetersurePoint` to set current location of temperature measuring point. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`. 3. Call `KeyThermalTemperatureMeasureMode` to set `ThermalTemperatureMeasureMode` to `SPOT`.** `Supported since MSDK 5.0.0`

[KeyThermalRegionMetersureArea](#key_camera_thermalspotmeteringarea_inline)

###### final KeyThermalRegionMetersureArea

|  |
| --- |
| ``` static final DJIKeyInfo<DoubleRect> KeyThermalRegionMetersureArea = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalRegionMetersureArea", new DJIValueConverter<>(DoubleRect.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("ThermalSpotMeteringArea") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `DoubleRect` To set regional location that needs temperature measurement. [0,0] means the upper left corner of camera screen. [1,1] means the lower right corner of camera screen. After setting the location of temperature measuring point, temperature information of current temperature measuring region can be obtained through `KeyThermalRegionMetersureTemperature`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`. 3. Call `KeyThermalTemperatureMeasureMode` to set `ThermalTemperatureMeasureMode` to `REGION`.** `Supported since MSDK 5.0.0`

[KeyThermalRegionMetersureTemperature](#key_camera_thermalareatemperatureaggregations_inline)

###### final KeyThermalRegionMetersureTemperature

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalAreaMetersureTemperature> KeyThermalRegionMetersureTemperature = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalRegionMetersureTemperature", new DJIValueConverter<>(ThermalAreaMetersureTemperature.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("ThermalAreaTemperatureAggregations") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalAreaMetersureTemperature` To get current temperature information of current temperature measuring region. The information includes average temperatur, minimum temperature and maximum temperature. Regional location that needs temperature measurement can be obtained through `KeyThermalRegionMetersureArea`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`. 3. Call `KeyThermalTemperatureMeasureMode` to set `ThermalTemperatureMeasureMode` to `REGION`.** `Supported since MSDK 5.0.0`

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

**Parameter:** `ThermalGainMode` To set and get gain mode. The setting operation is time-consuming and takes 3 to 5 seconds to take effect. The temperature applicable range under different gain modes can be obtained through `KeyThermalGainModeTemperatureRange`. In high gain mode, a narrower temperature range can be captured with a higher sensitivity to temperature differences. In low gain mode, a wider temperature range can be captured with a lower sensitivity to temperature difference. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

[KeyThermalGainModeTemperatureRange](#key_camera_thermalgainmodetemperaturerange_inline)

###### final KeyThermalGainModeTemperatureRange

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalGainModeTemperatureRange> KeyThermalGainModeTemperatureRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalGainModeTemperatureRange", new DJIValueConverter<>(ThermalGainModeTemperatureRange.class)).canGet(true).canSet(false).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalGainModeTemperatureRange` To get temperature range of different gain modes. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:**list<`CameraThermalPalette`> To get settable palette range of infrared lens. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

[KeyThermalPalette](#key_camera_camerathermalpalette_inline)

###### final KeyThermalPalette

|  |
| --- |
| ``` static final DJIKeyInfo<CameraThermalPalette> KeyThermalPalette = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalPalette", new SingleValueConverter<>(CameraThermalPalette.class,CameraThermalPaletteMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalPalette") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `CameraThermalPalette` To set palette type. Settable palette range can be obtained through `KeyThermalPaletteRange`. Distinct colors are used to show temperature differences in the thermal image, which are related to grayscale intensity. The temperature range of the image is mapped to 256 colors and displayed in the 8-bit JPEG or MP4 format. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:** Boolean Enable or disable isotherms. Turn on the isotherm function to see objects in the specified temperature range more intuitively. The temperature range is set by two thresholds of high temperature and low temperature. When disabled, all 256 values (8-bits) are dedicated to the temperature histogram of the scene. When enabled, only 128 values (0-127) are mapped linearly to temperature. Then three bands 128-175, 176-223 and 224-255 can be mapped to the user defined temperatures to highlight them to the user. Using some of the false color palettes (like RainbowIso) results in a thermal image that is grey scale except for three specific bands highlighted by either reds, blues or greens. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

[KeyThermalIsothermUnit](#key_camera_camerathermalisothermunit_inline)

###### final KeyThermalIsothermUnit

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalIsothermUnit> KeyThermalIsothermUnit = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalIsothermUnit", new SingleValueConverter<>(ThermalIsothermUnit.class,CameraThermalIsothermUnitMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalIsothermUnit") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalIsothermUnit` Units for isotherms. Sets the unit of the Isotherm ranges to either Celsius or percent. Different units results in different value ranges for Isotherms. If the unit is percent, the settable value range is [0,100]. If the unit is Celsius, the settable upper value and lower value can refer to `KeyThermalGainModeTemperatureRange`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** 3. H20 series cameras do not support setting isotherm units. `Supported since MSDK 5.0.0`

[KeyThermalIsothermUpperValue](#key_camera_camerathermalisothermuppervalue_inline)

###### final KeyThermalIsothermUpperValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyThermalIsothermUpperValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalIsothermUpperValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalIsothermUpperValue") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer The upper threshold value for Isotherm. All temperature values above this value will use the colors 224-255 in the palette. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

[KeyThermalIsothermLowerValue](#key_camera_camerathermalisothermlowervalue_inline)

###### final KeyThermalIsothermLowerValue

|  |
| --- |
| ``` static final DJIKeyInfo<Integer> KeyThermalIsothermLowerValue = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalIsothermLowerValue", SingleValueConverter.IntegerConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("CameraThermalIsothermLowerValue") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Integer The lower threshold value for Isotherm. Temperature values between the lower and medium isotherm thresholds will be displayed in 128-175 colors in the palette. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:** `ThermalDisplayMode` To set display mode of infrared lens. The mode includes infrared display and split screen display. If you need to set the infrared lens to the split screen display mode, please call this interface to set the display mode to `PIP`, and then call `KeyThermalPIPPosition` to set the display position to `SIDE_BY_SIDE`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

[KeyThermalPIPPosition](#key_camera_thermalpipposition_inline)

###### final KeyThermalPIPPosition

|  |
| --- |
| ``` static final DJIKeyInfo<ThermalPIPPosition> KeyThermalPIPPosition = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalPIPPosition", new SingleValueConverter<>(ThermalPIPPosition.class,PIPPositionMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `ThermalPIPPosition` Sets the display position of the infrared lens split screen. Please call `KeyThermalDisplayMode` to set the display mode to split screen display `PIP`. Then call this interface to set the display position to `SIDE_BY_SIDE`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.1.0`

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

**Parameter:** `ThermalFFCMode` To set FFC calibration mode. FFC calibration is a function of infrared lens. Image quality will be improved after the calibration. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

[KeyThermalTriggerFFC](#key_camera_thermaltriggerffc_inline)

###### final KeyThermalTriggerFFC

|  |
| --- |
| ``` static final DJIActionKeyInfo<EmptyMsg,EmptyMsg> KeyThermalTriggerFFC = new DJIActionKeyInfo<>(componentType.value(),subComponentType.value(),"ThermalTriggerFFC", EmptyValueConverter.converter,EmptyValueConverter.converter).canGet(false).canSet(false).canListen(false).canPerformAction(true).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

Manually trigger the FFC calibration mode once. During the calibration process, the camera screen may pause for 1 second, and the camera will make a "click" sound. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

Zoom Ratio

[KeyThermalZoomRatios](#key_camera_thermalzoomratios_inline)

###### final KeyThermalZoomRatios

|  |
| --- |
| ``` static final DJIKeyInfo<Double> KeyThermalZoomRatios = new KeyThermalZoomRatios()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** Double To set the infrared zoom ratio. And 1x, 2x, 4x and 8x zoom ratio is supported. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

Link Zoom

[KeyLinkZoomEnabled](#key_camera_enablezoomlinkage_inline)

###### final KeyLinkZoomEnabled

|  |
| --- |
| ``` static final DJIKeyInfo<Boolean> KeyLinkZoomEnabled = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"LinkZoomEnabled", SingleValueConverter.BooleanConverter).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false).setInnerIdentifier("EnableZoomLinkage") ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:**Boolean Enable or disable the link zoom function. After enabling the link zoom function, call `KeyThermalZoomRatios` to achieve synchronous zooming of the infrared lens and the zoom lens. In the split-screen mode `PIP`, the user can compare the images of the infrared lens and the zoom lens more efficiently. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.3.0`

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

**Parameter:** Boolean To set the infrared super resolution open and close. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:** `ThermalROI` To set the region of interest (ROI). User can choose ROI based on their needs. When the region of interest is full screen, the color levels of the entire image will be distributed in the default configuration. For instance, if there is a large patch of sky (relatively low temperature) in your image, much of the color spectrum will be allocated to the lower range, meaning that other parts of the spectrum will have a lower contrast. In this case, you may select “Sky excluded (33%)” or “Sky excluded (50%)” to ignore areas of the sky so that most of the spectrum can be allocated to remaining areas, providing higher contrast and utility for analysis. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:** `ThermalScene` To set thermal scene. It supports default and inspection scenarios, as well as custom scenarios. Users can set and save customized parameters according to their needs. The camera will adjust the camera parameters according to different scenes to optimize the image processing. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:** Integer To set thermal Digital Detail Enhancement (DDE). The sharpness of images and outlines can be improved. Manual setting is only possible when the `KeyThermalScene` is `MANUAL`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

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

**Parameter:** Integer To set thermal contrast. The ratio of screen black to white. The larger the ratio, the richer the color performance. Manual setting is only possible when the `KeyThermalScene` is Scene `MANUAL`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0</code`

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

**Parameter:** Integer To set thermal brightness. Manual setting is only possible when the `KeyThermalScene` is `MANUAL`. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `INFRARED_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_THERMAL`.** `Supported since MSDK 5.0.0`

#### Multispectral

Fusion Type

[KeyMultiSpectralFusionType](#key_camera_multispectralfusiontype_inline)

###### final KeyMultiSpectralFusionType

|  |
| --- |
| ``` static final DJIKeyInfo<MultiSpectralFusionType> KeyMultiSpectralFusionType = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiSpectralFusionType", new SingleValueConverter<>(MultiSpectralFusionType.class,MultiSpectralFusionTypeMsg.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `MultiSpectralFusionType` Sets the fusion type to coordinate the video feeds from both the RGB sensor and single-band sensors. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `NDVI_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_MS_NDVI`.** `Supported since MSDK 5.2.0`

Fusion Display Range

[KeyMultiSpectralFusionDisplayRange](#key_camera_multispectralfusiondisplayrange_inline)

###### final KeyMultiSpectralFusionDisplayRange

|  |
| --- |
| ``` static final DJIKeyInfo<MultiSpectralFusionDisplayRange> KeyMultiSpectralFusionDisplayRange = new DJIKeyInfo<>(componentType.value(),subComponentType.value(),"MultiSpectralFusionDisplayRange", new DJIValueConverter<>(MultiSpectralFusionDisplayRange.class)).canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `MultiSpectralFusionDisplayRange` Sets the multispectral fusion display range. the settable value range is [-10,10]. In the pilot this range is mapped to [-1,1]. **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `NDVI_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_MS_NDVI`.** `Supported since MSDK 5.2.0`

Display Mode

[KeyMultiSpectralDisplayMode](#key_camera_multispectraldisplaymode_inline)

###### final KeyMultiSpectralDisplayMode

|  |
| --- |
| ``` static final DJIKeyInfo<MultiSpectralDisplayMode> KeyMultiSpectralDisplayMode = new KeyMultiSpectralDisplayMode()            .canGet(true).canSet(true).canListen(true).canPerformAction(false).setIsEvent(false) ``` |

|  |  |
| --- | --- |
| *Package:* | dji.sdk.keyvalue.key |

##### Description:

**Parameter:** `MultiSpectralDisplayMode` To set display mode of NDVI lens. The mode includes NDVI display and split screen display. If you need to set the NDVI lens to the split screen display mode, please call this interface to set the display mode to `PIP` **Notice： 1. To use this function, please call `KeyCameraVideoStreamSource` to set the video source to `NDVI_CAMERA`. 2. When using `KeyTools` to create a `DJIKey` instance, please set `CameraLensType` to `CAMERA_LENS_MS_NDVI`.** `Supported since MSDK 5.2.0`

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

To reset camera parameters. `Supported since MSDK 5.0.0`

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

**Parameter:**`CameraStorageLocation` To format storage. M30 and M30T camera only supports SD Card. `Supported since MSDK 5.0.0`

[×](javascript:;)

## Search Mobile SDK Document

{{searchCount}} Results

- ### {{{(item.highlights && item.highlights.title) || item.title}}}

  {{{(item.highlights && item.highlights.content[0]) || item.title}}}

[Load More](javascript:;)

Loading...

Not Found