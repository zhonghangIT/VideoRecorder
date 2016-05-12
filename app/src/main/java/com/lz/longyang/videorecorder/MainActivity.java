package com.lz.longyang.videorecorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

import com.lz.longyang.videorecorder.R;

public class MainActivity extends AppCompatActivity {


    private MediaRecorder recorder;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceView.setKeepScreenOn(true);//保持屏幕的高亮
        //获取SurfaceHolder
        SurfaceHolder holder = surfaceView.getHolder();
        //让数据直接输出
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    public void start(View v) {
        try {
            //实例化媒体刻录器
            recorder = new MediaRecorder();
            //设置音频的来源    麦克风
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置视频的来源   相机
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //指定文件输出的格式   .3gp
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //设置音频编码的格式
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //设置视频编码的格式
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
            //指定文件输出的路径
            File file = new File(Environment.getExternalStorageDirectory(),"mimi.3gp");
            recorder.setOutputFile(file.getAbsolutePath());

            //把SurfaceView和MediaRecorder关联
            recorder.setPreviewDisplay(surfaceView.getHolder().getSurface());//设置预览显示

            //准备
            recorder.prepare();
            //刻录
            recorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(View v) {
        //停止刻录
        recorder.stop();
        //重置
        recorder.reset();
        //释放资源
        recorder.release();
        recorder = null;
    }

}
