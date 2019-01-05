package com.effisoft.docindex;

public class DocumentIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			// Compute parameters from args
			Parameters parameters = new Parameters(args);
			
			// Build file tree strucutre
			SimpleFileTree fileTree = new SimpleFileTree(parameters._rootpath);
			
			// Convert file tree into file list
			SimpleFileList fileList = new SimpleFileList();
			fileTree.toSimpleFileList(fileList);
			
			// Convert file list into HTML
			HTMLFile htmlFile = new HTMLFile(parameters._outfile);
			fileList.toHTML(htmlFile, parameters);
			
			// Dump HTML file to disk
			htmlFile.toDisk();
			
		} catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

}
