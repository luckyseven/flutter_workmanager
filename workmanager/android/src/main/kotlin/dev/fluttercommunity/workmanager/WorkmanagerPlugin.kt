package dev.fluttercommunity.workmanager

import android.content.Context
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class WorkmanagerPlugin : FlutterPlugin, ActivityAware {
    private var methodChannel: MethodChannel? = null
    private var workmanagerCallHandler: WorkmanagerCallHandler? = null
    private var context: Context? = null

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        context = binding.applicationContext
        workmanagerCallHandler = WorkmanagerCallHandler(binding.applicationContext)
        methodChannel = MethodChannel(binding.binaryMessenger, "be.tramckrijte.workmanager/foreground_channel_work_manager")
        methodChannel?.setMethodCallHandler(workmanagerCallHandler)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel?.setMethodCallHandler(null)
        methodChannel = null
        workmanagerCallHandler = null
        context = null
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        // No activity-specific setup needed
    }

    override fun onDetachedFromActivityForConfigChanges() {
        // No activity-specific cleanup needed
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        // No activity-specific setup needed
    }

    override fun onDetachedFromActivity() {
        // No activity-specific cleanup needed
    }
}
