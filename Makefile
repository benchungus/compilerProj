JAVA=java
JAVAC=javac
JFLEX=$(JAVA) -jar jflex-full-1.8.2.jar
CUPJAR=./java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR)
CP=.:$(CUPJAR)

default: run

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
		$(JAVAC) -cp $(CP) $*.java

FILE=    Lexer.java      parser.java    sym.java\
    LexerTest.java	ScannerTest.java TypeCheckingTest.java	Token.java 		Program.java\
	Expr.java Statement.java StatementList.java\
	Name.java Args.java Type.java ReadList.java\
	IfEnd.java OptionalSemi.java FieldDecl.java\
	FieldDecls.java MemberDecls.java MethodDecls.java\
	MethodDecl.java OptionalFinal.java OptionalExpr.java\
	ReturnType.java ArgDecls.java ArgDeclList.java\
	ArgDecl.java SymbolTable.java TypeInfo.java TypeException.java\


run: basicTest.txt

all: Lexer.java parser.java $(FILE:java=class)

basicTest.txt: all
		$(JAVA) -cp $(CP) TypeCheckingTest basicTest.txt > basicTest-output.txt
		cat basicTest.txt
		cat -n basicTest-output.txt

clean:
		rm -f *.class *~ *.bak Lexer.java parser.java sym.java

Lexer.java: tokens.jflex
		$(JFLEX) tokens.jflex

parser.java: grammar.cup
		$(CUP) -interface < grammar.cup

parserD.java: grammar.cup
		$(CUP) -interface -dump < grammar.cup
