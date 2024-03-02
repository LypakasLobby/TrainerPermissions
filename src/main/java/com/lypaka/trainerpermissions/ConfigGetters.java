package com.lypaka.trainerpermissions;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static Map<String, Map<String, List<String>>> npcMap;

    public static void load() throws ObjectMappingException {

        npcMap = TrainerPermissions.configManager.getConfigNode(0, "NPCs").getValue(new TypeToken<Map<String, Map<String, List<String>>>>() {});

    }

}
