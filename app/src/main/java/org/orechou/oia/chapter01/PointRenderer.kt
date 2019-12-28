package org.orechou.oia.chapter01

import android.opengl.GLES30
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class PointRenderer : BasicRenderer() {

    init {
        vertexData = floatArrayOf(0f, 0f)
        floatBuffer = ByteBuffer.allocateDirect(vertexData?.size!!.times(4))
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertexData)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, 1)
        // 禁止顶点数组的句柄
        GLES30.glDisableVertexAttribArray(aPositionHandle)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        floatBuffer?.position(0)
        GLES30.glVertexAttribPointer(aPositionHandle, 2, GLES30.GL_FLOAT, false, 0, floatBuffer)
        GLES30.glEnableVertexAttribArray(aPositionHandle)
    }
}