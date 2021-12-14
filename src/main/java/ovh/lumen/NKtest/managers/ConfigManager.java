package ovh.lumen.NKtest.managers;

import org.bukkit.configuration.file.FileConfiguration;
import ovh.lumen.NKtest.data.NKData;
import ovh.lumen.NKtest.enums.InternalMessages;
import ovh.lumen.NKtest.enums.LogLevel;
import ovh.lumen.NKtest.utils.NKLogger;
import ovh.lumen.NKcore.api.data.DBAccess;

public final class ConfigManager
{
	private ConfigManager() {}

	private static FileConfiguration config = null;

	public static void init(FileConfiguration config)
	{
		ConfigManager.config = config;
	}

	public static void load()
	{
		boolean useNKcoreAccess = config.getBoolean("use-nkcore-access", true);

		if(useNKcoreAccess)
		{
			NKData.DBACCESS = NKcoreAPIManager.nKcoreAPI.getDBAccess();
		}
		else
		{
			DBAccess dbAccess = new DBAccess();
			dbAccess.setHost(config.getString("host"));
			dbAccess.setPort(config.getInt("port"));
			dbAccess.setDbName(config.getString("dbName"));
			dbAccess.setUser(config.getString("user"));
			dbAccess.setPassword(config.getString("password"));

			NKData.DBACCESS = dbAccess;
		}

		NKData.PREFIX = config.getString("table-prefix", NKData.PLUGIN_NAME + "_");

		NKData.SERVER_INFO = NKcoreAPIManager.nKcoreAPI.getNKServer();

		try
		{
			NKData.LOGLEVEL = LogLevel.valueOf(config.getString("log-level", "LOG").toUpperCase());
		}
		catch(IllegalArgumentException e)
		{
			NKLogger.error(InternalMessages.CONFIG_KEY_LOGLEVEL_BAD_VALUE.toString());
			NKData.LOGLEVEL = LogLevel.LOG;
		}

		NKLogger.setLogLevel(NKData.LOGLEVEL);
	}
}
