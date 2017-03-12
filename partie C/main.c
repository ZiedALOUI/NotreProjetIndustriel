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
#define NB_OBS_VAR 64

char *str_join (char *cs, ...)
    {
        printf("str_join function.\n");
       va_list va;
       const char *ct = NULL;
       char *s = NULL;
       size_t size = 0;

       va_start (va, cs);
    /* (1) */
        printf("DEBUG1\n");
       while ((ct = va_arg (va, char *)) != NULL)
       {

          void *tmp = NULL;
            printf("DEBUG2 ct %s.\n", &ct);
    /* (2) */
          size = size + strlen(ct) + strlen(cs);

          tmp = realloc(s, sizeof (*s) * (size + 1));
          printf("DEBUG2bis\n");
          if (tmp != NULL)
          {
             if (s == NULL)
             {
    /* (3) */
                s = tmp;
                strcpy (s, ct);
             }
             else
             {
    /* (4) */
                 s = tmp;
                 strcat (s, cs);
                 strcat (s, ct);
             }
          }
          else
          {
              printf("DEBUG3\n");
             fprintf (stderr, "Memoire insuffisante\n");
             free (s);
             s = NULL;
             exit (EXIT_FAILURE);
          }
       }
       printf("str_join renvoie %s.\n", s);
       return s;
    }


