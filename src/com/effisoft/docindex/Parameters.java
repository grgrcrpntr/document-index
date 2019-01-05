package com.effisoft.docindex;

public class Parameters {

	public String _rootpath;
	public String _outfile;
	public boolean _allowPDF;
	public boolean _allowNonPDF;
		
		public Parameters(String[] args) {
			
			_rootpath = args[0];
			_outfile = _rootpath + "\\" + args[1];
			_allowPDF = true;
			_allowNonPDF = true;
			
			if (args.length == 3) {
				if (args[2].equals("-nopdf")) _allowPDF = false;
				if (args[2].equals("-onlypdf")) _allowNonPDF = false;
			}
			
		}
		
}
