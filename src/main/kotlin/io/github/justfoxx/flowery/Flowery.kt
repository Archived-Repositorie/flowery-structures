package io.github.justfoxx.flowery

import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Mod(Flowery.ID)
object Flowery {
    const val ID = "flowery"
    val logger = LoggerFactory.getLogger("FloweryStructures")
    val list = listOf("OwO", "UwU", "Flowewy!", "Magic", "Fabric > Forge", "Quilt, when?", "Fox or Umbreon")
    val supported = mapOf("croptopia" to "flowerycrop")
    init {
        logger.info(list.random())
        for(mod in supported) {
            if (ModList.get().isLoaded(mod.key) && !ModList.get().isLoaded(mod.value)) {
                logger.warn("Mod ${mod.key} is loaded but ${mod.key} support mod is not loaded")
            } else if(ModList.get().isLoaded(mod.value)) {
                logger.info("Support mod ${mod.value} has been loaded")
            }
        }
    }
}
