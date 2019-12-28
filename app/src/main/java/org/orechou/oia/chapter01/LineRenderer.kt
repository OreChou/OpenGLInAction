package org.orechou.oia.chapter01

import android.opengl.GLES30
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class LineRenderer : BasicRenderer() {

    init {
        vertexData = floatArrayOf(
            0.0f, 0.0f,
            0.5f, 0.5f
        )
        floatBuffer = ByteBuffer.allocateDirect(vertexData?.size!! * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertexData)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glEnableVertexAttribArray(aPositionHandle)
        super.onDrawFrame(gl)
        GLES30.glLineWidth(8f)
        GLES30.glDrawArrays(GLES30.GL_LINES, 0, 2)
        // 禁止顶点数组的句柄
        GLES30.glDisableVertexAttribArray(aPositionHandle)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        floatBuffer?.position(0)
        GLES30.glVertexAttribPointer(aPositionHandle, 2, GLES30.GL_FLOAT, false, 0, floatBuffer)
    }
}