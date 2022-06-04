package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BDcontext {

    private static Connection conn;
    
    /* Metodo de conexión */
    
    public BDcontext(){
        String dbURL ="jdbc:mysql://localhost:3306/ferreteria";

        String username = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection (dbURL, username, password);
        } catch ( SQLException ex ) {
            ex.printStackTrace();
        }
    }
    
    
    /* CATEGORIA DE PRODUCTOS */
    
    
    public void SelectCategoria() throws SQLException{
        String sql = "SELECT * FROM categoria";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            String nombre = result.getString(2);
            
            System.out.println("Nombre: " + nombre + "\n");
        }
    }
    
    
    /* CONFIGURACION */
    
    
    public ConfigClass SelectConfig() throws SQLException{
        /* Este metodo se usa para traer la informacion que se muestra en el inicio de la aplicación */
        String sql = "SELECT * FROM config";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        ConfigClass config = new ConfigClass();
        while(result.next()){
            config.NombreFerreteria = result.getString(1);
            config.Propietario = result.getString(2);
            config.Direccion = result.getString(3);
            config.Telefono = result.getString(4);
            
        }
        return config;
    }
    
    
    /* PRODUCTO */
    
    
    public ArrayList<ProductoClass> SelectProducto() throws SQLException{
        /* Este metodo sive para seleccionar todos los registros de producto, retorna una lista la cual se va a usar para llenar la tabla */
        String sql = "SELECT producto.codigo,producto.nombre,producto.valordecompra,producto.valordeventa,producto.cantidad,unidad.nombre,producto.detalle FROM producto INNER JOIN unidad ON producto.idunidades = unidad.id;";
        Statement statement = conn.createStatement();
        ArrayList<ProductoClass> lista = new ArrayList<>();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            ProductoClass producto = new ProductoClass();
            producto.Codigo = result.getString(1);
            producto.Nombre = result.getString(2);
            producto.ValorDeCompra = result.getString(3);
            producto.ValorDeVenta = result.getString(4);
            producto.Cantidad = result.getString(5);
            producto.Unidades = result.getString(6);
            producto.Detalle = result.getString(7);
            lista.add(producto);
        }
        return lista;
    }
    
    public String QueryProducto(String codigo, JTable tabla) throws SQLException{
        /* Este metodo sirve para seleccionar un registro en especifico de producto, retorna un mensaje y requiere como parametros el codigo de producto a buscar y la tabla*/
        String sql = "SELECT producto.codigo,producto.nombre,producto.valordecompra,producto.valordeventa,producto.cantidad,unidad.nombre,producto.detalle FROM producto INNER JOIN unidad ON producto.idunidades = unidad.id WHERE producto.codigo = '" + codigo + "';";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        ArrayList<ProductoClass> lista = new ArrayList<>();
        if(result.next()){
            ProductoClass producto = new ProductoClass();
            producto.Codigo = result.getString(1);
            producto.Nombre = result.getString(2);
            producto.ValorDeCompra = result.getString(3);
            producto.ValorDeVenta = result.getString(4);
            producto.Cantidad = result.getString(5);
            producto.Unidades = result.getString(6);
            producto.Detalle = result.getString(7);
            lista.add(producto);
            tabla.setModel(RefreshProducto(lista));
            return "Consulta exitosa";
        }
        return "El registro no existe";
    }
    
    public String CreateProducto(ProductoClass producto) throws SQLException{
        /* Este metodo se usa para crear un nuevo producto, requiere como parametro un objeto de tipo producto y devuelve un mensaje */
        String sql = "SELECT * FROM producto WHERE producto.codigo = '" + producto.Codigo + "';";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            return "Error: Este registro ya existe";
        }else{
            sql = "SELECT unidad.id FROM unidad WHERE unidad.nombre = '" + producto.Unidades + "';";
            result = statement.executeQuery(sql);
            while(result.next()){
                producto.Unidades = result.getString(1);
                if(!producto.ValorDeCompra.matches("[0-9]+\\.[0-9]+")||!producto.ValorDeVenta.matches("[0-9]+\\.[0-9]+")||!producto.Cantidad.matches("[0-9]+\\.[0-9]+")){
                    return "Ha ocurrido un error con los tipos de datos";
                }
            }            
            sql = "INSERT INTO producto VALUES (?,?,?,?,?,?,?);";
            PreparedStatement statementInsert = conn.prepareStatement(sql);
            statementInsert.setString(1, producto.Codigo);
            statementInsert.setString(2, producto.Nombre);
            statementInsert.setDouble(3, Double.parseDouble(producto.ValorDeCompra));
            statementInsert.setDouble(4, Double.parseDouble(producto.ValorDeVenta));
            statementInsert.setDouble(5, Double.parseDouble(producto.Cantidad));
            statementInsert.setInt(6, Integer.parseInt(producto.Unidades));
            statementInsert.setString(7, producto.Detalle);
            int rowsInserted = statementInsert.executeUpdate();
            if(rowsInserted > 0){
                return "Inserción exitosa";
            }
        }
        return "Ha ocurrido un error con los tipos de datos";
    }
    
    public String UpdateProducto(ProductoClass producto) throws SQLException{
        /* Este metodo actualiza un producto especifico, requiere como parametro un objeto de tipo producto y devuelve un mensaje */
        String sql = "SELECT * FROM producto WHERE producto.codigo = '" + producto.Codigo + "';";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            sql = "SELECT unidad.id FROM unidad WHERE unidad.nombre = '" + producto.Unidades + "';";
            result = statement.executeQuery(sql);
            while(result.next()){
                producto.Unidades = result.getString(1);
                if(!producto.ValorDeCompra.matches("[0-9]+\\.[0-9]+")||!producto.ValorDeVenta.matches("[0-9]+\\.[0-9]+")||!producto.Cantidad.matches("[0-9]+\\.[0-9]+")){
                    return "Ha ocurrido un error con los tipos de datos";
                }
            } 
            sql = "UPDATE producto SET producto.nombre=?, producto.valordecompra=?, producto.valordeventa=?, producto.cantidad=?, producto.idunidades=?, producto.detalle=? WHERE producto.codigo=?";
            PreparedStatement statementUpdate = conn.prepareStatement(sql);
            statementUpdate.setString(7, producto.Codigo);
            statementUpdate.setString(1, producto.Nombre);
            statementUpdate.setDouble(2, Double.parseDouble(producto.ValorDeCompra));
            statementUpdate.setDouble(3, Double.parseDouble(producto.ValorDeVenta));
            statementUpdate.setDouble(4, Double.parseDouble(producto.Cantidad));
            statementUpdate.setInt(5, Integer.parseInt(producto.Unidades));
            statementUpdate.setString(6, producto.Detalle);
            int rowsInserted = statementUpdate.executeUpdate();
            if(rowsInserted > 0){
                return "Actualización exitosa";
            }
        }else{
            return "Error: El registro no existe";
        }
        return null;
    }
    
    public String DeleteProducto(String codigo) throws SQLException{
        /* Este metodo elimina un producto en especifico */
        String sql = "SELECT * FROM producto WHERE producto.codigo = '" + codigo + "';";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            sql = "DELETE FROM producto WHERE producto.codigo =?";
            PreparedStatement statementDelete = conn.prepareStatement(sql);
            statementDelete.setString(1, codigo);
            int rowsDeleted = statementDelete.executeUpdate();
            if (rowsDeleted > 0) {
                return "El registro se ha eliminado correctamente";
            }
        }
        else{
            return "El registro no existe";
        }
        return "Ha ocurrido un error";
    }
    
    public DefaultTableModel RefreshProducto() throws SQLException{
        /* Este metodo refresca la tabla para que se muestren los cambios realizados (no los hace por si mismo, tiene que llamarse en el momento que se desee actualizar la tabla) */
        DefaultTableModel model;
        String[] nombreColumnas = {"Codigo","Nombre","Valor de Compra","Valor de Venta","Cantidad","Unidad","Detalles"};
        model = new DefaultTableModel(null, nombreColumnas);
        ArrayList<ProductoClass> lista = SelectProducto();
        String[] datosProducto = new String[7];
        for(ProductoClass pc : lista){
            datosProducto[0] = pc.Codigo+"";
            datosProducto[1] = pc.Nombre+"";
            datosProducto[2] = pc.ValorDeCompra+"";
            datosProducto[3] = pc.ValorDeVenta+"";
            datosProducto[4] = pc.Cantidad+"";
            datosProducto[5] = pc.Unidades+"";
            datosProducto[6] = pc.Detalle+"";
            model.addRow(datosProducto);
        }
        return model;
    }
    
    public DefaultTableModel RefreshProducto(ArrayList<ProductoClass> lista) throws SQLException{
        /* Este metodo actualiza la tabla pero solo con el o los registros que sean indicados en la lista que se envia como parametro */
        DefaultTableModel model;
        String[] nombreColumnas = {"Codigo","Nombre","Valor de Compra","Valor de Venta","Cantidad","Unidad","Detalles"};
        model = new DefaultTableModel(null, nombreColumnas);
        String[] datosProducto = new String[7];
        for(ProductoClass pc : lista){
            datosProducto[0] = pc.Codigo+"";
            datosProducto[1] = pc.Nombre+"";
            datosProducto[2] = pc.ValorDeCompra+"";
            datosProducto[3] = pc.ValorDeVenta+"";
            datosProducto[4] = pc.Cantidad+"";
            datosProducto[5] = pc.Unidades+"";
            datosProducto[6] = pc.Detalle+"";
            model.addRow(datosProducto);
        }
        return model;
    }
    
    /* TIPO DE UNIDAD */
    
    
    public void SelectTipoUnidad() throws SQLException{
        String sql = "SELECT * FROM tipounidad";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            String nombre = result.getString(2);
            
            System.out.println("Nombre: " + nombre + "\n");
        }
    }
    
    
    /* UNIDADES */
    
    
    public void SelectUnidades() throws SQLException{
        String sql = "SELECT u.id, u.nombre, u.unidad, t.nombre FROM unidad u INNER JOIN tipounidad t ON u.idtipounidad = t.id ORDER BY id";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            String nombre = result.getString(2);
            String unidad = result.getString(3);
            String tipounidad = result.getString(4);
            
            System.out.println("Nombre: " + nombre + " | Unidad: " + unidad + " | Tipo de unidad: " + tipounidad + "\n");
        }
    }

    /* Metodos de acceso para la conexion */
    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        BDcontext.conn = conn;
    }
}