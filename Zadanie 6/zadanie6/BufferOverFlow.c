#include <stdio.h>

void secretFunction()
{
    printf("Gratulujem!\n");
    printf("Dostali ste sa do tajnej funkcie!\n");
}

void echo()
{
    char buffer[20];

    printf("Zadajte nejaky text:\n");
    scanf("%s", buffer);
    printf("Zadali ste: %s\n", buffer);    
}

int main(){

	echo();
    return 0;
}