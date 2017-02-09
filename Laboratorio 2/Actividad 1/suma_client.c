#include "suma.h"
#include <stdio.h>

//Los argumentos son recibidos del main
void
suma_prog_1(char *host, int a, int b)
{
        CLIENT *clnt;
        int  *result_1;
        intpair  suma_1_arg;

#ifndef DEBUG
        clnt = clnt_create (host, SUMA_PROG, SUMA_VERS, "udp"); //Parámetros para la creación del cliente
        if (clnt == NULL) { //Si no se crea el cliente
                clnt_pcreateerror (host);
                exit (1);
        }
#endif  /* DEBUG */

        suma_1_arg.a = a; //datos a suma
        suma_1_arg.b = b;
        result_1 = suma_1(&suma_1_arg, clnt); //Se realizan las llamadas al otro procedimiento 
        if (result_1 == (int *) NULL) { //Si no se llegará a hacer la conexión al servidor
                clnt_perror (clnt, "call failed");
        }else {
                printf("result = %d\n", *result_1); //Aviso si se conecta  con el servidor
        }
#ifndef DEBUG
        clnt_destroy (clnt); //Eliminación del cliente
#endif   /* DEBUG */
}

//Función principal para l cliente
int
main(int argc, char *argv[]) {
        char *host;
        int a, b;
        if (argc != 4) {
            printf ("usage: %s server_host num1 num2\n", argv[0]);
            exit(1);
        }
        host = argv[1];
        if ((a = atoi(argv[2])) == 0 && *argv[2] != '0') {
            fprintf(stderr, "invalid value: %s\n", argv[2]);
            exit(1);
        }
        if ((b = atoi(argv[3])) == 0 && *argv[3] != '0') {
            fprintf(stderr, "invalid value: %s\n", argv[3]);
            exit(1);
        }
        suma_prog_1(host, a, b); //Paso para comprobar si los datos son correctos
}
~        
