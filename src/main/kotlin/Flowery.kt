import org.bukkit.plugin.java.JavaPlugin

@Suppress("UNUSED")
class Flowery : JavaPlugin() {
    init {
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
    init {
        Datapack()
    }
    override fun onEnable() {
        this.getServer().getPluginManager().registerEvents(FloweryListener(), this)
    }
}
