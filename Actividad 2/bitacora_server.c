//Librerias
#include "bitacora.h"
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

char **
add_1_svc(char **argp, struct svc_req *rqstp)
{
	static char * result;

	/*
	 * insert server code here
	 */
	time_t timeres = time(NULL);
	
	FILE *f = fopen("file.txt", "a+"); 
	if (f == NULL) {
    printf("Error opening file!\n");
    exit(1);
	}
	char * time = asctime(localtime(&timeres));
	
	fprintf(f, "%s %s", *argp, time);
	printf("Server Added:%s %s", *argp, time);
	fclose(f);
	
	asprintf(&result, "%s %s", *argp, time);
	
	return &result;
}

char **
search_1_svc(char **argp, struct svc_req *rqstp)
{
	static char * result;

	/*
	 * insert server code here
	 */
	printf("Server is searching for: %s \n", *argp);
	
	FILE* fh = fopen("file.txt", "r");
	//check if file exists
	if (fh == NULL){
    printf("file does not exists");
    return 0;
	}
 	char buff[1000]; //Tamaño del buffer
  	char *s;
	//Se lee linea por linea
	
	const size_t line_size = 300; //Tamaño de la linea a leer
	char* line = malloc(line_size);
	int count = 0; //Variable por si llegara a haber concurrencia
	while (fgets(line, line_size, fh) != NULL){
  	//printf("%s",line);
  	//Se imprime cada ocurrencia encontrada
  	s = strstr(line, *argp);
 		if (s != NULL) {
     count++;
    }
    else {
			printf("String not found\n"); //Si no se llegara a encontrar a la persona
 		}
	}
	
	//Se imprime el número de registros encontrados
  	printf("Registry found: %d times \n", count);
	asprintf(&result, "Server Found, %s %d times", *argp, count);
	
	return &result;
}
