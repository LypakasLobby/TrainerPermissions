package com.lypaka.trainerpermissions;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.minecraftforge.fml.common.Mod;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("trainerpermissions")
public class TrainerPermissions {

    public static final String MOD_ID = "trainerpermissions";
    public static final String MOD_NAME = "TrainerPermissions";
    public static final Logger logger = LogManager.getLogger(MOD_NAME);
    public static BasicConfigManager configManager;

    public TrainerPermissions() throws IOException, ObjectMappingException {

        Path dir = ConfigUtils.checkDir(Paths.get("./config/trainerpermissions"));
        String[] files = new String[]{"trainerpermissions.conf"};
        configManager = new BasicConfigManager(files, dir, TrainerPermissions.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();

    }

}
