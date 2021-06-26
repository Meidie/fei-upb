#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

void secretFunction()
{
    printf("Gratulujem!\n");
    printf("Dostali ste sa do tajnej funkcie!\n");
}

void vuln(char *string)
{
    volatile int target;
    char buffer[64];

    target = 0;

    sprintf(buffer, "%63s", string);

    if(target == 0xdeadbeef) {
        secretFunction();
    }
}

int main(int argc, char **argv)
{
    vuln(argv[1]);
}