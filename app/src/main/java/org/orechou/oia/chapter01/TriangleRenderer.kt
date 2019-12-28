package org.orechou.oia.chapter01

import android.opengl.GLES30
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TriangleRenderer : BasicRenderer() {

    init {
        vertexData = floatArrayOf(
            0.5f,  0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f  // bottom right
        )
        floatBuffer = ByteBuffer.allocateDirect(vertexData?.size!!.times(4))
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertexData)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3)
//        GLES30.glDisableVertexAttribArray(aPositionHandle)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        floatBuffer?.position(0)
        GLES30.glVertexAttribPointer(aPositionHandle, 3, GLES30.GL_FLOAT, false, 0, floatBuffer)
        GLES30.glEnableVertexAttribArray(aPositionHandle)
    }

}