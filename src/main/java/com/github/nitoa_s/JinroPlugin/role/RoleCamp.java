package com.github.nitoa_s.JinroPlugin.role;

public enum RoleCamp {
	VILLAGE_CAMP("村人陣営"),
	WEREWOLF_CAMP("人狼陣営"),
	FOX_CAMP("妖狐陣営")
	;
	private String campName;
	private RoleCamp(String campName) {
		this.campName = campName;
	}

	public String getCampName() {
		return campName;
	}
}
