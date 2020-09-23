# open_app_settings

Open App setting page by ObjC and java code.

# Simple Use

```dart
return MaterialApp(
  home: Scaffold(
    appBar: AppBar(
      title: const Text('Plugin example app'),
    ),
    body: Center(
      child: RaisedButton(
        color: Colors.blue,
        onPressed: () async {
          await OpenAppSettings.openAppSettings();
        },
        child: Text('Open'),
      ),
    ),
  ),
);
```

# Remark

This pluin is a remaster of `app_settings`. Piugin `app_setting` use `Kotlin` and `Swift` may cause some build issue, so this plugin used `Java` and `ObjC` to do same works.