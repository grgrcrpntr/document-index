package com.effisoft.docindex;

/** Main class of document index generator.
 * Contains main() method.
 * Should be exported as an runnable JAR file.
 */
public class DocumentIndex {

	/** Main method: Generates HTML index file from input dir.
	 * See Command line syntax in https://github.com/grgrcrpntr/document-index/blob/master/README.md
	 * @param args	1: Input directory, 2: Output file, 3: PDF option.
	 */
	public static void main(String[] args) {
		
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
			
		} catch (IllegalArgumentException e) {
			// If illegal argument usage, print error message in console
			System.out.println(e.getMessage());
	  
		} catch (Exception e) {
	    	e.printStackTrace();
	 
		}
		
	}

}
