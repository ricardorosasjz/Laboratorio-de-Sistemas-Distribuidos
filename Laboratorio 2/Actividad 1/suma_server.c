#include "suma.h"

int *
suma_1_svc(intpair *argp, struct svc_req *rqstp)
{
        static int  result;

        //Se calcula el resultado y se le envia al cliente
        //Se calcula el resultado y se le envia al cliente
        printf("Server response to client...\n");
        printf("parameters: %d, %d\n", argp->a, argp->b);
        result = argp->a + argp->b;
        printf("returning: %d\n", result);
        return &result;
}

