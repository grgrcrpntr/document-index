package com.effisoft.docindex;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Class for converting index entries (files and dirs) into HTML markups.
 * Includes CSS definition which shall be externalized in future versions.
 */
public class HTMLFile {
	
	// ----------------------
	// Attributes
	// ----------------------
	
	/** HTML file name */
	private String _htmlfile;
	/** HTML file content as String */
	private String _htmlContent;
	/** Number of file entries in index */
	private int _numberOfFiles;
	/** Number of directory entries in index */
	private int _numberOfDirectories;
	
	// ----------------------
	// Constructor
	// ----------------------
	
	/** Creates new HTML file with empty content
	* @param htmlfile	(String) HTML file name
	*/
	public HTMLFile(String htmlfile) {
		_htmlfile = htmlfile;
		_htmlContent = "";
		_numberOfFiles = 0;
		_numberOfDirectories = 0;
	}
	
	// ----------------------
	// Public methods
	// ----------------------
		
	
	/** Dump HTML content as String (mainly used for debugging in console).
	* @return	HTML content as String
	*/
	public String dump() {
		return _htmlContent;
	}
	
	/** Dump HTML file to disk
	*/
	public void toDisk() {
		
		File file = new File(_htmlfile); 
		
		try {
			
			// Create a file
			file.createNewFile();
	        
			// Create a writer
	        final FileWriter writer = new FileWriter(file);
	        
	        try {
	        	// Write HTML Content in file
	        	writer.write(_htmlContent);
	        }
	        finally {
	            // Whatever happens, file will be closed
	            writer.close();
	        }
	    }
		catch (Exception e) {
	        System.out.println("Can not create file " + _htmlfile);
	    } 
	}
	
	/** Init HTML file (creates title, favicon, style and head)
	* @param title	(String) HTML page title
	*/
	public void initFile(String title) {
		
		_htmlContent = "<html lang=\"en\">\n";
		
		addTitle(title);
		addFavicon();
		addStyle();
		addHeader(title);
		
		_numberOfFiles = 0;
		_numberOfDirectories = 0;
		
	}
	
	/** Add file entry to HTML index file
	* @param filepath	(String) File entry path
	*/
	public void addFileEntry(String filepath) {
		_htmlContent += "<a href=\"" + filepath +  "\">" + filepath.substring(filepath.lastIndexOf("\\") + 1) + "</a><br/>\n";
		_numberOfFiles++;
	}
	
	/** Add directory entry to HTML index file
	* @param dirname	(String) Directory entry path
	* @param level		(int) Directory depth
	*/
	public void addDirectory(String dirname, int level) {
		
		String openMarkup, closeMarkup;
		
		// Turn directory tree level into <h*> markup
		if(level > 6) level = 6;
		openMarkup = "<h" + level + ">";
		closeMarkup = "</h" + level + ">";
		
		_htmlContent += openMarkup + dirname.substring(dirname.lastIndexOf("\\")) + closeMarkup + "\n";
	
		_numberOfDirectories++;
	}
	
	/** Close HTML file (add footer)
	*/
	public void closeFile() {
		addFooter();
	}
	
	// ----------------------
	// Private methods
	// ----------------------
	
	/** Add HTML tile
	* @param title	(String) HTML page title
	*/
	private void addTitle(String title) {
		_htmlContent+= "<title>" + title.substring(title.lastIndexOf("\\")) + "</title>\n";
	}
	
	/** Add Favicon
	*/
	private void addFavicon() {
		_htmlContent+= "<link href=\"http://www.effisoft.com/templates/t3_bs3_blank/favicon.ico\" rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" />\n";
	}
	
	/** Add CSS
	*/
	private void addStyle() {
		_htmlContent += "<style>\n"
				+ "	* {  box-sizing: border-box; }\n"
				+ " a:link { color: #111111; text-decoration: none; }\n"
				+ " a:visited { color: #111111; text-decoration: none; }\n"
				+ " a:hover { color: #111111; text-decoration: underline; }\n"
				+ " a:active { color: #111111; text-decoration: underline; } \n"
				+ " body { font-family: Calibri, Verdana, Arial, Helvetica, sans-serif; }\n"
				+ "	header { background-color: #666; padding: 30px; text-align: center; font-size: 35px; color: white; }\n"
				+ " section { display: -webkit-flex; display: flex; }\n"  
				+ "	nav { /*-webkit-flex: 1; -ms-flex: 1; flex: 1;*/ background: #F10000; padding: 20px; }\n" 
				+ "	nav ul { list-style-type: none; padding: 0; }\n" 
				+ "	article { -webkit-flex: 3; -ms-flex: 3; flex: 3; background-color: #f1f1f1; padding: 50px; }\n"  
				+ "	footer { background-color: #777; padding: 10px; text-align: center; color: white; }\n"  
				+ "	@media (max-width: 600px) { section { -webkit-flex-direction: column; flex-direction: column; } }"
				+ "</style>\n";
	}
	
	/** Add HTML header (incl. page title)
	* @param title	(String) HTML page title
	*/
	private void addHeader(String title) {
		_htmlContent += "<body>\n<header>";
		_htmlContent+= title.substring(title.lastIndexOf("\\"));
		_htmlContent+= "</header>\n<section>\n<nav></nav>\n<article>\n";
	}
	
	/** Add HTML page footer
	*/
	private void addFooter() {
		
		_htmlContent += "</article>\n</section>\n";
		
		System.out.println("Indexed directories: " + _numberOfDirectories);
		System.out.println("Indexed files: " + _numberOfFiles);
		
		_htmlContent += "<footer>\n" + _numberOfDirectories + " directories - " + _numberOfFiles + " files<br //>\n";
		
		// Compute index generation date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		_htmlContent += "Last generated on " + dateFormat.format(date) + "\n</footer>\n";
		
		_htmlContent += "</body>\n</html>";
	}

}
