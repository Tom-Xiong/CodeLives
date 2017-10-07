package cn.ranfy.codelives.OpenGLDemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES20;
import android.os.Build;
import android.util.Log;

import cn.ranfy.codelives.UtilsGroup.Utils.ToastUtils;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by ransom on 2017/10/6 10:56.
 */

public class OpenGLUtils {
    public static Boolean checkSupported(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        Boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x2000;

        boolean isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));

        supportsEs2 = supportsEs2 || isEmulator;

        return supportsEs2;
    }

    public static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode) {

        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);

    }

    public static int compileFragmentShader(String shaderCode) {

        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);

    }

    public static int compileShader(int type, String shaderCode) {
        final int shaderObjectId = GLES20.glCreateShader(type);

        if (shaderObjectId == 0) {
            ToastUtils.ToastBottom("Counld not create new shader");
            return 0;
        }
        GLES20.glShaderSource(shaderObjectId, shaderCode);
        GLES20.glCompileShader(shaderObjectId);
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            GLES20.glDeleteShader(shaderObjectId);
            ToastUtils.ToastBottom("Counld not complie shader");
            return 0;
        }
        return shaderObjectId;
    }

    public static int linkProgram(int vertexShdaerId, int fragmentShaderId) {

        final int programObjectId = GLES20.glCreateProgram();
        if (programObjectId == 0) {
            ToastUtils.ToastBottom("Counld not create program");
            return 0;
        }

        GLES20.glAttachShader(programObjectId, vertexShdaerId);
        GLES20.glAttachShader(programObjectId, fragmentShaderId);
        GLES20.glLinkProgram(programObjectId);
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(programObjectId);
            ToastUtils.ToastBottom("Counld not link program");
            return 0;
        }
        return programObjectId;
    }

    public static boolean validateProgram(int programObjectId){

        GLES20.glValidateProgram(programObjectId);

        final int[] validateStatus=new int[1];

        GLES20.glGetProgramiv(programObjectId,GLES20.GL_VALIDATE_STATUS,validateStatus,0);

        Log.v(TAG,GLES20.glGetProgramInfoLog(programObjectId));

        return validateStatus[0]!=0;

    }
}
