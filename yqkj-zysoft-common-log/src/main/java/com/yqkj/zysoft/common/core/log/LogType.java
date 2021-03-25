package com.yqkj.zysoft.common.core.log;


public enum LogType {
     INSERT(0),DELETE(1),UPDATE(2),QUERY(3);

	private LogType(int value){
		this.value=value;
	}
	 private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


}
