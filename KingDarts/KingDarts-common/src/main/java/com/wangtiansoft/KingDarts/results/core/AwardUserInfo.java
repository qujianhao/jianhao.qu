package com.wangtiansoft.KingDarts.results.core;

public class AwardUserInfo {
	
	private String uuid;
	
	private String headimgurl;
	
	private String username;
	
	private int player_score;
	
	private String create_time;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public int getPlayer_score() {
		return player_score;
	}

	public void setPlayer_score(int player_score) {
		this.player_score = player_score;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "AwardUserInfo [uuid=" + uuid + ", headimgurl=" + headimgurl + ", username=" + username
				+ ", player_score=" + player_score + ", create_time=" + create_time + "]";
	}
}
