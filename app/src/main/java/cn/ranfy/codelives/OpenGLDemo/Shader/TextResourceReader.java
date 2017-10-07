package cn.ranfy.codelives.OpenGLDemo.Shader;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.ranfy.codelives.UtilsGroup.Utils.ToastUtils;

/**
 * Created by ransom on 2017/10/7 16:44.
 */
public class TextResourceReader {

    public static String readTextFileFromResource(Context context, int resourceId) {

        StringBuilder body = new StringBuilder();

        try {

            InputStream is = context.getResources().openRawResource(resourceId);

            InputStreamReader reader = new InputStreamReader(is);

            BufferedReader bufferedReader = new BufferedReader(reader);

            String nextLine;

            while ((nextLine = bufferedReader.readLine()) != null) {

                body.append(nextLine);

                body.append("\n");

            }

        } catch (IOException e) {
            ToastUtils.ToastBottom("Could not open resource: " + resourceId+""+e.toString());
            throw new RuntimeException(


                    "Could not open resource: " + resourceId, e);


        } catch (Resources.NotFoundException nfe) {
            ToastUtils.ToastBottom("Could not open resource: " + resourceId+""+nfe.toString());
            throw new RuntimeException("Resource not found: " + resourceId, nfe);

        }

        return body.toString();

    }
}