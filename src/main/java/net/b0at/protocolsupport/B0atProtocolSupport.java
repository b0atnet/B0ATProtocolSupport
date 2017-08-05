package net.b0at.protocolsupport;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.api.remapper.BlockRemapperControl;
import protocolsupport.api.remapper.ItemRemapperControl;

/**
 * Created by Jordin on 7/22/2017.
 * Jordin is still best hacker.
 */
public class B0atProtocolSupport extends JavaPlugin implements Listener {
    private BlockRemapperControl blockRemapper;
    private ItemRemapperControl itemRemapper;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        for (ProtocolVersion version : ProtocolVersion.getAllBefore(ProtocolVersion.MINECRAFT_1_11_1)) {
            remapVersion(version);
        }
    }

    private void remapVersion(ProtocolVersion version) {
        blockRemapper = new BlockRemapperControl(version);
        itemRemapper = new ItemRemapperControl(version);
        blockRemapper.setRemap(Material.CONCRETE, Material.STAINED_CLAY);
        itemRemapper.setRemap(Material.CONCRETE, Material.STAINED_CLAY);

        remap(Material.CONCRETE, 0, Material.QUARTZ_BLOCK, 0);

        for (int data = 7; data <= 11; data++) {
            remap(Material.CONCRETE, data, Material.WOOL, data);
        }

        remap(Material.CONCRETE, 15, Material.COAL_BLOCK, 0);
    }

    private void remap(Material fromMaterial, int fromData, Material toMaterial, int toData) {
        blockRemapper.setRemap(fromMaterial, fromData, toMaterial, toData);
        itemRemapper.setRemap(fromMaterial, fromData, toMaterial, toData);
    }
}
