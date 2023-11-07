package com.arch.raon.domain.dictionary.dto.response;

import java.io.Serializable;

public class DictionaryRoomResDTO implements Serializable {
	private boolean isRoomAvailable;

	public DictionaryRoomResDTO() {
		super();
	}

	public DictionaryRoomResDTO(boolean isRoomAvailable) {
		this.isRoomAvailable = isRoomAvailable;
	}

	public boolean isRoomAvailable() {
		return isRoomAvailable;
	}

	public void setRoomAvailable(boolean roomAvailable) {
		isRoomAvailable = roomAvailable;
	}
}
