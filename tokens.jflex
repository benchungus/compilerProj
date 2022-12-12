/*-***
 *
 * This file defines a stand-alone lexical analyzer for a subset of the Pascal
 * programming language.  This is the same lexer that will later be integrated
 * with a CUP-based parser.  Here the lexer is driven by the simple Java test
 * program in ./PascalLexerTest.java, q.v.  See 330 Lecture Notes 2 and the
 * Assignment 2 writeup for further discussion.
 *
 */


import java_cup.runtime.*;


%%
/*-*
 * LEXICAL FUNCTIONS:
 */

%cup
%line
%column
%unicode
%class Lexer

%{

/**
 * Return a new Symbol with the given token id, and with the current line and
 * column numbers.
 */
Symbol newSym(int tokenId) {
    return new Symbol(tokenId, yyline, yycolumn);
}

/**
 * Return a new Symbol with the given token id, the current line and column
 * numbers, and the given token value.  The value is used for tokens such as
 * identifiers and numbers.
 */
Symbol newSym(int tokenId, Object value) {
    return new Symbol(tokenId, yyline, yycolumn, value);
}

%}


/*-*
 * PATTERN DEFINITIONS:
 */

tab     				= \\t
newline	        = \\n
slash			      = \\ 
escapeapos		  = {slash}'
escapequote		  = {slash}\"
letter      	  = [A-Za-z]
digit       	  = [0-9]
id   			      = {letter}({letter}|{digit})* 
integer	    	  = {digit}+
float    	  = {integer}+\.{integer}+

charchar		    = [[^\\]&&[^']]|{newline}|{tab}|{escapeapos}|{slash}{slash}
character     	  = '{charchar}'

stringchar		  = [[[^\\]&&[^\"]]&&[[^\n]&&[^\t]]]|{newline}|{tab}|{escapequote}|{slash}{slash}
string		    = \"{stringchar}*\"
blockcommentS   = {slash}\*
blockcommentE   = \*{slash}

escapeapos		  = {slash}'
escapequote		  = {slash}\"

commentbody		  = ([^\*]|(\*+[^\\]))
blockcomment    = {blockcommentS}{commentbody}*?{blockcommentE}
inlinecomment 	= {slash}{slash}.*(\n|\r|\r\n)
whitespace      = [ \n\t\r]

/**
 * Implement patterns as regex here
 */




%%
/**
 * LEXICAL RULES:
 */

/**
 * Implement terminals here, ORDER MATTERS!
 */

"("             {return newSym(sym.LPAREN, "(");}
")"             {return newSym(sym.RPAREN, ")");}
":"             {return newSym(sym.COLON, ":");}
"-"             {return newSym(sym.MINUS, "-");}
"+"             {return newSym(sym.PLUS, "+");}
"*"             {return newSym(sym.MULTIPLY, "*");}
"/"             {return newSym(sym.DIVIDE, "/");}
"~"             {return newSym(sym.NOT, "~");}
true            {return newSym(sym.TRUE, "true");}
false           {return newSym(sym.FALSE, "false");}
"<>"            {return newSym(sym.NOTEQUAL, "<>");}
"<="            {return newSym(sym.LESSEQUAL, "<=");}
">="            {return newSym(sym.GREATEREQUAL, ">=");}
"=="            {return newSym(sym.EQUAL, "==");}
"<"             {return newSym(sym.LESS, "<");}
">"             {return newSym(sym.GREATER, ">");}
"||"            {return newSym(sym.OR, "||");}
"&&"            {return newSym(sym.AND, "&&");}
"?"             {return newSym(sym.QUESTION, "?");}
"="             {return newSym(sym.ASSIGN, "=");}
void             {return newSym(sym.VOIDTYPE, "void");}
int             {return newSym(sym.INTTYPE, "int");}
float             {return newSym(sym.FLOATTYPE, "float");}
bool             {return newSym(sym.BOOLTYPE, "bool");}
char             {return newSym(sym.CHARTYPE, "char");}
","             {return newSym(sym.COMMA, ",");}
";"             {return newSym(sym.SEMICOLON, ";");}
return             {return newSym(sym.RETURN, "return");}
class             {return newSym(sym.CLASS, "class");}
else             {return newSym(sym.ELSE, "else");}
if             {return newSym(sym.IF, "if");}
while             {return newSym(sym.WHILE, "while");}
read             {return newSym(sym.READ, "read");}
print             {return newSym(sym.PRINT, "print");}
printline             {return newSym(sym.PRINTLINE, "printline");}
"["             {return newSym(sym.LSQBRACK, "[");}
"]"             {return newSym(sym.RSQBRACK, "]");}
"{"             {return newSym(sym.LCURLBRACK, "{");}
"}"             {return newSym(sym.RCURLBRACK, "}");}
"++"              {return newSym(sym.INCREMENT, "++");}
"--"              {return newSym(sym.DECREMENT, "--");}
final           {return newSym(sym.FINAL, "final");}
{id}            {return newSym(sym.ID, yytext());}
{integer}       {return newSym(sym.NUMBERLIT, new Integer(yytext()));}
{float}         {return newSym(sym.FLOATLIT, new Float(yytext()));}
{character}     {return newSym(sym.CHARACTERLIT, yytext());}
{string}        {return newSym(sym.STRINGLIT, yytext());}
{whitespace}    { /* Ignore whitespace. */ }
{inlinecomment} { /* For this stand-alone lexer, print out comments. */}
{blockcomment}	{ /* For this stand-alone lexer, print out comments. */}
.               { System.out.println("Illegal char, '" + yytext() +
                    "' line: " + yyline + ", column: " + yychar); } 