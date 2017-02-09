//Librerias
#include "bitacora.h"

bool_t
xdr_String (XDR *xdrs, String *objp)
{
	register int32_t *buf;

	int i;
	 if (!xdr_vector (xdrs, (char *)objp->opt, 6, //Serialización de la opción add y search
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	 if (!xdr_vector (xdrs, (char *)objp->name, 50, //Serialización del nombre
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	return TRUE;
}
