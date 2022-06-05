package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BDcontext {

    private static Connection conn;
    
    /* Metodo de conexión */
    
    public BDcontext(){
        String dbURL ="jdbc:mysql://localhost:3306/ferreterianueva";

        String username = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection (dbURL, username, password);
        } catch ( SQLException ex ) {
            ex.printStackTrace();
        }
    }
    
    
    /* PRODUCTO */
    
    
    public ArrayList<ProductoClass> SelectProducto() throws SQLException{
        /* Este metodo sive para seleccionar todos los registros de producto, retorna una lista la cual se va a usar para llenar la tabla */
        String sql = "SELECT p.codigo, p.nombre, p.valorcompra, p.valorventa, p.cantidad, u.nombre, c.nombre FROM productos p INNER JOIN unidad u ON p.idunidad = u.id INNER JOIN categorias c ON p.idcategoria = c.id;";
        Statement statement = conn.createStatement();
        ArrayList<ProductoClass> lista = new ArrayList<>();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            ProductoClass producto = new ProductoClass();
            producto.Codigo = result.getString(1);
            producto.Nombre = result.getString(2);
            producto.ValorCompra = result.getString(3);
            producto.ValorVenta = result.getString(4);
            producto.Cantidad = result.getString(5);
            producto.Unidades = result.getString(6);
            producto.Categoria = result.getString(7);
            lista.add(producto);
        }
        return lista;
    }
    
    public DefaultTableModel QueryProducto(String codigo) throws SQLException{
        /* Este metodo sirve para seleccionar un registro en especifico de producto, retorna un mensaje y requiere como parametros el codigo de producto a buscar y la tabla*/
        String sql = "SELECT p.codigo, p.nombre, p.valorcompra, p.valorventa, p.cantidad, u.nombre, c.nombre FROM productos p INNER JOIN unidad u ON p.idunidad = u.id INNER JOIN categorias c ON p.idcategoria = c.id WHERE p.codigo = '" + codigo + "';";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        ArrayList<ProductoClass> lista = new ArrayList<>();
        if(result.next()){
            ProductoClass producto = new ProductoClass();
            producto.Codigo = result.getString(1);
            producto.Nombre = result.getString(2);
            producto.ValorCompra = result.getString(3);
            producto.ValorVenta = result.getString(4);
            producto.Cantidad = result.getString(5);
            producto.Unidades = result.getString(6);
            producto.Categoria = result.getString(7);
            lista.add(producto);
            return RefreshProducto(lista);
        }
        return null;
    }
    
    public ProductoClass QueryProducto(String codigo, ProductoClass producto) throws SQLException{
        /* Este metodo sirve para seleccionar un registro en especifico de producto, retorna un mensaje y requiere como parametros el codigo de producto a buscar y la tabla*/
        String sql = "SELECT p.codigo, p.nombre, p.valorcompra, p.valorventa, p.cantidad, u.nombre, c.nombre FROM productos p INNER JOIN unidad u ON p.idunidad = u.id INNER JOIN categorias c ON p.idcategoria = c.id WHERE p.codigo = '" + codigo + "';";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        ArrayList<ProductoClass> lista = new ArrayList<>();
        if(result.next()){
            producto.Codigo = result.getString(1);
            producto.Nombre = result.getString(2);
            producto.ValorCompra = result.getString(3);
            producto.ValorVenta = result.getString(4);
            producto.Cantidad = result.getString(5);
            producto.Unidades = result.getString(6);
            producto.Categoria = result.getString(7);
            return producto;
        }
        return null;
    }
    
    public String CreateProducto(ProductoClass producto) throws SQLException{
        /* Este metodo se usa para crear un nuevo producto, requiere como parametro un objeto de tipo producto y devuelve un mensaje */
        String sql = "SELECT * FROM productos WHERE productos.codigo = " + producto.Codigo + ";";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            return "Error: Este registro ya existe";
        }else{
            sql = "SELECT u.id FROM unidad u WHERE u.nombre = '" + producto.Unidades + "';";
            result = statement.executeQuery(sql);
            while(result.next()){
                producto.Unidades = result.getString(1);
                if(!producto.ValorCompra.matches("([0-9]+\\.[0-9]+)|([0-9]+)")||!producto.ValorVenta.matches("([0-9]+\\.[0-9]+)|([0-9]+)")||!producto.Cantidad.matches("([0-9]+\\.[0-9]+)|([0-9]+)")){
                    return "Ha ocurrido un error con los tipos de datos";
                }
            }
            sql = "SELECT c.id FROM categorias c WHERE c.nombre = '" + producto.Categoria + "';";
            result = statement.executeQuery(sql);
            while(result.next()){
                producto.Categoria = result.getString(1);
            }
            sql = "INSERT INTO productos VALUES (?,?,?,?,?,?,?);";
            PreparedStatement statementInsert = conn.prepareStatement(sql);
            statementInsert.setInt(1, Integer.parseInt(producto.Codigo));
            statementInsert.setString(2, producto.Nombre);
            statementInsert.setDouble(3, Double.parseDouble(producto.ValorCompra));
            statementInsert.setDouble(4, Double.parseDouble(producto.ValorVenta));
            statementInsert.setInt(5, Integer.parseInt(producto.Cantidad));
            statementInsert.setInt(6, Integer.parseInt(producto.Unidades));
            statementInsert.setInt(7, Integer.parseInt(producto.Categoria));
            int rowsInserted = statementInsert.executeUpdate();
            if(rowsInserted > 0){
                return "Inserción exitosa";
            }
        }
        return "Ha ocurrido un error con los tipos de datos";
    }
    
    public String UpdateProducto(ProductoClass producto) throws SQLException{
        /* Este metodo actualiza un producto especifico, requiere como parametro un objeto de tipo producto y devuelve un mensaje */
        String sql = "SELECT * FROM productos WHERE productos.codigo = " + producto.Codigo + ";";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            sql = "SELECT u.id FROM unidad u WHERE u.nombre = '" + producto.Unidades + "';";
            result = statement.executeQuery(sql);
            while(result.next()){
                producto.Unidades = result.getString(1);
            }
            sql = "SELECT c.id FROM categorias c WHERE c.nombre = '" + producto.Categoria + "';";
            result = statement.executeQuery(sql);
            while(result.next()){
                producto.Categoria = result.getString(1);
            }
            sql = "UPDATE productos p SET p.nombre=?, p.valorcompra=?, p.valorventa=?, p.cantidad=?, p.idunidad=?, p.idcategoria=? WHERE p.codigo=?";
            PreparedStatement statementUpdate = conn.prepareStatement(sql);
            statementUpdate.setInt(7, Integer.parseInt(producto.Codigo));
            statementUpdate.setString(1, producto.Nombre);
            statementUpdate.setDouble(2, Double.parseDouble(producto.ValorCompra));
            statementUpdate.setDouble(3, Double.parseDouble(producto.ValorVenta));
            statementUpdate.setInt(4, Integer.parseInt(producto.Cantidad));
            statementUpdate.setInt(5, Integer.parseInt(producto.Unidades));
            statementUpdate.setInt (6, Integer.parseInt(producto.Categoria));
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
        String sql = "SELECT * FROM productos WHERE productos.codigo = " + codigo + ";";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            sql = "DELETE FROM productos WHERE productos.codigo =?";
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
        String[] nombreColumnas = {"Codigo","Nombre","Valor de Compra","Valor de Venta","Cantidad","Unidad","Categoria"};
        model = new DefaultTableModel(null, nombreColumnas);
        ArrayList<ProductoClass> lista = SelectProducto();
        String[] datosProducto = new String[7];
        for(ProductoClass pc : lista){
            datosProducto[0] = pc.Codigo+"";
            datosProducto[1] = pc.Nombre+"";
            datosProducto[2] = pc.ValorCompra+"";
            datosProducto[3] = pc.ValorVenta+"";
            datosProducto[4] = pc.Cantidad+"";
            datosProducto[5] = pc.Unidades+"";
            datosProducto[6] = pc.Categoria+"";
            model.addRow(datosProducto);
        }
        return model;
    }
    
    public DefaultTableModel RefreshProducto(ArrayList<ProductoClass> lista) throws SQLException{
        /* Este metodo actualiza la tabla pero solo con el o los registros que sean indicados en la lista que se envia como parametro */
        DefaultTableModel model;
        String[] nombreColumnas = {"Codigo","Nombre","Valor de Compra","Valor de Venta","Cantidad","Unidad","Categoria"};
        model = new DefaultTableModel(null, nombreColumnas);
        String[] datosProducto = new String[7];
        for(ProductoClass pc : lista){
            datosProducto[0] = pc.Codigo+"";
            datosProducto[1] = pc.Nombre+"";
            datosProducto[2] = pc.ValorCompra+"";
            datosProducto[3] = pc.ValorVenta+"";
            datosProducto[4] = pc.Cantidad+"";
            datosProducto[5] = pc.Unidades+"";
            datosProducto[6] = pc.Categoria+"";
            model.addRow(datosProducto);
        }
        return model;
    }
    
    public Object[] LlenarUnidad() throws SQLException{
        String sql = "SELECT u.nombre FROM unidad u ORDER BY u.nombre;";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Seleccionar");
        while(result.next()){
            lista.add(result.getString(1));
        }
        return (Object[]) lista.toArray();
    }
    
    public Object[] LlenarCategoria() throws SQLException{
        String sql = "SELECT c.nombre FROM categorias c ORDER BY c.nombre;";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Seleccionar");
        while(result.next()){
            lista.add(result.getString(1));
        }
        return (Object[]) lista.toArray();
    }

    /* Metodos de acceso para la conexion */
    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        BDcontext.conn = conn;
    }
}