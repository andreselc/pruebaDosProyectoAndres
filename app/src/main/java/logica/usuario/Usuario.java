package logica.usuario;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.json.*;
/**
 * Clase encargada de manejar la información por usuario
 * @author Grupo4
 *
 */
public class Usuario{
    /**
     * Atributos para el correspondiente funcionamiento de la clase
     */
    private Password password;
    private Correo email;
    private char tipoCuenta;
    private JSONObject usuarioJSON;

    /**
     * Constructor donde se inicializan los atributos por defecto
     */
    public Usuario() {
        password = new Password();
        email = new Correo();
    }

    public Usuario(String password,String email,char tipoCuenta){
        this.password.setPassword(password);
        this.email.setAddress(email);
        //this.tipoCuenta.setTipoCuenta(tipoCuenta);
    }

    /**
     * Constructor donde se inicializan los atributos de password y email, el resto por defecto
     * @param password valor a pasarle al atributo password
     * @param email valor a pasarle al atributo email
     */

    public Usuario(String password, String email) {
        this.password.setPassword(password);
        this.email.setAddress(email);
    }

    /**
     * getter
     * @return devuelve el atributo email
     */
    public String getEmail() {
        return email.getAddress();
    }
    /**
     * getter
     * @return devuelve el atributo password
     */
    public String getPassword() {
        return password.getPassword();
    }

    /**
     * getter
     * @return devuelve el atriburo tipoCuenta
     */
    public char getTipoCuenta() { return tipoCuenta; }

    /**
     * setter modifica la información del parametro tipoCuenta
     * @param tipoCuenta valor a pasarle al atributo tipoCuenta
     */
    public void setTipoCuenta(char tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    /**
     * setter, modifica la información del parametro address
     * @param address valor a pasarle al atributo address
     */

    public void setAddress(String address) {
        email.setAddress(address);
    }
    /**
     * Setter, modifica la información del parametro password
     * @param password contraseña
     */
    public void setPassword(String password) {
        this.password.setPassword(password);
    }

    public JSONObject getUsuarioJSON() {
        return usuarioJSON;
    }

    public void setUsuarioJSON(JSONObject usuarioJSON) {
        this.usuarioJSON = usuarioJSON;
    }



    @SuppressWarnings("unchecked")
     public void llenarObjetoJson(Usuario usuario) {
      /*  Encrypt encriptar=new Encrypt();
        for (MonedasUsuario cripto: listaMonedas) {
            listaMonedasJSON.add(cripto.getMonedaJSON());
        }
        //usuarioJSON.put("listaMonedas", listaMonedasJSON);
        usuario.setPassword(encriptar.getAES(usuario.getPassword()));
        usuarioJSON.put("contraseña",usuario.getPassword());
        usuarioJSON.put("correo",usuario.getEmail());
        if (usuario.obtenerDireccionIP()!=null) {
            usuarioJSON.put("direccionIP",usuario.obtenerDireccionIP());
        }*/
    }

    /**
     * Verifica si el correo existe
     * @param correo correo a validar
     * @return true o false segun corresponda
     */
    public boolean verificarCorreo (String correo) {
        if (email.read(correo)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Verifica si la contraseña existe
     * @param clave contraseña a validar
     * @return true o false segun corresponda
     */
    public boolean verificarPassword (String clave) {
        if (password.ValidarPassword(clave)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de obtener la direcciónIP
     * @return devuelve la dirección IP
     */
    public String obtenerDireccionIP(){
        try {
            Enumeration<NetworkInterface> interfaz = NetworkInterface.getNetworkInterfaces();
            while( interfaz.hasMoreElements()){
                for ( InterfaceAddress direccion : interfaz.nextElement().getInterfaceAddresses()) {
                    if ( direccion.getAddress().isSiteLocalAddress()) {
                        return direccion.toString();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
