package org.firespeed.kensei.SVNLogConvertor;

import junit.framework.Assert;

import org.firespeed.kensei.SVNLogConvertor.Replacer;
import org.junit.Test;


public class ReplacerTest {

	@Test
	public void getReplaceWordTest_1() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 1 add func"),
										"refs #1 add func");
	}
	
	@Test
	public void getReplaceWordTest_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676 add func"),
										"refs #676 add func");
	}
	
	@Test
	public void getReplaceWordTest_3() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676,683 add func"),
										"refs #676,#683 add func");
	}
	@Test
	public void getReplaceWordTest_4() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676,683,684 add func"),
										"refs #676,#683,#684 add func");
	}
	
	@Test
	public void getReplaceWordTest_5() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676, 683, 684 add func"),
										"refs #676, #683, #684 add func");
	}
	
	@Test
	public void getReplaceWordTest_6() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676 683 add func"),
										"refs #676 #683 add func");
	}

	@Test
	public void getReplaceWordTest_7() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 665, 666　add func"),
										"refs #665, #666　add func");
	}

	@Test
	public void getReplaceWordTest_8() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 665, refs 666　add func"), 
										"refs #665, refs #666　add func");
	}

	@Test
	public void getReplaceWordTest_WithReturn_16() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676" + getReturnCd() + "add func"),
										"refs #676" + getReturnCd() + "add func");
	}
	
	@Test
	public void getReplaceWordTest_WithReturn_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 665, 666" + getReturnCd() + "add func"), 
										"refs #665, #666" + getReturnCd() + "add func");
	}

	@Test
	public void getReplaceWordTest_WithReturn_3() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 128,129" + getReturnCd() + "add func"), 
										"refs #128,#129" + getReturnCd() + "add func");
	}
	@Test
	public void getReplaceWordTest_WithReturn_4() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676 683" + getReturnCd() + "add func"),
										"refs #676 #683" + getReturnCd() + "add func");
	}
	
	@Test
	public void getNotReplaceWordTest_1() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665 add func"), 
										"refs #665 add func");
	}

	@Test
	public void getNotReplaceWordTest_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665, #666 add func"), 
										"refs #665, #666 add func");
	}

	@Test
	public void getNotReplaceWordTest_WithReturn_1() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665" + getReturnCd() + "add func"), 
										"refs #665" + getReturnCd() + "add func");
	}
	@Test
	public void getNotReplaceWordTest_WithReturn_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665, #666" + getReturnCd() + "add func"), 
										"refs #665, #666" + getReturnCd() + "add func");
	}

	@Test
	public void getNotReplaceWordTest_WithReturn_3() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #128,#129" + getReturnCd() + "add func"), 
										"refs #128,#129" + getReturnCd() + "add func");
	}
	@Test
	public void getNotReplaceWordTest_WithReturn_4() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #676 #683" + getReturnCd() + "add func"),
										"refs #676 #683" + getReturnCd() + "add func");
	}
	private String getReturnCd() {
		return System.getProperty("line.separator");
	}
}
