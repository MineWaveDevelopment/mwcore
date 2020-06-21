package de.minewave.mwcore.chat;

import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class ChatMentionedPlayer implements Comparable<ChatMentionedPlayer> {

	@Getter
	private Integer startIndex;
	
	@Getter
	private String name;
	
	public ChatMentionedPlayer(int startIndex, String name) {
		this.startIndex = startIndex;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ChatMentionedPlayer[" + startIndex + ", " + name + "]";
	}

	@Override
	public int compareTo(ChatMentionedPlayer o) {
		return o.getStartIndex().compareTo(startIndex);
	}

	
}
