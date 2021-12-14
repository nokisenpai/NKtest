package ovh.lumen.NKtest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ovh.lumen.NKtest.data.NKData;
import ovh.lumen.NKtest.enums.InternalMessages;
import ovh.lumen.NKtest.exceptions.SetupException;
import ovh.lumen.NKtest.interfaces.NKplugin;
import ovh.lumen.NKtest.managers.ConfigManager;
import ovh.lumen.NKtest.managers.DatabaseManager;
import ovh.lumen.NKtest.managers.NKcoreAPIManager;
import ovh.lumen.NKtest.registers.CommandRegister;
import ovh.lumen.NKtest.registers.CompleterRegister;
import ovh.lumen.NKtest.registers.ListenerRegister;
import ovh.lumen.NKtest.utils.NKLogger;

public class Main extends JavaPlugin implements NKplugin
{
	@Override
	public void onEnable()
	{
		setup();
	}

	@Override
	public void onDisable()
	{
		clean();
	}

	@Override
	public void setup()
	{
		NKData.PLUGIN = this;
		NKData.PLUGIN_NAME = this.getName();
		NKData.PLUGIN_VERSION = this.getDescription().getVersion();
		NKData.PLUGIN_AUTHOR = this.getDescription().getAuthors().get(0);

		this.saveDefaultConfig();

		// Init
		NKLogger.init(Bukkit.getConsoleSender());
		ConfigManager.init(this.getConfig());

		// Load
		try
		{
			NKcoreAPIManager.load(this);
			ConfigManager.load();
			DatabaseManager.load();
		}
		catch(SetupException e)
		{
			NKLogger.error(e.getMessage());
			disablePlugin();

			return;
		}

		//Register
		ListenerRegister.registerAllListeners(this);
		CommandRegister.registerAllCommands(this);
		CompleterRegister.registerAllCompleters(this);

		displayNKSuccess();
	}

	@Override
	public void clean()
	{
		DatabaseManager.unload();
		ListenerRegister.unregisterAllListeners(this);
	}

	@Override
	public void reload()
	{
		NKLogger.send(InternalMessages.RELOAD_ANNOUNCE.toString());
		clean();
		setup();
	}

	public void disablePlugin()
	{
		getServer().getPluginManager().disablePlugin(this);
	}

	private void displayNKSuccess()
	{
		NKLogger.show("\n"
				+ ChatColor.WHITE + "\u00A0 \u00A0 \u00A0.--.\n"
				+ "\u00A0 \u00A0 \u00A0| \u00A0 '. \u00A0" + ChatColor.GREEN + NKData.PLUGIN_NAME + " v" + NKData.PLUGIN_VERSION + " by " + NKData.PLUGIN_AUTHOR
				+ " - successfully enabled !\n"
				+ ChatColor.WHITE + "'-..___.-'");
	}
}
