package com.beijing.zzu.zsxy.utils;

import com.beijing.zzu.zsxy.model.VideoItemData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiayongkai on 2017/5/22.
 */

public class JsonUtil {

    public static boolean isOk(String result){
        try {
            JSONObject resp=new JSONObject(result);
            int status=resp.optInt("status");
            if (status == 200){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static List<VideoItemData> parseVideoList(String result){
        List<VideoItemData> videoItemDatas=null;
        if (!isOk(result)){
            return videoItemDatas;
        }
        try {
            JSONObject resp=new JSONObject(result);
            JSONArray resultArr = resp.optJSONArray("result");
            if (resultArr != null){
                videoItemDatas=new ArrayList<>();

                for (int i = 0; i < resultArr.length(); i++) {
                    VideoItemData videoItemData=new VideoItemData();
                    JSONObject resultObj = resultArr.optJSONObject(i);
                    if (resultObj != null){
                        JSONObject channelObj = resultObj.optJSONObject("channel");
                        if (channelObj != null){
                            JSONObject extOBj = channelObj.optJSONObject("ext");
                            if (extOBj != null){
                                videoItemData.setTimeLength(extOBj.optString("lengthNice"));
                                videoItemData.setFinishTimeNice(extOBj.optString("finishTimeNice"));
                                videoItemData.setTitle(extOBj.optString("ft"));
                            }
                        }

                        JSONObject picObj = channelObj.optJSONObject("pic");
                        if (picObj != null){
                            videoItemData.setImgUrl(picObj.optString("base"));
                        }

                        JSONObject streamObj = channelObj.optJSONObject("stream");
                        if (streamObj != null){
                            videoItemData.setVideoUrl(streamObj.optString("base"));
                        }
                    }

                    videoItemDatas.add(videoItemData);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return videoItemDatas;

    }
}
