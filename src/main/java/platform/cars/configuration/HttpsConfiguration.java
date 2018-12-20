package platform.cars.configuration;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//原本用于对登陆注册请求使用https，但是测试发现https抓包似乎也可以被看到传输字段。暂时不用
//经过查证，fiddler能够获取请求体中的参数，是由于fiddler作为被信任的代理并安装了fiddler证书，因此fiddler大概可以
//通过授信的其他证书解密请求体，所以能够获取具体内容
//@Configuration
public class HttpsConfiguration {
//
//    @Bean
//    public Connector connector(){
//        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080);//配置http的端口为8080
//        connector.setSecure(false);
//        connector.setRedirectPort(443);
//        return connector;
//    }
//
//    @Bean
//    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
//        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory(){
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint=new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection=new SecurityCollection();
//                collection.addPattern("/user/login");  //登陆和注册请求会重定向到https
//                collection.addPattern("/user/register");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(connector);
//        return tomcat;
//    }
}
