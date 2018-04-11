package com.zhongyu.util;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuChan on 02/26/2017.
 */
public class YamlUtil {

    private String fileName;
    private static Map<String, YamlUtil> instanceMap = new HashMap<>();

    public YamlUtil(String fileName) {
        this.fileName = fileName;
    }

    public static YamlUtil getInstance(String fileName) {
        if (null != instanceMap.get(fileName)) return instanceMap.get(fileName);
        YamlUtil instance = new YamlUtil(fileName);
        instanceMap.put(fileName, instance);
        return instance;
    }

    public String get(String root) {
        return get(root, "", "");
    }

    public String get(String root, String parent) {
        return get(root, parent, "");
    }

    public String get(String root, String parent, String child) {
        Map<String, Object> yaml = getYaml();
        if (StringUtils.isNotEmpty(parent) || StringUtils.isNotBlank(parent)) {
            Map<String, Object> maps = (Map) yaml.get(root);
            if (StringUtils.isNotEmpty(parent) || StringUtils.isNotBlank(parent)) {
                for (Map.Entry<String, Object> entry : maps.entrySet()) {
                    if (entry.getKey().equals(parent)) {
                        if (StringUtils.isNotEmpty(child) || StringUtils.isNotBlank(child)) {
                            Map<String, Object> result = (Map) entry.getValue();
                            return result.get(child).toString();
                        }
                        return entry.getValue().toString();
                    }
                }
            }
        } else {
            return yaml.get(root).toString();
        }
        return null;
    }

    public Map<String, Object> getYaml() {
        YamlConfig yamlConfig = new YamlConfig();
        yamlConfig.setAllowDuplicates(false);
        YamlReader reader = new YamlReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.fileName + ".yml")), yamlConfig);
        Object object = null;
        try {
            object = reader.read();
        } catch (YamlException e) {
            e.printStackTrace();
        }
        return (Map<String, Object>) object;
    }

    public static void main(String[] args) {
        System.out.println(YamlUtil.getInstance("config").get("browser"));
    }

}