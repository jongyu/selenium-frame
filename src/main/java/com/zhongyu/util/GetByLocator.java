package com.zhongyu.util;

import org.openqa.selenium.By;

/**
 * Created by YuChan on 02/26/2017.
 */
public class GetByLocator {

    /**
     * 使用YAML文件管理定位Element
     *
     * @param node 父节点
     * @param key  子节点
     * @return
     */
    public static By getLocator(String node, String key) {
        String locator = YamlUtil.getInstance("element").get(node, key);
        String type = locator.split(">")[0];
        String value = locator.split(">")[1];
        return by(type, value);
    }

    /**
     * @param type  定位类型
     * @param value 定位值
     * @return
     */
    private static By by(String type, String value) {
        if (type.equals("id")) {
            return By.id(value);
        } else if (type.equals("name")) {
            return By.name(value);
        } else if (type.equals("tagName")) {
            return By.tagName(value);
        } else if (type.equals("linkText")) {
            return By.linkText(value);
        } else if (type.equals("partialLinkText")) {
            return By.partialLinkText(value);
        } else if (type.equals("className")) {
            return By.className(value);
        } else if (type.equals("cssSelector")) {
            return By.cssSelector(value);
        } else {
            return By.xpath(value);
        }
    }

}