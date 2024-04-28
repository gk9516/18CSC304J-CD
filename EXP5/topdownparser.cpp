#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

char lookahead;

void match(char expected) {
    if (lookahead == expected)
        lookahead = getchar();
    else {
        fprintf(stderr, "Syntax error: Expected '%c' but found '%c'\n", expected, lookahead);
        exit(EXIT_FAILURE);
    }
}

void expression();
void term();
void factor();

void expression() {
    term();
    while (lookahead == '+' || lookahead == '-') {
        char op = lookahead;
        match(op);
        term();
        if (op == '+')
            printf("ADD\n");
        else if (op == '-')
            printf("SUB\n");
    }
}

void term() {
    factor();
    while (lookahead == '*' || lookahead == '/') {
        char op = lookahead;
        match(op);
        factor();
        if (op == '*')
            printf("MUL\n");
        else if (op == '/')
            printf("DIV\n");
    }
}

void factor() {
    if (isdigit(lookahead)) {
        printf("PUSH %c\n", lookahead);
        match(lookahead);
    } else if (lookahead == '(') {
        match('(');
        expression();
        match(')');
    } else {
        fprintf(stderr, "Syntax error: Expected digit or '(' but found '%c'\n", lookahead);
        exit(EXIT_FAILURE);
    }
}

int main() {
    printf("Enter an arithmetic expression: ");
    lookahead = getchar();
    expression();
    if (lookahead != '\n') {
        fprintf(stderr, "Syntax error: Unexpected input '%c'\n", lookahead);
        exit(EXIT_FAILURE);
    }
    return 0;
}
