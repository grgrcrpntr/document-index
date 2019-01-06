package com.effisoft.docindex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** Recursive class for storing and processing tree-like file structures.
 * SimpleFileTree instances store subdirectories as a SimpleFileTree list.
 */
public class SimpleFileTree {
	
	// ----------------------
	// Attributes
	// ----------------------
	
	/** Absolute path to file tree node */
	private String _path;
	/** Directory depth in the tree (0 if root) */
	private int _depth;
	/** Boolean used for checking that path is a real dir indeed */
	private boolean _checkIfPathExists;
	/** List of non-directoty files in the tree node */
	private List<String> _nodeFiles;
	/** List of subnodes as SimpleFileTrees */
	private List<SimpleFileTree> _subTrees;
	
	// ----------------------
	// Constructors
	// ----------------------
	
	/** Private constructor called by public constructors
	* @param path	(String) Path to the root node
	*/
	private void privateConstructor(String path) {
		
		_path = path;
		_nodeFiles = new ArrayList<String>();
		_subTrees = new ArrayList<SimpleFileTree>();
		
		File file = new File(_path);
		
		// If root node of an already-scanned node,
		// there is no need to check for file existence
		if (_checkIfPathExists) {
			if (file.exists()) {
				this.scan();
			} else {
				System.out.println("Can not access " + _path);
			}
		} else {
			this.scan();
		}
			
			
	}
	
	/** Public root node constructor
	* @param path	(String) Path to the root node
	*/
	public SimpleFileTree(String path) {
		
		// Depth is ZERO for root node
		_depth = 0;
		// Force to check if path is a real file
		_checkIfPathExists = true;
		// Call private constructor
		this.privateConstructor(path);
		
	}
	
	/** Private constructor for sub-nodes
	* @param path	(String) Path to the sub-node
	* @param depth	(int) Depth of sub-node in file tree
	*/
	private SimpleFileTree(String path, int depth) {

		_depth = depth;
		// No need to check for subnode file existence here,
		// as method is invokes by recursive calls that fetch the tree
		_checkIfPathExists = false;
		// Call private constructor
		this.privateConstructor(path);

	}
	
	// ----------------------
	// Private methods
	// ----------------------
	
	/** Build file tree though recursive calls.
	*/
	private void scan() {
		
		// Out in console
		System.out.println("Scanning directory: " + _path);
		
		File node = new File(_path);
		File[] nodeContent = node.listFiles();
		
		// Iterating on current node's content
		for (File content : nodeContent) {
			String nodeItemPath = content.toString();
			// If current item is a file, add it to file list
			if (content.isFile()) {
				_nodeFiles.add(nodeItemPath);
			}
			// If current iten is a directory, create a new SimpleFileTree with depth increment
			if (content.isDirectory()) {
				SimpleFileTree subTree = new SimpleFileTree(nodeItemPath, _depth + 1);
				_subTrees.add(subTree);
			}
		}
	}
	
	// ----------------------
	// Public methods
	// ----------------------
	
	/** Converts tree to string, maily for debug purposes.
	* Tree is flattened by deep-first recursive search.
	*/
	public String toString() {
		
		String outString = "";
		String indent = "";
		
		for (int i=0; i<_depth; i++) {
			indent = indent + "    ";
		}
		
		outString += indent + _path.substring(_path.lastIndexOf("\\")) + " :\n";
		
		for (String file : _nodeFiles) {
			outString += indent + file.substring(_path.length() + 1) + "\n";
		}
		
		for (SimpleFileTree subTree : this._subTrees) {
			outString += subTree.toString();
		}
		
		return outString;
		
	}
	
	/** Flatten tree to linked list through deep-first recursive search.
	* The output list can than be easly processed by an HTML converter to build an document index.
	* @param fileList	(SimpleFileList) Ouput file list
	*/
	public void toSimpleFileList(SimpleFileList fileList) {
		
		// Add current node to the list (as directory)
		fileList.addItem(_depth, _path, true);
		
		// Add each file in current node first (as file)
		for (String file : _nodeFiles) {
			fileList.addItem(_depth, file, false);
		}
		
		// Then, recursively call toSimpleFileList() to flatten sub-nodes.
		for (SimpleFileTree subTree : this._subTrees) {
			subTree.toSimpleFileList(fileList);
		}
		
	}

}
