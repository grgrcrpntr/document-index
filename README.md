# document-index

File index HTML generator

### Overview
DocumentIndex is a command-line java tool which scans all files in a given input directory and output a single file index in an HTML page.

### Command line syntax

We asume the project has previously been exported as the runnable JAR file `buildindex.jar`. 
###### Standard call (list all documents)
`java -jar buildindex.jar <input-directory> <output-html-file>`
###### PDF Only option (list only PDF documents)
`java -jar buildindex.jar <input-directory> <output-html-file> -onlypdf`
###### No PDF option (list alll non-PDF documents)
`java -jar buildindex.jar <input-directory> <output-html-file> -nopdf`

### Windows desktop executable

1. Save one of the above command lines in a Window BAT file e.g. `buildindex.bat`.
2. Make sure a [Java Runntime Environnement](https://www.java.com/fr/download/) is installed and configured on your machine.
3. Double-click `buildindex.bat`.

### Scheduling index generation on Windows

1. Generate a desktop executable batch (see above).
2. Use [Windows Task Scheduler](https://docs.microsoft.com/en-us/windows/desktop/taskschd/task-scheduler-start-page) to run the batch automatically.