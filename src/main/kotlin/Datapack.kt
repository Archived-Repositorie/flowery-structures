import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.world.WorldInitEvent
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths


class Datapack {
    fun Download(url: URL, file: String) {
        url.openStream().use { Files.copy(it, Paths.get(file)) }
    }
}

class FloweryListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onEvent(event: WorldInitEvent) {
        val world = event.world
        println(world)
    }
}