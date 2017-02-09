//Librerias
#include "bitacora.h"
#include <stdio.h>
#include <stdlib.h>
#include <rpc/pmap_clnt.h>
#include <string.h>
#include <memory.h>
#include <sys/socket.h>
#include <netinet/in.h>

#ifndef SIG_PF
#define SIG_PF void(*)(int)
#endif

static void
bitacora_prog_1(struct svc_req *rqstp, register SVCXPRT *transp)
{
	union {
		char *add_1_arg;
		char *search_1_arg;
	} argument;
	char *result;
	xdrproc_t _xdr_argument, _xdr_result;
	char *(*local)(char *, struct svc_req *);

	switch (rqstp->rq_proc) {
	case NULLPROC:
		(void) svc_sendreply (transp, (xdrproc_t) xdr_void, (char *)NULL);
		return;

	case ADD:
		_xdr_argument = (xdrproc_t) xdr_wrapstring; //Serialización de datos
		_xdr_result = (xdrproc_t) xdr_wrapstring;
		//Se establece un puntero
		local = (char *(*)(char *, struct svc_req *)) add_1_svc;
		break;

	case SEARCH:
		_xdr_argument = (xdrproc_t) xdr_wrapstring; //Serialización de datos
		_xdr_result = (xdrproc_t) xdr_wrapstring;
		//Se establece un puntero
		local = (char *(*)(char *, struct svc_req *)) search_1_svc;
		break;

	default:
		svcerr_noproc (transp);
		return;
	}
	memset ((char *)&argument, 0, sizeof (argument));
	//Unmarshalling de los datos	
	if (!svc_getargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		svcerr_decode (transp);
		return;
	}
	result = (*local)((char *)&argument, rqstp);
	if (result != NULL && !svc_sendreply(transp, (xdrproc_t) _xdr_result, result)) {
		svcerr_systemerr (transp);
	}
	if (!svc_freeargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		fprintf (stderr, "%s", "unable to free arguments");
		exit (1);
	}
	return;
}

int
main (int argc, char **argv)
{
	register SVCXPRT *transp; //Resgistro del servicio

	pmap_unset (BITACORA_PROG, BITACORA_VERS); //Esta rutina limpia la entrada para las tablas de los puertos
	transp = svcudp_create(RPC_ANYSOCK); //Rutina para obtener protocolos

	if (transp == NULL) { //Si no se obtiene identificador
		fprintf (stderr, "%s", "cannot create udp service.");
		exit(1);
	}
	//Un servicio debe registrar los números de puerto
	if (!svc_register(transp, BITACORA_PROG, BITACORA_VERS, bitacora_prog_1, IPPROTO_UDP)) {
		fprintf (stderr, "%s", "unable to register (BITACORA_PROG, BITACORA_VERS, udp).");
		exit(1);
	}
	//Rutina para obtener identificadores de protocolo
	transp = svctcp_create(RPC_ANYSOCK, 0, 0);
	if (transp == NULL) {
		fprintf (stderr, "%s", "cannot create tcp service.");
		exit(1);
	}
	if (!svc_register(transp, BITACORA_PROG, BITACORA_VERS, bitacora_prog_1, IPPROTO_TCP)) {
		fprintf (stderr, "%s", "unable to register (BITACORA_PROG, BITACORA_VERS, tcp).");
		exit(1);
	}

	svc_run ();
	fprintf (stderr, "%s", "svc_run returned");
	exit (1);
	/* NOTREACHED */
}
