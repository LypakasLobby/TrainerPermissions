package com.lypaka.trainerpermissions.Listeners;

import com.lypaka.trainerpermissions.TrainerPermissions;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = TrainerPermissions.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartingEvent event) {

        Pixelmon.EVENT_BUS.register(new BattleStartListener());

    }

}
