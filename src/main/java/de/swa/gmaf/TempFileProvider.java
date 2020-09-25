package de.swa.gmaf;

import java.io.File;
import java.io.FileOutputStream;

public class TempFileProvider {
	public static File provideTempFile(byte[] bytes, String suffix) {
		try {
			File f = File.createTempFile("GMAF_TMP_", suffix);
			FileOutputStream fout = new FileOutputStream(f);
			fout.write(bytes);
			fout.close();
			return f;
		}
		catch (Exception x) {
			x.printStackTrace();
		}
		return null;
	}
}
