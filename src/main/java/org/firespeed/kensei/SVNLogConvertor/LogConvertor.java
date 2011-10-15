package org.firespeed.kensei.SVNLogConvertor;

import java.util.Collection;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.firespeed.kensei.SVNLogConvertor.Exception.LogConvertorConnectException;
import org.firespeed.kensei.SVNLogConvertor.Exception.LogConvertorException;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNRevisionProperty;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class LogConvertor {

	private final static Logger logger_ = LogFactory.getLogger(LogConvertor.class);
	private static SVNRepository repository_;
	private Boolean logOnlyMode_ = false;
	
	/**
	 * 
	 * @throws Exception
	 */
	public LogConvertor() throws Exception {
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {
		logger_.debug("init");
		
		Setting setting = Setting.getInstance();
		setting.init("/setting.xml");
		String svnUrl = setting.getStringValue("/LogConvertor/svnURL").replaceAll("\\\\", "/");
		String userName = setting.getStringValue("/LogConvertor/svnUserName");
		String password = setting.getStringValue("/LogConvertor/svnPassword");
		String protocol = setting.getStringValue("/LogConvertor/svnProtocol");
		logOnlyMode_ = setting.getBoolValue("/LogConvertor/logOnlyMode");
		
		validateURL(svnUrl);
		
		try {
			if (StringUtils.equalsIgnoreCase("file", protocol)) {
				logger_.info("Initializing local repository factory");
				FSRepositoryFactory.setup();
			} else if (StringUtils.equalsIgnoreCase("http", protocol)) {
				logger_.info("Initializing http repository factory");
				DAVRepositoryFactory.setup();
			} else if (StringUtils.equalsIgnoreCase("svn", protocol)) {
				logger_.info("Initializing svn repository factory");
				SVNRepositoryFactoryImpl.setup();
			} else {
				throw new LogConvertorException("Unknown protocol " + protocol);
			}
			repository_ = getRepos(svnUrl, userName, password);
		} catch (SVNException e) {
			StringBuffer message = new StringBuffer(
					"Erreur connecting to svn server : ")
					.append(e.getMessage());
			logger_.info(message, e);
			throw new LogConvertorConnectException(message.toString());
		}
	}

	/**
	 * 
	 * @param url
	 * @throws LogConvertorConnectException
	 */
	private void validateURL(String url) throws LogConvertorConnectException {
		try {
			@SuppressWarnings("unused")
			SVNURL svnUrl = SVNURL.parseURIDecoded(url);
		} catch (SVNException e) {
			StringBuffer message = new StringBuffer("Error creation SVNURL : ")
					.append(e.getMessage());
			logger_.error(message, e);
			throw new LogConvertorConnectException(message.toString());
		}
	}
	
	/**
	 * <pre>
	 *   proxyがある�?合�?serverに設定しておくこと
	 * </pre>
	 * @param url
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws SVNException
	 */
	private SVNRepository getRepos(String url, String userName, String passWord) throws SVNException {
		SVNURL svnUrl = SVNURL.parseURIDecoded(url);
		SVNRepository repos = SVNRepositoryFactory.create(svnUrl);
		if (userName != null) {
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
							userName, passWord == null ? "" : passWord);
			repos.setAuthenticationManager(authManager);
		}
		return repos;
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void entry() throws Exception {
		logger_.debug("------------------------------------START------------------------------------");
		
		final long startnum = 1;
		//final long lastnum = 50;
		final long lastnum = repository_.getLatestRevision();
		logger_.debug("Convert revision " + String.valueOf(startnum)+ " to " + String.valueOf(lastnum));
		
		try {
			@SuppressWarnings("unchecked")
			Collection<SVNLogEntry> revisions = (Collection<SVNLogEntry>) repository_
					.log(new String[] { "" }, null, startnum, lastnum, true, true);
			
			String msg = "";
			for (SVNLogEntry revision : revisions) {
				// please customize it
				msg = Replacer.getReplaceWord(revision.getMessage());
				logger_.debug("revision:" + String.valueOf(revision.getRevision()));
				logger_.info("    before -> " + revision.getMessage());
				logger_.info("    after  -> " + msg);
				
				if (!logOnlyMode_)
					repository_.setRevisionPropertyValue(
							revision.getRevision(), 
							SVNRevisionProperty.LOG, 
							SVNPropertyValue.create(msg));
			}
			
		} catch (SVNException e) {
			throw new LogConvertorException(e);
		} finally {
			repository_.closeSession();
		}
		
		logger_.debug("------------------------------------END------------------------------------");
	}
}
