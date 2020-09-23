package com.oversketch.open_app_settings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * OpenAppSettingsPlugin
 */
public class OpenAppSettingsPlugin implements ActivityAware, FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private Activity activity;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "open_app_settings");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        this.activity = binding.getActivity();
    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        this.activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onDetachedFromActivity() {

    }

    private void openSettings(String url) {
        try {
            this.activity.startActivity(new Intent(url));
        } catch (Exception e) {
            // Default to APP Settings if setting activity fails to load/be available on device
            openAppSettings();
        }
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package", this.activity.getPackageName(), null);
        intent.setData(uri);
        this.activity.startActivity(intent);
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "open_app_settings");
        OpenAppSettingsPlugin plugin = new OpenAppSettingsPlugin();
        plugin.activity = registrar.activity();
        channel.setMethodCallHandler(plugin);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("wifi")) {
            openSettings(Settings.ACTION_WIFI_SETTINGS);
        } else if (call.method.equals("location")) {
            openSettings(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        } else if (call.method.equals("security")) {
            openSettings(Settings.ACTION_SECURITY_SETTINGS);
        } else if (call.method.equals("bluetooth")) {
            openSettings(Settings.ACTION_BLUETOOTH_SETTINGS);
        } else if (call.method.equals("data_roaming")) {
            openSettings(Settings.ACTION_DATA_ROAMING_SETTINGS);
        } else if (call.method.equals("date")) {
            openSettings(Settings.ACTION_DATE_SETTINGS);
        } else if (call.method.equals("display")) {
            openSettings(Settings.ACTION_DISPLAY_SETTINGS);
        } else if (call.method.equals("notification")) {
            if (Build.VERSION.SDK_INT >= 21) {
                Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .putExtra(Settings.EXTRA_APP_PACKAGE, this.activity.getPackageName());
                this.activity.startActivity(intent);
            } else {
                openSettings(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            }
        } else if (call.method.equals("nfc")) {
            openSettings(Settings.ACTION_NFC_SETTINGS);
        } else if (call.method.equals("sound")) {
            openSettings(Settings.ACTION_SOUND_SETTINGS);
        } else if (call.method.equals("internal_storage")) {
            openSettings(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
        } else if (call.method.equals("battery_optimization")) {
            openSettings(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        } else if (call.method.equals("app_settings")) {
            openAppSettings();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
