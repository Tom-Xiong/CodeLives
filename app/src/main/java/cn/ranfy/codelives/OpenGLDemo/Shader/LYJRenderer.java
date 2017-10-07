package cn.ranfy.codelives.OpenGLDemo.Shader;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import cn.ranfy.codelives.OpenGLDemo.OpenGLUtils;
import cn.ranfy.codelives.R;

/**
 * Created by ransom on 2017/10/7 16:50.
 */

public class LYJRenderer implements GLSurfaceView.Renderer {

    private int program;
    private final FloatBuffer vertexData;
    private Context context;

    private static int BYTES_PER_FLOAT =4;

    float[] tableVerticesWithTriangles = {

            //两个三角形和三角形的颜色分量
            0f, 0f, 1f, 1f, 1f,
            -0.5f, -0.5f, 0.1f, 0.5f, 0.7f,
            0.5f, -0.5f, 0.2f, 0.3f, 0.3f,
            0.5f, 0.5f, 0.3f, 0.2f, 0.4f,
            -0.5f, 0.5f, 0.5f, 0.6f, 0.1f,
            -0.5f, -0.5f, 0.7f, 0.1f, 0.3f,

            //两条直线和直线的颜色分量
            -0.5f, 0f, 1f, 0.3f, 0.6f,
            0.5f, 0f, 1f, 0.2f, 0.4f,

            //两个顶点和顶点的颜色分量
            0f, -0.25f, 0f, 0.3f, 1f,
            0f, 0.25f, 1f, 0.5f, 0.2f
    };

    private static final String A_POSITION="a_Position";

    private int aPositionLocation;

    private static final int POSITION_COMPONENT_COUNT = 2;

    private static final int COLOR_COMPONENT_COUNT=3;

    private static final String A_COLOR = "a_Color";

    private int aColorLocation;

    private static final int STRIDE=(POSITION_COMPONENT_COUNT+COLOR_COMPONENT_COUNT)*BYTES_PER_FLOAT;



    public LYJRenderer(Context context){
        this.context = context;
        this.vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.vertexData.put(tableVerticesWithTriangles);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1f, 0.0f, 0.0f, 0.0f);
        String vertexShaderSource = TextResourceReader.readTextFileFromResource(this.context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(this.context, R.raw.simple_fragment_shader);
        int vertexShader = OpenGLUtils.compileVertexShader(vertexShaderSource);
        int fragmentShader = OpenGLUtils.compileFragmentShader(fragmentShaderSource);
        program = OpenGLUtils.linkProgram(vertexShader,fragmentShader);
        if(OpenGLUtils.validateProgram(program)){
            GLES20.glUseProgram(program);
        }
        this.aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        this.vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false,STRIDE,vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);

        this.aColorLocation = GLES20.glGetAttribLocation(program,A_COLOR);
        this.vertexData.position(POSITION_COMPONENT_COUNT);
        GLES20.glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, this.vertexData);
        GLES20.glEnableVertexAttribArray(aColorLocation);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);

        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);

        GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
    }


}
