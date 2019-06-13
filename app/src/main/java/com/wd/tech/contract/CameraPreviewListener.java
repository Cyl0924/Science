package com.wd.tech.contract;

import com.arcsoft.facetracking.AFT_FSDKFace;

import java.util.List;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public interface CameraPreviewListener {
    void onPreviewData(byte[] data,List<AFT_FSDKFace> fsdkFaces);
    void onPreviewSize(int width,int height);
}
