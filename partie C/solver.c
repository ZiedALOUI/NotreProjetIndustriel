#include <stdio.h>
#include <stdlib.h>
#include "api.h"

int main(int argc, char **argv) {
    printf("Solver reçoit %d arguments\n", argc);
    printf("Ces arguments sont :\n");
    for(int i=0; i < argc; i++) {
        printf("-> %s\n", argv[i]);
    }
    printf("Solveur ouaip :%s: ma GUEULE\n", argv[argc]);
    //init("","");
    init(argv[argc-2], argv[argc-1]);
    printf("Solveur après init\n");
    declareData(1, "type1.fifo");
    declareData(1, "type2.fifo");
    printf("Solveur après declareData.\n");
    endDeclaration();
    printf("Solveur après endDeclaration.\n");
}
