package org.orechou.oia.chapter01

import android.opengl.GLES30
import android.util.Log
object ShaderUtils {

    private const val TAG = "ShaderUtils"

    private const val VERTEX_SHADER_STR =
        "attribute vec4 a_Position;\n" +
            "void main()\n" +
            "{\n" +
            "    gl_Position = a_Position;\n" +
            "    gl_PointSize = 10.0;\n" +
            "}"

    private const val FRAGMENT_SHADER_STR =
        "precision mediump float;\n" +
            "uniform vec4 u_Color;\n" +
            "void main()\n" +
            "{\n" +
            "    gl_FragColor = u_Color;\n" +
            "}"

    const val U_COLOR = "u_Color"
    const val A_POSITION = "a_Position"

    fun buildProgram(): Int {
        val vertexShaderId = compileShader(GLES30.GL_VERTEX_SHADER, VERTEX_SHADER_STR)
        val fragmentShaderId = compileShader(GLES30.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_STR)
        val programId = linkProgram(vertexShaderId, fragmentShaderId)
        validateProgram(programId)
        return programId
    }

    private fun compileShader(shaderType: Int, shaderStr: String): Int {
        val shaderId = GLES30.glCreateShader(shaderType)
        if (shaderId == 0) {
            Log.d(TAG, "create shader failed.")
            return 0
        }
        GLES30.glShaderSource(shaderId, shaderStr)
        GLES30.glCompileShader(shaderId)
        val compileStatus = intArrayOf(1)
        GLES30.glGetShaderiv(shaderId, GLES30.GL_COMPILE_STATUS, compileStatus, 0)
        if (compileStatus[0] == 0) {
            Log.d(TAG, "compile shader failed.")
            GLES30.glDeleteShader(shaderId)
            return 0
        }
        return shaderId
    }

    private fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
        val programId = GLES30.glCreateProgram()
        if (programId == 0) {
            Log.d(TAG, "create program failed.")
            return 0
        }
        GLES30.glAttachShader(programId, vertexShaderId)
        GLES30.glAttachShader(programId, fragmentShaderId)
        GLES30.glLinkProgram(programId)
        val linkStatus = intArrayOf(1)
        GLES30.glGetProgramiv(programId, GLES30.GL_LINK_STATUS, linkStatus, 0)
        if (linkStatus[0] == 0) {
            Log.d(TAG, "link program failed.")
            return 0
        }
        return programId
    }

    private fun validateProgram(programId: Int): Boolean {
        GLES30.glValidateProgram(programId)
        val validateStatus = intArrayOf(1)
        GLES30.glGetProgramiv(programId, GLES30.GL_VALIDATE_STATUS, validateStatus, 0)
        return validateStatus[0] != 0
    }

}