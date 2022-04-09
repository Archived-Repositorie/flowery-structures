package io.github.JustFoxx

import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory

val Logger = LoggerFactory.getLogger("FloweryStructures")

@Suppress("unused")
fun init() {
    Structure().registerStructureFeatures()
    val list = listOf("OwO", "UwU", "Flowewy!", "Magic", "Fabric > Forge", "Quilt, when?", "Fox or Umbreon")
    val supported = mapOf("croptopia" to "flowerycrop")
    Logger.info(list.random())
    for(mod in supported) {
        if (FabricLoader.getInstance().isModLoaded(mod.key) && !FabricLoader.getInstance().isModLoaded(mod.value)) {
            Logger.warn("Mod ${mod.key} is loaded but ${mod.key} support mod is not loaded")
        } else if(FabricLoader.getInstance().isModLoaded(mod.value)) {
            Logger.info("Support mod ${mod.value} has been loaded")
        }
    }
}

