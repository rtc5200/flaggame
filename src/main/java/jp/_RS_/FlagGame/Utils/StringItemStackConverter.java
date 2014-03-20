package jp._RS_.FlagGame.Utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class StringItemStackConverter {
	//書式:アイテムiD,データ@(エンチャID,レベル)/個数
	//書式   ID:メタ@エンチャID
	//書式 (ID:メタ,エンチャID,エンチャレベル;個数)
	public static ArrayList<ItemStack> convertToList(String string)
	{
		ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		String original = string;
		Material material = null;
		Byte data = 0;
		int amount = 1;
		Enchantment ec = null;
		int eclevel = 0;
		if(original.startsWith("<"))original = original.substring(1);
		for(String s : original.split(">"))
		{
			if(s.contains("<"))s = s.substring(1);
			result.add(convert(s));
		}
		return result;
	}
	//書式 (ID:メタ,エンチャID,エンチャレベル;個数)
	public static ItemStack convert(String string)
	{
		String original = string;
		String[] els = original.split(",");
		Material m = null;
		Byte data = null;
		int amount = 1;
		Enchantment ec = null;
		int eclevel = 1;
		if(original.contains(";"))
		{
			amount = Integer.parseInt(original.substring(original.indexOf(";") + 1));
			original = original.replace(";" + amount , "");
			els = original.split(",");
		}
		switch(els.length)
		{
		case 1://(ID:メタ) or (ID)
			String s_item = els[0];
			if(s_item.contains(":"))
			{
				data = Byte.parseByte(s_item.substring(els[0].indexOf(":") + 1));
				s_item = s_item.substring(0,s_item.indexOf(":"));
			}
			m = Material.getMaterial(Integer.parseInt(s_item));
			break;
		case 2://(ID:メタ,エンチャID)
			String s2_item = els[0];
			String s2_enc = els[1];
			if(s2_item.contains(":"))
			{
				data = Byte.parseByte(s2_item.substring(els[0].indexOf(":") + 1));
				s2_item = s2_item.substring(0,s2_item.indexOf(":"));
			}
			m =  Material.getMaterial(Integer.parseInt(s2_item));
			if(Enchantment.getById(Integer.parseInt(s2_enc) )!= null)ec = Enchantment.getById(Integer.parseInt(s2_enc));
			if(Enchantment.getByName(s2_enc) != null)ec = Enchantment.getByName(s2_enc);
			break;
		case 3://(ID:メタ,エンチャID,レベル)
			String s3_item = els[0];
			String s3_enc = els[1];
			String s3_encle = els[2];
			if(s3_item.contains(":"))
			{
				data = Byte.parseByte(s3_item.substring(els[0].indexOf(":") + 1));
				s3_item = s3_item.substring(0,s3_item.indexOf(":"));
			}
			m =  Material.getMaterial(Integer.parseInt(s3_item));
			if(Enchantment.getById(Integer.parseInt(s3_enc) )!= null)ec = Enchantment.getById(Integer.parseInt(s3_enc));
			if(Enchantment.getByName(s3_enc) != null)ec = Enchantment.getByName(s3_enc);
			eclevel = Integer.parseInt(s3_encle);
			break;
		}
		ItemStack result = null;
		if(data != null)
		{
			result = new ItemStack(m,amount,(short)0,data);
		}else{
			result = new ItemStack(m,amount);
		}
		if(ec != null)
		{
			result.addEnchantment(ec,eclevel);
		}
		return result;
	}

}
