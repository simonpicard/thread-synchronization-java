JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	main.java \
	Article.java \
	Assembling.java \
	Compressing.java \
	Extracting.java \
	Graph.java \
	Macerating.java \
	Melting.java \
	Worker.java \
	Workstation.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
