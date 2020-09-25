package de.swa.gmaf;

import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class TempURLProvider {
	private static final String FTP_SERVER = "dev.step2e.de";
	private static final String FTP_USER = "diss";
	private static final String FTP_PASS = "stw476.log";
	private static final String HTTP_URL = "http://dev.step2e.de/";

	public static URL provideTempUrl(byte[] bytes, String suffix) {
		URL url = null;
		try {
			FTPClient ftp = new FTPClient();
			ftp.connect(FTP_SERVER);
			ftp.login(FTP_USER, FTP_PASS);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			
			UUID id = UUID.randomUUID();
			String remoteFile = id + suffix;
			
			OutputStream os = ftp.storeFileStream(remoteFile);
			os.write(bytes);
			os.flush();
			os.close();

			url = new URL(HTTP_URL + id + suffix);
			ftp.logout();
		} catch (Exception x) {
			x.printStackTrace();
		}
		return url;
	}
}
