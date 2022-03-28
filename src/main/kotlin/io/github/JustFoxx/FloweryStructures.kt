package io.github.JustFoxx

import net.fabricmc.loader.api.FabricLoader
import org.slf4j.Logger
import org.slf4j.LoggerFactory



@Suppress("unused")
fun init() {
    val logger = LoggerFactory.getLogger("FloweryStructures")
    val list = listOf("OwO", "UwU", "Flowewy!", "Magic", "Fabric > Forge", "Quilt, when?", "Fox or Umbreon")
    logger.info(list.random())
    val supported = mapOf("croptopia" to "flowerycrop")
    for(mod in supported) {
        if (FabricLoader.getInstance().isModLoaded(mod.key) && !FabricLoader.getInstance().isModLoaded(mod.value)) {
            logger.warn("Mod ${mod.key} is loaded but ${mod.key} support mod is not loaded")
        } else if(FabricLoader.getInstance().isModLoaded(mod.value)) {
            logger.info("Support mod ${mod.value} has been loaded")
        }
    }
}

