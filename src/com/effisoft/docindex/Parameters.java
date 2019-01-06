package com.effisoft.docindex;

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
	/** True if PDF files are allowed in index */
	public boolean _allowPDF;
	/** True if non PDF files are allowed in index */
	public boolean _allowNonPDF;
		
		// ----------------------
		// Constructor
		// ----------------------
		
		/** Create parameter structure
		* @param args		Command line parameter list
		*/
		public Parameters(String[] args) {
			
			// In and Out paths
			_rootpath = args[0];
			_outfile = _rootpath + "\\" + args[1];
			
			// Default booleans
			_allowPDF = true;
			_allowNonPDF = true;
			
			// Handling PDF options
			if (args.length == 3) {
				if (args[2].equals("-nopdf")) _allowPDF = false;
				if (args[2].equals("-onlypdf")) _allowNonPDF = false;
			}
			
		}
		
}
