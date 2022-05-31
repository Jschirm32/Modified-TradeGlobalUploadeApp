package com.astralbrands.sage.x3.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/*
	Sftp process class to create a session
	for uploading the data from the databases to
	TradeGlobal
 */
@Component
public class SftpProcessor implements Processor {

	@Value("${ftp.server.targetpath}")
	private String targetPath;

	@Value("${ftp.server.sourcepath}")
	private String sourcePath;

	@Value("${ftp.server.user}")
	private String userName;

	@Value("${ftp.server.ftpHost}")
	private String host;

	@Value("${ftp.server.password}")
	private String password;

	Logger log = LoggerFactory.getLogger(SftpProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		String fileName = exchange.getProperty("FILE_NAME", String.class);
		uploadSftpFromPath(sourcePath+fileName);
	}

	private ChannelSftp setupJsch() throws JSchException {

		JSch jsch = new JSch();
		Session jschSession = jsch.getSession(userName, host);
		java.util.Properties props = new java.util.Properties();
		props.put("StrictHostKeyChecking", "no");
		jschSession.setConfig(props);
		jschSession.setPassword(password);
		jschSession.connect();
		log.info("Connected to FTP host : " + host);
		return (ChannelSftp) jschSession.openChannel("sftp");
	}

	public boolean uploadSftpFromPath(String sourceFile) throws SftpException {

		log.info("uploading file: " + sourceFile + ", sftp file location :" + targetPath);
		ChannelSftp channelSftp = null;
		try {
			channelSftp = setupJsch();
		} catch (JSchException e) {
			e.printStackTrace();
			log.error("Error while getting channel for host :" + host);
			throw new SftpException(0, "Error while getting channel for host :" + host);

		}
		try {
			channelSftp.connect();
			channelSftp.put(sourceFile, targetPath);
			log.info("Upload Complete");
		} catch (JSchException e) {
			// throw the exception
			e.printStackTrace();
			log.error("Error while connecting channel to host :" + host);
			throw new SftpException(0, "Error while connecting channel for host :" + host);
		} catch (SftpException e) {
			// throw the exception
			e.printStackTrace();
			log.error("Error while uploading file to host :" + host);
			throw new SftpException(0, "Error while uploading channel for host :" + host);
		} finally {
			if (channelSftp != null) {
				channelSftp.exit();
			}
		}

		return true;
	}

}
