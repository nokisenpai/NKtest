package ovh.lumen.NKtest.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import ovh.lumen.NKtest.data.NKcoreEventAPIimpl;
import ovh.lumen.NKtest.enums.InternalMessages;
import ovh.lumen.NKtest.exceptions.SetupException;
import ovh.lumen.NKcore.api.NKcoreAPI;
import ovh.lumen.NKcore.api.NKcoreEventAPI;

public final class NKcoreAPIManager
{
	private NKcoreAPIManager() {}

	public static NKcoreAPI nKcoreAPI = null;

	public static void load(JavaPlugin plugin) throws SetupException
	{
		if(Bukkit.getServer().getPluginManager().getPlugin("NKcore") == null)
		{
			throw new SetupException(InternalMessages.NKCORE_MISSING.toString());
		}

		RegisteredServiceProvider<NKcoreAPI> rsp = Bukkit.getServer().getServicesManager().getRegistration(NKcoreAPI.class);

		if(rsp == null)
		{
			throw new SetupException(InternalMessages.NKCORE_CANT_GET_API.toString());
		}

		nKcoreAPI = rsp.getProvider();

		Bukkit.getServicesManager().register(NKcoreEventAPI.class, new NKcoreEventAPIimpl(), plugin, ServicePriority.Normal);
	}
}
