package com.zhongyu.business;

import com.zhongyu.code.DriverBase;
import com.zhongyu.handle.BaiduHandle;

/**
 * Created by YuChan on 02/26/2017.
 */
public class BaiduBusiness {

    private BaiduHandle baiduHandle;

    public BaiduBusiness(DriverBase driver) {
        baiduHandle = new BaiduHandle(driver);
    }

    public void search(String key){
        baiduHandle.sendKeysSearchInput(key);
    }

    public void translate(String row) {
        baiduHandle.sendKeysContent(row);
        baiduHandle.clickTranslate();
        baiduHandle.getResult();
    }

}