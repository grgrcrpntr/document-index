package com.effisoft.docindex;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/** Class for storing and processing a flat file list.
 * The list follows the table-of-contents implicit rule:
 *   - Element of index 0 is the root node
 *   - Any element in list with index i belongs to the node with the greated index small than i.
 */
public class SimpleFileList {
	
	// ----------------------
	// Attributes
	// ----------------------
	
	// All the 3 list below have exactly the SAME number of items
	/** List of the depths of each item in file tree structure */
	private List<Integer> _depths;
	/** List of file names (including directories) */
	private List<String> _files;
	/** List of booleans: _isDirectory[i] is true iif _files[i] is a directory */
	private List<Boolean> _isDirectory;

	// ----------------------
	// Constructor
	// ----------------------
	
	/** Basic constructor that simply instanciate attributes
	*/
	public SimpleFileList() {
		_depths = new ArrayList<Integer>();
		_files = new ArrayList<String>();
		_isDirectory = new ArrayList<Boolean>();
	}
	
	/** Add an item (file or directory) to the list
	* @param depth		(int) File or directory depth in original filetree structure
	* @param filename	(String) File or directory name
	* @param isDir		(boolean) True id file is a directory 
	*/
	public void addItem(int depth, String filename, boolean isDir) {
		_depths.add(depth);
		_files.add(filename);
		_isDirectory.add(isDir);
	}

	// ----------------------
	// Public methods
	// ----------------------
	
	/** Converts SimpleFileList into an HTML index file
	* @param htmlFile	(HTMLFile) The output HTML file
	* @param parameters	(Parameters) Parameters for HTML generation
	*/
	public void toHTML(HTMLFile htmlFile, Parameters parameters) {
			
		ListIterator<String> it = _files.listIterator();
		int index;
		String	 file;
		
		// Iterate on file list content
		while (it.hasNext()) {
			
			index = it.nextIndex();
			file = it.next();
			
			// First, initiate the HTML file
			if (index == 0) {
				htmlFile.initFile(file);
			}
			else {
				// Then, transfer each item of the list into HTML content
				if (_isDirectory.get(index)) {
					int level = _depths.get(index) + 1;
					htmlFile.addDirectory(file, level);				
				}
				else {
					// Filters for PDF and non-PDF documents
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
