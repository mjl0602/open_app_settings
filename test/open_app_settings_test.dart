import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:open_app_settings/open_app_settings.dart';

void main() {
  const MethodChannel channel = MethodChannel('open_app_settings');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });
}
