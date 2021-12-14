package ovh.lumen.NKtest.data;

import ovh.lumen.NKtest.enums.LogLevel;
import ovh.lumen.NKtest.interfaces.NKplugin;
import ovh.lumen.NKcore.api.data.DBAccess;
import ovh.lumen.NKcore.api.data.NKServer;

public class NKData
{
	public static DBAccess DBACCESS = new DBAccess();
	public static NKServer SERVER_INFO = null;
	public static String PREFIX = null;
	public static LogLevel LOGLEVEL = null;
	public static NKplugin PLUGIN = null;
	public static String PLUGIN_NAME = null;
	public static String PLUGIN_VERSION = null;
	public static String PLUGIN_AUTHOR = null;
}
