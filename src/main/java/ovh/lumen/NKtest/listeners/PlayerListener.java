package ovh.lumen.NKtest.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener
{
	public PlayerListener()
	{

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerJoinEvent(final PlayerJoinEvent event)
	{

	}

	@EventHandler
	public void onPlayerQuitEvent(final PlayerQuitEvent event)
	{

	}
}