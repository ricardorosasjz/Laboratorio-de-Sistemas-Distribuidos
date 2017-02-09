#include "suma.h"

bool_t
xdr_intpair (XDR *xdrs, intpair *objp)
{
        register int32_t *buf;

         if (!xdr_int (xdrs, &objp->a)) //Serialización del primer entero
                 return FALSE;
         if (!xdr_int (xdrs, &objp->b)) //Serialización del segundo entero
                 return FALSE;
        return TRUE;
}

