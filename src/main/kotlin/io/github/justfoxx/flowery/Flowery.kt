package io.github.justfoxx.flowery

import net.minecraftforge.fml.common.Mod
@Mod(Flowery.ID)
object Flowery {
    const val ID = "flowery"
    val list =
        listOf(
                "OwO",
                "UwU",
                "Flowewy!",
                "Magic",
                "Fabric > Forge",
                "Quilt, when?",
                "Fox or Umbreon"
        )

    println(list.random())
}
