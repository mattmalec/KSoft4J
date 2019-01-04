package net.explodingbush.ksoftapi.enums;

public enum Routes {
	
	BAN_ADD("https://api.ksoft.si/bans/add"),
	BAN_INFO("https://api.ksoft.si/bans/info?user="),
	BAN_LIST("https://api.ksoft.si/bans/list?per_page="),
	BAN_BULK("https://api.ksoft.si/bans/bulkcheck?more_info=yes"),
	IMAGE("https://api.ksoft.si/images/random-image?tag=%s&nsfw=%s"),
	WIKIHOW("https://api.ksoft.si/images/random-wikihow?nsfw=%s"),
	REDDIT("https://api.ksoft.si/images/"),
	LYRICS("https://api.ksoft.si/lyrics/search?q=%s&text_only=%s&limit=%d"),
	KUMOWEATHER("https://api.ksoft.si/kumo/weather/%s?q=%s&units=%s&lang=%s"),
	KUMOIP("https://api.ksoft.si/kumo/geoip?ip=%s"),
	MUSICRECOMMEND("https://api.ksoft.si/music/recommendations");

	  
	private final String route;
	  
	Routes(String route){
	  this.route = route;
	}
	public String toString(){
	  return this.route;
	}
}
