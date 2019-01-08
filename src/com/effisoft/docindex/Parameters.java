package com.effisoft.docindex;

import java.util.HashSet;
import java.util.Set;

/** Parameter handling class
 */
public class Parameters {

	// ----------------------
	// Attributes (public to avoid useless accessors)
	// ----------------------
	
	/** Path of the directory to be scanned */
	public String _rootpath;
	/** Path of the output HTML index file */
	public String _outfile;
	/** True is allowed file types are whitelisted */
	private boolean _whitelist;
	/** IF _whitelist THEN allowed file types. ELSE blacklisted types. */
	private Set<String> _typeList;
		
		// ----------------------
		// Constructor
		// ----------------------
		
		/** Create parameter structure
		* @param args		Command line parameter list
		*/
		public Parameters(String[] args) {
			
			_whitelist = false;
			_typeList = new HashSet<String>();
			
			if (args.length < 1) {
				throw new IllegalArgumentException("Error: Input direstory is not specified.");
			}
			
			if (args.length < 2) {
				throw new IllegalArgumentException("Error: Output file is not specified.");
			}
			
			// In and Out paths
			_rootpath = args[0];
			_outfile = _rootpath + "\\" + args[1];
			
			if (args.length > 2) {
				
				// Choose whithlist or blacklist file type handling
				switch (args[2]) {
				case "-include":
					_whitelist = true;
					break;
				case "-exclude":
					_whitelist = false;
					break;
				default:
					throw new IllegalArgumentException("Error: " + args[2] + " is not a valid option.");
				}
				
				if (args.length < 4) {
					throw new IllegalArgumentException("Error: " + args[2] + " option requires a file type list.");
				}
				
				// Create file type white/black list
				String[] extensions = args[3].split("/");
				for (String ext : extensions) _typeList.add(ext);
				
			}	
		}
		
		// ----------------------
		// Public methods
		// ----------------------
		
		/** Return true if file type is allowed in index
		* @param extension	(String) File type
		* @return boolean	
		*/
		public boolean isFileTypeAllowed(String extension) {
			
			boolean out = false;
			
			if (_whitelist) {
				if (_typeList.contains(extension)) 
					return true;
			} else {
				if (!_typeList.contains(extension))
					return true;
			}
			
			return out;
		}
		
}
