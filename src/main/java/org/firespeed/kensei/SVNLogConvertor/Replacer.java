package org.firespeed.kensei.SVNLogConvertor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Replacer
 * 
 * @author BCC
 *
 */
public final class Replacer {
	
	private static Pattern ptn_;
	
	/**
	 * 
	 */
	private Replacer() {
	}
	
	/**
	 * 
	 * @return
	 */
	private static Pattern getPattern() {
		return (ptn_ != null) ? ptn_ : Pattern.compile("([ã€?\\s|,]|\\G)([1-9][0-9]*)([ã€?\\s|,])");
	}
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public static String getReplaceWord(String param) {
		
		String ret = param;
		Matcher matcher2 = getPattern().matcher(param);
		
		while (matcher2.find()) {
			ret = ret.replaceAll(matcher2.group(2), "#" + matcher2.group(2));
		}
		
        return (ret.isEmpty()) ? param : ret;
	}
}
