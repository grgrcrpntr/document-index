package com.effisoft.docindex;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLFile {
	
	String _htmlfile;
	String _htmlContent;
	
	public HTMLFile(String htmlfile) {
		_htmlfile = htmlfile;
		_htmlContent = "";
	}
	
	public String dump() {
		return _htmlContent;
	}
	
	public void toDisk() {
		
		File file =new File(_htmlfile); 
		try {
			// Creation du fichier
			file.createNewFile();
	        // creation d'un writer (un écrivain)
	        final FileWriter writer = new FileWriter(file);
	        try {
	        	writer.write(_htmlContent);
	        }
	        finally {
	            // quoiqu'il arrive, on ferme le fichier
	            writer.close();
	        }
	    }
		catch (Exception e) {
	        System.out.println("Can not create file " + _htmlfile);
	    } 
	}
	
	public void initFile(String title) {
		
		_htmlContent = "<html lang=\"en\">\n";
		
		addTitle(title);
		addFavicon();
		addStyle();
		addHeader(title);
		
	}
	
	public void addFileEntry(String filepath) {
		_htmlContent += "<a href=\"" + filepath +  "\">" + filepath.substring(filepath.lastIndexOf("\\") + 1) + "</a><br/>\n";
	}
	
	public void addDirectory(String dirname, int level) {
		String openMarkup, closeMarkup;
		if(level > 6) level = 6;
		openMarkup = "<h" + level + ">";
		closeMarkup = "</h" + level + ">";
		_htmlContent += openMarkup + dirname.substring(dirname.lastIndexOf("\\")) + closeMarkup + "\n";
	}
	
	public void closeFile() {
		addFooter();
	}
	
	private void addTitle(String title) {
		_htmlContent+= "<title>" + title.substring(title.lastIndexOf("\\")) + "</title>\n";
	}
	
	private void addFavicon() {
		_htmlContent+= "<link href=\"http://www.effisoft.com/templates/t3_bs3_blank/favicon.ico\" rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" />\n";
	}
	
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
	
	private void addHeader(String title) {
		_htmlContent += "<body>\n<header>";
		_htmlContent+= title.substring(title.lastIndexOf("\\"));
		_htmlContent+= "</header>\n<section>\n<nav></nav>\n<article>\n";
	}
	
	private void addFooter() {
		_htmlContent += "</article>\n</section>\n";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		_htmlContent += "<footer>Last generated on " + dateFormat.format(date) + "</footer>\n";
		_htmlContent += "</body>\n</html>";
	}

}
