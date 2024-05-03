import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ConectaBd {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            String jdbcDriver = "com.mysql.cj.jdbc.Driver";
            String dbUrl = "jdbc:mysql://localhost:3306/Senac";
            String dbUser = "root";
            String dbPassword = "";

            // Carregar o driver do MySQL do arquivo JAR na pasta lib
            URL url = new URL("file:///path_to_lib/mysql-connector-java.jar");
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {url});
            Class<?> jdbcClass = urlClassLoader.loadClass(jdbcDriver);

            DriverManager.registerDriver((java.sql.Driver) jdbcClass.newInstance());

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            if (connection != null) {
                System.out.println("Conexão bem-sucedida com o banco de dados MySQL!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}