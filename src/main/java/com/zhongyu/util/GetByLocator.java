package com.zhongyu.util;

import org.openqa.selenium.By;

/**
 * Created by YuChan on 02/26/2017.
 */
public class GetByLocator {

    public static By getLocator(String root) {
        String locator = YamlUtil.getInstance("element").get(root);
        return by(locator);
    }

    public static By getLocator(String root, String parent) {
        String locator = YamlUtil.getInstance("element").get(root, parent);
        return by(locator);
    }

    public static By getLocator(String root, String parent, String child) {
        String locator =YamlUtil.getInstance("element").get(root, parent, child);
        return by(locator);
    }

    private static By by(String locator) {
        String type = locator.split(">")[0];
        String value = locator.split(">")[1];
        switch (type) {
            case "id":
                return By.id(value);
            case "name":
                return By.name(value);
            case "tagName":
                return By.tagName(value);
            case "linkText":
                return By.linkText(value);
            case "partialLinkText":
                return By.partialLinkText(value);
            case "className":
                return By.className(value);
            case "cssSelector":
                return By.cssSelector(value);
            default:
                return By.xpath(value);
        }
    }

}