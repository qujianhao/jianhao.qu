package com.wangtiansoft.KingDarts.results.core;

public class NineGameResult {
//    private String id;
    /**
     * 俱乐部名称
     */
    private   Integer player_score;


	private Integer player_order;
    
    private String player_orderRate;
    


    /**
     * 俱乐部编号
     */
    private String username;

    /**
     * 俱乐部名称
     */
    private String headimgurl;

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public Integer getPlayer_score() {
		return player_score;
	}

	public void setPlayer_score(Integer player_score) {
		this.player_score = player_score;
	}

	public Integer getPlayer_order() {
		return player_order;
	}

	public void setPlayer_order(Integer player_order) {
		this.player_order = player_order;
	}

	public String getPlayer_orderRate() {
		return player_orderRate;
	}

	public void setPlayer_orderRate(String player_orderRate) {
		this.player_orderRate = player_orderRate;
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

	@Override
	public String toString() {
		return "NineGameResult [player_score=" + player_score + ", player_order=" + player_order + ", player_orderRate="
				+ player_orderRate + ", username=" + username + ", headimgurl=" + headimgurl + "]";
	}
	
}
