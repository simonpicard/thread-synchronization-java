# thread-synchronization-java

## Quickstart

```
git clone git@github.com:simonpicard/thread-synchronization-java.git
cd thread-synchronization-java
cd src
make
java main
make clean
```

## Example output

```
Melting the article 0
Macerating the article 0
Melting the article 1
Extracting the article 0
Macerating the article 1
Extracting the article 1
Compressing the article 0
Compressing the article 1
Assembling the article 0
Assembling the article 1
Melting the article 2
Macerating the article 2
Extracting the article 2
Compressing the article 2
Assembling the article 2
###########

Worker 0 :
Article 0 skipped Workstation A
Article 0 skipped Workstation B
Article 1 processed on Workstation A
Article 1 processed on Workstation B
Article 1 processed on Workstation C
Article 1 processed on Workstation D
Article 1 processed on Workstation E

Worker 1 :
Article 0 processed on Workstation A
Article 0 processed on Workstation B
Article 0 processed on Workstation C
Article 0 processed on Workstation D
Article 0 processed on Workstation E
Article 1 skipped Workstation A
Article 1 skipped Workstation B

Worker 2 :
Waited for the Workstation A to be ready for the article 2
Article 2 processed on Workstation A
Article 2 processed on Workstation B
Article 2 processed on Workstation C
Article 2 processed on Workstation D
Article 2 processed on Workstation E

###########

Article 0 :
Worker 1 processed Workstation A
Worker 0 skipped Workstation A
Worker 1 processed Workstation B
Worker 0 skipped Workstation B
Worker 1 processed Workstation C
Worker 1 processed Workstation D
Worker 1 processed Workstation E

Article 1 :
Worker 0 processed Workstation A
Worker 0 processed Workstation B
Worker 0 processed Workstation C
Worker 0 processed Workstation D
Worker 1 skipped Workstation A
Worker 1 skipped Workstation B
Worker 0 processed Workstation E

Article 2 :
Worker 2 processed Workstation A
Worker 2 processed Workstation B
Worker 2 processed Workstation C
Worker 2 processed Workstation D
Worker 2 processed Workstation E
```
