%{
#include <stdio.h>
%}

%option noyywrap

%%

[0-9]+          { printf("INTEGER: %s\n", yytext); }
[a-zA-Z][a-zA-Z0-9]*    { printf("IDENTIFIER: %s\n", yytext); }
\"if\"            { printf("IF\n"); }
\"else\"          { printf("ELSE\n"); }
"+"             { printf("PLUS\n"); }
"-"             { printf("MINUS\n"); }
"*"             { printf("TIMES\n"); }
"/"             { printf("DIVIDE\n"); }
"=="            { printf("EQUAL\n"); }
"="             { printf("ASSIGN\n"); }
"("             { printf("LPAREN\n"); }
")"             { printf("RPAREN\n"); }
";"             { printf("SEMICOLON\n"); }
[ \t\n]         ;  /* Skip whitespace and newline characters */
.               { printf("UNKNOWN CHARACTER: %s\n", yytext); }

%%


int main() {
    yylex();
    return 0;
}
