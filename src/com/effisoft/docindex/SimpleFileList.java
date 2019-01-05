package com.effisoft.docindex;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SimpleFileList {
	
	private List<Integer> _depths;
	private List<String> _files;
	private List<Boolean> _isDirectory;

	public SimpleFileList() {
		_depths = new ArrayList<Integer>();
		_files = new ArrayList<String>();
		_isDirectory = new ArrayList<Boolean>();
	}
	
	public void addItem(int depth, String filename, boolean isDir) {
		_depths.add(depth);
		_files.add(filename);
		_isDirectory.add(isDir);
	}

	public void toHTML(HTMLFile htmlFile, Parameters parameters) {
			
		ListIterator<String> it = _files.listIterator();
		int index;
		String	 file;
		
		while (it.hasNext()) {
			
			index = it.nextIndex();
			file = it.next();
			
			if (index == 0) {
				htmlFile.initFile(file);
			}
			else {
				if (_isDirectory.get(index)) {
					int level = _depths.get(index) + 1;
					htmlFile.addDirectory(file, level);				
				}
				else {
					if (parameters._allowPDF && file.endsWith(".pdf")) {
						htmlFile.addFileEntry(file);
					}
					if (parameters._allowNonPDF && !file.endsWith(".pdf")) {
						htmlFile.addFileEntry(file);
					}
				}
			}
		}
		
		htmlFile.closeFile();
		
	}
	
}
