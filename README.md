# document-index
File index HTML generator
#### Overview
DocumentIndex is a command-line java tool which scans all files in a given input directory and output a single file index in an HTML page.
#### Command line syntax
We asume the project has previously been exported as the runnable JAR file `buildindex.jar`. 
###### Standard call (list all documents)
`java -jar buildindex.jar <input-directory> <output-html-file>`
###### PDF Only option (list only PDF documents)
`java -jar buildindex.jar <input-directory> <output-html-file> -onlypdf`
###### No PDF option (list alll non-PDF documents)
`java -jar buildindex.jar <input-directory> <output-html-file> -nopdf`
#### Batching and Scheduling

#### JRE
Java SE-1.8 (JRE1.8.0_161)