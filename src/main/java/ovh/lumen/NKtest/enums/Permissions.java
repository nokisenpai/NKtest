package ovh.lumen.NKtest.enums;

import ovh.lumen.NKtest.data.NKData;

public enum Permissions
{
	USER("user"),
	ADMIN("admin"),

	ROOT_CMD(""),
	ROOT_RELOAD_CMD(".reload");

	private final String value;

	Permissions(String value)
	{
		this.value = value;
	}

	public String toString()
	{
		return NKData.PLUGIN_NAME + this.value;
	}
}
