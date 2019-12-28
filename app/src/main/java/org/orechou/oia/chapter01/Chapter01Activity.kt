package org.orechou.oia.chapter01

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.orechou.oia.R

/**
 * Chapter01：
 * 1. 学习使用 GLSurfaceView
 * 2. 使用 GLSurfaceView 绘制：点、线、三角形
 */
class Chapter01Activity : AppCompatActivity() {

    private var glSurfaceView: GLSurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter01)
        glSurfaceView = findViewById(R.id.gl_surface_view)
        // Inform the default EGLContextFactory and default EGLConfigChooser which EGLContext client version to pick.
        glSurfaceView?.setEGLContextClientVersion(3)
        glSurfaceView?.setRenderer(LineRenderer())
//        glSurfaceView?.requestRender()
    }

}
