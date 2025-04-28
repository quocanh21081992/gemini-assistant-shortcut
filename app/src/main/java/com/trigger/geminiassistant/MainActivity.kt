package com.trigger.geminiassistant

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Định nghĩa tên các package cần kiểm tra
        val geminiPackageName = "com.google.android.apps.bard"
        val googleAppPackageName = "com.google.android.googlequicksearchbox"

        // Kiểm tra sự tồn tại của các package
        val isGeminiInstalled = isPackageInstalled(geminiPackageName)
        val isGoogleAppInstalled = isPackageInstalled(googleAppPackageName)

        if (isGeminiInstalled && isGoogleAppInstalled) {
            // Nếu cả hai package đều tồn tại, thử khởi chạy ACTION_VOICE_COMMAND
            val intent = Intent(Intent.ACTION_VOICE_COMMAND).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            try {
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                // Xử lý nếu không tìm thấy Activity để xử lý intent (trường hợp hiếm nếu app đã cài)
                Toast.makeText(this, "Could not launch voice command.", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Nếu thiếu ít nhất một trong hai package, hiển thị thông báo
            Toast.makeText(this, "Please install Google and Gemini apps from Play Store", Toast.LENGTH_LONG).show()
        }

        // Kết thúc Activity hiện tại sau khi xử lý
        finish()
    }

    // Hàm kiểm tra xem một package có được cài đặt trên thiết bị không
    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (_: PackageManager.NameNotFoundException) {
            false
        }
    }
}