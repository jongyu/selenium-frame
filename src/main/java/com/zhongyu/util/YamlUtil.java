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

    /**
     * 根据父节点获取子节点
     *
     * @param node 父节点
     * @param key  子节点
     * @return
     */
    public String get(String node, String key) {
        String value = "未找到元素!";
        YamlConfig yamlConfig = new YamlConfig();
        yamlConfig.setAllowDuplicates(false);
        YamlReader reader = new YamlReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.fileName + ".yml")), yamlConfig);
        try {
            Object object = reader.read();
            Map<String, Object> map = (Map) object;
            if (StringUtils.isNotEmpty(node)) {
                Map<String, String> nodes = (Map) map.get(node);
                for (Map.Entry<String, String> entry : nodes.entrySet()) {
                    if (entry.getKey().equals(key)) {
                        value = entry.getValue();
                    }
                }
            } else {
                return String.valueOf(map.get(key));
            }
        } catch (YamlException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取yaml
     *
     * @param key 节点
     * @return
     */
    public String get(String key) {
        return get(null, key);
    }

}