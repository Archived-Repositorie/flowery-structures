import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.world.WorldInitEvent
import org.bukkit.event.world.WorldLoadEvent
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths


class Datapack {
    init {
        FloweryListener()
    }
    fun Download(url: URL, file: String) {
        url.openStream().use { Files.copy(it, Paths.get(file)) }
    }
}

class FloweryListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onInit(event: WorldInitEvent) {
        val world = event.world
        println(world)
    }
}