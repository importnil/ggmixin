package net.fabricmc.example.config;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.toml.TomlFormat;
import java.util.LinkedHashMap;
import java.util.List;

record ColorDefinition(List<String> items, String color) {
    public Config toConfig() {
        return Config.of(() -> new LinkedHashMap<String, Object>(){
            {
                this.put("items", ColorDefinition.this.items == null ? List.of() : ColorDefinition.this.items);
                this.put("color", ColorDefinition.this.color == null ? "" : ColorDefinition.this.color);
            }
        }, TomlFormat.instance());
    }

    public static ColorDefinition fromConfig(Config config) {
        return new ColorDefinition((List)config.get("items"), (String)config.get("color"));
    }

    public static boolean validateList(Object value) {
        if (value instanceof List) {
            List configList = (List)value;
            for (Object configValue : configList) {
                Config config;
                if (configValue instanceof Config && (config = (Config)configValue).contains("items") && config.contains("color") && config.get("items") instanceof List && config.get("color") instanceof String) continue;
                return false;
            }
            return true;
        }
        return false;
    }
}
