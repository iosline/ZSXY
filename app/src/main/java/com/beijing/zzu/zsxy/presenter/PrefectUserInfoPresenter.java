package com.beijing.zzu.zsxy.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.beijing.zzu.zsxy.Constants;
import com.beijing.zzu.zsxy.model.User;
import com.beijing.zzu.zsxy.utils.PreferencesUtils;
import com.beijing.zzu.zsxy.view.PrefectUserInfoView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.utils.UrlSafeBase64;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by jiayongkai on 2017/4/12.
 */

public class PrefectUserInfoPresenter extends BasePresenter<PrefectUserInfoView>{

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    User user=new User();
    private String userobjectId=PreferencesUtils.getString(Constants.USER_OBJECTID);;

    public void saveUserInfo(){

        String nickname=mView.getNickName();
        String profileUrl=mView.getUpdateProfileUrl();
        int sexValue=mView.getUserSex();
        if (!TextUtils.isEmpty(profileUrl)){
            user.setPhoto(profileUrl);
        }
        if (!TextUtils.isEmpty(nickname)){
            user.setNickname(nickname);
        }
        user.setSex(sexValue);


    }

    public void getUserInfo(){
        if (TextUtils.isEmpty(userobjectId)){
            return;
        }


    }


    public void  uploadProfile(final String imagePath){
        try {
            // 1:第一种方式 构造上传策略
            JSONObject _json = new JSONObject();
            long _dataline = System.currentTimeMillis() / 1000 + 3600;
            _json.put("deadline", _dataline);// 有效时间为一个小时
            _json.put("scope", Constants.QINIU_BUCKETNAME);
            String _encodedPutPolicy = UrlSafeBase64.encodeToString(_json
                    .toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, Constants.QINIU_SECRET_KEY);
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
            String _uploadToken = Constants.QINIU_ACCESS_KEY + ':' + _encodedSign + ':'
                    + _encodedPutPolicy;
            UploadManager uploadManager = new UploadManager();
            //参数介绍  图片地址  上传到七牛之后图片名,token
            uploadManager.put(imagePath,getImageName(), _uploadToken,
                    new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info,
                                             JSONObject response) {
                            if (info.isOK()){
                                String path=response.optString("key");
                                mView.onUploadProfileSuccess(imagePath,Constants.QINIU_WAILIAN_URL+path);
                            }else {
                                mView.onUploadProfileFailure();
                            }
                            Log.e("qiniu==info==", info.toString());
                            Log.e("qiniu==respne==", response.toString());
                        }
                    },new UploadOptions(null,null,false,progressHandler,null));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //上传进度
    UpProgressHandler progressHandler=new UpProgressHandler() {
        @Override
        public void progress(String key, double percent) {

        }
    };

    /**
     * 这个签名方法找了半天 一个个对出来的、、、、程序猿辛苦啊、、、 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }

    public String getImageName(){
        return System.currentTimeMillis()+"";
    }

}
