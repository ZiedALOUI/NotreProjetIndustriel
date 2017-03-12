#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <stdarg.h>

#define R 0
#define W 1
#define SIZE_MESSAGE 256
#define SIZE_TUBE_NAME 64

int myEntrySO;
int myExitOS;

void init(char* fifoOS, char* fifoSO) {
    printf("API init.\n");
    //close(pipeSO);
    //close(pipeOS);
    int entrySO;
    if((entrySO = open (fifoSO, O_WRONLY)) == -1)
    {
        fprintf(stderr, "API: Impossible d'ouvrir la sortie du tube nommé :%s.\n", fifoSO);
        exit(EXIT_FAILURE);
    }
    myEntrySO = entrySO;
    printf("API openned entrySO\n");
    int exitOS;
    if((exitOS = open (fifoOS, O_RDONLY)) == -1)
    {
        fprintf(stderr, "API: Impossible d'ouvrir l'entrée du tube nommé :%so.\n", fifoOS);
        exit(EXIT_FAILURE);
    }
    myExitOS = exitOS;
    printf("API openned entryOS\n");}

void declareData(int type, char label[]) {
    printf("API declareData.\n");
    char writeBuffer[SIZE_MESSAGE];
    char readBuffer[SIZE_MESSAGE];
    strcpy(writeBuffer, label);
    printf("API pret a ecrire %s\n", label);
    write(myEntrySO, writeBuffer, SIZE_MESSAGE);
    read(myExitOS, readBuffer, SIZE_MESSAGE);
    printf("API reçu %s.\n", readBuffer);
    if (strcmp(readBuffer, "OK") != 0)
    {
        int pipeEntry;
        if((pipeEntry = open(writeBuffer, O_WRONLY)) == -1)
        {
            fprintf(stderr, "API: Impossible d'ouvrir l'entrée du tube nommé %s.\n", writeBuffer);
            exit(EXIT_FAILURE);
        }
        printf("API pret a écrire dans le tube nommé %s\n", writeBuffer);
    }
    else
    {
        printf("API erreur %s.\n", writeBuffer);
    }
}

void endDeclaration() {
    printf("API endDeclaration.\n");
    write(myEntrySO, "STOP", SIZE_MESSAGE);
}
void updateData(char* label, int value) {

}

void sendData(char* label) {

}

void stopData(char* label) {

}
