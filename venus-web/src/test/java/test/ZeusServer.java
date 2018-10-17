package test;

import java.util.ArrayList;
import java.util.List;

public class ZeusServer {
	public static Long i = 0L;
    public static void main(String[] args) {
    	List<String> list = new ArrayList<>();
    	for(;;) {
    		String s = (++i).toString();
    		list.add(s);
    		if(i % 1000000L == 0) {
    			list = new ArrayList<>();
    		}
    	}
    	
        //        Server server = new Server(8070);
        //
        //        WebAppContext context = new WebAppContext();
        //        context.setContextPath("/");
        //        context.setDescriptor("./venus-web/src/main/webapp/WEB-INF/web.xml");
        //        context.setResourceBase("./venus-web/src/main/webapp");
        //        //解决静态资源缓存后再ide里面不能修改问题
        //        context.setDefaultsDescriptor("./venus-web/src/test/resources/webdefault.xml");
        //        context.setParentLoaderPriority(true);
        //        server.setHandler(context);
        //
        //        try {
        //            server.start();
        //            server.join();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }

    }

}
