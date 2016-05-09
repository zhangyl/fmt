package test;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class ZeusServer {

    public static void main(String[] args) {
        Server server = new Server(8070);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor("./venus-web/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("./venus-web/src/main/webapp");
        //解决静态资源缓存后再ide里面不能修改问题
        context.setDefaultsDescriptor("./venus-web/src/test/resources/webdefault.xml");
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
