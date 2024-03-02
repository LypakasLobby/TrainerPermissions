package com.lypaka.trainerpermissions.Listeners;

import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.lypaka.lypakautils.WorldStuff.WorldMap;
import com.lypaka.trainerpermissions.ConfigGetters;
import com.pixelmonmod.pixelmon.api.events.battles.BattleStartedEvent;
import com.pixelmonmod.pixelmon.battles.controller.BattleController;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.TrainerParticipant;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Map;

public class BattleStartListener {

    @SubscribeEvent
    public void onPreBattle (BattleStartedEvent.Pre event) {

        TrainerParticipant tp;
        PlayerParticipant pp;
        BattleController bc = event.getBattleController();

        if (bc.participants.get(0) instanceof TrainerParticipant && bc.participants.get(1) instanceof PlayerParticipant) {

            tp = (TrainerParticipant) bc.participants.get(0);
            pp = (PlayerParticipant) bc.participants.get(1);

        } else if (bc.participants.get(0) instanceof PlayerParticipant && bc.participants.get(1) instanceof TrainerParticipant) {

            tp = (TrainerParticipant) bc.participants.get(1);
            pp = (PlayerParticipant) bc.participants.get(0);

        } else {

            return;

        }

        ServerPlayerEntity player = pp.player;
        NPCTrainer trainer = tp.trainer;
        String location = WorldMap.getWorldName(player) + "," + trainer.blockPosition().getX() + "," + trainer.blockPosition().getY() + "," + trainer.blockPosition().getZ();
        if (ConfigGetters.npcMap.containsKey(location)) {

            Map<String, List<String>> stuffMap = ConfigGetters.npcMap.get(location);
            boolean hasPermissionPasses = true;
            if (stuffMap.containsKey("Have-Permissions")) {

                List<String> permissions = stuffMap.get("Have-Permissions");
                for (String p : permissions) {

                    if (!PermissionHandler.hasPermission(player, p)) {

                        hasPermissionPasses = false;
                        break;

                    }

                }

            }
            boolean notHavePermissionPasses = true;
            if (stuffMap.containsKey("Not-Have-Permissions")) {

                List<String> permissions = stuffMap.get("Not-Have-Permissions");
                for (String p : permissions) {

                    if (PermissionHandler.hasPermission(player, p)) {

                        notHavePermissionPasses = false;
                        break;

                    }

                }

            }
            if (!hasPermissionPasses || !notHavePermissionPasses) {

                event.setCanceled(true);
                if (!hasPermissionPasses) {

                    if (stuffMap.containsKey("Have-Command")) {

                        List<String> commands = stuffMap.get("Have-Command");
                        for (String c : commands) {

                            player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(), c.replace("%player%", player.getName().getString()));

                        }

                    }

                }
                if (!notHavePermissionPasses) {

                    if (stuffMap.containsKey("Not-Have-Command")) {

                        List<String> commands = stuffMap.get("Not-Have-Command");
                        for (String c : commands) {

                            player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(), c.replace("%player%", player.getName().getString()));

                        }

                    }

                }

            }

        }

    }

}
