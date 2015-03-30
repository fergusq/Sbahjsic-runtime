package sbahjsic.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public final class FileSource implements ScriptSource {
	
	private final File file;
	private final BufferedReader reader;
	
	public FileSource(File file) throws FileNotFoundException, UnsupportedEncodingException {
		this.file = file;
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
	}

	@Override
	public boolean hasMore() {
		try {
			return reader.ready();
		} catch (IOException e) {
			throw new RuntimeException("Error reading " + file.getName());
		}
	}

	@Override
	public String nextLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Error reading " + file.getName());
		}
	}

	@Override
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing " + file.getName());
		}
	}

	@Override
	public String getName() {
		return file.getName();
	}
	
}