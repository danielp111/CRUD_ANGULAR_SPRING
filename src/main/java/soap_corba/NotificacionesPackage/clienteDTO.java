package soap_corba.NotificacionesPackage;


/**
* soap_corba/NotificacionesPackage/clienteDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from operaciones.idl
* mi�rcoles 9 de diciembre de 2020 12:12:31 AM COT
*/

public final class clienteDTO implements org.omg.CORBA.portable.IDLEntity
{
  public String nombre = null;
  public String apellido = null;

  public clienteDTO ()
  {
  } // ctor

  public clienteDTO (String _nombre, String _apellido)
  {
    nombre = _nombre;
    apellido = _apellido;
  } // ctor

} // class clienteDTO