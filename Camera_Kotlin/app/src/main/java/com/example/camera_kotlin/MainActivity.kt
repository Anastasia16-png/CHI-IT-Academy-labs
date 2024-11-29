package com.example.camera_kotlin
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.hardware.Camera

class MainActivity : AppCompatActivity() {

    private lateinit var camera: Camera
    private lateinit var surfaceView: SurfaceView
    private lateinit var startCameraButton: Button
    private lateinit var stopCameraButton: Button
    private var isCameraActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surfaceView = findViewById(R.id.surfaceView)
        startCameraButton = findViewById(R.id.startCameraButton)
        stopCameraButton = findViewById(R.id.stopCameraButton)

        // camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        } else {
            initializeCamera()
        }

        startCameraButton.setOnClickListener {
            if (!isCameraActive) {
                startCamera()
            } else {
                Toast.makeText(this, "Camera is already active", Toast.LENGTH_SHORT).show()
            }
        }

        stopCameraButton.setOnClickListener {
            if (isCameraActive) {
                stopCamera()
            } else {
                Toast.makeText(this, "Camera is not active", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // launching the camera
    private fun startCamera() {
        try {
            camera = Camera.open()
            camera.setPreviewDisplay(surfaceView.holder)
            camera.startPreview()
            isCameraActive = true
        } catch (e: Exception) {
            Log.e("CameraApp", "Failed to start camera: ${e.message}")
        }
    }

    // Camera stop
    private fun stopCamera() {
        camera.stopPreview()
        camera.release()
        isCameraActive = false
    }

    private fun initializeCamera() {
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                startCamera()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                camera.stopPreview()
                camera.setPreviewDisplay(holder)
                camera.startPreview()
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                stopCamera()
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initializeCamera()
        } else {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}