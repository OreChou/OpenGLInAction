package org.orechou.oia.chapter01

import android.opengl.GLES20
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

open class BasicRenderer : GLSurfaceView.Renderer {

    protected var vertexData: FloatArray? = null
    protected var floatBuffer: FloatBuffer? = null

    protected var programId = 0
    protected var aColorHandle = 0
    protected var aPositionHandle = 0
    protected val color = floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f)

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT or GLES30.GL_DEPTH_BUFFER_BIT)
        GLES20.glUniform4fv(aColorHandle, 1, color, 0)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
        programId = ShaderUtils.buildProgram()
        GLES30.glUseProgram(programId)
        aColorHandle = GLES30.glGetUniformLocation(programId, ShaderUtils.U_COLOR)
        aPositionHandle = GLES30.glGetAttribLocation(programId, ShaderUtils.A_POSITION)
    }

}