package com.effisoft.docindex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileTree {
	
	private String _path;
	private int _depth;
	private boolean _checkIfPathExists;
	private List<String> _nodeFiles;
	private List<SimpleFileTree> _subTrees;
	
	private void rootConstructor(String name) {
		
		_path = name;
		_nodeFiles = new ArrayList<String>();
		_subTrees = new ArrayList<SimpleFileTree>();
		
		File path = new File(_path);
		
		if (_checkIfPathExists) {
			if (path.exists()) {
				this.scan();
			} else {
				System.out.println("Can not access " + _path);
			}
		} else {
			this.scan();
		}
			
			
	}
	
	public SimpleFileTree(String path) {
		
		_depth = 0;
		_checkIfPathExists = true;
		this.rootConstructor(path);
		
	}
	
	private SimpleFileTree(String path, int depth) {

		_depth = depth;
		_checkIfPathExists = false;
		this.rootConstructor(path);

	}
	
	private void scan() {
		
		System.out.println("Scanning directory: " + _path);
		
		File node = new File(_path);
		File[] nodeContent = node.listFiles();
		
		for (File content : nodeContent) {
			String nodeItemPath = content.toString();
		
			if (content.isFile()) {
				_nodeFiles.add(nodeItemPath);
			}
			
			if (content.isDirectory()) {
				SimpleFileTree subTree = new SimpleFileTree(nodeItemPath, _depth + 1);
				_subTrees.add(subTree);
			}
		}
	}
	
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
	
	public void toSimpleFileList(SimpleFileList fileList) {
		
		fileList.addItem(_depth, _path, true);
		
		for (String file : _nodeFiles) {
			fileList.addItem(_depth, file, false);
		}
		
		for (SimpleFileTree subTree : this._subTrees) {
			subTree.toSimpleFileList(fileList);
		}
		
	}

}
