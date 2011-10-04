package jp.co.bcc.SVNLogConvertor;

import junit.framework.Assert;

import org.junit.Test;


public class ReplacerTest {

	@Test
	public void getReplaceWordTest_1() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 1 修正"),
										"refs #1 修正");
	}
	
	@Test
	public void getReplaceWordTest_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676 修正"),
										"refs #676 修正");
	}
	
	@Test
	public void getReplaceWordTest_3() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676,683 修正"),
										"refs #676,#683 修正");
	}
	@Test
	public void getReplaceWordTest_4() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676,683,684 修正"),
										"refs #676,#683,#684 修正");
	}
	
	@Test
	public void getReplaceWordTest_5() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676, 683, 684 修正"),
										"refs #676, #683, #684 修正");
	}
	
	@Test
	public void getReplaceWordTest_6() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676 683 修正"),
										"refs #676 #683 修正");
	}

	@Test
	public void getReplaceWordTest_7() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 665, 666　　修正"),
										"refs #665, #666　　修正");
	}

	@Test
	public void getReplaceWordTest_8() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 665, refs 666　　修正"), 
										"refs #665, refs #666　　修正");
	}

	@Test
	public void getReplaceWordTest_WithReturn_16() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676" + getReturnCd() + "修正"),
										"refs #676" + getReturnCd() + "修正");
	}
	
	@Test
	public void getReplaceWordTest_WithReturn_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 665, 666" + getReturnCd() + "修正"), 
										"refs #665, #666" + getReturnCd() + "修正");
	}

	@Test
	public void getReplaceWordTest_WithReturn_3() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 128,129" + getReturnCd() + "修正"), 
										"refs #128,#129" + getReturnCd() + "修正");
	}
	@Test
	public void getReplaceWordTest_WithReturn_4() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs 676 683" + getReturnCd() + "修正"),
										"refs #676 #683" + getReturnCd() + "修正");
	}
	
	@Test
	public void getNotReplaceWordTest_1() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665 修正"), 
										"refs #665 修正");
	}

	@Test
	public void getNotReplaceWordTest_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665, #666 修正"), 
										"refs #665, #666 修正");
	}

	@Test
	public void getNotReplaceWordTest_WithReturn_1() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665" + getReturnCd() + "修正"), 
										"refs #665" + getReturnCd() + "修正");
	}
	@Test
	public void getNotReplaceWordTest_WithReturn_2() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #665, #666" + getReturnCd() + "修正"), 
										"refs #665, #666" + getReturnCd() + "修正");
	}

	@Test
	public void getNotReplaceWordTest_WithReturn_3() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #128,#129" + getReturnCd() + "修正"), 
										"refs #128,#129" + getReturnCd() + "修正");
	}
	@Test
	public void getNotReplaceWordTest_WithReturn_4() {
		Assert.assertEquals(
				Replacer.getReplaceWord("refs #676 #683" + getReturnCd() + "修正"),
										"refs #676 #683" + getReturnCd() + "修正");
	}
	private String getReturnCd() {
		return System.getProperty("line.separator");
	}
}
