package de.minewave.mwcore.head;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import de.minewave.mwcore.util.Reflections;
import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class CustomHead {

	@Expose
	@Getter
	private String name;
	
	@Expose
	@Getter @Setter
	private String headResource;
	
	@Expose
	@Getter @Setter
	private String displayname;
	
	@Expose
	@Getter @Setter
	private List<String> lore;
	
	public CustomHead(String name, String headResource) {
		this.name = name;
		this.headResource = headResource;
		this.displayname = name;
		this.lore = Lists.newArrayList();
	}
	
	public static ItemStack getCustomHead(String textureId, String displayname) {
		ItemStack head = getCustomHead(textureId);
		
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(displayname);
		head.setItemMeta(meta);
		
		return head;
	}
	
    public static ItemStack getCustomHead(String textureId) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/" + textureId).getBytes());
        propertyMap.put("textures", new Property("textures", new String(encodedData)));
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        Reflections.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
        head.setItemMeta(headMeta);
        return head;
    }
	
}
