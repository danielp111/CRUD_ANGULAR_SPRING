package soap_corba;

/**
* soap_corba/NotificacionesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from operaciones.idl
* mi�rcoles 9 de diciembre de 2020 12:12:31 AM COT
*/

public final class NotificacionesHolder implements org.omg.CORBA.portable.Streamable
{
  public soap_corba.Notificaciones value = null;

  public NotificacionesHolder ()
  {
  }

  public NotificacionesHolder (soap_corba.Notificaciones initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = soap_corba.NotificacionesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    soap_corba.NotificacionesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return soap_corba.NotificacionesHelper.type ();
  }

}