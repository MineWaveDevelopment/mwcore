package de.minewave.mwcore.chat;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
@AllArgsConstructor(staticName = "of")
public class AdvancedChatMessage {

	@Getter @Setter
	private String identifier;
	
	@Getter @Setter
	private UUID sender;
	
	@Getter @Setter
	private long timestamp;
	
	@Getter @Setter
	private String message;
	
}
