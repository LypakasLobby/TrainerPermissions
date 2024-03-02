package com.lypaka.trainerpermissions.Commands;

import com.lypaka.trainerpermissions.TrainerPermissions;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = TrainerPermissions.MOD_ID)
public class TrainerPermissionsCommand {

    public static final List<String> ALIASES = Arrays.asList("trainerpermissions", "tperms", "tpermissions", "trainerperms");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new ReloadCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
