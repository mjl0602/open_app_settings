import 'dart:async';

import 'package:flutter/services.dart';

class OpenAppSettings {
  // Static constant variable to initialize MethodChannel of 'open_app_settings'.
  static const MethodChannel _channel =
      const MethodChannel('open_app_settings');

  /// Future async method call to open WIFI settings.
  static Future<void> openWIFISettings() async => _to('wifi');

  /// Future async method call to open location settings.
  static Future<void> openLocationSettings() async => _to('location');

  /// Future async method call to open security settings.
  static Future<void> openSecuritySettings() async => _to('security');

  /// Future async method call to open bluetooth settings.
  static Future<void> openBluetoothSettings() async => _to('bluetooth');

  /// Future async method call to open data roaming settings.
  static Future<void> openDataRoamingSettings() async => _to('data_roaming');

  /// Future async method call to open date settings.
  static Future<void> openDateSettings() async => _to('date');

  /// Future async method call to open display settings.
  static Future<void> openDisplaySettings() async => _to('display');

  /// Future async method call to open notification settings.
  static Future<void> openNotificationSettings() async => _to('notification');

  /// Future async method call to open sound settings.
  static Future<void> openSoundSettings() async => _to('sound');

  /// Future async method call to open internal storage settings.
  static Future<void> openInternalStorageSettings() async =>
      _to('internal_storage');

  /// Future async method call to open battery optimization settings.
  static Future<void> openBatteryOptimizationSettings() async =>
      _to('battery_optimization');

  /// Future async method call to open app specific settings screen.
  static Future<void> openAppSettings() async => _to('app_settings');

  /// Future async method call to open app specific settings screen.
  static Future<void> openAndroidCustomSetting(String constantValue) async => _to("android", args: {"setting": constantValue});

  /// Future async method call to open NCF settings.
  static Future<void> openNFCSettings() async => _to('nfc');

  static _to(String tag, {Map<String, String>? args}) => _channel.invokeMethod(tag, args ?? {});
}
