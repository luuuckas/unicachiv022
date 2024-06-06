package app;
//cuando se inicie aplicacion
import ModeloDAO.ProductoDAO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInicio implements ServletContextListener{
private ProductoDAO prodDAO = new ProductoDAO();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("productos", prodDAO.obtenerProducto());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      
   }
    
}
