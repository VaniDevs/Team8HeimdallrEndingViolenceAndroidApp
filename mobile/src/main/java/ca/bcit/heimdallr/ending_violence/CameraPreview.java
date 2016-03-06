package ca.bcit.heimdallr.ending_violence;

import java.io.IOException;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kevin on 3/5/2016.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private Camera myCamera;
    private SurfaceHolder mHolder;

    public CameraPreview(Context ctx, Camera camera){
        super(ctx);
        myCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }
    public void surfaceCreated(SurfaceHolder holder){
        try {
            if (myCamera == null) {
                myCamera.setPreviewDisplay(holder);
                myCamera.startPreview();

            }
        }
        catch(IOException e){
                Log.d(VIEW_LOG_TAG,"Error setting camera preview:" + e.getMessage());
        }
    }
    public void refreshCamera(Camera camera){
        if(mHolder.getSurface() == null){
            return;
        }
        try{
            myCamera.stopPreview();
        }catch(Exception e){
            //Just ignore it
        }
        setCamera(camera);
        try{
            myCamera.setPreviewDisplay(mHolder);
            myCamera.startPreview();
        }catch(Exception e){
            Log.d(VIEW_LOG_TAG, "Error starting camera preview: " + e.getMessage());
        }

    }
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
        refreshCamera(myCamera);
    }
    public void setCamera(Camera camera){
        myCamera = camera;
    }

    public void surfaceDestroyed(SurfaceHolder arg0){
        myCamera.stopPreview();
        myCamera = null;
    }

}
