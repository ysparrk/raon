package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;

public class DictionaryRoomResDTO implements Serializable {
	private boolean isRoomIdExist;

	public DictionaryRoomResDTO() {
		super();
	}

	public DictionaryRoomResDTO(boolean isRoomAvailable) {
		this.isRoomIdExist = isRoomAvailable;
	}

	public boolean isRoomIdExist() {
		return isRoomIdExist;
	}

	public void setRoomIdExist(boolean roomIdExist) {
		isRoomIdExist = roomIdExist;
	}

	@Override
	public String toString() {
		return "DictionaryRoomResDTO{" +
			"isRoomIdExist=" + isRoomIdExist +
			'}';
	}
}
