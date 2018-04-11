package com.zhongyu.test;

import com.zhongyu.business.BaiduBusiness;
import com.zhongyu.code.DriverBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by YuChan on 02/26/2017.
 */
public class BaiduTest {

    private DriverBase driver;
    private BaiduBusiness baiduBusiness;

    @BeforeClass
    public void setUp() {
        this.driver = new DriverBase();
        baiduBusiness = new BaiduBusiness(driver);
    }

    @Test
    public void translate(){
        driver.get("http://fanyi.baidu.com/#zh/en");
        baiduBusiness.translate("yellow");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}