int main(int argc, char **argv)
{
    if(mkfifo("pipeOS.fifo", 0644) != 0)
    {
        fprintf(stderr, "Observateur Impossible de créer le fifo: %s.\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    if(mkfifo("pipeSO.fifo", 0644) != 0)
    {
        fprintf(stderr, "Observateur Impossible de créer le fifo: %s.\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    //int pipeOS[2];
    //int pipeSO[2];
    pid_t pid1;
    pid_t pid2;

    //pipe(pipeOS);
    //pipe(pipeSO);
    printf("pipe créés.\n");
    pid1 = fork();

    if (pid1 !=0) //Observateur
    {
        printf("Observateur\n");
        char writeBuffer[SIZE_MESSAGE];
        char readBuffer[SIZE_MESSAGE];
        char tubenames[NB_OBS_VAR][SIZE_MESSAGE];

        //Ouverture des descripteurs
        //close(pipeOS[R]);
        //close(pipeSO[W]);
        int exitSO;
        if((exitSO = open ("pipeSO.fifo", O_RDONLY)) == -1)
        {
            fprintf(stderr, "Impossible d'ouvrir la sortie du tube nommé :pipeSO.fifo.\n");
            exit(EXIT_FAILURE);
        }
        int entryOS;
        if((entryOS = open ("pipeOS.fifo", O_WRONLY)) == -1)
        {
            fprintf(stderr, "Impossible d'ouvrir l'entrée du tube nommé :pipeOS.fifo.\n");
            exit(EXIT_FAILURE);
        }

        //Ecoute pour reception et creation des fifo
        int i = 0;
        read(exitSO, readBuffer, SIZE_MESSAGE);
        printf("Observateur nom 1 reçu = \"%s\" \n", readBuffer);
        while(strcmp(readBuffer, "STOP") != 0)
        {
            if(mkfifo(readBuffer, 0644) != 0)
            {
                fprintf(stderr, "Observateur Impossible de créer le fifo: %s.\n", strerror(errno));
                exit(EXIT_FAILURE);
            }
            else
            {
                i++;
                printf("Observateur fifo \"%s\" made\n", readBuffer);
                strcpy(tubenames[i], readBuffer);
                sprintf(writeBuffer, "OK");
                printf("Observateur pret a ecrire OK\n");
                write(entryOS, writeBuffer, SIZE_MESSAGE);
            }
            pid2 = fork(); // creation des listeners
            if (pid2 == 0) //Listener
            {
                printf("Listener du fifo %s début.\n", tubenames[i]);


                int fifoExit;
                if((fifoExit = open(tubenames[i], O_RDONLY)) == -1)
                {
                    fprintf(stderr, "Listener: Impossible d'ouvrir la sortie du tube nommé %s.\n", tubenames[i]);
                    exit(EXIT_FAILURE);
                }
                printf("Listener pret a écouter dans le tube nommé %s.\n", tubenames[i]);
                read(fifoExit, readBuffer, SIZE_MESSAGE);
                printf("Listener a reçu %s sur le fifo%s.", readBuffer, tubenames[i]);
                while (strcmp(readBuffer, "STOP") != 0)
                {
                    //mise a jour de la Datatab
                    read(fifoExit, readBuffer, SIZE_MESSAGE);
                    printf("Listener a reçu %s sur le fifo%s.", readBuffer, tubenames[i]);
                }
                printf("Listener du fifo %s fin.\n", tubenames[i]);
            }
            read(exitSO, readBuffer, SIZE_MESSAGE);
            printf("Observateur nom while reçu = \"%s\" \n", readBuffer);
        }
        printf("Observateur fin création fifo et listeners.\n");
    }
    else //Appel solveur
    {
        /*
        char l1[];
        strcpy(l1, "solver");
        printf("L1 %s.\n", l1);

        printf("Appel solveur.\n");
        close(pipeSO[W]);
        close(pipeOS[R]);
        printf("Appel Solveur descripteurs fermés.\n");

        char* l2;
        strcpy(l2, (char*)pipeOS[R]);
        printf("L2 %s.\n", l2);
        char* l3;
        strcpy(l3, (char*)pipeSO[W]);
        printf("L3 %s/\n", l3);


        char* launchSolver = strcat(strcat(strcat("./solver 2 A B %d %d ", (char*)pipeOS[R]), " "), (char*)pipeSO[W]);
        printf("AppelSolveur commande: %s\n", launchSolver);
        int status = system(launchSolver);
        */
        //close(pipeSO[R]);
        //close(pipeOS[W]);
        printf("Appel solveur.\n");
        printf("Appel Solveur openned exitOS\n");
        char* launchSolver = str_join(" ", "./solver", "pipeOS.fifo", "pipeSO.fifo", NULL);
        printf("AppelSolveur commande: %s\n", launchSolver);
        int status = system(launchSolver);
        return EXIT_SUCCESS;


    }


    /**
    //OLDMAIN
    int status = system("./solver 2 A B");
    pid_t pid;
    int tubeFP[2];
    int tubePF[2];
    char messageEcrire[SIZE_MESSAGE];
    char messageLire[SIZE_MESSAGE];

    pipe(tubeFP);
    pipe(tubePF);
    pid = fork();

    if(pid != 0) // Pere
    {

        printf("Hello world!\n");
        close(tubeFP[W]);
        close(tubePF[R]);
        char tubenames[NB_OBS_VAR][SIZE_MESSAGE];

        int i = 0;
        read(tubeFP[R], messageLire, SIZE_MESSAGE);
        i++;
        printf("Pnom 1 reçu = \"%s\" \n", messageLire);
        while(strcmp(messageLire, "STOP") != 0 && i < 6)
        {
            if(mkfifo(messageLire, 0644) != 0)
            {
                fprintf(stderr, "Pere Impossible de créer le tube nommé: %s.\n", strerror(errno));
                exit(EXIT_FAILURE);
            }
            else
            {
                i++;
                printf("P \"%s\" made\n", messageLire);
                strcpy(tubenames[i], messageEcrire);
                sprintf(messageEcrire, "OK");
                printf("Pere pret a ecrire OK\n");
                write(tubePF[W], messageEcrire, SIZE_MESSAGE);
            }
            read(tubeFP[R], messageLire, SIZE_MESSAGE);
            printf("Pnom while reçu = \"%s\" \n", messageLire);
        }
        printf("Pere exit while\n");
        strcpy(messageEcrire, "STOP");
        printf("Pere pret a ecrire STOP\n");
        write(tubePF[W], messageEcrire, SIZE_MESSAGE);
        printf("Pere fin\n");


        int sortieTube;
        if((sortieTube = open ("name0.fifo", O_RDONLY)) == -1)
        {
            fprintf(stderr, "Impossible d'ouvrir la sortie du tube nommé :\"%s\".\n", tubenames[1]);
            exit(EXIT_FAILURE);
        }

        read(sortieTube, messageLire, SIZE_MESSAGE);
        printf("Pere lu \"%s\" dans tube nommé name0.fifo\n", messageLire);

    }
    else if(pid == 0) // Processus fils
    {
        close(tubeFP[R]);
        close(tubePF[W]);
        char variables[NB_OBS_VAR][SIZE_TUBE_NAME];

        strcpy(variables[0], "name0.fifo");
        strcpy(variables[1], "name1.fifo");
        strcpy(variables[2], "name2.fifo");
        strcpy(variables[3], "name3.fifo");
        strcpy(variables[4], "name4.fifo");
        strcpy(variables[5], "name5.fifo");
        strcpy(variables[6], "name6.fifo");

//        int debug;


        //sprintf(messageEcrire, "name1.fifo");
        int i = 0;
        strcpy(messageEcrire, variables[i]);
        write(tubeFP[W], messageEcrire, SIZE_MESSAGE);
        read(tubePF[R], messageLire, SIZE_MESSAGE);
        i++;
        printf("F 1 reçu = \"%s\"\n", messageLire);

        while (strcmp(messageLire, "OK") == 0  && i < 5) {
                printf("FilsInWhile\n");
            //sprintf(messageEcrire, "name2.fifo");
            strcpy(messageEcrire, variables[i]);
            write(tubeFP[W], messageEcrire, SIZE_MESSAGE);
            read(tubePF[R], messageLire, SIZE_MESSAGE);
            printf("F while reçu = \"%s\"\n", messageLire);
            i++;
            printf("FilsFinWhile\n");
        }
        strcpy(messageEcrire, "STOP");
        write(tubeFP[W], messageEcrire, SIZE_MESSAGE);
        read(tubePF[R], messageLire, SIZE_MESSAGE);
        printf("Fils fin nom reçu = \"%s\"\n", messageLire);



        int entreeTube;
        if((entreeTube = open("name0.fifo", O_WRONLY)) == -1)
        {
            fprintf(stderr, "Impossible d'ouvrir l'entrée du tube nommé.\n");
            exit(EXIT_FAILURE);
        }
        printf("Fils pret a écrire dans le tube nommé name0.fifo\n");
        write(entreeTube, "message pour tube0", SIZE_MESSAGE);

    }
    printf("fin\n");
    return EXIT_SUCCESS;
    */
}
